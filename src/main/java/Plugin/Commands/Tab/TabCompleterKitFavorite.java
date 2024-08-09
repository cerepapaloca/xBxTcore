package Plugin.Commands.Tab;

import Plugin.xBxTcore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TabCompleterKitFavorite implements TabCompleter {

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            xBxTcore.getPlayerFileManager().loadNameKit(player.getUniqueId());
            List<String> namekitsall = new ArrayList<>(xBxTcore.getPlayerFileManager().nameskitsboth);
            if (args.length == 1) {
                String currentArg = args[0].toLowerCase();
                return namekitsall.stream()
                        .filter(name -> name.toLowerCase().contains(currentArg))
                        .collect(Collectors.toList());
            }
        }
        return null;
    }
}
