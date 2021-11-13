package ir.mctracker.mctrackervote.network;

import ir.mctracker.mctrackervote.MCTrackerVote;
import ir.mctracker.mctrackervote.config.Config;
import ir.mctracker.mctrackervote.utilities.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.json.JSONArray;

public class Update implements Listener {
    boolean needUpdate = false;
    String localVersion = MCTrackerVote.getInst().getDescription().getVersion();
    JSONArray webVersion;

    public void checkUpdate() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(MCTrackerVote.getInst(), new Runnable() {
            @Override
            public void run() {
                webVersion = new JSONArray(Util.getJSON("https://mctracker.ir/api/plugin/updater"));
                if (webVersion.getString(0).equals(localVersion)) {
                    needUpdate = true;
                }
            }
        },0, Config.UPDATECYCLE * 60 * 20);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!needUpdate) return;

        Player player = event.getPlayer();

        if (player.hasPermission("mctracker.events.update")) {
            for (String s : Config.UPDATE_AVAILABLE_MESSAGES) {
                player.sendMessage(Util.colorize(s));
            }
        }
    }
}
