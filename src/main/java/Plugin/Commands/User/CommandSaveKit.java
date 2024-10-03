package Plugin.Commands.User;

import Plugin.Commands.BaseTabCommand;
import Plugin.File.FileManagerSection;
import Plugin.Messages.Messages.Messages;
import Plugin.Messages.MessageManager;
import Plugin.xBxTcore;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static Plugin.Messages.MessageManager.MasterMessageLocated;
import static Plugin.Utils.Utils.AntiSpam;

public class CommandSaveKit extends BaseTabCommand {

    private final xBxTcore plugin;
    private final ArrayList<ItemStack> items = new ArrayList<>();
    private String namekit;
    private Material material;

    public CommandSaveKit(xBxTcore plugin){
        super(new String[]{"savekit","sk"},
                "/savekit <Nombre del Kit> <!item de minecraft>",
                "xbxtcore.command.user",
                false,
                "puedes gurdar tu inventario en un kit para que más tar de lo puedas usar");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof Player player){
            if (player.getWorld().getName().equals(xBxTcore.worldBoxPvp)) {
                player.sendMessage(MessageManager.MasterMessageLocated(player, Messages.Generic_InArea));
                return;
            }

            if (args.length == 1) {
                namekit = args[0];
                GetItemInvetort(player);
            } else if (args.length == 2) {
                material = Material.getMaterial(args[1].toUpperCase());
                namekit = args[0];
                GetItemInvetort(player);
            } else if (args.length == 0) {
                player.sendMessage(MasterMessageLocated(player, Messages.Kit_SaveError));
            }
        }else{
            plugin.messageOnlyPlayer();
        }
    }

    private void GetItemInvetort(Player player){
        items.clear();
        if (namekit.contains(".")) {
            player.sendMessage(MasterMessageLocated(player, Messages.Kit_SaveErrorPunto));
            return;
        }

        for(int i = 0; i<player.getInventory().getContents().length;){
            ItemStack itemstack = player.getInventory().getItem(i);
            items.add(itemstack);
            i++;
        }
        FileManagerSection.getPlayerFileManager().SaveKit(player.getUniqueId(), namekit, Objects.requireNonNullElse(material, Material.WHITE_SHULKER_BOX), items);
        items.clear();
        material = null;
        AntiSpam(player, Messages.Kick_SpamCommand, plugin);
    }

    @Override
    public List<String> onTab(CommandSender sender, String[] args) {
        List<String> blockNames = new ArrayList<>();
        for (Material material : Material.values()) {
            blockNames.add(material.name().toLowerCase());
        }

        if (args.length == 2) {
            String currentArg = args[1].toLowerCase();
            return blockNames.stream()
                    .filter(name -> name.startsWith(currentArg))
                    .collect(Collectors.toList());
        }

        return null;
    }
}