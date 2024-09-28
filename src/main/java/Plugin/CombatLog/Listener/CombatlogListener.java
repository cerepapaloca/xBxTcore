package Plugin.CombatLog.Listener;

import Plugin.BoxPvp.BoxPvpSection;
import Plugin.CombatLog.CombatSection;
import Plugin.Messages.Messages.Messages;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.*;

import static Plugin.PlayerManager.Listener.BlockerListener.ejey;
import static Plugin.Messages.MessageManager.MasterMessageLocated;

public class CombatlogListener implements Listener {

    private final List<String> restrictedCommands = new ArrayList<>();
    private final xBxTcore plugin;

    public CombatlogListener(xBxTcore plugin) {
        this.plugin = plugin;
        restrictedCommands.add("lobby");
        restrictedCommands.add("kill");
        restrictedCommands.add("spectator");
        restrictedCommands.add("boxpvp");
        restrictedCommands.add("kf");
        restrictedCommands.add("kitfavorite");
    }

    @EventHandler
    public void DamagePlayres(EntityDamageByEntityEvent event) {
        if (event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) && event.getEntity() instanceof Player attacked) {
            CombatSection.getCombatlogManager().startCombat(attacked);
            return;
        }
        if (event.getEntity() instanceof Player attacked && event.getDamager() instanceof Player attacker) {
            if (ejey + 4 <= event.getEntity().getLocation().getBlockY() && (Bukkit.getWorld("lobby") == event.getEntity().getWorld()) || Bukkit.getWorld("creatorkits") == event.getEntity().getWorld()) {
                event.setCancelled(true);
                return;
            }else if (Bukkit.getWorld("boxpvp") == event.getEntity().getWorld()){
                if (BoxPvpSection.getZoneSafeBoxPvp().isSafeZone(event.getEntity().getLocation())){
                    event.setCancelled(true);
                    return;
                }
            }
            CombatSection.getCombatlogManager().startCombat(attacked);
            CombatSection.getCombatlogManager().startCombat(attacker);
        }
    }

    @EventHandler
    public void PlayerQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if(CombatSection.getCombatlogManager().isInCombat(player)){
            player.setHealth(0);
        }
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (CombatSection.getCombatlogManager().isInCombat(player)) {
            String command = event.getMessage().split(" ")[0].substring(1).toLowerCase();
            if (restrictedCommands.contains(command)) {
                event.setCancelled(true);
                player.sendMessage(MasterMessageLocated(player, Messages.CombateLog_OnCombat).replace("%time%", String.valueOf(CombatSection.getCombatlogManager().getTimeRemaining(player))));
            }
        }
    }







}

