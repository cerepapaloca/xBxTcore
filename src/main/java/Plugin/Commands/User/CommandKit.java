package Plugin.Commands.User;

import Plugin.Model.Player.InvetoryPlayer;
import Plugin.Enum.Messages;
import Plugin.xBxTcore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class CommandKit implements CommandExecutor {

    private final xBxTcore plugin;

    public CommandKit(xBxTcore plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@Nullable CommandSender commandSender,@Nullable Command command,@Nullable String s, String[] strings) {
        if(commandSender instanceof Player player){
            if(player.getLocation().getY() >= 30 && xBxTcore.getWorldProtec().contains(player.getWorld())){
                xBxTcore.getInventoryMenu().OpenMenuInvetory(new InvetoryPlayer(player));
            }else{
                player.sendMessage(xBxTcore.getMessageManager().MasterMessageLocated(player, Messages.InArea));
            }
        }else{
            plugin.messageOnlyPlayer();
        }
        return false;
    }

}
