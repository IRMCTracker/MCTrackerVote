package ir.mctracker.mctrackervote.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import ir.mctracker.mctrackervote.MCTrackerVote;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.*;

@DatabaseTable(tableName = "mctracker_votes") @Getter @Setter @NoArgsConstructor
public class Vote {
    @DatabaseField(columnName = "id", generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String username;

    @DatabaseField(columnName = "voted_at", canBeNull = false)
    private int votedAt;

    @DatabaseField(canBeNull = false)
    private boolean redeemed;

    public Vote(String username) {
        this(username, 0);
    }

    public Vote(JSONObject jsonVote) {
        this(jsonVote.getString("username"), jsonVote.getInt("voted_at"));
    }

    public Vote(String username, int votedAt) {
        this(0, username, votedAt, false);
    }

    public Vote(int id, String username, int votedAt, boolean redeemed) {
        setId(id);
        setUsername(username);
        setVotedAt(votedAt);
        setRedeemed(redeemed);
    }

    public boolean isExpired() {
        return this.votedAt - System.currentTimeMillis() > ((60 * 60) * 24);
    }

    public boolean delete()
    {
        try {
            MCTrackerVote.getVotesDao().delete(this);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean create() {
        try {
            MCTrackerVote.getVotesDao().create(this);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void redeem() throws SQLException {
        setRedeemed(true);
        update();
    }

    public void update() throws SQLException {
        MCTrackerVote.getVotesDao().update(this);
    }

    public boolean fetch() {
        if (this.username == null || this.votedAt == 0) return false;

        try {
            Map<String, Object> object = new HashMap<>();
            object.put("username", getUsername());
            object.put("voted_at", getVotedAt());
            Optional<Vote> optionalVote = MCTrackerVote.getVotesDao().queryForFieldValues(object).stream().findFirst();

            if (optionalVote.isPresent()) {
                Vote vote = optionalVote.get();
                setId(vote.getId());
                setUsername(vote.getUsername());
                setVotedAt(vote.getVotedAt());
                setRedeemed(vote.isRedeemed());
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Vote> all() {
        try {
            return MCTrackerVote
                    .getVotesDao()
                    .queryBuilder()
                    .orderBy("id", false)
                    .query();
        } catch (SQLException ignored) {
            return new ArrayList<>();
        }
    }

    public static List<Vote> getUnredeemed() {
        try {
            return MCTrackerVote
                    .getVotesDao()
                    .queryForEq("redeemed", false);
        } catch (SQLException ignored) {
            return new ArrayList<>();
        }
    }
}
