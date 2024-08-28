package Plugin.Listeners;

import Plugin.Inventory.Models.InvetoryPlayer;
import Plugin.xBxTcore;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class RewardsListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getClickedBlock() != null){
            if (Objects.requireNonNull(event.getClickedBlock()).getType().equals(Material.YELLOW_SHULKER_BOX) && player.getWorld().getName().equals("boxpvp")) {
                xBxTcore.getInventoryMenu().OpenRewardTimes(new InvetoryPlayer(player));
                event.setCancelled(true);
            }
        }
    }
}