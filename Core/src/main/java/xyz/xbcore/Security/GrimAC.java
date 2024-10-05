package xyz.xbcore.Security;

import ac.grim.grimac.api.events.FlagEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static xyz.xbcore.xBxTcore.worldBoxPvp;

public class GrimAC implements Listener {

    @EventHandler
    public void ByPassAC (FlagEvent event) {
        if (!Bukkit.getPlayer(event.getPlayer().getUniqueId()).getWorld().getName().equals(worldBoxPvp)){
            event.setCancelled(true);
        }
    }
}
