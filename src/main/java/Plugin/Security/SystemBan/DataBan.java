package Plugin.Security.SystemBan;

import org.bukkit.entity.Player;

public class DataBan {

    private Player player;
    private String reason;
    private long unbanTime;
    private String context;

    private final long banTime;

    public DataBan (Player player, String reason, long unBan, String context) {
        this.player = player;
        this.reason = reason;
        this.unbanTime = unBan;
        this.context = context;
        this.banTime = System.currentTimeMillis();
    }

    public long getBanTime() {
        return banTime;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public long getUnbanTime() {
        return unbanTime;
    }

    public void setUnbanTime(long unbanTime) {
        this.unbanTime = unbanTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
