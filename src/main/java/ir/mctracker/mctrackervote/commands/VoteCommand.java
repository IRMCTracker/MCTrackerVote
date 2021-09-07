package ir.mctracker.mctrackervote.commands;

import ir.mctracker.mctrackervote.config.Config;
import ir.mctracker.mctrackervote.config.YMLLoader;
import ir.mctracker.mctrackervote.utilities.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class VoteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            if (sender.hasPermission("mctracker.commands.vote")) {
                for (String s : Config.VOTE_MESSAGES) {
                    sender.sendMessage(Util.colorize(s));
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
