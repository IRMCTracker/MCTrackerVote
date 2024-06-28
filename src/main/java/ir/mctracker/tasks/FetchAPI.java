package ir.mctracker.tasks;

import ir.mctracker.config.Config;
import ir.mctracker.database.models.Vote;
import ir.mctracker.utilities.Util;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.JSONArray;
import org.json.JSONException;

public class FetchAPI extends BukkitRunnable {
    @Override
    public void run() {
        boolean doChecks = true;

        JSONArray apiResponse = null;

        if (Config.SERVER_ID == 0) {
            Util.sendToConsole("&bPlease set your &cserver_id &bcorrectly in &cconfig.yml &bthen do /tracker reload");
            doChecks = false;
        } else {
            try {
                apiResponse = new JSONArray(Util.fetchJson(Config.API_ENDPOINT));
            } catch (NullPointerException | JSONException ignored) {
                Util.sendToConsole("&cFailed to fetch API, Please check your network connectivity!");
                doChecks = false;
            }
        }

        if (doChecks) {
            for (int i = 0; i < apiResponse.length(); ++i) {
                Vote vote = new Vote(apiResponse.getJSONObject(i));

                boolean exists = vote.fetch();

                if (!exists) {
                    vote.create();
                }

                if (vote.isExpired()) {
                    vote.delete();
                }
            }
        }
    }
}
