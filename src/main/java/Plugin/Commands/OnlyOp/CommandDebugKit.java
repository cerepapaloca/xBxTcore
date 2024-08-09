package Plugin.Commands.OnlyOp;

import Plugin.Model.Messages;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class CommandDebugKit implements CommandExecutor {

    private final ArrayList<ItemStack> items;
    private String namekit;
    private Material material;

    public CommandDebugKit(){
        items = new ArrayList<>();
    }
    public boolean onCommand(@Nullable CommandSender sender,@Nullable Command cmd,@Nullable String label, String[] args) {
        if(sender instanceof Player p){
            if(p.isOp()){
                material = Material.getMaterial(args[1].toUpperCase());
                namekit = args[0];
                if(args.length == 3){
                    if(args[0].equalsIgnoreCase("view")){
                        UUID uuid = Objects.requireNonNull(Bukkit.getPlayer(args[1])).getUniqueId();
                        xBxTcore.getPlayerFileManager().loadKit(uuid, args[2], null, p);
                    }else{
                        if (args[2].equalsIgnoreCase("add")){
                            run(args, p);
                        }else if(args[2].equalsIgnoreCase("remove")){
                            xBxTcore.getPlayerFileManager().DeleteKitConfig(UUID.fromString("00000000-0000-0000-0000-000000000000"), namekit);
                        }
                    }
                }
            }else{
                p.sendMessage(xBxTcore.getMessageManager().MasterMessage(p, Messages.NotOp));
            }
        }

        return false;
    }

    public void run (String[] args, Player player){
        items.clear();
        for(int i = 0; i<player.getInventory().getContents().length;){
            ItemStack itemstack = player.getInventory().getItem(i);
            items.add(itemstack);
            i++;
        }
        if (material != null){
            xBxTcore.getPlayerFileManager().SaveKit(UUID.fromString("00000000-0000-0000-0000-000000000000"), namekit, material, items);
            items.clear();
            material = null;
        }else{
            xBxTcore.getPlayerFileManager().SaveKit(UUID.fromString("00000000-0000-0000-0000-000000000000"), namekit, Material.WHITE_SHULKER_BOX, items);
            items.clear();
            material = null;
        }
    }
}
