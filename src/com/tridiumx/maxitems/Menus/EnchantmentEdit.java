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
import org.bukkit.craftbukkit.libs.org.ibex.nestedvm.util.Seekable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by TridiumX on 7/16/2016.
 */
public class EnchantmentEdit extends GUIPage<MaxItems> implements Listener{
    private String itemWorkingWith;
    private String itemPack;
    private boolean lookingench;
    private String enchant;

    public EnchantmentEdit(MaxItems plugin, GUIPlayer player, String itemWorkingWith, String itemPack){

        super(plugin, player, Chat.color("&bMain Menu"));
        this.itemPack = itemPack;
        this.itemWorkingWith = itemWorkingWith;
    }

    @Override
    protected GUIInventory loadInventory() {
        PageInventory inv = new PageInventory(player, title);
        for(String ench : Arrays.asList("DAMAGE_ALL","DAMAGE_ARTHROPODS","DAMAGE_UNDEAD","DIG_SPEED","FIRE_ASPECT","KNOCKBACK","LOOT_BONUS_BLOCKS","LOOT_BONUS_MOBS","OXYGEN","PROTECTION_ENVIRONMENTAL","PROTECTION_EXPLOSIONS","PROTECTION_FALL","PROTECTION_FIRE","PROTECTION_PROJECTILE","SILK_TOUCH","WATER_WORKER","ARROW_FIRE","ARROW_DAMAGE","ARROW_KNOCKBACK","ARROW_INFINITE")){
            ItemStack is = new ItemStack(Material.BOOK);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(ench);
            im.setLore(Arrays.asList(ChatColor.GREEN + "Click to edit."));
            im.addEnchant(Enchantment.getByName(ench), 1, true);
            is.setItemMeta(im);
            inv.addItem(is);

        }
        Bukkit.getPluginManager().registerEvents(this, plugin);
        return inv;
    }

    @Override
    protected void onInventoryClick(InventoryClickEvent event) {
        lookingench = true;
        enchant = Chat.strip(event.getCurrentItem().getItemMeta().getDisplayName());
        player.closeGUI(true);
        player.player().sendMessage(Chat.makeMessage(Chat.getPrefix(plugin), "&ePlease type the level you wish to set &b" + enchant + "&e to! (&3MUST BE A NUMBER)"));
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        if(lookingench){
            if(event.getPlayer().equals(player.player())){
                if(Chat.isInteger(event.getMessage())){

                    int i = Integer.parseInt(event.getMessage());
                    File file = new File(plugin.getDataFolder(), itemPack + ".yml");
                    Configuration pack = YamlConfiguration.loadConfiguration(file);
                    pack.set(itemWorkingWith + ".enchantments." + enchant, i);
                    player.player().sendMessage(Chat.makeMessage(Chat.getPrefix(plugin), "&eYou have set &f" + enchant + "&e to &5" + event.getMessage()));
                    FileConfiguration configuration = (FileConfiguration) pack;
                    try {
                        configuration.save(file);
                    }catch (IOException ex){
                        plugin.getLogger().severe("Could not save item pack");
                    }


                }else{
                    player.player().sendMessage(Chat.makeMessage(Chat.getPrefix(plugin), "&e Was not a number, try again!"));
                }
                lookingench = false;
                enchant = null;
                event.setCancelled(true);

                player.openPage(this, true);
            }
        }
    }


}
