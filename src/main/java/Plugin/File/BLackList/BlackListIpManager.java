package Plugin.File.BLackList;

import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
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
                            ipBlacklist.add(InetAddress.getByName(ip));
                            blackListedIps.add(InetAddress.getByName(ip).getAddress());
                        }catch (UnknownHostException e) {
                            Bukkit.getLogger().warning(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning + "La ip " + ip.replace("/","") + " esta mal y sea omitida"));
                            e.printStackTrace();
                        }

                    }
                }
            }
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',  prefixConsole + Colorinfo + "hay" + blackListedIps.size() + " Ips están en la lista anti bot"));
        return ipBlacklist;
    }

    public static void RemoveIpBlackListAndSave(InetAddress ip) {
        for (byte[] blackListedIp : blackListedIps) {
            if (Arrays.equals(blackListedIp, ip.getAddress())) {
                blackListedIps.remove(blackListedIp);
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorSuccess + "Se elimino y " +
                        "guardo la lista de ips. La ip: " + ip.getHostAddress()));
                saveIpBlacklist();
                return;
            }
        }
        Bukkit.getConsoleSender().sendMessage("Nop");
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
                saveBlackListedIps.add(ip.toString().replace("/", ""));
            }catch (UnknownHostException e) {
                Bukkit.getLogger().warning(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning + "Una Ip esta mal y seta omitida"));
                e.printStackTrace();
            }
        }
        blackListIpFile.getBlackListIpFile().set("ipblacklist", saveBlackListedIps);
        blackListIpFile.saveConfig();
    }
}
