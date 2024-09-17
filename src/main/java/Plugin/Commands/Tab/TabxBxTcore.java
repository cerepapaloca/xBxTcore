package Plugin.Commands.Tab;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TabxBxTcore implements TabCompleter {

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {

        List<String> argsTab = new ArrayList<>();
        argsTab.add("ip");
        argsTab.add("antibot");
        argsTab.add("ac");
        argsTab.add("ban");
        argsTab.add("chatmodetarion");
        List<String> argsIp = new ArrayList<>();
        argsIp.add("reload");
        argsIp.add("save");
        argsIp.add("update");
        argsIp.add("remove");
        List<String> argsAntiBot = new ArrayList<>();
        argsAntiBot.add("true");
        argsAntiBot.add("false");
        List<String> argsBan = new ArrayList<>();
        argsBan.add("<Player> <Contexto> <tiempo> <Razón>");

        if (args.length == 1) {
            String currentArg = args[0].toLowerCase();
            return argsTab.stream()
                    .filter(name -> name.startsWith(currentArg))
                    .collect(Collectors.toList());
        }
        switch (args[0].toLowerCase()){
            case "ip" -> {
                String currentArg = args[1].toLowerCase();
                return argsIp.stream()
                        .filter(name -> name.startsWith(currentArg))
                        .collect(Collectors.toList());
            }
            case "antibot", "chatmodetarion" -> {
                String currentArg = args[1].toLowerCase();
                return argsAntiBot.stream()
                        .filter(name -> name.startsWith(currentArg))
                        .collect(Collectors.toList());
            }
            case "ban" -> {
                if (args.length == 2) return null;
                return argsBan;
            }
        }

        return null;
    }
}
