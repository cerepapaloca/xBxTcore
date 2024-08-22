package Plugin.Listeners.Invetory;

import Plugin.xBxTcore;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;


public class ItemframeListener implements Listener {


    @EventHandler
    public void PlayerInteractItemFrame(PlayerInteractEntityEvent event) {
        if (!event.getPlayer().hasPermission("xBxTpvp.Op")) {
            Player player = event.getPlayer();
            Entity entity = event.getRightClicked();
            if (entity instanceof ItemFrame frame) {
                event.setCancelled(true);
                xBxTcore.getInvetoryManager().invetorymenu().OpenItemframe(player, frame.getItem());
            }
        }
    }
}
