package me.ev0j1ix.prison.commands;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.ev0j1ix.prison.Prison;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class mine_command implements CommandExecutor {
    JsonObject mines;
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        Player player = (Player)commandSender;

        mines = new JsonObject();
        mines.add("dirt", new JsonObject());
        mines.get("dirt").getAsJsonObject().add("mins", new JsonObject());
        mines.get("dirt").getAsJsonObject().get("mins").getAsJsonObject().addProperty("x", 270);
        mines.get("dirt").getAsJsonObject().get("mins").getAsJsonObject().addProperty("y", 62);
        mines.get("dirt").getAsJsonObject().get("mins").getAsJsonObject().addProperty("z", -271);
        mines.get("dirt").getAsJsonObject().add("maxs", new JsonObject());
        mines.get("dirt").getAsJsonObject().get("maxs").getAsJsonObject().addProperty("x", 282);
        mines.get("dirt").getAsJsonObject().get("maxs").getAsJsonObject().addProperty("y", 65);
        mines.get("dirt").getAsJsonObject().get("maxs").getAsJsonObject().addProperty("z", -259);
        mines.get("dirt").getAsJsonObject().add("blocks", new JsonObject());
        mines.get("dirt").getAsJsonObject().get("blocks").getAsJsonObject().add("diamond", new JsonObject());
        mines.get("dirt").getAsJsonObject().get("blocks").getAsJsonObject().get("diamond").getAsJsonObject().addProperty("id", 77);
        mines.get("dirt").getAsJsonObject().get("blocks").getAsJsonObject().get("diamond").getAsJsonObject().addProperty("percent", 0.05);
        mines.get("dirt").getAsJsonObject().get("blocks").getAsJsonObject().add("dirt", new JsonObject());
        mines.get("dirt").getAsJsonObject().get("blocks").getAsJsonObject().get("dirt").getAsJsonObject().addProperty("id", 15);
        mines.get("dirt").getAsJsonObject().get("blocks").getAsJsonObject().get("dirt").getAsJsonObject().addProperty("percent", 0.95);

        JsonObject mine_dirt = mines.get("dirt").getAsJsonObject();
        JsonObject mine_dirt_min = mine_dirt.get("mins").getAsJsonObject();
        JsonObject mine_dirt_max = mine_dirt.get("maxs").getAsJsonObject();

        Location mins = new Location(player.getWorld(), mine_dirt_min.get("x").getAsInt(), mine_dirt_min.get("y").getAsInt(), mine_dirt_min.get("z").getAsInt());
        Location maxs = new Location(player.getWorld(),mine_dirt_max.get("x").getAsInt(), mine_dirt_max.get("y").getAsInt(), mine_dirt_max.get("z").getAsInt());

        ArrayList<Location> blocks = new ArrayList<>();

        for(int x = mins.getBlockX(); x <= maxs.getBlockX(); x++)
        {
            for(int y = mins.getBlockY(); y <= maxs.getBlockY(); y++)
            {
                for(int z = mins.getBlockZ(); z <= maxs.getBlockZ(); z++)
                {
                    blocks.add(new Location(player.getWorld(), x, y, z));
                }
            }
        }

        Collections.shuffle(blocks);

        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> block = new ArrayList<>();

        for (Map.Entry<String, JsonElement> entry : mine_dirt.get("blocks").getAsJsonObject().entrySet())
        {
            JsonObject json = entry.getValue().getAsJsonObject();
            Integer block_count = (int) (blocks.size() * json.get("percent").getAsDouble());
            AbstractMap.SimpleEntry map = new AbstractMap.SimpleEntry<>(json.get("id").getAsInt(), block_count);
            block.add(map);
        }

        Iterator iterator = blocks.stream().parallel().iterator();
        Iterator iterator2 = block.iterator();

        AbstractMap.SimpleEntry<Integer, Integer> block_pair = (AbstractMap.SimpleEntry<Integer, Integer>) iterator2.next();

        while(iterator.hasNext())
        {
            Location loc = (Location) iterator.next();

            if(block_pair.getValue() <= 0)
            {
                if(iterator2.hasNext()) {
                    block_pair = (AbstractMap.SimpleEntry<Integer, Integer>) iterator2.next();
                    Prison.plugin_system.getLogger().info("change block");
                }
            }

            loc.getBlock().setType(Material.values()[block_pair.getKey()]);
            block_pair.setValue(block_pair.getValue() - 1);
        }

        //Inventory inventory = Bukkit.createInventory(null, 9, "Шахты");

        //player.openInventory(inventory);

        return true;
    }
}
