package me.ev0j1ix.prison;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.entity.Player;

public class level {
    JsonObject level_base;

    public level() {
        Prison.plugin_system.getLogger().info("Initialization level system");
        String json_string = Prison.plugin_system.getConfig().get("users").toString();
        JsonElement element = JsonParser.parseString(json_string);
        level_base = element.getAsJsonObject();
    }

    public String get_balance(Player player)
    {
        if(!level_base.has(player.getPlayerProfile().getId().toString()))
            return "not found uuid";

        if(!level_base.get(player.getPlayerProfile().getId().toString()).getAsJsonObject().has("level"))
            return "not found balance";

        return level_base.get(player.getPlayerProfile().getId().toString()).getAsJsonObject().get("level").toString();
    }

    // test function
    public void level_up(Player player)
    {
        level_base.get(player.getPlayerProfile().getId().toString()).getAsJsonObject().addProperty("level", 6);
    }

    public void set_default(Player player) {
        JsonObject obj = new JsonObject();
        obj.addProperty("level", 5);
        level_base.add(player.getPlayerProfile().getId().toString(), obj);
    }


}
