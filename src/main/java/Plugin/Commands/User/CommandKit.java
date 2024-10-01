package Plugin.Commands.User;

import Plugin.Commands.BaseCommand;
import Plugin.Inventory.InventorySection;
import Plugin.Inventory.Models.InvetoryPlayer;
import Plugin.Messages.Messages.Messages;
import Plugin.Messages.MessageManager;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import javax.print.attribute.ResolutionSyntax;

import static Plugin.Messages.MessageManager.MasterMessageLocated;

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
                    Utils.sendMessage(sender, Messages.Generic_InArea);
                    return ;
                }
                InventorySection.getInventoryMenu().OpenMenuInvetory(new InvetoryPlayer(player));
            }else{
                Utils.sendMessage(sender, Messages.Generic_InArea);
            }
        }else{
            Utils.sendMessage(sender, Messages.Generic_OnlyPlayers);
        }
    }
}
