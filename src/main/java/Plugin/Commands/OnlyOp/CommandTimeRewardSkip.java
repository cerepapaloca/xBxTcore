package Plugin.Commands.OnlyOp;

import Plugin.Messages.Enum.Messages;
import Plugin.Inventory.Enum.PlayerFileTimes;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.Nullable;

import static Plugin.File.FileManagerSection.getPlayerFileManager;
import static Plugin.Messages.MessageManager.BroadcastMessageBuy;
import static Plugin.Messages.MessageManager.MasterMessageLocated;

public class CommandTimeRewardSkip implements CommandExecutor {

    public boolean onCommand(@Nullable CommandSender sender, @Nullable Command cmd, @Nullable String label, String[] args) {
        Player player = Bukkit.getPlayer(args[1]);
        String nameCompra = "?";
        if (player != null) {
            switch (args[0]) {
                case "d" -> {
                    getPlayerFileManager().SaveTimesRewords(player.getUniqueId(), PlayerFileTimes.daily,1);
                    nameCompra = "Omitir tiempo de la recompensa Diaria";
                }
                case "w" -> {
                    getPlayerFileManager().SaveTimesRewords(player.getUniqueId(), PlayerFileTimes.weekly,1);
                    nameCompra = "Omitir tiempo de la recompensa Semanal";
                }
                case "m" -> {
                    getPlayerFileManager().SaveTimesRewords(player.getUniqueId(), PlayerFileTimes.monthly,1);
                    nameCompra = "Omitir tiempo de la recompensa Mensual";
                }
            }
            if (args.length == 3 && args[2].equalsIgnoreCase("m")) {
                BroadcastMessageBuy(nameCompra,player, Messages.BuyGeneric);
                player.sendTitle(MasterMessageLocated(player, Messages.BuysTitel),
                        MasterMessageLocated(player, Messages.BuysTitelLower).replace("%compra%",
                                nameCompra), 10 ,60 ,10);
                player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            }
            return true;
        }
        return false;
    }
}

