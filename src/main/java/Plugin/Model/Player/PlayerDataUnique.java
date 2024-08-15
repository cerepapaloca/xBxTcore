package Plugin.Model.Player;

import Plugin.Model.KitData;
import Plugin.Enum.MapsDuel;
import Plugin.Enum.Messages;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

import static Plugin.Managers.MessageManager.ColorWarning;

public class PlayerDataUnique {

    private final ArrayList<UUID> guestPlayers = new ArrayList<>();
    private final UUID playerUUID;
    private final ArrayList<Player> players = new ArrayList<>();
    private KitData kitData = new KitData();
    private int timeDuel = 0;
    private Boolean Timelimit = false;
    private int indexMap = 0;

    public PlayerDataUnique(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    public ArrayList<Player> getGuestPlayers(Boolean messgas) {
        players.clear();
        players.add(Bukkit.getPlayer(playerUUID));
        for (UUID uuid : guestPlayers) {
            if (Bukkit.getPlayer(uuid) != null) {
                players.add(Bukkit.getPlayer(uuid));
            }else if (messgas){
                Player p = Bukkit.getPlayer(playerUUID);
                if (p != null) {
                    p.sendMessage(xBxTcore.getMessageManager().MasterMessage(p, Messages.WarningGetGuestPlayers));
                }
                Bukkit.getConsoleSender().sendMessage(ColorWarning + "Hubo un error al obtener el jugador: " + uuid);
            } else {
                Bukkit.getConsoleSender().sendMessage(ColorWarning + "Hubo un error al obtener el jugador: " + uuid);
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

    public int getTimeDuel() {
        return timeDuel;
    }

    public void setTimeDuel(int timeDuel) {
        this.timeDuel = timeDuel;
    }

    public KitData getKitData() {
        if (kitData == null) {
            kitData = new KitData();
        }

        return kitData;
    }

    public Boolean getTimelimit() {
        return Objects.requireNonNullElse(Timelimit, false);
    }

    public void setTimelimit(Boolean timelimit) {
        Timelimit = timelimit;
    }

    public void clearKitdata() {
        kitData = null;
    }

    public void setIndexMap(int indexMap) {
        this.indexMap = indexMap;
    }

    public int getIndexMap() {
        return indexMap;
    }

    public String getNameWolrd() {
        ArrayList<String> namemaps = new ArrayList<>();
        for (MapsDuel map : MapsDuel.values()) {
            namemaps.add(map.name());
        }
        return namemaps.get(indexMap);
    }
}
