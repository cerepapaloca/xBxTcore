package xyz.xbcore.Commands.User;

import xyz.xbcommun.Command.BaseTabCommand;
import xyz.xbcore.File.FileManagerSection;
import xyz.xbcore.Messages.Messages.Messages;
import xyz.xbcore.Utils.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommandDelKit extends BaseTabCommand {

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

    @Override
    public List<String> onTab(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            List<String> namekits;
            FileManagerSection.getPlayerFileManager().loadNameKit(player.getUniqueId());
            namekits = FileManagerSection.getPlayerFileManager().nameskits;

            if (args.length == 1) {
                String currentArg = args[0].toLowerCase();
                return namekits.stream()
                        .filter(name -> name.startsWith(currentArg))
                        .collect(Collectors.toList());
            }

        }
        return null;
    }
}