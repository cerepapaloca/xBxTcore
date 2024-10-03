package Plugin.CombatLog;

import Plugin.CombatLog.Listener.CombatlogListener;
import Plugin.Section;
import Plugin.xBxTcore;

public class CombatSection implements Section {

    private final xBxTcore plugin;

    private static CombatlogManager combatlogManager;

    public CombatSection(xBxTcore xBxTcore) {
        this.plugin = xBxTcore;
        combatlogManager = new CombatlogManager(plugin);
    }

    @Override
    public void enable() {
        plugin.register(new CombatlogListener());
    }

    @Override
    public void disable() {

    }

    @Override
    public String getName() {
        return "CombatSection";
    }

    @Override
    public void reloadConfig() {

    }

    public static CombatlogManager getCombatlogManager() {
        return combatlogManager;
    }
}
