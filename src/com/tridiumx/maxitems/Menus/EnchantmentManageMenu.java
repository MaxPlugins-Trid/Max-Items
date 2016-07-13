package com.tridiumx.maxitems.Menus;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.Arrays;

/**
 * Created by TridiumX on 7/12/2016.
 */
public class EnchantmentManageMenu extends Menu {
    @Override
    public void registerItems() {
        for(String ench : Arrays.asList("DAMAGE_ALL","DAMAGE_ARTHROPODS","DAMAGE_UNDEAD","DIG_SPEED","FIRE_ASPECT","KNOCKBACK","LOOT_BONUS_BLOCKS","LOOT_BONUS_MOBS","OXYGEN","PROTECTION_ENVIRONMENTAL","PROTECTION_EXPLOSIONS","PROTECTION_FALL","PROTECTION_FIRE","PROTECTION_PROJECTILE","SILK_TOUCH","WATER_WORKER","ARROW_FIRE","ARROW_DAMAGE","ARROW_KNOCKBACK","ARROW_INFINITE")){
            ItemStack is = new ItemStack(Material.BOOK);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(ench);
            im.setLore(Arrays.asList(ChatColor.GREEN + "Click to edit."));
            im.addEnchant(Enchantment.getByName(ench), 1, true);
            is.setItemMeta(im);
            inv.addItem(is);
        }
    }

    @Override
    public void eventHandler(Event e) {
        //Chat Events
        if(e instanceof AsyncPlayerChatEvent){
            AsyncPlayerChatEvent event = (AsyncPlayerChatEvent) e;
            if(event.getPlayer().equals(p)){
                if(p.hasMetadata("ACTION")){
                    if(getPlayerMeta(p, "ACTION").equals("ENCHANT")){
                        ItemStack itemWorkingWith = (ItemStack) p.getMetadata("ITEM_WORKING_WITH");
                        File file = new File(pl.getDataFolder(), ChatColor.stripColor(itemWorkingWith.getItemMeta().getDisplayName()) + ".yml");
                        Configuration pack = YamlConfiguration.loadConfiguration(file);
                        pack.set(ChatColor.stripColor(itemWorkingWith.getItemMeta().getDisplayName()) + ".enchantments." + getPlayerMeta(p, "ENCHANT_WORKING_WITH"),event.getMessage());
                        p.removeMetadata("ENCHANT_WORING_WITH", pl);
                        p.removeMetadata("ACTION", pl);
                        event.setCancelled(true);
                        Open(p);
                    }

                }
            }
        }
        if(e instanceof InventoryClickEvent){
            InventoryClickEvent event = (InventoryClickEvent) e;
            if(event.getInventory().getTitle().equals(inv.getTitle())){
                Player player = (Player) event.getWhoClicked();
                setPlayerMeta(player, "ACTION", "ENCHANT");
                event.setCancelled(true);
                player.closeInventory();
                setPlayerMeta(p, "ENCHANT_WORKING_WITH", ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
                p.closeInventory();

            }
        }
    }


}
