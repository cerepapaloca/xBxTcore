package xyz.xbcommun.Command;

import lombok.Getter;
import xyz.xbcommun.Section;

public class CommandSectionCommon implements Section {

    @Getter private static CommandHandler commandHandler;

    @Override
    public void enable() {
        commandHandler = new CommandHandler();
    }

    @Override
    public void disable() {

    }

    @Override
    public void reloadConfig() {

    }

    @Override
    public String getName() {
        return "CommandSection (Common)";
    }
}
