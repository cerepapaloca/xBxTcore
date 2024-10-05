package xyz.xbcore.Vote;

import com.bencodez.votingplugin.events.PlayerVoteEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.xbcore.Utils.Utils;

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
