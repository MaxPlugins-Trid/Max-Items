package com.tridiumx.maxitems.Menus;

import com.tridiumx.maxitems.MaxItems;
import com.tridiumx.maxitems.MenuUtils.GUIPlayer;
import com.tridiumx.maxitems.MenuUtils.page.GUIInventory;
import com.tridiumx.maxitems.MenuUtils.page.GUIPage;
import com.tridiumx.maxitems.MenuUtils.page.PageInventory;
import com.tridiumx.maxitems.Utils.Chat;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Created by TridiumX on 7/16/2016.
 */
public class MainMenu extends GUIPage<MaxItems> {

    public MainMenu(MaxItems plugin, GUIPlayer player){
        super(plugin, player, Chat.color("&bMain Menu"));
    }


    @Override
    protected GUIInventory loadInventory() {
        PageInventory inventory = new PageInventory(player, title);
        for(String pack : plugin.getConfig().getConfigurationSection("itempacks").getKeys(false)){
            if(plugin.getConfig().getBoolean("itempacks." + pack)){

                File file = new File(plugin.getDataFolder(), pack + ".yml");
                if(!file.exists()){
                    plugin.getLogger().info("Item pack " + pack + "does not exist!");
                }else{
                    Configuration config = YamlConfiguration.loadConfiguration(file);
                    for(String key : config.getKeys(false)){
                        ItemStack is = new ItemStack(Material.getMaterial(config.getString(key + ".material")));
                        ItemMeta im = is.getItemMeta();
                        im.setDisplayName(Chat.color("&5&l" + key));
                        im.setLore(Arrays.asList(Chat.color("&b" + pack), Chat.color("&aClick to edit!")));
                        is.setItemMeta(im);
                        inventory.addItem(is);
                    }
                }
            }

        }

        return inventory;



    }

    @Override
    protected void onInventoryClick(InventoryClickEvent event) {
        List<String> lores = event.getCurrentItem().getItemMeta().getLore();

        player.openPage(new StatSelect(plugin, player, Chat.strip(event.getCurrentItem().getItemMeta().getDisplayName()),Chat.strip(lores.get(0))),true);
    }
}
