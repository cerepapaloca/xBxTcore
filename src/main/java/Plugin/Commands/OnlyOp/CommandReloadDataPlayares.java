package Plugin.Commands.OnlyOp;

import Plugin.Managers.PlayerfileManager;
import Plugin.Enum.Messages;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.Nullable;

import static Plugin.Managers.MessageManager.*;

public class CommandReloadDataPlayares implements CommandExecutor {

    private final xBxTcore plugin;

    public CommandReloadDataPlayares(xBxTcore plugin){
        this.plugin = plugin;
    }
    public boolean onCommand(@Nullable CommandSender sender,@Nullable Command cmd,@Nullable String label, String[] args) {
        if(sender instanceof Player p){
            if(p.isOp()){
                PlayerfileManager.playesfiles.reloadConfigs();
            }else{
                p.sendMessage(xBxTcore.getMessageManager().MasterMessageLocated(p, Messages.NotOp));
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefixConsole + ColorSuccess + "Se recargo los datos de los usuarios"));
            }
        }else{
            PlayerfileManager.playesfiles.reloadConfigs();
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefixConsole + ColorSuccess + "Se recargo los datos de los usuarios"));
        }

        return false;
    }
}
