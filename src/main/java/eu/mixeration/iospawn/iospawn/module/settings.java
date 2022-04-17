package eu.mixeration.iospawn.iospawn.module;

import eu.mixeration.iospawn.iospawn.IOSpawn;

public class settings {

    public static String locale = IOSpawn.getInstance().getConfig().getString("io-spawn.locale");

    public static String firstJoinSpawn = IOSpawn.getInstance().getConfig().getString("io-spawn.first-join-spawn");

    public static String defaultSpawn = IOSpawn.getInstance().getConfig().getString("io-spawn.default-spawn");

    public static String join_mode = IOSpawn.getInstance().getConfig().getString("io-spawn.join-mode");

    public static boolean readytouse = IOSpawn.getInstance().getConfig().getBoolean("io-series.Ready-to-use");

    public static String dateformat = IOSpawn.getInstance().getConfig().getString("io-series.date-format");

}
