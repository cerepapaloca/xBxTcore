package xyz.xbcore.Messages;

import xyz.xbcommun.Messages.Messages.Messages;
import com.comphenix.protocol.events.PacketEvent;
import fr.xephi.authme.api.v3.AuthMeApi;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import xyz.xbcommun.Messages.PacketMessagesListener;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static xyz.xbcommun.Messages.MessageManager.*;

public class MessageListener implements PacketMessagesListener {

    @Override
    public void PacketMessagesEvent(PacketEvent event, String messageId) {
        switch (messageId) {
            case "Crates » givekey" -> event.setCancelled(true);
            case "reward" -> {
                event.setCancelled(true);
                event.getPlayer().sendMessage(MasterMessageLocated(event.getPlayer(), Messages.Reward_ClaimReward));
            }
            case "Crates » You don't have permissions to do that!" -> {
                event.setCancelled(true);
                event.getPlayer().sendMessage(MasterMessageLocated(event.getPlayer(), Messages.Reward_CrateNotPermission));
            }
            default -> {
                if (messageId.startsWith("#%#")) {
                    messageId = messageId.replace("#%#", "");
                    event.setCancelled(true);

                    if(messageId.contains("[")) {
                        String var = messageId.replace(messageId.replaceAll("\\[.*?]", ""), "");
                        Bukkit.getConsoleSender().sendMessage(var);

                        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
                        Matcher matcher = pattern.matcher(var);

                        ArrayList<String> lista = new ArrayList<>();


                        while (matcher.find()) {
                            lista.add(matcher.group(1));
                        }

                        String[] reemplazos = lista.toArray(new String[0]);

                        String mensaje = MasterMessageLocated(event.getPlayer(), Messages.valueOf(messageId.replaceAll("\\[.*?]", "")));

                        pattern = Pattern.compile("\\[(\\d+)]");
                        matcher = pattern.matcher(mensaje);

                        StringBuilder resultado = new StringBuilder();
                        int i = 0;

                        while (matcher.find() && i < reemplazos.length) {
                            matcher.appendReplacement(resultado,reemplazos[i++]);
                        }
                        matcher.appendTail(resultado);
                        Bukkit.getConsoleSender().sendMessage(resultado.toString());
                        event.getPlayer().sendMessage(resultado.toString());
                    }else {
                        event.getPlayer().sendMessage(MasterMessageLocated(event.getPlayer(), Messages.valueOf(messageId)));
                    }
                    if (messageId.equals("Login_Login_Success")){
                        event.getPlayer().setGravity(true);
                        event.getPlayer().teleport(new Location(Bukkit.getWorld("lobby"), 0 , 68, 0));
                        event.getPlayer().getInventory().clear();
                        Bukkit.getConsoleSender().sendMessage( ChatColor.translateAlternateColorCodes('&',prefixConsole + ColorSuccess + "El jugador " + Colorplayer + event.getPlayer().getName() + ColorSuccess + " a iniciado sesion exitosamente"));
                    }
                    if (messageId.equals("Login_Registration_Success")){
                        event.getPlayer().setGravity(true);
                        event.getPlayer().teleport(new Location(Bukkit.getWorld("lobby"), 0 , 68, 0));
                        AuthMeApi.getInstance().forceLogin(event.getPlayer());
                        Bukkit.getConsoleSender().sendMessage( ChatColor.translateAlternateColorCodes('&',prefixConsole + ColorSuccess + "El jugador " + Colorplayer + event.getPlayer().getName() + ColorSuccess + " se registrado exitosamente"));
                    }
                }
            }
        }
    }
}