package Plugin.Commands.User;

import Plugin.File.FileManagerSection;
import Plugin.Messages.Enum.Messages;
import Plugin.Utils.UtilsMain;
import Plugin.xBxTcore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static Plugin.Utils.Utils.AntiSpam;

public class CommandKitFavorite implements CommandExecutor {

    private final xBxTcore plugin;

    public CommandKitFavorite(xBxTcore plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender,@NotNull Command command,@NotNull String s, String[] args) {
        if(commandSender instanceof Player player){
            if(args.length == 1){
                FileManagerSection.getPlayerFileManager().SaveNameKitFavorite(player.getUniqueId(), args[0]);
            } else if (args.length == 0) {
                LoadKit(player);
                AntiSpam(player, Messages.SpamCommand, plugin);
            }
        }else{
            plugin.messageOnlyPlayer();
        }
        return false;
    }

    public void LoadKit(Player player) {
        FileManagerSection.getPlayerFileManager().loadkitfavorite(player);
    }
}
