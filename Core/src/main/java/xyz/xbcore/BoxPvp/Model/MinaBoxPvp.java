package xyz.xbcore.BoxPvp.Model;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;

import java.util.ArrayList;

@Getter
@Setter
public class MinaBoxPvp {

    private final String name;
    private final Material material;
    private final int timeReset;
    private final Location locationA;
    private final Location locationB;
    private final Location locationTimis;
    private ArmorStand armorStand;
    private final Boolean isSafeZone;
    private int TimeLeft;
    private final ArrayList<String> hexColor = new ArrayList<>();

    public MinaBoxPvp(String name, Material material, int timeReset, Location locationA, Location locationB, Location locationTimis, Boolean isSafeZone) {
        this.name = name;
        this.material = material;
        this.timeReset = timeReset;
        this.locationA = locationA;
        this.locationB = locationB;
        this.locationTimis = locationTimis;
        this.isSafeZone = isSafeZone;
    }

    public void resetTimeLeft() {
        TimeLeft = timeReset;
    }


}
