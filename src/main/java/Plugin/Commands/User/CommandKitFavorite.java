package Plugin.Commands.User;

import Plugin.File.FileManagerSection;
import Plugin.Messages.Enum.Messages;
import Plugin.Messages.MessageManager;
import Plugin.Messages.MessageSection;
import Plugin.Utils.UtilsMain;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
            if (player.getWorld().getName().equals(xBxTcore.worldBoxPvp)) {
                player.sendMessage(MessageManager.MasterMessageLocated(player, Messages.InArea));
                return false;
            }

            if(args.length == 1){
                FileManagerSection.getPlayerFileManager().SaveNameKitFavorite(player.getUniqueId(), args[0]);
                return true;
            } else if (args.length == 0) {
                LoadKit(player);
                AntiSpam(player, Messages.SpamCommand, plugin);
                return true;
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
