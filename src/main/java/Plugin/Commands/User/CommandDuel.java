package Plugin.Commands.User;

import Plugin.Managers.InventoryManager;
import Plugin.Model.InvetoryPlayer;
import Plugin.Model.Messages;
import Plugin.Model.Request;
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

import static Plugin.Managers.MessageManager.*;

public class CommandDuel implements CommandExecutor {

    private final Map<UUID, Request> pendingRequests = new HashMap<>();
    private final ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<Player> acceptPlayers = new ArrayList<>();
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
            if(args.length > 0){


            }/*else{
                //playareSender.sendMessage(xBxTcore.getMessageManager().MasterMessage(playareSender, Messages.DuelError));
            }*/
            if (xBxTcore.getWorldProtec().contains(playareSender.getWorld())) {
                switch (args.length) {
                    case 0:
                        xBxTcore.getInventoryMenu().OpenDuel(new InvetoryPlayer(playareSender));
                        return true;
                    case 1:
                        Player target = Bukkit.getPlayer(args[0]);
                        if (args[0].equalsIgnoreCase("deny")) {
                            denyRequest((Player) sender, requestlast.getRequesterId(), true);
                            return true;
                        } else if (args[0].equalsIgnoreCase("yes")){
                            acceptRequest((Player) sender, requestlast.getRequesterId());
                            return true;
                        } else if (args[0].equalsIgnoreCase("fast_inv")){
                            xBxTcore.getCommandDuel().sendRequest(xBxTcore.getPlayerDataUnique(playareSender.getUniqueId()).getGuestPlayers(false), xBxTcore.getPlayerDataUnique(playareSender.getUniqueId()).getNameWolrd(), playareSender.getUniqueId());
                            return true;
                        }

                        if (target != null) {
                            if(target != playareSender){
                                ClearSttingsDuel(playareSender.getUniqueId());
                                players.add(playareSender);
                                players.add(target);
                                sendRequest(players, "bedrock", (playareSender).getUniqueId());
                            }else{
                                playareSender.sendMessage(xBxTcore.getMessageManager().MasterMessage(playareSender, Messages.SendSelf));
                            }
                        }else{
                            playareSender.sendMessage(xBxTcore.getMessageManager().MasterMessage(playareSender, Messages.PlayerOffTarget));
                        }
                        return true;
                    case 2:
                        if (args[0].equalsIgnoreCase("deny") && pendingRequests.containsKey(Bukkit.getPlayer(args[1]).getUniqueId())){
                            denyRequest((Player) sender, Bukkit.getPlayer(args[1]).getUniqueId(), true);
                            return true;
                        } else if (args[0].equalsIgnoreCase("yes") && pendingRequests.containsKey(Bukkit.getPlayer(args[1]).getUniqueId())){
                            acceptRequest((Player) sender, Bukkit.getPlayer(args[1]).getUniqueId());
                            return true;
                        }
                        target = Bukkit.getPlayer(args[0]);
                        if (target != null) {
                            if(target != playareSender){
                                if(args[1].equalsIgnoreCase("bedrock") || args[1].equalsIgnoreCase("flat_bedrock") || args[1].equalsIgnoreCase("flat_world")){
                                    ClearSttingsDuel(playareSender.getUniqueId());
                                    players.add(playareSender);
                                    players.add(target);
                                    sendRequest(players, args[1].toLowerCase(), (playareSender).getUniqueId());

                                }else{
                                    playareSender.sendMessage(xBxTcore.getMessageManager().MasterMessage(playareSender, Messages.WorldType));
                                }
                            }else{
                                playareSender.sendMessage(xBxTcore.getMessageManager().MasterMessage(playareSender, Messages.SendSelf));
                            }
                        }else{
                            playareSender.sendMessage(xBxTcore.getMessageManager().MasterMessage(playareSender, Messages.PlayerOffTarget));
                        }
                }
            }else{
                playareSender.sendMessage(xBxTcore.getMessageManager().MasterMessage(playareSender, Messages.OnDuel));
            }

        } else {
            plugin.messageOnlyPlayer();
        }
        return false;
    }

    public void sendRequest(ArrayList<Player> players1, String worldType, UUID requesterId) {
        Bukkit.getConsoleSender().sendMessage("petición");
        requestlast = new Request(requesterId, System.currentTimeMillis() + 60000, world, players1, worldType); // 60 segundos
        Player requester = Bukkit.getPlayer(requesterId);
        players1.remove(requester);

        if(players1.isEmpty()){
            requester.sendMessage(xBxTcore.getMessageManager().MasterMessage(requester, Messages.ListPlayersEmpty));
            return;
        }

        pendingRequests.put(requesterId, requestlast);
        for (Player player : players1){
            TextComponent finalMessage = new TextComponent();
            String message1 = xBxTcore.getMessageManager().MasterMessage(player, Messages.SendRequest1);
            String message2 = xBxTcore.getMessageManager().MasterMessage(player, Messages.SendRequest2);
            yes.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(xBxTcore.getMessageManager().MasterMessage(player,Messages.HoverYes)).create()));
            deny.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(xBxTcore.getMessageManager().MasterMessage(player,Messages.HoverDeny)).create()));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + Colorinfo + "&l⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊>"));
            if (xBxTcore.getPlayerDataUnique(requesterId).getTimelimit()){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "-" + xBxTcore.getMessageManager().MasterMessage(player,Messages.TimeLimit) + Colorplayer + InventoryManager.secondsToMinutesLore(requester).get(0)));
            }else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "-" + xBxTcore.getMessageManager().MasterMessage(player,Messages.TimeLimit) + Colorplayer + "No"));
            }

            if (xBxTcore.getPlayerDataUnique(requesterId).getKitData().getName() != null){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "-" + xBxTcore.getMessageManager().MasterMessage(player,Messages.KitSelect) + xBxTcore.getPlayerDataUnique(requesterId).getKitData().getName()));
            }else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "-" + xBxTcore.getMessageManager().MasterMessage(player,Messages.KitSelect) + xBxTcore.getMessageManager().MasterMessage(player,Messages.KitFavorite)));
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "-" + xBxTcore.getMessageManager().MasterMessage(player,Messages.ArenaDuel) + worldType));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "-" + xBxTcore.getMessageManager().MasterMessage(player,Messages.SenderPlayer) + requester.getName()));
            finalMessage.addExtra(message1);
            finalMessage.addExtra(yes);
            finalMessage.addExtra(message2);
            finalMessage.addExtra(deny);
            player.spigot().sendMessage(finalMessage);
            requester.sendMessage(xBxTcore.getMessageManager().MasterMessage(requester, Messages.SendRequest).replace("%player%", player.getName()));
        }
        xBxTcore.getTools().AntiSpam(requester, Messages.SpamCommand);
    }

    private void acceptRequest(Player accepter, UUID uuidResquest) {
        if (pendingRequests.containsKey(uuidResquest)) {
            Request request = pendingRequests.get(uuidResquest);
            if (System.currentTimeMillis() <= request.getExpirationTime()) {
                Player requester = Bukkit.getPlayer(request.getRequesterId());
                if (requester != null) {
                    if (requestlast.getPlayers().contains(accepter)) {
                        accepter.sendMessage(xBxTcore.getMessageManager().MasterMessage(accepter, Messages.AcceptedRequest));
                        requester.sendMessage(xBxTcore.getMessageManager().MasterMessage(requester, Messages.AcceptedYourRequest));
                        if (accepter.getWorld().equals(Bukkit.getWorld("lobby")) || accepter.getWorld().equals(Bukkit.getWorld("creatorkits"))) {
                            request.getAcceptPlayers().add(accepter);
                            teleportDuel(request);

                        } else {
                            accepter.sendMessage(xBxTcore.getMessageManager().MasterMessage(accepter, Messages.OnDuel));
                        }
                    } else {
                        accepter.sendMessage(xBxTcore.getMessageManager().MasterMessage(accepter, Messages.SelfAccepted));
                    }
                } else {
                    accepter.sendMessage(xBxTcore.getMessageManager().MasterMessage(accepter, Messages.PlayerOffSender));
                }
            } else {
                accepter.sendMessage(xBxTcore.getMessageManager().MasterMessage(accepter, Messages.RequestExpired));
                pendingRequests.remove(uuidResquest);
            }
        } else {
            accepter.sendMessage(xBxTcore.getMessageManager().MasterMessage(accepter, Messages.NotRequests));
        }
    }

    public void denyRequest(Player denier, UUID uuidResquest, Boolean messges) {
        if (pendingRequests.containsKey(uuidResquest)) {
            Request request = pendingRequests.get(uuidResquest);
            Bukkit.getConsoleSender().sendMessage("falta eliminacion jugadores " + request.getPlayers().size() + "/" + request.getAcceptPlayers().size());
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
                    denier.sendMessage(xBxTcore.getMessageManager().MasterMessage(denier, Messages.DenyYou).replace("%player%", requester.getName()));
                    requester.sendMessage(xBxTcore.getMessageManager().MasterMessage(requester, Messages.DenyYour).replace("%player%", denier.getName()));
                }
            } else {
                denier.sendMessage(xBxTcore.getMessageManager().MasterMessage(denier, Messages.PlayerOffSender));
                pendingRequests.remove(uuidResquest);
            }

        } else {
            denier.sendMessage(xBxTcore.getMessageManager().MasterMessage(denier, Messages.NotRequests));
        }
    }

    private void teleportDuel(Request request){
        if (!(request.getPlayers().size() == request.getAcceptPlayers().size())){
            Broadcast(request);
            Bukkit.getConsoleSender().sendMessage("falta jugadores " + request.getPlayers().size() + "/" + request.getAcceptPlayers().size());
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
                xBxTcore.getDuelManager().StartDuel(request.getPlayers(), xBxTcore.getMultiverseCore().getMVWorldManager().getMVWorld(worldName));
                for (Player target : request.getPlayers()) {
                    pendingRequests.remove(target.getUniqueId());
                    pendingRequests.remove(target.getUniqueId());
                }

                return;
            }
        }
        for (Player player : request.getPlayers()) {
            player.sendMessage(xBxTcore.getMessageManager().MasterMessage(player, Messages.FullSites));
        }
    }

    public void Broadcast(Request request){
        for (Player player : request.getPlayers()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',xBxTcore.getMessageManager().MasterMessage(player, Messages.MissingPlayers) + "(" + Colorplayer + request.getPlayers().size() + Colorinfo + "/" + Colorplayer + request.getAcceptPlayers().size() + Colorinfo + ")"));
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