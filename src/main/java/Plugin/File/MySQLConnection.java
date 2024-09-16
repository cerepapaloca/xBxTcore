package Plugin.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import javax.swing.plaf.BorderUIResource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.HashSet;

import static Plugin.Messages.MessageManager.*;

public class MySQLConnection {
    private Connection connection;

    private final String host;
    private final String database;
    private final String user;
    private final String password;

    public static HashSet<byte[]> ipBan = new HashSet<>();

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
            e.printStackTrace();
        }
        return connection;
    }

    public void reloadBannedIPs() {
        String sql = "SELECT ip FROM bans";
        ipBan.clear();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String ip = resultSet.getString("ip");
                ipBan.add(InetAddress.getByName(ip).getAddress());
            }
        } catch (SQLException | UnknownHostException e) {
            e.printStackTrace();
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo +
                "hay " + ipBan.size() + " ips baneadas"));
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
