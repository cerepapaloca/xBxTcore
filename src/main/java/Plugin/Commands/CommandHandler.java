package Plugin.Commands;

import Plugin.Commands.User.*;
import Plugin.Messages.MessageManager;
import Plugin.Messages.Messages.Messages;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CommandHandler implements TabExecutor {
    @Getter private final HashSet<BaseCommand> commands = new HashSet<>();
    private final CommandSection commandSection;
    @Getter private CommandDuel commandDuel;

    public void registerCommands() {
        xBxTcore plugin = commandSection.getPlugin();
        addCommand(new CommandVote());
        addCommand(new CommandMenu());
        addCommand(new CommandShop(plugin));
        addCommand(new CommandSpectator(plugin));
        addCommand(new CommandSaveKit(plugin));
        addCommand(new CommandRank(plugin));
        addCommand(new CommandLobby());
        addCommand(new CommandKitFavorite(plugin));
        addCommand(new CommandKit());
        addCommand(new CommandKill());
        addCommand(new CommandInv());
        addCommand(new CommandHelp());
        addCommand(commandDuel = new CommandDuel(plugin));
        addCommand(new CommandDonate());
        addCommand(new CommandDiscord());
        addCommand(new CommandDelKit());
        addCommand(new CommandBoxPvp());
    }

    private void addCommand(BaseCommand command) {
        commands.add(command);
        for (String name : command.getName()){
            PluginCommand pluginCommand = commandSection.getPlugin().getCommand(name);
            pluginCommand.setExecutor(this);
            pluginCommand.setTabCompleter(this);
        }
    }

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
                        sender.sendMessage(MessageManager.MasterMessageLocated(p, Messages.Generic_NotOp));
                    }
                }
                break;
            }

            if (hasPermission) {
                command.execute(sender, args);
            }else{
                Utils.sendMessage(sender, Messages.Generic_NotOp);
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
