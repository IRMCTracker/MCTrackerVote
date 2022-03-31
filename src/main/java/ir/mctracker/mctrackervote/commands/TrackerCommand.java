package ir.mctracker.mctrackervote.commands;

import ir.mctracker.mctrackervote.MCTrackerVote;
import ir.mctracker.mctrackervote.config.Config;
import ir.mctracker.mctrackervote.config.Messages;
import ir.mctracker.mctrackervote.utilities.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TrackerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(Util.colorize(Messages.PREFIX + "&a&lMCTrackerVote &aplugin By: &cCipher&7 & &cAlijk"));
            sender.sendMessage(Util.colorize(Messages.PREFIX + "&eWebsite: &bhttps://mctracker.ir"));
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("mctracker.commands.reload")) {
                    MCTrackerVote.getConfigManager().reloadAll();
                    sender.sendMessage(Util.colorize(Messages.PREFIX + "&aConfig Reloaded."));
                }
            }
        } else {
            sender.sendMessage(Util.colorize(Messages.PREFIX + "&cEntered arg is Not Valid!"));
        }
        return true;
    }

}
