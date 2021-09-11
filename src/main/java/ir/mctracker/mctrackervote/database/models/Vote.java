package ir.mctracker.mctrackervote.database.models;

import ir.mctracker.mctrackervote.database.TrackerDB;
import org.json.JSONArray;
import org.json.JSONObject;

public class Vote {
        private String username;
        private int votedAt, totalVotes;
        private boolean redeemed;

        public Vote() {

        }

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

        public void setUsername(String username) {
                this.username = username;
        }

        public void setTotalVotes(int totalVotes) {
                this.totalVotes = totalVotes;
        }

        public void setVotedAt(int votedAt) {
                this.votedAt = votedAt;
        }

        public void setRedeemed(boolean redeemed) {
                this.redeemed = redeemed;
        }

        public int getTotalVotes() {
                return totalVotes;
        }

        public int getVotedAt() {
                return votedAt;
        }

        public boolean getRedeemed() {
                return redeemed;
        }


        public String getUsername() {
                return username;
        }

        //
        // METHODS
        //

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

                        Vote vote = this.getFromDB();

                        return vote.getRedeemed();
        }

        public void deleteFromDB() {
                TrackerDB.deleteVote(this.username, this.votedAt);
        }

        public void insertToDB() {
                TrackerDB.insertVote(this.username, this.votedAt, this.totalVotes);
        }
}
