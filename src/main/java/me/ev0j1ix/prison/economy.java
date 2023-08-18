package me.ev0j1ix.prison;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.entity.Player;

public class economy {
     JsonObject balance;

     public economy() {
         Prison.plugin_system.getLogger().info("Initialization economy system");
         String json_string = Prison.plugin_system.getConfig().get("balance").toString();
         Prison.plugin_system.getLogger().info(json_string);
         JsonElement element = JsonParser.parseString(json_string);
         balance = element.getAsJsonObject();
     }

     public String get_balance(Player player)
     {
         return balance.get(player.getPlayerProfile().getId().toString()).getAsJsonObject().get("balance").toString();
     }

     public void set_default(Player player)
     {
         JsonObject obj = new JsonObject();
         obj.addProperty("balance", 1000);
         balance.add(player.getPlayerProfile().getId().toString(), obj);
         Prison.plugin_system.getLogger().info(balance.get(player.getPlayerProfile().getId().toString()).getAsJsonObject().get("balance").toString());
     }
}
