package Plugin.Commands.User;

import Plugin.Commands.BaseCommand;
import Plugin.File.FileManagerSection;
import Plugin.Messages.Messages.Messages;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import static Plugin.Messages.MessageManager.MasterMessageLocated;


public class CommandDelKit extends BaseCommand {

    private String namekit;
    private final ArrayList<ItemStack> items = new ArrayList<>();


    public CommandDelKit() {
        super(new String[]{"delkit", "dk"},
                "/delkit <Nombre Del Kit>",
                "xbxtcore.command.user",
                false,
                "borras un kit tengas guardado escribiendo el nombre del kit");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            if (args.length != 0) {
                namekit = args[0];
                GetItemInvetort(player);

            }else{
                Utils.sendMessage(sender , Messages.Kit_DelError);
            }
        }else{
            Utils.sendMessage(sender , Messages.Generic_OnlyPlayers);
        }
    }

    public void GetItemInvetort(Player player) {
        for (int i = 0; i < player.getInventory().getContents().length; i++) {
            ItemStack itemstack = player.getInventory().getItem(i);
            FileManagerSection.getPlayerFileManager().itemStacks.add(itemstack);
        }

        FileManagerSection.getPlayerFileManager().DeleteKitConfig(player.getUniqueId(), namekit);
        items.clear();
    }
}