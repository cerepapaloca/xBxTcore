package Plugin.Inventory;

import Plugin.Commands.CommandSection;
import Plugin.Messages.Enum.Messages;
import Plugin.Inventory.Enum.PlayerFileTimes;
import Plugin.Inventory.Models.InvetoryPlayer;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;
import java.util.UUID;

import static Plugin.File.FileManagerSection.getPlayerFileManager;
import static Plugin.Messages.MessageManager.MasterMessageLocated;

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
            case MENUPREKIT -> {
                switch(item.getType()){
                    case ENDER_CHEST:
                        invetoryPlayer.setuuidkit(UUID.fromString("00000000-0000-0000-0000-000000000000"));
                        invetoryPlayer.setPage(0);
                        invetorymenu().OpenInvetoryKitsList(invetoryPlayer, 0);
                        break;
                    case MINECART:
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
                        break;
                    case CHEST:
                        invetoryPlayer.setuuidkit(invetoryPlayer.getPlayer().getUniqueId());
                        invetorymenu().OpenInvetoryKitsList(invetoryPlayer, 0);
                        break;
                }
            }
            case MENUKITS -> {
                if (slot >= 0 && slot <= max && item != null) {

                    if (invetoryPlayer.getPreviewMode()) {
                        invetorymenu().OpenPreviewKit(invetoryPlayer.getPlayer(), Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "kitName"), PersistentDataType.STRING), invetoryPlayer);
                        return;
                    }

                    if (click == ClickType.RIGHT) {
                        invetorymenu().OpenPreviewKit(invetoryPlayer.getPlayer(), Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "kitName"), PersistentDataType.STRING), invetoryPlayer);
                        return;
                    } else if (invetoryPlayer.getKitSelectMode()) {
                        xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getKitData().setUuid(invetoryPlayer.getuuidkit());
                        xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getKitData().setName(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "kitName"), PersistentDataType.STRING));
                        invetorymenu().OpenDuel(invetoryPlayer);
                        invetoryPlayer.setPreviewMode(false);
                        return;
                    } else {
                        invetoryPlayer.getPlayer().closeInventory();
                        getPlayerFileManager().loadKit(invetoryPlayer.getuuidkit(), Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "kitName"), PersistentDataType.STRING), null,invetoryPlayer.getPlayer());
                        return;
                    }
                } else if (slot == back) {
                    if(invetoryPlayer.getPage() == 0){
                        if(invetoryPlayer.getKitSelectMode()){
                            invetorymenu().OpenDuel(invetoryPlayer);
                            return;
                        }else{
                            invetorymenu().OpenMenuInvetory(invetoryPlayer);
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
                        invetorymenu().OpenDuel(invetoryPlayer);
                        return;
                    }else if (slot == mid - 1){
                        if (item.getType() == Material.CHEST){
                            invetoryPlayer.setuuidkit(invetoryPlayer.getPlayer().getUniqueId());
                            invetorymenu().OpenInvetoryKitsList(invetoryPlayer, 0);
                            return;
                        }else if(item.getType() == Material.ENDER_CHEST){
                            invetoryPlayer.setuuidkit(UUID.fromString("00000000-0000-0000-0000-000000000000"));
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
            case PREVIEWKITS -> {
                switch (slot){
                    case 53:
                        invetorymenu().OpenInvetoryKitsList(invetoryPlayer, invetoryPlayer.getPage());
                        break;
                    case 45:
                        invetoryPlayer.getPlayer().closeInventory();
                        getPlayerFileManager().loadKit(invetoryPlayer.getuuidkit(), Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "kitName"), PersistentDataType.STRING),null, invetoryPlayer.getPlayer());
                        break;
                }
            }

            case MENUDUEL -> {
                switch (slot) {
                    case 10 -> {
                        invetoryPlayer.getPlayer().sendTitle(MasterMessageLocated(invetoryPlayer.getPlayer(), Messages.Others_IvnPlayers1), MasterMessageLocated(invetoryPlayer.getPlayer(), Messages.Others_IvnPlayers2), 10, 70, 20);
                        invetoryPlayer.getPlayer().closeInventory();
                    }
                    case 12 -> {
                        invetoryPlayer.setKitSelectMode(true);
                        invetoryPlayer.setuuidkit(UUID.fromString("00000000-0000-0000-0000-000000000000"));
                        invetorymenu().OpenInvetoryKitsList(invetoryPlayer, 0);
                    }
                    case 14 -> invetorymenu().OpenTimeSelect(invetoryPlayer);

                    case 16 -> SelectMapDuel(invetoryPlayer, true);

                    case 22 -> {
                        CommandSection.getCommandDuel().sendRequest(xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getGuestPlayers(false), xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getNameWolrd(), invetoryPlayer.getPlayer().getUniqueId());
                        invetoryPlayer.getPlayer().closeInventory();
                    }
                }
            }

            case TIMESELECT -> {
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
                        if (xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getTimelimit()){
                            xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).setTimelimit(false);
                            Utils.NewitemInvetory(Messages.Inventory_DuelTimeLimitOff, Material.ENDER_PEARL, 13, invetoryPlayer.getPlayer().getOpenInventory().getTopInventory(), invetoryPlayer.getPlayer(), secondsToMinutesLore(invetoryPlayer.getPlayer()));
                        }else{
                            xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).setTimelimit(true);
                            Utils.NewitemInvetory(Messages.Inventory_DuelTimeLimitOn, Material.ENDER_EYE, 13, invetoryPlayer.getPlayer().getOpenInventory().getTopInventory(), invetoryPlayer.getPlayer(), secondsToMinutesLore(invetoryPlayer.getPlayer()));
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
                    case 22 -> invetorymenu().OpenDuel(invetoryPlayer);
                }
            }
            case REWARDTIMES -> {
                switch (slot) {
                    case 20 -> {
                        getPlayerFileManager().loadTimesRewords(invetoryPlayer.getPlayer().getUniqueId());
                        if (getPlayerFileManager().daily <= System.currentTimeMillis()){
                            rewardDaly(invetoryPlayer.getPlayer());
                            getPlayerFileManager().SaveTimesRewords(invetoryPlayer.getPlayer().getUniqueId(), PlayerFileTimes.daily, System.currentTimeMillis() + 1000L*60*60*24);
                            Utils.NewitemInvetory(Messages.Reward_Daily, Material.MINECART, 20, invetoryPlayer.getPlayer().getOpenInventory().getTopInventory(), invetoryPlayer.getPlayer());
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
                            Utils.NewitemInvetory(Messages.Reward_Weekly, Material.MINECART, 22, invetoryPlayer.getPlayer().getOpenInventory().getTopInventory(), invetoryPlayer.getPlayer());
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
                            Utils.NewitemInvetory(Messages.Reward_Monthly, Material.MINECART, 24, invetoryPlayer.getPlayer().getOpenInventory().getTopInventory(), invetoryPlayer.getPlayer());
                            invetoryPlayer.getPlayer().sendMessage(MasterMessageLocated(invetoryPlayer.getPlayer(), Messages.Reward_GiveMonthly));
                        }else{
                            invetoryPlayer.getPlayer().sendMessage(MasterMessageLocated(invetoryPlayer.getPlayer(), Messages.Reward_RewardNotYet));
                        }
                    }
                }
            }

            case HELP -> {

            }
        }
    }

}
