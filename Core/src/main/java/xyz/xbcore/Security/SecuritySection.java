package xyz.xbcore.Security;

import xyz.xbcore.File.FileManagerSection;
import xyz.xbcore.Section;
import xyz.xbcore.Security.SystemBan.AutoBan;
import xyz.xbcore.Security.SystemBan.BanManager;
import xyz.xbcore.xBxTcore;
import lombok.Getter;

@Getter
public class SecuritySection implements Section {

    private
    final xBxTcore plugin;
    public static Boolean ActiveAntiBot = true;
    @Getter private static BanManager banManager;
    @Getter public static AutoBan autoBan;

    public SecuritySection(xBxTcore xBxTcore) {
        this.plugin = xBxTcore;
    }

    @Override
    public void enable() {
        plugin.register(new AntiBotListener());
        plugin.register(new GrimAC());
        plugin.register(banManager = new BanManager(FileManagerSection.getMysql(), plugin));
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

}
