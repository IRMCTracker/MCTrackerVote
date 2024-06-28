package ir.mctracker;

import ir.jeykey.megacore.config.premade.Storage;
import ir.mctracker.core.commands.TrackerCommand;
import ir.mctracker.core.commands.VoteCommand;
import ir.mctracker.core.config.Configuration;
import ir.mctracker.core.config.Messages;
import ir.mctracker.core.database.DataSource;
import ir.mctracker.core.tasks.FetchAPI;
import ir.mctracker.core.tasks.RedeemRewards;
import ir.mctracker.core.utilities.Metrics;
import ir.mctracker.core.utilities.Util;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.sql.SQLException;

public class MCTrackerVote extends JavaPlugin {
    public static MCTrackerVote plugin;
    public static Configuration config;
    public static Messages messages;

    @Override
    public void onEnable() {
        plugin = this;

        // Setup configuration files
        if (!messages.messagesFile.exists()) {
            saveResource("messages.yml", false);
        }
        if (!config.configFile.exists()) {
            saveResource("settings.yml", false);
        }
        config = new Configuration();
        config.loadConfiguration();
        messages = new Messages();
        // TODO Message File Config

        // Register commands
        getCommand("tracker").setExecutor(new TrackerCommand(messages));
        getCommand("vote").setExecutor(new VoteCommand(config, messages));

        // Booting tasks
        new FetchAPI().runTaskTimerAsynchronously(plugin, 0, config.getCycle());
        new RedeemRewards().runTaskTimer(plugin, 0, config.getCycle() / 2);

        // Setting up datasource
        // TODO Making a good storage system

        // Setting up metrics
        new Metrics(plugin, 12780);
    }

    public static MCTrackerVote getPlugin() {
        return plugin;
    }
}