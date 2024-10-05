package xyz.xbcore.Environment;

import xyz.xbcore.xBxTcore;
import org.bukkit.*;
import org.bukkit.scheduler.BukkitRunnable;

import static xyz.xbcore.Messages.MessageManager.*;

public class Cleaner {

    private final xBxTcore plugin;
    private Location startLocation;
    private Location endLocation;
    private int CountBLock;


    public Cleaner(xBxTcore plugin) {
        this.plugin = plugin;
    }

    public void clearArea(String worldname) {
        if(worldname.equals("lobby")) {
            startLocation = new Location(Bukkit.getWorld(worldname), -150, 0, -150);
            endLocation = new Location(Bukkit.getWorld(worldname), 150, 30, 150);
        }else if (worldname.equals("boxpvp")){
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning + "NO LO HAGAS TONTO"));
            return;
        }else{
            startLocation = new Location(Bukkit.getWorld(worldname), -40, 0, -40);
            endLocation = new Location(Bukkit.getWorld(worldname), 39, 50, 39);
        }
        clearAir(worldname);
    }

    public void clearAir(String worldname) {
        World world = Bukkit.getWorld(worldname);
        endLocation.setWorld(world);
        startLocation.setWorld(world);
        int minX = Math.min(startLocation.getBlockX(), endLocation.getBlockX());
        int minY = Math.min(startLocation.getBlockY(), endLocation.getBlockY());
        int minZ = Math.min(startLocation.getBlockZ(), endLocation.getBlockZ());
        int maxX = Math.max(startLocation.getBlockX(), endLocation.getBlockX());
        int maxY = Math.max(startLocation.getBlockY(), endLocation.getBlockY());
        int maxZ = Math.max(startLocation.getBlockZ(), endLocation.getBlockZ());

        CountBLock = 0;

        new BukkitRunnable() {

            long time = System.currentTimeMillis();
            private int x = minX;
            private int y = minY;
            private int z = minZ;

            @Override
            public void run() {
                for (int i = 0 ; i <= 100;) {
                    if (y <= maxY) {
                        Location loc = new Location(world, x, y, z);

                        i = switch (worldname) {
                            case "flat_world1" ,"flat_world2" ,"flat_world3" -> CleanerWorld(y, loc, i);
                            default -> CleanerLobby(loc, i);
                        };

                        x++;

                        if (x > maxX) {
                            x = minX;
                            z++;
                        }
                        if (z > maxZ) {
                            z = minZ;
                            y++;
                        }
                    } else {

                        cancel();
                        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
                                prefixConsole + ColorSuccess + "Se a eliminado exitosamente " + CountBLock + " Bloques y tardo "
                                        + (System.currentTimeMillis() - time) + " Ms"));
                        break;
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 1L);
    }

    public int CleanerWorld(int y, Location loc, int i) {
        if(loc.getBlock().getType() == Material.BEDROCK){
            return i;
        }

        if(y <= 15) {
            if (!loc.getBlock().getType().equals(Material.STONE)) {
                loc.getBlock().setType(Material.STONE);
                CountBLock++;
                return i + 1;
            }
        }else if(y <= 19) {
            if (!loc.getBlock().getType().equals(Material.DIRT)) {
                loc.getBlock().setType(Material.DIRT);
                CountBLock++;
                return i + 1;
            }
        }else if(y == 20){
            if (!loc.getBlock().getType().equals(Material.GRASS_BLOCK)) {
                loc.getBlock().setType(Material.GRASS_BLOCK);
                CountBLock++;
                return i + 1;
            }
        }else{
            if(!loc.getBlock().getType().equals(Material.AIR)){
                loc.getBlock().setType(Material.AIR);
                CountBLock++;
                return i + 1;
            }
        }
        return i;
    }

    public int CleanerLobby(Location loc, int i){
        if(!loc.getBlock().getType().equals(Material.AIR) && !loc.getBlock().getType().equals(Material.BEDROCK)){
            loc.getBlock().setType(Material.AIR);
            CountBLock++;
            return i + 1;
        }
        return i;
    }

}

