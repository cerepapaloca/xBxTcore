package Plugin.Environments;

import Plugin.Model.MinaBoxPvp;
import Plugin.Utils.Tools;
import Plugin.xBxTcore;
import org.bukkit.Location;

import java.util.ArrayList;

public class ZoneSafeBoxPvp {

    public static final ArrayList<SafeArea> safeAreas = new ArrayList<>();

    public ZoneSafeBoxPvp() {
        Location location;
        SafeArea mine1;
        for (MinaBoxPvp mine : xBxTcore.getAutoFillsBox().minas){
            if (mine.isSafeZone()){
                location = Tools.getMidpoint(mine.getLocationA(), mine.getLocationB());
                mine1 = new SafeArea(location.clone().add(10, 4, 10), location.clone().add(-10, -4, -10));
                safeAreas.add(mine1);
            }
        }
    }

    public static Boolean isInSafeZone(Location location){
        for (SafeArea area : safeAreas) {
            if (area.isInside(location)) {
                return true;
            }
        }
        return false;
    }

    public Boolean isSafeZone(Location locationPlayer) {
        if (115 <= locationPlayer.getY()) {
            return true;
        } else return isInSafeZone(locationPlayer);
    }
}
