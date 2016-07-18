package com.tridiumx.maxitems.Powers.Bow;

import com.tridiumx.maxitems.Utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.FixedMetadataValue;

/**
 * Created by TridiumX on 7/18/2016.
 */
public class Transpose extends MaxPowerBow{


    @Override
    public void registerPower() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void onArrowLand(ProjectileHitEvent event) {
        return;
    }

    @Override
    public void onPlayerShoot(EntityShootBowEvent event) {

        if(event.getProjectile() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getProjectile();

            if (itemHasStat(event.getBow(), "TRANSPOSE")) {
                arrow.setGlowing(true);
                arrow.setMetadata("TRANSPOSE", new FixedMetadataValue(plugin, event.getEntity()));
            }
        }


    }

    @Override
    public void onDamageByArrow(EntityDamageByEntityEvent event) {
        Arrow arrow = (Arrow) event.getDamager();
        if(arrow.hasMetadata("TRANSPOSE")){

            Player p = (Player) arrow.getMetadata("TRANSPOSE").get(0).value();

            if(!cdManager.checkCD(plugin, p, "TRANSPOSE")) {
                Location loc1 = event.getEntity().getLocation();
                Location loc2 = p.getLocation();
                event.getEntity().teleport(loc2);
                p.teleport(loc1);
                cdManager.generateCD(plugin, p, "TRANSPOSE", 400);
            }else{
                p.sendMessage(Chat.makeMessage(Chat.color("&7[&3TRANSPOSE&7] "), "&ETranspose is currently on cooldown!"));
            }
        }

    }


}
