package com.tridiumx.maxitems.Menus;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by TridiumX on 7/11/2016.
 */
public class MainMenu extends Menu {


    @Override
    public void registerItems() {
        for(String key : pl.getConfig().getConfigurationSection("itempacks").getKeys(false)){

            pl.getLogger().info(key + "=" + pl.getConfig().getString("itempacks." + key)); //TAKEOUT
            if(pl.getConfig().getString("itempacks." + key) == "true"){

                pl.getLogger().info(key + "will be loaded"); //TAKEOUT
                File cfile = new File(pl.getDataFolder(), key + ".yml");

                if (cfile != null) {
                    pl.getLogger().info("Cfile is not null"); //TAKEOUT
                    Configuration pack = YamlConfiguration.loadConfiguration(cfile);
                    for(String item : pack.getKeys(false)){
                        pl.getLogger().info(item); //TAKEOUT
                        ItemStack is = new ItemStack(Material.getMaterial(pack.getString(item + ".material")));
                        ItemMeta im = is.getItemMeta();
                        im.setDisplayName(item);
                        List<String> lores = new ArrayList<>();
                        lores.add(lores.size(), ChatColor.GREEN + "Click to edit item!");
                        lores.add(lores.size(), ChatColor.GOLD + "Item Pack: " + ChatColor.GRAY + key);
                        im.setLore(lores);
                        is.setItemMeta(im);

                        inv.addItem(is);

                    }
                }
            }
        }

    }



    @Override
    public void eventHandler(Event e) {
        if(e instanceof InventoryClickEvent){
            InventoryClickEvent ev = (InventoryClickEvent) e;
            if(ev.getInventory().getTitle().equalsIgnoreCase(inv.getTitle())){
                Player p = (Player) ev.getWhoClicked();
                if(ev.getCurrentItem() != null && ev.getCurrentItem().getType() != Material.AIR){
                    pl.getLogger().info("Clicked Not air or null");//TAKEOUT
                    setPlayerMeta(p, "ITEM_WORKING_WITH", ChatColor.stripColor(ev.getCurrentItem().getItemMeta().getDisplayName()));
                    for(String lores : (ev.getCurrentItem().getItemMeta().getLore())){
                        if(lores.contains("Item Pack")){
                            String s = ChatColor.stripColor(lores);
                            s = s.replace("Item Pack: ", "");
                            setPlayerMeta(p, "ITEMPACK", s);

                        }
                    }

                    Menu nextMenu = new StatManageMenu();
                    nextMenu.p = p;
                    nextMenu.init(27, "Edit Stats", pl, manager);

                    ev.setCancelled(true);
                    nextMenu.Open(p);

                }
            }
        }
    }


}
