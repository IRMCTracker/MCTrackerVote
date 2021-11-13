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
    public static Boolean CHECKUPDATES;
    public static Boolean SPLASHSCREEN;
    public static Integer UPDATECYCLE;
    public static Integer ROWLIMIT;
    public static List<String> VOTE_MESSAGES;
    public static List<String> UPDATE_AVAILABLE_MESSAGES;
    public static String NO_PERMISSION;
    public static List<String> REWARD_ACTIONS;

    public static void initConfigValues() {

        //Integers
        SERVER_ID = cfg.getInt("server_id");
        CYCLE = cfg.getInt("cycle");
        UPDATECYCLE = cfg.getInt("update_cycle");
        ROWLIMIT = cfg.getInt("Row_Limit");

        //Strings
        VOTE_URL = "https://mctracker.ir/server/" + SERVER_ID + "/vote";
        API_ENDPOINT = "https://mctracker.ir/api/server/" + SERVER_ID + "/votes";
        PREFIX = Util.colorize(cfg.getString("prefix"));
        NO_PERMISSION = Util.colorize(cfg.getString("no_permission"));

        //Lists
        VOTE_MESSAGES = cfg.getStringList("vote_messages");
        UPDATE_AVAILABLE_MESSAGES = cfg.getStringList("update_available_message");
        REWARD_ACTIONS = cfg.getStringList("reward_actions");

        //Booleans
        CHECKUPDATES = cfg.getBoolean("check_updates");
        SPLASHSCREEN = cfg.getBoolean("splash_screen");
    }
}
