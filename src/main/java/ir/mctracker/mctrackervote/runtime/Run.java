package ir.mctracker.mctrackervote.runtime;

import ir.mctracker.mctrackervote.commands.TrackerCommand;
import ir.mctracker.mctrackervote.commands.VoteCommand;
import ir.mctracker.mctrackervote.config.YMLLoader;
import ir.mctracker.mctrackervote.database.SQLDataSource;
import ir.mctracker.mctrackervote.tasks.FetchAPI;
import ir.mctracker.mctrackervote.tasks.RedeemRewards;
import ir.mctracker.mctrackervote.utilities.Util;
import org.bukkit.Bukkit;
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
            Bukkit.getConsoleSender().sendMessage(Util.colorize("&c[&b&lMCTracker&c] &bLoading config"));
        } catch (IOException | InvalidConfigurationException e) {
            Bukkit.getConsoleSender().sendMessage(Util.colorize("&c[&b&lMCTracker&c] &cLoading config"));
            e.printStackTrace();
        }
    }

    public void handleSQL() {
        try {
            SQLDataSource.SQLite();
            Bukkit.getConsoleSender().sendMessage(Util.colorize("&c[&b&lMCTracker&c] &bConnecting to database"));
        } catch (SQLException | IOException e) {
            Bukkit.getConsoleSender().sendMessage(Util.colorize("&c[&b&lMCTracker&c] &cConnecting to database"));
            e.printStackTrace();
        }
    }

    public void registerCommands() {
        try {
            javaPlugin.getCommand("MCTracker").setExecutor(new TrackerCommand());
            javaPlugin.getCommand("Vote").setExecutor(new VoteCommand());
            Bukkit.getConsoleSender().sendMessage(Util.colorize("&c[&b&lMCTracker&c] &bRegistering commands"));
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(Util.colorize("&c[&b&lMCTracker&c] &bRegistering commands"));
            e.printStackTrace();
        }
    }

    public void registerRunnable() {
        try {
            int ticks = YMLLoader.getConfig().getInt("cycle") * 60 * 20;

            new FetchAPI().runTaskTimerAsynchronously(javaPlugin, 0, ticks);
            new RedeemRewards().runTaskTimer(javaPlugin, 0, ticks/4);
            Bukkit.getConsoleSender().sendMessage(Util.colorize("&c[&b&lMCTracker&c] &bRegistering runnable"));
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(Util.colorize("&c[&b&lMCTracker&c] &bRegistering runnable"));
            e.printStackTrace();
        }
    }
}
