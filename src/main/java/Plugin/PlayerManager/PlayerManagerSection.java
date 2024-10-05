package Plugin.PlayerManager;

import Plugin.PlayerManager.Listener.*;
import Plugin.PlayerManager.Model.PlayerDataGLobal;
import Plugin.Section;
import Plugin.xBxTcore;
import lombok.Getter;

@Getter
public class PlayerManagerSection implements Section {

    @Getter private final xBxTcore plugin;
    @Getter private static PlayerManager playerManager;
    @Getter private static PlayerDataGLobal playerDataGLobal;
    @Getter private static ModerationChat moderationChat;
    @Getter public static boolean moderationChatEnabled = true;

    public PlayerManagerSection(xBxTcore xBxTcore) {
        plugin = xBxTcore;
    }

    @Override
    public void enable() {
        moderationChat = new ModerationChat(plugin);
        playerDataGLobal = new PlayerDataGLobal();
        playerManager = new PlayerManager(plugin);
        plugin.register(new BlockerListener(plugin));
        plugin.register(new PlayerListener(plugin));
        plugin.register(new CommandListener());
        plugin.register(new JoinAndQuitPlayerListener());
        plugin.register(new EnvironmentInteractListener());
    }

    @Override
    public void disable() {

    }

    @Override
    public String getName() {
        return "PlayerManagerSection";
    }

    @Override
    public void reloadConfig() {

    }
}
