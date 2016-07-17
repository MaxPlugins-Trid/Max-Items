package com.tridiumx.maxitems.Powers;

import com.tridiumx.maxitems.Utils.CoolDownGenerator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

/**
 * Created by TridiumX on 7/16/2016.
 */
public abstract class MaxPower implements Listener{
    public Plugin plugin;
    public CoolDownGenerator cdManager;

    public void onEnable(Plugin plugin, CoolDownGenerator cdManager){
        this.plugin = plugin;
        this.cdManager = cdManager;
        registerPower();
    }
    public boolean itemInHandHasStat(Player p, String stat){
        for(String lore : p.getInventory().getItemInMainHand().getItemMeta().getLore()){
            if(lore.contains(stat)){
                return true;
            }
        }

        return false;
    }
    public boolean itemHasStat(ItemStack item, String stat){
        for(String lore : item.getItemMeta().getLore()){
            if(lore.contains(stat)){
                return true;
            }
        }



        return false;
    }

    public abstract void registerPower();









}

