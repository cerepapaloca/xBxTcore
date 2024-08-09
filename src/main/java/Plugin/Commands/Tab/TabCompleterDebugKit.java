package Plugin.Commands.Tab;

import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TabCompleterDebugKit implements TabCompleter {

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender,@NotNull Command command,@NotNull String alias, String[] args) {

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
                    xBxTcore.getPlayerFileManager().loadNameKit(player.getUniqueId());
                    namekits = xBxTcore.getPlayerFileManager().nameskits;
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
                xBxTcore.getPlayerFileManager().loadNameKit(UUID.fromString("00000000-0000-0000-0000-000000000000"));
                namekits = xBxTcore.getPlayerFileManager().nameskits;
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
