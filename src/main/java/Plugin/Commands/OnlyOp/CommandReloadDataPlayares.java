package Plugin.Commands.OnlyOp;

import Plugin.File.PlayerData.PlayerfileManager;
import Plugin.Messages.Enum.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.Nullable;

import static Plugin.Messages.MessageManager.*;

public class CommandReloadDataPlayares implements CommandExecutor {

    public boolean onCommand(@Nullable CommandSender sender,@Nullable Command cmd,@Nullable String label, String[] args) {
        long time = System.currentTimeMillis();
        if(sender instanceof Player p){
            if(p.isOp()){
                PlayerfileManager.playesfiles.reloadConfigs();
            }else{
                p.sendMessage(MasterMessageLocated(p, Messages.Generic_NotOp));
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefixConsole + ColorSuccess
                        + "Se recargo los datos de los usuarios y tardo: " + (System.currentTimeMillis() - time)));
            }
        }else{
            PlayerfileManager.playesfiles.reloadConfigs();
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefixConsole + ColorSuccess
                    + "Se recargo los datos de los usuarios y tardo: " + (System.currentTimeMillis() - time)));
        }

        return false;
    }
}
