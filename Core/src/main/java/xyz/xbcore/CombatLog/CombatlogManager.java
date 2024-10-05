package xyz.xbcore.CombatLog;

import xyz.xbcore.Utils.Utils;
import xyz.xbcore.xBxTcore;
import me.neznamy.tab.api.TabPlayer;
import me.neznamy.tab.api.bossbar.BarColor;
import me.neznamy.tab.api.bossbar.BarStyle;
import me.neznamy.tab.api.bossbar.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class CombatlogManager {

    private final HashMap<UUID, Long> combatCooldowns = new HashMap<>();
    private final xBxTcore plugin;

    public CombatlogManager(xBxTcore plugin) {
        this.plugin = plugin;
    }

    private void combatUpdate(Player player) {
        new BukkitRunnable() {
            public void run() {
                if (player == null){
                    cancel();
                    return;
                }
                if (xBxTcore.getWorldProtec().contains(player.getWorld())) {
                    if(getTimeRemaining(player) >= 1){
                        updateBossBars(getTimeRemaining(player),player,30);
                    }else{
                        endCombat(player);
                        cancel();
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }

    public void startCombat(Player player) {
        if (!isInCombat(player)) {
            combatUpdate(player);
        }
        long COMBAT_COOLDOWN_TIME = 30 * 1000;
        combatCooldowns.put(player.getUniqueId(), System.currentTimeMillis() + COMBAT_COOLDOWN_TIME);
    }

    public boolean isInCombat(Player player) {
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
        title = "En combate " + Utils.TimeToString(timeLeft, 0);
        BarStyle barStyle = BarStyle.PROGRESS;
        BarColor barColor = BarColor.RED;
        BossBar bossBar = Objects.requireNonNull(xBxTcore.getTabAPI().getBossBarManager()).getBossBar("timerBossBarCombat");
        if (bossBar != null) {
            bossBar.removePlayer(Objects.requireNonNull(xBxTcore.getTabAPI().getPlayer(player.getUniqueId())));
        }
        bossBar = xBxTcore.getTabAPI().getBossBarManager().createBossBar("timerBossBarCombat", 1.0f, barColor, barStyle);
        bossBar.setTitle(title);
        bossBar.setProgress(((float) timeLeft/timemax)*100);
        TabPlayer tabPlayer = xBxTcore.getTabAPI().getPlayer(player.getUniqueId());
        if (tabPlayer != null){
            xBxTcore.getTabAPI().getBossBarManager().sendBossBarTemporarily(tabPlayer, bossBar.getName(), 1);
        }
    }
}
