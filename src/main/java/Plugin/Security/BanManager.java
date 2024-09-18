package Plugin.Security;

import Plugin.File.MySQLConnection;
import Plugin.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;
import java.sql.*;
import java.util.Arrays;
import java.util.Objects;

import static Plugin.File.MySQLConnection.ipBan;
import static Plugin.Messages.MessageManager.*;

public class BanManager implements Listener {
    private static MySQLConnection mysql;

    public BanManager(MySQLConnection mysql) {
        BanManager.mysql = mysql;
    }

    @EventHandler (priority = EventPriority.NORMAL)
    public void onPlayerLogin(@NotNull PlayerLoginEvent event) {
        String ip = event.getAddress().getHostAddress();
        byte[] ipByte = event.getAddress().getAddress();
        for (byte[] bytes : ipBan){
            if (Arrays.equals(ipByte, bytes)){
                try (Connection connection = mysql.getConnection();
                    PreparedStatement statement = connection.prepareStatement("SELECT * FROM bans WHERE ip = ?")) {
                    statement.setString(1, ip);
                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        if (resultSet.getString("context").equals("global")) {
                            long unbanDate = resultSet.getLong("unban_date");
                            long currentTime = System.currentTimeMillis();

                            if (currentTime < unbanDate) {
                                String reason = ChatColor.translateAlternateColorCodes('&', prefixKick + Colorinfo + "Haz sido baneado de &oxBxTpvp.xyz&r\n" +
                                        Colorinfo + "Expira en: " + Colorplayer + Utils.SecondToMinutes(unbanDate - currentTime) + "\n" +
                                        Colorinfo + "Razón de baneo: " + Colorplayer + resultSet.getString("reason") + "\n" +
                                        Colorinfo + "Apelación de ban: " + LinkDiscord);
                                event.setKickMessage(reason);
                                event.setResult(PlayerLoginEvent.Result.KICK_BANNED);
                            } else {
                                unbanPlayer(ip);
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void unbanPlayer(String ip) {
        String sql = "DELETE FROM bans WHERE ip = ?";
        try (Connection connection = mysql.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, ip);
            statement.executeUpdate();
            mysql.reloadBannedIPs();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void banPlayer(String ip,String uuid, String name, String reason, long banDate, long unbanDate, String context) {
        String sql = "INSERT INTO bans (ip, uuid, name, reason, ban_date, unban_date, context) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE uuid = VALUES(uuid), name = VALUES(name), reason = VALUES(reason), " +
                "ban_date = VALUES(ban_date), unban_date = VALUES(unban_date), context = VALUES(context)";
        try (Connection connection = mysql.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, ip);
            statement.setString(2, name);
            statement.setString(3, uuid);
            statement.setString(4, reason);
            statement.setLong(5, banDate);
            statement.setLong(6, unbanDate);
            statement.setString(7, context);
            statement.executeUpdate();
            mysql.reloadBannedIPs();


            if (context.equals("global")) context = "xBxTpvp.xyz";
            String reasonFinal = ChatColor.translateAlternateColorCodes('&', prefixKick + Colorinfo + "Haz sido baneado de &o" + context + "&r\n" +
                    Colorinfo + "Expira en: " + Colorplayer + Utils.SecondToMinutes(unbanDate - banDate) + "\n" +
                    Colorinfo + "Razón de baneo: " + Colorplayer + reason + "\n" +
                    Colorinfo + "Apelación de ban: " + LinkDiscord);
            Objects.requireNonNull(Bukkit.getPlayer(uuid)).kickPlayer(reasonFinal);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkBanPlayer(InetAddress ipPlayer, Player player, String context) {
        String ip = ipPlayer.getHostAddress();
        byte[] ipByte = ipPlayer.getAddress();
        for (byte[] bytes : ipBan){
            if (Arrays.equals(ipByte, bytes)){
                try (Connection connection = mysql.getConnection();
                     PreparedStatement statement = connection.prepareStatement("SELECT * FROM bans WHERE ip = ?")) {
                    statement.setString(1, ip);
                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        if (resultSet.getString("context").equals(context)) {
                            long unbanDate = resultSet.getLong("unban_date");
                            long currentTime = System.currentTimeMillis();

                            if (currentTime < unbanDate) {
                                if (context.equals("global")) context = "xBxTpvp.xyz";
                                String reason = ChatColor.translateAlternateColorCodes('&', prefixKick + Colorinfo + "Haz sido baneado de &o" + context + "&r\n" +
                                        Colorinfo + "Expira en: " + Colorplayer + Utils.SecondToMinutes(unbanDate - currentTime) + "\n" +
                                        Colorinfo + "Razón de baneo: " + Colorplayer + resultSet.getString("reason") + "\n" +
                                        Colorinfo + "Apelación de ban: " + LinkDiscord);
                                player.kickPlayer(reason);
                                return true;
                            } else {
                                unbanPlayer(ip);
                                return false;
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return false;
            }
        }
        return false;
    }

}