package Plugin.BoxPvp.ItemsBoxPvp.Listener;

import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.scheduler.BukkitRunnable;

import static Plugin.BoxPvp.ItemsBoxPvp.BonusUpdate.UpdateBonus;

public class ArmorBonusListener implements Listener {

    private static xBxTcore plugin;

    public ArmorBonusListener(xBxTcore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        onInventoryClickEvent(event);
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        onInventoryClickEvent(event);
    }

    public void onInventoryClickEvent(InventoryEvent event) {
        if (event.getView().getPlayer().getWorld().equals(Bukkit.getWorld("boxpvp"))) {
            Player player = (Player) event.getView().getPlayer();

            new BukkitRunnable() {
                public void run() {
                    UpdateBonus(player);
                }
            }.runTaskLater(plugin, 1);

        }
    }
}
