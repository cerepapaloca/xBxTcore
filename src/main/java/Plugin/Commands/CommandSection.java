package Plugin.Commands;

import Plugin.Commands.OnlyOp.*;
import Plugin.Commands.Tab.*;
import Plugin.Commands.User.*;
import Plugin.File.PlayerData.PlayerfileManager;
import Plugin.Section;
import Plugin.xBxTcore;
import lombok.Getter;

import java.util.Objects;

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
        Objects.requireNonNull(plugin.getCommand("Cleaner")).setExecutor(new CommandCleaner());
        Objects.requireNonNull(plugin.getCommand("info")).setExecutor(new Commandinfo(plugin));
        Objects.requireNonNull(plugin.getCommand("ping")).setExecutor(new CommandPing());
        Objects.requireNonNull(plugin.getCommand("debugkit")).setExecutor(new CommandDebugKit());
        Objects.requireNonNull(plugin.getCommand("ReloadDataPlayares")).setExecutor(new CommandReloadDataPlayares());
        Objects.requireNonNull(plugin.getCommand("itemboxpvp")).setExecutor(new CommandItemBoxpvp(plugin));
        Objects.requireNonNull(plugin.getCommand("timerewardskip")).setExecutor(new CommandTimeRewardSkip());
        Objects.requireNonNull(plugin.getCommand("vip")).setExecutor(new CommandVip());
        Objects.requireNonNull(plugin.getCommand("xbxtcore")).setExecutor(new Commandxbxtcore());
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        Objects.requireNonNull(plugin.getCommand("itemboxpvp")).setTabCompleter(new TabItemBoxpvp());
        Objects.requireNonNull(plugin.getCommand("xbxtcore")).setTabCompleter(new TabxBxTcore());
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
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
        PlayerfileManager.playesfiles.reloadConfigs();
    }

}
