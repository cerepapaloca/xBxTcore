package Plugin.Commands;

import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

@Getter
public abstract class BaseCommand {

    private final String name;
    private final String usage;
    private final String[] permissions;
    private final String description;
    private final String[] subCommands;

    public BaseCommand(String name, String usage, String permission) {
        this(name, usage, permission, null, null);
    }

    public BaseCommand(String name, String usage, String permission, String description) {
        this(name, usage, permission, description, null);
    }
    public BaseCommand(String name, String usage, String[] permission, String description) {
        this(name, usage, permission, description, null);
    }

    public BaseCommand(String name, String usage, String permission, String description, String[] subCommands) {
        this.name = name;
        this.usage = usage;
        this.permissions = new String[]{permission};
        this.description = description;
        this.subCommands = subCommands;
    }

    public BaseCommand(String name, String usage, String[] permissions, String description, String[] subCommands) {
        this.name = name;
        this.usage = usage;
        this.permissions = permissions;
        this.description = description;
        this.subCommands = subCommands;
    }

    public Optional<Player> getSenderAsPlayer(CommandSender sender) {
        if (sender instanceof Player) {
            return Optional.of((Player) sender);
        } else return Optional.empty();
    }

    public abstract void execute(CommandSender sender, String[] args);

}
