package Plugin.Commands.User;

import Plugin.Enum.Messages;
import Plugin.xBxTcore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;


public class CommandDelKit implements CommandExecutor {

    private final xBxTcore plugin;
    private String namekit;
    private final ArrayList<ItemStack> items = new ArrayList<>();


    public CommandDelKit(xBxTcore plugin) {
        this.plugin = plugin;
        }

    @Override
    public boolean onCommand(@Nullable CommandSender commandSender,@Nullable Command command,@Nullable String s, String[] args) {
        if (commandSender instanceof Player player) {
            if (args.length != 0) {
                namekit = args[0];
                GetItemInvetort(player);

            }else{
                player.sendMessage(xBxTcore.getMessageManager().MasterMessage(player, Messages.DelError));
            }
        }else{
            plugin.messageOnlyPlayer();
        }
        return false;
    }

    public void GetItemInvetort(Player player) {
        for (int i = 0; i < player.getInventory().getContents().length; i++) {
            ItemStack itemstack = player.getInventory().getItem(i);
            xBxTcore.getPlayerFileManager().itemStacks.add(itemstack);
        }

        xBxTcore.getPlayerFileManager().DeleteKitConfig(player.getUniqueId(), namekit);
        items.clear();
    }
}