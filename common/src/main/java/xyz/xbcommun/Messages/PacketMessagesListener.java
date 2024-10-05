package xyz.xbcommun.Messages;

import com.comphenix.protocol.events.PacketEvent;

public interface PacketMessagesListener {
    void PacketMessagesEvent(PacketEvent event, String message);
}
