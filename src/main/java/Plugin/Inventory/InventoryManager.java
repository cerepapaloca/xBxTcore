package Plugin.Inventory;

import Plugin.Duel.Enum.MapsDuel;
import Plugin.Messages.Enum.Messages;
import Plugin.Inventory.Models.InvetoryPlayer;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

import static Plugin.Messages.MessageManager.MasterMessageLocated;

public class InventoryManager {

    protected final ArrayList<InvetoryPlayer> players;
    protected final xBxTcore plugin;
    /////////////////////////
    protected ItemStack ButtoPreviewOff;
    protected ItemStack ButtoPreviewOn;
    protected final ArrayList<String> nameMapsDuel = new ArrayList<>();

    public InventoryManager(xBxTcore plugin) {
        this.players = new ArrayList<>();
        InventorySection.inventoryManager = this;
        this.plugin = plugin;
        staritems();
        for (MapsDuel map : MapsDuel.values()){
            nameMapsDuel.add(map.name());
        }
    }

    public InventoryMenu invetorymenu() {
        return InventorySection.getInventoryMenu();
    }
    public InventoryClick inventoryClick() {
        return InventorySection.getInventoryClick();
    }


    public InvetoryPlayer getInvetoryPlayer(Player player){
        for(InvetoryPlayer invetoryPlayer : this.players){
            if(invetoryPlayer.getPlayer().equals(player)){
                return invetoryPlayer;
            }
        }
        return null;
    }

    public void addplayer(InvetoryPlayer invetoryPlayer){
        this.players.add(invetoryPlayer);
    }


    public void staritems(){
        ButtoPreviewOff = new ItemStack(Material.ENDER_PEARL);
        ButtoPreviewOn = new ItemStack(Material.ENDER_EYE);
    }

    public void removerPlayer(Player player){
        if(!players.isEmpty()){
            players.removeIf(invetoryPlayer -> invetoryPlayer.getPlayer().equals(player));
        }
    }

    public void togglePreview(ItemStack item, InvetoryPlayer invetoryPlayer){
        int slot;
        if(invetoryPlayer.getKitSelectMode()){
            slot = 51;
        }else{
            slot = 50;
        }
        if(item.getType() == Material.ENDER_PEARL){
            invetoryPlayer.setPreviewMode(true);
            ItemMeta Meta = ButtoPreviewOn.getItemMeta();
            assert Meta != null;
            Meta.setDisplayName(MasterMessageLocated(invetoryPlayer.getPlayer(), Messages.PreviewOn));
            ButtoPreviewOn.setItemMeta(Meta);
            invetoryPlayer.getPlayer().getOpenInventory().setItem(slot,ButtoPreviewOn);
        } else if (item.getType() == Material.ENDER_EYE) {
            invetoryPlayer.setPreviewMode(false);
            ItemMeta Meta = ButtoPreviewOff.getItemMeta();
            assert Meta != null;
            Meta.setDisplayName(MasterMessageLocated(invetoryPlayer.getPlayer(),Messages.PreviewOff));
            ButtoPreviewOff.setItemMeta(Meta);
            invetoryPlayer.getPlayer().getOpenInventory().setItem(slot,ButtoPreviewOff);
        }
    }

    protected void SelectMapDuel(InvetoryPlayer invetoryPlayer, Boolean addindex){
        if(addindex){
            xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).setIndexMap(xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getIndexMap() + 1);
            if (xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getIndexMap() > nameMapsDuel.size() - 1){
                xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).setIndexMap(0);
            }
        }
        ArrayList<String> lore = new ArrayList<>();
        for(String s : nameMapsDuel){
            lore.add(ChatColor.translateAlternateColorCodes('&',"&7" + s));
        }
        lore.set(xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getIndexMap(),MasterMessageLocated(invetoryPlayer.getPlayer(), Messages.DuelLoreSelectWorld) + lore.get(xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getIndexMap()));
        Utils.NewitemInvetory(Messages.DuelSelectWorld, Material.FILLED_MAP, 16, invetoryPlayer.getPlayer().getOpenInventory().getTopInventory(), invetoryPlayer.getPlayer(), lore);
    }

    public static ArrayList<String> secondsToMinutesLore(Player player){
        int time = xBxTcore.getPlayerDataUnique(player.getUniqueId()).getTimeDuel();
        ArrayList<String> lore = new ArrayList<>();
        lore.add(MasterMessageLocated(player, Messages.DuelTimeLore) + Utils.SecondToMinutes(time));
        return lore;
    }

    protected void UpdateEnderPearl(Player player, ArrayList<String> lore){
        Inventory inv = player.getOpenInventory().getTopInventory();
        if (xBxTcore.getPlayerDataUnique(player.getUniqueId()).getTimelimit()){
            Utils.NewitemInvetory(Messages.DuelTimeLimitOn, Material.ENDER_EYE, 13, inv, player, lore);
        }else{
            Utils.NewitemInvetory(Messages.DuelTimeLimitOff, Material.ENDER_PEARL, 13, inv, player, lore);
        }
    }

    protected void rewardDaly(Player player){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates:crate key give %player% key0 5".replace("%player%", player.getName()));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates:crate key give %player% key1 2".replace("%player%", player.getName()));
        if(player.hasPermission("Commandxbxtcore.vip")){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates:crate key give %player% key0 10".replace("%player%", player.getName()));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates:crate key give %player% key1 10".replace("%player%", player.getName()));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates:crate key give %player% key3 3".replace("%player%", player.getName()));
        }
    }

    protected void rewardWeekly(Player player){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates:crate key give %player% key0 25".replace("%player%", player.getName()));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates:crate key give %player% key1 10".replace("%player%", player.getName()));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates:crate key give %player% key2 5".replace("%player%", player.getName()));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates:crate key give %player% key2 2".replace("%player%", player.getName()));
        if(player.hasPermission("Commandxbxtcore.vip")){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates:crate key give %player% key0 30".replace("%player%", player.getName()));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates:crate key give %player% key1 25".replace("%player%", player.getName()));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates:crate key give %player% key2 20".replace("%player%", player.getName()));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates:crate key give %player% key3 10".replace("%player%", player.getName()));
        }
    }

    protected void rewardMonthly(Player player){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates:crate key give %player% key0 150".replace("%player%", player.getName()));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates:crate key give %player% key1 100".replace("%player%", player.getName()));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates:crate key give %player% key2 80".replace("%player%", player.getName()));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates:crate key give %player% key2 50".replace("%player%", player.getName()));
        if(player.hasPermission("Commandxbxtcore.vip")){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates:crate key give %player% key0 140".replace("%player%", player.getName()));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates:crate key give %player% key1 120".replace("%player%", player.getName()));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates:crate key give %player% key2 100".replace("%player%", player.getName()));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates:crate key give %player% key3 70".replace("%player%", player.getName()));
        }
    }


}
