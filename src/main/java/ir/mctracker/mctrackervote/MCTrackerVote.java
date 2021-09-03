package ir.mctracker.mctrackervote;

import ir.mctracker.mctrackervote.database.Query;
import ir.mctracker.mctrackervote.runtime.Run;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class MCTrackerVote extends JavaPlugin {
    private static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        new Run(this).loadConfig();

        new Run(this).handleSQL();

        new Run(this).registerCommands();
    }

    @Override
    public void onDisable() {
        plugin = null;

        Query.closeConnection();
    }

    public static Plugin getInst() {
        return plugin;
    }
}
