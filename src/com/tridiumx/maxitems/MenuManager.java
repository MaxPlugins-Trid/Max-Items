package com.tridiumx.maxitems;

import com.tridiumx.maxitems.Menus.MainMenu;
import com.tridiumx.maxitems.Menus.Menu;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;

/**
 * Created by TridiumX on 7/11/2016.
 */
public class MenuManager {
    MainMenu mainMenu;
    Plugin pl;
    public MaxItems main;
    public void initMenus(Plugin pl, MaxItems maxItems){
        this.main = maxItems;
        mainMenu = new MainMenu();
        mainMenu.init(27, "Main Menu", pl, this);
        Bukkit.getPluginManager().registerEvents(mainMenu, pl);




        this.pl = pl;
    }

    public Menu getMenuByName(String name){
        if(name == "Main Menu"){
            return mainMenu;
        }
        return null;
    }
    public Configuration getItemPack(String name){
        for(File file : pl.getDataFolder().listFiles()){
            YamlConfiguration con = YamlConfiguration.loadConfiguration(file);
            for(String Key : con.getKeys(false)){
                if(Key.equalsIgnoreCase(name)){
                    return con;
                }
            }
        }
        return null;
    }
    public Menu initNewMenu(Menu menu, Player p, int size, String name){
        Menu m = menu;
        m.p = p;
        m.init(size, name, pl, this);

        return m;
    }
}
