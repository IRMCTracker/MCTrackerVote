package ir.mctracker.mctrackervote.tasks;

import ir.mctracker.mctrackervote.config.YMLLoader;
import ir.mctracker.mctrackervote.database.Query;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RedeemRewards extends BukkitRunnable {
    @Override
    public void run() {
        try {
            ResultSet resultSet = Query.getResult("SELECT * FROM tracker_votes WHERE redeemed = false;");
            while (resultSet.next()) {
                Player player = Bukkit.getPlayer(resultSet.getString("username"));
                if (!(player == null)) {

                    List<String> commands = YMLLoader.getConfig().getStringList("reward_commands");
                    for (String s : commands) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s.replace("{player}", player.getName()));
                    }
                    Query.executeQuery("UPDATE tracker_votes SET redeemed = true WHERE username = '" + resultSet.getString("username") + "'");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
