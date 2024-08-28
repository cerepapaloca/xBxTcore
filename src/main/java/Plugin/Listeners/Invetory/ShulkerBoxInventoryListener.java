package Plugin.Listeners.Invetory;

import org.bukkit.Bukkit;
import org.bukkit.block.BlockState;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class ShulkerBoxInventoryListener implements Listener {
    @EventHandler
    public void onPlayerLeftClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_AIR) {
            return;
        }

        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        if (itemInHand == null || !itemInHand.getType().name().endsWith("SHULKER_BOX")) {
            return;
        }

        if (player.getOpenInventory() != null) {
            player.closeInventory();
        }

        Inventory shulkerInventory = getShulkerBoxInventory(itemInHand);
        if (shulkerInventory != null) {
            player.openInventory(shulkerInventory);
        }
    }

    private Inventory getShulkerBoxInventory(ItemStack shulkerItem) {
        if (!(shulkerItem.getItemMeta() instanceof BlockStateMeta)) {
            return null;
        }

        BlockStateMeta bsm = (BlockStateMeta) shulkerItem.getItemMeta();
        BlockState state = bsm.getBlockState();
        if (state instanceof ShulkerBox) {
            ShulkerBox shulker = (ShulkerBox) state;
            Inventory shulkerInventory = Bukkit.createInventory(null, InventoryType.SHULKER_BOX, "Shulker Box");
            shulkerInventory.setContents(shulker.getInventory().getContents());
            return shulkerInventory;
        }

        return null;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        updateShulkerBoxInventory(event);
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        updateShulkerBoxInventory(event);
    }

    private void updateShulkerBoxInventory(InventoryEvent event) {
        if (event.getView().getTitle().equals("Shulker Box")) {
            Player player = (Player) event.getView().getPlayer();
            ItemStack itemInHand = player.getInventory().getItemInMainHand();

            if (itemInHand != null && itemInHand.getItemMeta() instanceof BlockStateMeta) {
                BlockStateMeta bsm = (BlockStateMeta) itemInHand.getItemMeta();
                BlockState state = bsm.getBlockState();
                if (state instanceof ShulkerBox) {
                    ShulkerBox shulker = (ShulkerBox) state;
                    shulker.getInventory().setContents(event.getInventory().getContents());
                    bsm.setBlockState(shulker);
                    itemInHand.setItemMeta(bsm);
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        updateShulkerBoxInventory(event);
    }
}
