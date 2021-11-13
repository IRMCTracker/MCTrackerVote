package ir.mctracker.mctrackervote.database;

public class Queries {

    public static String SELECT_VOTE = "SELECT * FROM tracker_votes WHERE username = ? AND voted_at = ?";
    public static String INSERT_VOTE = "INSERT INTO tracker_votes (username, voted_at, total_votes, redeemed) VALUES (?, ?, ?, 'false')";
    public static String REDEEM_VOTE = "UPDATE tracker_votes SET redeemed = 'true' WHERE username = ?";
    public static String DELETE_VOTE = "DELETE FROM tracker_votes WHERE username = ?";
    public static String SELECT_ALL_VOTES = "SELECT * FROM tracker_votes";
    public static String SELECT_UNREDEEMED_VOTES = "SELECT * FROM tracker_votes WHERE redeemed = 'false' LIMIT ?;";
    public static String CREATE_VOTES_TABLE = "CREATE TABLE IF NOT EXISTS tracker_votes (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "username VARCHAR(50) NOT NULL," +
            "voted_at BIGINT NOT NULL," +
            "total_votes BIGINT NOT NULL," +
            "redeemed BOOLEAN NOT NULL" +
            ");";
}
