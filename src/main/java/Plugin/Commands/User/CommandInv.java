package Plugin.Commands.User;

import Plugin.Messages.Enum.Messages;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static Plugin.Messages.MessageManager.*;

public class CommandInv implements CommandExecutor {

    private final xBxTcore plugin;

    public CommandInv(xBxTcore plugin){
        this.plugin = plugin;
    }
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command cmd,@NotNull String label, String[] args) {
        if(sender instanceof Player p){
            if (xBxTcore.getPlayerDataUnique(p.getUniqueId()) != null){
                xBxTcore.getPlayerDataUnique(p.getUniqueId()).clearGuestPlayers();
            }
            for (String name : args){
                if (Bukkit.getPlayer(name) != null){
                    if (!Objects.equals(Bukkit.getPlayer(name), p)){
                        xBxTcore.getPlayerDataUnique(p.getUniqueId()).addGuestPlayer(Objects.requireNonNull(Bukkit.getPlayer(name)));
                    }
                }else{
                    p.sendMessage(MasterMessageLocated(p, Messages.Others_WarningGetGuestPlayers));
                }
            }
            p.sendMessage(MasterMessageLocated(p,Messages.RequestDuel_Inv));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',   prefixConsole + Colorplayer + xBxTcore.getPlayerDataUnique(p.getUniqueId()).getGuestPlayers(false).size() + Colorinfo + " Catidad de ivn"));
        }else{
            plugin.messageOnlyPlayer();
        }

        return false;
    }
}
