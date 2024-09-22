package Plugin.Security;

import Plugin.File.MySQLConnection;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.InetAddress;
import java.sql.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

import static Plugin.Messages.MessageManager.*;

public class BanManager implements Listener {

    public static HashSet<byte[]> ipBan = new HashSet<>();
    public static HashSet<UUID> UUIDBan = new HashSet<>();

    private static MySQLConnection mysql;
    private static xBxTcore plugin = null;

    public BanManager(MySQLConnection mysql, xBxTcore plugin) {
        BanManager.mysql = mysql;
        BanManager.plugin = plugin;
    }

    @EventHandler (priority = EventPriority.NORMAL)
    public void onPlayerLogin(@NotNull PlayerLoginEvent event) {
        String reason = checkBanPlayer(event.getAddress(), event.getPlayer(), "global");
        if (reason != null){
            event.setResult(PlayerLoginEvent.Result.KICK_BANNED);
            event.setKickMessage(reason);
        }
    }

    public static void unbanPlayer(String name) {
        String sql = "DELETE FROM bans WHERE name = ?";
        try (Connection connection = mysql.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.executeUpdate();
            mysql.reloadBannedIPs();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void banPlayer(Player player, String reason, long unbanDate) {
        banPlayer(Objects.requireNonNull(player.getAddress()).getHostName(), player.getUniqueId().toString(), player.getName(), reason, System.currentTimeMillis(),System.currentTimeMillis() + unbanDate, "global");
    }

    public static void banPlayer(Player player, String reason, long unbanDate, String context) {
        banPlayer(Objects.requireNonNull(player.getAddress()).getHostName(), player.getUniqueId().toString(), player.getName(), reason, System.currentTimeMillis(),System.currentTimeMillis() + unbanDate, context);
    }

    public static void banPlayer(String ip, String uuid, String name, String reason, long banDate, long unbanDate, String context) {
        String sql = "INSERT INTO bans (uuid, name, ip, reason, ban_date, unban_date, context) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE uuid = VALUES(uuid), name = VALUES(name), reason = VALUES(reason), " +
                "ban_date = VALUES(ban_date), unban_date = VALUES(unban_date), context = VALUES(context)";
        try (Connection connection = mysql.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, uuid);
            statement.setString(2, name);
            statement.setString(3, ip);
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
            Bukkit.getScheduler().runTask(plugin, () -> Objects.requireNonNull(Bukkit.getPlayer(name)).kickPlayer(reasonFinal));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorplayer + name +  Colorinfo + " Baneado: \n" +
                    Colorinfo + "Tiempo Baneado: " + Colorplayer + Utils.SecondToMinutes(unbanDate - banDate) + "\n" +
                    Colorinfo + "Razón de baneo: " + Colorplayer + reason + "\n"  +
                    Colorinfo + "Contexto: " + Colorplayer + context));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String checkBanPlayer(@NotNull InetAddress ipPlayer, @NotNull Player player,@Nullable String context) {
        byte[] ipByte = ipPlayer.getAddress();
        boolean checkName = UUIDBan.contains(player.getUniqueId());
        boolean checkIp = false;

        for (byte[] bytes : ipBan){
            if (Arrays.equals(bytes, ipByte)){
                checkIp = true;
                break;
            }
        }

        if (checkName || checkIp){
            final ResultSet resultSet = getDataMySQL(player, checkName);
            if (resultSet == null)return null;

            try {
                if (resultSet.next()) {
                    if (context == null || resultSet.getString("context").equals(context)) {
                        long unbanDate = resultSet.getLong("unban_date");
                        long currentTime = System.currentTimeMillis();

                        if (currentTime < unbanDate) {
                            if (Objects.equals(context, "global")) context = "xBxTpvp.xyz";
                            String reason = ChatColor.translateAlternateColorCodes('&', prefixKick + Colorinfo + "Haz sido baneado de &o" + context + "&r\n" +
                                    Colorinfo + "Expira en: " + Colorplayer + Utils.SecondToMinutes(unbanDate - currentTime) + "\n" +
                                    Colorinfo + "Razón de baneo: " + Colorplayer + resultSet.getString("reason") + "\n" +
                                    Colorinfo + "Apelación de ban: " + LinkDiscord);
                            player.kickPlayer(reason);
                            return reason;
                        } else {
                            unbanPlayer(player.getName());
                            return null;
                        }
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        }
        return null;
    }

    private static ResultSet getDataMySQL(@NotNull Player player, boolean checkName) {
        ResultSet resultSet;
        if (checkName){
            try (Connection connection = mysql.getConnection();
                 PreparedStatement statement = connection.prepareStatement("SELECT * FROM bans WHERE name = ?")) {
                statement.setString(1, player.getName());
                resultSet = statement.executeQuery();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            try (Connection connection = mysql.getConnection();
                 PreparedStatement statement = connection.prepareStatement("SELECT * FROM bans WHERE ip = ?")) {
                statement.setString(1, player.getName());
                resultSet = statement.executeQuery();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return resultSet;
    }

}