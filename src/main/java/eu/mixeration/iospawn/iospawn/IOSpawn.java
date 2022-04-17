package eu.mixeration.iospawn.iospawn;

import eu.mixeration.iospawn.iospawn.commands.player;
import eu.mixeration.iospawn.iospawn.events.join;
import eu.mixeration.iospawn.iospawn.module.gui.interact;
import eu.mixeration.iospawn.iospawn.module.settings;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import static eu.mixeration.iospawn.iospawn.module.get.console;

public final class IOSpawn extends JavaPlugin {
    private static IOSpawn instance;
    public static synchronized IOSpawn getInstance() {
        return instance;
    }
    public static synchronized void setInstance(IOSpawn mixeration) {
        instance = mixeration;
    }

    @Override
    public void onEnable() {
        setInstance(this);
        eu.mixeration.iospawn.iospawn.module.file.loadConfig();
        eu.mixeration.iospawn.iospawn.module.file.createData();
        eu.mixeration.iospawn.iospawn.module.file.createLanguage();
        Bukkit.getServer().getPluginManager().registerEvents(new join(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new interact(this), this);
        getCommand("IOSpawn").setExecutor(new player(this));
        console("&fJoin mode is &b" + settings.join_mode);
        console("&fFirst join spawn &b" + settings.firstJoinSpawn);
        console("&fDefault spawn &b" + settings.defaultSpawn);
        console("&fLocale is &b" + settings.locale);

    }
}
