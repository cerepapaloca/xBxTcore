package Plugin.Managers;

import Plugin.Inventory.InventoryMenu;
import Plugin.Model.*;
import Plugin.Utils.Tools;
import Plugin.xBxTcore;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import org.bukkit.*;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.util.*;

import static Plugin.xBxTcore.*;

public class InventoryManager {

    protected final ArrayList<InvetoryPlayer> players;
    protected final xBxTcore plugin;
    /////////////////////////
    protected ItemStack ButtoPreviewOff;
    protected ItemStack ButtoPreviewOn;
    private final InventoryMenu inventoryMenu;
    protected final ArrayList<String> nameMapsDuel = new ArrayList<>();

    public InventoryManager(xBxTcore plugin) {
        this.players = new ArrayList<>();
        this.plugin = plugin;
        inventoryMenu = xBxTcore.getInventoryMenu();
        staritems();
        for (MapsDuel map : MapsDuel.values()){
            nameMapsDuel.add(map.name());
        }
    }

    public InventoryMenu invetorymenu() {
        return this.inventoryMenu;
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
            Meta.setDisplayName(xBxTcore.getMessageManager().MasterMessage(invetoryPlayer.getPlayer(),Messages.PreviewOn));
            ButtoPreviewOn.setItemMeta(Meta);
            invetoryPlayer.getPlayer().getOpenInventory().setItem(slot,ButtoPreviewOn);
        } else if (item.getType() == Material.ENDER_EYE) {
            invetoryPlayer.setPreviewMode(false);
            ItemMeta Meta = ButtoPreviewOff.getItemMeta();
            assert Meta != null;
            Meta.setDisplayName(xBxTcore.getMessageManager().MasterMessage(invetoryPlayer.getPlayer(),Messages.PreviewOff));
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
        lore.set(xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getIndexMap(),xBxTcore.getMessageManager().MasterMessage(invetoryPlayer.getPlayer(), Messages.DuelLoreSelectWorld) + lore.get(xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getIndexMap()));
        Tools.NewitemInvetory(Messages.DuelSelectWorld, Material.FILLED_MAP, 16, invetoryPlayer.getPlayer().getOpenInventory().getTopInventory(), invetoryPlayer.getPlayer(), lore);
    }

    public static ArrayList<String> secondsToMinutesLore(Player player){
        int time = xBxTcore.getPlayerDataUnique(player.getUniqueId()).getTimeDuel();
        ArrayList<String> lore = new ArrayList<>();
        lore.add(xBxTcore.getMessageManager().MasterMessage(player, Messages.DuelTimeLore) + Tools.SecondToMinutes(time));
        return lore;
    }

    private void UpdateEnderPearl(Player player, ArrayList<String> lore){
        Inventory inv = player.getOpenInventory().getTopInventory();
        if (xBxTcore.getPlayerDataUnique(player.getUniqueId()).getTimelimit()){
            Tools.NewitemInvetory(Messages.DuelTimeLimitOn, Material.ENDER_EYE, 13, inv, player, lore);
        }else{
            Tools.NewitemInvetory(Messages.DuelTimeLimitOff, Material.ENDER_PEARL, 13, inv, player, lore);
        }
    }

    public void onClickInv(InvetoryPlayer invetoryPlayer, int slot, ItemStack item, ClickType click) {
        int back;
        int next;
        int mid;
        int max;

        if (invetoryPlayer.getPlayer().getName().contains(bedrockPrefix)){
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
            case MENU:
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
                }break;
            case MENUKITS:
                if (slot >= 0 && slot <= max && item != null) {

                    if (invetoryPlayer.getPreviewMode()) {
                        inventoryMenu.OpenPreviewKit(invetoryPlayer.getPlayer(), Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "kitName"), PersistentDataType.STRING), invetoryPlayer);
                        return;
                    }

