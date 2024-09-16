package Plugin.File;

import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLManager {

    private final MySQLConnection mysql;

    public MySQLManager(MySQLConnection mysql) {
        this.mysql = mysql;
    }

    public void createBanTableIfNotExists() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS bans (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "ip VARCHAR(45), " +
                "uuid VARCHAR(36) NOT NULL, " +
                "name VARCHAR(16) NOT NULL, " +
                "reason TEXT NOT NULL, " +
                "ban_date BIGINT NOT NULL, " +
                "unban_date BIGINT NOT NULL," +
                "context TEXT NOT NULL" +
                ");";

        try (Connection connection = mysql.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableSQL);
            Bukkit.getConsoleSender().sendMessage("Tabla 'bans' verificada o creada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
