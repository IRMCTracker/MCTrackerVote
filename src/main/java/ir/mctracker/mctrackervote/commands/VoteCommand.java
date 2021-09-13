package ir.mctracker.mctrackervote.commands;

import ir.mctracker.mctrackervote.config.Config;
import ir.mctracker.mctrackervote.utilities.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Util.colorize("&cError!, This Command is Only For Players!"));
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {

            if (sender.hasPermission("mctracker.commands.vote")) {
                for (String s : Config.VOTE_MESSAGES) {
                    sender.sendMessage(Util.colorize(s.replace("{player}", player.getName()).replace("{vote_url}", Config.VOTE_URL)));
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
