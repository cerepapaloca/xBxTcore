package Plugin.File.Clan;

import Plugin.File.FileManagerSection;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.*;

import static Plugin.Messages.MessageManager.ColorWarning;
import static Plugin.Messages.MessageManager.prefixConsole;


public class ClanFileManager {

    private final xBxTcore plugin;
    public static ClansFiles clansFile;

    public ClanFileManager(xBxTcore plugin) {
        this.plugin = plugin;
        clansFile = new ClansFiles(plugin, "PlayersData");
        clansFile.reloadConfigs();
    }

    public void CreateClan(String nameClan, UUID uuid) {
        if(getfile(nameClan) != null){
            clansFile.registerConfigFile(nameClan + ".yml");
            getfile(nameClan).getClanDataFile().set("members.leader", uuid);
        }
    }

    public void addMember(String nameClan, UUID uuid) {
        ArrayList<UUID> UUIDs = getMembers(nameClan);
        UUIDs.add(uuid);

        getfile(nameClan).getClanDataFile().set("members.members", UUIDs);
        getfile(nameClan).saveConfig();
        refreshClan(nameClan, UUIDs);
    }

    public void delMember(String nameClan, UUID uuid) {
        ArrayList<UUID> UUIDs = getMembers(nameClan);
        if (!UUIDs.contains(uuid)){
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning + "El jugador no es existe en el clan"));
        }

        UUIDs.remove(uuid);
        getfile(nameClan).getClanDataFile().set("members.members", UUIDs);
        getfile(nameClan).saveConfig();
        refreshClan(nameClan, UUIDs);
    }

    public void changeLeader(String nameClan, UUID uuidNew, UUID uuidOld) {
        ArrayList<UUID> UUIDs = getMembers(nameClan);
        getfile(nameClan).getClanDataFile().set("members.leader", uuidNew);
        UUIDs.remove(uuidNew);
        UUIDs.add(uuidOld);
        getfile(nameClan).getClanDataFile().set("members.members", UUIDs);
        getfile(nameClan).saveConfig();
    }

    public String loadPrefixClan(String nameClan) {
        if(getfile(nameClan) != null)return "";
        return getfile(nameClan).getClanDataFile().getString("prefix", "");
    }

    public void savePrefixClan(String nameClan, String prefix) {
        if(getfile(nameClan) != null)return;
        getfile(nameClan).getClanDataFile().set("prefix", prefix);
    }

    public static void refreshClan(@NotNull String clanId,ArrayList<UUID> UUIDs) {
        for (UUID uuid : UUIDs) {
            FileManagerSection.getPlayerFileManager().SaveClanId(uuid, clanId);
        }
    }

    public static @NotNull ArrayList<UUID> getMembers(String nameClan) {
        ArrayList<UUID> UUIDs = new ArrayList<>();
        if (getfile(nameClan) != null) {
            List<?> rawList = getfile(nameClan).getClanDataFile().getList("members.members");
            if (rawList != null) {
                for (Object obj : rawList) {
                    if (obj instanceof UUID uuids) {
                        UUIDs.add(uuids);
                    }
                }
            }
        }
        return UUIDs;
    }

    public static ClanFile getfile(String nameClan){
        String pathName = nameClan + ".yml";
        return clansFile.getConfigFile(pathName);
    }

    public void reloadCustomConfig(String nameClan) {
        getfile(nameClan).reloadConfig();
    }

}
