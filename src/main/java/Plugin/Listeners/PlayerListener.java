package Plugin.Listeners;

import Plugin.Managers.PlayerfileManager;
import Plugin.Model.EndCombatCauses;
import Plugin.Model.Messages;
import Plugin.Model.PlayerDataGLobal;
import Plugin.Model.Request;
import Plugin.Utils.Tools;
import Plugin.xBxTcore;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

import static Plugin.Managers.MessageManager.*;


public class PlayerListener implements Listener {


    private final xBxTcore plugin;
    public String weaponname;
    private Player playeranchor;
    private Player playeranvil;
    private Material MaterialFalling;
    public PlayerDataGLobal playerDataGLobal;
    private Player playerMasterKiller;

    public PlayerListener(xBxTcore plugin) {
        this.plugin = plugin;
        playerDataGLobal = new PlayerDataGLobal();
    }

    ////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!xBxTcore.getWorldProtec().contains(event.getPlayer().getWorld()) && event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)){
            if (xBxTcore.getDuelManager().GetBlockedMoment().contains(event.getPlayer().getUniqueId())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!xBxTcore.getWorldProtec().contains(event.getPlayer().getWorld()) && event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)){
            if (xBxTcore.getDuelManager().GetBlockedMoment().contains(event.getPlayer().getUniqueId())) {
                event.setCancelled(true);
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////

    @EventHandler
    public void AsyncPlayerChat(AsyncPlayerChatEvent event) {
        Bukkit.broadcastMessage(event.getPlayer().getName() + " » " + event.getMessage());
        event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
        event.setCancelled(true);
    }


    @EventHandler
    public void PlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        xBxTcore.getPlayerFileManager().loadkitfavorite(player);
        if(!player.getWorld().equals(Bukkit.getWorld("lobby"))){
            DelayTeleport(player);
        }
    }

    @EventHandler
    public void PlayerMove(PlayerMoveEvent event) {
        if (!xBxTcore.getWorldProtec().contains(event.getPlayer().getWorld()) && !Objects.equals(Bukkit.getWorld("boxpvp"), event.getPlayer().getWorld())){
            if (50 <= event.getPlayer().getLocation().getY() && event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
                if (event.getPlayer().getWorld().getName().contains("stone")){
                    event.getPlayer().teleport(new Location(event.getPlayer().getWorld(), 0, 21, 0));
                }else{
                    event.getPlayer().teleport(new Location(event.getPlayer().getWorld(), 0, 10, 0));
                }
                event.getPlayer().sendMessage(xBxTcore.getMessageManager().MasterMessage(event.getPlayer(),Messages.IncorrectLoc));
            }
        }
    }

    private Long coolDownJoin = System.currentTimeMillis();
    private final HashMap<String, Long> listAntiBot = new HashMap<>();
    private final HashMap<String, Integer> listAntiBotBan = new HashMap<>();
    private final String messageKick = ChatColor.translateAlternateColorCodes('&',prefixKick + Colorinfo + "Only one player can join every 3 seconds\nSolo se puede unir un jugador por cada 3 segundos");
    private final String messageKick2 = ChatColor.translateAlternateColorCodes('&',prefixKick + Colorinfo + "Please &l Do Not Enter&r " + Colorinfo + "it is possible that the server is under a bot attack\nPor favor &l No Entrar&r " + Colorinfo + "es posible que el servidor este bajo un ataque de bots");
    private final String messageKickConsole1 = ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning + "Se expulso al jugador " + Colorplayer + "%p%" + ColorWarning + " por entrar paridamente");
    private final String messageKickConsole2 = ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning + "Se expulso al jugador " + Colorplayer + "%p%" + ColorWarning + " por posible ataque de bots");
    private final String messageKickConsole3 = ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning + "Se le baneo ip del jugador " + Colorplayer + "%p%" + ColorWarning + " por posible ataque de bots");


    @EventHandler
    public void PlayerPreLoginEvent(AsyncPlayerPreLoginEvent event) {

        if (PlayerfileManager.namesPlayres.contains(event.getName())) {
            if (!listAntiBot.containsKey(event.getName())) {
                return;
            }

            if (listAntiBot.get(event.getName()) >= System.currentTimeMillis()) {
                event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
                event.setKickMessage(messageKick);
                Bukkit.getConsoleSender().sendMessage(messageKickConsole1.replace("%p%", event.getName()));
                listAntiBot.put(event.getName(), System.currentTimeMillis() + 3500);
            }
        }else{
            if (coolDownJoin >= System.currentTimeMillis()) {
                String name = event.getName();
                Bukkit.getConsoleSender().sendMessage(messageKickConsole2.replace("%p%", name));
                if (listAntiBotBan.containsKey(event.getName())) {
                    if (listAntiBotBan.get(event.getName()) >= 5) {
                        Bukkit.banIP(event.getAddress());
                        Bukkit.getConsoleSender().sendMessage(messageKickConsole3.replace("%p%", name));
                        listAntiBotBan.remove(event.getName());
                    }
                }
                event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
                event.setKickMessage(messageKick2);
                listAntiBotBan.put(name, listAntiBotBan.getOrDefault(name, 0) + 1);
            }else{
                Bukkit.unbanIP(event.getAddress());
            }
            coolDownJoin = System.currentTimeMillis() + 30000;
        }
    }

    @EventHandler
    public void PlayerLoginEvent(PlayerLoginEvent event) {
        listAntiBot.put(event.getPlayer().getName(), System.currentTimeMillis() + 3000);
    }

    @EventHandler
    public void Playerjoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        RestartStats(player);
        ///////////////////////////////////////////////////
        xBxTcore.getMessageManager().BroadcastMessagejoin(event.getPlayer());
        xBxTcore.getPlayerFileManager().SaveUUIDplayer(event.getPlayer().getUniqueId());
        player.teleport(new Location(Bukkit.getWorld("lobby"), 0 ,69 ,0));
        event.setJoinMessage(null);
        ///////////////////////////////////////////////////
        xBxTcore.getHologramas().ResetKills(player.getUniqueId());
        ///////////////////////////////////////////////////
        /*String prefixName = xBxTcore.getPlayerFileManager().loadPrefix(player.getUniqueId());
        xBxTcore.getHologramas().updateHologramUser();
        new BukkitRunnable() {
            public void run() {
                Objects.requireNonNull(xBxTcore.getTabAPI().getNameTagManager()).setPrefix(Objects.requireNonNull(xBxTcore.getTabAPI().getPlayer(player.getUniqueId())), prefixName);
                Objects.requireNonNull(xBxTcore.getTabAPI().getTabListFormatManager()).setPrefix(Objects.requireNonNull(xBxTcore.getTabAPI().getPlayer(player.getUniqueId())), prefixName);
            }
        }.runTaskLater(plugin, 20);*/
        ///////////////////////////////////////////////////
        Tools.RewardVote(player.getName(), false);
        ///////////////////////////////////////////////////
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefixConsole + Colorinfo + "El Idioma es: " +
                Colorinfo + player.getLocale()));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefixConsole + Colorinfo + "La UUid es: " +
                Colorinfo + player.getUniqueId()));
    }

    @EventHandler
    public void PlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        Player player = event.getPlayer();
        xBxTcore.getMessageManager().BroadcastMessageleave(event.getPlayer());
        xBxTcore.getHologramas().PlayerQuit(player.getUniqueId());
        if (!xBxTcore.getWorldProtec().contains(event.getPlayer().getWorld()) && event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)){
            xBxTcore.getDuelManager().EndDuel(event.getPlayer().getWorld(), null, EndCombatCauses.LEFT);
        }
        for(Request request : xBxTcore.getCommandDuel().getPendingRequests().values()){
            xBxTcore.getCommandDuel().denyRequest(player, request.getRequesterId(), false);
        }
        if(event.getPlayer().isOp()){
            event.getPlayer().setOp(false);
        }
        if(event.getPlayer().getWorld().getName().equals("boxpvp")){
            xBxTcore.getPlayerFileManager().SaveInventoryBoxPvp(event.getPlayer().getUniqueId(), Tools.getItensInvetory(player));
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo
         + "EL ping del jugador es: " + Colorplayer + event.getPlayer().getPing()));
    }

    @EventHandler
    public void PlayerPlaceAnchor(PlayerInteractEvent event) {
        if (event.getMaterial() == Material.RESPAWN_ANCHOR) {
            playeranchor = event.getPlayer();
        }
    }

    @EventHandler
    public void PlayerPlaceAnvil(BlockPlaceEvent event) {
        if (event.getBlock().getType() == Material.ANVIL || (event.getBlock().getType() == Material.POINTED_DRIPSTONE)) {
            MaterialFalling = event.getBlock().getType();
            playeranvil = event.getPlayer();
        }
    }

    @EventHandler
    public void PlayerDeathMessage(PlayerDeathEvent event) {
        event.getEntity().teleport(xBxTcore.getMultiverseCore().getMVWorldManager().getMVWorld("lobby").getSpawnLocation());
        if (event.getEntity().getKiller() != null) {
            playerMasterKiller = event.getEntity().getKiller();
        }

        if (event.getEntity().getLastDamageCause() != null) {
            Player p = event.getEntity();
            //if (event.getEntity().getWorld().equals(Bukkit.getWorld("lobby"))){
                if(p == playerMasterKiller){
                    suicide(p);
                    event.setDeathMessage(null);
                    return;
                }
                ItemStack weapon;
                if (null != event.getEntity().getKiller()){
                    weapon = playerMasterKiller.getInventory().getItemInMainHand();
                    if (weapon.getItemMeta() != null){
                        weaponname = Objects.requireNonNull(weapon.getItemMeta()).getDisplayName();
                        if(weaponname.isEmpty()){
                            weaponname = Objects.requireNonNull(weapon.getType().name().replace("_", " ")).toLowerCase();
                        }
                    }else{
                        weaponname = "?";
                    }
                    if(weaponname.equalsIgnoreCase("http")){
                        weaponname = "&4&l&oNo SPAM!!!!";
                    }
                    weaponname = ChatColor.translateAlternateColorCodes('&', weaponname);

                }else {
                    weaponname = "";
                }
                switch (Objects.requireNonNull(event.getEntity().getLastDamageCause()).getCause()) {
                    case ENTITY_EXPLOSION:
                        weaponname = custonNameWeapon(Material.END_CRYSTAL, playerMasterKiller);
                        xBxTcore.getMessageManager().BroadcastMessageDied(Messages.Died2, p, playerMasterKiller, weaponname);
                        break;
                    case BLOCK_EXPLOSION:
                        if(p == playeranchor){
                            suicide(p);
                            event.setDeathMessage(null);
                            return;
                        }
                        playerMasterKiller = playeranchor;
                        if (Objects.requireNonNull(event.getDeathMessage()).contains("[Intentional Game Design]")) {
                            weaponname = custonNameWeapon(Material.RESPAWN_ANCHOR, playerMasterKiller);
                        }else{
                            weaponname = custonNameWeapon(Material.TNT, playerMasterKiller);
                        }
                        xBxTcore.getMessageManager().BroadcastMessageDied(Messages.Died2, p, playerMasterKiller, weaponname);
                        break;
                    case SUICIDE:
                        suicide(playerMasterKiller);
                        break;
                    case ENTITY_ATTACK, PROJECTILE, ENTITY_SWEEP_ATTACK:
                        xBxTcore.getMessageManager().BroadcastMessageDied(Messages.Died1, p, playerMasterKiller, weaponname);
                        break;
                    case FALL:
                        xBxTcore.getMessageManager().BroadcastMessageDied(Messages.Died5, p, null, "");
                        break;
                    case FALLING_BLOCK:
                        playerMasterKiller = playeranvil;
                        if(p == playeranvil){
                            suicide(p);
                            event.setDeathMessage(null);
                            return;
                        }
                        weaponname = custonNameWeapon(MaterialFalling, playerMasterKiller);
                        xBxTcore.getMessageManager().BroadcastMessageDied(Messages.Died1, p, playerMasterKiller, weaponname);
                        break;
                    case SUFFOCATION:
                        xBxTcore.getMessageManager().BroadcastMessageDied(Messages.Died3, p, null,"");
                        break;
                }
                if (playerMasterKiller != null) {
                    int i = playerMasterKiller.getLevel();
                    playerMasterKiller.setLevel(i + 1);
                    xBxTcore.getcombatlogListener().endCombat(event.getEntity());
                    xBxTcore.getcombatlogListener().endCombat(playerMasterKiller);
                    xBxTcore.getHologramas().PlayerKillerAdd(playerMasterKiller);
                    playerMasterKiller = null;
                }
                if (!event.getEntity().getWorld().equals(Bukkit.getWorld("lobby"))){
                xBxTcore.getDuelManager().EndDuel(event.getEntity().getWorld(), playerMasterKiller, EndCombatCauses.DIED);
                }
                event.setDeathMessage(null);
            /*}else{
                if (event.getEntity().getGameMode().equals(GameMode.SURVIVAL)) {
                    for(Player player : event.getEntity().getWorld().getPlayers()){
                       if(player != event.getEntity() && player.getGameMode().equals(GameMode.SURVIVAL)){
                           xBxTcore.getDuelManager().EndDuel(event.getEntity().getWorld(), player, EndCombatCauses.DIED);
                           xBxTcore.getHologramas().PlayerKillerAdd(player);
                           int i = player.getLevel();
                           player.setLevel(i + 1);
                           xBxTcore.getMessageManager().BroadcastMessageDied(Messages.DiedDuel, p, player,"");
                           break;
                       }
                    }
                }
            }*/
        }
        event.setDeathMessage(null);
    }

    private String custonNameWeapon(Material material, Player player){
        ItemStack weapon = player.getInventory().getItemInMainHand();
        ItemStack weaponOff = player.getInventory().getItemInOffHand();
        if(weapon.getType().equals(material)){
            weaponname = Objects.requireNonNull(weapon.getItemMeta()).getDisplayName();
            if(weaponname.isEmpty()){
                weaponname = Objects.requireNonNull(weapon.getType().name().replace("_", " ")).toLowerCase();
            }
        }else if(weaponOff.getType().equals(material)){
            weaponname = Objects.requireNonNull(weaponOff.getItemMeta()).getDisplayName();
            if(weaponname.isEmpty()){
                weaponname = Objects.requireNonNull(weaponOff.getType().name().replace("_", " ")).toLowerCase();
            }
        }else{
            weaponname = material.name().replace("_", " ").toLowerCase();
        }
        /*if(weaponname.contains("")){
            weaponname = "&4&l&oNo SPAM!!!!";
        }*/
        return ChatColor.translateAlternateColorCodes('&', weaponname);
    }


    private void DelayTeleport(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                player.teleport(new Location((Bukkit.getWorld("lobby")), 0, 69, 0, 0, 0));
                cancel();
            }
        }.runTaskTimer(plugin, 4, 0);
    }

    public void RestartStats(Player player) {
        player.setLevel(0);
        player.setExp(0);
        player.setExpCooldown(0);
        xBxTcore.getDuelManager().RemoveBlockedMoment(player);
    }

    public void suicide(Player player) {
        xBxTcore.getMessageManager().BroadcastMessageDied(Messages.Died4, player, null, "");
    }
}