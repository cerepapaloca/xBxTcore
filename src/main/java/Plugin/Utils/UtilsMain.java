package Plugin.Utils;

import Plugin.Section;
import Plugin.xBxTcore;

import static Plugin.Security.FireWall.updateFirewallRule;

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
        updateFirewallRule();
    }

    @Override
    public String getName() {
        return "UtilsMain";
    }

    @Override
    public void reloadConfig() {
        updateFirewallRule();
    }

    public static Utils getUtils() {
        return utils;
    }
}
