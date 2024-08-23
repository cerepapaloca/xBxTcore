package Plugin.Managers;

import Plugin.Model.MinaBoxPvp;
import Plugin.Utils.ColorUtils;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

import static Plugin.Managers.MessageManager.Colorinfo;
import static Plugin.Utils.ColorUtils.colorLeatherArmor;
import static Plugin.Utils.Utils.additem;

public class ItemManage {

    private final xBxTcore plugin;

    public static ArrayList<ItemStack> coinNormal = new ArrayList<>();
    public static ArrayList<ItemStack> coinCompact = new ArrayList<>();

    public static ArrayList<ItemStack> helmets = new ArrayList<>();
    public static ArrayList<ItemStack> elytras = new ArrayList<>();
    public static ArrayList<ItemStack> leggings = new ArrayList<>();
    public static ArrayList<ItemStack> boots = new ArrayList<>();

    public static ArrayList<ItemStack> sword = new ArrayList<>();
    public static ArrayList<ItemStack> pickaxe = new ArrayList<>();

    public static ArrayList<ItemStack> moneyNormal = new ArrayList<>();
    public static ArrayList<ItemStack> moneyCompact = new ArrayList<>();

    public static ArrayList<ItemStack> especialItems = new ArrayList<>();

    public ItemManage(xBxTcore plugin) {
        this.plugin = plugin;
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&',Colorinfo + "Revisa la wiki para saber su función en tu idioma"));
        coinNormal.add(newItemBoxPVP(Material.SLIME_BALL,"<#5E7C16>Moneda Inicial<#80C71F>", lore, 0,true));
        coinCompact.add(newItemBoxPVP(Material.SLIME_BLOCK,"<#5E7C16>Moneda Inicial Compacta<#80C71F>", lore, 0,true));

        helmets.add(newItemBoxPVP(Material.LEATHER_HELMET ,"<#5E7C16>Casco Inicial<#80C71F>", lore, 0,"5E7C16"));
        elytras.add(newItemBoxPVP(Material.ELYTRA ,"<#5E7C16>Elytra Inicial<#80C71F>", lore, 0, true));
        leggings.add(newItemBoxPVP(Material.LEATHER_LEGGINGS ,"<#5E7C16>Pantalones Inicial<#80C71F>", lore, 0,"5E7C16"));
        boots.add(newItemBoxPVP(Material.LEATHER_BOOTS ,"<#5E7C16>Botas Inicial<#80C71F>", lore, 0,"5E7C16"));

        moneyNormal.add(newItemBoxPVP(Material.COPPER_INGOT ,"<#D07131>Moneda de Cobre<#e6a87e>", lore, 0,true));
        moneyNormal.add(newItemBoxPVP(Material.IRON_INGOT ,"<#e3e3e3>Moneda de Hierro<#8f8f8f>", lore, 0,true));
        moneyNormal.add(newItemBoxPVP(Material.GOLD_INGOT ,"<#d9aa02>Moneda de Oro<#ffd952>", lore, 0,true));
        moneyNormal.add(newItemBoxPVP(Material.EMERALD ,"<#24ff24>Moneda de Esmeralda<#a8ffa8>", lore, 0,true));
        moneyNormal.add(newItemBoxPVP(Material.PEARLESCENT_FROGLIGHT ,"<#ff38f8>Moneda de Especial<#ffa1fc>", lore, 0,true));

        moneyCompact.add(newItemBoxPVP(Material.COPPER_BLOCK ,"<#D07131>Moneda Compacta de Cobre<#e6a87e>", lore, 0,true));
        moneyCompact.add(newItemBoxPVP(Material.IRON_BLOCK ,"<#e3e3e3>Moneda Compacta de Hierro<#8f8f8f>", lore, 0,true));
        moneyCompact.add(newItemBoxPVP(Material.GOLD_BLOCK ,"<#d9aa02>Moneda Compacta de Oro<#ffd952>", lore, 0,true));
        moneyCompact.add(newItemBoxPVP(Material.EMERALD_BLOCK ,"<#24ff24>Moneda Compacta de Esmeralda<#a8ffa8>", lore, 0,true));
        moneyCompact.add(newItemBoxPVP(Material.PEARLESCENT_FROGLIGHT ,"<#ff38f8>Moneda Compacta de Especial<#ffa1fc>", lore, 0,true));

        especialItems.add(newItemBoxPVP(Material.FERMENTED_SPIDER_EYE ,"<#ff38f8>Bonificado de daño<#ffa1fc>", lore, "DañoBonus",true));
        especialItems.add(newItemBoxPVP(Material.CHARCOAL ,"<#ff38f8>Efecto de wither<#ffa1fc>", lore, "DañoPorWither",true));




        /////////
        int i = 0;
        ItemStack itemStack;
        ItemMeta itemMeta;
        for (MinaBoxPvp mina : xBxTcore.getAutoFillsBox().minas){
            i++;
            if (i >= 17) return;
            coinNormal.add(newItemBoxPVP(Utils.colorToMaterial(mina.getMaterial(), Material.BLACK_DYE),"<#" + ColorUtils.blockToHex(mina.getMaterial()) + ">Moneda Tier " + Utils.arabicToRoman(i) + "<#" + ColorUtils.modifyColorHexWithHLS(ColorUtils.blockToHex(mina.getMaterial()), 0.1f, 0.3f, -0.3f) + ">", lore, i, true));
            coinCompact.add(newItemBoxPVP(mina.getMaterial(),"<#" + ColorUtils.blockToHex(mina.getMaterial()) + ">Moneda Compacta Tier " + Utils.arabicToRoman(i) + "<#" + ColorUtils.modifyColorHexWithHLS(ColorUtils.blockToHex(mina.getMaterial()), 0.1f, 0.3f, -0.3f) + ">", lore, i, true));

            helmets.add(newItemBoxPVP(Material.LEATHER_HELMET ,"<#" + ColorUtils.blockToHex(mina.getMaterial()) + ">Casco Tier " + Utils.arabicToRoman(i) + "<#" + ColorUtils.modifyColorHexWithHLS(ColorUtils.blockToHex(mina.getMaterial()), 0.1f, 0.3f, -0.3f) + ">", lore, i, ColorUtils.blockToHex(mina.getMaterial())));
            elytras.add(newItemBoxPVP(Material.ELYTRA ,"<#" + ColorUtils.blockToHex(mina.getMaterial()) + ">Elytra Tier " + Utils.arabicToRoman(i) + "<#" + ColorUtils.modifyColorHexWithHLS(ColorUtils.blockToHex(mina.getMaterial()), 0.1f, 0.3f, -0.3f) + ">", lore, i,true));
            leggings.add(newItemBoxPVP(Material.LEATHER_LEGGINGS ,"<#" + ColorUtils.blockToHex(mina.getMaterial()) + ">Pantalones Tier " + Utils.arabicToRoman(i) + "<#" + ColorUtils.modifyColorHexWithHLS(ColorUtils.blockToHex(mina.getMaterial()), 0.1f, 0.3f, -0.3f) + ">", lore, i, ColorUtils.blockToHex(mina.getMaterial())));
            boots.add(newItemBoxPVP(Material.LEATHER_BOOTS ,"<#" + ColorUtils.blockToHex(mina.getMaterial()) + ">Botas Tier " + Utils.arabicToRoman(i) + "<#" + ColorUtils.modifyColorHexWithHLS(ColorUtils.blockToHex(mina.getMaterial()), 0.1f, 0.3f, -0.3f) + ">", lore, i, ColorUtils.blockToHex(mina.getMaterial())));

            itemStack = newItemBoxPVP(Material.NETHERITE_SWORD ,"<#" + ColorUtils.blockToHex(mina.getMaterial()) + ">Espada Tier " + Utils.arabicToRoman(i) + "<#" + ColorUtils.modifyColorHexWithHLS(ColorUtils.blockToHex(mina.getMaterial()), 0.1f, 0.3f, -0.3f) + ">", lore, i, true);
            itemMeta = itemStack.getItemMeta();
            itemMeta.addEnchant(Enchantment.DAMAGE_ALL , i * 2 , true);
            itemStack.setItemMeta(itemMeta);
            sword.add(itemStack);

            pickaxe.add(newItemBoxPVP(Material.NETHERITE_PICKAXE ,"<#" + ColorUtils.blockToHex(mina.getMaterial()) + ">Pico Tier " + Utils.arabicToRoman(i) + "<#" + ColorUtils.modifyColorHexWithHLS(ColorUtils.blockToHex(mina.getMaterial()), 0.1f, 0.3f, -0.3f) + ">", lore, i, true));

        }
    }

