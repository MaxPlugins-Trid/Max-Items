package com.tridiumx.maxitems.Powers.Consume;

import com.tridiumx.maxitems.Powers.MaxPower;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by TridiumX on 7/17/2016.
 */
public abstract class MaxPowerConsume extends MaxPower implements Listener{


    public ItemStack consume(ItemStack item){
        int i = item.getAmount();
        item.setAmount(i - 1);
        return item;

    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        onInteract(event);
    }



    public abstract void onInteract(PlayerInteractEvent event);


}
