package xyz.xbcore.PlayerManager;

import xyz.xbcore.PlayerManager.Listener.*;
import xyz.xbcore.PlayerManager.Model.PlayerDataGLobal;
import xyz.xbcommun.Section;
import xyz.xbcore.xBxTcore;
import lombok.Getter;

import static xyz.xbcommun.RegisterManager.register;

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
        register(new BlockerListener(plugin));
        register(new PlayerListener(plugin));
        register(new CommandListener());
        register(new JoinAndQuitPlayerListener());
        register(new EnvironmentInteractListener());
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
