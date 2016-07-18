package com.tridiumx.maxitems.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

/**
 * Created by TridiumX on 7/18/2016.
 */
public class StatusManager implements Listener{
    private Plugin plugin;

    public StatusManager(Plugin plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    public boolean playerHasStatus(Player player, Status status){
        String name = status.toString();
        if(player.hasMetadata(name)){
            return true;
        }

        return false;

    }


    public void setPlayerStatus(Player player, Status status){
        String name = status.toString();
        if(!player.hasMetadata(name)){
            player.setMetadata(name, new FixedMetadataValue(plugin, name));
            final Player p = player;
            final String s = status.toString();
            final Plugin pl = plugin;
            final long cd = status.getDurration();
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                    p.removeMetadata(s, pl);
                }
            }, cd);
        }
        }



    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        if(playerHasStatus(event.getPlayer(), Status.FROZEN)){
            event.getPlayer().sendMessage(Chat.makeMessage(Status.FROZEN.getPrefix(), "You are frozen!"));
            event.setCancelled(true);
        }
    }



}
