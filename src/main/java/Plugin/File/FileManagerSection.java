package Plugin.File;

import Plugin.File.BLackList.BlackListIpManager;
import Plugin.File.PlayerData.PlayerfileManager;
import Plugin.Section;
import Plugin.xBxTcore;

public class FileManagerSection implements Section {

    private final xBxTcore plugin;

    private static PlayerfileManager playerfileManager;
    private static BlackListIpManager blacklistIpManager;

    public FileManagerSection(xBxTcore plugin) {
        this.plugin = plugin;
    }

    @Override
    public void enable() {
        playerfileManager = new PlayerfileManager(plugin);
        blacklistIpManager = new BlackListIpManager(plugin);
    }

    @Override
    public void disable() {
        BlackListIpManager.saveIpBlacklist();
    }

    @Override
    public String getName() {
        return "FileManagerSection";
    }

    @Override
    public void reloadConfig() {

    }

    public static PlayerfileManager getPlayerFileManager() {
        return playerfileManager;
    }

    public static BlackListIpManager getBlacklistIpManager() {
        return blacklistIpManager;
    }
}
