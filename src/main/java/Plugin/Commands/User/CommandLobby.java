package Plugin.Commands.User;

import Plugin.Commands.BaseCommand;
import Plugin.File.FileManagerSection;
import Plugin.Messages.Messages.Messages;
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
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import static Plugin.BoxPvp.ItemsBoxPvp.BonusUpdate.UpdateBonus;
import static Plugin.Messages.MessageManager.MasterMessageLocated;

public class CommandLobby extends BaseCommand {

    public CommandLobby(){
        super(new String[]{"lobby", "spawn"},
                "/lobby",
                "xbxtcore.command.user",
                false,
                "regresas al lobby del servidor");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof Player player){
            if (xBxTcore.getWorldProtec().contains(player.getWorld()) || player.getGameMode().equals(GameMode.SPECTATOR) || player.isOp() || player.getWorld().getPlayers().size() == 1) {
                if (!player.getWorld().getName().equals("lobby") && !player.getWorld().getName().equals("boxpvp")) {
                    player.setLevel(0);
                    player.setExp(0);
                }
                if (player.getWorld().getName().equals("boxpvp")) {
                    player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                    FileManagerSection.getPlayerFileManager().SaveInventoryBoxPvp(player.getUniqueId(), Utils.getItensInvetory(player));
                    player.getInventory().clear();
                    UpdateBonus(player);
                }
                player.teleport(new Location(Bukkit.getWorld("lobby"), 0 , 68, 0));
                player.setGameMode(GameMode.SURVIVAL);
            }else{
                Utils.sendMessage(sender, Messages.Generic_InArea);
            }

        }else{
            Utils.sendMessage(sender, Messages.Generic_OnlyPlayers);
        }
    }
}
