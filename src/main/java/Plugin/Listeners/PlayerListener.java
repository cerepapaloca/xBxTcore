package Plugin.Listeners;

import Plugin.Enum.EndCombatCauses;
import Plugin.Enum.Messages;
import Plugin.Model.Player.PlayerDataGLobal;
import Plugin.Model.Request;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

import static Plugin.Managers.MessageManager.*;


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
        if (player.getWorld().getName().equals("boxpvp")){
            return;
        }

        if(!player.getWorld().equals(Bukkit.getWorld("lobby")) && !player.getWorld().equals(Bukkit.getWorld("boxpvp"))){
            xBxTcore.getPlayerFileManager().loadkitfavorite(player);
            DelayTeleport(player, "lobby");
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
        } else {
            if (xBxTcore.getcombatlogListener().isInCombat(event.getPlayer()) && xBxTcore.getZoneSafeBoxPvp().isSafeZone(event.getPlayer().getLocation())){
                PunishedBySafeZone(event.getPlayer());
            }
            event.getPlayer().setInvisible(event.getPlayer().getWorld().getName().equals("boxpvp") && event.getPlayer().getLocation().getY() > 115);
        }
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
        xBxTcore.getHologramas().updateHologramUser();
        ///////////////////////////////////////////////////
        if(!event.getPlayer().getWorld().getName().equals("boxpvp")){
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
        /*if(event.getPlayer().isOp()){
            event.getPlayer().setOp(false);
        }*/
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

    public void PunishedBySafeZone(Player player) {

        if (playresInSafeZone.contains(player.getUniqueId())){
            return;
        }else {
            playresInSafeZone.add(player.getUniqueId());
        }

        new BukkitRunnable() {
            int time = 3;
            public void run() {

                if (xBxTcore.getZoneSafeBoxPvp().isSafeZone(player.getLocation()) || player.getHealth() <= 0){
                    playresInSafeZone.remove(player.getUniqueId());
                    cancel();
                    return;
                }

                player.sendTitle("", xBxTcore.getMessageManager().MasterMessage(player, Messages.InSafeZone).replace("%time%", String.valueOf(time)), 5, 15, 5);
                if (time <= 0) {
                    cancel();
                    new BukkitRunnable() {
                        public void run() {
                            if (xBxTcore.getZoneSafeBoxPvp().isSafeZone(player.getLocation()) || player.getHealth() <= 0){
                                playresInSafeZone.remove(player.getUniqueId());
                                cancel();
                            }
                            player.damage(5);
                        }
                    }.runTaskTimer(plugin, 5, 5);
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