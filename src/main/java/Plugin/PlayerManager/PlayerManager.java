package Plugin.PlayerManager;

import Plugin.BoxPvp.BoxPvpSection;
import Plugin.Duel.DuelSection;
import Plugin.Messages.Enum.Messages;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

import static Plugin.BoxPvp.BoxPvpSection.getZoneSafeBoxPvp;
import static Plugin.Messages.MessageManager.*;

public class PlayerManager {

    private final xBxTcore plugin;

    public static final ArrayList<UUID> playresInSafeZone = new ArrayList<>();
    public static final HashMap<UUID, BukkitTask> punishedTiemer = new HashMap<>();

    public PlayerManager(xBxTcore plugin) {
        this.plugin = plugin;
    }

    public void PunishedBySafeZone(@NotNull Player player) {

        if (playresInSafeZone.contains(player.getUniqueId())){
            return;
        }else {
            playresInSafeZone.add(player.getUniqueId());
        }
        Bukkit.getConsoleSender().sendMessage( ChatColor.translateAlternateColorCodes('&',prefixConsole + Colorinfo + "El jugador " + Colorplayer + player.getName() + Colorinfo + " entro en zona segura en combate"));

        new BukkitRunnable() {
            int time = 3;
            public void run() {
                if (!BoxPvpSection.getZoneSafeBoxPvp().isSafeZone(player.getLocation()) || player.getHealth() <= 0){
                    playresInSafeZone.remove(player.getUniqueId());
                    cancel();
                    return;
                }

                player.sendTitle("", MasterMessageLocated(player, Messages.InSafeZone).replace("%time%", String.valueOf(time)), 5, 15, 5);
                if (time <= 0) {
                    cancel();
                    BukkitTask bukkitTask = new BukkitRunnable() {
                        public void run() {
                            if (!getZoneSafeBoxPvp().isSafeZone(player.getLocation())){
                                playresInSafeZone.remove(player.getUniqueId());
                                punishedTiemer.remove(player.getUniqueId());
                                cancel();
                            }
                            player.damage(5);
                        }
                    }.runTaskTimer(plugin, 5, 5);
                    punishedTiemer.put(player.getUniqueId(), bukkitTask);
                }
                time--;
            }
        }.runTaskTimer(plugin, 0, 20);

    }

    public void RestartStats(Player player) {
        player.setSaturatedRegenRate(10);
        player.setUnsaturatedRegenRate(80);
        Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ARMOR), "hola").setBaseValue(0);
        player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(0);
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
        player.setLevel(0);
        player.setExp(0);
        player.setExpCooldown(0);
        DuelSection.getDuelManager().RemoveBlockedMoment(player);
    }
}
