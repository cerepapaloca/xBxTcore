package Plugin.Security;

import Plugin.File.FileManagerSection;
import Plugin.Section;
import Plugin.Security.SystemBan.AutoBan;
import Plugin.Security.SystemBan.BanManager;
import Plugin.xBxTcore;

public class SecuritySection implements Section {

    private
    final xBxTcore plugin;
    public static Boolean ActiveAntiBot = true;
    private static BanManager banManager;
    public static AutoBan autoBan;

    public SecuritySection(xBxTcore xBxTcore) {
        this.plugin = xBxTcore;
    }

    @Override
    public void enable() {
        plugin.register(new AntiBotListener());
        plugin.register(new GrimAC());
        plugin.register(banManager = new BanManager(FileManagerSection.getMySQLConnection(), plugin));
        new AntiTwoPlayer();
        autoBan = new AutoBan(plugin);
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

    public static AutoBan getAutoBan() {
        return autoBan;
    }
}
