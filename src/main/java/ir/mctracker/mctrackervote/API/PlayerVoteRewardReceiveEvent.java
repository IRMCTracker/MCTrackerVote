package ir.mctracker.mctrackervote.API;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerVoteRewardReceiveEvent extends Event implements Cancellable {

    private String playerName;
    private boolean isCancelled = false;

    /**
     *
     * @param playerName the player name that going to receive the reward (gets executed ONLY ONCE even if there are more than 1 line of commands for rewarding)
     */
    public PlayerVoteRewardReceiveEvent(String playerName) {
        this.playerName = playerName;
    }

    /**
     *
     * @return returns the player name of the reward receiver
     */
    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
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
        return null;
    }
}
