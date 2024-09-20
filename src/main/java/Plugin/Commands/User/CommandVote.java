package Plugin.Commands.User;

import Plugin.Messages.Enum.Messages;
import Plugin.xBxTcore;
import fr.xephi.authme.api.v3.AuthMeApi;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import static Plugin.Messages.MessageManager.ColorLink;
import static Plugin.Messages.MessageManager.MasterMessageLocated;

public class CommandVote implements CommandExecutor {

    private final xBxTcore plugin;

    public CommandVote(xBxTcore plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@Nullable CommandSender commandSender,@Nullable Command command,@Nullable String s, String[] strings) {
        if(commandSender instanceof Player player){
            player.sendMessage(MasterMessageLocated(player, Messages.Others_Vote));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', ColorLink + "https://minecraft-mp.com/server-s334744"));
            //player.sendMessage(ChatColor.translateAlternateColorCodes('&', ColorLink + "https://best-minecraft-servers.co/server-xbxtpvp.24633"));
        }else{
            plugin.messageOnlyPlayer();
        }
        return false;
    }

}
