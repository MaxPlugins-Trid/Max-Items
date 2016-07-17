package com.tridiumx.maxitems.Menus;

import com.tridiumx.maxitems.MaxItems;
import com.tridiumx.maxitems.MenuUtils.GUIPlayer;
import com.tridiumx.maxitems.MenuUtils.page.GUIInventory;
import com.tridiumx.maxitems.MenuUtils.page.GUIPage;
import com.tridiumx.maxitems.MenuUtils.page.PageInventory;
import com.tridiumx.maxitems.Utils.Chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by TridiumX on 7/16/2016.
 */
public class StatSelect extends GUIPage<MaxItems> implements Listener{
    private String itemWorkingWith;
    private String itemPack;
    private String stat;
    private boolean looking = false;

    public StatSelect(MaxItems plugin, GUIPlayer player, String itemWorkingWith, String itemPack){


        super(plugin, player, Chat.color("&bMain Menu"));

        this.itemPack = itemPack;
        this.itemWorkingWith = itemWorkingWith;

    }


    @Override
    protected GUIInventory loadInventory() {
        PageInventory inv = new PageInventory(player, title);
        File file = new File(plugin.getDataFolder(), itemPack + ".yml");
        Configuration pack = YamlConfiguration.loadConfiguration(file);
        ItemStack is = new ItemStack(Material.NAME_TAG);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.BLUE + "Display Name");
        im.setLore(Arrays.asList(Chat.color("&5Current: &7" + pack.getString(itemWorkingWith + ".display_name")), ChatColor.GREEN + "Click to change!"));
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

        Bukkit.getPluginManager().registerEvents(this, plugin);
        return inv;



    }

    @Override
    protected void onInventoryClick(InventoryClickEvent event) {
        switch (Chat.strip(event.getCurrentItem().getItemMeta().getDisplayName())){
            case"Display Name":
                looking = true;
                stat = Chat.strip(event.getCurrentItem().getItemMeta().getDisplayName());
                player.closeGUI(true);
                event.getWhoClicked().sendMessage(Chat.makeMessage(Chat.getPrefix(plugin), "&ePlease type the new display name"));
                break;
            case"Material":
                looking = true;
                stat = Chat.strip(event.getCurrentItem().getItemMeta().getDisplayName());
                player.closeGUI(true);
                event.getWhoClicked().sendMessage(Chat.makeMessage(Chat.getPrefix(plugin), "&e Type &bset &eto set the material to your hand"));
                break;
            case"Rarity":
                looking = true;
                stat = Chat.strip(event.getCurrentItem().getItemMeta().getDisplayName());
                player.closeGUI(true);
                event.getWhoClicked().sendMessage(Chat.makeMessage(Chat.getPrefix(plugin), "&ePlease type the new rarity &f(ex. &6&lLegendary&f)"));
                break;
            case"Type":
                looking = true;
                stat = Chat.strip(event.getCurrentItem().getItemMeta().getDisplayName());
                player.closeGUI(true);
                event.getWhoClicked().sendMessage(Chat.makeMessage(Chat.getPrefix(plugin), "&ePlease type the new type &f(ex. &5Sword&f)"));
                break;
            case"Description":
                looking = true;
                stat = Chat.strip(event.getCurrentItem().getItemMeta().getDisplayName());
                player.closeGUI(true);
                event.getWhoClicked().sendMessage(Chat.makeMessage(Chat.getPrefix(plugin), "&ePlease type the new description"));
                break;
            case"Damage":
                looking = true;
                stat = Chat.strip(event.getCurrentItem().getItemMeta().getDisplayName());
                player.closeGUI(true);
                event.getWhoClicked().sendMessage(Chat.makeMessage(Chat.getPrefix(plugin), "&ePlease type the new damage &f(&3MUST BE A NUMBER)"));
                break;
            case"Enchantments":
                player.openPage(new EnchantmentEdit(plugin, player, itemWorkingWith, itemPack),true);
                break;
            case"Powers":
                player.openPage(new PowerEdit(plugin, player, itemWorkingWith, itemPack), true);
                break;
            default:
                break;
        }
    }
@EventHandler
    public void onChat(AsyncPlayerChatEvent event){
    if(looking){
        Player p = player.player();
        if(event.getPlayer().equals(p)){
            File file = new File(plugin.getDataFolder(), itemPack + ".yml");
            Configuration pack = YamlConfiguration.loadConfiguration(file);

            switch (stat){
                case"Display Name":
                    pack.set(itemWorkingWith + ".display_name", event.getMessage());
                    p.sendMessage(Chat.makeMessage(Chat.getPrefix(plugin), "&e You have set the display name of &b" + itemWorkingWith + " &eto: &f" ) +  Chat.color(event.getMessage()));
                    break;
                case"Material":
                    if(event.getMessage().contains("set")){
                        pack.set(itemWorkingWith + ".material", p.getInventory().getItemInMainHand().getType().toString());
                        p.sendMessage(Chat.makeMessage(Chat.getPrefix(plugin), "&e You have set the material of &b" + itemWorkingWith + "&eto: &5" + p.getInventory().getItemInMainHand().getType().toString()));

                    }
                    break;
                case"Rarity":
                    pack.set(itemWorkingWith + ".rarity", event.getMessage());
                    p.sendMessage(Chat.makeMessage(Chat.getPrefix(plugin), "&e You have set the rarity of &b" + itemWorkingWith + "&eto: &5" + event.getMessage()));
                    break;
                case"Type":
                    pack.set(itemWorkingWith + ".type", event.getMessage());
                    p.sendMessage(Chat.makeMessage(Chat.getPrefix(plugin), "&e You have set the type of &b" + itemWorkingWith + "&eto: &5" + event.getMessage()));
                    break;
                case"Description":
                    pack.set(itemWorkingWith + ".description", event.getMessage());
                    p.sendMessage(Chat.makeMessage(Chat.getPrefix(plugin), "&e You have set the description of &b" + itemWorkingWith + "&eto: &5" + event.getMessage()));
                    break;
                case"Damage":
                    if(Chat.isInteger(event.getMessage())){
                        int i = Integer.parseInt(event.getMessage());
                        pack.set(itemWorkingWith + ".damage", i);
                        p.sendMessage(Chat.makeMessage(Chat.getPrefix(plugin), "&e You have set the damage of &b" + itemWorkingWith + "&eto: &5" + event.getMessage()));
                    }else{
                        p.sendMessage(Chat.makeMessage(Chat.getPrefix(plugin), "&e Was not a number, try again!"));
                    }

                    break;
                default:
                    break;

            }
            looking = false;
            stat = null;
            event.setCancelled(true);
            FileConfiguration configuration = (FileConfiguration) pack;
            try {
                configuration.save(file);
            }catch (IOException ex){
                plugin.getLogger().severe("Could not save item pack");
            }
            player.openPage(this, true);
        }
    }
}

}

