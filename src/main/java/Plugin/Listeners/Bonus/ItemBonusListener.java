package Plugin.Listeners.Bonus;

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
            PotionEffect Wither = new PotionEffect(PotionEffectType.WITHER, 20*10, 2);

            switch (item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "keyEspacial"), PersistentDataType.STRING)){
                case "DañoBonus" -> {
                    event.setDamage(event.getDamage()*2);
                    attacker.setFoodLevel(attacker.getFoodLevel() - 1);
                }
                case "DañoPorWither" -> {
                    if (attacker.getHealth() <= 16){
                        attacked.addPotionEffect(Wither);
                    }
                }
            }
        }
    }
}
