package Plugin.Commands.OnlyOp;

import Plugin.Messages.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class CommandPing implements CommandExecutor {

    public boolean onCommand(@Nullable CommandSender sender,@Nullable Command cmd,@Nullable String label, String[] args) {
        if (sender instanceof Player p && !p.isOp()) {
            return false;
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.getPing();
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.prefix + MessageManager.Colorplayer + p.getName() + MessageManager.Colorinfo + " Tiene "
                    + MessageManager.Colorplayer + p.getPing()));
        }
        return true;
    }
}
