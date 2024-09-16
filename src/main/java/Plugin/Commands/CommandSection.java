package Plugin.Commands;

import Plugin.Commands.OnlyOp.*;
import Plugin.Commands.Tab.*;
import Plugin.Commands.User.*;
import Plugin.File.PlayerData.PlayerfileManager;
import Plugin.Section;
import Plugin.Security.SecuritySection;
import Plugin.xBxTcore;

import java.util.Objects;

public class CommandSection implements Section {

    private final xBxTcore plugin;
    public static CommandDuel commandDuel;

    public CommandSection(xBxTcore plugin) {
        this.plugin = plugin;
    }

    @Override
    public void enable() {
        Objects.requireNonNull(plugin.getCommand("kit")).setExecutor(new CommandKit(plugin));
        Objects.requireNonNull(plugin.getCommand("savekit")).setExecutor(new CommandSaveKit(plugin));
        Objects.requireNonNull(plugin.getCommand("delkit")).setExecutor(new CommandDelKit(plugin));
        Objects.requireNonNull(plugin.getCommand("kitfavorite")).setExecutor(new CommandKitFavorite(plugin));
        Objects.requireNonNull(plugin.getCommand("Cleaner")).setExecutor(new CommandCleaner());
        Objects.requireNonNull(plugin.getCommand("info")).setExecutor(new Commandinfo(plugin));
        Objects.requireNonNull(plugin.getCommand("lobby")).setExecutor(new CommandLobby(plugin));
        Objects.requireNonNull(plugin.getCommand("help")).setExecutor(new CommandHelp(plugin));
        Objects.requireNonNull(plugin.getCommand("kill")).setExecutor(new CommandKill(plugin));
        Objects.requireNonNull(plugin.getCommand("duel")).setExecutor(commandDuel = new CommandDuel(plugin));
        Objects.requireNonNull(plugin.getCommand("discord")).setExecutor(new CommandDiscord(plugin));
        Objects.requireNonNull(plugin.getCommand("spectator")).setExecutor(new CommandSpectator(plugin));
        Objects.requireNonNull(plugin.getCommand("ping")).setExecutor(new CommandPing());
        Objects.requireNonNull(plugin.getCommand("debugkit")).setExecutor(new CommandDebugKit());
        Objects.requireNonNull(plugin.getCommand("rank")).setExecutor(new CommandRank(plugin));
        Objects.requireNonNull(plugin.getCommand("vote")).setExecutor(new CommandVote(plugin));
        Objects.requireNonNull(plugin.getCommand("donate")).setExecutor(new CommandDonate(plugin));
        Objects.requireNonNull(plugin.getCommand("inv")).setExecutor(new CommandInv(plugin));
        Objects.requireNonNull(plugin.getCommand("boxpvp")).setExecutor(new CommandBoxPvp(plugin));
        Objects.requireNonNull(plugin.getCommand("ReloadDataPlayares")).setExecutor(new CommandReloadDataPlayares(plugin));
        Objects.requireNonNull(plugin.getCommand("itemboxpvp")).setExecutor(new CommandItemBoxpvp(plugin));
        Objects.requireNonNull(plugin.getCommand("timerewardskip")).setExecutor(new CommandTimeRewardSkip());
        Objects.requireNonNull(plugin.getCommand("vip")).setExecutor(new CommandVip());
        Objects.requireNonNull(plugin.getCommand("shop")).setExecutor(new CommandShop(plugin));
        Objects.requireNonNull(plugin.getCommand("xbxtcore")).setExecutor(new Commandxbxtcore(plugin, SecuritySection.getBanManager()));
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        Objects.requireNonNull(plugin.getCommand("sk")).setExecutor(new CommandSaveKit(plugin));
        Objects.requireNonNull(plugin.getCommand("dk")).setExecutor(new CommandDelKit(plugin));
        Objects.requireNonNull(plugin.getCommand("kf")).setExecutor(new CommandKitFavorite(plugin));
        Objects.requireNonNull(plugin.getCommand("spawn")).setExecutor(new CommandLobby(plugin));
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        Objects.requireNonNull(plugin.getCommand("savekit")).setTabCompleter(new TabCompleterSaveKit());
        Objects.requireNonNull(plugin.getCommand("delKit")).setTabCompleter(new TabCompleterDelKit());
        Objects.requireNonNull(plugin.getCommand("kitfavorite")).setTabCompleter(new TabCompleterKitFavorite());
        Objects.requireNonNull(plugin.getCommand("duel")).setTabCompleter(new TabCompleterDuel());
        Objects.requireNonNull(plugin.getCommand("spectator")).setTabCompleter(new TabCompleterSpectator());
        Objects.requireNonNull(plugin.getCommand("debugkit")).setTabCompleter(new TabCompleterDebugKit());
        Objects.requireNonNull(plugin.getCommand("itemboxpvp")).setTabCompleter(new TabItemBoxpvp());
        Objects.requireNonNull(plugin.getCommand("xbxtcore")).setTabCompleter(new TabxBxTcore());
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        Objects.requireNonNull(plugin.getCommand("sk")).setTabCompleter(new TabCompleterSaveKit());
        Objects.requireNonNull(plugin.getCommand("dk")).setTabCompleter(new TabCompleterDelKit());
        Objects.requireNonNull(plugin.getCommand("kf")).setTabCompleter(new TabCompleterKitFavorite());
    }

    public static CommandDuel getCommandDuel(){
        return commandDuel;
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
