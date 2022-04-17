package eu.mixeration.iospawn.iospawn.module;

import eu.mixeration.iospawn.iospawn.IOSpawn;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class file {
    public static File keyFile;
    public static FileConfiguration keyConfig;

    public static void createLanguage() {
        keyFile = new File(IOSpawn.getInstance().getDataFolder(), "language.yml");
        if (!keyFile.exists()) {
            keyFile.getParentFile().mkdirs();
            IOSpawn.getInstance().saveResource("language.yml", false);
        }
        keyConfig = new YamlConfiguration();
        try {
            keyConfig.load(keyFile);
        } catch (InvalidConfigurationException | IOException var2) {
            var2.printStackTrace();
        }
    }

    public static FileConfiguration getLanguage() {
        return keyConfig;
    }

    public static void saveLanguage() {
        IOSpawn.getInstance().getServer().getScheduler().runTask(IOSpawn.getInstance(), () ->{
            File fileKeys = new File(IOSpawn.getInstance().getDataFolder(), "language.yml");
            try {
                keyConfig.save(fileKeys);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static File dataFile;
    public static FileConfiguration dataConfig;

    public static void createData() {
        dataFile = new File(IOSpawn.getInstance().getDataFolder(), "storage.yml");
        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            IOSpawn.getInstance().saveResource("storage.yml", false);
        }
        dataConfig = new YamlConfiguration();
        try {
            dataConfig.load(dataFile);
        } catch (InvalidConfigurationException | IOException var2) {
            var2.printStackTrace();
        }
    }

    public static FileConfiguration getData() {
        return dataConfig;
    }

    public static void saveData() {
        IOSpawn.getInstance().getServer().getScheduler().runTask(IOSpawn.getInstance(), () ->{
            File fileKeys = new File(IOSpawn.getInstance().getDataFolder(), "storage.yml");
            try {
                dataConfig.save(fileKeys);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public static void saveSettings() {
        IOSpawn.getInstance().getServer().getScheduler().runTask(IOSpawn.getInstance(), () ->{
            File fileKeys = new File(IOSpawn.getInstance().getDataFolder(), "config.yml");
            try {
                IOSpawn.getInstance().getConfig().save(fileKeys);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void loadConfig() {
        FileConfiguration config = IOSpawn.getInstance().getConfig();
        new File(IOSpawn.getInstance().getDataFolder(), "config.yml");
        IOSpawn.getInstance().saveDefaultConfig();
    }
}
