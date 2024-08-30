package Plugin.Duel;

import Plugin.Duel.Enum.EndCombatCauses;
import Plugin.Environments.EnvironmentsSection;
import Plugin.Messages.MessageManager;
import Plugin.Inventory.Models.KitData;
import Plugin.Messages.Enum.Messages;
import Plugin.xBxTcore;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import me.neznamy.tab.api.TabPlayer;
import me.neznamy.tab.api.bossbar.BarColor;
import me.neznamy.tab.api.bossbar.BarStyle;
import me.neznamy.tab.api.bossbar.BossBar;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

import static Plugin.File.FileManagerSection.getPlayerFileManager;
import static Plugin.Messages.MessageManager.MasterMessageLocated;

public class DuelManager{

    private final xBxTcore plugin;
    private final HashSet<UUID> blockedPlayers = new HashSet<>();
    private final ArrayList<Player> players = new ArrayList<>();
    private final HashMap<World, BukkitTask> timers = new HashMap<>();
    private BukkitTask runnable;
    private final ArrayList<Location> locations = new ArrayList<>();

    public DuelManager(xBxTcore plugin){
        this.plugin = plugin;
    }

    public void StartDuel(ArrayList<Player> players, MultiverseWorld world){
        this.players.addAll(players);
        KitData kitData = xBxTcore.getPlayerDataUnique(players.get(0).getUniqueId()).getKitData();
        int y = 0;
        int i = 0;

        if (world.getName().contains("world")){
            y = 20;
        }else{
            y = 7;
        }
        locations.clear();
        locations.add(new Location(Bukkit.getWorld(world.getName()) , 0, y, 35.5, -180, 0));
        locations.add(new Location(Bukkit.getWorld(world.getName()) , 0, y, -35.5, 0, 0));
        locations.add(new Location(Bukkit.getWorld(world.getName()) , 35.5, y, 0, 90, 0));
        locations.add(new Location(Bukkit.getWorld(world.getName()) , -35.5, y, 0, -90, 0));
        locations.add(new Location(Bukkit.getWorld(world.getName()) , 35.5, y, 35.5, 135, 0));
        locations.add(new Location(Bukkit.getWorld(world.getName()) , 35.5, y, -35.5, 45, 0));
        locations.add(new Location(Bukkit.getWorld(world.getName()) , -35.5, y, 35.5, -135, 0));
        locations.add(new Location(Bukkit.getWorld(world.getName()) , -35.5, y, -35.5, -45, 0));

        for (Player player : players){
            if (kitData.getName() != null && kitData.getUuid() != null){
                getPlayerFileManager().loadKit(kitData.getUuid(),kitData.getName(),null, player);
            }else{
                getPlayerFileManager().loadkitfavorite(player);
            }
            player.teleport(locations.get(i));
            i++;
        }

        startCountdown(world.getName(), xBxTcore.getPlayerDataUnique(players.get(0).getUniqueId()).getTimelimit());
        EnvironmentsSection.getCleaner().clearArea(world.getName());
        TextComponent tp = new TextComponent();
        tp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/spectator " + world.getName()));
        for (Player player : Bukkit.getOnlinePlayers()) {
            tp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(MasterMessageLocated(player, Messages.HoverDuel)).create()));
            if(players.size() < 3){
                tp.setText(MasterMessageLocated(player, Messages.DuelStarted1).replace("%player1%", players.get(0).getName())
                        .replace("%player2%", players.get(1).getName()).replace("%world%", world.getName()));
                player.spigot().sendMessage(tp);
            }else {
                tp.setText(MasterMessageLocated(player, Messages.DuelStarted2).replace("%player%", players.get(0).getName())
                        .replace("%world%", world.getName()));
                player.spigot().sendMessage(tp);
            }
        }

        if(players.size() >= 3){
            for (Player player1 : players){
                if (!player1.getName().equals(players.get(0).getName())){
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&l- " + MessageManager.Colorplayer + player1.getName()));
                }
            }
        }

    }

    private void startCountdown(String world, Boolean timeLimit) {
        for (Player player : Objects.requireNonNull(Bukkit.getWorld(world)).getPlayers()) {
            AddBlockedMoment(player);
        }
        new BukkitRunnable() {
            int timeLeft = 5;
            public void run() {
                if (timeLeft <= 0) {
                    for (Player player : Objects.requireNonNull(Bukkit.getWorld(world)).getPlayers()) {
                        player.sendTitle("", MasterMessageLocated(player, Messages.Go), 0, 70, 20);
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1,1.587401f);
                        player.setGravity(true);
                        blockedPlayers.remove(player.getUniqueId());
                    }
                    if (timeLimit){
                        timeLimit(xBxTcore.getPlayerDataUnique(players.get(0).getUniqueId()).getTimeDuel(), Bukkit.getWorld(world));
                    }
                    cancel();
                    return;
                }

                for (Player player : Objects.requireNonNull(Bukkit.getWorld(world)).getPlayers()) {
                    player.sendTitle("", "" + timeLeft, 0, 15, 5);
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1,0.793701f);
                }

                timeLeft--;
            }
        }.runTaskTimer(plugin, 20, 20);
    }

    public void EndDuel(World world, Player winner, EndCombatCauses endCombatCauses){
        if (!world.getName().equals("lobby") && !world.getName().equals("creatorkits")){
            int i = 0;
            switch (endCombatCauses){
                case DIED:

                    for (Player player : world.getPlayers()) {
                        if (player.getGameMode().equals(GameMode.SURVIVAL)){
                            i++;
                        }
                    }

                    if (i > 2){
                        return;
                    }

                    if (winner != null){
                        startretur(world.getName());
                    }else{
                        for (Player p : Objects.requireNonNull(Bukkit.getWorld(world.getName())).getPlayers()){
                            p.teleport(new Location((Bukkit.getWorld("lobby")), 0, 69, 0, 0, 0));
                        }
                    }

                    if (timers.get(world) != null){
                        timers.get(world).cancel();
                    }

                    break;
                case LEFT:
                    for (Player player : world.getPlayers()) {
                        if (player.getGameMode().equals(GameMode.SURVIVAL)){
                            i++;
                        }
                    }

                    if (i > 2){
                        return;
                    }

                    for (Player p : Objects.requireNonNull(Bukkit.getWorld(world.getName())).getPlayers()){
                        p.teleport(new Location((Bukkit.getWorld("lobby")), 0, 69, 0, 0, 0));
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&',MasterMessageLocated(p, Messages.EndCombat)));
                        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',MessageManager.prefixConsole + MessageManager.Colorinfo + "Se a terminado el duelo"));
                    }

                    if (timers.get(world) != null){
                        timers.get(world).cancel();
                    }

                    break;
                case TIME:
                    for (Player p : Objects.requireNonNull(Bukkit.getWorld(world.getName())).getPlayers()){
                        p.teleport(new Location((Bukkit.getWorld("lobby")), 0, 69, 0, 0, 0));
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&',MasterMessageLocated(p, Messages.EndTimeDuel)));
                        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',MessageManager.prefixConsole + MessageManager.Colorinfo + "Se a terminado el duelo"));
                    }
                    break;
            }
        }
    }

    private void startretur(String world) {
        new BukkitRunnable() {
            int timeLeft = 5;
            public void run() {
                for (Player player : Objects.requireNonNull(Bukkit.getWorld(world)).getPlayers()) {
                    player.teleport(new Location((Bukkit.getWorld("lobby")), 0, 69, 0, 0, 0));
                }
                cancel();

                timeLeft--;
            }
        }.runTaskTimer(plugin, 40, 0);
    }

    private void timeLimit(int seconds, World world) {
        runnable = new BukkitRunnable() {
            int timeLeft = seconds;
            public void run() {
                updateBossBars(timeLeft, world.getName(), seconds);
                if (timeLeft <= 0){
                    EndDuel(Objects.requireNonNull(world), null, EndCombatCauses.TIME);
                    cancel();
                }
                timeLeft--;
            }
        }.runTaskTimer(plugin, 0, 20);
        timers.put(world, runnable);
    }

    private void updateBossBars(int timeLeft, String world, int timemax) {
        int minutes = timeLeft/60;
        int seconds = timeLeft%60;

        for (Player player : Objects.requireNonNull(Bukkit.getWorld(world)).getPlayers()) {
            String title;
            if (seconds < 10){
                title = MasterMessageLocated(player, Messages.TimeBossBar) + minutes + ":0" + seconds;
            }else{
                title = MasterMessageLocated(player, Messages.TimeBossBar) + minutes + ":" + seconds;
            }
            BarStyle barStyle = BarStyle.PROGRESS;
            BarColor barColor = BarColor.BLUE;
            BossBar bossBar = Objects.requireNonNull(xBxTcore.getTabAPI().getBossBarManager()).getBossBar("timerBossBar");
            if (bossBar == null) {
                bossBar = xBxTcore.getTabAPI().getBossBarManager().createBossBar("timerBossBar", 1.0f, barColor, barStyle);
                bossBar.setTitle(title);
                bossBar.setProgress(((float) timeLeft/timemax)*100);
                TabPlayer tabPlayer = xBxTcore.getTabAPI().getPlayer(player.getUniqueId());
                if (tabPlayer != null) {
                    xBxTcore.getTabAPI().getBossBarManager().sendBossBarTemporarily(tabPlayer, bossBar.getName(), 1);
                }
            }
        }
    }

    public void AddBlockedMoment(Player player){
        blockedPlayers.add(player.getUniqueId());
    }

    public HashSet<UUID> GetBlockedMoment(){
        return blockedPlayers;
    }

    public void RemoveBlockedMoment(Player player){
        blockedPlayers.remove(player.getUniqueId());
    }
}
