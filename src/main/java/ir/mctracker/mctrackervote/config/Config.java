package ir.mctracker.mctrackervote.config;

import ir.mctracker.mctrackervote.utilities.Util;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Config {

    public static FileConfiguration cfg = YMLLoader.getConfig();
    public static Integer SERVER_ID;
    public static String VOTE_URL;
    public static String API_ENDPOINT;
    public static String PREFIX;
    public static Integer CYCLE;
    public static List<String> VOTE_MESSAGES;
    public static String NO_PERMISSION;
    public static List<String> REWARD_COMMANDS;
    public static List<String> REWARD_MESSAGES;

    public static void initConfigValues() {

        SERVER_ID = cfg.getInt("server_id");
        VOTE_URL = "https://mctracker.ir/server/" + SERVER_ID + "/vote";
        API_ENDPOINT = "https://mctracker.ir/api/server/" + SERVER_ID + "/votes";
        PREFIX = Util.colorize(cfg.getString("prefix"));
        CYCLE = YMLLoader.getConfig().getInt("cycle");
        VOTE_MESSAGES = cfg.getStringList("vote_messages");
        NO_PERMISSION = Util.colorize(cfg.getString("no_permission"));
        REWARD_COMMANDS = cfg.getStringList("reward_commands");
        REWARD_MESSAGES = cfg.getStringList("reward_messages");
    }
}
