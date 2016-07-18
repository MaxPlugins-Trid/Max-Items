package com.tridiumx.maxitems.Menus;

import com.tridiumx.maxitems.MaxItems;
import com.tridiumx.maxitems.MenuUtils.GUIPlayer;
import com.tridiumx.maxitems.MenuUtils.page.GUIInventory;
import com.tridiumx.maxitems.MenuUtils.page.GUIPage;
import com.tridiumx.maxitems.MenuUtils.page.PageInventory;
import com.tridiumx.maxitems.Utils.Chat;
import org.bukkit.Material;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by TridiumX on 7/17/2016.
 */
public class PowerEdit extends GUIPage<MaxItems> {
    String itemPack;
    String itemWorkingWith;
    public PowerEdit(MaxItems plugin, GUIPlayer player, String itemWorkingWith, String itemPack){


        super(plugin, player, Chat.color("&7Powers"));
        this.itemPack = itemPack;
        this.itemWorkingWith = itemWorkingWith;
    }

    @Override
    protected GUIInventory loadInventory() {
        PageInventory inv = new PageInventory(player, Chat.color("&3Powers"));
        for(String power : Arrays.asList("LIFESTEAL", "BLINK", "TRANSPOSE")){
            ItemStack is = new ItemStack(Material.COMMAND_MINECART);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(Chat.color("&a" + power));
            is.setItemMeta(im);
            inv.addItem(is);

        }
        return inv;
    }

    @Override
    protected void onInventoryClick(InventoryClickEvent event) {
        File file = new File(plugin.getDataFolder(), itemPack + ".yml");
        Configuration pack = YamlConfiguration.loadConfiguration(file);
        if(pack.getInt(itemWorkingWith + ".powers." + Chat.strip(event.getCurrentItem().getItemMeta().getDisplayName())) != 0){
            pack.set(itemWorkingWith + ".powers." + Chat.strip(event.getCurrentItem().getItemMeta().getDisplayName()), 0);
        }else{
            pack.set(itemWorkingWith + ".powers." + Chat.strip(event.getCurrentItem().getItemMeta().getDisplayName()), 1);
        }
        FileConfiguration configuration = (FileConfiguration) pack;
        try {
            configuration.save(file);
        }catch (IOException ex){
            plugin.getLogger().severe("Could not save item pack");
        }

    }



}
