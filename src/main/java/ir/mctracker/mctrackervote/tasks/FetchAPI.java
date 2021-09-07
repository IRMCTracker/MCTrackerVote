package ir.mctracker.mctrackervote.tasks;

import ir.mctracker.mctrackervote.config.Config;
import ir.mctracker.mctrackervote.config.YMLLoader;
import ir.mctracker.mctrackervote.database.TrackerDB;
import ir.mctracker.mctrackervote.database.models.Vote;
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
            int serverId = Config.SERVER_ID;
            if (serverId == 0) {
                Bukkit.getConsoleSender().sendMessage(Util.colorize(Config.PREFIX + "&bPlease set your &cserver_id &bcorrectly in &cconfig.yml &bthen do &c/mctracker reload"));
                cancel();
            }

            JSONArray apiResponse = null;
            try {
                apiResponse = new JSONArray(Util.getJSON(Config.API_ENDPOINT));
            } catch (NullPointerException | JSONException ignored) {
                Bukkit.getConsoleSender().sendMessage(Util.colorize(Config.PREFIX + "&cFailed to fetch API, Please check your network connectivity!"));
            }

            if (apiResponse != null) {
                for (int i = 0; i < apiResponse.length(); ++i) {
                    Vote vote = new Vote(apiResponse.getJSONObject(i));

                    boolean exists = vote.existsInDB();

                    if (!exists) {
                        vote.insertToDB();
                    }

                    if (vote.isVoteExpired()) {
                        vote.deleteFromDB();
                    }
                }
            }
    }
}
