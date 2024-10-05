package xyz.xbcore.Commands.User;

import xyz.xbcommun.Command.BaseCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import static xyz.xbcommun.Messages.MessageManager.*;

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
