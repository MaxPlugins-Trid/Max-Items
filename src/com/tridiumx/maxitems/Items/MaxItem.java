package com.tridiumx.maxitems.Items;

import com.tridiumx.maxitems.Powers.MaxPower;
import com.tridiumx.maxitems.Utils.Chat;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TridiumX on 7/16/2016.
 */
public class MaxItem {
    public String name;
    private ItemStack itemStack;
    private List<String> enchants;
    private List<MaxPower> powers;
    private Plugin plugin;
    public int id;

    public void initItem(Plugin plugin, Configuration itemPack, String item, int ID){
        this.name = item;
        this.plugin = plugin;
        itemStack = new ItemStack(Material.getMaterial(itemPack.getString(item + ".material")));
        ItemMeta im = itemStack.getItemMeta();
        im.setDisplayName(Chat.color(itemPack.getString(item + ".display_name")));
        List<String> lores = new ArrayList<>();
        lores.add(lores.size(), Chat.color(itemPack.getString(item + ".type") + "         " + itemPack.getString(item + ".rarity")));
        lores.add(lores.size(), Chat.color(itemPack.getString(item + ".description")));
        im.setLore(lores);
        itemStack.setItemMeta(im);
        for(String key : itemPack.getConfigurationSection(item + ".enchantments").getKeys(false)){
            itemStack.addUnsafeEnchantment(Enchantment.getByName(key), itemPack.getInt(item + ".enchantments." + key));
            enchants.add(enchants.size(), key);
        }
        for(String key : itemPack.getConfigurationSection(item + ".powers").getKeys(false)){
            switch (key){
                case "":
                    break;
                default:
                    plugin.getLogger().info("Could not load power: " + key);
                    break;

            }

        }


    }

    public ItemStack giveItem(){
        return itemStack;
    }




}
