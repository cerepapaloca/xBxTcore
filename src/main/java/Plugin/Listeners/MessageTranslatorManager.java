package Plugin.Listeners;

import Plugin.Enum.Messages;
import Plugin.xBxTcore;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
                try {
                    PacketContainer packet = event.getPacket();
                    WrappedChatComponent chatComponent = packet.getChatComponents().read(0);
                    String message = normalizeChatMessage(chatComponent.getJson());
                    //Bukkit.getConsoleSender().sendMessage(message);
                    switch (message){
                        case "Crates » givekey" -> event.setCancelled(true);
                        case "Crates » reward" -> {
                            event.setCancelled(true);
                            event.getPlayer().sendMessage(xBxTcore.getMessageManager().MasterMessageLocated(event.getPlayer(), Messages.ClaimReward));
                        }
                        case "Crates » You don't have permissions to do that!" -> {
                            event.setCancelled(true);
                            event.getPlayer().sendMessage(xBxTcore.getMessageManager().MasterMessageLocated(event.getPlayer(), Messages.CrateNotPermission));
                        }
                    }
                }catch (Exception ignored){

                }
            }
        });
    }
    public static String normalizeChatMessage(String jsonMessage) {
        JsonObject jsonObject = JsonParser.parseString(jsonMessage).getAsJsonObject();
        return extractText(jsonObject);
    }

    private static String extractText(JsonObject jsonObject) {
        StringBuilder sb = new StringBuilder();

        if (jsonObject.has("text")) {
            sb.append(jsonObject.get("text").getAsString());
        }

        if (jsonObject.has("extra")) {
            for (JsonElement element : jsonObject.getAsJsonArray("extra")) {
                sb.append(extractText(element.getAsJsonObject()));
            }
        }

        return sb.toString();
    }
}