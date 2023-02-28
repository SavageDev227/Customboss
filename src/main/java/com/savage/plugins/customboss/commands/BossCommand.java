package com.savage.plugins.customboss.commands;

import com.savage.plugins.customboss.CustomBoss;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BossCommand implements CommandExecutor, TabCompleter, Listener {




    public Entity bossEntity;
    public BossBar bossBar;

    private CustomBoss plugin;




    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("createboss")) {
            if(sender instanceof Player) {
                if (args.length < 4) {
                    p.sendMessage(ChatColor.RED + "Invalid Syntax! Please use: /createboss [mob] [name] [bar color] [health] [damage]");
                }
                if (!sender.hasPermission("customboss.spawnboss")) {
                    p.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
                    return true;
                }

            }

            String bossType = args[0];
            String bossName = args[1];
            BarColor bosscolor = BarColor.valueOf(args[2]);
            String bossHeath = args[3];
            String bossDmg = args[4];
            double health = Integer.parseInt(bossHeath);
            double dmg = Integer.parseInt(bossDmg);





            summonBoss(p, bossType, bossName, health, dmg, bosscolor);

        }
        return true;
    }

    public CustomBoss main;
    private void summonBoss(Player player, String bossType, String bossName, double health, double dmg, BarColor barColor) {
        // Get the location where the boss should be summoned
        Location location = player.getLocation();

        // Create the boss entity using the correct entity type
        EntityType entityType = EntityType.valueOf(bossType.toUpperCase());
        Entity bossEntity = player.getWorld().spawnEntity(location, entityType);

        // Set the name of the boss
        bossEntity.setCustomName(bossName);
        bossEntity.setCustomNameVisible(true);

        // Modify the attributes of the boss using ProtocolLib
        AttributeInstance healthAttribute = ((LivingEntity) bossEntity).getAttribute(Attribute.GENERIC_MAX_HEALTH);
        healthAttribute.setBaseValue(health * 2);
        ((LivingEntity) bossEntity).setHealth(health * 2);

        AttributeInstance attackDamageAttribute = ((LivingEntity) bossEntity).getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        attackDamageAttribute.setBaseValue(dmg);

        bossBar = Bukkit.createBossBar(bossName, barColor, BarStyle.SOLID);
        bossBar.setVisible(true);
        bossBar.setProgress(1.0);
        bossBar.addPlayer(player);





    }

    EntityType[] hostileMobTypes = { EntityType.ZOMBIE, EntityType.SKELETON, EntityType.SPIDER, EntityType.CAVE_SPIDER, EntityType.CREEPER, EntityType.ENDERMAN, EntityType.WITCH, EntityType.PIGLIN, EntityType.HOGLIN, EntityType.BLAZE, EntityType.GHAST, EntityType.MAGMA_CUBE, EntityType.SLIME, EntityType.SILVERFISH, EntityType.ENDERMITE, EntityType.VEX, EntityType.VINDICATOR, EntityType.EVOKER, EntityType.PILLAGER, EntityType.RAVAGER };

    public boolean isHostile(Entity entity) {
        return Arrays.asList(hostileMobTypes).contains(entity.getType());
    }
    List<String> mobNames = new ArrayList<>();



    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 3) {
            // Tab completion for the first argument - the boss color
            return Arrays.asList("RED", "GREEN", "BLUE", "YELLOW", "PURPLE", "PINK", "WHITE", "PINK");
        } else if (args.length == 1) {
            for (EntityType mob : hostileMobTypes) {
                mobNames.add(mob.name());
            };
            return mobNames;
        } else {
            return new ArrayList<>();
        }

    }


}
