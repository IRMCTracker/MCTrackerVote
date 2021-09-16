package ir.mctracker.mctrackervote.commands;

import ir.mctracker.mctrackervote.API.PlayerPreVoteEvent;
import ir.mctracker.mctrackervote.config.Config;
import ir.mctracker.mctrackervote.utilities.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Util.colorize("&cThis command is just applicable for players"));
            return true;
        }

        Player p = (Player) sender;
        PlayerPreVoteEvent playerPreVoteEvent = new PlayerPreVoteEvent(p);
        Bukkit.getPluginManager().callEvent(playerPreVoteEvent);
        if (playerPreVoteEvent.isCancelled()) {
            return false;
        }

        if (args.length == 0) {
            if (sender.hasPermission("mctracker.commands.vote")) {
                for (String s : Config.VOTE_MESSAGES) {
                    sender.sendMessage(Util.colorize(s.replace("{player}", p.getName()).replace("{vote_url}", Config.VOTE_URL)));
                }
                return true;
            } else {
                sender.sendMessage(Config.NO_PERMISSION);
                return true;
            }
        }
        return true;
    }
}
