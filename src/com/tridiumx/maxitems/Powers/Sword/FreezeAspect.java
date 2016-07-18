package com.tridiumx.maxitems.Powers.Sword;

import com.tridiumx.maxitems.MaxItems;
import com.tridiumx.maxitems.Utils.Chat;
import com.tridiumx.maxitems.Utils.Status;
import com.tridiumx.maxitems.Utils.StatusManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by TridiumX on 7/18/2016.
 */
public class FreezeAspect extends MaxPowerSword{

    private MaxItems main;

    public FreezeAspect(MaxItems main){
        this.main = main;
    }

    @Override
    public void registerPower() {
        Bukkit.getPluginManager().registerEvents(this, plugin);

    }

    @Override
    public void onDamage(EntityDamageByEntityEvent event) {
        Player player = (Player) event.getDamager();
        if(itemInHandHasStat(player, "FREEZE ASPECT")) {

            if (!cdManager.checkCD(plugin, player, "FREEZE ASPECT")) {

                double chance = Math.random();

                if(chance > 0.7) {

                    if (event.getEntity() instanceof Player) {
                        main.statusManager.setPlayerStatus((Player) event.getEntity(), Status.FROZEN);
                    }
                }


            }
        }


    }
}
