package Plugin.Security.SystemBan;

import Plugin.Messages.Enum.Messages;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static Plugin.File.BLackList.BlackListIpManager.blackListedIps;
import static Plugin.Messages.MessageManager.*;

public class AutoBan {

    private static xBxTcore plugin;
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public AutoBan (xBxTcore plugin){
        AutoBan.plugin = plugin;
        removeDelay();
        removeDelayAC();
    }

    private static final HashMap<UUID, Integer> countPunishChat = new HashMap<>();
    private static final HashMap<UUID, Long> timePunishChat = new HashMap<>();
    private static final HashMap<UUID, Long> timeDifferenceNew = new HashMap<>();
    private static final HashMap<UUID, Long> timeDifferenceOld = new HashMap<>();
    private static final HashMap<UUID, Integer> timeDifferenceCount = new HashMap<>();
    private static String lastMessage = "";
    private static final ArrayList<Player> ChatBotTime = new ArrayList<>();

    public static void checkAutoBanChat(Player player, String message) {
        long currentTime = System.currentTimeMillis();
        if (Objects.equals(lastMessage, message)){
            ChatBotTime.add(player);
            new BukkitRunnable() {
                @Override
                public void run() {
                    executor.submit(() -> {
                        if (ChatBotTime.size() > 1) {
                            for (Player player : ChatBotTime) {
                                assert player != null;
                                BanManager.banPlayer(player, MasterMessageLocated(player, Messages.Ban_BotsChat), 1000 * 60 * 60 * 24 * 5L);
                                blackListedIps.add(Objects.requireNonNull(player.getAddress()).getAddress().getAddress());
                            }
                            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole +
                                    ColorSuccess + "Purga terminada"));
                            ChatBotTime.clear();
                        }else{
                            ChatBotTime.clear();
                        }
                    });
                }
            }.runTaskLater(plugin, 2);
        }else {
            lastMessage = message;
        }

        if (timePunishChat.containsKey(player.getUniqueId())) {
            long lastTime = timePunishChat.get(player.getUniqueId());
            long DifferenceOld = timeDifferenceOld.getOrDefault(player.getUniqueId(), -1000L);
            long DifferenceNew = timeDifferenceNew.getOrDefault(player.getUniqueId(), 1000L);

            if ((DifferenceOld - DifferenceNew) < 50 && (DifferenceOld - DifferenceNew) > -50) {
                timeDifferenceCount.put(player.getUniqueId(), timeDifferenceCount.getOrDefault(player.getUniqueId(), 0) + 1);
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', ColorWarning +
                        "Este jugador usa AutoBotChat: (" + timeDifferenceCount.get(player.getUniqueId()) + "/3) " + "Tiene una precision de "
                        + (DifferenceOld - DifferenceNew) + " ms"));
                if (timeDifferenceCount.get(player.getUniqueId()) >= 3){
                    BanManager.banPlayer(player, MasterMessageLocated(player, Messages.Ban_AutoMessages), 1000*60*30L, ContextBan.CHAT);
                    timeDifferenceCount.remove(player.getUniqueId());
                    timeDifferenceOld.remove(player.getUniqueId());
                    timeDifferenceNew.remove(player.getUniqueId());
                }
            }
            timeDifferenceNew.put(player.getUniqueId() ,currentTime - lastTime);
            timeDifferenceOld.put(player.getUniqueId(), DifferenceNew);
        }

        timePunishChat.put(player.getUniqueId(), currentTime);
    }

    public void removeDelay() {
        new BukkitRunnable() {
            public void run() {
                for (UUID uuid : countPunishChat.keySet()) {
                    if (countPunishChat.get(uuid) > 1) {
                        countPunishChat.put(uuid, countPunishChat.get(uuid) - 1);
                    }else {
                        countPunishChat.remove(uuid);
                    }
                }
                for (UUID uuid : timeDifferenceCount.keySet()) {
                    if (timeDifferenceCount.get(uuid) > 1) {
                        timeDifferenceCount.put(uuid, timeDifferenceCount.getOrDefault(uuid, 0) - 1);
                    }else {
                        timeDifferenceCount.remove(uuid);
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 20*60*2);
    }

    public void removeDelayAC() {
        new BukkitRunnable() {
            public void run() {
                for (UUID uuid : countPunishChatAC.keySet()) {
                    if (countPunishChatAC.get(uuid) > 1) {
                        countPunishChatAC.put(uuid, countPunishChatAC.getOrDefault(uuid, 0) - 1);
                    }else {
                        countPunishChatAC.remove(uuid);
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 20*60*5);
    }

    private static final HashMap<UUID, Integer> countPunishChatAC = new HashMap<>();

    public static boolean checkAutoBanCheat(Player player) {
        countPunishChatAC.put(player.getUniqueId(), countPunishChatAC.getOrDefault(player.getUniqueId(), 0) + 1);
        float pingLevel = player.getPing()*0.01F;
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorWarning +
                "Jugador usa hacks en Box pvp, Vetara en: (" + countPunishChatAC.get(player.getUniqueId()) + "/" + (2*pingLevel + 1) + ")"));

        if (countPunishChatAC.get(player.getUniqueId()) >= 2*pingLevel + 1) {
            BanManager.banPlayer(player, MasterMessageLocated(player, Messages.Ban_HacksBoxPvp), 1000*60*60*24L, ContextBan.BOX_PVP);
            countPunishChatAC.remove(player.getUniqueId());
            return true;
        }else if (countPunishChatAC.get(player.getUniqueId()) > 1) {
            BanManager.banPlayer(player, MasterMessageLocated(player, Messages.Ban_KickBoxPvp), 1000*60*5L, ContextBan.BOX_PVP);
            return true;
        }
        return false;
    }

}
