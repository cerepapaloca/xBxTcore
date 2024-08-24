package Plugin.Managers;

import Plugin.File.PlayerFile;
import Plugin.File.PlayersFiles;
import Plugin.Enum.Messages;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

import static Plugin.Managers.MessageManager.*;
import static Plugin.xBxTcore.*;


public class PlayerfileManager {
    public List<String> nameskits;
    public List<String> nameskitsglobal;
    public List<String> nameskitsboth;
    public ArrayList<ItemStack> itemStacks;
    private final xBxTcore plugin;
    public List<Material> materials;
    public String namekitfavorite;
    public static PlayersFiles playesfiles;
    public HashMap<UUID, String> listPrefix;
    public ArrayList<String> namesPlayres;

    public long daily;
    public long weekly;
    public long monthly;


    public PlayerfileManager(xBxTcore plugin) {
        this.plugin = plugin;
        playesfiles = new PlayersFiles(plugin, "PlayersData");
        playesfiles.reloadConfigs();
        itemStacks = new ArrayList<>();
        nameskits = new ArrayList<>();
        materials = new ArrayList<>();
        nameskitsglobal = new ArrayList<>();
        nameskitsboth = new ArrayList<>();
        listPrefix = new HashMap<>();
        namesPlayres = new ArrayList<>();
        loadNamesPlayers();
    }

