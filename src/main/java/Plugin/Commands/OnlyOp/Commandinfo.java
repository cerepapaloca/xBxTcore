package Plugin.Commands.OnlyOp;

import Plugin.Commands.BaseCommand;
import Plugin.Commands.BaseTabCommand;
import Plugin.Messages.Messages.Messages;
import Plugin.xBxTcore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.Nullable;

import static Plugin.Messages.MessageManager.MasterMessageLocated;

public class Commandinfo extends BaseCommand {

    private final xBxTcore plugin;

    public Commandinfo(xBxTcore plugin){
        super("info",
                "/info",
                "xbxtcore.command.info",
                true,
                "Da información básica del estado del servidor");
        this.plugin = plugin;
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        plugin.info();
    }
}
