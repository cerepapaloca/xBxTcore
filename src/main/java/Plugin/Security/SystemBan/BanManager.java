package Plugin.Security.SystemBan;

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
        String reason = checkBanPlayer(event.getAddress(), event.getPlayer(), ContextBan.GLOBAL);
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
            mysql.reloadBannedBans();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void banPlayer(Player player, String reason, long unbanDate) {
        banPlayer(Objects.requireNonNull(player.getAddress()).getHostName(), player.getUniqueId().toString(), player.getName(), reason, System.currentTimeMillis(),System.currentTimeMillis() + unbanDate, "GLOBAL");
    }

    public static void banPlayer(Player player, String reason, long unbanDate, ContextBan context) {
        banPlayer(Objects.requireNonNull(player.getAddress()).getHostName(), player.getUniqueId().toString(), player.getName(), reason, System.currentTimeMillis(),System.currentTimeMillis() + unbanDate, context.name());
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
            MySQLConnection.reloadBannedBans();


            if (context.equals("GLOBAL")) context = "xBxTpvp.xyz";
            context = context.toLowerCase().replace("_", " ");
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
    public static @Nullable String checkBanPlayer(@Nullable InetAddress ipPlayer, @NotNull Player player,@NotNull ContextBan context) {
        return checkBanPlayerMaster(ipPlayer, player, context);
    }

    public static @Nullable String checkBanPlayer(@NotNull Player player,@Nullable ContextBan context) {
        return checkBanPlayerMaster(player.getAddress().getAddress(), player, context);
    }

    public static @Nullable String checkBanPlayer(@NotNull Player player) {
        return checkBanPlayerMaster(player.getAddress().getAddress(), player, null);
    }

    private static @Nullable String checkBanPlayerMaster(@Nullable InetAddress ipPlayer, @NotNull Player player, @Nullable ContextBan context) {

        boolean checkName = UUIDBan.contains(player.getUniqueId());
        boolean checkIp = false;

        if (ipPlayer != null) {
            byte[] ipByte = ipPlayer.getAddress();
            for (byte[] bytes : ipBan) {
                if (Arrays.equals(bytes, ipByte)) {
                    checkIp = true;
                    break;
                }
            }
        }

        if (checkName || checkIp) {
            DataBan dataBan = getDataMySQL(player, checkName, ipPlayer);
            if (dataBan == null) {
                MySQLConnection.reloadBannedBans();
                return null;
            }

            if (context == null){
                context = ContextBan.GLOBAL;
            }

            if (dataBan.getContext().equals(context)) {
                long unbanDate = dataBan.getUnbanTime();
                long currentTime = System.currentTimeMillis();

                if (currentTime < unbanDate) {
                    String contextName = context.name().toLowerCase().replace("_", " ");
                    if (Objects.equals(context.name(), "GLOBAL")) contextName = "xBxTpvp.xyz";

                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning + dataBan.getPlayer().getName() +
                            "ingreso estando vetado"));
                    String reason = ChatColor.translateAlternateColorCodes('&', prefixKick + Colorinfo + "Haz sido baneado de &o" + contextName + "&r\n" +
                            Colorinfo + "Expira en: " + Colorplayer + Utils.SecondToMinutes(unbanDate - currentTime) + "\n" +
                            Colorinfo + "Razón de baneo: " + Colorplayer + dataBan.getReason() + "\n" +
                            Colorinfo + "Apelación de ban: " + LinkDiscord);
                    Bukkit.getScheduler().runTask(plugin, () -> player.kickPlayer(reason));

                    return reason;
                } else {
                    unbanPlayer(player.getName());
                    return null;
                }
            } else{
                return null;
            }
        } else {
            return null;
        }
    }

    private static @Nullable DataBan getDataMySQL(@NotNull Player player, boolean checkName, InetAddress address) {
        ResultSet resultSet;
        if (checkName) {
            try (Connection connection = MySQLConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement("SELECT * FROM bans WHERE name = ?")) {
                statement.setString(1, player.getName());
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return new DataBan(player, resultSet.getString("reason"), resultSet.getLong("unban_date"), ContextBan.valueOf(resultSet.getString("context")));
                } else {
                    return null;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try (Connection connection = MySQLConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement("SELECT * FROM bans WHERE ip = ?")) {
                statement.setString(1, address.getHostAddress());
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return new DataBan(player, resultSet.getString("reason"), resultSet.getLong("unban_date"), ContextBan.valueOf(resultSet.getString("context")));
                } else {
                    return null;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}