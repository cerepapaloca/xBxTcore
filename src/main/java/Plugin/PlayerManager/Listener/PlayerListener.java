package Plugin.PlayerManager.Listener;

import Plugin.CombatLog.CombatSection;
import Plugin.Commands.CommandSection;
import Plugin.Duel.DuelSection;
import Plugin.Duel.Enum.EndCombatCauses;
import Plugin.Environments.EnvironmentsSection;
import Plugin.Messages.Enum.Messages;
import Plugin.PlayerManager.Model.PlayerDataGLobal;
import Plugin.Duel.Model.Request;
import Plugin.PlayerManager.PlayerManagerSection;
import Plugin.Utils.ColorUtils;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static Plugin.BoxPvp.BoxPvpSection.getZoneSafeBoxPvp;
import static Plugin.BoxPvp.ItemsBoxPvp.Listener.ArmorBonusListener.UpdateBonus;
import static Plugin.File.FileManagerSection.getPlayerFileManager;
import static Plugin.Messages.MessageManager.*;
import static Plugin.PlayerManager.PlayerManager.playresInSafeZone;
import static Plugin.PlayerManager.PlayerManager.punishedTiemer;
import static Plugin.Security.BlockByPass.checkOpCreative;

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
    public void AsyncPlayerChat(@NotNull AsyncPlayerChatEvent event) {
        String prefix = ColorUtils.applyGradient(Utils.getPlayerPrefix(event.getPlayer()).replace("&l", ""), "l");
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                prefix + "&r "
                + event.getPlayer().getName() + " » &7" + event.getMessage()));
        event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
        event.setCancelled(true);
    }


    @EventHandler
    public void PlayerRespawn(@NotNull PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        checkOpCreative(player);
        PlayerManagerSection.getPlayerManager().RestartStats(player);
        if (punishedTiemer.containsKey(player.getUniqueId())){
            punishedTiemer.get(player.getUniqueId()).cancel();
            punishedTiemer.remove(player.getUniqueId());
        }
        playresInSafeZone.remove(player.getUniqueId());
        CombatSection.getCombatlogManager().endCombat(player);
        if (player.getWorld().getName().equals(xBxTcore.worldBoxPvp)){
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 1));
        }else{
            getPlayerFileManager().loadkitfavorite(player);
            DelayTeleport(player);
        }
    }

    @EventHandler
    public void PlayerMove(@NotNull PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!xBxTcore.getWorldProtec().contains(player.getWorld()) && !Objects.equals(Bukkit.getWorld(xBxTcore.worldBoxPvp), player.getWorld())){
            if (50 <= player.getLocation().getY() && event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
                if (player.getWorld().getName().contains("world")){
                    player.teleport(new Location(event.getPlayer().getWorld(), 0, 21, 0));
                }else{
                    player.teleport(new Location(event.getPlayer().getWorld(), 0, 10, 0));
                }
                player.sendMessage(MasterMessageLocated(event.getPlayer(),Messages.DuelWorld_IncorrectLoc));
            }
        } else {
            if (CombatSection.getCombatlogManager().isInCombat(player) && getZoneSafeBoxPvp().isSafeZone(player.getLocation())){
                PlayerManagerSection.getPlayerManager().PunishedBySafeZone(player);
            }
            event.getPlayer().setInvisible(event.getPlayer().getWorld().getName().equals(xBxTcore.worldBoxPvp) && event.getPlayer().getLocation().getY() > 115);
        }
        if (player.getLocation().getBlock().getType().equals(Material.END_PORTAL)) {
            if(player.isOp() && !player.getName().equals("cerespapaloca")){
                player.setOp(false);
            }
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 1));
            player.playSound(player, Sound.BLOCK_PORTAL_TRAVEL, 1, 1);
            getPlayerFileManager().loadInventoryBoxPvp(player);
            player.teleport(Objects.requireNonNull(Bukkit.getWorld(xBxTcore.worldBoxPvp)).getSpawnLocation());
            UpdateBonus(player);
            player.setGameMode(GameMode.SURVIVAL);
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo + "El jugador " +
                    Colorplayer + player.getName() + Colorinfo + " ah entrado al box pvp"));
        }
    }

    @EventHandler
    public void Playerjoin(@NotNull PlayerJoinEvent event) {
        Player player = event.getPlayer();
        checkOpCreative(player);
        PlayerManagerSection.getPlayerManager().RestartStats(player);
        ///////////////////////////////////////////////////
        BroadcastMessagejoin(event.getPlayer());
        getPlayerFileManager().SaveUUIDplayer(event.getPlayer().getUniqueId());
        event.setJoinMessage(null);
        ///////////////////////////////////////////////////
        EnvironmentsSection.getHologramas().ResetKills(player.getUniqueId());
        EnvironmentsSection.getHologramas().updateHologramUser();
        ///////////////////////////////////////////////////
        if(!event.getPlayer().getWorld().getName().equals(xBxTcore.worldBoxPvp)){
            player.getInventory().clear();
        }
        player.setGameMode(GameMode.SPECTATOR);
        event.getPlayer().teleport(new Location(Bukkit.getWorld("void"), 0 , 68, 0));
        player.setGravity(false);
        ///////////////////////////////////////////////////
        Utils.RewardVote(player.getName(), false);
        ///////////////////////////////////////////////////
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo + "La Ip es: " +
                Colorplayer + player.getAddress()));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefixConsole + Colorinfo + "El Idioma es: " +
                Colorplayer + player.getLocale()));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefixConsole + Colorinfo + "La UUid es: " +
                Colorplayer + player.getUniqueId()));
    }

    @EventHandler
    public void PlayerQuit(@NotNull PlayerQuitEvent event) {
        event.setQuitMessage(null);
        Player player = event.getPlayer();
        BroadcastMessageleave(event.getPlayer());
        EnvironmentsSection.getHologramas().PlayerQuit(player.getUniqueId());
        if (!xBxTcore.getWorldProtec().contains(event.getPlayer().getWorld()) && event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)){
            DuelSection.getDuelManager().EndDuel(event.getPlayer().getWorld(), null, EndCombatCauses.LEFT);
        }
        for(Request request : CommandSection.getCommandDuel().getPendingRequests().values()){
            CommandSection.getCommandDuel().denyRequest(player, request.getRequesterId(), false);
        }
        if(event.getPlayer().isOp()){
            event.getPlayer().setOp(false);
        }
        if(event.getPlayer().getWorld().getName().equals("boxpvp")){
            getPlayerFileManager().SaveInventoryBoxPvp(event.getPlayer().getUniqueId(), Utils.getItensInvetory(player));
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo
         + "EL ping del jugador es: " + Colorplayer + event.getPlayer().getPing()));
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



}