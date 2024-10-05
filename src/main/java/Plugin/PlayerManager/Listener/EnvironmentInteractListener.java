package Plugin.PlayerManager.Listener;

import Plugin.Inventory.InventorySection;
import Plugin.Inventory.Models.InvetoryPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class EnvironmentInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(@NotNull PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getClickedBlock() != null){
            if (Objects.requireNonNull(event.getClickedBlock()).getType().equals(Material.YELLOW_SHULKER_BOX) && player.getWorld().getName().equals("boxpvp")) {
                InventorySection.getInventoryMenu().OpenRewardTimes(new InvetoryPlayer(player));
                event.setCancelled(true);
            }
        }
    }
}