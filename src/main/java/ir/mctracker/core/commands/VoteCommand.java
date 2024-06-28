package ir.mctracker.core.commands;

import ir.mctracker.MCTrackerVote;
import ir.mctracker.api.PlayerPreVoteEvent;
import ir.mctracker.core.config.Configuration;
import ir.mctracker.core.config.Messages;
import ir.mctracker.core.utilities.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoteCommand implements CommandExecutor {
    private final String VOTE_PERMISSION = "mctracker.commands.vote";
    private Configuration config;
    private Messages messages;

    public VoteCommand(Configuration config, Messages messages) {
        this.config = config;
        this.messages = messages;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Messages.getString("console-not-allowed"));
            return true;
        }

        Player p = (Player) sender;

        PlayerPreVoteEvent playerPreVoteEvent = new PlayerPreVoteEvent(p);
        Bukkit.getPluginManager().callEvent(playerPreVoteEvent);
        if (playerPreVoteEvent.isCancelled()) {
            return false;
        }

        if (args.length == 0) {
            if (sender.hasPermission(VOTE_PERMISSION) || !config.isVoteNeedsPermission()) {
                for (String s : Messages.getStringList("vote-messages")) {
                    sender.sendMessage(Util.colorize(s.replace("{player}", p.getName()).replace("{vote_url}", MCTrackerVote.config.getVoteUrl())));
                }
            } else {
                sender.sendMessage(Messages.getString("no-permission"));
            }
        }
        return true;
    }
}
