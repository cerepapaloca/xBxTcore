package Plugin.Commands;

import Plugin.Commands.User.*;
import Plugin.File.FileManagerSection;
import Plugin.File.PlayerData.PlayerfileManager;
import Plugin.Section;
import Plugin.xBxTcore;
import lombok.Getter;

public class CommandSection implements Section {
    @Getter private final xBxTcore plugin;
    @Getter public static CommandDuel commandDuel;
    @Getter public static CommandHandler commandHandler;

    public CommandSection(xBxTcore plugin) {
        this.plugin = plugin;
    }

    @Override
    public void enable() {
        commandHandler = new CommandHandler(this);
        commandHandler.registerCommands();
        commandDuel = commandHandler.getCommandDuel();
    }

    @Override
    public void disable() {

    }

    @Override
    public String getName() {
        return "CommandSection";
    }

    @Override
    public void reloadConfig() {
        FileManagerSection.getBlacklistIpManager().ReloadIpBlacklist();
        PlayerfileManager.getPlayesfiles().reloadConfigs();
    }

}
