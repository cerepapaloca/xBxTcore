package Plugin.Model;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;

public class MinaBoxPvp {

    private final String name;
    private final Material material;
    private final int timeReset;
    private final Location locationA;
    private final Location locationB;
    private final Location locationTimis;
    private ArmorStand armorStand;
    private Boolean isSafeZone;
    private int TimeLeft;

    public MinaBoxPvp(String name, Material material, int timeReset, Location locationA, Location locationB, Location locationTimis, Boolean isSafeZone) {
        this.name = name;
        this.material = material;
        this.timeReset = timeReset;
        this.locationA = locationA;
        this.locationB = locationB;
        this.locationTimis = locationTimis;
        this.isSafeZone = isSafeZone;
    }

    public ArmorStand getArmorStand() {
        return armorStand;
    }

    public Location getLocationTimis() {
        return locationTimis;
    }

    public Location getLocationB() {
        return locationB;
    }

    public Location getLocationA() {
        return locationA;
    }

    public int getTimeReset() {
        return timeReset;
    }

    public Material getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }

    public int resetTimeLeft() {
        return TimeLeft = timeReset;
    }

    public int getTimeLeft() {
        return TimeLeft;
    }

    public Boolean isSafeZone() {
        return isSafeZone;
    }

    public void setTimeLeft(int timeLeft) {
        TimeLeft = timeLeft;
    }

    public void setArmorStand(ArmorStand armorStand) {
        this.armorStand = armorStand;
    }

}
