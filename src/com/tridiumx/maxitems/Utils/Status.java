package com.tridiumx.maxitems.Utils;

/**
 * Created by TridiumX on 7/18/2016.
 */
public enum Status {
    FROZEN("Frozen", 0, 40, "&7[&3FREEZE&7]");



    String statusName;

    int statusID;
    long durration;
    String prefix;

    Status(String statusName, int statusID, long durration, String prefix){
        this.statusName = statusName;
        this.statusID = statusID;
        this. durration = durration;
        this.prefix = Chat.color(prefix);
    }

    public String toString(){
        return statusName;
    }

    public int getID(){
        return statusID;
    }

    public long getDurration(){
        return durration;
    }

    public static Status[] asArray(){
        Status[] sa = {FROZEN};
        return sa;
    }
    public String getPrefix(){
        return prefix;
    }
}
