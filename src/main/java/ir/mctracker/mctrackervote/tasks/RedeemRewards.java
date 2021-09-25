package ir.mctracker.mctrackervote.tasks;
import ir.mctracker.mctrackervote.api.PlayerVoteEvent;
import ir.mctracker.mctrackervote.api.PlayerVoteRewardReceiveEvent;
import ir.mctracker.mctrackervote.config.Config;
import ir.mctracker.mctrackervote.database.TrackerDB;
import ir.mctracker.mctrackervote.database.models.Vote;
import ir.mctracker.mctrackervote.utilities.Util;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class RedeemRewards extends BukkitRunnable {
    @Override
    public void run() {
        List<Vote> votes = TrackerDB.getUnredeemedVotes();

        for (Vote vote : votes) {
            Player player = Bukkit.getPlayer(vote.getUsername());
            if (player != null) {
                PlayerVoteEvent voteEvent = new PlayerVoteEvent(Bukkit.getOfflinePlayer(player.getUniqueId()));
                Bukkit.getPluginManager().callEvent(voteEvent);
                PlayerVoteRewardReceiveEvent rewardReceiveEvent = new PlayerVoteRewardReceiveEvent(player.getName());
                Bukkit.getPluginManager().callEvent(rewardReceiveEvent);
                if (rewardReceiveEvent.isCancelled()) {
                    continue;
                }

                for (String action : Config.REWARD_ACTIONS) {
                    action = action.replace("{player}", player.getName());

                    if (action.startsWith("[message]")) {
                        player.sendMessage(
                                Util.colorize(
                                        action.replace("[message]", "").trim()
                                )
                        );
                    } else if (action.startsWith("[console]")) {
                        Bukkit.dispatchCommand(
                                Bukkit.getConsoleSender(),
                                Util.colorize(action.replace("[console]", "").trim())
                        );
                    } else if (action.startsWith("[player]")) {
                        player.performCommand(
                                Util.colorize(action .replace("[player]", "").trim())
                        );
                    }
                }

                TrackerDB.redeemVote(vote.getUsername());
            }
        }
    }
}
