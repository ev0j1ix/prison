package me.ev0j1ix.prison;

import me.ev0j1ix.prison.commands.balance;
import me.ev0j1ix.prison.commands.level_command;
import me.ev0j1ix.prison.commands.mine_command;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public final class Prison extends JavaPlugin {

    public static Plugin plugin_system;
    public static scoreboard scoreboard_system;
    public static economy economy_system;
    public static level level_system;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin_system = this;
        scoreboard_system = new scoreboard();
        economy_system = new economy();
        level_system = new level();

        getCommand("balance").setExecutor(new balance());
        getCommand("level").setExecutor(new level_command());
        getCommand("mine").setExecutor(new mine_command());

        getServer().getPluginManager().registerEvents(new listener(), this);

        BukkitScheduler bukkitScheduler = Bukkit.getScheduler();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
