package Plugin.BoxPvp;

import Plugin.BoxPvp.Model.MinaBoxPvp;
import Plugin.BoxPvp.Model.SafeArea;
import Plugin.Utils.Utils;
import org.bukkit.Location;

import java.util.ArrayList;

public class ZoneSafeBoxPvp {

    public static final ArrayList<SafeArea> safeAreas = new ArrayList<>();

    public ZoneSafeBoxPvp() {
        Location location;
        SafeArea mine1;
        for (MinaBoxPvp mine : AutoFillsBox.minas){
            if (mine.isSafeZone()){
                location = Utils.getMidpoint(mine.getLocationA(), mine.getLocationB());
                mine1 = new SafeArea(location.clone().add(10, 6, 10), location.clone().add(-10, -10, -10));
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
