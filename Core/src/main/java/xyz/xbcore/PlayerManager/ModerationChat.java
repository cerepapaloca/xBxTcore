package xyz.xbcore.PlayerManager;

import xyz.xbcommun.Messages.Messages.Messages;
import xyz.xbcore.Security.SecuritySection;
import xyz.xbcore.Security.SystemBan.BanManager;
import xyz.xbcore.Security.SystemBan.ContextBan;
import xyz.xbcommun.Utils.ColorUtils;
import xyz.xbcommun.Utils.UtilsGlobal;
import xyz.xbcore.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.UUID;

import static xyz.xbcommun.Messages.MessageManager.MasterMessageLocated;
import static xyz.xbcore.PlayerManager.PlayerManagerSection.moderationChatEnabled;

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
    }

    public void CheckMessage(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        String prefix = ColorUtils.applyGradient(UtilsGlobal.getPlayerPrefix(player).replace("&l", ""), 'l');

        if (BanManager.checkBanPlayer(player, ContextBan.CHAT) != null) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
                    prefix + "&r " + player.getName() + " » &7" + event.getMessage() + " &c[Eliminado: Baneado]"));
            event.setCancelled(true);
            return;
        }

        if (moderationChatEnabled){
            if(!cooldownPlayer.contains(event.getPlayer().getUniqueId())){
                if (isBanWord(event.getMessage())){
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
                            prefix + "&r " + event.getPlayer().getName() + " » &7" + event.getMessage() + " &c[Eliminado: Palabras prohibidas]"));
                    event.getPlayer().sendMessage(MasterMessageLocated(event.getPlayer(), Messages.Others_Chat_BanWord));
                }else{
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                            prefix + "&r "
                                    + player.getName() + " » &7" + event.getMessage()));
                }
            }else {
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
                        prefix + "&r " + player.getName() + " » &7" + event.getMessage() + " &c[Eliminado: Spam]"));
                player.sendMessage(MasterMessageLocated(player, Messages.Others_Chat_Cooldown).replace("%time%", "4s"));
            }
            SecuritySection.getAutoBan().checkAutoBanChat(player, event.getMessage());
            starCoolDown(player.getUniqueId());
        }else{
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                    prefix + "&r "
                            + player.getName() + " » &7" + event.getMessage()));
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
