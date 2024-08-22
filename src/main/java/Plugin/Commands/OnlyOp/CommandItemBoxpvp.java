package Plugin.Commands.OnlyOp;

import Plugin.Enum.Messages;
import Plugin.Managers.ItemManage;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class CommandItemBoxpvp implements CommandExecutor {

    private final xBxTcore plugin;

    public CommandItemBoxpvp(xBxTcore plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@Nullable CommandSender sender,@Nullable Command cmd,@Nullable String label, String[] args) {
        if(sender instanceof Player p){
            if (p.isOp()){
                if (args.length == 1){
                    switch (args[0]){
                        case "*" -> {
                            for (ItemStack item : ItemManage.coinCompact){
                                Utils.additem(p, item);
                            }
                            for (ItemStack item : ItemManage.coinNormal){
                                Utils.additem(p, item);
                            }
                            for (ItemStack item : ItemManage.helmets){
                                Utils.additem(p, item);
                            }
                            for (ItemStack item : ItemManage.elytras){
                                Utils.additem(p, item);
                            }
                            for (ItemStack item : ItemManage.leggings){
                                Utils.additem(p, item);
                            }
                            for (ItemStack item : ItemManage.boots){
                                Utils.additem(p, item);
                            }
                        }
                        case "coins" -> {
                            for (ItemStack item : ItemManage.coinCompact){
                                Utils.additem(p, item);
                            }
                            for (ItemStack item : ItemManage.coinNormal){
                                Utils.additem(p, item);
                            }
                        }
                        case "coins_normal" -> {
                            for (ItemStack item : ItemManage.coinNormal){
                                Utils.additem(p, item);
                            }
                        }
                        case "coin_compact" -> {
                            for (ItemStack item : ItemManage.coinCompact){
                                Utils.additem(p, item);
                            }
                        }
                        case "helmets" -> {
                            for (ItemStack item : ItemManage.helmets){
                                Utils.additem(p, item);
                            }
                        }
                        case "elytra" -> {
                            for (ItemStack item : ItemManage.elytras){
                                Utils.additem(p, item);
                            }
                        }
                        case "leggings" -> {
                            for (ItemStack item : ItemManage.leggings){
                                Utils.additem(p, item);
                            }
                        }
                        case "boots" -> {
                            for (ItemStack item : ItemManage.boots){
                                Utils.additem(p, item);
                            }
                        }
                    }
                } else if (args.length == 2 && args[0].equals("tier")){
                    for (ItemStack item : ItemManage.coinCompact){
                        if (String.valueOf(args[1]).equals(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.STRING))){
                            Utils.additem(p, item);
                        }
                    }
                    for (ItemStack item : ItemManage.coinNormal){
                        if (String.valueOf(args[1]).equals(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.STRING))){
                            Utils.additem(p, item);
                        }
                    }
                    for (ItemStack item : ItemManage.helmets){
                        if (String.valueOf(args[1]).equals(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.STRING))){
                            Utils.additem(p, item);
                        }
                    }
                    for (ItemStack item : ItemManage.elytras){
                        if (String.valueOf(args[1]).equals(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.STRING))){
                            Utils.additem(p, item);
                        }
                    }
                    for (ItemStack item : ItemManage.leggings){
                        if (String.valueOf(args[1]).equals(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.STRING))){
                            Utils.additem(p, item);
                        }
                    }
                    for (ItemStack item : ItemManage.boots){
                        if (String.valueOf(args[1]).equals(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.STRING))){
                            Utils.additem(p, item);
                        }
                    }
                }
            }else {
                p.sendMessage(xBxTcore.getMessageManager().MasterMessage(p, Messages.NotOp));
            }
        }

        return false;
    }
}
