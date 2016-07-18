package com.tridiumx.maxitems.Powers.Sword;

import com.tridiumx.maxitems.Powers.MaxPower;
import com.tridiumx.maxitems.Utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by TridiumX on 7/17/2016.
 */
public class LifeSteal extends MaxPowerSword {
    @Override
    public void registerPower() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void onDamage(EntityDamageByEntityEvent event) {
        Player entity = (Player) event.getDamager();
        if(itemInHandHasStat(entity, "LIFESTEAL")){
            double chance = Math.random();

            if(chance < 0.9){
                double health = entity.getHealth();
                double damage = event.getDamage();
                double newhealth = health + damage;
                if(newhealth < entity.getMaxHealth()){
                    entity.setHealth(newhealth);
                    entity.sendMessage(Chat.makeMessage(Chat.color("&7[&3LIFESTEAL&7]:"), "&eYou have been healed some!"));
                }else{
                    entity.setHealth(entity.getMaxHealth());
                    entity.sendMessage(Chat.makeMessage(Chat.color("&7[&3LIFESTEAL&7]:"), "&eYou have been healed some!"));
                }
            }
        }
    }
}
