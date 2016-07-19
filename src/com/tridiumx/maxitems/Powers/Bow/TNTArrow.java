package com.tridiumx.maxitems.Powers.Bow;

import com.tridiumx.maxitems.Utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.FixedMetadataValue;

/**
 * Created by TridiumX on 7/18/2016.
 */
public class TNTArrow extends MaxPowerBow {


    @Override
    public void registerPower() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void onArrowLand(ProjectileHitEvent event) {
        Arrow arrow = (Arrow) event.getEntity();
        if(arrow.hasMetadata("TNT ARROW")){
            Location loc = arrow.getLocation();
            TNTPrimed tnt = loc.getWorld().spawn(loc, TNTPrimed.class);
            tnt.setFuseTicks(20);
            Player player = (Player) arrow.getMetadata("TNT ARROW").get(0).value();
            cdManager.generateCD(plugin, player, "TNT ARROW", 400);
            arrow.removeMetadata("TNT ARROW", plugin);
        }


    }

    @Override
    public void onPlayerShoot(EntityShootBowEvent event) {

        Player player = (Player) event.getEntity();

        if(itemInHandHasStat(player, "TNT ARROW")){

            if(!cdManager.checkCD(plugin, player, "TNT ARROW")){

                Arrow arrow = (Arrow) event.getProjectile();
                arrow.setMetadata("TNT ARROW", new FixedMetadataValue(plugin, player));

            }else{
                player.sendMessage(Chat.makeMessage(Chat.color("&7[&3TNT ARROW&7]"), "&e is on cooldown!"));
            }

        }

    }
}
