package ir.mctracker.mctrackervote.listeners;

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

                        for (String action : Config.REWARD_ACTIONS) {
                            action = action.replace("{player}", event.getPlayer().getName());

                            if (action.startsWith("[message]")) {
                                event.getPlayer().sendMessage(
                                        Util.colorize(
                                                action.replace("[message]", "")
                                        )
                                );
                            } else if (action.startsWith("[console]")) {
                                Bukkit.dispatchCommand(
                                        Bukkit.getConsoleSender(),
                                        action.replace("[console]", "")
                                );
                            } else if (action.startsWith("[player]")) {
                                event.getPlayer().performCommand(
                                        action .replace("[player]", "")
                                );
                            }
                        }

                    }
                }
            }, 20 * 5);


        }


    }

}
