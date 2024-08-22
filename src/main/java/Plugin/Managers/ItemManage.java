package Plugin.Managers;

import Plugin.Environments.AutoFillsBox;
import Plugin.Model.MinaBoxPvp;
import Plugin.Utils.ColorUtils;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static Plugin.Managers.MessageManager.Colorinfo;
import static Plugin.Utils.Utils.additem;

public class ItemManage {


    private static final ArrayList<ItemStack> coinNormal = new ArrayList<>();
    private static final ArrayList<ItemStack> coinCompact = new ArrayList<>();



    public ItemManage() {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(Colorinfo + "Sirve para tradear con los aldeanos");
        coinNormal.add(newItemBoxPVP(Material.SLIME_BALL,"<#5E7C16>Moneda Inicial<#80C71F>", lore));
        coinCompact.add(newItemBoxPVP(Material.SLIME_BLOCK,"<#5E7C16>Moneda Inicial Compacta<#80C71F>", lore));
        /////////
        int i = 0;
        for (MinaBoxPvp mina : xBxTcore.getAutoFillsBox().minas){
            i++;
            coinNormal.add(newItemBoxPVP(mina.getMaterial(),"<#" + ColorUtils.blockToHex(mina.getMaterial()) + ">Moneda Tier " + Utils.arabicToRoman(i) + "<#" + ColorUtils.modifyColorHexWithHLS(ColorUtils.blockToHex(mina.getMaterial()), 0, 0, 0) + ">", lore));
            coinCompact.add(newItemBoxPVP(mina.getMaterial(),"<#" + ColorUtils.blockToHex(mina.getMaterial()) + ">Moneda Compacta Tier " + Utils.arabicToRoman(i) + "<#" + ColorUtils.modifyColorHexWithHLS(ColorUtils.blockToHex(mina.getMaterial()), 0, 0, 0) + ">", lore));
        }
    }

    public void AddItemMine(Player player, Material material){
        switch(material){
            case PEARLESCENT_FROGLIGHT -> additem(player, new ItemStack(Material.PEARLESCENT_FROGLIGHT));
            //////
            case DEEPSLATE_COPPER_ORE -> additem(player, new ItemStack(Material.DEEPSLATE_COPPER_ORE));
            case COPPER_BLOCK -> additem(player, new ItemStack(Material.COPPER_BLOCK));
            //////
            case DEEPSLATE_IRON_ORE -> additem(player, new ItemStack(Material.DEEPSLATE_IRON_ORE));
            case IRON_BLOCK -> additem(player, new ItemStack(Material.IRON_BLOCK));
            //////
            case DEEPSLATE_GOLD_ORE -> additem(player, new ItemStack(Material.DEEPSLATE_GOLD_ORE));
            case GOLD_BLOCK -> additem(player, new ItemStack(Material.GOLD_BLOCK));
            //////
            case DEEPSLATE_EMERALD_ORE -> additem(player, new ItemStack(Material.DEEPSLATE_EMERALD_ORE));
            case EMERALD_BLOCK -> additem(player, new ItemStack(Material.EMERALD_BLOCK));
            //////
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

    public static ItemStack newItemBoxPVP(Material material, String tile, ArrayList<String> lore){
        ItemStack item;
        item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtils.applyGradient(tile));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }
}
