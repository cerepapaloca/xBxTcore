package Plugin.Commands;

import java.util.List;

public abstract class BaseTabCommand extends BaseCommand {


    public BaseTabCommand(String name, String usage, String permission, boolean onlyOP, String description) {
        super(name, usage, permission, onlyOP, description);
    }

    public BaseTabCommand(String name, String usage, String permission, boolean onlyOP, String description, String[] subCommands) {
        super(name, usage, permission, onlyOP, description, subCommands);
    }

    public BaseTabCommand(String[] name, String usage, String permission, boolean onlyOP, String description) {
        super(name, usage, permission, onlyOP, description);
    }

    public BaseTabCommand(String[] name, String usage, String permission, boolean onlyOP, String description, String[] subCommands) {
        super(name, usage, permission, onlyOP, description, subCommands);
    }

    public abstract List<String> onTab(String[] args);
}
