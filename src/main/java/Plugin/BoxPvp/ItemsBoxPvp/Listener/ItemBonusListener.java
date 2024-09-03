package Plugin.BoxPvp.ItemsBoxPvp.Listener;

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
    public void damage(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof Player attacked && event.getDamager() instanceof Player attacker){

            if (!attacked.getWorld().getName().equals(worldBoxPvp)){
                return;
            }

            ItemStack item = attacker.getInventory().getItemInOffHand();
            if (item.getItemMeta() == null) return;
            PotionEffect Wither = new PotionEffect(PotionEffectType.WITHER, 20*10, 3);
            if (item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "keyEspacial"), PersistentDataType.STRING) == null)return;
            switch (requireNonNull(item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "keyEspacial"), PersistentDataType.STRING))){
                case "DañoBonus" -> {
                    event.setDamage(event.getDamage()*1.75);
                    attacker.setFoodLevel(attacker.getFoodLevel() - 3);
                }
                case "DañoPorWither" -> {
                    if (attacker.getHealth() <= 16){
                        attacked.addPotionEffect(Wither);
                    }
                }
                case "DañoBonusPorAbsorción" -> {
                    if (attacked.hasPotionEffect(PotionEffectType.ABSORPTION)){
                        event.setDamage(event.getDamage()*3);
                    }else{
                        event.setDamage(event.getDamage()*0.75);
                    }
                }
            }
        }
    }
}
