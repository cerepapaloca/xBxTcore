package Plugin.Commands.User;

import Plugin.Messages.Enum.Messages;
import Plugin.Messages.MessageManager;
import Plugin.Utils.Utils;
import Plugin.Utils.UtilsMain;
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

import static Plugin.File.FileManagerSection.getPlayerFileManager;
import static Plugin.Messages.MessageManager.MasterMessageLocated;
import static Plugin.Utils.Utils.AntiSpam;

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
            if (player.getWorld().getName().equals(xBxTcore.worldBoxPvp)) {
                player.sendMessage(MessageManager.MasterMessageLocated(player, Messages.InArea));
                return false;
            }

            if (args.length == 1) {
                namekit = args[0];
                GetItemInvetort(player);
            } else if (args.length == 2) {
                material = Material.getMaterial(args[1].toUpperCase());
                namekit = args[0];
                GetItemInvetort(player);
            } else if (args.length == 0) {
                player.sendMessage(MasterMessageLocated(player, Messages.SaveError));
            }
        }else{
            plugin.messageOnlyPlayer();
        }
        return false;
    }

    public void GetItemInvetort(Player player){
        items.clear();
        if (namekit.contains(".")) {
            player.sendMessage(MasterMessageLocated(player, Messages.SaveErrorPunto));
            return;
        }

        for(int i = 0; i<player.getInventory().getContents().length;){
            ItemStack itemstack = player.getInventory().getItem(i);
            items.add(itemstack);
            i++;
        }
        getPlayerFileManager().SaveKit(player.getUniqueId(), namekit, Objects.requireNonNullElse(material, Material.WHITE_SHULKER_BOX), items);
        items.clear();
        material = null;
        AntiSpam(player, Messages.SpamCommand, plugin);
    }
}