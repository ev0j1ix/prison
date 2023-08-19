package me.ev0j1ix.prison;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

public class scoreboard {

    Scoreboard scoreboard;
    ScoreboardManager scoreboard_manager;
    Objective objective;
    int slot = 1;

    public scoreboard() {
        Bukkit.getPluginManager().getPlugin("prison").getLogger().info("Initilization scoreboard system");
        scoreboard_manager = Bukkit.getScoreboardManager();
    }
    public void create_scoreboard(String name) {
        scoreboard = scoreboard_manager.getNewScoreboard();
        objective = scoreboard.registerNewObjective(name, "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(name);
    }
    public void add_score(String name) {
        Score score = objective.getScore(name);
        score.setScore(slot);
        slot++;
    }
    public void finish_scoreboard(Player player)
    {
        player.setScoreboard(scoreboard);
    }
    public void on_player_join(PlayerJoinEvent event){
        Prison.scoreboard_system.create_scoreboard("Prison");
        Prison.scoreboard_system.add_score(ChatColor.WHITE + "Fraction: Nigger");
        Prison.scoreboard_system.add_score("");
        Prison.scoreboard_system.add_score(ChatColor.WHITE + "Money: " + Prison.economy_system.get_balance(event.getPlayer()) + "$");
        Prison.scoreboard_system.add_score(ChatColor.WHITE + "Level: " + Prison.level_system.get_balance(event.getPlayer()));
        Prison.scoreboard_system.add_score(ChatColor.WHITE + "Username: " + event.getPlayer().getDisplayName());
        Prison.scoreboard_system.finish_scoreboard(event.getPlayer());
    }

}
