package Plugin.Security;

import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

import static Plugin.File.BLackList.BlackListIpManager.blackListedIps;
import static Plugin.Messages.MessageManager.ColorWarning;

public class AutoBan {

    private static xBxTcore plugin;

    public AutoBan (xBxTcore plugin){
        AutoBan.plugin = plugin;
        removeDelay();
    }

    private static final HashMap<UUID, Integer> countPunishChat = new HashMap<>();
    private static final HashMap<UUID, Long> timePunishChat = new HashMap<>();
    private static final HashMap<UUID, Long> timeDifferenceNew = new HashMap<>();
    private static final HashMap<UUID, Long> timeDifferenceOld = new HashMap<>();
    private static final HashMap<UUID, Integer> timeDifferenceCount = new HashMap<>();
    private static String lastMessage = "";
    private static final HashSet<UUID> ChatBotTime = new HashSet<>();

    public static void checkAutoBanChat(Player player, ReasonBan reasonBan, String message) {
        long currentTime = System.currentTimeMillis();
        switch (reasonBan) {
            case Chat_Bot -> {
                if (Objects.equals(lastMessage, message)){
                    ChatBotTime.add(player.getUniqueId());
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (ChatBotTime.size() > 1) {
                                for (UUID uuid : ChatBotTime){
                                    Player banPlayer = Bukkit.getPlayer(uuid);
                                    assert banPlayer != null;
                                    BanManager.banPlayer(banPlayer, "(Ban Automático) uso de bot o de uso indebido de multicuentas." +
                                            " Por seguridad tu ip fue agregada a la lista negras de los bos", 1000*60*60*24*5L);
                                    blackListedIps.add(banPlayer.getAddress().getAddress().getAddress());
                                }
                            }
                            ChatBotTime.clear();
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
                            BanManager.banPlayer(player, "(Ban Automático) uso de mensajes automatizado", 1000*60*30L);
                            timeDifferenceCount.remove(player.getUniqueId());
                            timeDifferenceOld.remove(player.getUniqueId());
                            timeDifferenceNew.remove(player.getUniqueId());
                        }
                    }
                    timeDifferenceNew.put(player.getUniqueId() ,currentTime - lastTime);
                    timeDifferenceOld.put(player.getUniqueId(), DifferenceNew);
                }
            }
            case Chat_Spam -> countPunishChat.put(player.getUniqueId(), countPunishChat.getOrDefault(player.getUniqueId(), 0) + 1);
            case Chat_Word -> countPunishChat.put(player.getUniqueId(), countPunishChat.getOrDefault(player.getUniqueId(), 0) + 2);
            case Chat_Both -> countPunishChat.put(player.getUniqueId(), countPunishChat.getOrDefault(player.getUniqueId(), 0) + 3);
        }


        if (countPunishChat.getOrDefault(player.getUniqueId(), 0) == 15) {
            BanManager.banPlayer(player, "(Ban Automático) Span indebido en el chat y/o palabras no adecuadas", 1000*60*2L);
        }

        timePunishChat.put(player.getUniqueId(), currentTime);
    }

    public void removeDelay() {
        new BukkitRunnable() {
            public void run() {
                for (UUID uuid : countPunishChat.keySet()) {
                    if (countPunishChat.get(uuid) <= 0) {
                        countPunishChat.put(uuid, countPunishChat.get(uuid) - 1);
                        timeDifferenceCount.put(uuid, timeDifferenceCount.getOrDefault(uuid, 0) - 1);
                    }else {
                        countPunishChat.remove(uuid);
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 20*5);
    }

}
