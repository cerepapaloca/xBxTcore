package Plugin.Commands.OnlyOp;

import Plugin.Enum.Messages;
import Plugin.xBxTcore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.Nullable;

public class Commandinfo implements CommandExecutor {

    private final xBxTcore plugin;

    public Commandinfo(xBxTcore plugin){
        this.plugin = plugin;
    }
    public boolean onCommand(@Nullable CommandSender sender,@Nullable Command cmd,@Nullable String label, String[] args) {
        if(sender instanceof Player p){
            if(p.isOp()){
                plugin.info();
            }else{
                p.sendMessage(xBxTcore.getMessageManager().MasterMessage(p, Messages.NotOp));
            }
        }else{
            plugin.info();
        }

        return false;
    }
}
