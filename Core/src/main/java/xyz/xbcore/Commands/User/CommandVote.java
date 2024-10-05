package xyz.xbcore.Commands.User;

import xyz.xbcommun.Command.BaseCommand;
import xyz.xbcommun.Messages.Messages.Messages;
import xyz.xbcommun.Utils.UtilsGlobal;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import static xyz.xbcommun.Messages.MessageManager.ColorLink;

public class CommandVote extends BaseCommand {

    public CommandVote() {
        super("vote",
                "/vote",
                "xbxtcore.command.user",
                false,
                "puedes ver la página donde se vota el servidor para obtener recompensa");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        UtilsGlobal.sendMessage(sender, Messages.Others_Vote);
        UtilsGlobal.sendMessage(sender, ChatColor.translateAlternateColorCodes('&', ColorLink + "https://minecraft-mp.com/server-s334744"));
    }
}
