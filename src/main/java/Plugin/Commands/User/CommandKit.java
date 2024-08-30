package Plugin.Commands.User;

import Plugin.Inventory.InventorySection;
import Plugin.Inventory.Models.InvetoryPlayer;
import Plugin.Messages.Enum.Messages;
import Plugin.xBxTcore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import static Plugin.Messages.MessageManager.MasterMessageLocated;

public class CommandKit implements CommandExecutor {

    private final xBxTcore plugin;

    public CommandKit(xBxTcore plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@Nullable CommandSender commandSender,@Nullable Command command,@Nullable String s, String[] strings) {
        if(commandSender instanceof Player player){
            if(player.getLocation().getY() >= 30 && xBxTcore.getWorldProtec().contains(player.getWorld())){
                InventorySection.getInventoryMenu().OpenMenuInvetory(new InvetoryPlayer(player));
            }else{
                player.sendMessage(MasterMessageLocated(player, Messages.InArea));
            }
        }else{
            plugin.messageOnlyPlayer();
        }
        return false;
    }

}
