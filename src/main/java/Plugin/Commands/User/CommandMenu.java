package Plugin.Commands.User;

import Plugin.Commands.BaseCommand;
import Plugin.Inventory.InventorySection;
import Plugin.Inventory.Models.InvetoryPlayer;
import Plugin.Messages.Messages.Messages;
import Plugin.Utils.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMenu extends BaseCommand {

    public CommandMenu(){
        super("help",
                "/help",
                "xbxtcore.command.user",
                false,
                "Puedes ver todos los inventarios del servidor relacionados a xBxTCore");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        /*if(sender instanceof Player p){
            InventorySection.getInventoryMenu().OpenMenu(new InvetoryPlayer(p));
        }else{
            Utils.sendMessage(sender, Messages.Generic_OnlyPlayers);
        }*/
    }
}
