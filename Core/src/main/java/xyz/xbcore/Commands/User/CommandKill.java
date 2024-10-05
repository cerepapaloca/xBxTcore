package xyz.xbcore.Commands.User;

import xyz.xbcommun.Command.BaseCommand;
import xyz.xbcommun.Messages.Messages.Messages;
import xyz.xbcommun.Utils.UtilsGlobal;
import xyz.xbcore.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
                UtilsGlobal.sendMessage(sender, Messages.Generic_InArea);
            }

        }else{
            UtilsGlobal.sendMessage(sender, Messages.Generic_OnlyPlayers);
        }
    }
}
