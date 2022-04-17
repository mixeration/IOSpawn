package eu.mixeration.iospawn.iospawn.module;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static eu.mixeration.iospawn.iospawn.module.message.*;

public class get {

    public static void iosend(Player player, String path) {
        player.sendMessage((ChatColor.translateAlternateColorCodes('&', prefix)) + " " + (ChatColor.translateAlternateColorCodes('&', path)));
    }

    public static void console(String message) {
        Bukkit.getConsoleSender().sendMessage((ChatColor.translateAlternateColorCodes('&', prefix)) + " " + (ChatColor.translateAlternateColorCodes('&', message)));
    }

    public static void sendSpawn(Player player, String name) {
        if(!(file.getData().getString("io-spawn-storage." + name) == null)) {
            String worldName = file.getData().getString("io-spawn-storage." + name + ".worldName");
            World spawnWorld = Bukkit.getServer().getWorld(worldName);
            double x = file.getData().getDouble("io-spawn-storage." + name + ".x");
            double y = file.getData().getDouble("io-spawn-storage." + name + ".y");
            double z = file.getData().getDouble("io-spawn-storage." + name + ".z");
            float yaw = (float) file.getData().getDouble("io-spawn-storage." + name + ".double");
            float pitch = (float) file.getData().getDouble("io-spawn-storage." + name + ".pitch");
            Location loc = new Location(spawnWorld, x, y, z);
            loc.setYaw(yaw);
            player.getLocation().setPitch(pitch);
            player.teleport(loc);
            iosend(player, spawn_teleporting.replace("<spawn>", name));
        } else {
            iosend(player, error_spawn_not_found.replace("<spawn>", name));
        }
    }

    public static List<ConfigurationSection> getSections(ConfigurationSection source) {
        List<ConfigurationSection> nodes = new ArrayList<ConfigurationSection>();
        for (String key : source.getKeys(false)) {
            if (source.isConfigurationSection(key)) {
                nodes.add(source.getConfigurationSection(key));
            }
        }
        return nodes;
    }

}
