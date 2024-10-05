package xyz.xbcore.Vote;

import xyz.xbcommun.Section;
import xyz.xbcore.xBxTcore;

import static xyz.xbcommun.RegisterManager.register;

public class VoteSection implements Section {

    private final xBxTcore plugin;

    public VoteSection(xBxTcore xBxTcore) {
        this.plugin = xBxTcore;
    }

    @Override
    public void enable() {
        register(new VoteListener(plugin));
    }

    @Override
    public void disable() {

    }

    @Override
    public String getName() {
        return "VoteSection";
    }

    @Override
    public void reloadConfig() {

    }
}
