package Plugin.Commands.User;

import Plugin.Commands.BaseCommand;
import Plugin.Messages.Messages.Messages;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import static Plugin.Messages.MessageManager.MasterMessageLocated;

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
