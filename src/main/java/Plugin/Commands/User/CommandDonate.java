package Plugin.Commands.User;

import Plugin.Messages.Messages.Messages;
import Plugin.xBxTcore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import static Plugin.Messages.MessageManager.MasterMessageLocated;

public class CommandDonate implements CommandExecutor {

    private final xBxTcore plugin;

    public CommandDonate(xBxTcore plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@Nullable CommandSender commandSender,@Nullable Command command,@Nullable String s, String[] strings) {
        if(commandSender instanceof Player player){
            player.sendMessage(MasterMessageLocated(player, Messages.Others_Donate));
        }else{
            plugin.messageOnlyPlayer();
        }
        return false;
    }

}
