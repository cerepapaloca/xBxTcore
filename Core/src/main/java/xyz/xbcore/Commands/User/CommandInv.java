package xyz.xbcore.Commands.User;

import xyz.xbcommun.Command.BaseCommand;
import xyz.xbcore.Messages.Messages.Messages;
import xyz.xbcore.Utils.Utils;
import xyz.xbcore.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

import static xyz.xbcore.Messages.MessageManager.*;

public class CommandInv extends BaseCommand {

    public CommandInv(){
        super("inv",
                "/inv <nombre del jugador> <nombre del jugador>...",
                "xbxtcore.command.user",
                false,
                "es un comando que se usa para agrega a los jugadores a una lista de invitación para un duelo");
    }
    @Override
    public void execute(CommandSender sender, String[] args) {
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
                    Utils.sendMessage(sender, Messages.Others_WarningGetGuestPlayers);
                }
            }
            Utils.sendMessage(sender, Messages.RequestDuel_Inv);
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',   prefixConsole + Colorplayer + xBxTcore.getPlayerDataUnique(p.getUniqueId()).getGuestPlayers(false).size() + Colorinfo + " Catidad de ivn"));
        }else{
            Utils.sendMessage(sender, Messages.Generic_OnlyPlayers);
        }
    }
}
