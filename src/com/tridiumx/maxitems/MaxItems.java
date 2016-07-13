package com.tridiumx.maxitems;

import com.tridiumx.maxitems.Menus.MainMenu;
import com.tridiumx.maxitems.Menus.Menu;
import com.tridiumx.maxitems.Menus.StatManageMenu;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.logging.Level;

/**
 * Created by TridiumX on 7/11/2016.
 */
public class MaxItems extends JavaPlugin{

    MenuManager mm;
    private FileConfiguration exItemPack = null;
    private File exItemPackFile = null;


    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onEnable() {
        if(!getDataFolder().exists()){
            getConfig().options().copyDefaults(true);
            saveConfig();
            getLogger().info("Data folder does not exist, creating files now!");
            saveResource("exampleitems.yml", false);

        }

        mm = new MenuManager();
        mm.initMenus(this, this);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args[0].equalsIgnoreCase("menu")){
            mm.getMenuByName("Main Menu").Open((Player) sender);
        }
        if(args[0].equalsIgnoreCase("test")){
            Menu nextMenu = new StatManageMenu();
            nextMenu.p = (Player) sender;
            nextMenu.init(27, "Edit Stats", this, mm);
            nextMenu.Open((Player) sender);
        }
        return true;
    }

    public void reloadCustomConfig() {
        if (exItemPackFile == null) {
            exItemPackFile = new File(getDataFolder(), "exampleitems.yml");
        }
        exItemPack = YamlConfiguration.loadConfiguration(exItemPackFile);

        // Look for defaults in the jar
        Reader defConfigStream = new InputStreamReader(this.getResource("customConfig.yml"));
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            exItemPack.setDefaults(defConfig);
        }
    }
    public FileConfiguration getCustomConfig() {
        if (exItemPack == null) {
            reloadCustomConfig();
        }
        return exItemPack;
    }
    public void saveCustomConfig() {
        if (exItemPack == null || exItemPackFile == null) {
            return;
        }
        try {
            getCustomConfig().save(exItemPackFile);
        } catch (IOException ex) {
            getLogger().log(Level.SEVERE, "Could not save config to " + exItemPackFile, ex);
        }
    }
}
