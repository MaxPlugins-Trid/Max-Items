package com.tridiumx.maxitems.Menus;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by TridiumX on 7/11/2016.
 */
public class StatManageMenu extends Menu {


    @Override
    public void registerItems() {

        //TODO send message instructions
        if(p != null){
            pl.getLogger().info("Plyer not null");//TAKEOUT
            if(p.hasMetadata("ITEM_WORKING_WITH")){
                pl.getLogger().info("Has meta");//TAKEOUT
                String itemWorkingWith = p.getMetadata("ITEM_WORKING_WITH").get(0).asString();


                File file = new File(pl.getDataFolder(), itemWorkingWith + ".yml");
                Configuration pack = YamlConfiguration.loadConfiguration(file);
                if(pack != null){
                    pl.getLogger().info("Pack is not null");
                }
                ItemStack is = new ItemStack(Material.NAME_TAG);
                ItemMeta im = is.getItemMeta();
                im.setDisplayName(ChatColor.BLUE + "Display Name");
                //im.setLore(Arrays.asList(ChatColor.GOLD + "Current: " + ChatColor.translateAlternateColorCodes('&', pack.getString(itemWorkingWith + ".display_name")), ChatColor.GREEN + "Click to change"));
                is.setItemMeta(im);
                inv.addItem(is);
                is = new ItemStack(Material.BRICK);
                im = is.getItemMeta();
                im.setDisplayName(ChatColor.BLUE + "Material");
                //im.setLore(Arrays.asList(ChatColor.GOLD + "Current: " + ChatColor.translateAlternateColorCodes('&', pack.getString(itemWorkingWith + ".material")), ChatColor.GREEN + "Click to change"));
                is.setItemMeta(im);
                inv.addItem(is);
                is = new ItemStack(Material.DIAMOND);
                im = is.getItemMeta();
                im.setDisplayName(ChatColor.BLUE + "Rarity");
                //im.setLore(Arrays.asList(ChatColor.GOLD + "Current: " + ChatColor.translateAlternateColorCodes('&', pack.getString(itemWorkingWith + ".rarity")), ChatColor.GREEN + "Click to change"));
                is.setItemMeta(im);
                inv.addItem(is);
                is = new ItemStack(Material.PAPER);
                im = is.getItemMeta();
                im.setDisplayName(ChatColor.BLUE + "Type");
                //im.setLore(Arrays.asList(ChatColor.GOLD + "Current: " + ChatColor.translateAlternateColorCodes('&', pack.getString(itemWorkingWith + ".type")), ChatColor.GREEN + "Click to change"));
                is.setItemMeta(im);
                inv.addItem(is);
                is = new ItemStack(Material.BOOK_AND_QUILL);
                im = is.getItemMeta();
                im.setDisplayName(ChatColor.BLUE + "Description");
                //im.setLore(Arrays.asList(ChatColor.GOLD + "Current: " + ChatColor.translateAlternateColorCodes('&', pack.getString(itemWorkingWith + ".description")), ChatColor.GREEN + "Click to change"));
                is.setItemMeta(im);
                inv.addItem(is);
                is = new ItemStack(Material.DIAMOND_SWORD);
                im = is.getItemMeta();
                im.setDisplayName(ChatColor.BLUE + "Damage");
                //im.setLore(Arrays.asList(ChatColor.GOLD + "Current: " + ChatColor.translateAlternateColorCodes('&', pack.getString(itemWorkingWith + ".damage")), ChatColor.GREEN + "Click to change"));
                is.setItemMeta(im);
                inv.addItem(is);
                is = new ItemStack(Material.BOOK);
                im = is.getItemMeta();
                im.setDisplayName(ChatColor.BLUE + "Enchantments");
                im.setLore(Arrays.asList(ChatColor.GREEN + "Click to manage enchantments"));
                is.setItemMeta(im);
                inv.addItem(is);
                is = new ItemStack(Material.BEACON);
                im = is.getItemMeta();
                im.setDisplayName(ChatColor.BLUE + "Powers");
                im.setLore(Arrays.asList(ChatColor.GREEN + "Click to manage powers"));
                is.setItemMeta(im);
                inv.addItem(is);



            }
        }



    }



