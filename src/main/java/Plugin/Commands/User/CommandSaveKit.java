package Plugin.Commands.User;

import Plugin.Messages.Enum.Messages;
import Plugin.xBxTcore;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Objects;

public class CommandSaveKit implements CommandExecutor {

    private final xBxTcore plugin;
    private final ArrayList<ItemStack> items;
    private String namekit;
    private Material material;

    public CommandSaveKit(xBxTcore plugin){
        this.plugin = plugin;
        this.items = new ArrayList<>();
    }

    @Override
    public boolean onCommand(@Nullable CommandSender commandSender,@Nullable Command command,@Nullable String s, String[] args) {

        if(commandSender instanceof Player player){
            if (args.length == 1) {
                namekit = args[0];
                GetItemInvetort(player);
            } else if (args.length == 2) {
                material = Material.getMaterial(args[1].toUpperCase());
                namekit = args[0];
                GetItemInvetort(player);
            } else if (args.length == 0) {
                player.sendMessage(xBxTcore.getMessageManager().MasterMessageLocated(player, Messages.SaveError));
            }
        }else{
            plugin.messageOnlyPlayer();
        }
        return false;
    }

    public void GetItemInvetort(Player player){
        items.clear();
        if (namekit.contains(".")) {
            player.sendMessage(xBxTcore.getMessageManager().MasterMessageLocated(player, Messages.SaveErrorPunto));
            return;
        }

        for(int i = 0; i<player.getInventory().getContents().length;){
            ItemStack itemstack = player.getInventory().getItem(i);
            items.add(itemstack);
            i++;
        }
        xBxTcore.getPlayerFileManager().SaveKit(player.getUniqueId(), namekit, Objects.requireNonNullElse(material, Material.WHITE_SHULKER_BOX), items);
        items.clear();
        material = null;
        xBxTcore.getTools().AntiSpam(player, Messages.SpamCommand);
    }
}