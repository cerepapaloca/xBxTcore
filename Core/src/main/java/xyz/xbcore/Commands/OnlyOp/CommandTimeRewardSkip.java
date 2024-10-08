package xyz.xbcore.Commands.OnlyOp;

import xyz.xbcommun.Command.BaseCommand;
import xyz.xbcore.File.FileManagerSection;
import xyz.xbcommun.Messages.Messages.Messages;
import xyz.xbcore.Inventory.Enum.PlayerFileTimes;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static xyz.xbcommun.Messages.MessageManager.BroadcastMessageBuy;
import static xyz.xbcommun.Messages.MessageManager.MasterMessageLocated;

public class CommandTimeRewardSkip extends BaseCommand {

    public CommandTimeRewardSkip() {
        super("timerewardskip",
                "/timerewardskip",
                "xbxtcore.command.o",
                true,
                "");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = Bukkit.getPlayer(args[1]);
        String nameCompra = "?";
        if (player != null) {
            switch (args[0]) {
                case "d" -> {
                    FileManagerSection.getPlayerFileManager().SaveTimesRewords(player.getUniqueId(), PlayerFileTimes.daily,1);
                    nameCompra = "Omitir tiempo de la recompensa Diaria";
                }
                case "w" -> {
                    FileManagerSection.getPlayerFileManager().SaveTimesRewords(player.getUniqueId(), PlayerFileTimes.weekly,1);
                    nameCompra = "Omitir tiempo de la recompensa Semanal";
                }
                case "m" -> {
                    FileManagerSection.getPlayerFileManager().SaveTimesRewords(player.getUniqueId(), PlayerFileTimes.monthly,1);
                    nameCompra = "Omitir tiempo de la recompensa Mensual";
                }
            }
            if (args.length == 3 && args[2].equalsIgnoreCase("m")) {
                BroadcastMessageBuy(nameCompra,player, Messages.Reward_BuyGeneric);
                player.sendTitle(MasterMessageLocated(player, Messages.Reward_BuysTitel),
                        MasterMessageLocated(player, Messages.Reward_BuysTitelLower).replace("%compra%",
                                nameCompra), 10 ,60 ,10);
                player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            }
        }
    }
}

