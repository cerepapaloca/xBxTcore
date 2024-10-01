package Plugin.Commands.User;

import Plugin.Commands.BaseCommand;
import Plugin.File.FileManagerSection;
import Plugin.Messages.Messages.Messages;
import Plugin.Security.SystemBan.BanManager;
import Plugin.Security.SystemBan.ContextBan;
import Plugin.Utils.Utils;
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

import static Plugin.BoxPvp.ItemsBoxPvp.BonusUpdate.UpdateBonus;
import static Plugin.Messages.MessageManager.MasterMessageLocated;
import static Plugin.Utils.Utils.RewardBoxPvpCheck;

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
                RewardBoxPvpCheck(player.getName());
                player.setGameMode(GameMode.SURVIVAL);
            }else{
                Utils.sendMessage(sender, Messages.Generic_InArea);
            }

        }else{
            Utils.sendMessage(sender, Messages.Generic_OnlyPlayers);
        }
    }
}
