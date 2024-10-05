package xyz.xbcore.PlayerManager.Listener;

import xyz.xbcore.CombatLog.CombatSection;
import xyz.xbcore.File.FileManagerSection;
import xyz.xbcore.Messages.Messages.Messages;
import xyz.xbcore.PlayerManager.Model.PlayerDataGLobal;
import xyz.xbcore.PlayerManager.PlayerManagerSection;
import xyz.xbcore.Security.SystemBan.ContextBan;
import xyz.xbcore.xBxTcore;
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

import static xyz.xbcore.BoxPvp.BoxPvpSection.getZoneSafeBoxPvp;
import static xyz.xbcore.BoxPvp.ItemsBoxPvp.BonusUpdate.UpdateBonus;
import static xyz.xbcore.Messages.MessageManager.*;
import static xyz.xbcore.PlayerManager.PlayerManager.playresInSafeZone;
import static xyz.xbcore.PlayerManager.PlayerManager.punishedTiemer;
import static xyz.xbcore.Security.SystemBan.BanManager.checkBanPlayer;
import static xyz.xbcore.Security.BlockByPass.checkOpCreative;
import static xyz.xbcore.Utils.Utils.RewardBoxPvpCheck;

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
        PlayerManagerSection.getModerationChat().CheckMessage(event);
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
            FileManagerSection.getPlayerFileManager().loadkitfavorite(player);
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
            RewardBoxPvpCheck(player.getName());
            if (checkBanPlayer(player, ContextBan.BOX_PVP) != null)return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 1));
            player.playSound(player, Sound.BLOCK_PORTAL_TRAVEL, 1, 1);
            FileManagerSection.getPlayerFileManager().loadInventoryBoxPvp(player);
            player.teleport(Objects.requireNonNull(Bukkit.getWorld(xBxTcore.worldBoxPvp)).getSpawnLocation());
            UpdateBonus(player);
            player.setGameMode(GameMode.SURVIVAL);
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo + "El jugador " +
                    Colorplayer + player.getName() + Colorinfo + " ah entrado al box pvp"));
        }
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