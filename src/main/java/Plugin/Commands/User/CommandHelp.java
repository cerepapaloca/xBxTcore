package Plugin.Commands.User;

import Plugin.Inventory.InventorySection;
import Plugin.Inventory.Models.InvetoryPlayer;
import Plugin.xBxTcore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandHelp implements CommandExecutor {

    private final xBxTcore plugin;

    public CommandHelp(xBxTcore plugin){
        this.plugin = plugin;
    }
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command cmd,@NotNull String label, String[] args) {
        if(sender instanceof Player p){
            InventorySection.getInventoryMenu().OpenHelp(new InvetoryPlayer(p));
        }else{
            plugin.messageOnlyPlayer();
        }

        return false;
    }
}
