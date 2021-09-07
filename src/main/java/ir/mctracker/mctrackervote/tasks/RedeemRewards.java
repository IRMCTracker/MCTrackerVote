package ir.mctracker.mctrackervote.tasks;

import ir.mctracker.mctrackervote.config.Config;
import ir.mctracker.mctrackervote.config.YMLLoader;
import ir.mctracker.mctrackervote.database.TrackerDB;
import ir.mctracker.mctrackervote.database.models.Vote;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RedeemRewards extends BukkitRunnable {
    @Override
    public void run() {
        List<Vote> votes = TrackerDB.getUnredeemedVotes();
        for (Vote vote : votes) {
            Player player = Bukkit.getPlayer(vote.getUsername());
            if (!(player == null)) {
                for (String s : Config.REWARD_COMMANDS) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s.replace("{player}", player.getName()));
                }
                TrackerDB.redeemVote(vote.getUsername());
            }
        }
    }
}
