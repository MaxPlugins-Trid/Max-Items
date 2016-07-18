package com.tridiumx.maxitems.Powers;

import com.tridiumx.maxitems.MaxItems;
import com.tridiumx.maxitems.Powers.Bow.Blink;
import com.tridiumx.maxitems.Powers.Bow.Transpose;
import com.tridiumx.maxitems.Powers.Sword.FreezeAspect;
import com.tridiumx.maxitems.Powers.Sword.LifeSteal;
import com.tridiumx.maxitems.Utils.CoolDownGenerator;
import org.bukkit.plugin.Plugin;

/**
 * Created by TridiumX on 7/17/2016.
 */
public class PowerManager {
    public MaxItems main;
    //Variable for powers
    MaxPower lifeSteal;
    MaxPower blink;
    MaxPower transpose;
    MaxPower freezeAspect;

    public PowerManager(MaxItems main){
        this.main = main;
    }

    public void loadPowers(Plugin plugin){
        //Init powers
        lifeSteal = new LifeSteal();
        lifeSteal.onEnable(plugin, new CoolDownGenerator());
        blink = new Blink();
        blink.onEnable(plugin, new CoolDownGenerator());
        transpose = new Transpose();
        transpose.onEnable(plugin, new CoolDownGenerator());
        freezeAspect = new FreezeAspect(main);
        freezeAspect.onEnable(plugin, new CoolDownGenerator());


    }
}
