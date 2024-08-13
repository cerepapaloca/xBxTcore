package Plugin.Environments;

import Plugin.xBxTcore;
import org.bukkit.*;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Locale;
import java.util.LongSummaryStatistics;
import java.util.Objects;

public class AutoFillsBox {

    public final xBxTcore plugin;

    public AutoFillsBox(xBxTcore plugin) {
        this.plugin = plugin;

        /////
        Location location1 = new Location(xBxTcore.worldBoxpvp, 5, 60, 5);
        Location location2 = new Location(xBxTcore.worldBoxpvp, 10, 64, 10);

        new BukkitRunnable() {
            public void run() {
                FillArea(location1, location2, Material.BLUE_GLAZED_TERRACOTTA);
            }
        }.runTaskTimer(plugin, 100, 100);
    }

    public void FillArea(Location location1, Location location2, Material material1) {
        int minX = Math.min(location1.getBlockX(), location2.getBlockX());
        int minY = Math.min(location1.getBlockY(), location2.getBlockY());
        int minZ = Math.min(location1.getBlockZ(), location2.getBlockZ());
        int maxX = Math.max(location1.getBlockX(), location2.getBlockX());
        int maxY = Math.max(location1.getBlockY(), location2.getBlockY());
        int maxZ = Math.max(location1.getBlockZ(), location2.getBlockZ());

        int x = minX;
        int z = minZ;

        for (int y = minY; y <= maxY;) {
            Location loc = new Location(Bukkit.getWorld("boxpvp"), x, y, z);
            loc.getBlock().setType(material1);
            x++;

            if (x > maxX) {
                x = minX;
                z++;
            }
            if (z > maxZ) {
                z = minZ;
                y++;
            }
        }
    }
}
