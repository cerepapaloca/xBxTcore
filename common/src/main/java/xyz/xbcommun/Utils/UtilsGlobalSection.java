package xyz.xbcommun.Utils;

import org.bukkit.plugin.Plugin;
import xyz.xbcommun.Section;
import lombok.Getter;

@Getter
public class UtilsGlobalSection implements Section {

    private static Plugin plugin;

    @Getter private static UtilsGlobal utils;

    public UtilsGlobalSection(Plugin xBxTcore) {
        plugin = xBxTcore;
    }


    @Override
    public void enable() {
        utils = new UtilsGlobal(plugin);
    }

    @Override
    public void disable() {

    }

    @Override
    public String getName() {
        return "UtilsMain";
    }

    @Override
    public void reloadConfig() {

    }

}
