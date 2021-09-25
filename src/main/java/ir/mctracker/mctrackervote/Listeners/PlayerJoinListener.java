package ir.mctracker.mctrackervote.Listeners;

import ir.mctracker.mctrackervote.api.PlayerVoteRewardReceiveEvent;
import ir.mctracker.mctrackervote.MCTrackerVote;
import ir.mctracker.mctrackervote.config.Config;
import ir.mctracker.mctrackervote.utilities.Util;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        if (Util.offlineVotedPlayers.contains(event.getPlayer().getName())) {
            //give the rewards now
            //after 5 seconds in order to prevent giving rewards in world loading delay
            Bukkit.getScheduler().runTaskLater(MCTrackerVote.getInst(), new Runnable() {
                @Override
                public void run() {

                    //once again check if that player is online right now
                    if (event.getPlayer().isOnline()) {
                        Util.offlineVotedPlayers.remove(event.getPlayer().getName());
                        PlayerVoteRewardReceiveEvent rewardReceiveEvent = new PlayerVoteRewardReceiveEvent(event.getPlayer().getName());
                        Bukkit.getPluginManager().callEvent(rewardReceiveEvent);
                        if (rewardReceiveEvent.isCancelled()) {
                            return;
                        }
                        for (String s : Config.REWARD_COMMANDS) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s.replace("{player}", event.getPlayer().getName()));
                        }
                        for (String s : Config.REWARD_MESSAGES) {
                            event.getPlayer().sendMessage(Util.colorize(s).replace("{player}", event.getPlayer().getName()));
                        }
                    }

                }
            }, 20 * 5);


        }


    }

}