    public void loadKit(UUID uuid, String namekit, Inventory inv, Player player) {
        int i = 0;
        if(!Objects.requireNonNull(getfile(uuid).getPlayerDataFile().getConfigurationSection("Kits")).getKeys(false).contains(namekit)){
           player.sendMessage(xBxTcore.getMessageManager().MasterMessage(player,Messages.KitNotExist));
           return;
        }
        List<?> rawList = getfile(uuid).getPlayerDataFile().getList("Kits." + namekit + ".inventory", null);
        itemStacks.clear();
        if (rawList != null) {
            for (Object obj : rawList) {
                if (obj == null) {
                    itemStacks.add(new ItemStack(Material.AIR));
                }else if (obj instanceof ItemStack) {
                    itemStacks.add((ItemStack) obj);
                } else {
                    Objects.requireNonNull(player.getPlayer()).sendMessage(xBxTcore.getMessageManager().MasterMessage(player, Messages.LoadWaring).replace("%item%", String.valueOf(obj)));
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning + "El Kit&r " + namekit
                            + ColorWarning + " contiene un objeto no válido: " + obj));
                }
            }
        }else{
            Objects.requireNonNull(player.getPlayer()).sendMessage(xBxTcore.getMessageManager().MasterMessage(player, Messages.LoadError).replace("%namekit%", ChatColor.translateAlternateColorCodes('&', namekit)));
        }
        if (inv != null){
            for(ItemStack item : itemStacks) {
                if(i < 54){
                    if (item != null){
                        if (i == 36 || i == 37){
                            inv.setItem(i + 2 + 9,item);
                        }else if (i == 38 || i == 39){
                            inv.setItem(i + 3 + 9,item);
                        }else if (i == 40){
                            inv.setItem(i + 9,item);
                        }else if (i > 8){
                            inv.setItem(i - 9,item);
                        }else{
                            inv.setItem(i + 36,item);
                        }
                    }
                    ++i;
                }
            }
            ///////////////////////////////////////////////////////
            ItemStack Hotbar = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
            ItemMeta HotbarItemMeta = Hotbar.getItemMeta();
            assert HotbarItemMeta != null;
            HotbarItemMeta.setDisplayName(" ");
            Hotbar.setItemMeta(HotbarItemMeta);
            for(i = 27; i < 36; i++){
                inv.setItem(i,Hotbar);
            }
            ItemStack Exit = new ItemStack(Material.BARRIER);
            ItemMeta exitItemMeta = Exit.getItemMeta();
            assert exitItemMeta != null;
            exitItemMeta.setDisplayName(xBxTcore.getMessageManager().MasterMessage(player, Messages.InvExit));
            Exit.setItemMeta(exitItemMeta);
            inv.setItem(53,Exit);
            ///////////////////////////////////////////////////////
            List<String> lore = new ArrayList<>();
            ItemStack selec = new ItemStack(Material.BLAZE_POWDER);
            ItemMeta selecitemmeta = Exit.getItemMeta();
            selecitemmeta.setDisplayName(xBxTcore.getMessageManager().MasterMessage(player, Messages.InvLoad));
            lore.add(ChatColor.translateAlternateColorCodes('&', namekit));
            selecitemmeta.setLore(lore);
            selecitemmeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "kitName"), PersistentDataType.STRING, namekit);
            selec.setItemMeta(selecitemmeta);
            inv.setItem(45,selec);
            lore.clear();
            ///////////////////////////////////////////////////////
        }else{
            for(ItemStack item : itemStacks) {
                if (item != null){
                    player.getInventory().setItem(i,item);
                }
                ++i;
            }
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorSuccess + "El Kit&r " + namekit
                    + "&r" + ColorSuccess + " Fue Cargado Corretamente Del jugador " + Colorplayer + player.getName()));
            Objects.requireNonNull(player.getPlayer()).sendMessage(xBxTcore.getMessageManager().MasterMessage(player, Messages.Load).replace("%namekit%", ChatColor.translateAlternateColorCodes('&', namekit)));
        }
        namekitfavorite = null;
    }

    public void loadNameKit(UUID uuid) {
        Set<String> names = Objects.requireNonNull(getfile(uuid).getPlayerDataFile().getConfigurationSection("Kits")).getKeys(false);
        materials.clear();
        nameskits = List.of(names.toArray(new String[0]));

        for (String namekit : nameskits){
            Material material = Material.getMaterial(Objects.requireNonNull(getfile(uuid).getPlayerDataFile().getString("Kits." + namekit + ".metadata.icon")));
            materials.add(material);
        }
        Set<String> namesall = Objects.requireNonNull(getfile(UUID.fromString("00000000-0000-0000-0000-000000000000")).getPlayerDataFile().getConfigurationSection("Kits")).getKeys(false);
        nameskitsglobal = List.of(namesall.toArray(new String[0]));
        concatenate(nameskitsglobal,nameskits);
    }

    public String loadNameKitfavorite(UUID uuid) {
        namekitfavorite = getfile(uuid).getPlayerDataFile().getString("metadataplayer.kitfavorite", null);
        return namekitfavorite;
    }

    public void loadkitfavorite(Player player) {
        String target = loadNameKitfavorite(player.getUniqueId());
        loadNameKit(player.getUniqueId());
        if (target != null) {
            if (nameskits.contains(target)){
                xBxTcore.getPlayerFileManager().loadKit(player.getUniqueId(), target, null, player);
                return;
            }
            if (nameskitsglobal.contains(target)){
                xBxTcore.getPlayerFileManager().loadKit(UUID.fromString("00000000-0000-0000-0000-000000000000"), target, null, player);
                return;
            }
            player.sendMessage(xBxTcore.getMessageManager().MasterMessage(player,Messages.KitNotExist));
        }else{
            player.sendMessage(xBxTcore.getMessageManager().MasterMessage(player,Messages.KitNotExist));
        }
    }

    public String loadPrefix(UUID uuid) {

        if (listPrefix.containsKey(uuid)){
            return listPrefix.get(uuid);
        }else{
            String prefix = getfile(uuid).getPlayerDataFile().getString("metadataplayer.Prefix", "");
            listPrefix.put(uuid,prefix);
            return prefix;
        }
    }

    public void loadNamesPlayers() {
        namesPlayres.clear();
        for(PlayerFile playerFile : playesfiles.getConfigFiles()){
            String name = playerFile.getPlayerDataFile().getString("metadataplayer.Name");
            namesPlayres.add(name);
        }
    }

    public void loadTimesRewords(UUID uuid) {
        daily = getfile(uuid).getPlayerDataFile().getLong("metadataplayer.rewardsTimes.daily", 1);
        weekly = getfile(uuid).getPlayerDataFile().getLong("metadataplayer.rewardsTimes.weekly", 1);
        monthly = getfile(uuid).getPlayerDataFile().getLong("metadataplayer.rewardsTimes.monthly", 1);
    }

    public void loadInventoryBoxPvp(Player player){
        UUID uuid = player.getUniqueId();
        List<?> rawList = getfile(uuid).getPlayerDataFile().getList("InventoryBoxPvp", null);
        itemStacks.clear();
        if (rawList != null) {
            for (Object obj : rawList) {
                if (obj == null) {
                    itemStacks.add(new ItemStack(Material.AIR));
                }else if (obj instanceof ItemStack) {
                    itemStacks.add((ItemStack) obj);
                }else {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning + "Contiene un objeto no válido: " + obj));
                }
            }
        }else{
            Objects.requireNonNull(player.getPlayer()).sendMessage(xBxTcore.getMessageManager().MasterMessage(player, Messages.LoadError));
        }
        int i = 0;
        for(ItemStack item : itemStacks) {
            if (item != null){
                player.getInventory().setItem(i,item);
            }
            ++i;
        }
    }

    public void SaveTimesRewords(UUID uuid, long daily, long weekly, long monthly) {
        getfile(uuid).getPlayerDataFile().set("metadataplayer.rewardsTimes.daily", null);
        getfile(uuid).getPlayerDataFile().set("metadataplayer.rewardsTimes.weekly", null);
        getfile(uuid).getPlayerDataFile().set("metadataplayer.rewardsTimes.monthly", null);
        getfile(uuid).getPlayerDataFile().set("metadataplayer.rewardsTimes.daily", daily);
        getfile(uuid).getPlayerDataFile().set("metadataplayer.rewardsTimes.weekly", weekly);
        getfile(uuid).getPlayerDataFile().set("metadataplayer.rewardsTimes.monthly", monthly);
        getfile(uuid).saveConfig();
    }

    public void SaveKit(UUID uuid, String namekit, Material material, ArrayList<ItemStack> itemstacks){
        getfile(uuid).getPlayerDataFile().set("Kits." + namekit, null);
        getfile(uuid).getPlayerDataFile().set("Kits." + namekit + ".inventory", itemstacks);
        getfile(uuid).getPlayerDataFile().set("Kits." + namekit + ".metadata.icon", material.name());
        getfile(uuid).saveConfig();
        getPlayerFileManager().reloadCustomConfig(uuid);
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorSuccess
                + "Fue guardado correctamente el kit&r " + namekit + ColorSuccess + " hacia el jugador " + Colorplayer + Bukkit.getOfflinePlayer(uuid).getName()));
        if(uuid.equals(UUID.fromString("00000000-0000-0000-0000-000000000000"))){
            return;
        }
        Objects.requireNonNull(Bukkit.getPlayer(uuid)).sendMessage(xBxTcore.getMessageManager().MasterMessage(Objects.requireNonNull(Bukkit.getPlayer(uuid))
                ,Messages.Save).replace("%namekit%", ChatColor.translateAlternateColorCodes('&', namekit)));
        itemstacks.clear();
    }

    public void SaveInventoryBoxPvp(UUID uuid, ArrayList<ItemStack> itemstacks){
        getfile(uuid).getPlayerDataFile().set("InventoryBoxPvp", null);
        getfile(uuid).getPlayerDataFile().set("InventoryBoxPvp", itemstacks);
        getfile(uuid).saveConfig();
        getPlayerFileManager().reloadCustomConfig(uuid);
        //Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorSuccess
                //+ "Fue guardado correctamente el inventario&r " + namekit + ColorSuccess + " hacia el jugador " + Colorplayer + Bukkit.getOfflinePlayer(uuid).getName()));
        itemstacks.clear();
    }

    public void SaveNameKitFavorite(UUID uuid, String namekitfavorite){
        getfile(uuid).getPlayerDataFile().set("metadataplayer.kitfavorite", namekitfavorite);
        getfile(uuid).saveConfig();
        getPlayerFileManager().reloadCustomConfig(uuid);
        loadNameKitfavorite(uuid);
        if(nameskitsboth.contains(namekitfavorite)){
            Objects.requireNonNull(Bukkit.getPlayer(uuid)).sendMessage(getMessageManager().MasterMessage(Objects.requireNonNull(Bukkit.getPlayer(uuid)),Messages.Favorite));
        }else{
            Objects.requireNonNull(Bukkit.getPlayer(uuid)).sendMessage(getMessageManager().MasterMessage(Objects.requireNonNull(Bukkit.getPlayer(uuid)),Messages.FavoriteWaring));
        }
    }

    public void SaveUUIDplayer(UUID uuid){
        if(getfile(uuid) == null){
            playesfiles.registerConfigFile(uuid.toString() + ".yml");
            getfile(uuid).getPlayerDataFile().set("metadataplayer.Name", Objects.requireNonNull(Bukkit.getPlayer(uuid)).getName());
            getfile(uuid).getPlayerDataFile().set("Kits.test", "null");
            getfile(uuid).getPlayerDataFile().set("Kits.test", null);
            SaveTimesRewords(uuid, 1,1,1);
            SaveInventoryBoxPvp(uuid,null);
            getfile(uuid).saveConfig();
            getPlayerFileManager().reloadCustomConfig(uuid);
            loadNamesPlayers();
            Objects.requireNonNull(Bukkit.getPlayer(uuid)).sendMessage(xBxTcore.getMessageManager().MasterMessage(Objects.requireNonNull(Bukkit.getPlayer(uuid)),Messages.NewPlayer));
        }
    }

    public void SavePrefix(UUID uuid, String prefix){
        getfile(uuid).getPlayerDataFile().set("metadataplayer.Prefix", null);
        getfile(uuid).getPlayerDataFile().set("metadataplayer.Prefix", prefix);
        getfile(uuid).saveConfig();
        listPrefix.remove(uuid);
        getPlayerFileManager().reloadCustomConfig(uuid);
    }

    public void DeleteKitConfig(UUID uuid, String namekit){
        if (null == getfile(uuid).getPlayerDataFile().get("Kits." + namekit, null)){
            Objects.requireNonNull(Bukkit.getPlayer(uuid)).sendMessage(xBxTcore.getMessageManager().MasterMessage(Objects.requireNonNull(Bukkit.getPlayer(uuid)),
                    Messages.RemovedWaring));
        }
        getfile(uuid).getPlayerDataFile().set("Kits." + namekit, null);
        getfile(uuid).saveConfig();
        itemStacks.clear();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorSuccess
                + "Se eliminio correctamente el kit&r " + namekit + ColorSuccess + " Del jugador " + Colorplayer + Bukkit.getOfflinePlayer(uuid).getName()));
        if(uuid.equals(UUID.fromString("00000000-0000-0000-0000-000000000000"))){
            return;
        }
        Objects.requireNonNull(Bukkit.getPlayer(uuid)).sendMessage(xBxTcore.getMessageManager().MasterMessage(Objects.requireNonNull(Bukkit.getPlayer(uuid)),
                Messages.Removed).replace("%namekit%", ChatColor.translateAlternateColorCodes('&', namekit)));
    }

    public PlayerFile getfile(UUID uuid){
        String pathName = uuid + ".yml";
        return playesfiles.getConfigFile(pathName);
    }

    public void concatenate(List<String> list1, List<String> list2) {
        List<String> result = new ArrayList<>(list1);
        result.addAll(list2);
        nameskitsboth = result;
    }

    public void reloadCustomConfig(UUID uuid) {
        getfile(uuid).reloadConfig();
    }

}
