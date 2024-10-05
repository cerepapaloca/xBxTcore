package xyz.xbcore.Inventory;

import xyz.xbcore.Commands.CommandSection;
import xyz.xbcore.File.FileManagerSection;
import xyz.xbcore.Messages.Messages.Messages;
import xyz.xbcore.Inventory.Enum.PlayerFileTimes;
import xyz.xbcore.Inventory.Models.InvetoryPlayer;
import xyz.xbcore.Utils.Utils;
import xyz.xbcore.xBxTcore;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;
import java.util.UUID;

import static xyz.xbcore.File.FileManagerSection.*;
import static xyz.xbcore.Messages.MessageManager.MasterMessageLocated;

public class InventoryClick extends InventoryManager {

    public InventoryClick(xBxTcore plugin) {
        super(plugin);
    }

    public void onClickInv(InvetoryPlayer invetoryPlayer, int slot, ItemStack item, ClickType click) {
        int back;
        int next;
        int mid;
        int max;

        if (invetoryPlayer.getPlayer().getName().contains(xBxTcore.bedrockPrefix)){
            mid = 48;
            back = 45;
            next = 53;
            max = 44;
        }else{
            mid = 31;
            back = 27;
            next = 35;
            max = 26;
        }

        switch (invetoryPlayer.getSection()){
            case MENU_PREKIT -> {
                switch(item.getType()){
                    case ENDER_CHEST -> {
                        invetoryPlayer.setUUIDKit(UUID.fromString("00000000-0000-0000-0000-000000000000"));
                        invetoryPlayer.setPage(0);
                        invetorymenu().OpenInvetoryKitsList(invetoryPlayer, 0);
                    }

                    case MINECART -> {
                        MultiverseWorld mvWorld = xBxTcore.getMultiverseCore().getMVWorldManager().getMVWorld("creatorkits");
                        if (!Objects.equals(invetoryPlayer.getPlayer().getWorld().getName(), "creatorkits")) {
                            World world = mvWorld.getCBWorld();
                            invetoryPlayer.getPlayer().teleport(world.getSpawnLocation());
                            invetoryPlayer.getPlayer().setLevel(9999);
                        }else{
                            mvWorld = xBxTcore.getMultiverseCore().getMVWorldManager().getMVWorld("lobby");
                            World world = mvWorld.getCBWorld();
                            invetoryPlayer.getPlayer().teleport(world.getSpawnLocation());
                            invetoryPlayer.setPage(0);
                            invetoryPlayer.getPlayer().setLevel(0);
                        }
                    }
                    case CHEST -> {
                        invetoryPlayer.setUUIDKit(invetoryPlayer.getPlayer().getUniqueId());
                        invetorymenu().OpenInvetoryKitsList(invetoryPlayer, 0);
                    }
                    case BARRIER -> invetorymenu().OpenMenu(invetoryPlayer);
                }
            }
            case MENU_KITS -> {
                if (slot >= 0 && slot <= max && item != null) {

                    if (invetoryPlayer.getPreviewMode()) {
                        invetorymenu().OpenPreviewKit(invetoryPlayer.getPlayer(), Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "kitName"), PersistentDataType.STRING), invetoryPlayer);
                        return;
                    }

                    if (click == ClickType.RIGHT) {
                        invetorymenu().OpenPreviewKit(invetoryPlayer.getPlayer(), Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "kitName"), PersistentDataType.STRING), invetoryPlayer);
                        return;
                    } else if (invetoryPlayer.getKitSelectMode()) {
                        xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getKitData().setUuid(invetoryPlayer.getUUIDKit());
                        xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getKitData().setName(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "kitName"), PersistentDataType.STRING));
                        invetorymenu().OpenMenuDuel(invetoryPlayer);
                        invetoryPlayer.setPreviewMode(false);
                        return;
                    } else {
                        invetoryPlayer.getPlayer().closeInventory();
                        FileManagerSection.getPlayerFileManager().loadKit(invetoryPlayer.getUUIDKit(), Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "kitName"), PersistentDataType.STRING), null,invetoryPlayer.getPlayer());
                        return;
                    }
                } else if (slot == back) {
                    if(invetoryPlayer.getPage() == 0){
                        if(invetoryPlayer.getKitSelectMode()){
                            invetorymenu().OpenMenuDuel(invetoryPlayer);
                            return;
                        }else{
                            invetorymenu().OpenMenuKit(invetoryPlayer);
                            return;
                        }
                    }else{
                        int i = invetoryPlayer.getPage();
                        i--;
                        invetoryPlayer.setPage(i);
                        invetorymenu().OpenInvetoryKitsList(invetoryPlayer, invetoryPlayer.getPage());
                        return;
                    }
                } else if (slot == next && Material.ARROW == item.getType()) {
                    int i = invetoryPlayer.getPage();
                    i++;
                    invetoryPlayer.setPage(i);
                    invetorymenu().OpenInvetoryKitsList(invetoryPlayer, invetoryPlayer.getPage());
                    return;
                } else if (invetoryPlayer.getKitSelectMode()) {
                    if (slot == mid + 1){
                        xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).clearKitdata();
                        invetorymenu().OpenMenuDuel(invetoryPlayer);
                        return;
                    }else if (slot == mid - 1){
                        if (item.getType() == Material.CHEST){
                            invetoryPlayer.setUUIDKit(invetoryPlayer.getPlayer().getUniqueId());
                            invetorymenu().OpenInvetoryKitsList(invetoryPlayer, 0);
                            return;
                        }else if(item.getType() == Material.ENDER_CHEST){
                            invetoryPlayer.setUUIDKit(UUID.fromString("00000000-0000-0000-0000-000000000000"));
                            invetorymenu().OpenInvetoryKitsList(invetoryPlayer, 0);
                            return;
                        }
                    }
                } else if (slot == mid) {
                    invetoryPlayer.getPlayer().getInventory().clear();
                    return;
                }
                assert item != null;
                if (item.getType() == Material.ENDER_EYE || item.getType() == Material.ENDER_PEARL){
                    togglePreview(item, invetoryPlayer);
                }
            }
            case PREVIEW_KITS -> {
                switch (slot) {
                    case 53 -> invetorymenu().OpenInvetoryKitsList(invetoryPlayer, invetoryPlayer.getPage());
                    case 45 -> {
                        invetoryPlayer.getPlayer().closeInventory();
                        getPlayerFileManager().loadKit(invetoryPlayer.getUUIDKit(), Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "kitName"), PersistentDataType.STRING), null, invetoryPlayer.getPlayer());
                    }
                }
            }

            case MENU_DUEL -> {
                switch (slot) {
                    case 10 -> {
                        invetoryPlayer.getPlayer().sendTitle(MasterMessageLocated(invetoryPlayer.getPlayer(), Messages.Others_IvnPlayers1), MasterMessageLocated(invetoryPlayer.getPlayer(), Messages.Others_IvnPlayers2), 10, 70, 20);
                        invetoryPlayer.getPlayer().closeInventory();
                    }
                    case 12 -> {
                        invetoryPlayer.setKitSelectMode(true);
                        invetoryPlayer.setUUIDKit(UUID.fromString("00000000-0000-0000-0000-000000000000"));
                        invetorymenu().OpenInvetoryKitsList(invetoryPlayer, 0);
                    }
                    case 14 -> invetorymenu().OpenTimeSelect(invetoryPlayer);

                    case 16 -> SelectMapDuel(invetoryPlayer, true);

                    case 22 -> {
                        CommandSection.getCommandDuel().sendRequest(xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getGuestPlayers(false), xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getNameWolrd(), invetoryPlayer.getPlayer().getUniqueId());
                        invetoryPlayer.getPlayer().closeInventory();
                    }
                    case 26 -> invetorymenu().OpenMenu(invetoryPlayer);
                }
            }

            case TIME_SELECT -> {
                switch (slot) {
                    case 11 -> {
                        if (xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getTimeDuel() > 60){
                            xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).setTimeDuel(xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getTimeDuel() - 60);
                            UpdateEnderPearl(invetoryPlayer.getPlayer(), secondsToMinutesLore(invetoryPlayer.getPlayer()));
                        }
                    }
                    case 12 -> {
                        if (xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getTimeDuel() > 1){
                            xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).setTimeDuel(xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getTimeDuel() - 1);
                            UpdateEnderPearl(invetoryPlayer.getPlayer(), secondsToMinutesLore(invetoryPlayer.getPlayer()));
                        }
                    }
                    case 13 -> {
                        if (xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getTimeLimit()){
                            xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).setTimeLimit(false);
                            Utils.newItemInventory(Messages.Inventory_MenuDuel_TimeLimit_Off, Material.ENDER_PEARL, 13, invetoryPlayer.getPlayer().getOpenInventory().getTopInventory(), invetoryPlayer.getPlayer(), secondsToMinutesLore(invetoryPlayer.getPlayer()));
                        }else{
                            xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).setTimeLimit(true);
                            Utils.newItemInventory(Messages.Inventory_MenuDuel_TimeLimit_On, Material.ENDER_EYE, 13, invetoryPlayer.getPlayer().getOpenInventory().getTopInventory(), invetoryPlayer.getPlayer(), secondsToMinutesLore(invetoryPlayer.getPlayer()));
                        }
                    }
                    case 14 -> {
                        xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).setTimeDuel(xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getTimeDuel() + 1);
                        UpdateEnderPearl(invetoryPlayer.getPlayer(), secondsToMinutesLore(invetoryPlayer.getPlayer()));
                    }
                    case 15 -> {
                        xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).setTimeDuel(xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getTimeDuel() + 60);
                        UpdateEnderPearl(invetoryPlayer.getPlayer(), secondsToMinutesLore(invetoryPlayer.getPlayer()));
                    }
                    case 22 -> invetorymenu().OpenMenuDuel(invetoryPlayer);
                }
            }
            case REWARD_TIMES -> {
                switch (slot) {
                    case 20 -> {
                        getPlayerFileManager().loadTimesRewords(invetoryPlayer.getPlayer().getUniqueId());
                        if (getPlayerFileManager().daily <= System.currentTimeMillis()){
                            rewardDaly(invetoryPlayer.getPlayer());
                            getPlayerFileManager().SaveTimesRewords(invetoryPlayer.getPlayer().getUniqueId(), PlayerFileTimes.daily, System.currentTimeMillis() + 1000L*60*60*24);
                            Utils.newItemInventory(Messages.Reward_Daily, Material.MINECART, 20, invetoryPlayer.getPlayer().getOpenInventory().getTopInventory(), invetoryPlayer.getPlayer());
                            invetoryPlayer.getPlayer().sendMessage(MasterMessageLocated(invetoryPlayer.getPlayer(), Messages.Reward_GiveDaily));
                        }else{
                            invetoryPlayer.getPlayer().sendMessage(MasterMessageLocated(invetoryPlayer.getPlayer(), Messages.Reward_RewardNotYet));
                        }
                    }
                    case 22 -> {
                        getPlayerFileManager().loadTimesRewords(invetoryPlayer.getPlayer().getUniqueId());
                        if (getPlayerFileManager().weekly <= System.currentTimeMillis()){
                            rewardWeekly(invetoryPlayer.getPlayer());
                            getPlayerFileManager().SaveTimesRewords(invetoryPlayer.getPlayer().getUniqueId(), PlayerFileTimes.weekly, System.currentTimeMillis() + 1000L*60*60*24*7);
                            Utils.newItemInventory(Messages.Reward_Weekly, Material.MINECART, 22, invetoryPlayer.getPlayer().getOpenInventory().getTopInventory(), invetoryPlayer.getPlayer());
                            invetoryPlayer.getPlayer().sendMessage(MasterMessageLocated(invetoryPlayer.getPlayer(), Messages.Reward_GiveWeekly));
                        }else{
                            invetoryPlayer.getPlayer().sendMessage(MasterMessageLocated(invetoryPlayer.getPlayer(), Messages.Reward_RewardNotYet));
                        }
                    }
                    case 24 -> {
                        getPlayerFileManager().loadTimesRewords(invetoryPlayer.getPlayer().getUniqueId());
                        if (getPlayerFileManager().monthly <= System.currentTimeMillis()){
                            rewardMonthly(invetoryPlayer.getPlayer());
                            getPlayerFileManager().SaveTimesRewords(invetoryPlayer.getPlayer().getUniqueId(), PlayerFileTimes.monthly, System.currentTimeMillis() + 1000L*60*60*24*30);
                            Utils.newItemInventory(Messages.Reward_Monthly, Material.MINECART, 24, invetoryPlayer.getPlayer().getOpenInventory().getTopInventory(), invetoryPlayer.getPlayer());
                            invetoryPlayer.getPlayer().sendMessage(MasterMessageLocated(invetoryPlayer.getPlayer(), Messages.Reward_GiveMonthly));
                        }else{
                            invetoryPlayer.getPlayer().sendMessage(MasterMessageLocated(invetoryPlayer.getPlayer(), Messages.Reward_RewardNotYet));
                        }
                    }
                }
            }

            case HELP -> {
                switch (slot){
                    case 10 -> invetorymenu().OpenMenuCommands(invetoryPlayer);
                    case 12 -> invetorymenu().OpenHelpItem(invetoryPlayer);
                    case 14 -> invetorymenu().OpenHelpRules(invetoryPlayer);
                    case 16 -> invetorymenu().OpenHelpInfo(invetoryPlayer);
                    case 26 -> invetorymenu().OpenMenu(invetoryPlayer);
                }
            }

            case HELP_RULE, HELP_INFO, HELP_COMMANDS, HELP_ITEM -> {
                switch (slot){
                    case 26, 53 -> invetorymenu().OpenMenuHelp(invetoryPlayer);
                }
            }

            case MENU -> {
                switch (slot){
                    case 11 -> {
                        if(!invetoryPlayer.getPlayer().getWorld().getName().equals(xBxTcore.worldBoxPvp)) invetorymenu().OpenMenuKit(invetoryPlayer);
                    }
                    case 13 -> {
                        if(!invetoryPlayer.getPlayer().getWorld().getName().equals(xBxTcore.worldBoxPvp)) invetorymenu().OpenMenuDuel(invetoryPlayer);
                    }
                    case 15 -> invetorymenu().OpenMenuHelp(invetoryPlayer);
                }
            }
        }
    }
}
