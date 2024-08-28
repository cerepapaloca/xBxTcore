package Plugin.Commands.User;

import Plugin.Managers.ItemManage;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static Plugin.Managers.MessageManager.prefix;

public class CommandHelp implements CommandExecutor {

    private final xBxTcore plugin;

    public CommandHelp(xBxTcore plugin){
        this.plugin = plugin;
    }
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command cmd,@NotNull String label, String[] args) {
        if(sender instanceof Player p){
            if (p.getLocale().contains("es")){
                Utils.additem(p, ItemManage.wikis.get(1));
            } else{
                Utils.additem(p, ItemManage.wikis.get(0));
            }

        }else{
            plugin.messageOnlyPlayer();
        }

        return false;
    }
}
