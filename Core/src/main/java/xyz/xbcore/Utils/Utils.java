package xyz.xbcore.Utils;

import net.luckperms.api.node.types.InheritanceNode;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import xyz.xbcommun.Messages.Messages.Messages;
import xyz.xbcore.xBxTcore;

import java.util.HashSet;

import static xyz.xbcommun.Messages.MessageManager.*;
import static xyz.xbcommun.Messages.MessageManager.Colorplayer;

public class Utils {

    private static final xBxTcore plugin = xBxTcore.getInstance();

    private static final HashSet<String> playersOfflineReward = new HashSet<>();
    private static final HashSet<String> playersNotBoxPvpReward = new HashSet<>();

    public static void RewardBoxPvpCheck(String name){
        if (playersNotBoxPvpReward.contains(name)){
            new BukkitRunnable(){
                public void run() {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates:crate key give %player% key0 10".replace("%player%", name));
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates:crate key give %player% key1 5".replace("%player%", name));
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates:crate key give %player% key1 2".replace("%player%", name));
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates:crate key give %player% key3 2".replace("%player%", name));
                    playersNotBoxPvpReward.remove(name);
                }
            }.runTaskLater(plugin, 20);
        }
    }

    public static void RewardVote(String name, @NotNull Boolean force) {
        Player player = Bukkit.getPlayer(name);
        if (force){
            if(player == null) {
                playersOfflineReward.add(name);
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefixConsole + ColorWarning + "El jugador&r " + name
                        + ColorWarning + " no existe o no esta conectado"));
            }else{
                Bukkit.getScheduler().runTask(plugin, () -> {
                    if (player.getWorld().getName().equals(xBxTcore.worldBoxPvp)){
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates:crate key give %player% key0 10".replace("%player%", player.getName()));
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates:crate key give %player% key1 5".replace("%player%", player.getName()));
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates:crate key give %player% key1 2".replace("%player%", player.getName()));
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates:crate key give %player% key3 2".replace("%player%", player.getName()));
                    }else{
                        playersNotBoxPvpReward.add(name);
                        player.sendMessage(MasterMessageLocated(player, Messages.Vote_NotBoxPvp));
                    }
                    xBxTcore.getLuckPerms().getUserManager().modifyUser(player.getUniqueId(), user -> {
                        user.data().add(InheritanceNode.builder("vote").build());
                    });
                    player.sendMessage(MasterMessageLocated(player, Messages.Vote_Voted));
                    Bukkit.getConsoleSender().sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo + "Ha votado el jugador: " + Colorplayer + name));
                });
            }
        }else{
            if(player == null && playersOfflineReward.contains(name)) {
                playersOfflineReward.add(name);
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefixConsole + ColorError + "El jugador&r " + Colorplayer + name + " no se pudo dar la recompensa de algún modo"));
                return;
            }
            if(playersOfflineReward.contains(name)){
                playersOfflineReward.remove(name);
                assert player != null;
                player.sendMessage(MasterMessageLocated(player, Messages.Vote_Voted));
                xBxTcore.getLuckPerms().getUserManager().modifyUser(player.getUniqueId(), user -> {
                    user.data().add(InheritanceNode.builder("vote").build());
                });
                Bukkit.getConsoleSender().sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo + "Se ha echo un voto por adelantado de parte del jugador: " + Colorplayer + name));
                playersNotBoxPvpReward.add(name);
            }
        }
    }
}
