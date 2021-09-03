package ir.mctracker.mctrackervote.database;

import ir.mctracker.mctrackervote.MCTrackerVote;
import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {
    public static void executeQuery(String query) {
        Bukkit.getScheduler().runTaskAsynchronously(MCTrackerVote.getInst(), new Runnable() {
            @Override
            public void run() {
                try {
                    final Statement statement = SQLDataSource.getConnection().createStatement();
                    statement.execute(query);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static ResultSet getResult(String query) {
        ResultSet result = null;
        try {
            PreparedStatement statement = SQLDataSource.getConnection().prepareStatement(query);
            try  {
                final ResultSet resultSet = statement.executeQuery();
                result = resultSet;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void closeConnection() {
        try {
            SQLDataSource.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
