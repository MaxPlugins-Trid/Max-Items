package com.tridiumx.maxitems.Powers.Bow;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.FixedMetadataValue;

/**
 * Created by TridiumX on 7/17/2016.
 */
public class Blink extends MaxPowerBow {

    @Override
    public void registerPower() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void onArrowLand(ProjectileHitEvent event) {
        Arrow arrow = (Arrow) event.getEntity();
        if(arrow.hasMetadata("BLINK")){
            Player p = (Player) arrow.getMetadata("BLINK").get(0).value();
            Location location = arrow.getLocation();
            p.teleport(location);

        }


    }

    @Override
    public void onPlayerShoot(EntityShootBowEvent event) {
        if(event.getProjectile() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getProjectile();
            if (itemHasStat(event.getBow(), "BLINK")) {
                arrow.setGlowing(true);
                arrow.setMetadata("BLINK", new FixedMetadataValue(plugin,event.getEntity()));
            }
        }

    }
}
