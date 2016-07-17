package com.tridiumx.maxitems.MenuUtils.page;

/**
 * Created by TridiumX on 7/16/2016.
 */
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public interface GUIInventory {

    void open();

    boolean hasAction(int slot);

    void executeAction(int slot, InventoryClickEvent event);

    void setItem(int slot, ItemStack item);

    void setItem(int slot, ItemStack item, InventoryAction action);

}
