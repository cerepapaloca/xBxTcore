package Plugin.Commands.User;

import Plugin.Messages.Enum.Messages;
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
                                Utils.ClickExecuteCommand("lobby", Messages.SpectatorSuccess, p);
                                new BukkitRunnable() {
                                    public void run() {
                                        p.setGameMode(GameMode.SPECTATOR);
                                    }
                                }.runTaskLater(plugin, 2);
                            }else{
                                p.sendMessage(MasterMessageLocated(p, Messages.NotCombatWorld));
                            }
                        }else{
                            p.sendMessage(MasterMessageLocated(p, Messages.NotAllowed));
                        }
                    }else{
                        p.sendMessage(MasterMessageLocated(p, Messages.NotFoundWorld));
                    }
                }else{
                    p.sendMessage(MasterMessageLocated(p, Messages.SpectatorError));
                }
            }else{
                p.sendMessage(MasterMessageLocated(p, Messages.InArea));
            }
        }else{
            plugin.messageOnlyPlayer();
        }

        return false;
    }
}
