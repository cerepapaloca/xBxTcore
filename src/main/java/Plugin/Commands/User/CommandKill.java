package Plugin.Commands.User;

import Plugin.Commands.BaseCommand;
import Plugin.Messages.Messages.Messages;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import static Plugin.Messages.MessageManager.MasterMessageLocated;

public class CommandKill extends BaseCommand {

    public CommandKill(){
        super("kill",
                "/kill",
                "xbxtcore.command.user",
                false,
                "te matas a ti mismo");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof Player p){
            if (xBxTcore.getWorldProtec().contains(p.getWorld())){
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecraft:kill " + p.getName());
            }else{
                Utils.sendMessage(sender, Messages.Generic_InArea);
            }

        }else{
            Utils.sendMessage(sender, Messages.Generic_OnlyPlayers);
        }
    }
}
