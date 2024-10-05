package xyz.xbcore.Messages;

import xyz.xbcommun.Section;

import static xyz.xbcommun.RegisterManager.register;

public class MessageSection implements Section {

    @Override
    public void enable() {
        register(new MessageDiedListener());
        register(new MessageListener());
    }

    @Override
    public void disable() {

    }

    @Override
    public String getName() {
        return "MessageSection";
    }

    @Override
    public void reloadConfig() {

    }
}
