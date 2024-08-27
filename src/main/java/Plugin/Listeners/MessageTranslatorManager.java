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
import com.vexsoftware.votifier.model.VotifierEvent;
import org.bukkit.Bukkit;

import java.security.cert.CertPathValidatorException;

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

                }catch (Exception exception){
                    message = event.getPacket().getChatComponents().read(0).getJson().replace("\"","");
                }
                replaceMessages(message, event);
            }
        });
    }

    public void replaceMessages(String message, PacketEvent event){
        switch (message){
            case "Crates » givekey" -> event.setCancelled(true);
            case "reward" -> {
                event.setCancelled(true);
                event.getPlayer().sendMessage(xBxTcore.getMessageManager().MasterMessageLocated(event.getPlayer(), Messages.ClaimReward));
            }
            case "Crates » You don't have permissions to do that!" -> {
                event.setCancelled(true);
                event.getPlayer().sendMessage(xBxTcore.getMessageManager().MasterMessageLocated(event.getPlayer(), Messages.CrateNotPermission));
            }

            case "%PendingLogin" ->{
                event.setCancelled(true);
                event.getPlayer().sendMessage(xBxTcore.getMessageManager().MasterMessageLocated(event.getPlayer(), Messages.PendingLogin));
            }
            case "%PendingRegister" -> {
                event.setCancelled(true);
                event.getPlayer().sendMessage(xBxTcore.getMessageManager().MasterMessageLocated(event.getPlayer(), Messages.PendingRegister));
            }
            case "%SuccessLogin" -> {
                event.setCancelled(true);
                event.getPlayer().sendMessage(xBxTcore.getMessageManager().MasterMessageLocated(event.getPlayer(), Messages.SuccessLogin));
            }
            case "%SuccessRegister" -> {
                event.setCancelled(true);
                event.getPlayer().sendMessage(xBxTcore.getMessageManager().MasterMessageLocated(event.getPlayer(), Messages.SuccessRegister));
            }
            case "%SuccessSession" -> {
                event.setCancelled(true);
                event.getPlayer().sendMessage(xBxTcore.getMessageManager().MasterMessageLocated(event.getPlayer(), Messages.SuccessSession));
            }
            case "%SuccessPremium" -> {
                event.setCancelled(true);
                event.getPlayer().sendMessage(xBxTcore.getMessageManager().MasterMessageLocated(event.getPlayer(), Messages.SuccessPremium));
            }
            case "%ErrorNotRegistered" -> {
                event.setCancelled(true);
                event.getPlayer().sendMessage(xBxTcore.getMessageManager().MasterMessageLocated(event.getPlayer(), Messages.ErrorNotRegistered));
            }
            case "%ErrorOffline" -> {
                event.setCancelled(true);
                event.getPlayer().sendMessage(xBxTcore.getMessageManager().MasterMessageLocated(event.getPlayer(), Messages.ErrorOffline));
            }
            case "%ErrorAddressLimit" -> {
                event.setCancelled(true);
                event.getPlayer().sendMessage(xBxTcore.getMessageManager().MasterMessageLocated(event.getPlayer(), Messages.ErrorAddressLimit));
            }
            case "%AlreadyLogin" -> {
                event.setCancelled(true);
                event.getPlayer().sendMessage(xBxTcore.getMessageManager().MasterMessageLocated(event.getPlayer(), Messages.AlreadyLogin));
            }
            case "%AlreadyLoginOther" -> {
                event.setCancelled(true);
                event.getPlayer().sendMessage(xBxTcore.getMessageManager().MasterMessageLocated(event.getPlayer(), Messages.AlreadyLoginOther));
            }
            case "%AlreadyRegistered" -> {
                event.setCancelled(true);
                event.getPlayer().sendMessage(xBxTcore.getMessageManager().MasterMessageLocated(event.getPlayer(), Messages.AlreadyRegistered));
            }
            case "%AlreadyPremium" -> {
                event.setCancelled(true);
                event.getPlayer().sendMessage(xBxTcore.getMessageManager().MasterMessageLocated(event.getPlayer(), Messages.AlreadyPremium));
            }
            case "%PasswordSameAsCurrent" -> {
                event.setCancelled(true);
                event.getPlayer().sendMessage(xBxTcore.getMessageManager().MasterMessageLocated(event.getPlayer(), Messages.PasswordSameAsCurrent));
            }
            case "%PasswordRequireConfirmation" -> {
                event.setCancelled(true);
                event.getPlayer().sendMessage(xBxTcore.getMessageManager().MasterMessageLocated(event.getPlayer(), Messages.PasswordRequireConfirmation));
            }
            case "%PasswordIncorrect" -> {
                event.setCancelled(true);
                event.getPlayer().sendMessage(xBxTcore.getMessageManager().MasterMessageLocated(event.getPlayer(), Messages.PasswordIncorrect));
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
}