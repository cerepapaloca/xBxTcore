package Plugin.Commands.OnlyOp;

import Plugin.Commands.BaseCommand;
import Plugin.Messages.Messages.Messages;
import Plugin.xBxTcore;
import net.luckperms.api.node.types.InheritanceNode;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.TimeUnit;

import static Plugin.Messages.MessageManager.BroadcastMessageBuy;
import static Plugin.Messages.MessageManager.MasterMessageLocated;

public class CommandVip extends BaseCommand {

    public CommandVip() {
        super ("vip",
                "/vip",
                "xbxtcore.command.vip",
                true,
                "le da vip como si hubiera comprado");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = Bukkit.getPlayer(args[0]);
        if (player != null) {
            switch (args[1]) {
                case "add" -> xBxTcore.getLuckPerms().getUserManager().modifyUser(player.getUniqueId(),
                        user -> user.data().add(InheritanceNode.builder("vip").build()));
                case "del" -> xBxTcore.getLuckPerms().getUserManager().modifyUser(player.getUniqueId(),
                        user -> user.data().remove(InheritanceNode.builder("vip").build()));
                case "temp" -> xBxTcore.getLuckPerms().getUserManager().modifyUser(player.getUniqueId(),
                        user -> user.data().add(InheritanceNode.builder("vip").expiry(2, TimeUnit.DAYS).build()));
            }
            if (args.length == 3 && args[2].equals("m")) {
                BroadcastMessageBuy("Rango Vip",player,Messages.Reward_BuyGeneric);
                player.sendTitle(MasterMessageLocated(player, Messages.Reward_BuysTitel),
                        MasterMessageLocated(player, Messages.Reward_BuysTitelLower).replace("%compra%",
                                "Rango Vip"), 10 ,60 ,10);
                player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            }
        }
    }
}

