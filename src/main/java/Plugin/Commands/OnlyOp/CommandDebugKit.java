package Plugin.Commands.OnlyOp;

import Plugin.Commands.BaseTabCommand;
import Plugin.File.FileManagerSection;
import Plugin.Messages.Messages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static Plugin.Messages.MessageManager.MasterMessageLocated;

public class CommandDebugKit extends BaseTabCommand {

    private final ArrayList<ItemStack> items = new ArrayList<>();;
    private String namekit;
    private Material material;

    public CommandDebugKit(){
        super("debugkit",
                "/deugkit",
                "xbxtcore.command.debugkit",
                true,
                "sirve para investigar el kit de otro usuarios");
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof Player p){
            if(p.isOp()){
                material = Material.getMaterial(args[1].toUpperCase());
                namekit = args[0];
                if(args.length == 3){
                    if(args[0].equalsIgnoreCase("view")){
                        UUID uuid = Objects.requireNonNull(Bukkit.getPlayer(args[1])).getUniqueId();
                        FileManagerSection.getPlayerFileManager().loadKit(uuid, args[2], null, p);
                    }else{
                        if (args[2].equalsIgnoreCase("add")){
                            run(p);
                        }else if(args[2].equalsIgnoreCase("remove")){
                            FileManagerSection.getPlayerFileManager().DeleteKitConfig(UUID.fromString("00000000-0000-0000-0000-000000000000"), namekit);
                        }
                    }
                }
            }else{
                p.sendMessage(MasterMessageLocated(p, Messages.Generic_NotOp));
            }
        }
    }

    public void run (Player player){
        items.clear();
        for(int i = 0; i<player.getInventory().getContents().length;){
            ItemStack itemstack = player.getInventory().getItem(i);
            items.add(itemstack);
            i++;
        }
        if (material != null){
            FileManagerSection.getPlayerFileManager().SaveKit(UUID.fromString("00000000-0000-0000-0000-000000000000"), namekit, material, items);
        }else{
            FileManagerSection.getPlayerFileManager().SaveKit(UUID.fromString("00000000-0000-0000-0000-000000000000"), namekit, Material.WHITE_SHULKER_BOX, items);
        }
        items.clear();
        material = null;
    }

    @Override
    public List<String> onTab(CommandSender sender, String[] args) {
        List<String> blockNames = new ArrayList<>();
        List<String> onlyOp = new ArrayList<>();
        onlyOp.add("add");
        onlyOp.add("remove");
        for (Material material : Material.values()) {
            blockNames.add(material.name().toLowerCase());
        }

        if (sender.hasPermission("xBxTpvp.Op") && (args.length > 0)) {
            if (args[0].equals("view")) {
                if (args.length == 3) {
                    List<String> namekits;
                    Player player = Bukkit.getPlayer(args[1]);
                    assert player != null;
                    FileManagerSection.getPlayerFileManager().loadNameKit(player.getUniqueId());
                    namekits = FileManagerSection.getPlayerFileManager().nameskits;
                    String currentArg = args[2].toLowerCase();
                    return namekits.stream()
                            .filter(name -> name.startsWith(currentArg))
                            .collect(Collectors.toList());
                }
            }else{
                if (args.length == 3) {
                    String currentArg = args[2].toLowerCase();
                    return onlyOp.stream()
                            .filter(name -> name.startsWith(currentArg))
                            .collect(Collectors.toList());
                }else if (args.length == 2) {
                    String currentArg = args[1].toLowerCase();
                    return blockNames.stream()
                            .filter(name -> name.startsWith(currentArg))
                            .collect(Collectors.toList());
                }else if (args.length == 1){
                    List<String> namekits;
                    FileManagerSection.getPlayerFileManager().loadNameKit(UUID.fromString("00000000-0000-0000-0000-000000000000"));
                    namekits = FileManagerSection.getPlayerFileManager().nameskits;
                    String currentArg = args[0].toLowerCase();
                    return namekits.stream()
                            .filter(name -> name.startsWith(currentArg))
                            .collect(Collectors.toList());
                }
            }
        }
        return null;
    }

}
