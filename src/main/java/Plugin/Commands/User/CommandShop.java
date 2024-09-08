package Plugin.Commands.User;

import Plugin.Messages.Enum.Messages;
import Plugin.xBxTcore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import static Plugin.Messages.MessageManager.ColorLink;
import static Plugin.Messages.MessageManager.MasterMessageLocated;

public class CommandShop implements CommandExecutor {

    private final xBxTcore plugin;

    public CommandShop(xBxTcore plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@Nullable CommandSender commandSender,@Nullable Command command,@Nullable String s, String[] strings) {
        if(commandSender instanceof Player player){
            player.sendMessage(MasterMessageLocated(player, Messages.Reward_StoreLink));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', ColorLink + "https://xbxtshop.tebex.io/"));
        }else{
            plugin.messageOnlyPlayer();
        }
        return false;
    }

}
