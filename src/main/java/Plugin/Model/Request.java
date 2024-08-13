package Plugin.Model;

import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class Request {

    private final UUID requesterId;
    private final long expirationTime;
    private final MultiverseWorld nameWold;
    private final ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<Player> acceptPlayers = new ArrayList<>();
    //private final ArrayList<UUID> uuids = new ArrayList<>();
    private final String WorldType;

    public Request(UUID requesterId, long expirationTime, MultiverseWorld nameWold, ArrayList<Player> players, String worldType) {
        this.requesterId = requesterId;
        this.expirationTime = expirationTime;
        this.nameWold = nameWold;
        this.players.addAll(players);
        this.WorldType = worldType;
        acceptPlayers.add(Bukkit.getPlayer(requesterId));
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public UUID getRequesterId() {
        return requesterId;
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public String getWorldType() {
        return WorldType;
    }

    public ArrayList<Player> getAcceptPlayers() {
        return acceptPlayers;
    }
}