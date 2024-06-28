package ir.mctracker.core.config;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Messages {

    public static File messagesFile = new File("plugins/MCTracker/messages.yml");
    public static YamlConfiguration messages;

    public static void loadConfig() {
        addDefaults();
    }

    public static void reloadConfig() {
        messages = YamlConfiguration.loadConfiguration(messagesFile);
    }

    public static void addDefaults() {
        messages = YamlConfiguration.loadConfiguration(messagesFile);

        // Messages File
        messages.addDefault("prefix", "&2[&aVote&2] ");
        messages.addDefault("vote-messages", Arrays.asList(
                "&bSalam &3{player}",
                "&bBaraye Vote Dadan Be Server Az Link Zir Estefade Konid &7(1 Vote per Day)",
                "&a{vote_url}"
        ));
        messages.addDefault("no-permission", "{prefix}&cShoma Dastresi Kafi Baraye Ejraye In Dastoor Ro Nadarid.");
        messages.addDefault("console-not-allowed", "{prefix}&cIn Command Baraye Player Hast.");
        messages.addDefault("invalid-arg", "{prefix}&cIn Dastoor Vojod Nadarad.");

        messages.options().copyDefaults(true);
        try {
            messages.save(messagesFile);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        messages = YamlConfiguration.loadConfiguration(messagesFile);
    }

    public static String getString(String key) {
        return messages.getString(key);
    }

    public static List<String> getStringList(String key) {
        return messages.getStringList(key);
    }
}
