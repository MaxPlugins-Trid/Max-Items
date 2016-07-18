package com.tridiumx.maxitems.Powers;

import com.tridiumx.maxitems.Powers.Bow.Blink;
import com.tridiumx.maxitems.Powers.Sword.LifeSteal;
import com.tridiumx.maxitems.Utils.CoolDownGenerator;
import org.bukkit.plugin.Plugin;

/**
 * Created by TridiumX on 7/17/2016.
 */
public class PowerManager {
    //Variable for powers
    MaxPower lifeSteal;
    MaxPower blink;



    public void loadPowers(Plugin plugin){
        //Init powers
        lifeSteal = new LifeSteal();
        lifeSteal.onEnable(plugin, new CoolDownGenerator());
        blink = new Blink();
        blink.onEnable(plugin, new CoolDownGenerator());


    }
}
