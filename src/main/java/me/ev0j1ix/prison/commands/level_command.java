package me.ev0j1ix.prison.commands;

import me.ev0j1ix.prison.Prison;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class level_command implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        Player player = (Player)commandSender;

        Prison.level_system.level_up(player);
        //Bukkit.broadcastMessage(Prison.level_system.get_balance(player));

        return true;
    }
}
