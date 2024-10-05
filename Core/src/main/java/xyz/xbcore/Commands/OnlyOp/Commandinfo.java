package xyz.xbcore.Commands.OnlyOp;

import xyz.xbcommun.Command.BaseCommand;
import xyz.xbcore.xBxTcore;
import org.bukkit.command.CommandSender;

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