    public void AddItemMine(Player player, Material material){
        switch(material){
            //////
            case DEEPSLATE_COPPER_ORE -> additem(player, moneyNormal.get(0));
            case COPPER_BLOCK -> additem(player, moneyCompact.get(0));
            //////
            case DEEPSLATE_IRON_ORE -> additem(player, moneyNormal.get(1));
            case IRON_BLOCK -> additem(player, moneyCompact.get(1));
            //////
            case DEEPSLATE_GOLD_ORE -> additem(player, moneyNormal.get(2));
            case GOLD_BLOCK -> additem(player, moneyCompact.get(2));
            //////
            case DEEPSLATE_EMERALD_ORE -> additem(player, moneyNormal.get(3));
            case EMERALD_BLOCK -> additem(player, moneyCompact.get(3));
            //////
            case PEARLESCENT_FROGLIGHT -> additem(player, moneyNormal.get(4));
            //////
            //////
            case SLIME_BLOCK -> additem(player, coinNormal.get(0));
            //////
            case BLACK_GLAZED_TERRACOTTA -> additem(player, coinNormal.get(1));
            //////
            case RED_GLAZED_TERRACOTTA -> additem(player, coinNormal.get(2));
            //////
            case GREEN_GLAZED_TERRACOTTA -> additem(player, coinNormal.get(3));
            //////
            case BROWN_GLAZED_TERRACOTTA -> additem(player, coinNormal.get(4));
            //////
            case BLUE_GLAZED_TERRACOTTA -> additem(player, coinNormal.get(5));
            //////
            case PURPLE_GLAZED_TERRACOTTA -> additem(player, coinNormal.get(6));
            //////
            case CYAN_GLAZED_TERRACOTTA -> additem(player, coinNormal.get(7));
            //////
            case LIGHT_GRAY_GLAZED_TERRACOTTA -> additem(player, coinNormal.get(8));
            //////
            case GRAY_GLAZED_TERRACOTTA -> additem(player, coinNormal.get(9));
            //////
            case PINK_GLAZED_TERRACOTTA -> additem(player, coinNormal.get(10));
            //////
            case LIME_GLAZED_TERRACOTTA -> additem(player, coinNormal.get(11));
            //////
            case YELLOW_GLAZED_TERRACOTTA -> additem(player, coinNormal.get(12));
            //////
            case LIGHT_BLUE_GLAZED_TERRACOTTA -> additem(player, coinNormal.get(13));
            //////
            case MAGENTA_GLAZED_TERRACOTTA -> additem(player, coinNormal.get(14));
            //////
            case ORANGE_GLAZED_TERRACOTTA -> additem(player, coinNormal.get(15));
            //////
            case WHITE_GLAZED_TERRACOTTA -> additem(player, coinNormal.get(16));
            //////
        }
    }

