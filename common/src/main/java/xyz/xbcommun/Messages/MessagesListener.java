package xyz.xbcommun.Messages;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import xyz.xbcommun.RegisterManager;

import static xyz.xbcommun.xBxTcommon.plugin;

public class MessagesListener {

    public MessagesListener() {
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
                String message;
                try {
                    PacketContainer packet = event.getPacket();
                    WrappedChatComponent chatComponent = packet.getChatComponents().read(0);
                    message = normalizeChatMessage(chatComponent.getJson());

                } catch (Exception exception) {
                    try {
                        message = extractExtraValue(event.getPacket().getChatComponents().read(0).getJson());
                    }catch (Exception ignored) {
                        message = "";
                    }

                }

                for (PacketMessagesListener listener : RegisterManager.getMessagesListener()){
                    listener.PacketMessagesEvent(event, message);
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

    public static String extractExtraValue(String jsonString) {
        // Parsear el JSON de entrada
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();

        // Obtener el primer elemento del array "extra"
        JsonArray extraArray = jsonObject.getAsJsonArray("extra");
        String extraValue = extraArray.get(0).getAsString();

        // Retornar el valor extraído
        return extraValue;
    }
}
