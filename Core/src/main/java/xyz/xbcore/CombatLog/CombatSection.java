package xyz.xbcore.CombatLog;

import xyz.xbcore.CombatLog.Listener.CombatlogListener;
import xyz.xbcommun.Section;
import xyz.xbcore.xBxTcore;

import static xyz.xbcommun.RegisterManager.register;

public class CombatSection implements Section {

    private final xBxTcore plugin;

    private static CombatlogManager combatlogManager;

    public CombatSection(xBxTcore xBxTcore) {
        this.plugin = xBxTcore;
        combatlogManager = new CombatlogManager(plugin);
    }

    @Override
    public void enable() {
        register(new CombatlogListener());
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
