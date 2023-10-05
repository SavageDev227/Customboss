package com.savage.plugins.customboss;

import com.comphenix.protocol.ProtocolLibrary;
import com.savage.plugins.customboss.commands.BossCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class CustomBoss extends JavaPlugin {

    private static CustomBoss plugin;
    Logger log = this.getLogger();


    @Override
    public void onEnable() {
        plugin = this;
        String prefix = "&c&l[CustomBoss]: &r";
        // Plugin startup logic
        ProtocolLibrary.getProtocolManager();
        getCommand("createboss").setExecutor(new BossCommand());
        if(!getServer().getVersion().contains("1.20")) {
            log.severe(ColorUtils.translateColorCodes(prefix + "&cThis plugin is only supported of version 1.20.x."));
            log.severe(ColorUtils.translateColorCodes(prefix + "&cThe plugin is now disabling."));
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        } else {
            log.info(prefix + "&a is now enabled on version: " + plugin.getServer().getVersion() + "!");
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }


}
