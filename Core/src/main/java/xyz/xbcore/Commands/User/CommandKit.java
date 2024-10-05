package xyz.xbcore.Commands.User;

import xyz.xbcommun.Command.BaseCommand;
import xyz.xbcore.Inventory.InventorySection;
import xyz.xbcore.Inventory.Models.InvetoryPlayer;
import xyz.xbcommun.Messages.Messages.Messages;
import xyz.xbcommun.Utils.UtilsGlobal;
import xyz.xbcore.xBxTcore;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandKit extends BaseCommand {

    public CommandKit(){
        super("kit",
                "/kit",
                "xbxtcore.command.user",
                false,
                "se abrirá un inventario donde puedes seleccionar tu kit");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof Player player){
            if(player.getLocation().getY() >= 30 && xBxTcore.getWorldProtec().contains(player.getWorld())){
                if (player.getWorld().getName().equals(xBxTcore.worldBoxPvp)) {
                    UtilsGlobal.sendMessage(sender, Messages.Generic_InArea);
                    return ;
                }
                InventorySection.getInventoryMenu().OpenMenuKit(new InvetoryPlayer(player));
            }else{
                UtilsGlobal.sendMessage(sender, Messages.Generic_InArea);
            }
        }else{
            UtilsGlobal.sendMessage(sender, Messages.Generic_OnlyPlayers);
        }
    }
}
