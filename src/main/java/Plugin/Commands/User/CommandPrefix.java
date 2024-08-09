package Plugin.Commands.User;

import Plugin.Model.Messages;
import Plugin.Utils.Tools;
import Plugin.xBxTcore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CommandPrefix implements CommandExecutor {

    private final xBxTcore plugin;
    private final List<String> bannedWords = Arrays.asList("owner", "admin", "helper", "0wner", "ceres", "dueño", "moderador", "moderator", "mod", "m0d","onwer","0nwer");

    public CommandPrefix(xBxTcore plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@Nullable CommandSender commandSender,@Nullable Command command,@Nullable String s, String[] args) {
        if(commandSender instanceof Player player){
            if (player.hasPermission("xBxTpvp.vote")){
                if (args.length == 1){
                    String prefixName = ChatColor.translateAlternateColorCodes('&', "&r[&r" + args[0] + "&r]&r ");
                    if (ChatColor.stripColor(prefixName).length() < 17){
                        String normalizedMessage = ChatColor.stripColor(prefixName).toLowerCase().replaceAll("\\s+", "");
                        for (String bannedWord : bannedWords) {
                            if (normalizedMessage.contains(bannedWord.toLowerCase())) {
                                player.sendMessage(xBxTcore.getMessageManager().MasterMessage(player, Messages.WordNotAllowed));
                                return false;
                            }
                        }
                        Objects.requireNonNull(xBxTcore.getTabAPI().getNameTagManager()).setPrefix(Objects.requireNonNull(xBxTcore.getTabAPI().getPlayer(player.getUniqueId())), prefixName);
                        Objects.requireNonNull(xBxTcore.getTabAPI().getTabListFormatManager()).setPrefix(Objects.requireNonNull(xBxTcore.getTabAPI().getPlayer(player.getUniqueId())), prefixName);
                        xBxTcore.getPlayerFileManager().SavePrefix(player.getUniqueId(), prefixName);
                        player.sendMessage(xBxTcore.getMessageManager().MasterMessage(player, Messages.PrefixSuccess));
                        xBxTcore.getTools().AntiSpam(player, Messages.SpamCommand);
                        return true;
                    }else{
                        player.sendMessage(xBxTcore.getMessageManager().MasterMessage(player, Messages.TooLengthName));
                    }
                }else if (args.length == 0){
                    Objects.requireNonNull(xBxTcore.getTabAPI().getNameTagManager()).setPrefix(Objects.requireNonNull(xBxTcore.getTabAPI().getPlayer(player.getUniqueId())), "");
                    Objects.requireNonNull(xBxTcore.getTabAPI().getTabListFormatManager()).setPrefix(Objects.requireNonNull(xBxTcore.getTabAPI().getPlayer(player.getUniqueId())), "");
                    xBxTcore.getPlayerFileManager().SavePrefix(player.getUniqueId(), "");
                    player.sendMessage(xBxTcore.getMessageManager().MasterMessage(player, Messages.PrefixClear));
                }
            }else{
                Tools.ClickExecuteCommand("vote", Messages.NotVote, player);
            }
        }else{
            plugin.messageOnlyPlayer();
        }
        return false;
    }

}
