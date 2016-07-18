package com.tridiumx.maxitems;


import com.tridiumx.maxitems.Items.ItemManager;
import com.tridiumx.maxitems.Items.MaxItem;
import com.tridiumx.maxitems.MenuUtils.GUIPlayer;
import com.tridiumx.maxitems.Menus.MainMenu;
import com.tridiumx.maxitems.Powers.PowerManager;
import com.tridiumx.maxitems.Utils.CoolDownGenerator;
import com.tridiumx.maxitems.Utils.Status;
import com.tridiumx.maxitems.Utils.StatusManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;




import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.logging.Level;
import com.tridiumx.maxitems.MenuUtils.GUIManager;

/**
 * Created by TridiumX on 7/11/2016.
 */
public class MaxItems extends JavaPlugin{

    private FileConfiguration exItemPack = null;
    private File exItemPackFile = null;
    private GUIManager guiManager;
    private ItemManager itemManager;
    private PowerManager powerManager;
    public CoolDownGenerator cdManager;
    public StatusManager statusManager;



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

        guiManager = new GUIManager(this);
        itemManager = new ItemManager();
        powerManager = new PowerManager(this);
        powerManager.loadPowers(this);
        cdManager = new CoolDownGenerator();
        statusManager = new StatusManager(this);






    }

    public GUIManager getGuiManager(){
        return guiManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args[0].equalsIgnoreCase("menu")){
            GUIPlayer guiPlayer = guiManager.getPlayer((Player) sender);
            guiPlayer.openPage(new MainMenu(this,guiPlayer),true);

        }
        if(args[0].equalsIgnoreCase("give")){
            if(args.length == 2){
                for(String key : getConfig().getConfigurationSection("itempacks").getKeys(false)){
                    File file = new File(getDataFolder(), key + ".yml");
                    Configuration pack = YamlConfiguration.loadConfiguration(file);
                    if(pack.getString(args[1] + ".display_name") != null){
                        Player p = (Player) sender;
                        p.getInventory().addItem(itemManager.giveItem(this, pack, args[1]));

                    }
                }
            }


        }

        if(args[0].equalsIgnoreCase("freeze")){
            statusManager.setPlayerStatus((Player) sender, Status.FROZEN);
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
