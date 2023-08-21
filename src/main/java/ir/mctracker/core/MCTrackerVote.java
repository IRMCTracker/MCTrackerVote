package ir.mctracker.core;

import com.j256.ormlite.dao.Dao;
import ir.jeykey.megacore.MegaPlugin;
import ir.jeykey.megacore.config.premade.Storage;
import ir.mctracker.core.commands.TrackerCommand;
import ir.mctracker.core.commands.VoteCommand;
import ir.mctracker.core.config.Config;
import ir.mctracker.core.config.Messages;
import ir.mctracker.core.database.DataSource;
import ir.mctracker.core.database.models.Vote;
import ir.mctracker.core.tasks.FetchAPI;
import ir.mctracker.core.tasks.RedeemRewards;
import ir.mctracker.core.utilities.Metrics;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.sql.SQLException;

public final class MCTrackerVote extends MegaPlugin {
    @Getter
    @Setter
    private static Dao<Vote, String> votesDao;

    @Override
    public void onPluginEnable() {
        // Setup configuration files
        getConfigManager().register(Storage.class);
        getConfigManager().register(Config.class);
        getConfigManager().register(Messages.class);

        // Setting up datasource
        try {
            if (Storage.LOCATION.equalsIgnoreCase("sqlite")) {
                DataSource.SQLite();
            } else if (Storage.LOCATION.equalsIgnoreCase("mysql")) {
                DataSource.MySQL();
            } else {
                disablePlugin("&cStorage type defined in config (" + Storage.LOCATION + ") is not valid!");
                return;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            disablePlugin("&cPlugin could not work with database! [ Check Stack Trace For More Information ]");
            return;
        } catch (IOException exception) {
            exception.printStackTrace();
            disablePlugin("&cPlugin is unable to create database file, Please check directory permissions [ Check Stack Trace For More Information ]");
            return;
        }

        // Register commands
        register("tracker", new TrackerCommand());
        register("vote", new VoteCommand());

        // Booting tasks
        new FetchAPI().runTaskTimerAsynchronously(getInstance(), 0, Config.CYCLE);
        new RedeemRewards().runTaskTimer(getInstance(), 0, Config.CYCLE / 2);

        // Setting up metrics
        new Metrics(getInstance(), 12780);
    }

    @Override
    public void onPluginDisable() {

    }

}
