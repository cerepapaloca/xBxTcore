package Plugin.Commands.User;

import Plugin.Commands.BaseCommand;
import Plugin.Inventory.InventorySection;
import Plugin.Inventory.Models.InvetoryPlayer;
import Plugin.Messages.Messages.Messages;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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
            InventorySection.getInventoryMenu().OpenHelp(new InvetoryPlayer(p));
        }else{
            Utils.sendMessage(sender, Messages.Generic_OnlyPlayers);
        }
    }
}
