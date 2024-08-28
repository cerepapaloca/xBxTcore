package Plugin.Commands.User;

import Plugin.Messages.Enum.Messages;
import Plugin.xBxTcore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandKitFavorite implements CommandExecutor {

    private final xBxTcore plugin;

    public CommandKitFavorite(xBxTcore plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender,@NotNull Command command,@NotNull String s, String[] args) {
        if(commandSender instanceof Player player){
            if(args.length == 1){
                xBxTcore.getPlayerFileManager().SaveNameKitFavorite(player.getUniqueId(), args[0]);
            } else if (args.length == 0) {
                LoadKit(player);
                xBxTcore.getTools().AntiSpam(player, Messages.SpamCommand);
            }
        }else{
            plugin.messageOnlyPlayer();
        }
        return false;
    }

    public void LoadKit(Player player) {
        xBxTcore.getPlayerFileManager().loadkitfavorite(player);
    }
}
