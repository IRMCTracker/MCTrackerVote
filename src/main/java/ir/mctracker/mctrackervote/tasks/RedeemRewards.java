package ir.mctracker.mctrackervote.tasks;
import ir.mctracker.mctrackervote.API.PlayerVoteEvent;
import ir.mctracker.mctrackervote.API.PlayerVoteRewardReceiveEvent;
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
            Player player = Bukkit.getPlayer(vote.getUsername()); //saving UUID is better than saving username, because now Bukkit.getOfflinePlayer MAY cause a bit of lag
            if (player != null) {
                PlayerVoteEvent voteEvent = new PlayerVoteEvent(Bukkit.getOfflinePlayer(player.getUniqueId()));
                Bukkit.getPluginManager().callEvent(voteEvent);
                PlayerVoteRewardReceiveEvent rewardReceiveEvent = new PlayerVoteRewardReceiveEvent(player.getName());
                Bukkit.getPluginManager().callEvent(rewardReceiveEvent);
                if (rewardReceiveEvent.isCancelled()) {
                    continue;
                }
                for (String s : Config.REWARD_COMMANDS) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s.replace("{player}", player.getName()));
                }

                for (String s : Config.REWARD_MESSAGES) {
                    player.sendMessage(Util.colorize(s).replace("{player}", player.getName()));
                }
                TrackerDB.redeemVote(vote.getUsername());
            } else {
                //so it is null, which means that player can possibly be offline or even doesn't exist in the server so lets check that
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(vote.getUsername());
                if (offlinePlayer.hasPlayedBefore()) {
                    Util.offlineVotedPlayers.add(offlinePlayer.getName());
                    PlayerVoteEvent voteEvent = new PlayerVoteEvent(Bukkit.getOfflinePlayer(player.getUniqueId()));
                    Bukkit.getPluginManager().callEvent(voteEvent);
                }
            }
        }
    }
}
