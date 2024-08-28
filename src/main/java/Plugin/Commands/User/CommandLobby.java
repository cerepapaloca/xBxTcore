package Plugin.Commands.User;

import Plugin.Messages.Enum.Messages;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class CommandLobby implements CommandExecutor {

    private final xBxTcore plugin;

    public CommandLobby(xBxTcore plugin){
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
                if (player.getWorld().getName().equals("boxpvp")) {
                    player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                    xBxTcore.getPlayerFileManager().SaveInventoryBoxPvp(player.getUniqueId(), Utils.getItensInvetory(player));
                    player.getInventory().clear();
                }
                player.teleport(new Location(Bukkit.getWorld("lobby"), 0 , 68, 0));
                player.setGameMode(GameMode.SURVIVAL);
            }else{
                player.sendMessage(xBxTcore.getMessageManager().MasterMessageLocated(player, Messages.InArea));
            }

        }else{
            plugin.messageOnlyPlayer();
        }
        return false;
    }

}
