package Plugin.BoxPvp.ItemsBoxPvp.Listener;

import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class ArmorBonusListener implements Listener {

    private final xBxTcore plugin;

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

    public void UpdateBonus(Player player) {
        ItemStack item;
        item = player.getInventory().getItem(EquipmentSlot.HEAD);
        if (item.getType() == Material.LEATHER_HELMET){
            int tier = item.getItemMeta().getPersistentDataContainer().get(
                    new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER);
            int i = 10 - tier;
            if (i < 2) {
                i = 2;
            }
            player.setUnsaturatedRegenRate(80 - 2 * tier);
            player.setSaturatedRegenRate(i);
        } else {
            player.setSaturatedRegenRate(10);
            player.setUnsaturatedRegenRate(80);
        }
        item = player.getInventory().getItem(EquipmentSlot.CHEST);
        if (item.getType() == Material.ELYTRA) {
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(20 + 2 *
                    item.getItemMeta().getPersistentDataContainer().get(
                            new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER));
        }else {
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(20);
        }
        item = player.getInventory().getItem(EquipmentSlot.LEGS);
        if (item.getType() == Material.LEATHER_LEGGINGS) {
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ARMOR)).setBaseValue(1 + item.getItemMeta().getPersistentDataContainer().get(
                            new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER));
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS)).setBaseValue(1 + item.getItemMeta().getPersistentDataContainer().get(
                    new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER));
        }else {
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ARMOR)).setBaseValue(0);
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS)).setBaseValue(0);
        }
    }
}
