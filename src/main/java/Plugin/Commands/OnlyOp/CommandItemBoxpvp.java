package Plugin.Commands.OnlyOp;

import Plugin.Messages.Enum.Messages;
import Plugin.BoxPvp.ItemsBoxPvp.ItemManage;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

import static Plugin.Messages.MessageManager.MasterMessageLocated;

public class CommandItemBoxpvp implements CommandExecutor {

    private final xBxTcore plugin;
    private Player player;

    public CommandItemBoxpvp(xBxTcore plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@Nullable CommandSender sender,@Nullable Command cmd,@Nullable String label, String[] args) {
        if (sender instanceof Player p) {
            player = p;
            if (!p.isOp()) {
                p.sendMessage(MasterMessageLocated(p, Messages.Generic_NotOp));
                return false;
            }
        }

        if (args.length == 1) {
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
                    addItems(ItemManage.swords);
                    addItems(ItemManage.pickaxes);
                    addItems(ItemManage.especialItems);
                    addItems(ItemManage.moneyPrincipal);
                    addItems(ItemManage.shurlkerBoxs);

                }
                case "coins" -> {
                    addItems(ItemManage.coinNormal);
                    addItems(ItemManage.coinCompact);
                }
                case "coin_normal" -> addItems(ItemManage.coinNormal);
                case "coin_compact" -> addItems(ItemManage.coinCompact);
                case "helmets" -> addItems(ItemManage.helmets);
                case "elytra" -> addItems(ItemManage.elytras);
                case "leggings" -> addItems(ItemManage.leggings);
                case "boots" -> addItems(ItemManage.boots);
                case "sword" -> addItems(ItemManage.swords);
                case "pickaxe" -> addItems(ItemManage.pickaxes);
                case "money_principal" -> addItems(ItemManage.moneyPrincipal);
                case "kits" -> addItems(ItemManage.shurlkerBoxs);
                case "moneys" -> {
                    addItems(ItemManage.moneyCompact);
                    addItems(ItemManage.moneyNormal);
                    addItems(ItemManage.moneyPrincipal);
                }
                case "shurlker_Boxs_Personal" -> addItems(ItemManage.shurlkerBoxsPersonal);
                case "keys" -> addItems(ItemManage.keys);
                case "especial_items" -> addItems(ItemManage.especialItems);
                case "money_normal" -> addItems(ItemManage.moneyNormal);
                case "money_compact" -> addItems(ItemManage.moneyCompact);
            }
        } else if (args.length >= 3 && args[0].equals("tier")){
            player = Bukkit.getPlayer(args[2]);
            if (player == null) {
                return false;
            }
            for (ItemStack item : ItemManage.helmets){
                if (String.valueOf(args[1]).equals(String.valueOf(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER)))){
                    Utils.additem(player, item);
                }
            }
            for (ItemStack item : ItemManage.elytras){
                if (String.valueOf(args[1]).equals(String.valueOf(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER)))){
                    Utils.additem(player, item);
                }
            }
            for (ItemStack item : ItemManage.leggings){
                if (String.valueOf(args[1]).equals(String.valueOf(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER)))){
                    Utils.additem(player, item);
                }
            }
            for (ItemStack item : ItemManage.boots){
                if (String.valueOf(args[1]).equals(String.valueOf(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER)))){
                    Utils.additem(player, item);
                }
            }
            for (ItemStack item : ItemManage.swords){
                if (String.valueOf(args[1]).equals(String.valueOf(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER)))){
                    Utils.additem(player, item);
                }
            }
            for (ItemStack item : ItemManage.pickaxes) {
                if (String.valueOf(args[1]).equals(String.valueOf(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER)))) {
                    Utils.additem(player, item);
                }
            }
            if (args.length == 4 && args[3].equals("message")) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', MasterMessageLocated(player, Messages.Reward_BuysTitel)));
            }
            return true;
        }


        return true;
    }




    public void addItems(ArrayList<ItemStack> itemStacks){
        for (ItemStack item : itemStacks){
            Utils.additem(player, item);
        }
    }
}
