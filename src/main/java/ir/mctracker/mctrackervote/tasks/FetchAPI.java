package ir.mctracker.mctrackervote.tasks;

import ir.mctracker.mctrackervote.config.Config;
import ir.mctracker.mctrackervote.database.models.Vote;
import ir.mctracker.mctrackervote.utilities.Util;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.JSONArray;
import org.json.JSONException;

public class FetchAPI extends BukkitRunnable {
    @Override
    public void run() {
            boolean doChecks = true;

            JSONArray apiResponse = null;

            if (Config.SERVER_ID == 0) {
                    Util.sendToConsole("&ePlease set your &cserver_id &ecorrectly in &cconfig.yml &ethen do /tracker reload");
                    doChecks = false;
            } else {
                    try {
                            apiResponse = new JSONArray(Util.getJSON(Config.API_ENDPOINT));
                    } catch (NullPointerException | JSONException ignored) {
                            Util.sendToConsole("&cFailed to fetch API, Please Check Your Network Status!");
                            doChecks = false;
                    }
            }

            if (doChecks) {
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
