package Plugin.File;

import Plugin.Security.SystemBan.ContextBan;
import Plugin.Security.SystemBan.DataBan;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.*;
import java.util.UUID;

import static Plugin.Messages.MessageManager.*;
import static Plugin.Security.SystemBan.BanManager.*;
import static Plugin.Security.SystemBan.BanManager.addListBanIp;

public class MySQLConnection {
    private static Connection connection;

    private static String host;
    private static String database;
    private static String user;
    private static String password;

    public MySQLConnection(String host, String database, String user, String password) {
        MySQLConnection.host = host;
        MySQLConnection.database = database;
        MySQLConnection.user = user;
        MySQLConnection.password = password;
    }

    public static void connect() {
        String url = "jdbc:mysql://" + host + "/" + database;
        try {
            connection = DriverManager.getConnection(url, user, password);
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorSuccess +  "Conexión a MySQL establecida."));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning + "Conexión cerrada, reconectando..."));
                connect();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void reloadBannedBans() {
        String sql = "SELECT uuid, ip, reason, unban_date, context FROM bans";
        clearAll();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String ip = resultSet.getString("ip");
                String name = resultSet.getString("uuid");
                String reason = resultSet.getString("reason");
                long unBan = resultSet.getLong("unban_date");
                String context = resultSet.getString("context");
                DataBan dataBan = new DataBan(UUID.fromString(name), reason, unBan, ContextBan.valueOf(context));
                addListBanUUID(UUID.fromString(name), dataBan);
                addListBanIp(ip, dataBan);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorSuccess +
                "Baneos recargado con exitosamente"));
    }

}
