package Plugin.Commands.User;

import Plugin.Messages.Enum.Messages;
import Plugin.Security.BanManager;
import Plugin.Security.SecuritySection;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static Plugin.BoxPvp.ItemsBoxPvp.Listener.ArmorBonusListener.UpdateBonus;
import static Plugin.File.FileManagerSection.getPlayerFileManager;
import static Plugin.Messages.MessageManager.MasterMessageLocated;

public class CommandBoxPvp implements CommandExecutor {

    private final xBxTcore plugin;

    public CommandBoxPvp(xBxTcore plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        if(commandSender instanceof Player player){
            if (xBxTcore.getWorldProtec().contains(player.getWorld()) || player.getGameMode().equals(GameMode.SPECTATOR) || player.isOp() || player.getWorld().getPlayers().size() == 1) {
                if (!BanManager.checkBanPlayer(Objects.requireNonNull(player.getAddress()).getAddress(), player, "boxpvp"))return false;
                if (!player.getWorld().getName().equals("lobby") && !player.getWorld().getName().equals("boxpvp")) {
                    player.setLevel(0);
                    player.setExp(0);
                }
                if (!player.getWorld().getName().equals("boxpvp")) {
                    player.getInventory().clear();
                    getPlayerFileManager().loadInventoryBoxPvp(player);
                }
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 1));
                player.teleport(Objects.requireNonNull(Bukkit.getWorld("boxpvp")).getSpawnLocation());
                UpdateBonus(player);
                player.setGameMode(GameMode.SURVIVAL);
            }else{
                player.sendMessage(MasterMessageLocated(player, Messages.Generic_InArea));
            }

        }else{
            plugin.messageOnlyPlayer();
        }
        return false;
    }

}
