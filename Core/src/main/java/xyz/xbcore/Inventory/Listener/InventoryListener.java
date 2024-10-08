package xyz.xbcore.Inventory.Listener;

import xyz.xbcore.Inventory.InventorySection;
import xyz.xbcore.Inventory.Models.InvetoryPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.Objects;


public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player)event.getWhoClicked();
        InvetoryPlayer invetoryPlayer = InventorySection.getInventoryManager().getInvetoryPlayer(player);
        if (invetoryPlayer != null) {
            event.setCancelled(true);
            if(event.getCurrentItem() != null && Objects.equals(event.getClickedInventory(), player.getOpenInventory().getTopInventory())){
                InventorySection.getInventoryManager().inventoryClick().onClickInv(invetoryPlayer, event.getSlot(), event.getCurrentItem(), event.getClick());
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
       Player player = (Player)event.getPlayer();
        InventorySection.getInventoryManager().removerPlayer(player);
    }
}
