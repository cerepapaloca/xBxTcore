package Plugin.PlayerManager;

import Plugin.PlayerManager.Listener.BlockerListener;
import Plugin.PlayerManager.Listener.PlayerListener;
import Plugin.PlayerManager.Listener.RewardsListener;
import Plugin.PlayerManager.Model.PlayerDataGLobal;
import Plugin.Section;
import Plugin.xBxTcore;

public class PlayerManagerSection implements Section {

    private final xBxTcore plugin;

    private static PlayerManager playerManager;
    public static PlayerDataGLobal playerDataGLobal;
    private static ModerationChat moderationChat;
    public static boolean moderationChatEnabled = false;

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
        plugin.register(new RewardsListener());
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

    public static PlayerDataGLobal getPlayerDataGLobal() {
        return playerDataGLobal;
    }

    public static PlayerManager getPlayerManager() {
        return playerManager;
    }

    public static ModerationChat getModerationChat() {
        return moderationChat;
    }
}
