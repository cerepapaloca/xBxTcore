package Plugin.Utils;

import Plugin.Section;
import Plugin.xBxTcore;
import lombok.Getter;

@Getter
public class UtilsMain implements Section {

    private static xBxTcore plugin;

    @Getter private static Utils utils;

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

}
