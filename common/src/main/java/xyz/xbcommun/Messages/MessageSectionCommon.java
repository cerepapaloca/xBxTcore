package xyz.xbcommun.Messages;

import xyz.xbcommun.Messages.Messages.MessagesEN;
import xyz.xbcommun.Messages.Messages.MessagesES;
import xyz.xbcommun.Section;

public class MessageSectionCommon implements Section {

    @Override
    public void enable() {
        new MessagesListener();
        new MessagesEN();
        new MessagesES();
    }

    @Override
    public void disable() {

    }

    @Override
    public String getName() {
        return "MessageSection (Common)";
    }

    @Override
    public void reloadConfig() {

    }
}
