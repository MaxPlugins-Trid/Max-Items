package com.tridiumx.maxitems.Items;

import com.tridiumx.maxitems.Utils.Chat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TridiumX on 7/16/2016.
 */
public class ItemManager {
    public ItemStack giveItem(Plugin pl, Configuration pack, String item){
        ItemStack is = new ItemStack(Material.getMaterial(pack.getString(item + ".material")));
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(Chat.color(pack.getString(item + ".display_name")));
        List<String> lores = new ArrayList<>();
        lores.add(lores.size(), Chat.color(pack.getString(item + ".type") + "&0 &0 &0 &0 &0 &0 &0 &0 &0 &0 &0 &0 &0 &0 &0 &0 &r" + pack.getString(item + ".rarity")));
        lores.add(lores.size(), Chat.color(pack.getString(item + ".description")));
        lores.add(lores.size(), Chat.color("&6Powers:"));
        for(String key : pack.getConfigurationSection(item + ".powers").getKeys(false)){
            if(pack.getInt(item + ".powers." + key) > 0) {
                lores.add(lores.size(), ChatColor.AQUA + key);
            }
        }
        im.setLore(lores);
        is.setItemMeta(im);
        for(String key : pack.getConfigurationSection(item + ".enchantments").getKeys(false)){
            is.addUnsafeEnchantment(Enchantment.getByName(key), pack.getInt(item + ".enchantments." + key));

        }

        return is;
    }
}
