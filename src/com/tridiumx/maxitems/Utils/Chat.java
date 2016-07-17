package com.tridiumx.maxitems.Utils;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

/**
 * Created by TridiumX on 7/16/2016.
 */
public class Chat {
    //Color chat using the & key instead
    public static String color(String text){
        String s = ChatColor.translateAlternateColorCodes('&', text);
        return s;
    }

    public static String strip(String text){
        return ChatColor.stripColor(text);
    }

    public static String getPrefix(Plugin plugin){
        return color(plugin.getConfig().getString("prefix"));
    }
    public static String makeMessage(String prefix, String text){
        return prefix + " " + color(text);
    }
    public static boolean equalsIgnoreColor(String text1, String text2){
        String t1 = strip(text1);
        String t2 = strip(text2);
        if(t1.equalsIgnoreCase(t2)){
            return true;
        }
        return false;
    }

    public static String removeFromString(String full, String toRemove){
        return full.replace(toRemove, "");
    }
    public static boolean isInteger( String input ) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }
}
