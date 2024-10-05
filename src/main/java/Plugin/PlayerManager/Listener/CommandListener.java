package Plugin.PlayerManager.Listener;

import Plugin.Commands.BaseCommand;
import Plugin.Commands.CommandSection;
import Plugin.Messages.Messages.Messages;
import fr.xephi.authme.api.v3.AuthMeApi;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static Plugin.Messages.MessageManager.MasterMessageLocated;
import static Plugin.Security.BlockByPass.*;

public class CommandListener implements Listener {

    private final HashSet<String> AlloedCommands = new HashSet<>();
    private final HashMap<String, String> AlloedCommandsWithPermissions = new HashMap<>();

    public CommandListener(){
        AlloedCommands.add("login");
        AlloedCommands.add("log");
        AlloedCommands.add("register");
        AlloedCommands.add("team");
        AlloedCommands.add("w");
        AlloedCommands.add("r");
        AlloedCommands.add("tell");
        AlloedCommands.add("msg");
        AlloedCommandsWithPermissions.put("skin", "xbxtcore.vip");
        for (BaseCommand command : CommandSection.getCommandHandler().getCommands()){
            AlloedCommands.addAll(Arrays.asList(command.getName()));
        }
        //////////////////////////////////////
    }

    @EventHandler
    public void PlayerCommandPreprocess(@NotNull PlayerCommandPreprocessEvent event) {
        if (!checkOpCreative(event.getPlayer())){
            if(!event.getPlayer().isOp()){
                String command = event.getMessage().split(" ")[0].substring(1).toLowerCase();

                if (!AuthMeApi.getInstance().isAuthenticated(event.getPlayer()) &&(command.equals("login") || command.equals("log") || command.equals("register") || command.equals("reg"))) {
                    if (!(event.getMessage().split(" ").length >= 1))return;

                    String password = event.getMessage().split(" ")[1];
                    if (password == null)return;
                    passwordList.put(event.getPlayer().getUniqueId(), password);
                }else{
                    if(!checkAuthenticated(event.getPlayer())){
                        event.setCancelled(true);
                        return;
                    }
                }

                if(AlloedCommandsWithPermissions.containsKey(command)){
                    if (event.getPlayer().hasPermission(AlloedCommandsWithPermissions.get(command))){
                        return;
                    }
                }

                if(!AlloedCommands.contains(command)){
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(MasterMessageLocated(event.getPlayer(), Messages.Generic_NotAllowed));
                }
            }
        }else{
            event.setCancelled(true);
        }
    }
}
