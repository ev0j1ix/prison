package me.ev0j1ix.prison;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class listener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Bukkit.getPluginManager().getPlugin("prison").getLogger().info("join player");

        if(!Prison.economy_system.balance.has(event.getPlayer().getPlayerProfile().getId().toString())) // проверяем если нету в json object uuid игрока то значит это новый игрок и создаём ему дефолтный баланс
            Prison.economy_system.set_default(event.getPlayer());

        if(!Prison.level_system.level_base.has(event.getPlayer().getPlayerProfile().getId().toString())) // проверяем если нету в json object uuid игрока то значит это новый игрок и создаём ему дефолтный баланс
            Prison.level_system.set_default(event.getPlayer());

        Prison.scoreboard_system.on_player_join(event);
    }
}
