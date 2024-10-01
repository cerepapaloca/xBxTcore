package Plugin.Commands.User;

import Plugin.Commands.BaseCommand;
import Plugin.xBxTcore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static Plugin.Messages.MessageManager.*;

public class CommandDiscord extends BaseCommand {

    public CommandDiscord(){
        super("discord",
                "/discord",
                "xbxtcore.command.user",
                false,
                "te envia un lick de discord");
    }

    @Override
    public void execute(@NotNull CommandSender sender, String[] args) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes( '&',prefix + ColorLink + "Discord:&a&ohttps://discord.gg/QYBwEFvnsG"));
    }
}
