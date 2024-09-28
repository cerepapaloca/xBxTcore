package Plugin.BoxPvp.ItemsBoxPvp.Listener;

import Plugin.Messages.MessageManager;
import Plugin.Messages.Messages.Messages;
import Plugin.xBxTcore;
import net.luckperms.api.node.types.InheritanceNode;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.concurrent.TimeUnit;

public class AddRangeListener implements Listener {

    private final xBxTcore plugin;

    public AddRangeListener(xBxTcore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void addRange(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType().equals(Material.NAME_TAG) && item.getItemMeta() != null){
            String range = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "range"), PersistentDataType.STRING);
            Long time = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "duration"), PersistentDataType.LONG);
            if (range == null)return;
            player.getInventory().getItemInMainHand().setAmount(0);
            if (time != null){
                xBxTcore.getLuckPerms().getUserManager().modifyUser(player.getUniqueId(),
                        user -> user.data().add(InheritanceNode.builder(range).expiry(time, TimeUnit.MILLISECONDS).build()));
            }else {
                xBxTcore.getLuckPerms().getUserManager().modifyUser(player.getUniqueId(),
                        user -> user.data().remove(InheritanceNode.builder(range).build()));
            }
            player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            player.sendTitle(MessageManager.MasterMessageLocated(player, Messages.Others_AddVIPTitle),"", 1, 3, 2);
        }
    }
}
