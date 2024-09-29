package Plugin.Commands.OnlyOp;

import Plugin.BoxPvp.BoxPvpSection;
import Plugin.BoxPvp.ItemsBoxPvp.Enum.TagsRanges;
import Plugin.Messages.Messages.Messages;
import Plugin.BoxPvp.ItemsBoxPvp.ItemManage;
import Plugin.Security.SystemBan.AutoBan;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

import static Plugin.Messages.MessageManager.*;
import static Plugin.Utils.ColorUtils.applyGradient;

public class CommandItemBoxpvp implements CommandExecutor {

    private final xBxTcore plugin;
    private Player player;

    public CommandItemBoxpvp(xBxTcore plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@Nullable CommandSender sender,@Nullable Command cmd,@Nullable String label, String[] args) {
        assert sender != null;
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
        }else if (args.length == 3 && args[0].equals("tag")){
            AutoBan.checkDupes(player);
            ArrayList<String> lore = new ArrayList<>();
            lore.add(" ");
            lore.add(applyGradient("<#19fbff>Tendras ese rango durante un<#2a7c7d>"));
            lore.add(applyGradient("<#19fbff>tiempo si haces click en el<#2a7c7d>"));
            lore.add(applyGradient("<#19fbff>aire con el tag en la mano<#2a7c7d>"));
            lore.add(" ");
            TagsRanges range = null;
            String title = null;
            try {
                range = TagsRanges.valueOf(args[1]);
            }catch (IllegalArgumentException e){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorError + "Ese rango no existe"));
            }
            String time = Utils.TimeToString(Utils.StringToMilliseconds(args[2]), 2);

            if (range == null)return false;
            switch (range){
                case vip -> title = String.format("<#19fbff>Rango VIP %s<#2a7c7d>", time);
                case vote -> title = String.format("<#FDC661>Rango VOTE %s<#FF7302>", time);
                case hacker -> title = String.format("<#FDC661>Rango HACKER %s<#FF7302>", time);
            }

            ItemStack tag = BoxPvpSection.getItemManage().newItemBoxPVP(Material.NAME_TAG ,title, lore,true);
            ItemMeta tagMeta = tag.getItemMeta();
            tagMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "uuid"),  PersistentDataType.STRING, UUID.randomUUID().toString());
            tagMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "range"), PersistentDataType.STRING, range.name());
            tagMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "duration"), PersistentDataType.LONG, Utils.StringToMilliseconds(args[2]));
            tag.setItemMeta(tagMeta);
            Utils.additem(player, tag);
        }
        return true;
    }

    public void addItems(ArrayList<ItemStack> itemStacks){
        for (ItemStack item : itemStacks){
            Utils.additem(player, item);
        }
    }
}
