package Plugin.Security;

import Plugin.File.FileManagerSection;
import Plugin.Section;
import Plugin.xBxTcore;

public class SecuritySection implements Section {

    private
    final xBxTcore plugin;
    public static Boolean ActiveAntiBot = true;
    private static BanManager banManager;

    public SecuritySection(xBxTcore xBxTcore) {
        this.plugin = xBxTcore;
    }

    @Override
    public void enable() {
        plugin.register(new AntiBotListener());
        plugin.register(new GrimAC());
        plugin.register(banManager = new BanManager(FileManagerSection.getMySQLConnection()));
        new AntiTwoPlayer();
    }

    @Override
    public void disable() {

    }

    @Override
    public String getName() {
        return "SecuritySection";
    }

    @Override
    public void reloadConfig() {

    }

    public static BanManager getBanManager() {
        return banManager;
    }
}
