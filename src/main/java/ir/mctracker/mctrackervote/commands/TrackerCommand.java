package ir.mctracker.mctrackervote.commands;

import ir.mctracker.mctrackervote.config.Config;
import ir.mctracker.mctrackervote.config.YMLLoader;
import ir.mctracker.mctrackervote.utilities.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TrackerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(Util.colorize(Config.PREFIX + "&bMCTrackerVote plugin By: &cCipher&b, &cAlijk"));
            sender.sendMessage(Util.colorize(Config.PREFIX + "&bWebsite: &chttps://mctracker.ir"));
            return true;
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("mctracker.commands.reload")) {
                    YMLLoader.reloadConfig();
                    sender.sendMessage(Util.colorize(Config.PREFIX + "&bConfig reloaded successfully"));
                    return true;
                }
            }
        } else {
            sender.sendMessage(Util.colorize(Config.PREFIX + "Entered arg is not valid"));
            return true;
        }

        return true;
    }

}
