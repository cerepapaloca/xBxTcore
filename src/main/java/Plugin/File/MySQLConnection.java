package Plugin.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.UUID;

import static Plugin.Messages.MessageManager.*;
import static Plugin.Security.SystemBan.BanManager.UUIDBan;
import static Plugin.Security.SystemBan.BanManager.ipBan;

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
        String sql = "SELECT uuid, ip FROM bans";
        ipBan.clear();
        UUIDBan.clear();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String ip = resultSet.getString("ip");
                ipBan.add(InetAddress.getByName(ip).getAddress());
                String name = resultSet.getString("uuid");
                UUIDBan.add(UUID.fromString(name));
            }
        } catch (SQLException | UnknownHostException e) {
            throw new RuntimeException(e);
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo +
                "hay " + ipBan.size() + " jugadores baneados"));
    }

}
