package Plugin.Messages;

import Plugin.Messages.Listener.MessageDiedListener;
import Plugin.Section;
import Plugin.xBxTcore;

public class MessageSection implements Section {

    private static xBxTcore plugin;

    public MessageSection(xBxTcore xBxTcore) {
        plugin = xBxTcore;
    }

    @Override
    public void enable() {
        plugin.register(new MessageDiedListener());
        new MessageManager();
        new MessageTranslatorManager(plugin);
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
