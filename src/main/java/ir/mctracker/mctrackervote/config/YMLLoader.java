package ir.mctracker.mctrackervote.config;

import ir.mctracker.mctrackervote.MCTrackerVote;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class YMLLoader {

    private static File configFile;
    private static FileConfiguration config;

    public static void createConfig() throws IOException, InvalidConfigurationException {

        configFile = new File(MCTrackerVote.getInst().getDataFolder(), "config.yml");

        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            MCTrackerVote.getInst().saveResource("config.yml", false);
        }

        config = new YamlConfiguration();
        config.load(configFile);
    }

    public static void reloadConfig() {

        try {
            config.load(configFile);
            Config.initConfigValues();
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static FileConfiguration getConfig() {
        return config;
    }

}
