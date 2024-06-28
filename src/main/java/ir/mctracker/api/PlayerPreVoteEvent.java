package ir.mctracker.api;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerPreVoteEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private boolean isCancelled = false;
    private final Player player;

    /**
     * @param player the online player that is trying to vote (does not mean he voted!)
     */
    public PlayerPreVoteEvent(Player player) {
        this.player = player;
    }

    /**
     * @return returns the player that is trying to vote
     */
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.isCancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
