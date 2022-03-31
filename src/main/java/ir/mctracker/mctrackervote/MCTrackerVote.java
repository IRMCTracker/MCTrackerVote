package ir.mctracker.mctrackervote;

import ir.jeykey.megacore.MegaPlugin;
import ir.mctracker.mctrackervote.database.TrackerDB;
import ir.mctracker.mctrackervote.runtime.Run;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

public final class MCTrackerVote extends MegaPlugin {

    @Override
    public void onPluginEnable() {
        Run run = new Run(this);

        run.loadConfig();

        run.handleSQL();

        run.registerCommands();

        run.registerRunnable();

        run.registerMetrics();
        
    }

    @Override
    public void onPluginDisable() {
        TrackerDB.closeConnection();
    }

}
