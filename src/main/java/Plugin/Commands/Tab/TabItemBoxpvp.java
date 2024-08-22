package Plugin.Commands.Tab;

import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TabItemBoxpvp implements TabCompleter {

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {

        List<String> nameworlds = new ArrayList<>();
        nameworlds.add("*");
        nameworlds.add("coins");
        nameworlds.add("coin_normal");
        nameworlds.add("coin_compact");
        nameworlds.add("helmets");
        nameworlds.add("elytra");
        nameworlds.add("leggings");
        nameworlds.add("boots");
        nameworlds.add("tier");

        if (args.length == 1) {
            String currentArg = args[0].toLowerCase();
            return nameworlds.stream()
                    .filter(name -> name.startsWith(currentArg))
                    .collect(Collectors.toList());
        }

        return null;
    }
}
