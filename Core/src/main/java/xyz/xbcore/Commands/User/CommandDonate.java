package xyz.xbcore.Commands.User;

import xyz.xbcommun.Command.BaseCommand;
import xyz.xbcore.Messages.Messages.Messages;
import xyz.xbcore.Utils.Utils;
import org.bukkit.command.CommandSender;

public class CommandDonate extends BaseCommand {

    public CommandDonate(){
        super("donate",
                "/donate",
                "xbxtcore.command.user",
                false,
                "te enviara mi paypal por si quieres aportar");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Utils.sendMessage(sender, Messages.Others_Donate);
    }
}
