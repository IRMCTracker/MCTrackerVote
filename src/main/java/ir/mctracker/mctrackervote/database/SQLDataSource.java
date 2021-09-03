package ir.mctracker.mctrackervote.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ir.mctracker.mctrackervote.MCTrackerVote;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLDataSource {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    private static Connection connection;

    public static void SQLite() throws SQLException, IOException {
        File file = new File(MCTrackerVote.getInst().getDataFolder(), "data.db");
        if (!file.exists()) file.createNewFile();

        config.setJdbcUrl("jdbc:sqlite:" + MCTrackerVote.getInst().getDataFolder() + "/data.db");
        config.setConnectionTestQuery("SELECT 1");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
        connection = ds.getConnection();

        try (final Statement statement = ds.getConnection().createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS tracker_votes (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "uuid VARCHAR(16) NOT NULL," +
                    "voted_at BIGINT NOT NULL" +
                    ");");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private SQLDataSource() { }

    public static Connection getConnection() throws SQLException {
        return connection;
    }
}
