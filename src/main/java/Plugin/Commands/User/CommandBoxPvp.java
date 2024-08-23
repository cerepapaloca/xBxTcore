package Plugin.Commands.User;

import Plugin.Enum.Messages;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CommandBoxPvp implements CommandExecutor {

    private final xBxTcore plugin;

    public CommandBoxPvp(xBxTcore plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        if(commandSender instanceof Player player){
            if (xBxTcore.getWorldProtec().contains(player.getWorld()) || player.getGameMode().equals(GameMode.SPECTATOR) || player.isOp() || player.getWorld().getPlayers().size() == 1) {
                if (!player.getWorld().getName().equals("lobby") && !player.getWorld().getName().equals("boxpvp")) {
                    player.setLevel(0);
                    player.setExp(0);
                }
                if (!player.getWorld().getName().equals("boxpvp")) {
                    player.getInventory().clear();
                    xBxTcore.getPlayerFileManager().loadInventoryBoxPvp(player);
                }
                player.teleport(Objects.requireNonNull(Bukkit.getWorld("boxpvp")).getSpawnLocation());
                xBxTcore.getArmorBonusListener().UpdateBonus(player);
                player.setGameMode(GameMode.SURVIVAL);
            }else{
                player.sendMessage(xBxTcore.getMessageManager().MasterMessage(player, Messages.InArea));
            }

        }else{
            plugin.messageOnlyPlayer();
        }
        return false;
    }

}
