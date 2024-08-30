package Plugin.Commands.OnlyOp;

import Plugin.Environments.EnvironmentsSection;
import Plugin.Messages.Enum.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.Nullable;

import static Plugin.Messages.MessageManager.BroadcastMessage;
import static Plugin.Messages.MessageManager.MasterMessageLocated;

public class CommandCleaner implements CommandExecutor {

    public boolean onCommand(@Nullable CommandSender sender,@Nullable Command cmd,@Nullable String label, String[] args) {
        if(sender instanceof Player p){
            if(p.isOp()){
                EnvironmentsSection.getCleaner().clearArea(p.getWorld().getName());
                BroadcastMessage(Messages.CleanerExecuted);
            }else{
                p.sendMessage(MasterMessageLocated(p, Messages.NotOp));
            }
        }else{
            EnvironmentsSection.getCleaner().clearArea("lobby");
        }

        return false;
    }
}
