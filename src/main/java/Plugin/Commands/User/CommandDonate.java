package Plugin.Commands.User;

import Plugin.Enum.Messages;
import Plugin.xBxTcore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class CommandDonate implements CommandExecutor {

    private final xBxTcore plugin;

    public CommandDonate(xBxTcore plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@Nullable CommandSender commandSender,@Nullable Command command,@Nullable String s, String[] strings) {
        if(commandSender instanceof Player player){
            player.sendMessage(xBxTcore.getMessageManager().MasterMessage(player, Messages.Donate));
        }else{
            plugin.messageOnlyPlayer();
        }
        return false;
    }

}
