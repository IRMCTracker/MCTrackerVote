package ir.mctracker.mctrackervote.config;

import ir.mctracker.mctrackervote.utilities.Util;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Config {
        public static FileConfiguration cfg = YMLLoader.getConfig();

        public static String PREFIX = Util.colorize(cfg.getString("prefix"));
        public static Integer CYCLE = YMLLoader.getConfig().getInt("cycle");
        public static List<String> VOTE_MESSAGES = cfg.getStringList("vote_messages");
        public static String NO_PERMISSION = Util.colorize(cfg.getString("no_permission"));
}
