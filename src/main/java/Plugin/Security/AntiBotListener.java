package Plugin.Security;

import Plugin.File.BLackList.BlackListIpManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.HashMap;

import static Plugin.File.FileManagerSection.getPlayerFileManager;
import static Plugin.Messages.MessageManager.*;
import static Plugin.Messages.MessageManager.ColorWarning;

public class AntiBotListener implements Listener {

    private Long coolDownJoin = System.currentTimeMillis();
    private final HashMap<String, Long> listAntiBot = new HashMap<>();
    private final HashMap<String, Integer> listAntiBotBan = new HashMap<>();
    private final String messageBlackListIp = ChatColor.translateAlternateColorCodes('&',prefixKick + Colorinfo + "Esta Ip esta Baneada, Si crees que un error Contacta con el owner\nThis IP is Banned, If you think it's a mistake Contact the owner\n" + ColorLink +  "https://discord.gg/QYBwEFvnsG");
    private final String messageKick = ChatColor.translateAlternateColorCodes('&',prefixKick + Colorinfo + "Only one player can join every 3 seconds\nSolo se puede unir un jugador por cada 3 segundos");
    private final String messageKick2 = ChatColor.translateAlternateColorCodes('&',prefixKick + Colorinfo + "Please &l Do Not Enter&r " + Colorinfo + "it is possible that the server is under a bot attack\nPor favor &l No Entrar&r " + Colorinfo + "es posible que el servidor este bajo un ataque de bots");
    private final String messageKickConsole1 = ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning + "Se expulso al jugador " + Colorplayer + "%p%" + ColorWarning + " por entrar paridamente");
    private final String messageKickConsole2 = ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning + "Se expulso al jugador " + Colorplayer + "%p%" + ColorWarning + " por posible ataque de bots");
    private final String messageKickConsole3 = ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning + "Se le baneo ip del jugador " + Colorplayer + "%p%" + ColorWarning + " por posible ataque de bots");


    @EventHandler(priority = EventPriority.LOW)
    public void AntiBot(AsyncPlayerPreLoginEvent event) {

        if(!SecuritySection.ActiveAntiBot)return;

        if(BlackListIpManager.blackListedIps.contains(event.getAddress().toString())){
            event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
            event.setKickMessage(messageBlackListIp);
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning + "Se Expulso por Ip al jugador: " + event.getName() + " y su ip: " + event.getAddress()));
            return;
        }

        if (getPlayerFileManager().namesPlayres.contains(event.getName())) {
            if (!listAntiBot.containsKey(event.getName())) {
                return;
            }

            if (listAntiBot.get(event.getName()) >= System.currentTimeMillis()) {
                event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
                event.setKickMessage(messageKick);
                Bukkit.getConsoleSender().sendMessage(messageKickConsole1.replace("%p%", event.getName()));
                listAntiBot.put(event.getName(), System.currentTimeMillis() + 3500);
            }
        }else{
            if (coolDownJoin >= System.currentTimeMillis()) {
                String name = event.getName();
                Bukkit.getConsoleSender().sendMessage(messageKickConsole2.replace("%p%", name));
                if (listAntiBotBan.containsKey(event.getName())) {
                    if (listAntiBotBan.get(event.getName()) >= 4) {
                        BlackListIpManager.AddIpBlackList(event.getAddress());
                        Bukkit.getConsoleSender().sendMessage(messageKickConsole3.replace("%p%", name));
                        listAntiBotBan.remove(event.getName());
                    }
                }
                event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
                event.setKickMessage(messageKick2);
                listAntiBotBan.put(name, listAntiBotBan.getOrDefault(name, 0) + 1);
            }
            coolDownJoin = System.currentTimeMillis() + 30000;
        }
    }

    @EventHandler
    public void PlayerLoginEvent(PlayerLoginEvent event) {
        listAntiBot.put(event.getPlayer().getName(), System.currentTimeMillis() + 3000);
    }
}
