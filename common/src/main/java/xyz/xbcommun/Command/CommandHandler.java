package xyz.xbcommun.Command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class CommandHandler implements TabExecutor {
    private final HashSet<BaseCommand> commands = new HashSet<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        for (BaseCommand command : commands) {
            if (!isName(cmd, command)) continue;
            boolean hasPermission = false;
            for (String permission : command.getPermissions()) {
                if (sender.hasPermission(permission)) {
                    hasPermission = true;
                    break;
                }
            }

            if (command.getOnlyOP()) {
                if (sender.isOp()){
                    command.execute(sender, args);
                } else {
                    if (sender instanceof Player p) {
                        Bukkit.getConsoleSender().sendMessage("Comando - No permiso");
                    }
                }
                break;
            }

            if (hasPermission) {
                command.execute(sender, args);
            }else{
                Bukkit.getConsoleSender().sendMessage("Comando - No permiso");
            }
            break;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String alias, String[] args) {
        for (BaseCommand command : commands) {
            isName(cmd, command);
            if (!isName(cmd, command)) continue;
            if (command instanceof BaseTabCommand tabCommand)return tabCommand.onTab(sender, args);
            if (command.getSubCommands() != null && args.length == 1) {
                return Arrays.stream(command.getSubCommands()).map(s -> s.split("::")[0]).filter(s -> s.startsWith(args[0].toLowerCase())).collect(Collectors.toList());
            } else if (args.length > 1) {
                return Bukkit.getOnlinePlayers().stream().map(Player::getName).filter(s -> s.toLowerCase().startsWith(args[args.length - 1].toLowerCase())).collect(Collectors.toList());
            } else return Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
        }
        return Collections.singletonList("");
    }

    private boolean isName(@NotNull Command cmd, BaseCommand command) {
        boolean isName = false;
        for (String name : command.getName()){
            if (cmd.getAliases().contains(name)){
                isName = true;
                break;
            }
            if (name.equalsIgnoreCase(cmd.getName())){
                isName = true;
                break;
            }
        }
        return isName;
    }
}
