package xyz.xbcore.BoxPvp;

import xyz.xbcore.BoxPvp.Model.MinaBoxPvp;
import xyz.xbcore.Environment.EnvironmentsSection;
import xyz.xbcore.xBxTcore;
import org.bukkit.*;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public record AutoFillsBox(xBxTcore plugin) {

    public static ArrayList<MinaBoxPvp> minas = new ArrayList<>();

    public AutoFillsBox(xBxTcore plugin) {
        this.plugin = plugin;
        /////
        Location location1;
        Location location2;
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), 100, 66, 20);
        location2 = new Location(Bukkit.getWorld("boxpvp"), 93, 67, 20);
        minas.add(createMinaBoxPvp("Negro", Material.BLACK_GLAZED_TERRACOTTA, location1, location2, 22, true));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), 84, 65, 60);
        location2 = new Location(Bukkit.getWorld("boxpvp"), 78, 66, 60);
        minas.add(createMinaBoxPvp("Rojo", Material.RED_GLAZED_TERRACOTTA, location1, location2, 30, true));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), 56, 67, 86);
        location2 = new Location(Bukkit.getWorld("boxpvp"), 56, 68, 80);
        minas.add(createMinaBoxPvp("Verde", Material.GREEN_GLAZED_TERRACOTTA, location1, location2, 35, true));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), 26, 69, 103);
        location2 = new Location(Bukkit.getWorld("boxpvp"), 26, 70, 97);
        minas.add(createMinaBoxPvp("Marrón", Material.BROWN_GLAZED_TERRACOTTA, location1, location2, 48, true));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), -23, 69, 103);
        location2 = new Location(Bukkit.getWorld("boxpvp"), -23, 70, 97);
        minas.add(createMinaBoxPvp("Azul", Material.BLUE_GLAZED_TERRACOTTA, location1, location2, 65, false));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), -63, 69, 87);
        location2 = new Location(Bukkit.getWorld("boxpvp"), -63, 70, 81);
        minas.add(createMinaBoxPvp("Morado", Material.PURPLE_GLAZED_TERRACOTTA, location1, location2, 76, false));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), -83, 68, 57);
        location2 = new Location(Bukkit.getWorld("boxpvp"), -77, 69, 57);
        minas.add(createMinaBoxPvp("Cian", Material.CYAN_GLAZED_TERRACOTTA, location1, location2, 82, false));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), -95, 68, 19);
        location2 = new Location(Bukkit.getWorld("boxpvp"), -89, 69, 19);
        minas.add(createMinaBoxPvp("Gris Claro", Material.LIGHT_GRAY_GLAZED_TERRACOTTA, location1, location2, 90, false));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), -93, 66, -22);
        location2 = new Location(Bukkit.getWorld("boxpvp"), -87, 67, -22);
        minas.add(createMinaBoxPvp("Gris", Material.GRAY_GLAZED_TERRACOTTA, location1, location2, 100, false));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), -82, 64, -58);
        location2 = new Location(Bukkit.getWorld("boxpvp"), -76, 65, -58);
        minas.add(createMinaBoxPvp("Rosa", Material.PINK_GLAZED_TERRACOTTA, location1, location2, 105, false));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), -56, 63, -83);
        location2 = new Location(Bukkit.getWorld("boxpvp"), -56, 64, -77);
        minas.add(createMinaBoxPvp("Lima", Material.LIME_GLAZED_TERRACOTTA, location1, location2, 110, false));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), -19, 65, -101);
        location2 = new Location(Bukkit.getWorld("boxpvp"), -19, 66, -95);
        minas.add(createMinaBoxPvp("Amarillo", Material.YELLOW_GLAZED_TERRACOTTA, location1, location2, 115, false));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), 18, 67, -102);
        location2 = new Location(Bukkit.getWorld("boxpvp"), 18, 68, -96);
        minas.add(createMinaBoxPvp("Azul Claro", Material.LIGHT_BLUE_GLAZED_TERRACOTTA, location1, location2, 120, false));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), 55, 69, -84);
        location2 = new Location(Bukkit.getWorld("boxpvp"), 55, 70, -78);
        minas.add(createMinaBoxPvp("Magenta", Material.MAGENTA_GLAZED_TERRACOTTA, location1, location2, 145, false));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), 83, 68, -56);
        location2 = new Location(Bukkit.getWorld("boxpvp"), 77, 69, -56);
        minas.add(createMinaBoxPvp("Naranja", Material.ORANGE_GLAZED_TERRACOTTA, location1, location2, 160, false));
        ////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), 100, 67, -23);
        location2 = new Location(Bukkit.getWorld("boxpvp"), 94, 68, -23);
        minas.add(createMinaBoxPvp("Blanco", Material.WHITE_GLAZED_TERRACOTTA, location1, location2, 180, false));
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        MinaBoxPvp minaBoxPvp;
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), 60, 122, 3);
        location2 = new Location(Bukkit.getWorld("boxpvp"), 52, 126, -5);
        minas.add(new MinaBoxPvp("Mina Inicial", Material.SLIME_BLOCK, 30, location1, location2, new Location(Bukkit.getWorld("boxpvp"), 48, 123, -0.5), false));

        location1 = new Location(Bukkit.getWorld("boxpvp"), 5, 35, -52);
        location2 = new Location(Bukkit.getWorld("boxpvp"), -6, 39, -63);
        minaBoxPvp = new MinaBoxPvp("Mina de Cobre", Material.COPPER_BLOCK, 1200, location1, location2, new Location(Bukkit.getWorld("boxpvp"), 0, 41, -58), false);
        minaBoxPvp.getHexColor().add(0, "D07131");
        minaBoxPvp.getHexColor().add(1, "e6a87e");
        minas.add(minaBoxPvp);
        location1 = new Location(Bukkit.getWorld("boxpvp"), 52, 35, 5);
        location2 = new Location(Bukkit.getWorld("boxpvp"), 63, 39, -6);
        minaBoxPvp = new MinaBoxPvp("Mina de Hierro", Material.IRON_BLOCK, 1200, location1, location2, new Location(Bukkit.getWorld("boxpvp"), 58, 41, 0), false);
        minaBoxPvp.getHexColor().add(0, "e3e3e3");
        minaBoxPvp.getHexColor().add(1, "8f8f8f");
        minas.add(minaBoxPvp);
        location1 = new Location(Bukkit.getWorld("boxpvp"), -6, 35, 52);
        location2 = new Location(Bukkit.getWorld("boxpvp"), 5, 39, 63);
        minaBoxPvp = new MinaBoxPvp("Mina de Oro", Material.GOLD_BLOCK, 1200, location1, location2, new Location(Bukkit.getWorld("boxpvp"), 0, 41, 58), false);
        minaBoxPvp.getHexColor().add(0, "d9aa02");
        minaBoxPvp.getHexColor().add(1, "ffd952");
        minas.add(minaBoxPvp);
        location1 = new Location(Bukkit.getWorld("boxpvp"), -52, 35, -6);
        location2 = new Location(Bukkit.getWorld("boxpvp"), -63, 39, 5);
        minaBoxPvp = new MinaBoxPvp("Mina deEsmeraldas", Material.EMERALD_BLOCK, 1200, location1, location2, new Location(Bukkit.getWorld("boxpvp"), -58, 41, 0), false);
        minaBoxPvp.getHexColor().add(0, "24ff24");
        minaBoxPvp.getHexColor().add(1, "a8ffa8");
        minas.add(minaBoxPvp);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        location1 = new Location(Bukkit.getWorld("boxpvp"), -3, 36, -3);
        location2 = new Location(Bukkit.getWorld("boxpvp"), 2, 39, 2);
        minaBoxPvp = new MinaBoxPvp("Mina de Especial", Material.PEARLESCENT_FROGLIGHT, 7200, location1, location2, new Location(Bukkit.getWorld("boxpvp"), 0, 42, 0), false);
        minaBoxPvp.getHexColor().add(0, "ff38f8");
        minaBoxPvp.getHexColor().add(1, "ffa1fc");
        minas.add(minaBoxPvp);
        new BukkitRunnable() {
            public void run() {

                for (MinaBoxPvp minaBoxPvp : minas) {
                    if (minaBoxPvp.getTimeLeft() < 1) {
                        FillArea(minaBoxPvp.getLocationA(), minaBoxPvp.getLocationB(), minaBoxPvp.getMaterial());
                        minaBoxPvp.resetTimeLeft();
                    }
                    minaBoxPvp.setTimeLeft(minaBoxPvp.getTimeLeft() - 1);
                }

                EnvironmentsSection.getHologramasBoxPvp().updateHologramTimes();
                EnvironmentsSection.getHologramasBoxPvp().updateHologramTimesMina();
            }
        }.runTaskTimer(plugin, 80, 20);
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

        for (int y = minY; y <= maxY; ) {
            Location loc = new Location(Bukkit.getWorld("boxpvp"), x, y, z);
            double i = Math.random();
            switch (material) {
                case COPPER_BLOCK -> {
                    if (i < 0.30) {
                        loc.getBlock().setType(Material.COPPER_BLOCK);
                    } else {
                        loc.getBlock().setType(Material.DEEPSLATE_COPPER_ORE);
                    }
                }
                case IRON_BLOCK -> {
                    if (i < 0.30) {
                        loc.getBlock().setType(Material.IRON_BLOCK);
                    } else {
                        loc.getBlock().setType(Material.DEEPSLATE_IRON_ORE);
                    }
                }
                case GOLD_BLOCK -> {
                    if (i < 0.30) {
                        loc.getBlock().setType(Material.GOLD_BLOCK);
                    } else {
                        loc.getBlock().setType(Material.DEEPSLATE_GOLD_ORE);
                    }
                }
                case EMERALD_BLOCK -> {
                    if (i < 0.30) {
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

    public MinaBoxPvp createMinaBoxPvp(String name, Material material, Location locationCentral, Location locationTime, int time, Boolean isSafe) {
        Location location1 = new Location(Bukkit.getWorld("boxpvp"), locationCentral.getX() + 3, locationCentral.getY(), locationCentral.getZ() + 3);
        Location location2 = new Location(Bukkit.getWorld("boxpvp"), locationCentral.getX() - 3, locationCentral.getY() + 3, locationCentral.getZ() - 3);
        return new MinaBoxPvp(name, material, time, location1, location2, locationTime, isSafe);
    }
}