    public ItemStack newItemBoxPVP(Material material, String tile, ArrayList<String> lore, int tier, Boolean enchant){
        ItemStack item;
        item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtils.applyGradient(tile));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER, tier);
        if (enchant){
            meta.addEnchant(Enchantment.LUCK, 1, true);
        }
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack newItemBoxPVP(Material material, String tile, ArrayList<String> lore, String keyEspacial, Boolean enchant){
        ItemStack item;
        item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtils.applyGradient(tile));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "keyEspacial"), PersistentDataType.STRING, keyEspacial);
        if (enchant){
            meta.addEnchant(Enchantment.LUCK, 1, true);
        }
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack newItemBoxPVP(Material material, String tile, ArrayList<String> lore, int tier, String hexColor){
        ItemStack item;
        item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtils.applyGradient(tile));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_DYE);
        if (tier >= 1){
            if (item.getType() == Material.LEATHER_HELMET){
                meta.addEnchant(Enchantment.LUCK, 1, true);
            } else if (item.getType() == Material.LEATHER_LEGGINGS){
                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, tier, true);
            } else if (item.getType() == Material.LEATHER_BOOTS){
                meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, tier * 2, true);
            }
        }
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER, tier);
        item.setItemMeta(meta);


        return colorLeatherArmor(item, hexColor);
    }
}
