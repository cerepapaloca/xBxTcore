package xyz.xbcore.Commands;

import org.bukkit.command.PluginCommand;
import xyz.xbcommun.Command.BaseCommand;
import xyz.xbcommun.Command.CommandHandler;
import xyz.xbcore.Commands.OnlyOp.*;
import xyz.xbcore.Commands.User.*;
import xyz.xbcore.File.FileManagerSection;
import xyz.xbcore.File.PlayerData.PlayerfileManager;
import xyz.xbcore.Section;
import xyz.xbcore.xBxTcore;
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
        commandHandler = new CommandHandler();
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
        //////////////
        addCommand(new CommandCleaner());
        addCommand(new CommandDebugKit());
        addCommand(new Commandinfo(plugin));
        addCommand(new CommandItemBoxpvp(plugin));
        addCommand(new CommandTimeRewardSkip());
        addCommand(new CommandVip());
        addCommand(new Commandxbxtcore());
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

    private void addCommand(BaseCommand command) {
        commandHandler.getCommands().add(command);
        for (String name : command.getName()){
            PluginCommand pluginCommand = this.getPlugin().getCommand(name);
            pluginCommand.setExecutor(commandHandler);
            pluginCommand.setTabCompleter(commandHandler);
        }
    }

}
