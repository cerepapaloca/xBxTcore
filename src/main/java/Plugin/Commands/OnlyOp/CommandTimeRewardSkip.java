package Plugin.Commands.OnlyOp;

import Plugin.Enum.PlayerFileTimes;
import Plugin.xBxTcore;
import net.luckperms.api.node.types.InheritanceNode;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.Nullable;

import static Plugin.xBxTcore.getPlayerFileManager;

public class CommandTimeRewardSkip implements CommandExecutor {

    public boolean onCommand(@Nullable CommandSender sender, @Nullable Command cmd, @Nullable String label, String[] args) {
        Player player = Bukkit.getPlayer(args[1]);
        if (player != null) {
            switch (args[0]) {
                case "d" -> getPlayerFileManager().SaveTimesRewords(player.getUniqueId(), PlayerFileTimes.daily,1);
                case "w" -> getPlayerFileManager().SaveTimesRewords(player.getUniqueId(), PlayerFileTimes.weekly,1);
                case "m" -> getPlayerFileManager().SaveTimesRewords(player.getUniqueId(), PlayerFileTimes.monthly,1);
            }
            return true;
        }
        return false;
    }
}

