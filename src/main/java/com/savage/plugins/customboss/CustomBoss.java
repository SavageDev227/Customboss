package com.savage.plugins.customboss;

import com.comphenix.protocol.ProtocolLibrary;
import com.savage.plugins.customboss.commands.BossCommand;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class CustomBoss extends JavaPlugin {

    private static CustomBoss plugin;
    Logger log = this.getLogger();


    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic
        ProtocolLibrary.getProtocolManager();
        getCommand("createboss").setExecutor(new BossCommand());
        log.info(ColorUtils.translateColorCodes("&a&l[CustomBoss]: &aPlugin Enabled on server version: " + getServer().getVersion()));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }


}
