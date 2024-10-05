package xyz.xbcore.Messages;

import xyz.xbcore.Messages.Listener.MessageDiedListener;
import xyz.xbcore.Messages.Messages.MessagesEN;
import xyz.xbcore.Messages.Messages.MessagesES;
import xyz.xbcore.Section;
import xyz.xbcore.xBxTcore;

public class MessageSection implements Section {

    private static xBxTcore plugin;

    public MessageSection(xBxTcore xBxTcore) {
        plugin = xBxTcore;
    }

    @Override
    public void enable() {
        plugin.register(new MessageDiedListener());
        new MessageTranslatorManager(plugin);
        new MessagesEN();
        new MessagesES();
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
