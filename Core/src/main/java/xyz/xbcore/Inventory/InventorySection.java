package xyz.xbcore.Inventory;

import xyz.xbcore.Inventory.Listener.InventoryListener;
import xyz.xbcore.Inventory.Listener.ItemframeListener;
import xyz.xbcore.Inventory.Listener.ShulkerBoxInventoryListener;
import xyz.xbcore.Section;
import xyz.xbcore.xBxTcore;
import lombok.Getter;

public class InventorySection implements Section {

    private final xBxTcore plugin;

    @Getter private static InventoryClick inventoryClick;
    @Getter private static InventoryMenu inventoryMenu;
    @Getter public static InventoryManager inventoryManager;

    public InventorySection(xBxTcore xBxTcore) {
        plugin = xBxTcore;
    }

    @Override
    public void enable() {
        plugin.register(new InventoryListener());
        plugin.register(new ItemframeListener());
        plugin.register(new ShulkerBoxInventoryListener());
        inventoryClick = new InventoryClick(plugin);
        inventoryMenu = new InventoryMenu(plugin);
    }

    @Override
    public void disable() {

    }

    @Override
    public String getName() {
        return "InventorySection";
    }

    @Override
    public void reloadConfig() {

    }
}
