package Plugin.Commands.User;

import Plugin.Commands.CommandSection;
import Plugin.Duel.DuelSection;
import Plugin.Inventory.InventorySection;
import Plugin.Inventory.InventoryManager;
import Plugin.Inventory.Models.InvetoryPlayer;
import Plugin.Messages.Enum.Messages;
import Plugin.Duel.Model.Request;
import Plugin.xBxTcore;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static Plugin.Messages.MessageManager.*;
import static Plugin.Utils.Utils.AntiSpam;

public class CommandDuel implements CommandExecutor {

    private final Map<UUID, Request> pendingRequests = new HashMap<>();
    private final ArrayList<Player> players = new ArrayList<>();
    private final xBxTcore plugin;
    MultiverseWorld world;
    private final TextComponent yes;
    private final TextComponent deny;
    private Request requestlast;

    public CommandDuel(xBxTcore plugin){
        this.plugin = plugin;

        yes = new TextComponent(ChatColor.translateAlternateColorCodes('&', "&a&l/Duel Yes"));
        deny = new TextComponent(ChatColor.translateAlternateColorCodes('&', "&c&l/Duel Deny"));
        yes.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/duel yes"));
        deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/duel deny"));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player playareSender) {
            players.clear();
            if (xBxTcore.getWorldProtec().contains(playareSender.getWorld())) {
                Player target = null;
                if (args.length != 0) target = Bukkit.getPlayer(args[0]);
                switch (args.length) {
                    case 0:
                        InventorySection.getInventoryMenu().OpenDuel(new InvetoryPlayer(playareSender));
                        return true;
                    case 1:
                        if (args[0].equalsIgnoreCase("deny")) {
                            denyRequest((Player) sender, requestlast.getRequesterId(), true);
                            return true;
                        } else if (args[0].equalsIgnoreCase("yes")){
                            acceptRequest((Player) sender, requestlast.getRequesterId());
                            return true;
                        } else if (args[0].equalsIgnoreCase("fast_inv")){
                            CommandSection.getCommandDuel().sendRequest(xBxTcore.getPlayerDataUnique(playareSender.getUniqueId()).getGuestPlayers(false), xBxTcore.getPlayerDataUnique(playareSender.getUniqueId()).getNameWolrd(), playareSender.getUniqueId());
                            return true;
                        }

                        if (target != null) {
                            if(target != playareSender){
                                ClearSttingsDuel(playareSender.getUniqueId());
                                players.add(playareSender);
                                players.add(target);
                                sendRequest(players, "bedrock", (playareSender).getUniqueId());
                            }else{
                                playareSender.sendMessage(MasterMessageLocated(playareSender, Messages.RequestDuel_SendSelf));
                            }
                        }else{
                            playareSender.sendMessage(MasterMessageLocated(playareSender, Messages.RequestDuel_PlayerOffTarget));
                        }
                        return true;
                    case 2:
                        UUID uuid = Objects.requireNonNull(Bukkit.getPlayer(args[1])).getUniqueId();
                        if (args[0].equalsIgnoreCase("deny") && pendingRequests.containsKey(uuid)){
                            denyRequest((Player) sender, uuid, true);
                            return true;
                        } else if (args[0].equalsIgnoreCase("yes") && pendingRequests.containsKey(uuid)){
                            acceptRequest((Player) sender, uuid);
                            return true;
                        }
                        if (target != null) {
                            if(target != playareSender){
                                if(args[1].equalsIgnoreCase("bedrock") || args[1].equalsIgnoreCase("flat_bedrock") || args[1].equalsIgnoreCase("flat_world")){
                                    ClearSttingsDuel(playareSender.getUniqueId());
                                    players.add(playareSender);
                                    players.add(target);
                                    sendRequest(players, args[1].toLowerCase(), (playareSender).getUniqueId());

                                }else{
                                    playareSender.sendMessage(MasterMessageLocated(playareSender, Messages.RequestDuel_WorldType));
                                }
                            }else{
                                playareSender.sendMessage(MasterMessageLocated(playareSender, Messages.RequestDuel_SendSelf));
                            }
                        }else{
                            playareSender.sendMessage(MasterMessageLocated(playareSender, Messages.RequestDuel_PlayerOffTarget));
                        }
                }
            }else{
                playareSender.sendMessage(MasterMessageLocated(playareSender, Messages.RequestDuel_OnDuel));
            }

        } else {
            plugin.messageOnlyPlayer();
        }
        return false;
    }

    public void sendRequest(ArrayList<Player> players1, String worldType, UUID requesterId) {
        requestlast = new Request(requesterId, System.currentTimeMillis() + 60000, players1, worldType); // 60 segundos
        Player requester = Bukkit.getPlayer(requesterId);
        players1.remove(requester);

        if(players1.isEmpty()){
            assert requester != null;
            requester.sendMessage(MasterMessageLocated(requester, Messages.DuelWorld_ListPlayersEmpty));
            return;
        }

        pendingRequests.put(requesterId, requestlast);
        for (Player player : players1){
            TextComponent finalMessage = new TextComponent();
            String message1 = MasterMessageLocated(player, Messages.RequestDuel_SendRequest1);
            String message2 = MasterMessageLocated(player, Messages.RequestDuel_SendRequest2);
            yes.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(MasterMessageLocated(player,Messages.RequestDuel_HoverYes)).create()));
            deny.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(MasterMessageLocated(player,Messages.RequestDuel_HoverDeny)).create()));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + Colorinfo + "&l⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊>"));
            if (xBxTcore.getPlayerDataUnique(requesterId).getTimelimit()){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "-" + InventoryManager.secondsToMinutesLore(requester).get(0)));
            }else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "-" + MasterMessageLocated(player,Messages.RequestDuel_TimeLimit) + Colorplayer + "No"));
            }

            if (xBxTcore.getPlayerDataUnique(requesterId).getKitData().getName() != null){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "-" + MasterMessageLocated(player,Messages.RequestDuel_KitSelect) + xBxTcore.getPlayerDataUnique(requesterId).getKitData().getName()));
            }else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "-" + MasterMessageLocated(player,Messages.RequestDuel_KitSelect) + MasterMessageLocated(player,Messages.RequestDuel_KitFavorite)));
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "-" + MasterMessageLocated(player,Messages.RequestDuel_ArenaDuel) + worldType));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "-" + MasterMessageLocated(player,Messages.RequestDuel_SenderPlayer) + requester.getName()));
            finalMessage.addExtra(message1);
            finalMessage.addExtra(yes);
            finalMessage.addExtra(message2);
            finalMessage.addExtra(deny);
            player.spigot().sendMessage(finalMessage);
            requester.sendMessage(MasterMessageLocated(requester, Messages.RequestDuel_SendRequest).replace("%player%", player.getName()));
        }
        assert requester != null;
        AntiSpam(requester, Messages.Kick_SpamCommand, plugin);
    }

    private void acceptRequest(Player accepter, UUID uuidResquest) {
        if (pendingRequests.containsKey(uuidResquest)) {
            Request request = pendingRequests.get(uuidResquest);
            if (System.currentTimeMillis() <= request.getExpirationTime()) {
                Player requester = Bukkit.getPlayer(request.getRequesterId());
                if (requester != null) {
                    if (requestlast.getPlayers().contains(accepter)) {
                        accepter.sendMessage(MasterMessageLocated(accepter, Messages.RequestDuel_AcceptedRequest));
                        requester.sendMessage(MasterMessageLocated(requester, Messages.RequestDuel_AcceptedYourRequest));
                        if (accepter.getWorld().equals(Bukkit.getWorld("lobby")) || accepter.getWorld().equals(Bukkit.getWorld("creatorkits"))) {
                            request.getAcceptPlayers().add(accepter);
                            teleportDuel(request);

                        } else {
                            accepter.sendMessage(MasterMessageLocated(accepter, Messages.RequestDuel_OnDuel));
                        }
                    } else {
                        accepter.sendMessage(MasterMessageLocated(accepter, Messages.RequestDuel_SelfAccepted));
                    }
                } else {
                    accepter.sendMessage(MasterMessageLocated(accepter, Messages.RequestDuel_PlayerOffSender));
                }
            } else {
                accepter.sendMessage(MasterMessageLocated(accepter, Messages.RequestDuel_RequestExpired));
                pendingRequests.remove(uuidResquest);
            }
        } else {
            accepter.sendMessage(MasterMessageLocated(accepter, Messages.RequestDuel_NotRequests));
        }
    }

    public void denyRequest(Player denier, UUID uuidResquest, Boolean messges) {
        if (pendingRequests.containsKey(uuidResquest)) {
            Request request = pendingRequests.get(uuidResquest);

            Player requester = Bukkit.getPlayer(request.getRequesterId());
            if (requester != null) {
                request.getAcceptPlayers().remove(denier);
                if (request.getPlayers().size() <= 2) {
                    pendingRequests.remove(uuidResquest);
                }else{
                    request.getPlayers().remove(denier);
                    teleportDuel(request);
                }
                if(messges){
                    denier.sendMessage(MasterMessageLocated(denier, Messages.RequestDuel_DenyYou).replace("%player%", requester.getName()));
                    requester.sendMessage(MasterMessageLocated(requester, Messages.RequestDuel_DenyYour).replace("%player%", denier.getName()));
                }
            } else {
                denier.sendMessage(MasterMessageLocated(denier, Messages.RequestDuel_PlayerOffSender));
                pendingRequests.remove(uuidResquest);
            }

        } else {
            denier.sendMessage(MasterMessageLocated(denier, Messages.RequestDuel_NotRequests));
        }
    }

    private void teleportDuel(Request request){
        if (!(request.getPlayers().size() == request.getAcceptPlayers().size())){
            Broadcast(request);
            Bukkit.getConsoleSender().sendMessage("falta eliminacion jugadores " + request.getPlayers().size() + "/" + request.getAcceptPlayers().size());
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefixConsole + Colorinfo + "falta jugadores " + request.getPlayers().size() + "/" + request.getAcceptPlayers().size()));
            return;
        }

        if (request.getPlayers().size() < 2){
            pendingRequests.remove(request.getRequesterId());
            return;
        }

        for (Player target : request.getPlayers()) {
            if(target.getWorld().equals(Bukkit.getWorld("creatorkits"))){
                target.setExp(0);
                target.setLevel(0);
            }
        }

        for (int i = 1 ; i <= 3 ; i++) {
            String worldName = request.getWorldType();
            worldName = worldName + i;
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole +
                    "Se iniciado un duelo por " + Colorplayer + request.getPlayers().get(0).getName() + Colorinfo + " Hacia a " + Colorplayer
                    + request.getPlayers().get(1).getName()));
            if(Objects.requireNonNull(Bukkit.getWorld(worldName)).getPlayers().isEmpty()){;
                DuelSection.getDuelManager().StartDuel(request.getPlayers(), xBxTcore.getMultiverseCore().getMVWorldManager().getMVWorld(worldName));
                for (Player target : request.getPlayers()) {
                    pendingRequests.remove(target.getUniqueId());
                    pendingRequests.remove(target.getUniqueId());
                }

                return;
            }
        }
        for (Player player : request.getPlayers()) {
            player.sendMessage(MasterMessageLocated(player, Messages.RequestDuel_FullSites));
        }
    }

    public void Broadcast(Request request){
        for (Player player : request.getPlayers()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',MasterMessageLocated(player, Messages.RequestDuel_MissingPlayers) + "(" + Colorplayer + request.getPlayers().size() + Colorinfo + "/" + Colorplayer + request.getAcceptPlayers().size() + Colorinfo + ")"));
        }
    }

    public void ClearSttingsDuel(UUID uuid){
        try{
            xBxTcore.getPlayerDataUnique(uuid).getKitData();
            xBxTcore.getPlayerDataUnique(uuid).setIndexMap(0);
            xBxTcore.getPlayerDataUnique(uuid).setTimelimit(false);
        }catch (Exception ignored){

        }
    }

    public Map<UUID, Request> getPendingRequests() {
        return pendingRequests;
    }
}