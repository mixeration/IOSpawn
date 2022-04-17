package eu.mixeration.iospawn.iospawn.module.gui;

import eu.mixeration.iospawn.iospawn.IOSpawn;
import eu.mixeration.iospawn.iospawn.module.file;
import eu.mixeration.iospawn.iospawn.module.message;
import eu.mixeration.iospawn.iospawn.module.settings;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

import static eu.mixeration.iospawn.iospawn.module.get.*;
import static eu.mixeration.iospawn.iospawn.module.message.error_spawn_not_found;
import static eu.mixeration.iospawn.iospawn.module.message.spawn_gui_title;

public class interact implements Listener {

    public interact(IOSpawn ioSpawn) {
    }

    @EventHandler
    public void menu(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        String menuName =event.getView().getTitle();
        final ItemStack clickedItem = event.getCurrentItem();
        if(menuName.equals(ChatColor.translateAlternateColorCodes('&', spawn_gui_title))) {
            event.setCancelled(true);
            ConfigurationSection section = file.getData().getConfigurationSection("io-spawn-storage");
            for (ConfigurationSection subNode : getSections(section)) {
                if(!(clickedItem == null)) {
                    if(clickedItem.getType() == Material.GRASS_BLOCK) {
                        if(clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase(subNode.getName().toString())) {
                            if(!(file.getData().getString("io-spawn-storage." + subNode.getName().toString()) == null)) {
                                String permission = file.getData().getString("io-spawn-storage." + subNode.getName().toString() + ".permission");
                                if (player.hasPermission(permission)) {
                                    sendSpawn(player, subNode.getName().toString());
                                }
                            } else {
                                iosend(player, error_spawn_not_found.replace("<spawn>", subNode.getName().toString()));
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClick(final InventoryDragEvent event) {
        String menuName =event.getView().getTitle();
        if(menuName.equals(spawn_gui_title)) {
            event.setCancelled(true);
        }
    }

}
