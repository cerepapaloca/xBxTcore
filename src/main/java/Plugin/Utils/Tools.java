package Plugin.Utils;

import Plugin.Model.Messages;
import Plugin.xBxTcore;
import net.luckperms.api.node.types.InheritanceNode;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Parrot;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static Plugin.Managers.MessageManager.*;
import static Plugin.Managers.MessageManager.ColorLink;
import static org.bukkit.Bukkit.getIdleTimeout;
import static org.bukkit.Bukkit.getServer;

public class Tools {

    private final xBxTcore plugin;

    public Tools(xBxTcore plugin){
        this.plugin = plugin;
        Timealone();
    }

    private void Timealone() {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            if(getServer().getOnlinePlayers().size() == 1){
                for (Player p :getServer().getOnlinePlayers()){
                    p.sendMessage(xBxTcore.getMessageManager().MasterMessage(p, Messages.Alone));
                }
            }
        }, 2 * 20,40 * 20);
    }

    private final HashMap<UUID, Integer> playersExecute = new HashMap<>();

    public void AntiSpam(Player p, Messages messages) {
        UUID uuid = p.getUniqueId();
        playersExecute.put(uuid, playersExecute.getOrDefault(uuid, 0) + 1);
        if (playersExecute.get(uuid) > 3) {
            xBxTcore.getMessageManager().KickMessage(p, messages);
            playersExecute.remove(uuid);
        }
        new BukkitRunnable() {
            public void run() {
                playersExecute.remove(uuid);
            }
        }.runTaskLater(plugin, 200);
    }

    public static void RewardVote(String name, Boolean force) {
        Player player = Bukkit.getPlayer(name);
        if (force){
            if(player == null) {
                xBxTcore.playersOffline.add(name);
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefixConsole + ColorWarning + "El jugador&r " + name
                        + ColorWarning + " no existe o no esta conectado"));
            }else{
                xBxTcore.getLuckPerms().getUserManager().loadUser(player.getUniqueId()).thenAccept(user -> {
                    InheritanceNode node = InheritanceNode.builder("vote").build();
                    user.data().add(node);
                    xBxTcore.getLuckPerms().getUserManager().saveUser(user).join();
                });
                Bukkit.getConsoleSender().sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo + "Ha votado el jugador: " + Colorplayer + name));
            }
        }else{
            if(player == null && xBxTcore.playersOffline.contains(name)) {
                xBxTcore.playersOffline.add(name);
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefixConsole + ColorError + "El jugador&r " + Colorplayer + name + " no se pudo dar la recompensa de algún modo"));
                return;
            }
            if(xBxTcore.playersOffline.contains(name)){
                xBxTcore.playersOffline.remove(name);
                assert player != null;
                xBxTcore.getLuckPerms().getUserManager().modifyUser(player.getUniqueId(), user -> {
                    user.data().clear();
                    user.data().add(InheritanceNode.builder("vote").build());
                });
                Bukkit.getConsoleSender().sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo + "Se ha echo un voto por adelantado de parte del jugador: " + Colorplayer + name));
            }
        }
    }

    public static void ClickExecuteCommand(String command, Messages messages, Player p) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix));
        TextComponent execute = new TextComponent();
        TextComponent executefinal = new TextComponent(xBxTcore.getMessageManager().MasterMessage(p, messages));
        execute.setText(ChatColor.translateAlternateColorCodes('&', ColorLink + "/" + command));
        execute.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/" + command));
        execute.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(xBxTcore.getMessageManager().MasterMessage(p,Messages.HoverExecute)).create()));
        executefinal.addExtra(execute);
        p.spigot().sendMessage(executefinal);
    }

    public static void NewitemInvetory(Messages m, Material ma, int slot, Inventory i, Player p){
        ItemStack itemStack = new ItemStack(ma);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(xBxTcore.getMessageManager().MasterMessage(p, m));
        itemStack.setItemMeta(itemMeta);
        i.setItem(slot, itemStack);
    }

    public static void NewitemInvetory(Messages m, Material ma, int slot, Inventory i, Player p, ArrayList<String> lore){
        ItemStack itemStack = new ItemStack(ma);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        ArrayList<String> colorlore = new ArrayList<>();
        for (String s : lore){
            colorlore.add(ChatColor.translateAlternateColorCodes('&',  s));
        }
        itemMeta.setLore(colorlore);
        itemMeta.setDisplayName(xBxTcore.getMessageManager().MasterMessage(p, m));
        itemStack.setItemMeta(itemMeta);
        i.setItem(slot, itemStack);
    }

    public static ArrayList<ItemStack> getItensInvetory(Player player){
        ArrayList<ItemStack> items = new ArrayList<>();
        for(int i = 0; i<player.getInventory().getContents().length;){
            ItemStack itemstack = player.getInventory().getItem(i);
            items.add(itemstack);
            i++;
        }
        return items;
    }
}