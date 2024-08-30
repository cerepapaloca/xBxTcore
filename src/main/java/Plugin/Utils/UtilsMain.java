package Plugin.Utils;

import Plugin.Section;
import Plugin.xBxTcore;

public class UtilsMain implements Section {

    private static xBxTcore plugin;

    private static Utils utils;

    public UtilsMain(xBxTcore xBxTcore) {
        plugin = xBxTcore;
    }


    @Override
    public void enable() {
        utils = new Utils(plugin);
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

    public static Utils getUtils() {
        return utils;
    }
}
