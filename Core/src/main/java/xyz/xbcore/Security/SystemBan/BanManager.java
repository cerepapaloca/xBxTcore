package xyz.xbcore.Security.SystemBan;

import xyz.xbcore.File.MySQLConnection;
import xyz.xbcommun.Utils.UtilsGlobal;
import xyz.xbcore.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.*;

import static xyz.xbcommun.Messages.MessageManager.*;

public class BanManager implements Listener {

    public static HashMap<UUID, DataBan> listBanUUID = new HashMap<>();
    public static HashMap<byte[], DataBan> listBanIP = new HashMap<>();

    private static MySQLConnection mysql;
    private static xBxTcore plugin = null;

    public BanManager(MySQLConnection mysql, xBxTcore plugin) {
        BanManager.mysql = mysql;
        BanManager.plugin = plugin;
    }

    public static void addListBanUUID(UUID uuid, DataBan listBan) {
        listBanUUID.put(uuid, listBan);
    }

    public static void addListBanIp(String ip, DataBan listBan) {
        try {
            listBanIP.put(InetAddress.getByName(ip).getAddress(), listBan);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public static void clearAll(){
        listBanUUID.clear();
        listBanIP.clear();
    }

    @EventHandler (priority = EventPriority.NORMAL)
    public void onPlayerLogin(@NotNull PlayerLoginEvent event) {
        String reason = checkBanPlayer(event.getAddress(), event.getPlayer(), ContextBan.GLOBAL);
        if (reason != null){
            event.setResult(PlayerLoginEvent.Result.KICK_BANNED);
            event.setKickMessage(reason);
        }

    }

    @EventHandler
    public void onInventoryClick(@NotNull InventoryClickEvent event) {
        AutoBan.checkDupes((Player) event.getWhoClicked());
    }

    public static void unbanPlayer(String name) {
        String sql = "DELETE FROM bans WHERE name = ?";
        try (Connection connection = mysql.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.executeUpdate();
            MySQLConnection.reloadBannedBans();
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
                    Colorinfo + "Expira en: " + Colorplayer + UtilsGlobal.TimeToString(unbanDate - banDate, 1) + "\n" +
                    Colorinfo + "Razón de baneo: " + Colorplayer + reason + "\n" +
                    Colorinfo + "Apelación de ban: " + LinkDiscord);
            Bukkit.getScheduler().runTask(plugin, () -> Objects.requireNonNull(Bukkit.getPlayer(name)).kickPlayer(reasonFinal));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorplayer + name +  Colorinfo + " Baneado: \n" +
                    Colorinfo + "Tiempo Baneado: " + Colorplayer + UtilsGlobal.TimeToString(unbanDate - banDate, 1) + "\n" +
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
        boolean checkName = listBanUUID.containsKey(player.getUniqueId());
        boolean checkIp = false;
        DataBan dataBan = null;

        if (ipPlayer != null) {
            byte[] ipByte = ipPlayer.getAddress();
            for (byte[] bytes : listBanIP.keySet()) {
                if (Arrays.equals(bytes, ipByte)) {
                    dataBan = listBanIP.get(bytes);
                    checkIp = true;
                    break;
                }
            }
        }

        if (checkName){
            dataBan = listBanUUID.get(player.getUniqueId());
        }

        if (checkName || checkIp) {

            if (dataBan == null) {
                dataBan = getDataMySQL(player, checkName, ipPlayer);
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

                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning + player.getName() +
                            " se echo por que estar baneado de: " + contextName + ". Se detecto por Nombre:" + checkName + " por ip:" + checkIp));
                    String reason = ChatColor.translateAlternateColorCodes('&', prefixKick + Colorinfo + "Haz sido baneado de &o" + contextName + "&r\n" +
                            Colorinfo + "Expira en: " + Colorplayer + UtilsGlobal.TimeToString(unbanDate - currentTime, 1) + "\n" +
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
                    return new DataBan(player.getUniqueId(), resultSet.getString("reason"), resultSet.getLong("unban_date"), ContextBan.valueOf(resultSet.getString("context")));
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
                    return new DataBan(player.getUniqueId(), resultSet.getString("reason"), resultSet.getLong("unban_date"), ContextBan.valueOf(resultSet.getString("context")));
                } else {
                    return null;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}