package Plugin.Duel;

import Plugin.Duel.Listener.DuelListener;
import Plugin.Section;
import Plugin.xBxTcore;

public class DuelSection implements Section {

    private static xBxTcore plugin;

    private static DuelManager duelManager;

    public DuelSection(xBxTcore xBxTcore) {
        plugin = xBxTcore;
    }

    @Override
    public void enable() {
        plugin.register(new DuelListener());
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

    public static DuelManager getDuelManager() {
        return duelManager;
    }
}
