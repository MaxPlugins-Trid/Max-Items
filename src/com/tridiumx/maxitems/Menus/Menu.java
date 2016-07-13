package com.tridiumx.maxitems.Menus;

import com.sun.xml.internal.ws.api.addressing.WSEndpointReference;
import com.tridiumx.maxitems.MaxItems;
import com.tridiumx.maxitems.MenuManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 * Created by TridiumX on 7/11/2016.
 */
public abstract class Menu implements Listener{
    public Inventory inv;
    public String name;
    public int size;
    public Plugin pl;
    public MenuManager manager;
    public Player p;

    public void init(int size, String name, Plugin pl, MenuManager manager){
        this.pl = pl;
        this.name = name;
        this.size = size;
        this.manager = manager;
       inv = Bukkit.createInventory(null, size, name);
        registerItems();
        registerEvents(manager.main);

    }

    public abstract void registerItems();

    public void Open(Player p){
        p.openInventory(inv);
    }


    public Object getPlayerMeta(Player player, String Key){
        List<MetadataValue> vals = player.getMetadata(Key);
        for(MetadataValue value : vals){
            if(value.getOwningPlugin().getDescription().getName().equals(pl.getDescription().getName())){
                return value;
            }
        }
        return null;
    }
    @EventHandler
    public void onClick(InventoryClickEvent e){
        eventHandler(e);
    }
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e){
        eventHandler(e);
    }

    public abstract void eventHandler(Event e);
    public void setPlayerMeta(Player player, String Key, Object Value){
        player.setMetadata(Key, new FixedMetadataValue(pl, Value));
    }
    public void registerEvents(MaxItems maxItems){
        Bukkit.getPluginManager().registerEvents(this, maxItems);
    }

}
