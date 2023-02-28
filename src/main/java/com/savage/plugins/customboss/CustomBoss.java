package com.savage.plugins.customboss;

import com.comphenix.protocol.ProtocolLibrary;
import com.savage.plugins.customboss.commands.BossCommand;
import org.bukkit.plugin.java.JavaPlugin;
public class CustomBoss extends JavaPlugin {






    @Override
    public void onEnable() {
        // Plugin startup logic
        ProtocolLibrary.getProtocolManager();
        getCommand("createboss").setExecutor(new BossCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }


}
