package eu.mixeration.iospawn.iospawn.commands;

import eu.mixeration.iospawn.iospawn.IOSpawn;
import eu.mixeration.iospawn.iospawn.module.*;
import eu.mixeration.iospawn.iospawn.module.gui.clickToTeleport;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import static eu.mixeration.iospawn.iospawn.module.get.*;
import static eu.mixeration.iospawn.iospawn.module.message.*;

public class player implements CommandExecutor {
    public player(IOSpawn ioSpawn) {}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(command.getName().equalsIgnoreCase("IOSpawn")) {
                if(player.hasPermission("iospawn.adminisator")) {
                    if(args.length == 0 || args[0].equalsIgnoreCase("help")) {
                        for(String help : file.getLanguage().getStringList("messages." + settings.locale + ".help-messages.adminisator")) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', help));
                        }
                        return true;
                    } else if(args.length == 1) {
                        if(args[0].equalsIgnoreCase("list")) {
                            ConfigurationSection section = file.getData().getConfigurationSection("io-spawn-storage");
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', list_title));
                            for (ConfigurationSection subNode : getSections(section)) {
                                player.sendMessage(" # " + ChatColor.ITALIC + subNode.getName().toString());
                            }
                            return true;
                        }else if(args[0].equalsIgnoreCase("reload")) {
                            IOSpawn.getInstance().getConfig().options().copyDefaults(true);
                            IOSpawn.getInstance().saveConfig();
                            file.saveData();
                            file.saveLanguage();
                            IOSpawn.getInstance().saveConfig();
                            IOSpawn.getInstance().reloadConfig();
                            file.saveData();
                            file.saveLanguage();
                            IOSpawn.getInstance().saveConfig();
                            iosend(player, message.plugin_reloaded);
                            return true;
                        }else if(args[0].equalsIgnoreCase("menu")) {
                            clickToTeleport.openInventory(player);
                            return true;
                        }else if(args[0].equalsIgnoreCase("save")) {
                            IOSpawn.getInstance().getConfig().options().copyDefaults(true);
                            IOSpawn.getInstance().saveConfig();
                            file.saveData();
                            file.saveLanguage();
                            file.saveSettings();
                            IOSpawn.getInstance().saveConfig();
                            iosend(player, message.plugin_saved);
                            return true;
                        }else if(args[0].equalsIgnoreCase("mode")) {
                            iosend(player, message.spawn_mode.replace("<mode>", settings.join_mode));
                            return true;
                        }
                    } else if(args.length == 2) {
                        String argument = args[1];
                        if (args[0].equalsIgnoreCase("set")) {
                            set.setSpawn(player, argument);
                            return true;
                        } else if (args[0].equalsIgnoreCase("remove")) {
                            set.remSpawn(player, argument);
                            return true;
                        } else if (args[0].equalsIgnoreCase("teleport")) {
                            get.sendSpawn(player, argument);
                            return true;
                        }
                    }
                } else {
                    if(args.length == 0 || args[0].equalsIgnoreCase("help")) {
                        for (String help : file.getLanguage().getStringList("messages." + settings.locale + ".help-messages.user")) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', help));
                        }
                        return true;
                    } else if(args[0].equalsIgnoreCase("list")) {
                        ConfigurationSection section = file.getData().getConfigurationSection("io-spawn-storage");
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', list_title));
                        for (ConfigurationSection subNode : getSections(section)) {
                            for (String list : file.getData().getStringList("io-spawn-storage.")) {
                                if (!(list.isEmpty())) {
                                    player.sendMessage(" # " + ChatColor.ITALIC + subNode.getName().toString());
                                } else {
                                    iosend(player, error_no_data_found);
                                    return true;
                                }
                            }
                        }
                        return true;
                    } else if(args[0].equalsIgnoreCase("menu")) {
                        clickToTeleport.openInventory(player);
                        return true;
                    } else if (args.length == 1) {
                        String argument = args[0];
                        if(!(file.getData().getString("io-spawn-storage." + argument) == null)) {
                            String permission = file.getData().getString("io-spawn-storage." + argument + ".permission");
                            if (player.hasPermission(permission)) {
                                get.sendSpawn(player, argument);
                                return true;
                            } else {
                                iosend(player, no_permission.replace("<permission>", permission));
                                return true;
                            }
                        } else {
                            iosend(player, error_spawn_not_found.replace("<spawn>", argument));
                            return true;
                        }
                    }
                }
            }
        } else {
            console(message.only_in_game);
            return true;
        }
        return true;
    }
}
