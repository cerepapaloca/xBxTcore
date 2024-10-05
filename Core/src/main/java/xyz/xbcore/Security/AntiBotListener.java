package xyz.xbcore.Security;

import xyz.xbcore.File.FileManagerSection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

import static xyz.xbcore.File.BLackList.BlackListIpManager.blackListedIps;
import static xyz.xbcore.Messages.MessageManager.*;
import static xyz.xbcore.Messages.MessageManager.ColorWarning;

public class AntiBotListener implements Listener {

    private Long coolDownJoin = System.currentTimeMillis();
    private final HashMap<UUID, Long> listAntiBot = new HashMap<>();
    private final HashMap<UUID, Integer> listAntiBotBan = new HashMap<>();
    private final String messageBlackListIp = ChatColor.translateAlternateColorCodes('&',prefixKick + Colorinfo +
            "Esta Ip esta Baneada, Si crees que es error Contacta con el owner rápidamente antes de se la ip se agregue a la BlackList del FireWall\n" +
            "This IP is Banned, If you think it is a mistake, contact the owner quickly before the IP is added to the FireWall BlackList\n"
            + ColorLink +  "https://discord.gg/QYBwEFvnsG");
    private final String messageKick = ChatColor.translateAlternateColorCodes('&',prefixKick + Colorinfo + "Only one player can join every 3 seconds\nSolo se puede unir un jugador por cada 3 segundos");
    private final String messageKick2 = ChatColor.translateAlternateColorCodes('&',prefixKick + Colorinfo +
            "Please &lDo Not Enter&r " + Colorinfo + "it is possible that the server is under a bot attack after %p% attempts the connection is blocked\n" +
            "Por favor &lNo Entrar&r " + Colorinfo + "es posible que el servidor este bajo un ataque de bots después de %p% intentos se le bloqueara la conexión");
    private final String messageKickConsole1 = ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning + "Se expulso al jugador " + Colorplayer + "%p%" + ColorWarning + " por entrar paridamente");
    private final String messageKickConsole2 = ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning + "Se expulso al jugador " + Colorplayer + "%p%" + ColorWarning + " por posible ataque de bots");
    private final String messageKickConsole3 = ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning + "Se le baneo ip del jugador " + Colorplayer + "%p%" + ColorWarning + " por posible ataque de bots");


    @EventHandler(priority = EventPriority.HIGH)
    public void AntiBot(AsyncPlayerPreLoginEvent event) {

        if(!SecuritySection.ActiveAntiBot)return;

        String name = event.getName();
        UUID uuid = event.getUniqueId();
        byte[] PlayerBytesIp = event.getAddress().getAddress();

        for (byte[] bytes : blackListedIps){
            if (Arrays.equals(PlayerBytesIp, bytes)){
                event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
                event.setKickMessage(messageBlackListIp);
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning + "Se Expulso por Ip al jugador: " + event.getName() + " y su ip: " + event.getAddress()));
                return;
            }
        }

        if (FileManagerSection.getPlayerFileManager().UuidsPlayres.contains(uuid)) {
            if (!listAntiBot.containsKey(uuid)) return;

            if (listAntiBot.get(uuid) >= System.currentTimeMillis()) {
                event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
                event.setKickMessage(messageKick);
                Bukkit.getConsoleSender().sendMessage(messageKickConsole1.replace("%p%", name));
                listAntiBot.put(uuid, System.currentTimeMillis() + 3500);
            }
        }else{
            if (coolDownJoin >= System.currentTimeMillis()) {
                Bukkit.getConsoleSender().sendMessage(messageKickConsole2.replace("%p%", name));
                if (listAntiBotBan.containsKey(uuid)) {
                    if (listAntiBotBan.get(uuid) <= 1) {
                        blackListedIps.add(PlayerBytesIp);
                        Bukkit.getConsoleSender().sendMessage(messageKickConsole3.replace("%p%", name));
                        listAntiBotBan.remove(uuid);
                    }
                }
                event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
                listAntiBotBan.put(uuid, listAntiBotBan.getOrDefault(uuid,  8) - 1);
                event.setKickMessage(messageKick2.replace("%p%",  listAntiBotBan.get(uuid).toString()));
            }
            coolDownJoin = System.currentTimeMillis() + 30000;
        }
    }

    @EventHandler
    public void PlayerLoginEvent(PlayerLoginEvent event) {
        listAntiBot.put(event.getPlayer().getUniqueId(), System.currentTimeMillis() + 3000);
    }
}
