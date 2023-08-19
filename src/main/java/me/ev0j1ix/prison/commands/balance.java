package me.ev0j1ix.prison.commands;

import me.ev0j1ix.prison.Prison;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class balance implements CommandExecutor {
    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player)sender;

        Bukkit.broadcastMessage(Prison.economy_system.get_balance(player));

        return true;
    }
}