                    if (click == ClickType.RIGHT) {
                        inventoryMenu.OpenPreviewKit(invetoryPlayer.getPlayer(), Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "kitName"), PersistentDataType.STRING), invetoryPlayer);
                        return;
                    } else if (invetoryPlayer.getKitSelectMode()) {
                        xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getKitData().setUuid(invetoryPlayer.getuuidkit());
                        xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getKitData().setName(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "kitName"), PersistentDataType.STRING));
                        inventoryMenu.OpenDuel(invetoryPlayer);
                        invetoryPlayer.setPreviewMode(false);
                        return;
                    } else {
                        invetoryPlayer.getPlayer().closeInventory();
                        xBxTcore.getPlayerFileManager().loadKit(invetoryPlayer.getuuidkit(), Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "kitName"), PersistentDataType.STRING), null,invetoryPlayer.getPlayer());
                        return;
                    }
                } else if (slot == back) {
                    if(invetoryPlayer.getPage() == 0){
                        if(invetoryPlayer.getKitSelectMode()){
                            xBxTcore.getInvetoryManager().invetorymenu().OpenDuel(invetoryPlayer);
                            return;
                        }else{
                            xBxTcore.getInvetoryManager().invetorymenu().OpenMenuInvetory(invetoryPlayer);
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
                        inventoryMenu.OpenDuel(invetoryPlayer);
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
                    return;
                }
                break;
            case PREVIEWKITS:
                switch (slot){
                    case 53:
                        invetorymenu().OpenInvetoryKitsList(invetoryPlayer, invetoryPlayer.getPage());
                        break;
                    case 45:
                        invetoryPlayer.getPlayer().closeInventory();
                        xBxTcore.getPlayerFileManager().loadKit(invetoryPlayer.getuuidkit(), Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "kitName"), PersistentDataType.STRING),null, invetoryPlayer.getPlayer());
                        break;
                }break;
            case MENUDUEL:
                switch (slot) {
                    case 10:
                        invetoryPlayer.getPlayer().sendTitle(xBxTcore.getMessageManager().MasterMessage(invetoryPlayer.getPlayer(), Messages.IvnPlayers1), xBxTcore.getMessageManager().MasterMessage(invetoryPlayer.getPlayer(), Messages.IvnPlayers2), 10, 70, 20);
                        invetoryPlayer.getPlayer().closeInventory();
                        break;
                    case 12:
                        invetoryPlayer.setKitSelectMode(true);
                        invetoryPlayer.setuuidkit(UUID.fromString("00000000-0000-0000-0000-000000000000"));
                        invetorymenu().OpenInvetoryKitsList(invetoryPlayer, 0);
                        break;
                    case 14:
                        invetorymenu().OpenTimeSelect(invetoryPlayer);
                        break;
                    case 16:
                        SelectMapDuel(invetoryPlayer, true);
                        break;
                    case 22:
                        xBxTcore.getCommandDuel().sendRequest(xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getGuestPlayers(false), xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getNameWolrd(), invetoryPlayer.getPlayer().getUniqueId());
                        invetoryPlayer.getPlayer().closeInventory();
                        break;
                }break;
            case TIMESELECT:
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
                            Tools.NewitemInvetory(Messages.DuelTimeLimitOff, Material.ENDER_PEARL, 13, invetoryPlayer.getPlayer().getOpenInventory().getTopInventory(), invetoryPlayer.getPlayer(), secondsToMinutesLore(invetoryPlayer.getPlayer()));
                        }else{
                            xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).setTimelimit(true);
                            Tools.NewitemInvetory(Messages.DuelTimeLimitOn, Material.ENDER_EYE, 13, invetoryPlayer.getPlayer().getOpenInventory().getTopInventory(), invetoryPlayer.getPlayer(), secondsToMinutesLore(invetoryPlayer.getPlayer()));
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
                    case 22 -> inventoryMenu.OpenDuel(invetoryPlayer);
                }
            case REWARDTIMES:
                switch (slot) {
                    case 11 -> {
                        xBxTcore.getPlayerFileManager().loadTimesRewords(invetoryPlayer.getPlayer().getUniqueId());
                        if (getPlayerFileManager().daily <= System.currentTimeMillis()){
                            Tools.additem(invetoryPlayer.getPlayer(), new ItemStack(Material.CHEST));
                            xBxTcore.getPlayerFileManager().SaveTimesRewords(invetoryPlayer.getPlayer().getUniqueId() ,System.currentTimeMillis() + 1000*10, getPlayerFileManager().weekly, getPlayerFileManager().monthly);
                        }else{
                            invetoryPlayer.getPlayer().sendMessage("No puede tomar la recompensa aún");
                        }
                    }
                }
        }
    }
}
