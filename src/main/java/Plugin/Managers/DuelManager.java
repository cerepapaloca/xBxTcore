package Plugin.Managers;

import Plugin.Model.Messages;
import Plugin.xBxTcore;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

public class DuelManager{

    private final xBxTcore plugin;
    private final HashSet<UUID> blockedPlayers = new HashSet<>();

    public DuelManager(xBxTcore plugin){
        this.plugin = plugin;
    }

    public void StartDuel(ArrayList<Player> players, MultiverseWorld world){
        for (Player player : players){
            xBxTcore.getPlayerFileManager().loadkitfavorite(player);
            if (world.getName().contains("world")){
                player.teleport(new Location(Bukkit.getWorld(world.getName()) , -35.5, 20, -35.5, -45, 0));
            }else{
                player.teleport(new Location(Bukkit.getWorld(world.getName()) , -35.5, 7, -35.5, -45, 0));
            }
        }

        startCountdown(world.getName());
        xBxTcore.getCleaner().clearArea(world.getName());
        TextComponent tp = new TextComponent();
        tp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/spectator " + world.getName()));
        for (Player player : Bukkit.getOnlinePlayers()) {
            tp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(xBxTcore.getMessageManager().MasterMessage(player, Messages.HoverDuel)).create()));
            tp.setText(xBxTcore.getMessageManager().MasterMessage(player, Messages.DuelStarted).replace("%player1%", players.get(0).getName())
                    .replace("%player2%", players.get(1).getName()).replace("%world%", world.getName()));
            player.spigot().sendMessage(tp);
        }
    }

    private void startCountdown(String world) {
        for (Player player : Objects.requireNonNull(Bukkit.getWorld(world)).getPlayers()) {
            AddBlockedMoment(player);
        }
        new BukkitRunnable() {
            int timeLeft = 5;

            @Override
            public void run() {
                if (timeLeft <= 0) {
                    for (Player player : Objects.requireNonNull(Bukkit.getWorld(world)).getPlayers()) {
                        player.sendTitle("", xBxTcore.getMessageManager().MasterMessage(player, Messages.Go), 10, 70, 20);
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1,1.587401f);
                        player.setGravity(true);
                        blockedPlayers.remove(player.getUniqueId());
                    }
                    cancel();
                    return;
                }

                for (Player player : Objects.requireNonNull(Bukkit.getWorld(world)).getPlayers()) {
                    player.sendTitle("", "" + timeLeft, 0, 20, 0);
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1,0.793701f);
                }

                timeLeft--;
            }
        }.runTaskTimer(plugin, 40, 20);
    }

    public void EndDuel(World world, Player winner, Boolean left){
        if (!world.getName().equals("lobby") && !world.getName().equals("creatorkits")){
            if (left){
                for (Player p : Objects.requireNonNull(Bukkit.getWorld(world.getName())).getPlayers()){
                    p.teleport(new Location((Bukkit.getWorld("lobby")), 0, 69, 0, 0, 0));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',xBxTcore.getMessageManager().MasterMessage(p, Messages.EndCombat)));
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',MessageManager.prefixConsole + MessageManager.Colorinfo + "Se a terminado el duelo"));
                }
            }else{
                if (winner != null){
                    startretur(5, world.getName());
                }else{
                    for (Player p : Objects.requireNonNull(Bukkit.getWorld(world.getName())).getPlayers()){
                        p.teleport(new Location((Bukkit.getWorld("lobby")), 0, 69, 0, 0, 0));
                    }
                }
            }
        }
    }

    private void startretur(int seconds, String world) {
        new BukkitRunnable() {
            int timeLeft = seconds;

            @Override
            public void run() {

                for (Player player : Objects.requireNonNull(Bukkit.getWorld(world)).getPlayers()) {
                    player.teleport(new Location((Bukkit.getWorld("lobby")), 0, 69, 0, 0, 0));
                }
                cancel();

                timeLeft--;
            }
        }.runTaskTimer(plugin, 40, 0);
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
