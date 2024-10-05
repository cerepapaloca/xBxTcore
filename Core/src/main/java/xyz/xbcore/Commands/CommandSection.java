package xyz.xbcore.Commands;

import xyz.xbcore.Commands.OnlyOp.*;
import xyz.xbcore.Commands.User.*;
import xyz.xbcore.File.FileManagerSection;
import xyz.xbcore.File.PlayerData.PlayerfileManager;
import xyz.xbcommun.Section;
import xyz.xbcore.xBxTcore;
import lombok.Getter;

import static xyz.xbcommun.RegisterManager.register;

public class CommandSection implements Section {
    @Getter private final xBxTcore plugin;
    @Getter public static CommandDuel commandDuel;

    public CommandSection(xBxTcore plugin) {
        this.plugin = plugin;
    }

    @Override
    public void enable() {
        register(new CommandVote());
        register(new CommandMenu());
        register(new CommandShop(plugin));
        register(new CommandSpectator(plugin));
        register(new CommandSaveKit(plugin));
        register(new CommandRank(plugin));
        register(new CommandLobby());
        register(new CommandKitFavorite(plugin));
        register(new CommandKit());
        register(new CommandKill());
        register(new CommandInv());
        register(new CommandHelp());
        register(commandDuel = new CommandDuel(plugin));
        register(new CommandDonate());
        register(new CommandDiscord());
        register(new CommandDelKit());
        register(new CommandBoxPvp());
        //////////////
        register(new CommandCleaner());
        register(new CommandDebugKit());
        register(new Commandinfo(plugin));
        register(new CommandItemBoxpvp(plugin));
        register(new CommandTimeRewardSkip());
        register(new CommandVip());
        register(new Commandxbxtcore());
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
