package xyz.xbcore.Duel.Listener;

import xyz.xbcore.Duel.DuelSection;
import xyz.xbcore.xBxTcore;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class DuelListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!xBxTcore.getWorldProtec().contains(event.getPlayer().getWorld()) && event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)){
            if (DuelSection.getDuelManager().GetBlockedMoment().contains(event.getPlayer().getUniqueId())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!xBxTcore.getWorldProtec().contains(event.getPlayer().getWorld()) && event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)){
            if (DuelSection.getDuelManager().GetBlockedMoment().contains(event.getPlayer().getUniqueId())) {
                event.setCancelled(true);
            }
        }
    }

}
