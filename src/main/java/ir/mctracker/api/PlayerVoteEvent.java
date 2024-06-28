package ir.mctracker.api;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerVoteEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    //When the server is attempting to give the reward to the player, which means the player vote is registered.
    private final OfflinePlayer player;

    /**
     * Creating an event.
     *
     * @param player the player that voted
     */
    public PlayerVoteEvent(OfflinePlayer player) {
        this.player = player;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    /**
     * @return returns the player that voted
     */
    public OfflinePlayer getPlayer() {
        return this.player;
    }

}
