package Plugin.Commands.User;

import Plugin.Messages.Enum.Messages;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandKill implements CommandExecutor {

    private final xBxTcore plugin;

    public CommandKill(xBxTcore plugin){
        this.plugin = plugin;
    }
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command cmd,@NotNull String label, String[] args) {
        if(sender instanceof Player p){
            if (xBxTcore.getWorldProtec().contains(p.getWorld())){
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecraft:kill " + p.getName());
            }else{
                p.sendMessage(xBxTcore.getMessageManager().MasterMessageLocated(p, Messages.InArea));
            }

        }else{
            plugin.messageOnlyPlayer();
        }

        return false;
    }
}
