package com.tridiumx.maxitems.Powers.Bow;

import com.tridiumx.maxitems.Powers.MaxPower;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

/**
 * Created by TridiumX on 7/17/2016.
 */
public abstract class MaxPowerBow extends MaxPower implements Listener{

    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent event){
        if(event.getEntity() instanceof Player){
            onPlayerShoot(event);
        }
    }
    @EventHandler
    public void onArrowHit(ProjectileHitEvent event){
        if(event.getEntity() instanceof Arrow){
            onArrowLand(event);
        }
    }

    public abstract void onPlayerShoot(EntityShootBowEvent event);
    public abstract void onArrowLand(ProjectileHitEvent event);
}
