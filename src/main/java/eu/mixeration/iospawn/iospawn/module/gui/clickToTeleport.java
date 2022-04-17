package eu.mixeration.iospawn.iospawn.module.gui;

import eu.mixeration.iospawn.iospawn.module.create;
import eu.mixeration.iospawn.iospawn.module.file;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;

import static eu.mixeration.iospawn.iospawn.module.create.createGuiItem;
import static eu.mixeration.iospawn.iospawn.module.get.getSections;
import static eu.mixeration.iospawn.iospawn.module.message.*;

public class clickToTeleport {
    private static Inventory inv = null;

    public static void openInventory(final HumanEntity ent) {
        inv = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&', spawn_gui_title));
        loadItems();
        ent.openInventory(inv);
    }

    public static void loadItems() {
        ConfigurationSection section = file.getData().getConfigurationSection("io-spawn-storage");
        for (ConfigurationSection subNode : getSections(section)) {
            inv.addItem(createGuiItem(Material.GRASS_BLOCK, subNode.getName().toString()));
        }
    }
}
