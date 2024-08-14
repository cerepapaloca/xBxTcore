package Plugin.Environments;

import Plugin.Managers.PlayerfileManager;
import Plugin.Model.MinaBoxPvp;
import Plugin.Model.Player.PlayerDataGLobal;
import Plugin.Utils.Tools;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.*;


import java.util.*;

import static Plugin.Managers.MessageManager.Colorplayer;


public class Hologramas{

    private final ArrayList<String> textholograms;
    private ArrayList<ArmorStand> armorStandsKills;
    private ArrayList<ArmorStand> armorStandsUser;
    private ArrayList<ArmorStand> armorStandsTimes;
    private final PlayerDataGLobal playerDataGLobal;
    private final HashMap<UUID, Long> playerLogoutTimes = new HashMap<>();
    private final xBxTcore plugin;

    public Hologramas(xBxTcore plugin)  {
        this.armorStandsKills = new ArrayList<>();
        this.armorStandsUser = new ArrayList<>();
        this.playerDataGLobal = xBxTcore.getPlayerDataGlobal();
        this.plugin = plugin;
        textholograms = new ArrayList<>();
        removeArmorStands();
        Bukkit.getScheduler().runTaskTimer(plugin, this::updateHologramkills, 100L, 100L);
    }

    public void removeArmorStands() {
        Objects.requireNonNull(Bukkit.getWorld("lobby")).getEntities().stream().filter(entity -> entity instanceof ArmorStand).forEach(Entity::remove);
        Objects.requireNonNull(Bukkit.getWorld("creatorkits")).getEntities().stream().filter(entity -> entity instanceof ArmorStand).forEach(Entity::remove);
        Objects.requireNonNull(Bukkit.getWorld("boxpvp")).getEntities().stream().filter(entity -> entity instanceof ArmorStand).forEach(Entity::remove);
        createPvPBoard(new Location(Bukkit.getWorld("lobby"), 0, 70, 5.5));
        textholograms.clear();
        createCommandsBoardEN(new Location(Bukkit.getWorld("lobby"), -6, 70.9, 6));
        textholograms.clear();
        createCommandsBoardES(new Location(Bukkit.getWorld("lobby"), 6, 70.9, 6));
        textholograms.clear();
        createDonationBoard(new Location(Bukkit.getWorld("lobby"), -10, 70, 0));
        textholograms.clear();
        createKillBoard(new Location(Bukkit.getWorld("lobby"), 10, 71, 0));
        textholograms.clear();
        createkitsBord(new Location(Bukkit.getWorld("creatorkits"), 0, 64.5, 0));
        textholograms.clear();
        createUserBoard(new Location(Bukkit.getWorld("lobby"), 0, 70, -10));
        textholograms.clear();
        createTimesBoard(new Location(Bukkit.getWorld("boxpvp"), 0, 64.5, 0));
        textholograms.clear();
    }

    public void createPvPBoard(Location location) {
        textholograms.add(ChatColor.translateAlternateColorCodes('&', "&b<--&c&lPVP is here!!&r&b-->"));
        textholograms.add(ChatColor.translateAlternateColorCodes('&', "&b<--&l&c ↓     ↓     ↓&r&b -->"));
        createListArmorStand(textholograms, location, textholograms.size());
    }

    public void createCommandsBoardEN(Location location) {
        textholograms.add(ChatColor.translateAlternateColorCodes('&', "&b<--&c&lCommands&r&b-->&cEN"));
        textholograms.add(ChatColor.translateAlternateColorCodes('&', "&c/kit&e Open List Kit"));
        textholograms.add(ChatColor.translateAlternateColorCodes('&', "&c/kitfavorite&e Load your kit favorite when respawn or save your kit"));
        textholograms.add(ChatColor.translateAlternateColorCodes('&', "&c/savekit&e save all your inventory in a kit"));
        textholograms.add(ChatColor.translateAlternateColorCodes('&', "&c/delkit&e Delete kit selected"));
        textholograms.add(ChatColor.translateAlternateColorCodes('&',"&c/lobby&e Teleports you to the lobby"));
        textholograms.add(ChatColor.translateAlternateColorCodes('&',"&c/duel &e You can create a duel with a player and select world type"));
        createListArmorStand(textholograms, location, textholograms.size());
    }

    public void createCommandsBoardES(Location location){
        textholograms.add(ChatColor.translateAlternateColorCodes('&',"&b<--&c&lComandos&b--> &cES"));
        textholograms.add(ChatColor.translateAlternateColorCodes('&',"&c/kit&e Abres La lista de Kits"));
        textholograms.add(ChatColor.translateAlternateColorCodes('&',"&c/kitfavorite&e Carga el kit favorito o guarda tu kit favorito"));
        textholograms.add(ChatColor.translateAlternateColorCodes('&',"&c/savekit&e Guarda todo tu invetario en un kit"));
        textholograms.add(ChatColor.translateAlternateColorCodes('&',"&c/delkit&e Elimina el kit selecionado"));
        textholograms.add(ChatColor.translateAlternateColorCodes('&',"&c/lobby&e Te teletrasporta al lobby"));
        textholograms.add(ChatColor.translateAlternateColorCodes('&',"&c/duel&e Creas un duelo con otro jugador y seleciona el tipo de mundo"));
        createListArmorStand(textholograms, location, textholograms.size());
    }

    public void createDonationBoard(Location location) {
        textholograms.add(ChatColor.translateAlternateColorCodes('&', "&b<--&c&lDonation&r&b-->"));
        textholograms.add(ChatColor.translateAlternateColorCodes('&', "&ehttps://paypal.me/xBxTpvp"));
        textholograms.add(ChatColor.translateAlternateColorCodes('&', "&b<--&c&lDiscord&b-->"));
        textholograms.add(ChatColor.translateAlternateColorCodes('&', "&ehttps://discord.gg/QYBwEFvnsG"));
        createListArmorStand(textholograms, location ,textholograms.size());
    }

    public void createTimesBoard(Location location) {
        ArmorStand armorStand = (ArmorStand) Objects.requireNonNull(location.getWorld()).spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&b<--&c&lTiempos&r&b-->"));
        armorStand.setCustomNameVisible(true);
        armorStand.setInvisible(true);
        armorStand.setGravity(false);
        armorStand.setMarker(true);
        armorStandsTimes = createListArmorStand(textholograms, location, 10);
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
        armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&b<--&c&lWelcome&r&b-->"));
        armorStand.setCustomNameVisible(true);
        armorStand.setInvisible(true);
        armorStand.setGravity(false);
        armorStand.setMarker(true);
        textholograms.add(ChatColor.translateAlternateColorCodes('&',"&exBxTCore version:&b " + plugin.getVercion()));
        armorStandsUser = createListArmorStand(textholograms, location, 2);
    }

    private ArrayList<ArmorStand> createListArmorStand (ArrayList<String> text, Location location, int size){
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
        for (MinaBoxPvp mina : xBxTcore.getAutoFillsBox().minas){
            ArmorStand armorStand = armorStandsTimes.get(i);
            armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&',mina.getName() + Tools.SecondToMinutes(mina.getTimeLeft())));
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





