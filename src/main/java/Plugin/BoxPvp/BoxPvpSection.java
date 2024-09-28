package Plugin.BoxPvp;

import Plugin.BoxPvp.ItemsBoxPvp.BonusUpdate;
import Plugin.BoxPvp.ItemsBoxPvp.ItemManage;
import Plugin.BoxPvp.ItemsBoxPvp.Listener.AddRangeListener;
import Plugin.BoxPvp.ItemsBoxPvp.Listener.ArmorBonusListener;
import Plugin.BoxPvp.ItemsBoxPvp.Listener.ItemBonusListener;
import Plugin.Section;
import Plugin.xBxTcore;

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
        plugin.register(new ArmorBonusListener(plugin));
        plugin.register(new ItemBonusListener(plugin));
        plugin.register(new AddRangeListener(plugin));
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
