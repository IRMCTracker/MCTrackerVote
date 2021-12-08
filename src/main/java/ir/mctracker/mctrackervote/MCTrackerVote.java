package ir.mctracker.mctrackervote;

import ir.mctracker.mctrackervote.database.TrackerDB;
import ir.mctracker.mctrackervote.runtime.Run;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class MCTrackerVote extends JavaPlugin {

    private static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        Run run = new Run(this);

        run.splashScreen();

        run.loadConfig();

        run.handleSQL();

        run.registerCommands();

        run.registerEvents();

        run.registerRunnable();

        run.registerMetrics();
    }

    @Override
    public void onDisable() {
        plugin = null;

        TrackerDB.closeConnection();
    }

    public static Plugin getInst() {
        return plugin;
    }

}
