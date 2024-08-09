package Plugin.Listeners;

import Plugin.Model.Messages;
import Plugin.xBxTcore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.*;

public class CombatlogListener implements Listener {
    private final HashMap<UUID, Long> combatCooldowns = new HashMap<>();
    private final List<String> restrictedCommands = new ArrayList<>();

    public CombatlogListener() {
        restrictedCommands.add("lobby");
        restrictedCommands.add("kill");
        restrictedCommands.add("spectator");
    }

    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player attacked && event.getDamager() instanceof Player attacker) {
            startCombat(attacked);
            startCombat(attacker);
        }
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (isInCombat(player)) {
            String command = event.getMessage().split(" ")[0].substring(1).toLowerCase();
            if (restrictedCommands.contains(command)) {
                event.setCancelled(true);
                player.sendMessage(xBxTcore.getMessageManager().MasterMessage(player, Messages.OnCombat).replace("%time%", String.valueOf(getTimeRemaining(player))));
            }
        }
    }

    private void startCombat(Player player) {
        long COMBAT_COOLDOWN_TIME = 30 * 1000;
        combatCooldowns.put(player.getUniqueId(), System.currentTimeMillis() + COMBAT_COOLDOWN_TIME);
    }

    private boolean isInCombat(Player player) {
        return combatCooldowns.containsKey(player.getUniqueId()) &&
                combatCooldowns.get(player.getUniqueId()) > System.currentTimeMillis();
    }

    public void endCombat(Player player) {
        combatCooldowns.remove(player.getUniqueId());
    }

    public long getTimeRemaining(Player player) {
        if (isInCombat(player)) {
            long currentTime = System.currentTimeMillis();
            long endTime = combatCooldowns.get(player.getUniqueId());
            return (endTime - currentTime) / 1000;
        }
        return 0;
    }


}

