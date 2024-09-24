package Plugin.Security.SystemBan;

import org.bukkit.entity.Player;

public class DataBan {

    private Player player;
    private String reason;
    private long unbanTime;
    private ContextBan context;

    private final long banTime;

    public DataBan (Player player, String reason, long unBan, ContextBan context) {
        this.player = player;
        this.reason = reason;
        this.unbanTime = unBan;
        this.context = context;
        this.banTime = System.currentTimeMillis();
    }

    public long getBanTime() {
        return banTime;
    }

    public ContextBan getContext() {
        return context;
    }

    public void setContext(ContextBan context) {
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
