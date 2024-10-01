package Plugin.Commands.User;

import Plugin.Commands.BaseCommand;
import Plugin.Messages.Messages.Messages;
import Plugin.xBxTcore;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static Plugin.Messages.MessageManager.ColorLink;
import static Plugin.Messages.MessageManager.MasterMessageLocated;

public class CommandShop extends BaseCommand {

    private final xBxTcore plugin;

    public CommandShop(xBxTcore plugin){
        super("shop",
                "/shop",
                "xbxtcore.command.user",
                false,
                "Te manda un link para ver la tienda",
                new String[]{"reload::Reload the config file", "version::Show the version of the plugin", "help::Shows the help for the plugin",
        });
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
