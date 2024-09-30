package Plugin.Inventory;

import Plugin.Inventory.Listener.InventoryListener;
import Plugin.Inventory.Listener.ItemframeListener;
import Plugin.Inventory.Listener.ShulkerBoxInventoryListener;
import Plugin.Section;
import Plugin.xBxTcore;
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
