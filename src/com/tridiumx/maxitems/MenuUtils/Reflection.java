package com.tridiumx.maxitems.MenuUtils;

/**
 * Created by TridiumX on 7/16/2016.
 */
import org.bukkit.Bukkit;

public class Reflection {

    private static Reflection reflection;

    public static Reflection getInstance() {
        if (reflection == null) {
            reflection = new Reflection();
        }
        return reflection;
    }

    private String version;

    private Reflection()
    {
        final String name = Bukkit.getServer().getClass().getPackage().getName();
        this.version = name.substring(name.lastIndexOf('.') + 1);
    }

    public String getVersion()
    {
        return version;
    }

}
