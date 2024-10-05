package xyz.xbcore.BoxPvp;

import xyz.xbcommun.RegisterManager;
import xyz.xbcore.BoxPvp.ItemsBoxPvp.BonusUpdate;
import xyz.xbcore.BoxPvp.ItemsBoxPvp.ItemManage;
import xyz.xbcore.BoxPvp.ItemsBoxPvp.Listener.AddRangeListener;
import xyz.xbcore.BoxPvp.ItemsBoxPvp.Listener.ArmorBonusListener;
import xyz.xbcore.BoxPvp.ItemsBoxPvp.Listener.ItemBonusListener;
import xyz.xbcommun.Section;
import xyz.xbcore.xBxTcore;

public class BoxPvpSection implements Section {

    private static xBxTcore plugin;

    private static AutoFillsBox autoFillsBox;
    private static ZoneSafeBoxPvp zoneSafeBoxPvp;
    private static ItemManage itemManage;
    private static BonusUpdate bonusUpdate;

    public BoxPvpSection(xBxTcore xBxTcore) {
        plugin = xBxTcore;
    }

    @Override
    public void enable() {
        RegisterManager.register(new ArmorBonusListener(plugin));
        RegisterManager.register(new ItemBonusListener(plugin));
        RegisterManager.register(new AddRangeListener(plugin));
        autoFillsBox = new AutoFillsBox(plugin);
        zoneSafeBoxPvp = new ZoneSafeBoxPvp();
        itemManage = new ItemManage(plugin);
        bonusUpdate = new BonusUpdate(plugin);
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

    public static ItemManage getItemManage() {
        return itemManage;
    }

    public static BonusUpdate getBonusUpdate() {
        return bonusUpdate;
    }
}
