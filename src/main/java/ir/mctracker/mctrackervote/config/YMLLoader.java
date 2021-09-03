package ir.mctracker.mctrackervote.config;

import ir.mctracker.mctrackervote.MCTrackerVote;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class YMLLoader {
    private static File ConfigFile;
    private static FileConfiguration Config;

    public static void createConfig() throws IOException, InvalidConfigurationException {
        ConfigFile = new File(MCTrackerVote.getInst().getDataFolder(), "config.yml");
        if (!ConfigFile.exists()) {
            ConfigFile.getParentFile().mkdirs();
            MCTrackerVote.getInst().saveResource("config.yml", false);
        }
        Config = new YamlConfiguration();
        Config.load(ConfigFile);
    }

    public static void reloadConfig() {
        try {
            Config.load(ConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static FileConfiguration getConfig() {
        return Config;
    }
}
