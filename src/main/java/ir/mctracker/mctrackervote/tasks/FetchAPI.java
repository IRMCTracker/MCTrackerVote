package ir.mctracker.mctrackervote.tasks;

import ir.mctracker.mctrackervote.MCTrackerVote;
import ir.mctracker.mctrackervote.config.YMLLoader;
import ir.mctracker.mctrackervote.database.Query;
import ir.mctracker.mctrackervote.utilities.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FetchAPI extends BukkitRunnable {
    @Override
    public void run() {
        try {
            int server_id = YMLLoader.getConfig().getInt("server_id");
            if (server_id == 0) {
                Bukkit.getConsoleSender().sendMessage(Util.colorize(Util.prefix + "&bPlease set your &cserver_id &bcorrectly in &cconfig.yml &bthen do &c/mctracker reload"));
                return;
            }
            String json = Util.getJSON( "https://mctracker.ir/api/server/" + server_id + "/votes");
            JSONArray res = new JSONArray(json);

            final int n = res.length();
            for (int i = 0; i < n; ++i) {
                JSONObject user = res.getJSONObject(i);

                Player player = Bukkit.getServer().getPlayer(user.getString("username"));
                if(player == null) {
                    continue;
                }
                ResultSet resultSet = Query.getResult("SELECT * FROM tracker_votes WHERE uuid = '" + player.getUniqueId());
                if (!(resultSet.next())) {
                    List<String> commands = YMLLoader.getConfig().getStringList("reward_commands");
                    for (String s : commands) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s.replace("{player}", player.getName()));
                    }

                    Query.executeQuery("INSERT INTO tracker_votes (uuid, voted_at) VALUES ('" + player.getUniqueId() + "', '" + user.getInt("voted_at") + "')");
                }
            }
        } catch (JSONException | SQLException e) {
            e.printStackTrace();
        }
    }
}
