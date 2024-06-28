package ir.mctracker.core.commands;

import ir.mctracker.MCTrackerVote;
import ir.mctracker.core.config.Configuration;
import ir.mctracker.core.config.Messages;
import ir.mctracker.core.utilities.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TrackerCommand implements CommandExecutor {

    private Messages messages;

    public TrackerCommand(Messages messages) {
        this.messages = messages;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(Util.colorize(Messages.getString("prefix") + "&a&lMCTrackerVote &aplugin By: &cCipher&7 & &cAlijk"));
            sender.sendMessage(Util.colorize(Messages.getString("prefix") + "&eWebsite: &bhttps://mctracker.ir"));
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("mctracker.commands.reload")) {
                    Configuration.reloadConfiguration();
                    sender.sendMessage(Util.colorize(Messages.getString("prefix") + "&aConfig Reloaded."));
                }
            }
        } else {
            sender.sendMessage(Util.colorize(Messages.getString("prefix") + "&cEntered arg is Not Valid"));
        }
        return true;
    }

}