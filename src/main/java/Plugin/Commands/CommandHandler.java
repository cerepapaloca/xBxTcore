package Plugin.Commands;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CommandHandler implements TabExecutor {
    @Getter private final ArrayList<BaseCommand> commands = new ArrayList<>();
    private final CommandSection commandSection;

    public void registerCommands() {

    }

    private void addCommand(BaseCommand command) {
        commands.add(command);
        PluginCommand pluginCommand = commandSection.getPlugin().getCommand(command.getName());
        pluginCommand.setExecutor(this);
        pluginCommand.setTabCompleter(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        for (BaseCommand command : commands) {
            if (!command.getName().equalsIgnoreCase(cmd.getName())) continue;

            boolean hasPermission = false;
            for (String permission : command.getPermissions()) {
                if (sender.hasPermission(permission)) {
                    hasPermission = true;
                    break;
                }
            }

            if (hasPermission) {
                command.execute(sender, args);
            }

            break;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String alias, String[] args) {
        for (BaseCommand command : commands) {
            if (!command.getName().equalsIgnoreCase(cmd.getName())) continue;
            if (command instanceof BaseTabCommand tabCommand) return tabCommand.onTab(args);
            if (command.getSubCommands() != null && args.length == 1) {
                return Arrays.stream(command.getSubCommands()).map(s -> s.split("::")[0]).filter(s -> s.startsWith(args[0].toLowerCase())).collect(Collectors.toList());
            } else if (args.length > 1) {
                return Bukkit.getOnlinePlayers().stream().map(Player::getName).filter(s -> s.toLowerCase().startsWith(args[args.length - 1].toLowerCase())).collect(Collectors.toList());
            } else return Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
        }
        return Collections.singletonList("");
    }
}
