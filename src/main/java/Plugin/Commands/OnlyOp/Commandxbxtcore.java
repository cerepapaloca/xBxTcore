package Plugin.Commands.OnlyOp;

import Plugin.File.BLackList.BlackListIpManager;
import Plugin.File.FileManagerSection;
import Plugin.Messages.Enum.Messages;
import Plugin.Security.SecuritySection;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static Plugin.File.BLackList.BlackListIpManager.RemoveIpBlackListAndSave;
import static Plugin.Messages.MessageManager.*;
import static Plugin.Security.FireWall.runBatFile;
import static Plugin.Security.FireWall.updateFirewallRule;

public class Commandxbxtcore implements CommandExecutor {

    private final xBxTcore plugin;

    public Commandxbxtcore(xBxTcore plugin){
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {

            if (args.length < 1) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorError + "Pon los argumentos"));
                return false;
            }
            args[0] = args[0].toLowerCase();
            switch (args[0]) {

                case "antibot" -> {
                    switch (args[1]) {
                        case "true" -> {
                            SecuritySection.ActiveAntiBot = true;
                            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorSuccess + "Sistema antiBot Activo"));
                            return true;
                        }
                        case "false" -> {
                            SecuritySection.ActiveAntiBot = false;
                            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning + "Sistema antiBot Desactivado"));
                            return true;
                        }

                    }
                }

                case "ac" -> {
                    switch (args[1]) {
                        case "kick" -> {
                            if(!(args.length == 3))return false;
                            Player player = Bukkit.getPlayer(args[2]);
                            if(player == null)return false;
                            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefixConsole + ColorSuccess + "Se Echo al jugador por hacks"));
                            player.kickPlayer(MasterMessageLocated(player, Messages.Kick_Cheat));
                            return true;
                        }
                    }
                }

                case "ip" -> {
                    switch (args[1]) {
                        case "save" -> {
                            updateFirewallRule();
                            BlackListIpManager.saveIpBlacklist();
                            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorSuccess + "Se guardo las ips Correctamente"));
                            return true;
                        }
                        case "reload" -> {
                            FileManagerSection.getBlacklistIpManager().ReloadIpBlacklist();
                            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorSuccess + "Lista negra recargada"));
                            return true;
                        }
                        case "update" -> {
                            try {
                                runBatFile();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            return true;
                        }
                        case "remove" -> {
                            if(!(args.length == 3))return false;
                            try {
                                RemoveIpBlackListAndSave(InetAddress.getByName(args[2]));
                            } catch (UnknownHostException e) {
                                throw new RuntimeException(e);
                            }
                            return true;
                        }
                    }
                }
                default -> {
                    return false;
                }
            }
        }
        return false;
    }
}
