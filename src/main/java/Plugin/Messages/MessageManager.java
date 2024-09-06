package Plugin.Messages;

import Plugin.Messages.Enum.Messages;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static Plugin.Messages.Enum.Messages.*;
import static Plugin.Messages.Messages.MessagesEN.enMessages;
import static Plugin.Messages.Messages.MessagesES.esMessages;

public class MessageManager {

    public static String prefixKick = "&6[&8&lxB&f&lxT &e&lPvP&6]&r \n";
    public static String prefixConsole = "&6[&8&lxB&f&lxT &eConsoler&6]&r ";
    public static String prefix = "&6[" + ChatColor.of("#61CAFD") + "&lx" + ChatColor.of("#7CAFEC") + "&lB" + ChatColor.of("#9893DC")
            + "&lx" + ChatColor.of("#B378CB") + "&lT" + ChatColor.of("#FDC661") + " &lC" + ChatColor.of("#FEAA41") + "&lo" +
            ChatColor.of("#FE8F22") + "&lr" + ChatColor.of("#FF7302") + "&le" + "&6]&r ";
    public static String prefixDuel = "&8[&4⚔&8] ";
    public static String prefixDied = "&8[&4☠&8] ";

    public static String ColorError = "&c";
    public static String ColorSuccess = "&a";
    public static String ColorWarning = "&e";
    public static String Colorinfo = "&3";
    public static String Colorplayer = "&6";
    public static String ColorLink = "&a&n";
    public static String Coloritem = "&b";

    private static final String esErrorMensaje = prefix + ColorError + "&LHubo un error con el mensaje: '%message%' Contacta rapidamente con el dueño del servidor";
    private static final String enErrorMensaje = prefix + ColorError + "&LThere was an error with the message: '%message%' Please contact the server owner quickly";

    public static String MasterMessageLocated(@NotNull Player player,@NotNull Messages message){
        String locate = player.getLocale().toLowerCase();
        if (locate.contains("es")){
            if (esMessages.get(message) != null){
                return ChatColor.translateAlternateColorCodes('&', esMessages.get(message));
            }else{
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', esErrorMensaje).replace("%message%", message.name()));
                return ChatColor.translateAlternateColorCodes('&', esErrorMensaje).replace("%message%", message.name());
            }
        }else{
            if (enMessages.get(message) != null){
                return ChatColor.translateAlternateColorCodes('&', enMessages.get(message));
            }else{
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', enErrorMensaje).replace("%message%", message.name()));
                return ChatColor.translateAlternateColorCodes('&', enErrorMensaje).replace("%message%", message.name());
            }
        }
    }

    public static void BroadcastMessage(Messages message){
        for(Player p : Bukkit.getOnlinePlayers()){
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', MasterMessageLocated(p,message)));
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', esMessages.get(message)));
    }

    public static void BroadcastMessageBuy(String compra, Player player, Messages message){
        for(Player p : Bukkit.getOnlinePlayers()){
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', MasterMessageLocated(p,message)).replace("%player%", player.getDisplayName()).replace("%compra%", compra));
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', esMessages.get(message)).replace("%player%", player.getDisplayName()).replace("%compra%", compra));
    }

    public static void BroadcastMessageDied(Messages message, Player player, Player killer, String item){
        String prefixWorld;

        switch (player.getWorld().getName()){
            case "lobby" -> prefixWorld = "&8[&9FFA&8]&r ";
            case "boxpvp" -> prefixWorld = "&8[&4BOXPVP&8]&r ";
            default -> prefixWorld = "&8[&eDUEL&8]&r ";
        }

        for(Player p : Bukkit.getOnlinePlayers()){
            if(killer != null){
                p.sendMessage(ChatColor.translateAlternateColorCodes( '&', MasterMessageLocated(p,message).replace("%player%", prefixWorld + Colorplayer +  player.getName()).replace("%killer%", Colorplayer + killer.getName()).replace("%item%", item)));
            }else{
                p.sendMessage(ChatColor.translateAlternateColorCodes( '&', MasterMessageLocated(p,message).replace("%player%", prefixWorld +  Colorplayer +  player.getName()).replace("%item%", item)));
            }

        }
        if(killer == null){
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', esMessages.get(message)).replace("%player%",ChatColor.translateAlternateColorCodes('&',prefixWorld + Colorplayer +  player.getName()).replace("%item%", item)));
        }else{
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ChatColor.translateAlternateColorCodes('&', esMessages.get(message)).replace("%player%", prefixWorld + Colorplayer +  player.getName()).replace("%killer%", Colorplayer + killer.getName()).replace("%item%", item)));
        }
    }

    public static void KickMessage(Player p ,Messages message){
        p.kickPlayer(MasterMessageLocated(p,message));
    }

    public static void BroadcastMessagejoin(Player player){
        for(Player p : Bukkit.getOnlinePlayers()){
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&a+&8] " + Colorplayer + player.getName() + " " + MasterMessageLocated(p,Messages.Event_join)));
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&a+&8] " + Colorplayer + player.getName() + " " + esMessages.get(Event_join)));
    }

    public static void BroadcastMessageleave(Player player){
        for(Player p : Bukkit.getOnlinePlayers()){
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&4-&8] " + Colorplayer + player.getName() + " " + MasterMessageLocated(p,Messages.Event_leave)));

        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&4-&8] " + Colorplayer + player.getName() + " " + esMessages.get(Event_leave)));
    }

    public static void SendMessageConsole(String nota, Thread thread){
        String nameClas = thread.getStackTrace()[1].getClassLoaderName();
        String nameMethod = thread.getStackTrace()[1].getMethodName();
        int nameLine = thread.getStackTrace()[1].getLineNumber();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning + "Hubo un error " +
                "en la clase: " + nameClas + " en el metodo: " + nameMethod + " en la linea " + nameLine + "\nNota: " + nota));
    }
}