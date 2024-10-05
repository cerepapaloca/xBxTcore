package xyz.xbcore.Duel;

import lombok.Getter;
import xyz.xbcore.Duel.Listener.DuelListener;
import xyz.xbcommun.Section;
import xyz.xbcore.xBxTcore;

import static xyz.xbcommun.RegisterManager.register;

public class DuelSection implements Section {

    private static xBxTcore plugin;

    @Getter
    private static DuelManager duelManager;

    public DuelSection(xBxTcore xBxTcore) {
        plugin = xBxTcore;
    }

    @Override
    public void enable() {
        register(new DuelListener());
        duelManager = new DuelManager(plugin);
    }

    @Override
    public void disable() {

    }

    @Override
    public String getName() {
        return "DuelSection";
    }

    @Override
    public void reloadConfig() {

    }

}
