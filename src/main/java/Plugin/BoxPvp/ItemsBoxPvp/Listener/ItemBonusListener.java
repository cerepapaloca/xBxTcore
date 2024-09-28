package Plugin.BoxPvp.ItemsBoxPvp.Listener;

import Plugin.BoxPvp.ItemsBoxPvp.Enum.ItemBonus;
import Plugin.xBxTcore;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static Plugin.xBxTcore.worldBoxPvp;
import static java.util.Objects.requireNonNull;

public class ItemBonusListener implements Listener {

    private final xBxTcore plugin;

    public ItemBonusListener(xBxTcore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void damageEspacial(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof Player attacked && event.getDamager() instanceof Player attacker){

            if (!attacked.getWorld().getName().equals(worldBoxPvp)){
                return;
            }

            ItemStack item = attacker.getInventory().getItemInOffHand();
            if (item.getItemMeta() == null) return;
            if (item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "keyEspacial"), PersistentDataType.STRING) == null)return;
            String itemBonus = requireNonNull(item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "keyEspacial"), PersistentDataType.STRING));
            ItemBonus itemBonusEnum = ItemBonus.valueOf(itemBonus);
            switch (itemBonusEnum){
                case DañoBonus -> {
                    event.setDamage(event.getDamage()*1.75);
                    attacker.setFoodLevel(attacker.getFoodLevel() - 3);
                }
                case DañoPorWither -> {
                    if (attacker.getHealth() <= 16){
                        attacked.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 20*10, 3));
                    }
                }
                case DañoBonusPorAbsorción -> {
                    if (attacked.hasPotionEffect(PotionEffectType.ABSORPTION)){
                        event.setDamage(event.getDamage()*2.25);
                    }else{
                        event.setDamage(event.getDamage()*0.75);
                    }
                }
                case BonusDeCeguera -> {
                    double i = Math.random();
                    if(i < 0.3D){
                        attacked.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20*10, 1));
                    }
                }
                case BonusTank -> {
                    attacker.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20*30, 3));
                    attacker.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*30, 3));
                }
            }
        }
    }
}
