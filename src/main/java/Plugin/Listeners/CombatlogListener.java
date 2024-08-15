package Plugin.Listeners;

import Plugin.Model.Messages;
import Plugin.Utils.Tools;
import Plugin.xBxTcore;
import me.neznamy.tab.api.TabPlayer;
import me.neznamy.tab.api.bossbar.BarColor;
import me.neznamy.tab.api.bossbar.BarStyle;
import me.neznamy.tab.api.bossbar.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class CombatlogListener implements Listener {
    private final HashMap<UUID, Long> combatCooldowns = new HashMap<>();
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

    private void combatUpdate(Player player) {
        new BukkitRunnable() {
            public void run() {
                if (xBxTcore.getWorldProtec().contains(player.getWorld())) {
                    if(getTimeRemaining(player) >= 0){
                        updateBossBars(getTimeRemaining(player),player,30);
                    }else{
                        cancel();
                    }
                }
            }
        }.runTaskTimer(plugin, 20, 20);
    }

    private void startCombat(Player player) {
        combatUpdate(player);
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

    public int getTimeRemaining(Player player) {
        if (isInCombat(player)) {
            long currentTime = System.currentTimeMillis();
            long endTime = combatCooldowns.get(player.getUniqueId());
            return (int) ((endTime - currentTime) / 1000);
        }
        return 0;
    }

    private void updateBossBars(int timeLeft, Player player, int timemax) {
        String title;
        title = "En combate " + Tools.SecondToMinutes(timeLeft);
        BarStyle barStyle = BarStyle.PROGRESS;
        BarColor barColor = BarColor.RED;
        BossBar bossBar = Objects.requireNonNull(xBxTcore.getTabAPI().getBossBarManager()).getBossBar("timerBossBarCombat");
        if (bossBar == null) {
            bossBar = xBxTcore.getTabAPI().getBossBarManager().createBossBar("timerBossBarCombat", 1.0f, barColor, barStyle);
            bossBar.setTitle(title);
            bossBar.setProgress(((float) timeLeft/timemax)*100);
            TabPlayer tabPlayer = xBxTcore.getTabAPI().getPlayer(player.getUniqueId());
            if (tabPlayer != null) {
                xBxTcore.getTabAPI().getBossBarManager().sendBossBarTemporarily(tabPlayer, bossBar.getName(), 1);
            }
        }
    }
}

