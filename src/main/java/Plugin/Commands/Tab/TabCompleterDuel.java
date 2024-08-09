package Plugin.Commands.Tab;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TabCompleterDuel implements TabCompleter {

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {

        List<String> worldtype = new ArrayList<>();
        worldtype.add("bedrock");
        worldtype.add("flat_bedrock");
        worldtype.add("flat_world");

        if (args.length == 2) {
            String currentArg = args[1].toLowerCase();
            return worldtype.stream()
                    .filter(name -> name.startsWith(currentArg))
                    .collect(Collectors.toList());
        }

        return null;
    }
}
