package Plugin.PlayerManager.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataGLobal {
    private final Map<UUID, Integer> playerKills;
    public PlayerDataGLobal() {
        playerKills = new HashMap<>();
    }

    public void addKill(UUID playerUUID) {
        playerKills.put(playerUUID, playerKills.getOrDefault(playerUUID, 0) + 1);
    }

    public int getKills(UUID playerUUID) {
        return playerKills.getOrDefault(playerUUID, 0);
    }

    public void resetKills(UUID playerUUID) {
        playerKills.put(playerUUID, 0);
    }

    public Map<UUID, Integer> getPlayerKills() {
        return playerKills;
    }

}
