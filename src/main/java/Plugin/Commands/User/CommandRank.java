package Plugin.Commands.User;

import Plugin.Model.Player.PlayerDataGLobal;
import Plugin.xBxTcore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static Plugin.Managers.MessageManager.*;

public class CommandRank implements CommandExecutor {

    private final xBxTcore plugin;
    private final PlayerDataGLobal playerDataGLobal;

    public CommandRank(xBxTcore plugin){
        this.plugin = plugin;
        playerDataGLobal = xBxTcore.getPlayerDataGlobal();
    }
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if(sender instanceof Player p){
            int top;
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + Colorinfo + "Top Kills -------------->"));
            List<Player> sortedPlayers = xBxTcore.getHologramas().sortPlayersByKill(playerDataGLobal.getPlayerKills());

            for (int i = 0; i < 10; i++) {
                Player player = null;

                if (i < sortedPlayers.size()) {
                    player = sortedPlayers.get(i);
                }

                if (player != null) {
                    int lvl;
                    if(player.getWorld().getName().equals("lobby")){
                        lvl = player.getLevel();
                    }else{
                        lvl = 0;
                    }

                    String colorRack;
                    if(i == 0){
                        colorRack = "&e&o";
                    }else if(i == 1){
                        colorRack = "&7&o";
                    }else if(i == 2){
                        colorRack = "&6&o";
                    }else{
                        colorRack = "&8";
                    }
                    top = i + 1;
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',  colorRack + top + "#&r " + Colorplayer + player.getName() + ":&b " + playerDataGLobal.getKills(player.getUniqueId()) + " &3(&b" + lvl + "&3) " + "&3kills"));
                }
            }
        }else{
            plugin.messageOnlyPlayer();
        }

        return false;
    }
}
