package ir.mctracker.core.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import ir.jeykey.megacore.config.premade.Storage;
import ir.mctracker.core.database.models.Vote;
import ir.mctracker.MCTrackerVote;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class DataSource {
    private static ConnectionSource connectionSource;
    private static Dao<Vote, Integer> voteDao;

    public DataSource(MCTrackerVote plugin) throws SQLException, IOException {
        File file = new File(plugin.getDataFolder(), "data.db");
        if (!file.exists()) file.createNewFile();

        String databaseUrl = "jdbc:sqlite:" + plugin.getDataFolder() + "/data.db";

        connectionSource = new JdbcConnectionSource(databaseUrl);

        setupTables();

        // TODO: Make the new Storage System Indeed to work with MySQL/MariaDB & Redis
    }

    public void setupTables() throws SQLException {
        voteDao = DaoManager.createDao(connectionSource, Vote.class);
        TableUtils.createTableIfNotExists(connectionSource, Vote.class);
    }

    public static Dao<Vote, Integer> getVoteDao() {
        return voteDao;
    }
}