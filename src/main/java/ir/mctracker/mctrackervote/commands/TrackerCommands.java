package ir.mctracker.mctrackervote.commands;

import ir.mctracker.mctrackervote.config.YMLLoader;
import ir.mctracker.mctrackervote.utilities.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TrackerCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(Util.colorize( Util.prefix + "&bMCTrackerVote plugin By: &cCipher&b, &cAlijk"));
            return true;
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("Reload")) {
                if (sender.hasPermission("mctracker.commands.reload")) {
                    YMLLoader.reloadConfig();
                    sender.sendMessage(Util.colorize(Util.prefix + "&bConfig reloaded successfully"));
                    return true;
                }
            }
        }

        return true;
    }
}
