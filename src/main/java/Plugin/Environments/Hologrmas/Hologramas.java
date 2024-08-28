package Plugin.Environments.Hologrmas;

import Plugin.File.PlayerfileManager;
import Plugin.BoxPvp.Model.MinaBoxPvp;
import Plugin.Model.Player.PlayerDataGLobal;
import Plugin.Utils.ColorUtils;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;


import java.util.*;

import static Plugin.BoxPvp.AutoFillsBox.minas;
import static Plugin.Messages.MessageManager.*;
import static Plugin.Utils.ColorUtils.applyGradient;


public class Hologramas{

    protected final ArrayList<String> textholograms;
    private ArrayList<ArmorStand> armorStandsKills;
    private ArrayList<ArmorStand> armorStandsUser;
    private ArrayList<ArmorStand> armorStandsTimes;
    private final PlayerDataGLobal playerDataGLobal;
    private final HashMap<UUID, Long> playerLogoutTimes = new HashMap<>();
    protected static xBxTcore plugin;

    public Hologramas(xBxTcore plugin)  {
        this.armorStandsKills = new ArrayList<>();
        this.armorStandsUser = new ArrayList<>();
        this.playerDataGLobal = xBxTcore.getPlayerDataGlobal();
        Hologramas.plugin = plugin;
        textholograms = new ArrayList<>();
        removeArmorStands();
        Bukkit.getScheduler().runTaskTimer(plugin, this::updateHologramkills, 100L, 100L);
        xBxTcore.hologramas = this;
    }

