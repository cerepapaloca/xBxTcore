package Plugin.Environments;

import Plugin.Environments.Hologrmas.Hologramas;
import Plugin.Environments.Hologrmas.HologramasBoxPvp;
import Plugin.Section;
import Plugin.xBxTcore;

public class EnvironmentsSection implements Section {

    private static xBxTcore plugin;

    public static Hologramas hologramas;
    private static HologramasBoxPvp hologramasBoxPvp;
    private static Cleaner cleaner;

    public EnvironmentsSection(xBxTcore xBxTcore) {
        plugin = xBxTcore;
    }

    @Override
    public void enable() {
        cleaner = new Cleaner(plugin);
        hologramasBoxPvp = new HologramasBoxPvp(plugin);
    }

    @Override
    public void disable() {

    }

    @Override
    public String getName() {
        return "EnvironmentsSection";
    }

    @Override
    public void reloadConfig() {

    }

    public static Cleaner getCleaner() {
        return cleaner;
    }

    public static HologramasBoxPvp getHologramasBoxPvp() {
        return hologramasBoxPvp;
    }

    public static Hologramas getHologramas() {
        return hologramas;
    }
}
