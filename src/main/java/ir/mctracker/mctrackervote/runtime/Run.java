package ir.mctracker.mctrackervote.runtime;

import ir.mctracker.mctrackervote.commands.TrackerCommand;
import ir.mctracker.mctrackervote.commands.VoteCommand;
import ir.mctracker.mctrackervote.config.Config;
import ir.mctracker.mctrackervote.config.YMLLoader;
import ir.mctracker.mctrackervote.database.SQLDataSource;
import ir.mctracker.mctrackervote.tasks.FetchAPI;
import ir.mctracker.mctrackervote.tasks.RedeemRewards;
import ir.mctracker.mctrackervote.utilities.Util;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.sql.SQLException;

public class Run {

    private JavaPlugin javaPlugin;

    public Run(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
    }

    public void loadConfig() {

        try {
            YMLLoader.createConfig();
            Config.initConfigValues();
            Util.sendToConsole("&bLoading Config");
        } catch (IOException | InvalidConfigurationException e) {
            Util.sendToConsole("Failed To Load Config!");
            e.printStackTrace();
        }
    }

    public void handleSQL() {

        try {
            SQLDataSource.SQLite();
            Util.sendToConsole("&aConnecting to Database!");
        } catch (SQLException | ClassNotFoundException | IOException e) {
            Util.sendToConsole("&cFailed to connect to Database!");
            e.printStackTrace();
        }
    }

    public void registerCommands() {

        // Register plugin commands
        javaPlugin.getCommand("MCTracker").setExecutor(new TrackerCommand());
        javaPlugin.getCommand("Vote").setExecutor(new VoteCommand());

        // Log commands registration in console
        Util.sendToConsole("&aRegistering commands");

    }

    public void registerRunnable() {

        int ticks = Config.CYCLE * 60 * 20;

        // Startup runnables
        new FetchAPI().runTaskTimerAsynchronously(javaPlugin, 0, ticks);
        new RedeemRewards().runTaskTimer(javaPlugin, 0, ticks / 4);

        // Log commands registration in console
        Util.sendToConsole("&bRegistering runnable");
    }
}
