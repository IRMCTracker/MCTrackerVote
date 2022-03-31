package ir.mctracker.mctrackervote.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

@DatabaseTable(tableName = "mctracker_votes") @Getter @Setter @NoArgsConstructor
public class Vote {
    @DatabaseField(columnName = "id", generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String username;

    @DatabaseField(canBeNull = false)
    private int votedAt;

    @DatabaseField(canBeNull = false)
    private int totalVotes;

    @DatabaseField(canBeNull = false)
    private boolean redeemed;

    public Vote(String username) {
        this.username = username;
    }

    public Vote(String username, int votedAt) {
        this(username);
        this.votedAt = votedAt;
    }

    public Vote(String username, int votedAt, int totalVotes) {
        this(username, votedAt);
        this.totalVotes = totalVotes;
    }

    public Vote(JSONObject jsonVote) {
        this(jsonVote.getString("username"), jsonVote.getInt("voted_at"), jsonVote.getInt("total_votes"));
    }


    // Methods
    public Vote getFromDB() {
        return TrackerDB.getVote(this.username, this.votedAt);
    }

    public boolean isVoteExpired() {
        return this.votedAt - System.currentTimeMillis() > ((60 * 60) * 24);
    }

    public boolean existsInDB() {
        return this.getFromDB() != null;
    }

    public boolean isRedeemed() {
        if (!this.existsInDB())
            return false;
        return this.getFromDB().isRedeemed();
    }

    public void deleteFromDB() {
        TrackerDB.deleteVote(this.username, this.votedAt);
    }

    public void insertToDB() {
        TrackerDB.insertVote(this.username, this.votedAt, this.totalVotes);
    }

}
