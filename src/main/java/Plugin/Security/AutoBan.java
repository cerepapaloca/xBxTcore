package Plugin.Security;

import Plugin.xBxTcore;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

import static Plugin.File.BLackList.BlackListIpManager.AddIpBlackList;

public class AutoBan {

    private final xBxTcore plugin;

    public AutoBan (xBxTcore plugin){
        this.plugin = plugin;
        removeDelay();
    }

    private static final HashMap<UUID, Integer> countPunishChat = new HashMap<>();
    private static final HashMap<UUID, Long> timePunishChat = new HashMap<>();
    private static String lastMessage = "";
    private static long cooldown = System.currentTimeMillis();

    public static void checkAutoBanChat(Player player, ReasonBan reasonBan, String Message) {
        long currentTime = System.currentTimeMillis();

        if (Objects.equals(lastMessage, Message)){
            if ((currentTime - cooldown) > 100){
                BanManager.banPlayer(player, "(Ban Automático) uso de bot o de uso indebido de multicuentas." +
                        " Por seguridad tu ip fue agregada a la lista negras de los bos", 1000*60*60*24*5);
                AddIpBlackList(Objects.requireNonNull(player.getAddress()).getAddress());
                cooldown = currentTime;
                return;
            }
        }else {
            lastMessage = Message;
        }

        if (timePunishChat.containsKey(player.getUniqueId())) {
            long lastTime = timePunishChat.get(player.getUniqueId());
            long timeDifference = currentTime - lastTime;

            if (timeDifference < 80) {
                BanManager.banPlayer(player, "(Ban Automático) uso de mensajes automatizado", 1000*60*30);
                return;
            }
        }

        switch (reasonBan){
            case Chat_Spam -> countPunishChat.put(player.getUniqueId(), countPunishChat.getOrDefault(player.getUniqueId(), 0) + 1);
            case Chat_Word -> countPunishChat.put(player.getUniqueId(), countPunishChat.getOrDefault(player.getUniqueId(), 0) + 2);
            case Chat_Both -> countPunishChat.put(player.getUniqueId(), countPunishChat.getOrDefault(player.getUniqueId(), 0) + 3);
        }

        if (countPunishChat.get(player.getUniqueId()) == 15) {
            long timeDelay = currentTime - timePunishChat.get(player.getUniqueId());
            long levelPunish;
            if (timeDelay > 15000) {
                levelPunish = 1;
            }else if (timeDelay > 10000) {
                levelPunish = 3;
            }else if (timeDelay > 4000) {
                levelPunish = 10;
            }else if (timeDelay > 2000) {
                levelPunish = 25;
            }else {
                levelPunish = 100;
            }
            BanManager.banPlayer(player, "(Ban Automático) Span indebido en el chat y/o palabras no adecuadas", 1000*30L*levelPunish);
        }

        timePunishChat.put(player.getUniqueId(), currentTime);
    }

    public void removeDelay() {
        new BukkitRunnable() {
            public void run() {
                for (UUID uuid : countPunishChat.keySet()) {
                    if (countPunishChat.get(uuid) <= 0) {
                        countPunishChat.put(uuid, countPunishChat.get(uuid) - 1);
                    }else {
                        countPunishChat.remove(uuid);
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 20*5);
    }

}
