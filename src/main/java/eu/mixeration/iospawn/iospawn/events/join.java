package eu.mixeration.iospawn.iospawn.events;

import eu.mixeration.iospawn.iospawn.IOSpawn;
import eu.mixeration.iospawn.iospawn.module.settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

import static eu.mixeration.iospawn.iospawn.module.get.*;
import static eu.mixeration.iospawn.iospawn.module.message.*;
import static eu.mixeration.iospawn.iospawn.module.settings.defaultSpawn;

public class join implements Listener {
    public join(IOSpawn ioSpawn) {}

    @EventHandler
    public void join(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(settings.readytouse) {
            if (!player.hasPlayedBefore()) {
                sendSpawn(player, settings.firstJoinSpawn.replace("<spawn>", settings.firstJoinSpawn));
            } else {
                if (settings.join_mode.equalsIgnoreCase("permission")) {
                    for (String groups : IOSpawn.getInstance().getConfig().getConfigurationSection("io-spawn.permission").getKeys(false)) {
                        String permission = IOSpawn.getInstance().getConfig().getString("io-spawn.permission." + groups + ".permission");
                        String spawnName = IOSpawn.getInstance().getConfig().getString("io-spawn.permission." + groups + ".spawn-name");
                        if (player.hasPermission(permission)) {
                            sendSpawn(player, spawnName.replace("<spawn>", spawnName));
                        } else {
                            sendSpawn(player, defaultSpawn.replace("<spawn>", defaultSpawn));
                        }
                    }
                } else if (settings.join_mode.equalsIgnoreCase("default")) {
                    sendSpawn(player, defaultSpawn.replace("<spawn>", defaultSpawn));
                } else if (settings.join_mode.equalsIgnoreCase("player-level")) {
                    for (String groups : IOSpawn.getInstance().getConfig().getConfigurationSection("io-spawn.player-level").getKeys(false)) {
                        int level = IOSpawn.getInstance().getConfig().getInt("io-spawn.player-level." + groups + ".level");
                        String spawnName = IOSpawn.getInstance().getConfig().getString("io-spawn.player-level." + groups + ".spawn-name");
                        if (player.getLevel() >= level) {
                            sendSpawn(player, spawnName.replace("<spawn>", spawnName));
                        } else {
                            iosend(player, error_not_enough_level.replace("<neededLevel>", String.valueOf(level)).replace("<yourLevel>", String.valueOf(player.getLevel())));
                        }
                    }
                }else if (settings.join_mode.equalsIgnoreCase("perplayer")) {
                    for (String groups : IOSpawn.getInstance().getConfig().getConfigurationSection("io-spawn.perplayer").getKeys(false)) {
                        List<String> players = IOSpawn.getInstance().getConfig().getStringList("io-spawn.perplayer." + groups + ".players");
                        String spawnName = IOSpawn.getInstance().getConfig().getString("io-spawn.perplayer." + groups + ".spawn-name");
                        if (players.contains(player.getName())) {
                            sendSpawn(player, spawnName.replace("<spawn>", spawnName));
                        } else {
                            sendSpawn(player, defaultSpawn.replace("<spawn>", defaultSpawn));
                        }
                    }
                }
            }
        } else {
            iosend(player, error_not_ready);
            console(console_not_ready);
        }
    }
}
