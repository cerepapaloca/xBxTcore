package Plugin.Commands.OnlyOp;

import Plugin.Messages.Enum.Messages;
import Plugin.xBxTcore;
import net.luckperms.api.node.types.InheritanceNode;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.Nullable;

import static Plugin.Messages.MessageManager.BroadcastMessageBuy;
import static Plugin.Messages.MessageManager.MasterMessageLocated;

public class CommandVip implements CommandExecutor {

    public boolean onCommand(@Nullable CommandSender sender, @Nullable Command cmd, @Nullable String label, String[] args) {
        Player player = Bukkit.getPlayer(args[0]);
        if (player != null) {
            switch (args[1]) {
                case "add" -> xBxTcore.getLuckPerms().getUserManager().modifyUser(player.getUniqueId(), user -> {
                    user.data().add(InheritanceNode.builder("vip").build());
                });
                case "del" -> xBxTcore.getLuckPerms().getUserManager().modifyUser(player.getUniqueId(), user -> {
                    user.data().remove(InheritanceNode.builder("vip").build());
                });
            }
            if (args.length == 3 && args[2].equals("m")) {
                BroadcastMessageBuy("Rango Vip",player,Messages.BuyGeneric);
                player.sendTitle(MasterMessageLocated(player, Messages.BuysTitel),
                        MasterMessageLocated(player, Messages.BuysTitelLower).replace("%compra%",
                                "Rango Vip"), 10 ,60 ,10);
                player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            }
            return true;
        }
        return false;
    }
}

