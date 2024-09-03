package Plugin.Messages;

import Plugin.Messages.Enum.Messages;
import Plugin.xBxTcore;
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
import org.bukkit.ChatColor;
import org.bukkit.Location;

import static Plugin.Messages.MessageManager.*;

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
                String message;
                try {
                    PacketContainer packet = event.getPacket();
                    WrappedChatComponent chatComponent = packet.getChatComponents().read(0);
                    message = normalizeChatMessage(chatComponent.getJson());
                    //Bukkit.getConsoleSender().sendMessage(message);

                } catch (Exception exception) {
                    try {
                        message = extractExtraValue(event.getPacket().getChatComponents().read(0).getJson());
                    }catch (Exception ignored) {
                        message = "";
                    }

                }
                replaceMessages(message, event);
            }
        });
    }

    public void replaceMessages(String message, PacketEvent event) {
        switch (message) {
            case "Crates » givekey" -> event.setCancelled(true);
            case "reward" -> {
                event.setCancelled(true);
                event.getPlayer().sendMessage(MasterMessageLocated(event.getPlayer(), Messages.ClaimReward));
            }
            case "Crates » You don't have permissions to do that!" -> {
                event.setCancelled(true);
                event.getPlayer().sendMessage(MasterMessageLocated(event.getPlayer(), Messages.CrateNotPermission));
            }
            default -> {
                if (message.startsWith("#%#")) {
                    message = message.replace("#%#", "");
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(MasterMessageLocated(event.getPlayer(), Messages.valueOf(message)));
                    if (message.equals("Login_Success")){
                        event.getPlayer().setGravity(true);
                        event.getPlayer().teleport(new Location(Bukkit.getWorld("lobby"), 0 , 68, 0));
                        event.getPlayer().getInventory().clear();
                        Bukkit.getConsoleSender().sendMessage( ChatColor.translateAlternateColorCodes('&',prefixConsole + ColorSuccess + "El jugador " + Colorplayer + event.getPlayer().getName() + ColorSuccess + " a iniciado sesion exitosamente"));
                    }
                    if (message.equals("Registration_Success")){
                        event.getPlayer().setGravity(true);
                        event.getPlayer().teleport(new Location(Bukkit.getWorld("lobby"), 0 , 68, 0));
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "authme forcelogin " + event.getPlayer().getName());
                        Bukkit.getConsoleSender().sendMessage( ChatColor.translateAlternateColorCodes('&',prefixConsole + ColorSuccess + "El jugador " + Colorplayer + event.getPlayer().getName() + ColorSuccess + " se registrado exitosamente"));
                    }
                }
            }
        }
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