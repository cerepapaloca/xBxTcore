package Plugin.Commands.Tab;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TabCompleterSaveKit implements TabCompleter {

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {

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
