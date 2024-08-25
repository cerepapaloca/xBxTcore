package Plugin.Commands.OnlyOp;

import Plugin.xBxTcore;
import net.luckperms.api.node.types.InheritanceNode;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.Nullable;

public class CommandAddVip implements CommandExecutor {

    public boolean onCommand(@Nullable CommandSender sender, @Nullable Command cmd, @Nullable String label, String[] args) {
        Player player = Bukkit.getPlayer(args[0]);
        if (player != null) {
            xBxTcore.getLuckPerms().getUserManager().modifyUser(player.getUniqueId(), user -> {
                user.data().add(InheritanceNode.builder("vip").build());
            });
            return true;
        }
        return false;
    }
}

