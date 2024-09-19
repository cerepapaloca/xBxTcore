package Plugin.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.HashSet;
import java.util.UUID;

import static Plugin.Messages.MessageManager.*;

public class MySQLConnection {
    private Connection connection;

    private final String host;
    private final String database;
    private final String user;
    private final String password;

    public static HashSet<byte[]> ipBan = new HashSet<>();
    public static HashSet<UUID> UUIDBan = new HashSet<>();

    public MySQLConnection(String host, String database, String user, String password) {
        this.host = host;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    public void connect() {
        String url = "jdbc:mysql://" + host + "/" + database;
        try {
            connection = DriverManager.getConnection(url, user, password);
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorSuccess +  "Conexión a MySQL establecida."));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
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

    public void reloadBannedIPs() {
        String sql = "SELECT uuid, ip FROM bans";
        ipBan.clear();
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

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
