package eu.mixeration.iospawn.iospawn.module;

import static eu.mixeration.iospawn.iospawn.module.settings.locale;

public class message {

    public static String prefix = file.getLanguage().getString("prefix");

    public static String no_permission = file.getLanguage().getString("messages." + locale + ".no-permission");
    public static String plugin_reloaded = file.getLanguage().getString("messages." + locale + ".plugin-reloaded");
    public static String only_in_game = file.getLanguage().getString("messages." + locale + ".only-in-game");
    public static String plugin_saved = file.getLanguage().getString("messages." + locale + ".files-saved");
    public static String list_title = file.getLanguage().getString("messages." + locale + ".list-title");
    public static String spawn_setted = file.getLanguage().getString("messages." + locale + ".spawn.setted");
    public static String spawn_removed = file.getLanguage().getString("messages." + locale + ".spawn.removed");
    public static String spawn_already = file.getLanguage().getString("messages." + locale + ".spawn.choose-different-name");
    public static String spawn_mode = file.getLanguage().getString("messages." + locale + ".mode-is");
    public static String spawn_teleporting = file.getLanguage().getString("messages." + locale + ".spawn.teleporting");
    public static String spawn_gui_title = file.getLanguage().getString("messages." + locale + ".menu.gui-title");
    public static String spawn_gui_item_lore = file.getLanguage().getString("messages." + locale + ".menu.gui-item-lore");
    public static String error_not_ready = file.getLanguage().getString("messages." + locale + ".error.not-ready");
    public static String error_spawn_not_found = file.getLanguage().getString("messages." + locale + ".error.spawn-not-found");
    public static String error_not_enough_level = file.getLanguage().getString("messages." + locale + ".error.not-enough-level");
    public static String error_no_data_found = file.getLanguage().getString("messages." + locale + ".error.no-data-found");

    public static String console_not_ready = file.getLanguage().getString("messages." + locale + ".console.not-ready");

}
