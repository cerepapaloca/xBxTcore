package xyz.xbcommun;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.xbcommun.Command.CommandSectionCommon;
import xyz.xbcommun.Messages.MessageSectionCommon;
import xyz.xbcommun.Utils.SystemOperative;

public final class xBxTcommon {

    public static JavaPlugin plugin;
    public xBxTcommon instance = this;
    public static SystemOperative getSystemOperative;

    public static void Enable(JavaPlugin plugin) {
        xBxTcommon.plugin = plugin;
        RegisterManager.register(new MessageSectionCommon());
        RegisterManager.register(new CommandSectionCommon());
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            getSystemOperative = SystemOperative.WINDOWS;
        } else if (os.contains("nix") || os.contains("nux")) {
            getSystemOperative = SystemOperative.LINUX;
        }
    }

    public static void Disable() {
        RegisterManager.Unregister();
    }
}