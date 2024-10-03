package Plugin.Vote;

import Plugin.Section;
import Plugin.xBxTcore;

public class VoteSection implements Section {

    private final xBxTcore plugin;

    public VoteSection(xBxTcore xBxTcore) {
        this.plugin = xBxTcore;
    }

    @Override
    public void enable() {
        plugin.register(new VoteListener(plugin));
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
