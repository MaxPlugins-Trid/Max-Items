package com.tridiumx.maxitems.Powers.Sword;

import com.tridiumx.maxitems.Powers.MaxPower;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by TridiumX on 7/17/2016.
 */
public abstract class MaxPowerSword extends MaxPower implements Listener{
    @EventHandler
    public void onEntityDamageEntity(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player){
            onDamage(event);
        }
    }

    public abstract void onDamage(EntityDamageByEntityEvent event);


}
