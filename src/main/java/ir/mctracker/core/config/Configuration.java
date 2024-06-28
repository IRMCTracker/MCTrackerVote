package ir.mctracker.core.config;

import ir.mctracker.MCTrackerVote;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Configuration {
    public File configFile = new File("plugins/MCTrackerVote/settings.yml");
    public Integer serverId;
    public boolean voteNeedsPermission;
    public String voteUrl;
    public String apiEndpoint;
    public Integer cycle;
    public List<String> rewardActions;

    public void loadConfiguration() {
        addDefaultValues();
        configurationDefaults();
    }

    public void configurationDefaults() {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        serverId = config.getInt("server-id");
        voteNeedsPermission = config.getBoolean("vote-needs-permission");
        cycle = config.getInt("cycle") * 60 * 20;
        rewardActions = config.getStringList("reward-actions");
        voteUrl = "https://mctracker.ir/server/" + serverId + "/vote";
        apiEndpoint = "https://mctracker.ir/api/server" + serverId + "/votes";
    }

    public void addDefaultValues() {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        // Config File Defaults
        config.addDefault("server-id", "1");
        config.addDefault("vote-needs-permission", true);
        config.addDefault("cycle", 30);
        config.addDefault("reward-actions", List.of("give %player% 100 gold", "broadcast %player% has voted"));

        config.options().copyDefaults(true);

        try {
            config.save(configFile);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static void reloadConfiguration() {
        MCTrackerVote.config = new Configuration();
        MCTrackerVote.config.loadConfiguration();
    }

    public Integer getServerId() {
        return serverId;
    }

    public boolean isVoteNeedsPermission() {
        return voteNeedsPermission;
    }

    public String getVoteUrl() {
        return voteUrl;
    }

    public String getApiEndpoint() {
        return apiEndpoint;
    }

    public Integer getCycle() {
        return cycle;
    }

    public List<String> getRewardActions() {
        return rewardActions;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public void setVoteNeedsPermission(boolean voteNeedsPermission) {
        this.voteNeedsPermission = voteNeedsPermission;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public void setRewardActions(List<String> rewardActions) {
        this.rewardActions = rewardActions;
    }
}