    @Override
    public void eventHandler(Event e) {
        //chat events
        if(e instanceof AsyncPlayerChatEvent){
            AsyncPlayerChatEvent event = (AsyncPlayerChatEvent) e;
            if (event.getPlayer().equals(p) && p.hasMetadata("ACTION")){
                String itemWorkingWith = p.getMetadata("ITEM_WORKING_WITH").get(0).asString();
                String itemPack = p.getMetadata("ITEMPACK").get(0).asString();
                File file = new File(pl.getDataFolder(), ChatColor.stripColor(itemPack + ".yml"));
                Configuration pack = YamlConfiguration.loadConfiguration(file);

                switch (p.getMetadata("ACTION").get(0).asString()) {

                    case "DISPLAY_NAME":
                        p.removeMetadata("ACTION", pl);
                        pack.set(itemWorkingWith + ".display_name", event.getMessage());

                        break;
                    case "MATERIAL":
                        p.removeMetadata("ACTION", pl);
                        if(event.getMessage().contains("set")){
                            pack.set(itemWorkingWith + ".material", p.getInventory().getItemInMainHand().getType().name());
                        }
                        break;
                    case "RARITY":
                        p.removeMetadata("ACTION", pl);
                        pack.set(itemWorkingWith+ ".rarity", event.getMessage());
                        break;
                    case "TYPE":
                        p.removeMetadata("ACTION", pl);
                        pack.set(itemWorkingWith + ".type", event.getMessage());
                        break;
                    case "DESCRIPTION":
                        p.removeMetadata("ACTION", pl);
                        pack.set(itemWorkingWith + ".description", event.getMessage());
                        break;
                    case "DAMAGE":
                        p.removeMetadata("ACTION", pl);
                        pack.set(itemWorkingWith + ".damage", event.getMessage());
                        break;

                    default:
                        break;
                }
                event.setCancelled(true);
                FileConfiguration configuration = (FileConfiguration) pack;
                try {
                    configuration.save(file);
                }catch (IOException ex){
                    pl.getLogger().severe("Could not save item pack");
                }


                Open(p);
            }
        }
        //Gui Click Events
        if(e instanceof InventoryClickEvent){
            InventoryClickEvent event = (InventoryClickEvent) e;
            if(event.getInventory().getTitle().equals(inv.getTitle()) && event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR){
                String name = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
                pl.getLogger().info(name);//Takeout
                switch (name){
                    case "Display Name":
                        p.closeInventory();
                        setPlayerMeta(p, "ACTION", "DISPLAY_NAME");
                        pl.getLogger().info(name); //takeout
                        break;
                    case "Material":
                        p.closeInventory();
                        setPlayerMeta(p, "ACTION", "MATERIAL");
                        pl.getLogger().info(name); //takeout
                        break;
                    case "Rarity":
                        p.closeInventory();
                        setPlayerMeta(p, "ACTION", "RARITY");
                        pl.getLogger().info(name); //takeout
                        break;
                    case "Type":
                        p.closeInventory();
                        setPlayerMeta(p, "ACTION", "TYPE");
                        pl.getLogger().info(name); //takeout
                        break;
                    case "Description":
                        p.closeInventory();
                        setPlayerMeta(p, "ACTION", "DESCRIPTION");
                        pl.getLogger().info(name); //takeout
                        break;
                    case "Damage":
                        setPlayerMeta(p, "ACTION", "DAMAGE");
                        p.closeInventory();
                        pl.getLogger().info(name); //takeout
                        break;
                    case "Enchantments":
                        Menu nextMenu = new EnchantmentManageMenu();
                        nextMenu.p = (Player) event.getWhoClicked();
                        nextMenu.init(36, "Enchantments", pl, manager);
                        nextMenu.Open(p);
                        break;
                    case "Powers":
                        //TODO open power menu
                        break;
                    default:
                        break;

                }

            }
        }
    }
}
