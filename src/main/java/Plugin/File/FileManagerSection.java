package Plugin.File;

import Plugin.File.BLackList.BlackListIpManager;
import Plugin.File.PlayerData.PlayerfileManager;
import Plugin.Section;
import Plugin.xBxTcore;
import lombok.Getter;

import java.net.SocketException;
import java.util.Objects;

import static Plugin.Service.DDNS_NameCheap.getPrivateIP;

@Getter
public class FileManagerSection implements Section {
    private final xBxTcore plugin;
    @Getter private static PlayerfileManager playerFileManager;
    @Getter private static BlackListIpManager blacklistIpManager;
    @Getter private static MySQLConnection mysql;
    @Getter private static MySQLManager mySQLManager;

    public FileManagerSection(xBxTcore plugin) {
        this.plugin = plugin;
    }

    @Override
    public void enable() {
        String myIpMySql;
        try {
            if (Objects.equals(getPrivateIP(), "192.168.1.4")){
                myIpMySql = "192.168.1.55";
            }else{
                myIpMySql = "127.0.0.1";
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        playerFileManager = new PlayerfileManager(plugin);
        blacklistIpManager = new BlackListIpManager(plugin);
        mysql = new MySQLConnection(myIpMySql, "xbxtcore", "root", "");
        MySQLConnection.connect();
        mySQLManager = new MySQLManager(mysql);
        mySQLManager.createBanTable();
        MySQLConnection.reloadBannedBans();
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
        MySQLConnection.reloadBannedBans();
        blacklistIpManager.ReloadIpBlacklist();
    }
}
