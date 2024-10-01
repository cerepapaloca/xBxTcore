package Plugin.Commands.User;

import Plugin.Commands.BaseCommand;
import Plugin.Messages.Messages.Messages;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import static Plugin.Messages.MessageManager.ColorLink;
import static Plugin.Messages.MessageManager.MasterMessageLocated;

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
