package Plugin.Listeners;

import Plugin.Duel.Enum.EndCombatCauses;
import Plugin.Messages.Enum.Messages;
import Plugin.Model.Player.PlayerDataGLobal;
import Plugin.Duel.Model.Request;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

import static Plugin.Messages.MessageManager.*;


public class PlayerListener implements Listener {


    private final xBxTcore plugin;

    public PlayerDataGLobal playerDataGLobal;

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
        if (event.getMessage().equals("Hola")){
            event.setMessage("Adios");
        }
    }


    @EventHandler
    public void PlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        RestartStats(player);
        if (punishedTiemer.containsKey(player.getUniqueId())){
            punishedTiemer.get(player.getUniqueId()).cancel();
            punishedTiemer.remove(player.getUniqueId());
        }

        if (playresInSafeZone.contains(player.getUniqueId())){
            playresInSafeZone.remove(player.getUniqueId());
        }


        xBxTcore.getcombatlogListener().endCombat(player);
        if (player.getWorld().getName().equals(xBxTcore.worldBoxPvp)){
            return;
        }

        if(!player.getWorld().equals(Bukkit.getWorld("lobby")) && !player.getWorld().equals(Bukkit.getWorld(xBxTcore.worldBoxPvp))){
            xBxTcore.getPlayerFileManager().loadkitfavorite(player);
            DelayTeleport(player, "lobby");
        }
    }

    @EventHandler
    public void PlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!xBxTcore.getWorldProtec().contains(player.getWorld()) && !Objects.equals(Bukkit.getWorld(xBxTcore.worldBoxPvp), player.getWorld())){
            if (50 <= player.getLocation().getY() && event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
                if (player.getWorld().getName().contains("stone")){
                    player.teleport(new Location(event.getPlayer().getWorld(), 0, 21, 0));
                }else{
                    player.teleport(new Location(event.getPlayer().getWorld(), 0, 10, 0));
                }
                player.sendMessage(xBxTcore.getMessageManager().MasterMessageLocated(event.getPlayer(),Messages.IncorrectLoc));
            }
        } else {
            if (xBxTcore.getcombatlogListener().isInCombat(player) && xBxTcore.getZoneSafeBoxPvp().isSafeZone(player.getLocation())){
                PunishedBySafeZone(player);
            }
            event.getPlayer().setInvisible(event.getPlayer().getWorld().getName().equals(xBxTcore.worldBoxPvp) && event.getPlayer().getLocation().getY() > 115);
        }
        if (player.getLocation().getBlock().getType().equals(Material.END_PORTAL)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 1));
            player.playSound(player, Sound.BLOCK_PORTAL_TRAVEL, 1, 1);
            xBxTcore.getPlayerFileManager().loadInventoryBoxPvp(player);
            player.teleport(Objects.requireNonNull(Bukkit.getWorld(xBxTcore.worldBoxPvp)).getSpawnLocation());
            xBxTcore.getArmorBonusListener().UpdateBonus(player);
            player.setGameMode(GameMode.SURVIVAL);
        }
    }

    @EventHandler
    public void Playerjoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        RestartStats(player);
        ///////////////////////////////////////////////////
        xBxTcore.getMessageManager().BroadcastMessagejoin(event.getPlayer());
        xBxTcore.getPlayerFileManager().SaveUUIDplayer(event.getPlayer().getUniqueId());
        event.setJoinMessage(null);
        ///////////////////////////////////////////////////
        xBxTcore.getHologramas().ResetKills(player.getUniqueId());
        xBxTcore.getHologramas().updateHologramUser();
        ///////////////////////////////////////////////////
        if(!event.getPlayer().getWorld().getName().equals(xBxTcore.worldBoxPvp)){
            player.getInventory().clear();
        }
        ///////////////////////////////////////////////////
        Utils.RewardVote(player.getName(), false);
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
            xBxTcore.getPlayerFileManager().SaveInventoryBoxPvp(event.getPlayer().getUniqueId(), Utils.getItensInvetory(player));
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo
         + "EL ping del jugador es: " + Colorplayer + event.getPlayer().getPing()));
    }

    private void DelayTeleport(Player player, String worldName) {
        new BukkitRunnable() {
            @Override
            public void run() {
                player.teleport(new Location((Bukkit.getWorld(worldName)), 0, 69, 0, 0, 0));
                cancel();
            }
        }.runTaskTimer(plugin, 4, 0);
    }

    private final ArrayList<UUID> playresInSafeZone = new ArrayList<>();
    private final static HashMap<UUID, BukkitTask> punishedTiemer = new HashMap<>();

    public void PunishedBySafeZone(Player player) {

        if (playresInSafeZone.contains(player.getUniqueId())){
            return;
        }else {
            playresInSafeZone.add(player.getUniqueId());
        }
        Bukkit.getConsoleSender().sendMessage( ChatColor.translateAlternateColorCodes('&',prefixConsole + Colorinfo + "El jugador " + Colorplayer + player.getName() + Colorinfo + " entro en zona segura en combate"));

        new BukkitRunnable() {
            int time = 3;
            public void run() {
                if (!xBxTcore.getZoneSafeBoxPvp().isSafeZone(player.getLocation()) || player.getHealth() <= 0){
                    playresInSafeZone.remove(player.getUniqueId());
                    cancel();
                    return;
                }

                player.sendTitle("", xBxTcore.getMessageManager().MasterMessageLocated(player, Messages.InSafeZone).replace("%time%", String.valueOf(time)), 5, 15, 5);
                if (time <= 0) {
                    cancel();
                    BukkitTask bukkitTask = new BukkitRunnable() {
                        public void run() {
                            if (!xBxTcore.getZoneSafeBoxPvp().isSafeZone(player.getLocation())){
                                playresInSafeZone.remove(player.getUniqueId());
                                punishedTiemer.remove(player.getUniqueId());
                                cancel();
                            }
                            player.damage(5);
                        }
                    }.runTaskTimer(plugin, 5, 5);
                    punishedTiemer.put(player.getUniqueId(), bukkitTask);
                }
                time--;
            }
        }.runTaskTimer(plugin, 0, 20);

    }

    public void RestartStats(Player player) {
        player.setSaturatedRegenRate(10);
        player.setUnsaturatedRegenRate(80);
        player.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(0);
        player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(0);
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
        player.setLevel(0);
        player.setExp(0);
        player.setExpCooldown(0);
        xBxTcore.getDuelManager().RemoveBlockedMoment(player);
    }
}