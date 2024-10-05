package xyz.xbcore.Commands.OnlyOp;

import xyz.xbcore.BoxPvp.BoxPvpSection;
import xyz.xbcore.BoxPvp.ItemsBoxPvp.Enum.TagsRanges;
import xyz.xbcommun.Command.BaseTabCommand;
import xyz.xbcommun.Messages.Messages.Messages;
import xyz.xbcore.BoxPvp.ItemsBoxPvp.ItemManage;
import xyz.xbcommun.Utils.UtilsGlobal;
import xyz.xbcore.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static xyz.xbcommun.Messages.MessageManager.*;
import static xyz.xbcommun.Utils.ColorUtils.applyGradient;

public class CommandItemBoxpvp extends BaseTabCommand {

    private final xBxTcore plugin;
    private Player player;

    public CommandItemBoxpvp(xBxTcore plugin) {
        super("itemboxpvp",
                "/itemboxpvp",
                "xbxtcore.command.itemboxpvp",
                true,
                "");
        this.plugin = plugin;
    }


    public void addItems(ArrayList<ItemStack> itemStacks) {
        for (ItemStack item : itemStacks) {
            UtilsGlobal.additem(player, item);
        }
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        assert sender != null;
        if (sender instanceof Player p) {
            player = p;
            if (!p.isOp()) {
                p.sendMessage(MasterMessageLocated(p, Messages.Generic_NotOp));
                return ;
            }
        }

        if (args.length == 1) {
            switch (args[0]){
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
            if (player == null) return;
            for (ItemStack item : ItemManage.helmets){
                if (String.valueOf(args[1]).equals(String.valueOf(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER)))){
                    UtilsGlobal.additem(player, item);
                }
            }
            for (ItemStack item : ItemManage.elytras){
                if (String.valueOf(args[1]).equals(String.valueOf(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER)))){
                    UtilsGlobal.additem(player, item);
                }
            }
            for (ItemStack item : ItemManage.leggings){
                if (String.valueOf(args[1]).equals(String.valueOf(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER)))){
                    UtilsGlobal.additem(player, item);
                }
            }
            for (ItemStack item : ItemManage.boots){
                if (String.valueOf(args[1]).equals(String.valueOf(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER)))){
                    UtilsGlobal.additem(player, item);
                }
            }
            for (ItemStack item : ItemManage.swords){
                if (String.valueOf(args[1]).equals(String.valueOf(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER)))){
                    UtilsGlobal.additem(player, item);
                }
            }
            for (ItemStack item : ItemManage.pickaxes) {
                if (String.valueOf(args[1]).equals(String.valueOf(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER)))) {
                    UtilsGlobal.additem(player, item);
                }
            }
            if (args.length == 4 && args[3].equals("message")) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', MasterMessageLocated(player, Messages.Reward_BuysTitel)));
            }
        }else if (args.length == 4 && args[0].equals("tag")){
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
            String time = UtilsGlobal.TimeToString(UtilsGlobal.StringToMilliseconds(args[2]), 2);

            if (range == null)return ;
            switch (range){
                case vip -> title = String.format("<#FDC661>Rango VIP %s<#FF7302>", time);
                case vote -> title = String.format("<#FFE50E>Rango VOTE %s<#FDF192>", time);
                case hacker -> title = String.format("<#690000>Rango HACKER %s<#FF0000>", time);
            }

            ItemStack tag = BoxPvpSection.getItemManage().newItemBoxPVP(Material.NAME_TAG ,title, lore,true);
            ItemMeta tagMeta = tag.getItemMeta();
            tagMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "uuid"),  PersistentDataType.STRING, UUID.randomUUID().toString());
            tagMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "range"), PersistentDataType.STRING, range.name());
            tagMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "duration"), PersistentDataType.LONG, UtilsGlobal.StringToMilliseconds(args[2]));
            tag.setItemMeta(tagMeta);
            Player player = Bukkit.getPlayer(args[3]);
            if (player != null) {
                UtilsGlobal.additem(player, tag);
            }else {
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorError + "El jugador no existe"));
            }
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, String[] args) {
        List<String> nameworlds = new ArrayList<>();
        nameworlds.add("coins");
        nameworlds.add("coin_normal");
        nameworlds.add("coin_compact");
        nameworlds.add("helmets");
        nameworlds.add("elytra");
        nameworlds.add("leggings");
        nameworlds.add("boots");
        nameworlds.add("sword");
        nameworlds.add("tier");
        nameworlds.add("money_normal");
        nameworlds.add("money_compact");
        nameworlds.add("moneys");
        nameworlds.add("especial_items");
        nameworlds.add("kits");
        nameworlds.add("shurlker_Boxs_Personal");
        nameworlds.add("keys");
        nameworlds.add("tags");

        if (args.length == 1) {
            String currentArg = args[0].toLowerCase();
            return nameworlds.stream()
                    .filter(name -> name.startsWith(currentArg))
                    .collect(Collectors.toList());
        }

        return null;
    }
}
