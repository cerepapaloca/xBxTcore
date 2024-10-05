package xyz.xbcore.PlayerManager.Listener;

import xyz.xbcore.Commands.CommandSection;
import xyz.xbcore.Duel.DuelSection;
import xyz.xbcore.Duel.Enum.EndCombatCauses;
import xyz.xbcore.Duel.Model.Request;
import xyz.xbcore.Environment.EnvironmentsSection;
import xyz.xbcore.File.FileManagerSection;
import xyz.xbcore.PlayerManager.PlayerManagerSection;
import xyz.xbcommun.Utils.UtilsGlobal;
import xyz.xbcore.Utils.Utils;
import xyz.xbcore.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static xyz.xbcommun.Messages.MessageManager.*;
import static xyz.xbcommun.Messages.MessageManager.Colorplayer;
import static xyz.xbcore.Security.BlockByPass.checkOpCreative;
import static xyz.xbcore.Security.BlockByPass.passwordList;

public class JoinAndQuitPlayerListener implements Listener {

    @EventHandler
    public void Playerjoin(@NotNull PlayerJoinEvent event) {
        Player player = event.getPlayer();
        checkOpCreative(player);
        PlayerManagerSection.getPlayerManager().RestartStats(player);
        ///////////////////////////////////////////////////
        BroadcastMessagejoin(event.getPlayer());
        FileManagerSection.getPlayerFileManager().SaveUUIDplayer(event.getPlayer().getUniqueId());
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
                Colorplayer + Objects.requireNonNull(player.getAddress()).getHostString()));
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
        passwordList.remove(player.getUniqueId());
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
            FileManagerSection.getPlayerFileManager().SaveInventoryBoxPvp(event.getPlayer().getUniqueId(), UtilsGlobal.getItensInvetory(player));
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo
                + "EL ping del jugador es: " + Colorplayer + event.getPlayer().getPing()));
    }
}
