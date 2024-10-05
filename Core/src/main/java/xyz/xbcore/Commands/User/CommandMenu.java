package xyz.xbcore.Commands.User;

import xyz.xbcommun.Command.BaseCommand;
import xyz.xbcore.Inventory.InventorySection;
import xyz.xbcore.Inventory.Models.InvetoryPlayer;
import xyz.xbcommun.Messages.Messages.Messages;
import xyz.xbcommun.Utils.UtilsGlobal;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMenu extends BaseCommand {

    public CommandMenu(){
        super("menu",
                "/menu",
                "xbxtcore.command.user",
                false,
                "Puedes ver todos los inventarios del servidor relacionados a xBxTCore");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof Player p){
            InventorySection.getInventoryMenu().OpenMenu(new InvetoryPlayer(p));
        }else{
            UtilsGlobal.sendMessage(sender, Messages.Generic_OnlyPlayers);
        }
    }
}
