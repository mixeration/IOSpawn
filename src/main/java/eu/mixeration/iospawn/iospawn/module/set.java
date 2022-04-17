package eu.mixeration.iospawn.iospawn.module;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

import static eu.mixeration.iospawn.iospawn.module.get.iosend;
import static eu.mixeration.iospawn.iospawn.module.message.*;

public class set {

    public static void setSpawn(Player player, String name) {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat(settings.dateformat);
        if(file.getData().getString("io-spawn-storage." + name) == null) {
            String worldName = file.getData().getString("io-spawn-storage." + name + ".worldName");
            Location location = player.getLocation();
            file.getData().set("io-spawn-storage." + name + ".worldName", player.getWorld().getName());
            file.getData().set("io-spawn-storage." + name + ".x", location.getX());
            file.getData().set("io-spawn-storage." + name + ".y", location.getY());
            file.getData().set("io-spawn-storage." + name + ".z", location.getZ());
            file.getData().set("io-spawn-storage." + name + ".yaw", location.getYaw());
            file.getData().set("io-spawn-storage." + name + ".pitch", location.getPitch());
            file.getData().set("io-spawn-storage." + name + ".by", player.getName());
            file.getData().set("io-spawn-storage." + name + ".createdAt", now);
            file.getData().set("io-spawn-storage." + name + ".permission", "iospawn." + name);
            file.saveData();
            iosend(player, spawn_setted.replace("<spawn>", name));
        } else {
            iosend(player, spawn_already.replace("<spawn>", name));
        }
    }
    public static void remSpawn(Player player, String name) {
        if(!(file.getData().getString("io-spawn-storage." + name) == null)) {
            file.getData().set("io-spawn-storage." + name, null);
            file.saveData();
            iosend(player, spawn_removed.replace("<spawn>", name));
        } else {
            iosend(player, error_spawn_not_found.replace("<spawn>", name));
        }
    }
}
