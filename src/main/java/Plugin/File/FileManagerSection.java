package Plugin.File;

import Plugin.File.BLackList.BlackListIpManager;
import Plugin.File.PlayerData.PlayerfileManager;
import Plugin.Section;
import Plugin.xBxTcore;

public class FileManagerSection implements Section {

    private final xBxTcore plugin;

    private static PlayerfileManager playerfileManager;
    private static BlackListIpManager blacklistIpManager;
    private static MySQLConnection mysql;
    private static MySQLManager mySQLManager;

    public FileManagerSection(xBxTcore plugin) {
        this.plugin = plugin;
    }

    @Override
    public void enable() {
        playerfileManager = new PlayerfileManager(plugin);
        blacklistIpManager = new BlackListIpManager(plugin);
        mysql = new MySQLConnection("192.168.1.55", "xbxtcore", "root", "");
        mysql.connect();
        mySQLManager = new MySQLManager(mysql);
        mySQLManager.createBanTableIfNotExists();
        mysql.reloadBannedIPs();
    }

    @Override
    public void disable() {
        BlackListIpManager.saveIpBlacklist();
        mysql.close();
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

    public static MySQLManager getMySQLManager() {
        return mySQLManager;
    }

    public static MySQLConnection getMySQLConnection() {
        return mysql;
    }
}
