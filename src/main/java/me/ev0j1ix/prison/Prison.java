package me.ev0j1ix.prison;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Prison extends JavaPlugin {

    public static Plugin plugin_system;
    public static scoreboard scoreboard_system;
    public static economy economy_system;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin_system = this;
        scoreboard_system = new scoreboard();
        economy_system = new economy();

        getServer().getPluginManager().registerEvents(new listener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
