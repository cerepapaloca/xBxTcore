package Plugin.Listeners.Invetory;

import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class ArmorBonusListener implements Listener {

    private final xBxTcore plugin;

    public ArmorBonusListener(xBxTcore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        if (event.getWhoClicked().getWorld().equals(Bukkit.getWorld("boxpvp"))) {
           if (event.getSlotType().equals(InventoryType.SlotType.ARMOR)){
               Player player = (Player) event.getWhoClicked();
               ItemStack item = event.getCurrentItem();
               switch (item.getType()) {
                   case ELYTRA -> player.setHealth(Math.min(player.getHealth() + item.getItemMeta().getPersistentDataContainer().get(
                                   new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER) * 2,
                           Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()));
               }
           }
        }
    }
}
