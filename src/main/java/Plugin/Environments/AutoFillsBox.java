package Plugin.Environments;

import Plugin.Model.MinaBoxPvp;
import Plugin.xBxTcore;
import org.bukkit.*;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class AutoFillsBox {

    public final xBxTcore plugin;
    public final ArrayList<MinaBoxPvp> minas = new ArrayList<>();

    public AutoFillsBox(xBxTcore plugin) {
        this.plugin = plugin;
        /////
        Location location1 = new Location(Bukkit.getWorld("boxpvp"), 5, 60, 5);
        Location location2 = new Location(Bukkit.getWorld("boxpvp"), 10, 64, 10);
        Location location3 = new Location(Bukkit.getWorld("boxpvp"), 5, 60, 5);
        minas.add(new MinaBoxPvp("Cosa", Material.BLUE_GLAZED_TERRACOTTA, 45, location1, location2, location3));
        for (MinaBoxPvp minaBoxPvp : minas){
            xBxTcore.getHologramasBoxPvp().createTimesMina(minaBoxPvp);
        }
        new BukkitRunnable() {
            public void run() {

                for (MinaBoxPvp minaBoxPvp : minas) {
                    if (minaBoxPvp.getTimeLeft() < 1){
                        FillArea(location1, location2, Material.BLUE_GLAZED_TERRACOTTA);
                        minaBoxPvp.resetTimeLeft();
                    }
                    minaBoxPvp.setTimeLeft(minaBoxPvp.getTimeLeft() - 1);
                }

                xBxTcore.getHologramasBoxPvp().updateHologramTimes();
                xBxTcore.getHologramasBoxPvp().updateHologramTimesMina();
            }
        }.runTaskTimer(plugin, 20, 20);
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
