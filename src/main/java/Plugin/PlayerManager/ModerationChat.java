package Plugin.PlayerManager;

import Plugin.Messages.Enum.Messages;
import Plugin.Security.ReasonBan;
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
import static Plugin.Security.AutoBan.checkAutoBanChat;

public class ModerationChat {

    private static final HashSet<UUID> cooldownPlayer = new HashSet<>();
    private static final HashSet<String> banWord = new HashSet<>();
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
            if(!cooldownPlayer.contains(event.getPlayer().getUniqueId())){
                if (isBanWord(event.getMessage())){
                    checkAutoBanChat(event.getPlayer(), ReasonBan.Chat_Word, event.getMessage());
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
                            prefix + "&r " + event.getPlayer().getName() + " » &7" + event.getMessage() + " &c[Eliminado: Palabras prohibidas]"));
                    event.getPlayer().sendMessage(MasterMessageLocated(event.getPlayer(), Messages.Others_Chat_BanWord));
                }else{
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                            prefix + "&r "
                                    + event.getPlayer().getName() + " » &7" + event.getMessage()));
                }
                checkAutoBanChat(event.getPlayer(), ReasonBan.Chat_Bot, event.getMessage());
            }else {
                if (isBanWord(event.getMessage()))checkAutoBanChat(event.getPlayer(), ReasonBan.Chat_Both, event.getMessage());
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
                        prefix + "&r " + event.getPlayer().getName() + " » &7" + event.getMessage() + " &c[Eliminado: Spam]"));
                event.getPlayer().sendMessage(MasterMessageLocated(event.getPlayer(), Messages.Others_Chat_Cooldown).replace("%time%", "4s"));
                checkAutoBanChat(event.getPlayer(), ReasonBan.Chat_Spam, event.getMessage());
            }
            starCoolDown(event.getPlayer().getUniqueId());
        }else{
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                    prefix + "&r "
                            + event.getPlayer().getName() + " » &7" + event.getMessage()));
        }
        event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
        event.setCancelled(true);
    }

    public void starCoolDown(UUID uuid){
        cooldownPlayer.add(uuid);
        new BukkitRunnable() {
            public void run() {
                cooldownPlayer.remove(uuid);
            }
        }.runTaskLater(plugin, 20*4);
    }

    public boolean isBanWord(String message){
        message = message.toLowerCase();
        for (String word : banWord){
            if (message.contains(word)){
                return true;
            }
            if (message.contains(word.replace("i", "1"))){
                return true;
            }
            if (message.contains(word.replace("l", "1"))){
                return true;
            }
            if (message.contains(word.replace("a", "4"))){
                return true;
            }
            if (message.contains(word.replace("o", "0"))){
                return true;
            }
            if(message.contains(word.replace("n", "ñ"))){
                return true;
            }
            if(message.contains(word.replace("ñ", "n"))){
                return true;
            }
        }
        return false;
    }
}
