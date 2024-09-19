package Plugin.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static Plugin.Messages.MessageManager.ColorSuccess;
import static Plugin.Messages.MessageManager.Colorinfo;

public class MySQLManager {

    private final MySQLConnection mysql;

    public MySQLManager(MySQLConnection mysql) {
        this.mysql = mysql;
    }

    public void createBanTableIfNotExists() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS bans (" +
                "uuid VARCHAR(100) NOT NULL, " +
                "name VARCHAR(36), " +
                "ip VARCHAR(45) NOT NULL, " +
                "reason TEXT, " +
                "ban_date BIGINT, " +
                "unban_date BIGINT, " +
                "context VARCHAR(255), " +
                "PRIMARY KEY (uuid), " +
                "UNIQUE (uuid)" +
                ");";

        try (Connection connection = mysql.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableSQL);
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', Colorinfo + "DataBaseMySQL " + ColorSuccess + "Ok"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
