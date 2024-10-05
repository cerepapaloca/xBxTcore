package xyz.xbcore.Commands.User;

import xyz.xbcommun.Command.BaseCommand;
import xyz.xbcommun.Messages.Messages.Messages;
import xyz.xbcore.xBxTcore;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static xyz.xbcommun.Messages.MessageManager.ColorLink;
import static xyz.xbcommun.Messages.MessageManager.MasterMessageLocated;

public class CommandShop extends BaseCommand {

    private final xBxTcore plugin;

    public CommandShop(xBxTcore plugin){
        super("shop",
                "/shop",
                "xbxtcore.command.user",
                false,
                "Te manda un link para ver la tienda");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof Player player){
            player.sendMessage(MasterMessageLocated(player, Messages.Reward_StoreLink));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', ColorLink + "https://xbxtshop.tebex.io/"));
        }else{
            plugin.messageOnlyPlayer();
        }
    }
}
