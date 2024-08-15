package Plugin.Commands.Tab;

import Plugin.Enum.MapsDuel;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TabCompleterDuel implements TabCompleter {

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {

        List<String> worldtype = new ArrayList<>();
        for(MapsDuel s : MapsDuel.values()){
            worldtype.add(s.name());
        }

        List<String> player = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers()){
            player.add(p.getName());
        }
        player.add("yes");
        player.add("deny");
        player.add("fast_inv");

        if (args.length == 1){
            String currentArg = args[0].toLowerCase();
            return player.stream()
                    .filter(name -> name.startsWith(currentArg))
                    .collect(Collectors.toList());
        }else if (args.length == 2){
            if (!args[0].equalsIgnoreCase("yes") && !args[0].equalsIgnoreCase("deny")) {
                String currentArg = args[1].toLowerCase();
                return worldtype.stream()
                        .filter(name -> name.startsWith(currentArg))
                        .collect(Collectors.toList());
            }
        }
        return null;
    }
}
