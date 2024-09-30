package Plugin.BoxPvp.Model;

import lombok.RequiredArgsConstructor;
import org.bukkit.Location;

@RequiredArgsConstructor
public class SafeArea {
    private final Location corner1;
    private final Location corner2;

    public boolean isInside(Location loc) {
        return loc.getX() >= Math.min(corner1.getX(), corner2.getX()) &&
                loc.getX() <= Math.max(corner1.getX(), corner2.getX()) &&
                loc.getY() >= Math.min(corner1.getY(), corner2.getY()) &&
                loc.getY() <= Math.max(corner1.getY(), corner2.getY()) &&
                loc.getZ() >= Math.min(corner1.getZ(), corner2.getZ()) &&
                loc.getZ() <= Math.max(corner1.getZ(), corner2.getZ());
    }
}
