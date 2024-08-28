package Plugin.Commands.User;

import Plugin.xBxTcore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import static Plugin.Messages.MessageManager.*;

public class CommandDiscord implements CommandExecutor {

    private final xBxTcore plugin;

    public CommandDiscord(xBxTcore plugin){
        this.plugin = plugin;
    }
    public boolean onCommand(@Nullable CommandSender sender,@Nullable Command cmd,@Nullable String label, String[] args) {
        if(sender instanceof Player p){
            p.sendMessage(ChatColor.translateAlternateColorCodes( '&',prefix + Colorinfo + "Discord:&a&ohttps://discord.gg/QYBwEFvnsG"));
            return true;
        }else{
            plugin.messageOnlyPlayer();
            return false;
        }
    }
}
