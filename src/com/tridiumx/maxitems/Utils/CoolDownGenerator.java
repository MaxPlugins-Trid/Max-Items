package com.tridiumx.maxitems.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

/**
 * Created by TridiumX on 7/17/2016.
 */
public class CoolDownGenerator {
    public void generateCD(Plugin plugin, Player player, String stat, long time){
        final Player p = player;
        final String s = stat;
        final long cd = time;
        final Plugin pl = plugin;
        player.setMetadata("CD" + stat, new FixedMetadataValue(plugin, "CD" + stat));
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                p.removeMetadata("CD" + s, pl);
            }
        }, cd);
    }

    public boolean checkCD(Plugin plugin, Player player, String stat){
        if(player.hasMetadata("CD" + stat)){
            return true;
        }

        return false;
    }


}
