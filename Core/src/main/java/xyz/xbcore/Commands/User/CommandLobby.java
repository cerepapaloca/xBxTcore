package xyz.xbcore.Commands.User;

import xyz.xbcommun.Command.BaseCommand;
import xyz.xbcore.File.FileManagerSection;
import xyz.xbcore.Messages.Messages.Messages;
import xyz.xbcore.Utils.Utils;
import xyz.xbcore.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import static xyz.xbcore.BoxPvp.ItemsBoxPvp.BonusUpdate.UpdateBonus;

public class CommandLobby extends BaseCommand {

    public CommandLobby(){
        super(new String[]{"lobby", "spawn", "sw"},
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
