package Plugin.Vote;

import Plugin.Utils.Utils;
import com.vexsoftware.votifier.model.VotifierEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class VoteListener implements Listener {
    @EventHandler
    public void Vote(VotifierEvent event){
        Utils.RewardVote(event.getVote().getUsername(), true);
    }
}
