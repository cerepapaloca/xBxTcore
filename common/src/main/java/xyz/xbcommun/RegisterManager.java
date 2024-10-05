package xyz.xbcommun;

import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import xyz.xbcommun.Command.BaseCommand;
import xyz.xbcommun.Command.CommandSectionCommon;
import xyz.xbcommun.Messages.PacketMessagesListener;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;
import static xyz.xbcommun.Messages.MessageManager.*;
import static xyz.xbcommun.Messages.MessageManager.ColorError;
import static xyz.xbcommun.xBxTcommon.plugin;

public class RegisterManager {

    private static final List<Section> sections = new ArrayList<>();
    @Getter private static final List<PacketMessagesListener> messagesListener = new ArrayList<>();

    public static void register(@NotNull Section section) {
        if (getSectionByName(section.getName()) != null)
            throw new IllegalArgumentException("Section has already been registered " + section.getName());
        sections.add(section);
    }

    public static void register(@NotNull PacketMessagesListener listener) {
        messagesListener.add(listener);
    }


    public static void register(Listener @NotNull ... listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, xBxTcommon.plugin);
        }
    }

    public static void register(BaseCommand command) {
        CommandSectionCommon.getCommandHandler().getCommands().add(command);
        for (String name : command.getName()){
            PluginCommand pluginCommand = plugin.getCommand(name);
            pluginCommand.setExecutor(CommandSectionCommon.getCommandHandler());
            pluginCommand.setTabCompleter(CommandSectionCommon.getCommandHandler());
        }
    }

    public static void startRegister() {
        for (Section section : sections) {
            try {
                section.enable();
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', Colorinfo + section.getName() + ColorSuccess + " Ok"));
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorError + "Error al Cargar " + section.getName() + " servidor requiere reinicio"));
                throw new RuntimeException(e);
            }
        }
    }

    public static Section getSectionByName(String name) {
        return sections.stream().filter(s -> s.getName().equals(name)).findFirst().orElse(null);
    }

    public static void Unregister() {
        sections.forEach(Section::disable);
        sections.clear();
    }
}
