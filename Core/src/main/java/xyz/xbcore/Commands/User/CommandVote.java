package xyz.xbcore.Commands.User;

import xyz.xbcommun.Command.BaseCommand;
import xyz.xbcore.Messages.Messages.Messages;
import xyz.xbcore.Utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import static xyz.xbcore.Messages.MessageManager.ColorLink;

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
        Utils.sendMessage(sender, Messages.Others_Vote);
        Utils.sendMessage(sender, ChatColor.translateAlternateColorCodes('&', ColorLink + "https://minecraft-mp.com/server-s334744"));
    }
}