    public void removeArmorStands() {
        new BukkitRunnable() {
            @Override
            public void run() {
                long time = System.currentTimeMillis();
                for (World world : Bukkit.getWorlds()) {

                    for (ArmorStand armorStand : world.getEntitiesByClass(ArmorStand.class)) {
                        armorStand.remove();
                    }
                }
                for (MinaBoxPvp minaboxPvp : minas){
                    xBxTcore.getHologramasBoxPvp().createTimesMina(minaboxPvp);
                }
                textholograms.clear();
                createPvPBoard(new Location(Bukkit.getWorld("lobby"), 0, 70, 5.5));
                textholograms.clear();
                createDonationBoard(new Location(Bukkit.getWorld("lobby"), -10, 70, 0));
                textholograms.clear();
                createKillBoard(new Location(Bukkit.getWorld("lobby"), 10, 71, 0));
                textholograms.clear();
                createkitsBord(new Location(Bukkit.getWorld("creatorkits"), 0, 64.5, 0));
                textholograms.clear();
                createUserBoard(new Location(Bukkit.getWorld("lobby"), 0, 70, -10));
                textholograms.clear();
                createTimesBoard(new Location(Bukkit.getWorld("boxpvp"), 12, 128, 0));
                textholograms.clear();
                createRewardsTimesBoard(new Location(Bukkit.getWorld("boxpvp"), 35.5, 125.5, -5.5));
                textholograms.clear();
                createBoxPvp(new Location(Bukkit.getWorld("lobby"), 0.5, 69, 14.5));
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo + "Hologramas Creados En " + Colorplayer + (System.currentTimeMillis() - time) + "ms"));
            }
        }.runTaskLater(plugin, 40);
    }

    public void createPvPBoard(Location location) {
        textholograms.add(ChatColor.translateAlternateColorCodes('&', Colorinfo + "<---" + Colorplayer + "El PvP Es Aquí!!" + Colorinfo + "--->"));
        textholograms.add(ChatColor.translateAlternateColorCodes('&', Colorinfo + "<---" + Colorplayer + " ↓   ↓   ↓ " + Colorinfo + "--->"));
        createListArmorStand(textholograms, location, textholograms.size());
    }

    public void createDonationBoard(Location location) {
        textholograms.add(ChatColor.translateAlternateColorCodes('&', "&b<--&c&lDiscord&b-->"));
        textholograms.add(ChatColor.translateAlternateColorCodes('&', "&ehttps://discord.gg/QYBwEFvnsG"));
        createListArmorStand(textholograms, location ,textholograms.size());
    }

    public void createRewardsTimesBoard(Location location) {
        textholograms.add(ChatColor.translateAlternateColorCodes('&', Colorinfo + "<---" + Colorplayer + "Recompensas Gratis!!" + Colorinfo + "--->"));
        createListArmorStand(textholograms, location ,textholograms.size());
    }

    public void createBoxPvp(Location location) {
        textholograms.add(ChatColor.translateAlternateColorCodes('&', Colorinfo + "<---" + Colorplayer + "El BOXPvP Es Aquí!!" + Colorinfo + "--->"));
        textholograms.add(ChatColor.translateAlternateColorCodes('&', Colorinfo + "<---" + Colorplayer + " ↓   ↓   ↓ " + Colorinfo + "--->"));
        createListArmorStand(textholograms, location ,textholograms.size());
    }

    public void createTimesBoard(Location location) {
        ArmorStand armorStand = (ArmorStand) Objects.requireNonNull(location.getWorld()).spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', Colorinfo + "<---" + Colorplayer + "Tiempos" + Colorinfo + "--->"));
        armorStand.setCustomNameVisible(true);
        armorStand.setInvisible(true);
        armorStand.setGravity(false);
        armorStand.setMarker(true);
        armorStandsTimes = createListArmorStand(textholograms, location, 16);
    }

    public void createkitsBord(Location location) {
        textholograms.add(ChatColor.translateAlternateColorCodes('&', "&b<--&cHow To Save Your Kit&b-->"));
        textholograms.add(ChatColor.translateAlternateColorCodes('&', "&eChoose the items you want to have in your kit"));
        textholograms.add(ChatColor.translateAlternateColorCodes('&', "&ewhen finished use /savekit or /sk"));
        textholograms.add(ChatColor.translateAlternateColorCodes('&', "&b<&cSyntax&b>"));
        textholograms.add(ChatColor.translateAlternateColorCodes('&', "&f/savekit&b <Name Your Kit. You Can Color Code Using &>&e <The Item That Will Be Displayed(Optional)>"));
        textholograms.add(ChatColor.translateAlternateColorCodes('&', "&b<--&cComo Guardar Tu Kit&b-->"));
        textholograms.add(ChatColor.translateAlternateColorCodes('&', "&eElige todo lo que quieras para tu kit"));
        textholograms.add(ChatColor.translateAlternateColorCodes('&', "&ecuando termines usa /savekit o /sk"));
        textholograms.add(ChatColor.translateAlternateColorCodes('&', "&b<&cSintaxis&b>"));
        textholograms.add(ChatColor.translateAlternateColorCodes('&', "&f/savekit&b <El nombre del kit. Puedes usar codigos de colores usando &>&e <El obejeto que se va mostrar (Opcional)>"));
        createListArmorStand(textholograms, location, textholograms.size());
    }

    public void createKillBoard(Location location) {
        ArmorStand armorStand = (ArmorStand) Objects.requireNonNull(location.getWorld()).spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&b<--&c&lTop Kills&r&b-->"));
        armorStand.setCustomNameVisible(true);
        armorStand.setInvisible(true);
        armorStand.setGravity(false);
        armorStand.setMarker(true);
        armorStandsKills = createListArmorStand(textholograms, location, 10);
    }

    public void createUserBoard(Location location) {
        ArmorStand armorStand = (ArmorStand) Objects.requireNonNull(location.getWorld()).spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&b<--&c&lBienvenido&r&b-->"));
        armorStand.setCustomNameVisible(true);
        armorStand.setInvisible(true);
        armorStand.setGravity(false);
        armorStand.setMarker(true);
        textholograms.add(ChatColor.translateAlternateColorCodes('&',"&exBxTCore version:&b " + plugin.getDescription().getVersion()));
        armorStandsUser = createListArmorStand(textholograms, location, 2);
    }

    protected ArrayList<ArmorStand> createListArmorStand (ArrayList<String> text, Location location, int size){
        ArrayList<ArmorStand> armorStands1 = new ArrayList<>();
        ArmorStand armorStand;
        for(int i = 0; i < size; i++) {
            Location lineLocation = location.add(0, -0.3, 0);
            armorStand = (ArmorStand) Objects.requireNonNull(lineLocation.getWorld()).spawnEntity(lineLocation, EntityType.ARMOR_STAND);
            if (text.size() <= i){
                text.add("???");
            }
            armorStand.setCustomName(text.get(i));
            armorStand.setCustomNameVisible(true);
            armorStand.setInvisible(true);
            armorStand.setGravity(false);
            armorStand.setMarker(true);
            armorStands1.add(armorStand);
        }
        return armorStands1;
    }

    ////////////////////////////////////////////////////
    public List<Player> sortPlayersByKill(Map<UUID, Integer> playerKills) {
        List<Player> players = new ArrayList<>();
        for (UUID uuid : playerKills.keySet()) {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null) {
                players.add(player);
            }
        }
        players.sort(Comparator.comparingInt(player -> playerDataGLobal.getKills(player.getUniqueId())));
        Collections.reverse(players);
        return players;
    }

    private void updateHologramkills() {
        List<Player> sortedPlayers = sortPlayersByKill(playerDataGLobal.getPlayerKills());

        for (int i = 0; i < armorStandsKills.size(); i++) {
            ArmorStand armorStand = armorStandsKills.get(i);
            Player player = null;

            if (i < sortedPlayers.size()) {
                player = sortedPlayers.get(i);
            }

            if (player != null) {
                int lvl;
                if(player.getWorld().getName().equals("lobby")){
                    lvl = player.getLevel();
                }else{
                    lvl = 0;
                }
                armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', Colorplayer + player.getName() + ":&b " + playerDataGLobal.getKills(player.getUniqueId()) + " &3(&b" + lvl + "&3) " + "&3 kills"));
            } else {
                armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&8--"));
            }
        }
    }

    public void updateHologramTimes(){
        int i = 0;
        String name;
        String iconSafeZone;
        for (ArmorStand armorStand : armorStandsTimes){
            MinaBoxPvp mina = xBxTcore.getAutoFillsBox().minas.get(i);
            if (mina.isSafeZone()){
                iconSafeZone = ChatColor.translateAlternateColorCodes('&', " &6[&2☮&6]");
            } else {
                iconSafeZone = ChatColor.translateAlternateColorCodes('&', " &6[&c⚔&6]");
            }
            name = applyGradient("<#" + ColorUtils.blockToHex(mina.getMaterial()) + ">" + Utils.arabicToRoman(i + 1) + " " + mina.getName() + "<#" +
                    ColorUtils.modifyColorHexWithHLS(ColorUtils.blockToHex(mina.getMaterial()), 0.1f, 0.3f, -0.3f) +
                    ">");

            armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&l" + name + " " +
                    Colorplayer + Utils.SecondToMinutes(mina.getTimeLeft()) + iconSafeZone));
            i++;
        }
    }

    public void updateHologramUser() {
        ArmorStand armorStand = armorStandsUser.get(1);
        armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&eNumber of unique users:&b " + PlayerfileManager.playesfiles.getConfigFiles().size()));
    }

    public void ResetKills(UUID playerUUID){
        long currentTime = System.currentTimeMillis();
        if(playerLogoutTimes.containsKey(playerUUID)){
            long logoutTime = playerLogoutTimes.get(playerUUID);
            long timeDifference = currentTime - logoutTime;
            long timeLimit = 15 * 1000;
            if (timeDifference >= timeLimit){
                playerDataGLobal.resetKills(playerUUID);
            }
        }else{
            playerDataGLobal.resetKills(playerUUID);
        }

    }

    public void PlayerQuit(UUID playerUUID){
        long logoutTime = System.currentTimeMillis();
        playerLogoutTimes.put(playerUUID, logoutTime);
    }



    public void PlayerKillerAdd(Player killer) {
        if (killer != null) {
            UUID killerUUID = killer.getUniqueId();
            playerDataGLobal.addKill(killerUUID);
        }
    }
}





