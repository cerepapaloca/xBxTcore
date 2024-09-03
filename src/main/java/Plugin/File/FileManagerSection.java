package Plugin.File;

import Plugin.File.BLackList.BlackListIpManager;
import Plugin.File.Clan.ClanFileManager;
import Plugin.File.PlayerData.PlayerfileManager;
import Plugin.Section;
import Plugin.xBxTcore;

public class FileManagerSection implements Section {

    private final xBxTcore plugin;

    private static PlayerfileManager playerfileManager;
    private static BlackListIpManager blacklistIpManager;
    private static ClanFileManager clanFileManager;

    public FileManagerSection(xBxTcore plugin) {
        this.plugin = plugin;
    }

    @Override
    public void enable() {
        playerfileManager = new PlayerfileManager(plugin);
        blacklistIpManager = new BlackListIpManager(plugin);
        clanFileManager = new ClanFileManager(plugin);
    }

    @Override
    public void disable() {

    }

    @Override
    public String getName() {
        return "FileManagerSection";
    }

    @Override
    public void reloadConfig() {

    }

    public static ClanFileManager getClanFileManager() {
        return clanFileManager;
    }

    public static PlayerfileManager getPlayerFileManager() {
        return playerfileManager;
    }

    public static BlackListIpManager getBlacklistIpManager() {
        return blacklistIpManager;
    }
}
