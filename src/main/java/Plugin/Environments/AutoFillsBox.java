package Plugin.Environments;

import Plugin.Model.MinaBoxPvp;
import Plugin.xBxTcore;
import org.bukkit.*;
import org.bukkit.block.data.type.Switch;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Random;

public class AutoFillsBox {

    public final xBxTcore plugin;
    public final ArrayList<MinaBoxPvp> minas = new ArrayList<>();

    public AutoFillsBox(xBxTcore plugin) {
        this.plugin = plugin;
        /////
        Location location1;
        Location location2;
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), 100, 66, 20);
        location2 = new Location(Bukkit.getWorld("boxpvp"), 93, 67, 20);
        minas.add(createMinaBoxPvp("Negro", Material.BLACK_GLAZED_TERRACOTTA, location1, location2, 10, true));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), 84, 65, 60);
        location2 = new Location(Bukkit.getWorld("boxpvp"), 78, 66, 60);
        minas.add(createMinaBoxPvp("Rojo", Material.RED_GLAZED_TERRACOTTA, location1, location2, 10, true));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), 56, 67, 86);
        location2 = new Location(Bukkit.getWorld("boxpvp"), 56, 68, 80);
        minas.add(createMinaBoxPvp("Verde", Material.GREEN_GLAZED_TERRACOTTA, location1, location2, 10, true));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), 26, 69, 103);
        location2 = new Location(Bukkit.getWorld("boxpvp"), 26, 70, 97);
        minas.add(createMinaBoxPvp("Marrón", Material.BROWN_GLAZED_TERRACOTTA, location1, location2, 10, true));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), -23, 69, 103);
        location2 = new Location(Bukkit.getWorld("boxpvp"), -23, 70, 97);
        minas.add(createMinaBoxPvp("Azul", Material.BLUE_GLAZED_TERRACOTTA, location1, location2, 10, false));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), -63, 69, 87);
        location2 = new Location(Bukkit.getWorld("boxpvp"), -63, 70, 81);
        minas.add(createMinaBoxPvp("Morado", Material.PURPLE_GLAZED_TERRACOTTA, location1, location2, 10, false));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), -83, 68, 57);
        location2 = new Location(Bukkit.getWorld("boxpvp"), -77, 69, 57);
        minas.add(createMinaBoxPvp("Cian", Material.CYAN_GLAZED_TERRACOTTA, location1, location2, 10, false));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), -95, 68, 19);
        location2 = new Location(Bukkit.getWorld("boxpvp"),  -89, 69, 19);
        minas.add(createMinaBoxPvp("Gris Claro", Material.LIGHT_GRAY_GLAZED_TERRACOTTA, location1, location2, 10, false));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), -93, 66, -22);
        location2 = new Location(Bukkit.getWorld("boxpvp"), -87, 67, -22);
        minas.add(createMinaBoxPvp("Gris", Material.GRAY_GLAZED_TERRACOTTA, location1, location2, 10, false));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), -82, 64, -58);
        location2 = new Location(Bukkit.getWorld("boxpvp"), -76, 65, -58);
        minas.add(createMinaBoxPvp("Rosa", Material.PINK_GLAZED_TERRACOTTA, location1, location2, 10, false));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), -56, 63, -83);
        location2 = new Location(Bukkit.getWorld("boxpvp"), -56, 64, -77);
        minas.add(createMinaBoxPvp("Lima", Material.LIME_GLAZED_TERRACOTTA, location1, location2, 10, false));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), -19, 65, -101);
        location2 = new Location(Bukkit.getWorld("boxpvp"), -19, 66, -95);
        minas.add(createMinaBoxPvp("Amarillo", Material.YELLOW_GLAZED_TERRACOTTA, location1, location2, 10, false));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), 18, 67, -102);
        location2 = new Location(Bukkit.getWorld("boxpvp"), 18, 68, -96);
        minas.add(createMinaBoxPvp("Azul Claro", Material.LIGHT_BLUE_GLAZED_TERRACOTTA, location1, location2, 10, false));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), 55, 69, -84);
        location2 = new Location(Bukkit.getWorld("boxpvp"), 55, 70, -78);
        minas.add(createMinaBoxPvp("Magenta", Material.MAGENTA_GLAZED_TERRACOTTA, location1, location2, 10, false));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), 83, 68, -56);
        location2 = new Location(Bukkit.getWorld("boxpvp"), 77, 69, -56);
        minas.add(createMinaBoxPvp("Naranja", Material.ORANGE_GLAZED_TERRACOTTA, location1, location2, 10, false));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), 100, 67, -23);
        location2 = new Location(Bukkit.getWorld("boxpvp"), 94, 68, -23);
        minas.add(createMinaBoxPvp("Blanco", Material.WHITE_GLAZED_TERRACOTTA, location1, location2, 10, false));
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), 5, 35, -52);
        location2 = new Location(Bukkit.getWorld("boxpvp"), -6, 39, -63);
        minas.add(new MinaBoxPvp("Dinero 1",Material.COPPER_BLOCK,10, location1, location2, new Location(Bukkit.getWorld("boxpvp"),0,41, -58), false));
        location1 = new Location(Bukkit.getWorld("boxpvp"), 52, 35, 5);
        location2 = new Location(Bukkit.getWorld("boxpvp"), 63, 39, -6);
        minas.add(new MinaBoxPvp("Dinero 1",Material.IRON_BLOCK,10, location1, location2, new Location(Bukkit.getWorld("boxpvp"),58, 41, 0), false));
        location1 = new Location(Bukkit.getWorld("boxpvp"), -6, 35, 52);
        location2 = new Location(Bukkit.getWorld("boxpvp"), 5, 39, 63);
        minas.add(new MinaBoxPvp("Dinero 1",Material.GOLD_BLOCK,10, location1, location2, new Location(Bukkit.getWorld("boxpvp"),0, 41, 58), false));
        location1 = new Location(Bukkit.getWorld("boxpvp"), -52, 35, -6);
        location2 = new Location(Bukkit.getWorld("boxpvp"), -63, 39, 5);
        minas.add(new MinaBoxPvp("Dinero 1",Material.EMERALD_BLOCK,10, location1, location2, new Location(Bukkit.getWorld("boxpvp"),-58, 41, 0), false));
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), -3, 36, -3);
        location2 = new Location(Bukkit.getWorld("boxpvp"), 2, 39, 2);
        minas.add(new MinaBoxPvp("Bloque magico",Material.PEARLESCENT_FROGLIGHT,10, location1, location2, new Location(Bukkit.getWorld("boxpvp"),0, 42, 0), false));
        location1 = new Location(Bukkit.getWorld("boxpvp"), 60, 122, 3);
        location2 = new Location(Bukkit.getWorld("boxpvp"), 52, 126, -5);
        minas.add(new MinaBoxPvp("Mina inicial",Material.SLIME_BLOCK,10, location1, location2, new Location(Bukkit.getWorld("boxpvp"),48, 123, -0.5), false));
        for (MinaBoxPvp minaBoxPvp : minas){
            xBxTcore.getHologramasBoxPvp().createTimesMina(minaBoxPvp);
        }
        new BukkitRunnable() {
            public void run() {

                for (MinaBoxPvp minaBoxPvp : minas) {
                    if (minaBoxPvp.getTimeLeft() < 1){
                        FillArea(minaBoxPvp.getLocationA(), minaBoxPvp.getLocationB(), minaBoxPvp.getMaterial());
                        minaBoxPvp.resetTimeLeft();
                    }
                    minaBoxPvp.setTimeLeft(minaBoxPvp.getTimeLeft() - 1);
                }

                xBxTcore.getHologramasBoxPvp().updateHologramTimes();
                xBxTcore.getHologramasBoxPvp().updateHologramTimesMina();
            }
        }.runTaskTimer(plugin, 20, 20);
    }

    public void FillArea(Location location1, Location location2, Material material) {
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
            double i = Math.random();
            switch (material) {
                case COPPER_BLOCK -> {
                    if (i < 0.20) {
                        loc.getBlock().setType(Material.COPPER_BLOCK);
                    } else{
                        loc.getBlock().setType(Material.DEEPSLATE_COPPER_ORE);
                    }
                }
                case IRON_BLOCK -> {
                    if (i < 0.20) {
                        loc.getBlock().setType(Material.IRON_BLOCK);
                    } else{
                        loc.getBlock().setType(Material.DEEPSLATE_IRON_ORE);
                    }
                }
                case GOLD_BLOCK -> {
                    if (i < 0.20) {
                        loc.getBlock().setType(Material.GOLD_BLOCK);
                    } else {
                        loc.getBlock().setType(Material.DEEPSLATE_GOLD_ORE);
                    }
                }
                case EMERALD_BLOCK -> {
                    if (i < 0.20) {
                        loc.getBlock().setType(Material.EMERALD_BLOCK);
                    } else {
                        loc.getBlock().setType(Material.DEEPSLATE_EMERALD_ORE);
                    }
                }
                default -> loc.getBlock().setType(material);
            }

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

    public MinaBoxPvp createMinaBoxPvp(String name,Material material, Location locationCentral, Location locationTime, int time, Boolean isSafe)  {
        Location location1 = new Location(Bukkit.getWorld("boxpvp"), locationCentral.getX() + 3, locationCentral.getY(), locationCentral.getZ() + 3);
        Location location2 = new Location(Bukkit.getWorld("boxpvp"), locationCentral.getX() - 3, locationCentral.getY() + 3, locationCentral.getZ() - 3);
        return new MinaBoxPvp(name, material, time, location1, location2, locationTime, isSafe);
    }
}
