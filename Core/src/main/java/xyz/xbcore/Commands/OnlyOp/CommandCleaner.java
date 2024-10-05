package xyz.xbcore.Commands.OnlyOp;

import xyz.xbcommun.Command.BaseTabCommand;
import xyz.xbcore.Environment.EnvironmentsSection;
import xyz.xbcommun.Messages.Messages.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

import static xyz.xbcommun.Messages.MessageManager.BroadcastMessage;
import static xyz.xbcommun.Messages.MessageManager.MasterMessageLocated;

public class CommandCleaner extends BaseTabCommand {

    public CommandCleaner() {
        super("cleaner",
                "/cleaner",
                "xbxtcore.command.cleaner",
                true,
                "");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof Player p){
            if(p.isOp()){
                EnvironmentsSection.getCleaner().clearArea(p.getWorld().getName());
                BroadcastMessage(Messages.Others_CleanerExecuted);
            }else{
                p.sendMessage(MasterMessageLocated(p, Messages.Generic_NotOp));
            }
        }else{
            EnvironmentsSection.getCleaner().clearArea("lobby");
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, String[] args) {
        return List.of();
    }

}
