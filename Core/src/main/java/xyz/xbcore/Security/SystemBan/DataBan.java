package xyz.xbcore.Security.SystemBan;

import lombok.Getter;

import java.util.UUID;

@Getter
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

}
