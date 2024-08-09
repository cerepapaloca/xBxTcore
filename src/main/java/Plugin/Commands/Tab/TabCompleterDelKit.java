package Plugin.Commands.Tab;

import Plugin.xBxTcore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class TabCompleterDelKit implements TabCompleter {

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender,@NotNull Command command,@NotNull String alias, String[] args) {
        if (sender instanceof Player player) {
            List<String> namekits;
            xBxTcore.getPlayerFileManager().loadNameKit(player.getUniqueId());
            namekits = xBxTcore.getPlayerFileManager().nameskits;

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
