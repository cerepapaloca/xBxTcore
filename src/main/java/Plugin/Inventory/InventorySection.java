package Plugin.Inventory;

import Plugin.Inventory.Listener.InventoryListener;
import Plugin.Inventory.Listener.ItemframeListener;
import Plugin.Inventory.Listener.ShulkerBoxInventoryListener;
import Plugin.Section;
import Plugin.xBxTcore;

public class InventorySection implements Section {

    private final xBxTcore plugin;

    private static InventoryClick inventoryClick;
    private static InventoryMenu inventoryMenu;
    public static InventoryManager inventoryManager;

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

    public static InventoryClick getInventoryClick() {
        return inventoryClick;
    }

    public static InventoryMenu getInventoryMenu() {
        return inventoryMenu;
    }

    public static InventoryManager getInvetoryManager() {
        return inventoryManager;
    }

}
