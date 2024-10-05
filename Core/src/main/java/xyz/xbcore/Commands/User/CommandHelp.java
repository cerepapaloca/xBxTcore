package xyz.xbcore.Commands.User;

import xyz.xbcommun.Command.BaseCommand;
import xyz.xbcore.Inventory.InventorySection;
import xyz.xbcore.Inventory.Models.InvetoryPlayer;
import xyz.xbcommun.Messages.Messages.Messages;
import xyz.xbcommun.Utils.UtilsGlobal;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHelp extends BaseCommand {

    public CommandHelp(){
        super("help",
                "/help",
                "xbxtcore.command.user",
                false,
                "con este comando se abrirá un inventario con toda la información del servidor");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof Player p){
            InventorySection.getInventoryMenu().OpenMenuHelp(new InvetoryPlayer(p));
        }else{
            UtilsGlobal.sendMessage(sender, Messages.Generic_OnlyPlayers);
        }
    }
}
