package xyz.xbcore.Environment;

import xyz.xbcore.Environment.Hologrmas.Hologramas;
import xyz.xbcore.Environment.Hologrmas.HologramasBoxPvp;
import xyz.xbcore.Section;
import xyz.xbcore.xBxTcore;
import lombok.Getter;

@Getter
public class EnvironmentsSection implements Section {

    private static xBxTcore plugin;

    @Getter public static Hologramas hologramas;
    @Getter private static HologramasBoxPvp hologramasBoxPvp;
    @Getter private static Cleaner cleaner;

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
}
