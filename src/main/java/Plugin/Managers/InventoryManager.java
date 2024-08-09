package Plugin.Managers;

import Plugin.Inventory.InventoryMenu;
import Plugin.Model.InvetoryPlayer;
import Plugin.Model.InvetorySection;
import Plugin.Model.KitData;
import Plugin.Model.Messages;
import Plugin.xBxTcore;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

import static Plugin.xBxTcore.*;

public class InventoryManager {

    protected final ArrayList<InvetoryPlayer> players;
    protected final xBxTcore plugin;
    /////////////////////////
    protected ItemStack ButtoPreviewOff;
    protected ItemStack ButtoPreviewOn;
    private final InventoryMenu inventoryMenu;

    public InventoryManager(xBxTcore plugin) {
        this.players = new ArrayList<>();
        this.plugin = plugin;
        inventoryMenu = xBxTcore.getInventoryMenu();
        staritems();
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

    public void togglePreview(ItemStack item, InvetoryPlayer invetoryPlayer ){
        if(item.getType() == Material.ENDER_PEARL){
            invetoryPlayer.setPreviewMode(true);
            ItemMeta Meta = ButtoPreviewOn.getItemMeta();
            assert Meta != null;
            Meta.setDisplayName(xBxTcore.getMessageManager().MasterMessage(invetoryPlayer.getPlayer(),Messages.PreviewOn));
            ButtoPreviewOn.setItemMeta(Meta);
            invetoryPlayer.getPlayer().getOpenInventory().setItem(50,ButtoPreviewOn);
        } else if (item.getType() == Material.ENDER_EYE) {
            invetoryPlayer.setPreviewMode(false);
            ItemMeta Meta = ButtoPreviewOff.getItemMeta();
            assert Meta != null;
            Meta.setDisplayName(xBxTcore.getMessageManager().MasterMessage(invetoryPlayer.getPlayer(),Messages.PreviewOff));
            ButtoPreviewOff.setItemMeta(Meta);
            invetoryPlayer.getPlayer().getOpenInventory().setItem(50,ButtoPreviewOff);
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
                    if (invetoryPlayer.getKitSelectMode()){
                        xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getKitData().setUuid(invetoryPlayer.getuuidkit());
                        xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getKitData().setName(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "kitName"), PersistentDataType.STRING));
                        inventoryMenu.OpenDuel(invetoryPlayer);
                        invetoryPlayer.setKitSelectMode(false);
                        return;
                    }

                    if (invetoryPlayer.getPreviewMode()) {
                        inventoryMenu.OpenPreviewKit(invetoryPlayer.getPlayer(), Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "kitName"), PersistentDataType.STRING), invetoryPlayer);
                        return;
                    }

                    if (click == ClickType.RIGHT) {
                        inventoryMenu.OpenPreviewKit(invetoryPlayer.getPlayer(), Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "kitName"), PersistentDataType.STRING), invetoryPlayer);
                    } else {
                        invetoryPlayer.getPlayer().closeInventory();
                        xBxTcore.getPlayerFileManager().loadKit(invetoryPlayer.getuuidkit(), Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "kitName"), PersistentDataType.STRING), null,invetoryPlayer.getPlayer());
                    }
                } else if (slot == back) {
                    if(invetoryPlayer.getPage() == 0){
                        xBxTcore.getInvetoryManager().invetorymenu().OpenMenuInvetory(invetoryPlayer);
                    }else{
                        int i = invetoryPlayer.getPage();
                        i--;
                        invetoryPlayer.setPage(i);
                        invetorymenu().OpenInvetoryKitsList(invetoryPlayer, invetoryPlayer.getPage());
                    }
                } else if (slot == next && Material.ARROW == item.getType()) {
                    int i = invetoryPlayer.getPage();
                    i++;
                    invetoryPlayer.setPage(i);
                    invetorymenu().OpenInvetoryKitsList(invetoryPlayer, invetoryPlayer.getPage());
                } else if (slot == mid) {
                    invetoryPlayer.getPlayer().getInventory().clear();
                } else if (slot == 50){
                    togglePreview(item, invetoryPlayer);
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
                        invetoryPlayer.getPlayer().closeInventory();
                        break;
                    case 11:
                        invetoryPlayer.setKitSelectMode(true);
                        invetoryPlayer.setuuidkit(UUID.fromString("00000000-0000-0000-0000-000000000000"));
                        invetorymenu().OpenInvetoryKitsList(invetoryPlayer, 0);
                        break;
                    case 26:
                        xBxTcore.getCommandDuel().sendRequest(xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getGuestPlayers(false), "flat_world", invetoryPlayer.getPlayer().getUniqueId());
                        invetoryPlayer.getPlayer().closeInventory();
                        break;
                }break;
        }
    }
}
