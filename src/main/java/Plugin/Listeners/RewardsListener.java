package Plugin.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class RewardsListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getClickedBlock().getType().equals(Material.YELLOW_SHULKER_BOX) && player.getWorld().getName().equals("boxpvp")) {
            player.sendMessage("Recompensa");
            event.setCancelled(true);
        }
    }
}