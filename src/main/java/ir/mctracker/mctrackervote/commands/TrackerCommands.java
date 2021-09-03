package ir.mctracker.mctrackervote.commands;

import ir.mctracker.mctrackervote.utilities.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TrackerCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(Util.colorize("&aMC&FVote >> &6MCTrackerVote plugin By: &4Cipher, Alijk"));
            return true;
        }

        return true;
    }
}
