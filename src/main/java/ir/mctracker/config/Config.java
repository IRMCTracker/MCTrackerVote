package ir.mctracker.config;

import ir.jeykey.megacore.MegaPlugin;
import ir.jeykey.megacore.config.Configurable;

import java.util.List;

public class Config extends Configurable {

    public static Integer SERVER_ID;
    public static boolean VOTE_NEEDS_PERMISSION;
    public static String VOTE_URL;
    public static String API_ENDPOINT;
    public static Integer CYCLE;
    public static List<String> REWARD_ACTIONS;

    public Config(MegaPlugin instance) {
        super(instance, "config.yml");
    }

    @Override
    public void init() {
        SERVER_ID = Configurable.getConfig().getInt("server-id");
        VOTE_NEEDS_PERMISSION = Configurable.getConfig().getBoolean("vote-needs-permission");
        VOTE_URL = "https://mctracker.ir/server/" + SERVER_ID + "/vote";
        API_ENDPOINT = "https://mctracker.ir/api/server/" + SERVER_ID + "/votes";
        CYCLE = Configurable.getConfig().getInt("cycle") * 60 * 20;
        REWARD_ACTIONS = Configurable.getConfig().getStringList("reward-actions");
    }
}
