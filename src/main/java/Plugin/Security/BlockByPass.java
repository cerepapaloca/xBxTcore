package Plugin.Security;

import Plugin.Messages.Enum.Messages;
import Plugin.Messages.MessageManager;
import fr.xephi.authme.api.v3.AuthMeApi;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

import static Plugin.Messages.MessageManager.MasterMessageLocated;

public class BlockByPass {

    public static HashMap<UUID, String> passwordList = new HashMap<>();

    public static void checkOpCreative (@NotNull Player player) {
        if(player.getAddress().toString().contains("192.168.1.4"))return; //La ip privada de mi pc
        if(player.getAddress().toString().contains("127.0.0.1"))return;

        if(player.getGameMode() == GameMode.CREATIVE){
            player.setGameMode(GameMode.SURVIVAL);
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',  MessageManager.prefixConsole +
                    MessageManager.ColorWarning + " El jugador " + MessageManager.Colorplayer +
                    player.getName() + MessageManager.ColorWarning + " Tenia Creative y fue eliminado"));
            player.sendMessage(MasterMessageLocated(player, Messages.Others_OpNotAuthized));
        }

        if(player.isOp()){
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',  MessageManager.prefixConsole +
                    MessageManager.ColorWarning + " El jugador " + MessageManager.Colorplayer +
                    player.getName() + MessageManager.ColorWarning + " Tenia Op y fue eliminado"));
            player.setOp(false);
            player.sendMessage(MasterMessageLocated(player, Messages.Others_OpNotAuthized));
        }

    }

    public static void checkAuthenticated (@NotNull Player player) {
        if (passwordList.containsKey(player.getUniqueId())) {
            if (!AuthMeApi.getInstance().checkPassword(player.getName(), passwordList.get(player.getUniqueId()))){
                player.kickPlayer("No Esta Autenticado");
            }
        }else{
            player.kickPlayer("No Esta Autenticado");
        }
    }
}
