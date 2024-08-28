package Plugin.BoxPvp.Model;

import org.bukkit.Location;

public class SafeArea {
    private final Location corner1;
    private final Location corner2;

    public SafeArea(Location corner1, Location corner2) {
        this.corner1 = corner1;
        this.corner2 = corner2;
    }

    public boolean isInside(Location loc) {
        return loc.getX() >= Math.min(corner1.getX(), corner2.getX()) &&
                loc.getX() <= Math.max(corner1.getX(), corner2.getX()) &&
                loc.getY() >= Math.min(corner1.getY(), corner2.getY()) &&
                loc.getY() <= Math.max(corner1.getY(), corner2.getY()) &&
                loc.getZ() >= Math.min(corner1.getZ(), corner2.getZ()) &&
                loc.getZ() <= Math.max(corner1.getZ(), corner2.getZ());
    }
}
