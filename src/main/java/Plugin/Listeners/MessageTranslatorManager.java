package Plugin.Listeners;

import Plugin.Enum.Messages;
import Plugin.xBxTcore;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.Bukkit;

public class MessageTranslatorManager {

    private final xBxTcore plugin;

    public MessageTranslatorManager(xBxTcore plugin) {
        this.plugin = plugin;
        registerPacketListener();
    }

    private void registerPacketListener() {
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(
                plugin,
                ListenerPriority.NORMAL,
                PacketType.Play.Server.SYSTEM_CHAT
        ) {
            @Override
            public void onPacketSending(PacketEvent event) {
                //Bukkit.getConsoleSender().sendMessage("Test");
                /*PacketContainer packet = event.getPacket();
                String message = packet.getStrings().read(0);

                if (message.equals("RewardNotYet") ) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(xBxTcore.getMessageManager().MasterMessageLocated(event.getPlayer(),Messages.RewardNotYet));
                }*/
            }
        });
    }
}