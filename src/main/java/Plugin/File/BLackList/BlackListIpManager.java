package Plugin.File.BLackList;

import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.net.UnknownHostException;

import static Plugin.Messages.MessageManager.*;

public class BlackListIpManager {

    private final xBxTcore plugin;
    private static BlackListIpFile blackListIpFile;
    public static final HashSet<byte[]> blackListedIps = new HashSet<>();

    public BlackListIpManager(xBxTcore plugin) {
        blackListIpFile = new BlackListIpFile("BlackListIp.yml", null, plugin);
        blackListIpFile.registerConfig();
        this.plugin = plugin;
        loadIpBlacklist();
    }

    public static ArrayList<InetAddress> loadIpBlacklist(){
        ArrayList<InetAddress> ipBlacklist = new ArrayList<>();
        blackListedIps.clear();
        List<?> rawList = blackListIpFile.getBlackListIpFile().getList("ipblacklist", null);
        if (rawList != null) {
            for (Object obj : rawList) {
                if (obj != null) {
                    if (obj instanceof String ip) {
                        try {
                            ipBlacklist.add(InetAddress.getByName(ip.replace("/","")));
                            blackListedIps.add(InetAddress.getByName(ip.replace("/","")).getAddress());
                        }catch (UnknownHostException e) {
                            Bukkit.getLogger().warning(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning + "La ip " + ip.replace("/","") + " esta mal y sea omitida"));
                            e.printStackTrace();
                        }

                    }
                }
            }
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', ColorSuccess +  blackListedIps.size() + " Ips están en la lista negra"));
        return ipBlacklist;
    }

    public static void AddIpBlackListAndSave(InetAddress ip) {
        blackListedIps.add(ip.getAddress());
        saveIpBlacklist();
    }

    public static void RemoveIpBlackList(InetAddress ip) {
        blackListedIps.remove(ip.getAddress());
        blackListIpFile.getBlackListIpFile().set("ipblacklist", blackListedIps);
        blackListIpFile.saveConfig();
    }

    public void ReloadIpBlacklist() {
        blackListedIps.clear();
        blackListIpFile.reloadConfig();
        loadIpBlacklist();
    }

    public static void saveIpBlacklist() {
        ArrayList<String> saveBlackListedIps = new ArrayList<>();
        for (byte[] bytes : blackListedIps) {
            try {
                InetAddress ip = InetAddress.getByAddress(bytes);
                if (!saveBlackListedIps.contains(ip.toString())) {
                    saveBlackListedIps.add(ip.toString());
                }
            }catch (UnknownHostException e) {
                Bukkit.getLogger().warning(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning + "Una Ip esta mal y sea omitida en auto save"));
                e.printStackTrace();
            }

        }
        blackListIpFile.getBlackListIpFile().set("ipblacklist", saveBlackListedIps);
        blackListIpFile.saveConfig();
    }
}
