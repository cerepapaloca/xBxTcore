package Plugin.Commands.User;

import Plugin.Commands.BaseTabCommand;
import Plugin.Messages.Messages.Messages;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static Plugin.Messages.MessageManager.MasterMessageLocated;

public class CommandSpectator extends BaseTabCommand {

    private final xBxTcore plugin;

    public CommandSpectator(xBxTcore plugin){
        super("spectator",
                "/spectator",
                "xbxtcore.command.user",
                false,
                "Puedes entrar modo espectador y salir ejecutando el comando"
        );
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args){
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
            Utils.sendMessage(sender, Messages.Generic_InArea);
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, String[] args) {
        List<String> nameworlds = new ArrayList<>();
        List<World> worlds = new ArrayList<>(Bukkit.getWorlds());
        worlds.removeAll(xBxTcore.getWorldProtec());
        worlds.removeIf(w -> w.getPlayers().isEmpty());
        for (World w : worlds) {
            nameworlds.add(w.getName());
        }

        if (args.length == 1) {
            String currentArg = args[0].toLowerCase();
            return nameworlds.stream()
                    .filter(name -> name.startsWith(currentArg))
                    .collect(Collectors.toList());
        }

        return null;
    }
}
