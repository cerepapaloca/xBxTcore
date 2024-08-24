package Plugin.Listeners;

import Plugin.Managers.PlayerfileManager;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.HashMap;

import static Plugin.Managers.MessageManager.*;
import static Plugin.Managers.MessageManager.ColorWarning;

public class AntiBotListener implements Listener {

    private Long coolDownJoin = System.currentTimeMillis();
    private final HashMap<String, Long> listAntiBot = new HashMap<>();
    private final HashMap<String, Integer> listAntiBotBan = new HashMap<>();
    private final String messageKick = ChatColor.translateAlternateColorCodes('&',prefixKick + Colorinfo + "Only one player can join every 3 seconds\nSolo se puede unir un jugador por cada 3 segundos");
    private final String messageKick2 = ChatColor.translateAlternateColorCodes('&',prefixKick + Colorinfo + "Please &l Do Not Enter&r " + Colorinfo + "it is possible that the server is under a bot attack\nPor favor &l No Entrar&r " + Colorinfo + "es posible que el servidor este bajo un ataque de bots");
    private final String messageKickConsole1 = ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning + "Se expulso al jugador " + Colorplayer + "%p%" + ColorWarning + " por entrar paridamente");
    private final String messageKickConsole2 = ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning + "Se expulso al jugador " + Colorplayer + "%p%" + ColorWarning + " por posible ataque de bots");
    private final String messageKickConsole3 = ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning + "Se le baneo ip del jugador " + Colorplayer + "%p%" + ColorWarning + " por posible ataque de bots");


    @EventHandler
    public void PlayerPreLoginEvent(AsyncPlayerPreLoginEvent event) {

        if (xBxTcore.getPlayerFileManager().namesPlayres.contains(event.getName())) {
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
                    if (listAntiBotBan.get(event.getName()) >= 5) {
                        Bukkit.banIP(event.getAddress());
                        Bukkit.getConsoleSender().sendMessage(messageKickConsole3.replace("%p%", name));
                        listAntiBotBan.remove(event.getName());
                    }
                }
                event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
                event.setKickMessage(messageKick2);
                listAntiBotBan.put(name, listAntiBotBan.getOrDefault(name, 0) + 1);
            }else{
                Bukkit.unbanIP(event.getAddress());
            }
            coolDownJoin = System.currentTimeMillis() + 30000;
        }
    }

    @EventHandler
    public void PlayerLoginEvent(PlayerLoginEvent event) {
        listAntiBot.put(event.getPlayer().getName(), System.currentTimeMillis() + 3000);
    }
}
