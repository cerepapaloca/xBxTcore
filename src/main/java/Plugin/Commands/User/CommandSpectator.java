package Plugin.Commands.User;

import Plugin.Messages.Messages.Messages;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static Plugin.Messages.MessageManager.MasterMessageLocated;

public class CommandSpectator implements CommandExecutor {

    private final xBxTcore plugin;

    public CommandSpectator(xBxTcore plugin){
        this.plugin = plugin;
    }
    public boolean onCommand(@Nullable CommandSender sender,@Nullable Command cmd,@Nullable String label, String[] args) {
        if(sender instanceof Player p){
            if(Objects.equals(Bukkit.getWorld("lobby"), p.getWorld())){
                if (args.length == 1) {
                    if(Bukkit.getWorld(args[0]) != null){
                        World world = Bukkit.getWorld(args[0]);
                        if (!xBxTcore.getWorldProtec().contains(world)) {
                            if(!Objects.requireNonNull(world).getPlayers().isEmpty()){
                                Location loc = new Location(world,0,15,0);
                                p.teleport(loc);
                                Utils.ClickExecuteCommand("lobby", Messages.SpectatorMode_SpectatorSuccess, p);
                                new BukkitRunnable() {
                                    public void run() {
                                        p.setGameMode(GameMode.SPECTATOR);
                                    }
                                }.runTaskLater(plugin, 2);
                            }else{
                                p.sendMessage(MasterMessageLocated(p, Messages.SpectatorMode_NotCombatWorld));
                            }
                        }else{
                            p.sendMessage(MasterMessageLocated(p, Messages.Generic_NotAllowed));
                        }
                    }else{
                        p.sendMessage(MasterMessageLocated(p, Messages.SpectatorMode_NotFoundWorld));
                    }
                }else{
                    if(p.getGameMode() == GameMode.SPECTATOR){
                        p.setGameMode(GameMode.SURVIVAL);
                        p.teleport(Objects.requireNonNull(Bukkit.getWorld("lobby")).getSpawnLocation());
                    }else {
                        p.setGameMode(GameMode.SPECTATOR);
                        Utils.ClickExecuteCommand("lobby", Messages.SpectatorMode_SpectatorSuccess, p);
                    }
                }
            }else{
                p.sendMessage(MasterMessageLocated(p, Messages.Generic_InArea));
            }
        }else{
            plugin.messageOnlyPlayer();
        }

        return false;
    }
}
