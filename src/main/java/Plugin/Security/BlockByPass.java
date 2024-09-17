package Plugin.Security;

import Plugin.Messages.Enum.Messages;
import Plugin.Messages.MessageManager;
import Plugin.xBxTcore;
import fr.xephi.authme.api.v3.AuthMeApi;
import net.luckperms.api.node.types.InheritanceNode;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

import static Plugin.Messages.MessageManager.*;

public class BlockByPass {

    public static HashMap<UUID, String> passwordList = new HashMap<>();

    public static Boolean checkOpCreative (@NotNull Player player) {
        if(Objects.requireNonNull(player.getAddress()).toString().contains("192.168.1.4"))return false; //La ip privada de mi pc
        if(player.getAddress().toString().contains("127.0.0.1"))return false;

        if(player.getGameMode() == GameMode.CREATIVE){
            player.setGameMode(GameMode.SURVIVAL);
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',  MessageManager.prefixConsole +
                    MessageManager.ColorWarning + " El jugador " + MessageManager.Colorplayer +
                    player.getName() + MessageManager.ColorWarning + " Tenia Creative y fue eliminado"));
            xBxTcore.getLuckPerms().getUserManager().modifyUser(player.getUniqueId(), user -> {
                user.data().add(InheritanceNode.builder("hacker").build());
            });
            player.sendMessage(MasterMessageLocated(player, Messages.Others_OpNotAuthized));
            return true;
        }

        if(player.isOp()){
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',  MessageManager.prefixConsole +
                    MessageManager.ColorWarning + " El jugador " + MessageManager.Colorplayer +
                    player.getName() + MessageManager.ColorWarning + " Tenia Op y fue eliminado"));
            xBxTcore.getLuckPerms().getUserManager().modifyUser(player.getUniqueId(), user -> {
                user.data().add(InheritanceNode.builder("hacker").build());
            });
            player.setOp(false);
            player.sendMessage(MasterMessageLocated(player, Messages.Others_OpNotAuthized));
            return true;
        }
        return false;
    }

    public static boolean checkAuthenticated (@NotNull Player player) {
        if (passwordList.containsKey(player.getUniqueId())) {
            if (!AuthMeApi.getInstance().checkPassword(player.getName(), passwordList.get(player.getUniqueId()))){
                player.kickPlayer(MasterMessageLocated(player, Messages.Kick_NotAuthenticated));
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning + "Se echo al jugador " +
                        Colorplayer + player.getName() + ColorWarning + " por no estar autenticado"));
                return false;
            }
        }else{
            player.kickPlayer(MasterMessageLocated(player, Messages.Kick_NotAuthenticated));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning + "Se echo al jugador " +
                    Colorplayer + player.getName() + ColorWarning + " por no estar autenticado"));
            return false;
        }
        return true;
    }
}
