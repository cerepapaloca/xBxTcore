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
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class CommandItemBoxpvp implements CommandExecutor {

    private final xBxTcore plugin;
    private Player player;

    public CommandItemBoxpvp(xBxTcore plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@Nullable CommandSender sender,@Nullable Command cmd,@Nullable String label, String[] args) {
        if(sender instanceof Player p){
            player = p;
            if (p.isOp()){
                if (args.length == 1){
                    switch (args[0]){
                        case "*" -> {
                            addItems(ItemManage.coinNormal);
                            addItems(ItemManage.coinCompact);
                            addItems(ItemManage.helmets);
                            addItems(ItemManage.elytras);
                            addItems(ItemManage.leggings);
                            addItems(ItemManage.boots);
                            addItems(ItemManage.moneyNormal);
                            addItems(ItemManage.moneyCompact);
                            addItems(ItemManage.sword);
                            addItems(ItemManage.pickaxe);
                            addItems(ItemManage.especialItems);
                            addItems(ItemManage.moneyPrincipal);
                        }
                        case "coins" -> {
                            addItems(ItemManage.coinNormal);
                            addItems(ItemManage.coinCompact);
                        }
                        case "coins_normal" -> addItems(ItemManage.coinNormal);
                        case "coin_compact" -> addItems(ItemManage.coinCompact);
                        case "helmets" -> addItems(ItemManage.helmets);
                        case "elytra" -> addItems(ItemManage.elytras);
                        case "leggings" -> addItems(ItemManage.leggings);
                        case "boots" -> addItems(ItemManage.boots);
                        case "sword" -> addItems(ItemManage.sword);
                        case "pickaxe" -> addItems(ItemManage.pickaxe);
                        case "money_principal" -> addItems(ItemManage.moneyPrincipal);
                        case "moneys" -> {
                            addItems(ItemManage.moneyCompact);
                            addItems(ItemManage.moneyNormal);
                            addItems(ItemManage.moneyPrincipal);
                        }
                        case "especial_items" -> addItems(ItemManage.especialItems);
                        case "money_normal" -> addItems(ItemManage.moneyNormal);
                        case "money_compact" -> addItems(ItemManage.moneyCompact);
                    }
                } else if (args.length == 2 && args[0].equals("tier")){
                    for (ItemStack item : ItemManage.coinCompact){
                        if (String.valueOf(args[1]).equals(String.valueOf(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER)))){
                            Utils.additem(p, item);
                        }
                    }
                    for (ItemStack item : ItemManage.coinNormal){
                        if (String.valueOf(args[1]).equals(String.valueOf(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER)))){
                            Utils.additem(p, item);
                        }
                    }
                    for (ItemStack item : ItemManage.helmets){
                        if (String.valueOf(args[1]).equals(String.valueOf(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER)))){
                            Utils.additem(p, item);
                        }
                    }
                    for (ItemStack item : ItemManage.elytras){
                        if (String.valueOf(args[1]).equals(String.valueOf(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER)))){
                            Utils.additem(p, item);
                        }
                    }
                    for (ItemStack item : ItemManage.leggings){
                        if (String.valueOf(args[1]).equals(String.valueOf(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER)))){
                            Utils.additem(p, item);
                        }
                    }
                    for (ItemStack item : ItemManage.boots){
                        if (String.valueOf(args[1]).equals(String.valueOf(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER)))){
                            Utils.additem(p, item);
                        }
                    }
                    for (ItemStack item : ItemManage.sword){
                        if (String.valueOf(args[1]).equals(String.valueOf(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER)))){
                            Utils.additem(p, item);
                        }
                    }
                    for (ItemStack item : ItemManage.pickaxe) {
                        if (String.valueOf(args[1]).equals(String.valueOf(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER)))) {
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

    public void addItems(ArrayList<ItemStack> itemStacks){
        for (ItemStack item : itemStacks){
            Utils.additem(player, item);
        }
    }
}
