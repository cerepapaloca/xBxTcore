package Plugin.Environment;

import Plugin.Environment.Hologrmas.Hologramas;
import Plugin.Environment.Hologrmas.HologramasBoxPvp;
import Plugin.Section;
import Plugin.xBxTcore;
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
