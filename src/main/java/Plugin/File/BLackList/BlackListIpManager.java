package Plugin.File.BLackList;


import Plugin.Section;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import static Plugin.Messages.MessageManager.ColorSuccess;

public class BlackListIpManager {

    private final xBxTcore plugin;
    private static BlackListIpFile blackListIpFile;
    public static final ArrayList<String> blackListedIps = new ArrayList<>();

    public BlackListIpManager(xBxTcore plugin) {
        blackListIpFile = new BlackListIpFile("BlackListIp.yml", null, plugin);
        blackListIpFile.registerConfig();
        this.plugin = plugin;
        loadIpBlacklist();
    }

    public void loadIpBlacklist(){
        blackListedIps.clear();
        List<?> rawList = blackListIpFile.getBlackListIpFile().getList("ipblacklist", null);
        if (rawList != null) {
            for (Object obj : rawList) {
                if (obj != null) {
                    if (obj instanceof String ip) {
                        blackListedIps.add(ip);
                    }
                }
            }
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', ColorSuccess +  blackListedIps.size() + " Ips están en la lista negra"));
    }

    public static void AddIpBlackList(InetAddress ip) {
        blackListedIps.add(ip.toString());
        blackListIpFile.getBlackListIpFile().set("ipblacklist", blackListedIps);
        blackListIpFile.saveConfig();
    }

    public static void RemoveIpBlackList(InetAddress ip) {
        blackListedIps.remove(ip.toString());
        blackListIpFile.getBlackListIpFile().set("ipblacklist", blackListedIps);
        blackListIpFile.saveConfig();
    }

    public void ReloadIpBlacklist() {
        blackListedIps.clear();
        blackListIpFile.reloadConfig();
        loadIpBlacklist();
    }
}
