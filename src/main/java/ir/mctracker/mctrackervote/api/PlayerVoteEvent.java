package ir.mctracker.mctrackervote.api;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerVoteEvent extends Event {

    //When the server is attempting to give the reward to the player, which means the player vote is registered.

    private OfflinePlayer player;

    @Override
    public HandlerList getHandlers() {
        return null;
    }


    /** Creating an event.
     * @param player the player that voted
     */
    public PlayerVoteEvent(OfflinePlayer player) {
        this.player = player;
    }

    /**
     * @return returns the player that voted
     */
    public OfflinePlayer getPlayer() {
        return this.player;
    }

}
