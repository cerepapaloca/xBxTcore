package Plugin.Commands.OnlyOp;

import Plugin.Model.Messages;
import Plugin.xBxTcore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.Nullable;

public class CommandCleaner implements CommandExecutor {

    public boolean onCommand(@Nullable CommandSender sender,@Nullable Command cmd,@Nullable String label, String[] args) {
        if(sender instanceof Player p){
            if(p.isOp()){
                xBxTcore.getCleaner().clearArea(p.getWorld().getName());
                xBxTcore.getMessageManager().BroadcastMessage(Messages.CleanerExecuted);
            }else{
                p.sendMessage(xBxTcore.getMessageManager().MasterMessage(p, Messages.NotOp));
            }
        }else{
            xBxTcore.getCleaner().clearArea("lobby");
        }

        return false;
    }
}
