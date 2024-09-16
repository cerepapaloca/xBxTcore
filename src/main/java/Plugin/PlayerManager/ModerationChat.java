package Plugin.PlayerManager;

import Plugin.Messages.Enum.Messages;
import Plugin.Utils.ColorUtils;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.UUID;

import static Plugin.Messages.MessageManager.MasterMessageLocated;
import static Plugin.PlayerManager.PlayerManagerSection.moderationChatEnabled;

public class ModerationChat {

    private static HashSet<UUID> cooldown = new HashSet<>();
    private static HashSet<String> banWord = new HashSet<>();
    private final xBxTcore plugin;

    public ModerationChat(xBxTcore plugin) {
        this.plugin = plugin;
        banWord.add("http");
        banWord.add(".com");
        banWord.add(".net");
        banWord.add(".git");
        banWord.add(".gg");
        banWord.add(".xyz");
        banWord.add(".org");
        banWord.add(".pro");
        banWord.add(".info");
        banWord.add(".lat");
        banWord.add(".inc");
        banWord.add(".tv");
        banWord.add(".us");
        banWord.add("nigga");
        banWord.add("fack");
        banWord.add("puto");
        banWord.add("puta");
        banWord.add("mierda");
        banWord.add("sexo");
        banWord.add("coño");
        banWord.add(" ñ ");
        banWord.add("maricon");
        banWord.add("violar");
        banWord.add("pene");
        banWord.add("vajina");
        banWord.add("on top");
    }

    public void CheckMessage(AsyncPlayerChatEvent event){
        String prefix = ColorUtils.applyGradient(Utils.getPlayerPrefix(event.getPlayer()).replace("&l", ""), "l");

        if (moderationChatEnabled){
            if(cooldown.contains(event.getPlayer().getUniqueId())){
                if (isBanWord(event.getMessage())){
                    event.getPlayer().sendMessage(MasterMessageLocated(event.getPlayer(), Messages.Others_Chat_BanWord));
                    event.setCancelled(true);
                    return;
                }
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                        prefix + "&r "
                                + event.getPlayer().getName() + " » &7" + event.getMessage()));
                event.setCancelled(true);
                cooldown.add(event.getPlayer().getUniqueId());
                starCoolDown(event.getPlayer().getUniqueId());
            }else {
                event.getPlayer().sendMessage(MasterMessageLocated(event.getPlayer(), Messages.Others_Chat_Cooldown));
            }

        }else{
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                    prefix + "&r "
                            + event.getPlayer().getName() + " » &7" + event.getMessage()));
        }
        event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
        event.setCancelled(true);
    }

    public void starCoolDown(UUID uuid){
        new BukkitRunnable() {
            public void run() {
                cooldown.remove(uuid);
            }
        }.runTaskLater(plugin, 80);
    }

    public boolean isBanWord(String message){
        message = message.toLowerCase();
        for (String word : banWord){
            if (message.contains(word)){
                return true;
            }
            if (message.contains(word.replace("1", "i").toLowerCase())){
                return true;
            }
            if (message.contains(message.replace("1", "l").toLowerCase())){
                return true;
            }
            if (message.contains(message.replace("4", "a").toLowerCase())){
                return true;
            }
            if (message.contains(message.replace("0", "o").toLowerCase())){
                return true;
            }
            if(message.contains(message.replace("n", "ñ").toLowerCase())){
                return true;
            }
            if(message.contains(message.replace("ñ", "n").toLowerCase())){
                return true;
            }
        }
        return false;
    }
}
