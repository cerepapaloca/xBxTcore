package Plugin.Security.SystemBan;

import java.util.UUID;

public class DataBan {

    private final UUID uuid;
    private final String reason;
    private final long unbanTime;
    private final ContextBan context;

    private final long banTime;

    public DataBan (UUID uuid, String reason, long unBan, ContextBan context) {
        this.uuid = uuid;
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

    public long getUnbanTime() {
        return unbanTime;
    }


    public String getReason() {
        return reason;
    }

    public UUID getUuid() {
        return uuid;
    }
}
