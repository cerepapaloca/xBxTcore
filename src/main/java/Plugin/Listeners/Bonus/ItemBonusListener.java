package Plugin.Listeners.Bonus;

import Plugin.xBxTcore;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class ItemBonusListener implements Listener {

    private final xBxTcore plugin;

    public ItemBonusListener(xBxTcore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void damage(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof Player attacked && event.getDamager() instanceof Player attacker){

            ItemStack item = attacked.getInventory().getItemInOffHand();


            switch (item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "keyEspacial"), PersistentDataType.STRING)){
                case "DañoBonus" -> event.setDamage(event.getDamage()*2);
            }
        }
    }
}
