package Plugin.Vote;

import Plugin.Utils.Utils;
import com.bencodez.votingplugin.events.PlayerVoteEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class VoteListener implements Listener {

    private final JavaPlugin plugin;

    public VoteListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerVote(PlayerVoteEvent event) {
        Utils.RewardVote(event.getPlayer(), true);
    }
}
