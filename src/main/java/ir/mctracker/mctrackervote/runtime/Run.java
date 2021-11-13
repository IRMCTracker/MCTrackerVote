package ir.mctracker.mctrackervote.runtime;

import ir.mctracker.mctrackervote.commands.TrackerCommand;
import ir.mctracker.mctrackervote.commands.VoteCommand;
import ir.mctracker.mctrackervote.config.Config;
import ir.mctracker.mctrackervote.config.YMLLoader;
import ir.mctracker.mctrackervote.database.SQLDataSource;
import ir.mctracker.mctrackervote.network.Update;
import ir.mctracker.mctrackervote.tasks.FetchAPI;
import ir.mctracker.mctrackervote.tasks.RedeemRewards;
import ir.mctracker.mctrackervote.network.Metrics;
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

	public void splashScreen() {
		if (YMLLoader.cfgLoaded) {
			if (Config.SPLASHSCREEN) {
				Util.sendToConsole("                                                           ");
				Util.sendToConsole("  __  __  ___ _____            _         __   __   _       ");
				Util.sendToConsole(" |  \\/  |/ __|_   _| _ __ _ __| |_____ _ \\ \\ / /__| |_ ___ ");
				Util.sendToConsole(" | |\\/| | (__  | || '_/ _` / _| / / -_) '_\\ V / _ \\  _/ -_)");
				Util.sendToConsole(" |_|  |_|\\___| |_||_| \\__,_\\__|_\\_\\___|_|  \\_/\\___/\\__\\___|");
				Util.sendToConsole("                                                           ");
			} else {
				Util.sendToConsole("[&bMCTrackerVote] &aLoading plugin:");
				Util.sendToConsole("                                                           ");
			}

		} else {
			Util.sendToConsole("                                                           ");
			Util.sendToConsole("  __  __  ___ _____            _         __   __   _       ");
			Util.sendToConsole(" |  \\/  |/ __|_   _| _ __ _ __| |_____ _ \\ \\ / /__| |_ ___ ");
			Util.sendToConsole(" | |\\/| | (__  | || '_/ _` / _| / / -_) '_\\ V / _ \\  _/ -_)");
			Util.sendToConsole(" |_|  |_|\\___| |_||_| \\__,_\\__|_\\_\\___|_|  \\_/\\___/\\__\\___|");
			Util.sendToConsole("                                                           ");
		}
	}

	public void loadConfig() {

		try {
			YMLLoader.createConfig();
			Config.initConfigValues();
			Util.sendToConsole("&c[&bMCVote&c] &bLoading Config");
		} catch (IOException | InvalidConfigurationException e) {
			Util.sendToConsole("&c[&bMCVote&c] &cLoading Config");
			e.printStackTrace();
		}
	}

	public void handleSQL() {

		try {
			SQLDataSource.SQLite();
			Util.sendToConsole("&c[&bMCVote&c] &bConnecting to Database");
		} catch (SQLException | ClassNotFoundException | IOException e) {
			Util.sendToConsole("&c[&bMCVote&c] &cConnecting to Database");
			e.printStackTrace();
		}
	}

	public void registerCommands() {

		// Register plugin commands
		try {
			javaPlugin.getCommand("MCTracker").setExecutor(new TrackerCommand());
			javaPlugin.getCommand("Vote").setExecutor(new VoteCommand());
			Util.sendToConsole("&c[&bMCVote&c] &bRegistering commands");
		} catch (Exception e) {
			Util.sendToConsole("&c[&bMCVote&c] &cRegistering commands");
			e.printStackTrace();
		}
	}

	public void registerEvents() {

		try {
			if (Config.CHECKUPDATES) javaPlugin.getServer().getPluginManager().registerEvents(new Update(), javaPlugin);
			Util.sendToConsole("&c[&bMCVote&c] &bRegistering events");
		} catch (Exception e) {
			Util.sendToConsole("&c[&bMCVote&c] &cRegistering events");
			e.printStackTrace();
		}
	}

	public void registerRunnable() {

		try {
			int ticks = Config.CYCLE * 60 * 20;

			new FetchAPI().runTaskTimerAsynchronously(javaPlugin, 0, ticks);
			new RedeemRewards().runTaskTimer(javaPlugin, 0, ticks / 4);
			if (Config.CHECKUPDATES) new Update().checkUpdate();

			Util.sendToConsole("&c[&bMCVote&c] &bRegistering runnables");
		} catch (Exception e) {
			Util.sendToConsole("&c[&bMCVote&c] &cRegistering runnables");
			e.printStackTrace();
		}
	}

	/**
	 * Registering bStats metrics
	 */
	public void registerMetrics() {
		Metrics metrics = new Metrics(javaPlugin, 12780);
	}

}
