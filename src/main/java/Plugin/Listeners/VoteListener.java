package Plugin.Listeners;

import Plugin.Enum.Messages;
import Plugin.Utils.Utils;
import com.vexsoftware.votifier.model.VotifierEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class VoteListener implements Listener {
    @EventHandler
    public void Vote(VotifierEvent event){
        Utils.RewardVote(event.getVote().getUsername(), true);
        Player player = Bukkit.getPlayer(event.getVote().getUsername());
        if (player != null){
            Utils.ClickExecuteCommand("prefix", Messages.Voted, player);
        }
    }
}
