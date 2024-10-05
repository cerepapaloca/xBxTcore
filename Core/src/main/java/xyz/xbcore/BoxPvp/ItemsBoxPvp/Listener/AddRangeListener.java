package xyz.xbcore.BoxPvp.ItemsBoxPvp.Listener;

import xyz.xbcommun.Messages.MessageManager;
import xyz.xbcommun.Messages.Messages.Messages;
import xyz.xbcore.xBxTcore;
import net.luckperms.api.node.types.InheritanceNode;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.concurrent.TimeUnit;

import static xyz.xbcommun.Messages.MessageManager.*;

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
            PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
            NamespacedKey keyRange = new NamespacedKey(plugin, "range");
            NamespacedKey keyTime = new NamespacedKey(plugin, "duration");
            if (container.has(keyRange, PersistentDataType.STRING) && container.has(keyTime, PersistentDataType.LONG)){
                String range = container.get(new NamespacedKey(plugin, "range"), PersistentDataType.STRING);
                Long time = container.get(new NamespacedKey(plugin, "duration"), PersistentDataType.LONG);
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
                player.sendTitle(MessageManager.MasterMessageLocated(player, Messages.Others_AddVIPTitle),"", 20, 60, 40);
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefixConsole + ColorSuccess +
                        "Se reclamo el rango '" + range + "' por el jugador " + Colorplayer + player.getName()));
            }
        }
    }
}
