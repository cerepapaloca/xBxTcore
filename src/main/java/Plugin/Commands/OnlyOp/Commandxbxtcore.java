package Plugin.Commands.OnlyOp;

import Plugin.Commands.BaseCommand;
import Plugin.Commands.BaseTabCommand;
import Plugin.File.BLackList.BlackListIpManager;
import Plugin.File.FileManagerSection;
import Plugin.File.MySQLConnection;
import Plugin.File.PlayerData.PlayerfileManager;
import Plugin.Messages.MessageManager;
import Plugin.Messages.Messages.Messages;
import Plugin.PlayerManager.PlayerManagerSection;
import Plugin.Security.SystemBan.BanManager;
import Plugin.Security.FireWall.FireWallLinux;
import Plugin.Security.FireWall.FireWallWindows;
import Plugin.Security.SecuritySection;
import Plugin.Security.SystemBan.ContextBan;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static Plugin.File.BLackList.BlackListIpManager.RemoveIpBlackListAndSave;
import static Plugin.Messages.MessageManager.*;
import static Plugin.Security.SystemBan.AutoBan.checkAutoBanCheat;

public class Commandxbxtcore extends BaseTabCommand {

    private final List<String> contexts = new ArrayList<>();

    public Commandxbxtcore(){
        super("xbxtcore",
                "/xbxtcore",
                "xbxtcore.command.xbxtcore",
                true,
                "se encarga de todas las funciones del servidor");
        contexts.add("global");
        contexts.add("box_pvp");
        contexts.add("chat");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {

            if (args.length < 1) {
                Utils.sendMessage(sender, prefixConsole + ColorError + "Pon los argumentos");
                return;
            }
            args[0] = args[0].toLowerCase();
            switch (args[0]) {

                case "antibot" -> {
                    switch (args[1]) {
                        case "true" -> {
                            SecuritySection.ActiveAntiBot = true;
                            Utils.sendMessage(sender, prefixConsole + ColorSuccess + "Sistema antiBot Activo");
                        }
                        case "false" -> {
                            SecuritySection.ActiveAntiBot = false;
                            Utils.sendMessage(sender, prefixConsole + ColorWarning + "Sistema antiBot Desactivado");
                        }

                    }
                }

                case "ac" -> {
                    switch (args[1]) {
                        case "kick" -> {
                            if(!(args.length == 3))return;
                            Player player = Bukkit.getPlayer(args[2]);
                            if(player == null)return;
                            if (checkAutoBanCheat(player))return;
                            Utils.sendMessage(sender, prefixConsole + ColorSuccess + "Se Echo al jugador por hacks");
                            player.kickPlayer(MasterMessageLocated(player, Messages.Kick_Cheat));
                        }
                    }
                }

                case "ip" -> {
                    switch (args[1]) {
                        case "save" -> {
                            switch (xBxTcore.getSystemOperative){
                                case WINDOWS -> FireWallWindows.updateFirewallRule();
                                case LINUX -> FireWallLinux.updateFirewallZone();
                            }

                            BlackListIpManager.saveIpBlacklist();
                            Utils.sendMessage(sender, prefixConsole + ColorSuccess + "Se guardo las ips Correctamente");
                        }
                        case "reload" -> {
                            FileManagerSection.getBlacklistIpManager().ReloadIpBlacklist();
                            Utils.sendMessage(sender, prefixConsole + ColorSuccess + "Lista negra recargada");
                        }
                        case "update" -> {
                            switch (xBxTcore.getSystemOperative){
                                case LINUX -> FireWallLinux.executeFirewallScript();
                                case WINDOWS -> FireWallWindows.runBatFile();
                            }
                        }
                        case "remove" -> {
                            if(!(args.length == 3))return;
                            try {
                                RemoveIpBlackListAndSave(InetAddress.getByName(args[2]));
                            } catch (UnknownHostException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }

                case "ban" -> {
                    if (args[1].equalsIgnoreCase("reload")) MySQLConnection.reloadBannedBans();
                    if(!(args.length >= 5))return;
                    Player target = sender.getServer().getPlayer(args[1]);
                    if (target != null) {
                        String name = target.getName();
                        String reason = "";
                        for (int i = 4; i < args.length; i++){
                            reason = reason.concat(args[i] + " ");
                        }

                        long duration = Utils.StringToMilliseconds(args[3]);
                        if (contexts.contains(args[2].toLowerCase())) {

                            BanManager.banPlayer(target, reason, duration, ContextBan.valueOf(args[2].toUpperCase()));
                        }else{
                            Utils.sendMessage(sender, prefixConsole + ColorError +
                                    "Ese Contexto no existe solo exite 'global' y 'box_pvp'");
                        }

                    } else {
                        sender.sendMessage("Jugador no encontrado.");
                    }
                }

                case "chatmodetarion" -> {
                    switch (args[1]) {
                        case "true" -> {
                            PlayerManagerSection.moderationChatEnabled = true;
                            BroadcastMessage(Messages.Others_Chat_Active);
                        }
                        case "false" -> {
                            PlayerManagerSection.moderationChatEnabled = false;
                            Utils.sendMessage(sender, prefixConsole + ColorWarning + "Moderación del chat desactivada ");
                        }
                    }
                }

                case "users" -> {
                    switch (args[1]) {
                        case "reload" -> {
                            long time = System.currentTimeMillis();
                            PlayerfileManager.getPlayesfiles().reloadConfigs();
                            Utils.sendMessage(sender ,prefixConsole + ColorSuccess + "Se recargo los datos de los usuarios y tardo: " + (System.currentTimeMillis() - time));
                        }
                        case "ping" -> {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                p.getPing();
                                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.prefix + MessageManager.Colorplayer + p.getName() + MessageManager.Colorinfo + " Tiene "
                                        + MessageManager.Colorplayer + p.getPing()));
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, String[] args) {
        List<String> argsTab = new ArrayList<>();
        argsTab.add("ip");
        argsTab.add("antibot");
        argsTab.add("ac");
        argsTab.add("ban");
        argsTab.add("chatmodetarion");
        List<String> argsIp = new ArrayList<>();
        argsIp.add("reload");
        argsIp.add("save");
        argsIp.add("update");
        argsIp.add("remove");
        List<String> argsAntiBot = new ArrayList<>();
        argsAntiBot.add("true");
        argsAntiBot.add("false");
        List<String> argsBan = new ArrayList<>();
        argsBan.add("<Player> <Contexto> <tiempo> <Razón>");
        List<String> argsUser = new ArrayList<>();
        argsAntiBot.add("reload");
        argsAntiBot.add("ping");

        if (args.length == 1) {
            String currentArg = args[0].toLowerCase();
            return argsTab.stream()
                    .filter(name -> name.startsWith(currentArg))
                    .collect(Collectors.toList());
        }
        switch (args[0].toLowerCase()){
            case "ip" -> {
                String currentArg = args[1].toLowerCase();
                return argsIp.stream()
                        .filter(name -> name.startsWith(currentArg))
                        .collect(Collectors.toList());
            }
            case "antibot", "chatmodetarion" -> {
                String currentArg = args[1].toLowerCase();
                return argsAntiBot.stream()
                        .filter(name -> name.startsWith(currentArg))
                        .collect(Collectors.toList());
            }
            case "ban" -> {
                if (args.length == 2) return null;
                return argsBan;
            }
            case "user" -> {
                if (args.length == 2) return null;
                return argsUser;
            }
        }

        return null;
    }
}
