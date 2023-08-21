package ir.mctracker.core.config;

import ir.jeykey.megacore.MegaPlugin;
import ir.jeykey.megacore.config.Configurable;
import ir.mctracker.core.utilities.Util;

import java.util.List;
import java.util.stream.Collectors;

public class Messages extends Configurable {
    public static String PREFIX;
    public static List<String> VOTE_MESSAGES;
    public static String NO_PERMISSION;
    public static String CONSOLE_NOT_ALLOWED;
    public static String INVALID_ARG;

    public Messages(MegaPlugin instance) {
        super(instance, "messages.yml");
    }

    @Override
    public void init() {
        PREFIX = format(Configurable.getConfig().getString("prefix"));
        VOTE_MESSAGES = format(Configurable.getConfig().getStringList("vote-messages"));
        NO_PERMISSION = format(Configurable.getConfig().getString("no-permission"));
        CONSOLE_NOT_ALLOWED = format(Configurable.getConfig().getString("console-not-allowed"));
        INVALID_ARG = format(Configurable.getConfig().getString("invalid-arg"));
    }

    private List<String> format(List<String> texts) {
        return texts.stream().map(this::format).collect(Collectors.toList());
    }

    private String format(String text) {
        return Util.colorize(
                text.replace("{prefix}", PREFIX != null ? PREFIX : "")
        );
    }
}
