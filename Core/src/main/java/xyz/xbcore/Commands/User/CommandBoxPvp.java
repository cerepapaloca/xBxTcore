package xyz.xbcore.Commands.User;

import xyz.xbcommun.Command.BaseCommand;
import xyz.xbcore.File.FileManagerSection;
import xyz.xbcommun.Messages.Messages.Messages;
import xyz.xbcore.Security.SystemBan.BanManager;
import xyz.xbcore.Security.SystemBan.ContextBan;
import xyz.xbcommun.Utils.UtilsGlobal;
import xyz.xbcore.Utils.Utils;
import xyz.xbcore.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

import static xyz.xbcore.BoxPvp.ItemsBoxPvp.BonusUpdate.UpdateBonus;

public class CommandBoxPvp extends BaseCommand {

    public CommandBoxPvp(){
        super("boxpvp",
                "/boxpvp ",
                "xbxtcore.command.user",
                false,
                "viajas al box pvp");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof Player player){
            if (xBxTcore.getWorldProtec().contains(player.getWorld()) || player.getGameMode().equals(GameMode.SPECTATOR) || player.isOp() || player.getWorld().getPlayers().size() == 1) {
                if (BanManager.checkBanPlayer(player, ContextBan.BOX_PVP) != null)return;

                if (!player.getWorld().getName().equals("lobby") && !player.getWorld().getName().equals("boxpvp")) {
                    player.setLevel(0);
                    player.setExp(0);
                }
                if (!player.getWorld().getName().equals("boxpvp")) {
                    player.getInventory().clear();
                    FileManagerSection.getPlayerFileManager().loadInventoryBoxPvp(player);
                }
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 1));
                player.teleport(Objects.requireNonNull(Bukkit.getWorld("boxpvp")).getSpawnLocation());
                UpdateBonus(player);
                Utils.RewardBoxPvpCheck(player.getName());
                player.setGameMode(GameMode.SURVIVAL);
            }else{
                UtilsGlobal.sendMessage(sender, Messages.Generic_InArea);
            }

        }else{
            UtilsGlobal.sendMessage(sender, Messages.Generic_OnlyPlayers);
        }
    }
}
