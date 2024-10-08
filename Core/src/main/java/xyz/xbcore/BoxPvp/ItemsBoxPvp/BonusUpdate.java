package xyz.xbcore.BoxPvp.ItemsBoxPvp;

import xyz.xbcore.xBxTcore;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class BonusUpdate {

    private static xBxTcore plugin;
    public static int maxTier = 0;
    public static HashMap<UUID, Integer> playerTier = new HashMap<>();

    public BonusUpdate(xBxTcore plugin) {
        BonusUpdate.plugin = plugin;
    }

    public static void UpdateBonus(@NotNull Player player) {
        int tierHead = 0;
        int tierChest = 0;
        int tierLegs = 0;
        int tierBoots = 0;
        int tierSword = 0;
        ItemStack item;
        item = player.getInventory().getItem(EquipmentSlot.HEAD);
        if (item != null) {
            if (item.getType() == Material.LEATHER_HELMET){
                int tier = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER);
                int i = 10 - tier;
                if (i < 2) {
                    i = 2;
                }
                player.setUnsaturatedRegenRate(80 - 2 * tier);
                player.setSaturatedRegenRate(i);
                tierHead = tier;
            } else {
                player.setSaturatedRegenRate(10);
                player.setUnsaturatedRegenRate(80);
            }
        }
        item = player.getInventory().getItem(EquipmentSlot.CHEST);
        if (item != null) {
            if (item.getType() == Material.ELYTRA) {
                int tier = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER);
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(20 + 2 * tier);
                tierChest = tier;
            }else {
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(20);
            }
        }
        item = player.getInventory().getItem(EquipmentSlot.LEGS);
        if (item != null){
            if (item.getType() == Material.LEATHER_LEGGINGS) {
                int tier = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER);
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ARMOR)).setBaseValue(1 + tier);
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS)).setBaseValue(1 + tier);
                tierLegs = tier;

            }else {
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ARMOR)).setBaseValue(0);
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS)).setBaseValue(0);
            }
        }
        item = player.getInventory().getItem(EquipmentSlot.FEET);
        if (item != null){
            if (item.getType() == Material.LEATHER_BOOTS) {
                int tier = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER);
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(1 + tier);
                tierBoots = tier;
            }else {
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(0);
            }
        }
        for (ItemStack itemContent : player.getInventory().getContents()) {
            if (itemContent != null) {
                if (itemContent.getType().equals(Material.NETHERITE_SWORD)) {
                    if (itemContent.getItemMeta() != null) {
                        try {
                            tierSword = itemContent.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER);
                        }catch (Exception e) {
                            tierHead = 0;
                        }
                        break;
                    }
                }
            }
        }
        playerTier.put(player.getUniqueId(), maxTier = Math.max(Math.max(Math.max(Math.max(tierHead, tierChest), tierLegs), tierBoots), tierSword));
    }
}
