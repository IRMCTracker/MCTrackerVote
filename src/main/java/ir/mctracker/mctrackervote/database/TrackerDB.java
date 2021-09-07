package ir.mctracker.mctrackervote.database;

import ir.mctracker.mctrackervote.MCTrackerVote;
import ir.mctracker.mctrackervote.database.models.Vote;
import org.bukkit.Bukkit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrackerDB {
    public static void executeQueryAsync(PreparedStatement statement) {
        Bukkit.getScheduler().runTaskAsynchronously(MCTrackerVote.getInst(), new Runnable() {
            @Override
            public void run() {
                try {
                    statement.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void createTables() {
        try (final Statement statement = SQLDataSource.getConnection().createStatement()) {
            statement.execute(Queries.CREATE_VOTES_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertVote(String username, Integer votedAt, Integer totalVotes) {
        try {
            PreparedStatement pst = SQLDataSource.getConnection().prepareStatement(Queries.INSERT_VOTE);

            pst.setString(1, username);
            pst.setInt(2, votedAt);
            pst.setInt(3, totalVotes);

            executeQueryAsync(pst);

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


    public static void redeemVote(String username) {
        try {
            PreparedStatement pst = SQLDataSource.getConnection().prepareStatement(Queries.REDEEM_VOTE);

            pst.setString(1, username);

            executeQueryAsync(pst);

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void deleteVote(String username, Integer votedAt) {
        try {
            PreparedStatement pst = SQLDataSource.getConnection().prepareStatement(Queries.DELETE_VOTE);

            pst.setString(1, username);
            pst.setInt(2, votedAt);

            executeQueryAsync(pst);

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static List<Vote> getUnredeemedVotes() {
        List<Vote> votes = null;
        try (Connection con = SQLDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement( Queries.SELECT_UNREDEEMED_VOTES );
             ResultSet rs = pst.executeQuery()) {

            votes = new ArrayList<>();

            Vote vote;
            while ( rs.next() ) {
                vote = new Vote();

                vote.setUsername(rs.getString("username"));
                vote.setVotedAt(rs.getInt("voted_at"));
                vote.setTotalVotes(rs.getInt("total_votes"));

                votes.add(vote);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return votes;
    }

    public static List<Vote> getAllVotes() {
        List<Vote> votes = null;
        try (Connection con = SQLDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement( Queries.SELECT_ALL_VOTES );
             ResultSet rs = pst.executeQuery()) {

            votes = new ArrayList<>();

            Vote vote;
            while ( rs.next() ) {
                vote = new Vote();

                vote.setUsername(rs.getString("username"));
                vote.setVotedAt(rs.getInt("voted_at"));
                vote.setTotalVotes(rs.getInt("total_votes"));

                votes.add(vote);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return votes;
    }


    public static Vote getVote(String username, Integer votedAt) {
        Vote vote = null;

        try (Connection con = SQLDataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement( Queries.SELECT_VOTE );

            pst.setString(1, username);
            pst.setInt(2, votedAt);

            ResultSet rs = pst.executeQuery();

            while ( rs.next() ) {
                vote = new Vote();

                vote.setUsername(rs.getString("username"));
                vote.setVotedAt(rs.getInt("voted_at"));
                vote.setTotalVotes(rs.getInt("total_votes"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return vote;
    }

    public static void closeConnection() {
        try {
            SQLDataSource.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
