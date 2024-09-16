package Plugin.Security;

import Plugin.File.MySQLConnection;
import Plugin.Utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import static Plugin.File.MySQLConnection.ipBan;
import static Plugin.Messages.MessageManager.*;

public class BanManager implements Listener {
    private final MySQLConnection mysql;

    public BanManager(MySQLConnection mysql) {
        this.mysql = mysql;
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
                                String reason = ChatColor.translateAlternateColorCodes('&', prefixKick + Colorinfo + "Haz sido baneado de &oxbxtpvp.xyz&r" +
                                         Colorinfo + "Expira en: " + Colorplayer + Utils.SecondToMinutes(currentTime - unbanDate) + "\n" +
                                         "Razón de baneo: " + resultSet.getString("reason"));
                                event.disallow(PlayerLoginEvent.Result.KICK_BANNED, "Baneado por: " + reason);
                            } else {
                                // El jugador ya debería ser desbaneado
                                unbanPlayer(ip);
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
    }

    private void unbanPlayer(String ip) {
        String sql = "DELETE FROM bans WHERE ip = ?";
        try (Connection connection = mysql.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, ip);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void banPlayer(String ip,String uuid, String name, String reason, long banDate, long unbanDate, String context) {
        String sql = "INSERT INTO bans (ip,uuid, name, reason, ban_date, unban_date, context) VALUES (?, ?, ?, ?, ?, ?, ?)";

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}