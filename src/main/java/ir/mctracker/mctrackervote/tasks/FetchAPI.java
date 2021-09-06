package ir.mctracker.mctrackervote.tasks;

import ir.mctracker.mctrackervote.config.YMLLoader;
import ir.mctracker.mctrackervote.database.Query;
import ir.mctracker.mctrackervote.utilities.Util;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FetchAPI extends BukkitRunnable {
    @Override
    public void run() {
        try {
            int server_id = YMLLoader.getConfig().getInt("server_id");
            if (server_id == 0) {
                Bukkit.getConsoleSender().sendMessage(Util.colorize(Util.prefix + "&bPlease set your &cserver_id &bcorrectly in &cconfig.yml &bthen do &c/mctracker reload"));
                return;
            }
            String json = Util.getJSON("https://mctracker.ir/api/server/" + server_id + "/votes");
            JSONArray res = new JSONArray(json);

            for (int i = 0; i < res.length(); ++i) {
                JSONObject user = res.getJSONObject(i);

                String username = user.getString("username");
                int vote_at = user.getInt("voted_at");
                int total_vote = user.getInt("total_votes");

                ResultSet resultSet = Query.getResult("SELECT * FROM tracker_votes WHERE username = '" + username + "' AND voted_at = " + vote_at + "");
                if (!(resultSet.next())) {
                    Query.executeQuery("INSERT INTO tracker_votes (username, voted_at, total_votes, redeemed) VALUES ('" + username + "', " + vote_at + ", " + total_vote + ", false)");
                }

                if (vote_at - System.currentTimeMillis() > ((60 * 60) * 24)) {
                    Query.executeQuery("DELETE FROM tracker_votes WHERE username='" + username + "';");
                }
            }
        } catch (JSONException | SQLException e) {
            Bukkit.getConsoleSender().sendMessage(Util.colorize(Util.prefix + "&cRequest failed please check your network connection"));
            Bukkit.getConsoleSender().sendMessage(Util.colorize(Util.prefix + "&cIf problem is consistent, contact us in discord"));
        }
    }
}
