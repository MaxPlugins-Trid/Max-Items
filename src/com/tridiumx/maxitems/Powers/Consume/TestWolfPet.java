package com.tridiumx.maxitems.Powers.Consume;

import com.tridiumx.maxitems.Utils.Chat;
import com.tridiumx.maxitems.Utils.CoolDownGenerator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Wolf;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by TridiumX on 7/17/2016.
 */
public class TestWolfPet extends MaxPowerConsume {
    @Override
    public void registerPower() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void onInteract(PlayerInteractEvent event) {
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(event.getPlayer().getInventory().getItemInMainHand().hasItemMeta() && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()){

                if(itemHasStat(event.getPlayer().getInventory().getItemInMainHand(), "PETWOLF")) {

                    if (cdManager.checkCD(plugin, event.getPlayer(), "PETWOLF")) {
                     event.getPlayer().sendMessage(Chat.makeMessage(Chat.getPrefix(plugin), "&ePet Wolf is on cool down!"));
                    } else {

                        cdManager.generateCD(plugin, event.getPlayer(), "PETWOLF", 1200);
                        Location spawn = event.getClickedBlock().getLocation();
                        spawn = spawn.add(00, 01, 00);
                        EntityType twolf = EntityType.WOLF;
                        Wolf wolf = (Wolf) spawn.getWorld().spawnEntity(spawn, twolf);


                        wolf.setGlowing(true);

                        if(event.getPlayer().getInventory().getItemInMainHand().getAmount() > 1) {
                            event.getPlayer().getInventory().setItemInMainHand(consume(event.getPlayer().getInventory().getItemInMainHand()));
                        }else{
                            event.getPlayer().getInventory().removeItem(event.getPlayer().getInventory().getItemInMainHand());
                        }
                        event.getPlayer().sendMessage(Chat.makeMessage("&7[&3PET WOLF&7]", "&eYou have spawned a pet wolf!"));
                    }
                }

            }
        }
    }
}
