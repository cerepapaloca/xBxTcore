package Plugin.Security;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import static Plugin.Messages.MessageManager.ColorError;
import static Plugin.Messages.MessageManager.prefixKick;

public class AntiTwoPlayer implements Listener {

    @EventHandler
    public void PlayerPreLoginEvent(AsyncPlayerPreLoginEvent event) {
        for (Player player : Bukkit.getOnlinePlayers()){
            if (player.getName().equals(event.getName())){
                event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
                event.setKickMessage(prefixKick + ColorError + "Ya estas Conectado");
            }
        }
    }
}
