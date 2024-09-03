package Plugin.Security;

import Plugin.Messages.Enum.Messages;
import Plugin.Messages.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import static Plugin.Messages.MessageManager.MasterMessageLocated;

public class BlockByPass {

    public static void checkOpCreative (Player player) {
        if(player.getAddress().toString().contains("192.168.1.4"))return; //La ip privada de mi pc
        if(player.getAddress().toString().contains("127.0.0.1"))return;

        if(player.getGameMode() == GameMode.CREATIVE){
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',  MessageManager.prefixConsole +
                    MessageManager.ColorWarning + " El jugador " + MessageManager.Colorplayer +
                    player.getName() + MessageManager.ColorWarning + " Tenia Creative y fue eliminado\n"));
        }

        if(player.isOp()) Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',  MessageManager.prefixConsole +
                MessageManager.ColorWarning + " El jugador " + MessageManager.Colorplayer +
                        player.getName() + MessageManager.ColorWarning + " Tenia Op y fue eliminado\n"));
        player.setOp(false);

        player.sendMessage(MasterMessageLocated(player, Messages.OpNotAuthized));
    }

}
