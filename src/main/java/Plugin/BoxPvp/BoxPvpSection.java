package Plugin.BoxPvp;

import Plugin.BoxPvp.ItemsBoxPvp.ItemManage;
import Plugin.BoxPvp.ItemsBoxPvp.Listener.ArmorBonusListener;
import Plugin.BoxPvp.ItemsBoxPvp.Listener.ItemBonusListener;
import Plugin.File.PlayerData.PlayerfileManager;
import Plugin.Section;
import Plugin.xBxTcore;

public class BoxPvpSection implements Section {

    private static xBxTcore plugin;

    private static PlayerfileManager playerfileManager;
    private static AutoFillsBox autoFillsBox;
    private static ZoneSafeBoxPvp zoneSafeBoxPvp;
    private static ItemManage itemManage;

    public BoxPvpSection(xBxTcore xBxTcore) {
        plugin = xBxTcore;
    }

    @Override
    public void enable() {
        plugin.register(new ArmorBonusListener(plugin));
        plugin.register(new ItemBonusListener(plugin));
        playerfileManager = new PlayerfileManager(plugin);
        autoFillsBox = new AutoFillsBox(plugin);
        zoneSafeBoxPvp = new ZoneSafeBoxPvp();
        itemManage = new ItemManage(plugin);
    }

    @Override
    public void disable() {

    }

    @Override
    public String getName() {
        return "BoxPvpSection";
    }

    @Override
    public void reloadConfig() {

    }

    public static ZoneSafeBoxPvp getZoneSafeBoxPvp() {
        return zoneSafeBoxPvp;
    }

    public static AutoFillsBox getAutoFillsBox() {
        return autoFillsBox;
    }

    public static PlayerfileManager getPlayerfileManager() {
        return playerfileManager;
    }

    public static ItemManage getItemManage() {
        return itemManage;
    }
}
