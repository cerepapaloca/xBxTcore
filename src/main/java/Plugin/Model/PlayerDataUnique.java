package Plugin.Model;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import java.util.function.BinaryOperator;

public class PlayerDataUnique {

    private final ArrayList<UUID> guestPlayers = new ArrayList<>();
    private final UUID playerUUID;
    private final ArrayList<Player> players = new ArrayList<>();
    private final KitData kitData = new KitData();

    public PlayerDataUnique(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    public ArrayList<Player> getGuestPlayers(Boolean messgas) {
        players.clear();
        for (UUID uuid : guestPlayers) {
            if (Bukkit.getPlayer(uuid) != null) {
                players.add(Bukkit.getPlayer(uuid));
            }else if (messgas){
                Objects.requireNonNull(Bukkit.getPlayer(playerUUID)).sendMessage("Error");
            }
        }
        return players;
    }

    public void addGuestPlayer(Player player) {
        guestPlayers.add(player.getUniqueId());
    }

    public void removeGuestPlayer(Player player) {
        guestPlayers.remove(player.getUniqueId());
    }

    public void clearGuestPlayers() {
        guestPlayers.clear();
    }

    public KitData getKitData() {
        return kitData;
    }

}
