package Plugin.Managers;

import Plugin.Model.MinaBoxPvp;
import Plugin.Utils.ColorUtils;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

import static Plugin.Managers.MessageManager.*;
import static Plugin.Utils.ColorUtils.*;
import static Plugin.Utils.Utils.additem;

public class ItemManage {

    private final xBxTcore plugin;

    public static ArrayList<ItemStack> coinNormal = new ArrayList<>();
    public static ArrayList<ItemStack> coinCompact = new ArrayList<>();

    public static ArrayList<ItemStack> helmets = new ArrayList<>();
    public static ArrayList<ItemStack> elytras = new ArrayList<>();
    public static ArrayList<ItemStack> leggings = new ArrayList<>();
    public static ArrayList<ItemStack> boots = new ArrayList<>();

    public static ArrayList<ItemStack> swords = new ArrayList<>();
    public static ArrayList<ItemStack> pickaxes = new ArrayList<>();

    public static ArrayList<ItemStack> moneyNormal = new ArrayList<>();
    public static ArrayList<ItemStack> moneyCompact = new ArrayList<>();

    public static ArrayList<ItemStack> moneyPrincipal = new ArrayList<>();

    public static ArrayList<ItemStack> especialItems = new ArrayList<>();

    public static ArrayList<ItemStack> shurlkerBoxs = new ArrayList<>();
    public static ArrayList<ItemStack> shurlkerBoxsPersonal = new ArrayList<>();

    public static ArrayList<ItemStack> keys = new ArrayList<>();

    public static ArrayList<ItemStack> wikis = new ArrayList<>();

    public ItemManage(xBxTcore plugin) {
        this.plugin = plugin;

        wikis.add(wiki());

        //////////////
        //////////////
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
        lore.add(applyGradient("<#19fbff>Usalo para tradear con<#2a7c7d>"));
        lore.add(applyGradient("<#19fbff>los aldeano de las minas<#2a7c7d>"));
        lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
        lore.add(ChatColor.translateAlternateColorCodes('&',"&8" + "Mas información con " + ColorLink + "/help"));

        coinNormal.add(newItemBoxPVP(Material.SLIME_BALL,"<#5E7C16>Moneda Inicial<#80C71F>", lore, 0,true));
        coinCompact.add(newItemBoxPVP(Material.SLIME_BLOCK,"<#5E7C16>Moneda Inicial Compacta<#80C71F>", lore, 0,true));

        lore.clear();
        lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
        lore.add(applyGradient("<#19fbff>Usalo para tradear con los<#2a7c7d>"));
        lore.add(applyGradient("<#19fbff>aldeano de la superficie<#2a7c7d>"));
        lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
        lore.add(ChatColor.translateAlternateColorCodes('&',"&8" + "Mas información con " + ColorLink + "/help"));

        moneyNormal.add(newItemBoxPVP(Material.COPPER_INGOT ,"<#D07131>Moneda de Cobre<#e6a87e>", lore, 0,true));
        moneyNormal.add(newItemBoxPVP(Material.IRON_INGOT ,"<#e3e3e3>Moneda de Hierro<#8f8f8f>", lore, 0,true));
        moneyNormal.add(newItemBoxPVP(Material.GOLD_INGOT ,"<#d9aa02>Moneda de Oro<#ffd952>", lore, 0,true));
        moneyNormal.add(newItemBoxPVP(Material.EMERALD ,"<#24ff24>Moneda de Esmeralda<#a8ffa8>", lore, 0,true));
        moneyNormal.add(newItemBoxPVP(Material.PEARLESCENT_FROGLIGHT ,"<#ff38f8>Moneda de Especial<#ffa1fc>", lore, 0,true));

        moneyCompact.add(newItemBoxPVP(Material.COPPER_BLOCK ,"<#D07131>Moneda Compacta de Cobre<#e6a87e>", lore, 0,true));
        moneyCompact.add(newItemBoxPVP(Material.IRON_BLOCK ,"<#e3e3e3>Moneda Compacta de Hierro<#8f8f8f>", lore, 0,true));
        moneyCompact.add(newItemBoxPVP(Material.GOLD_BLOCK ,"<#d9aa02>Moneda Compacta de Oro<#ffd952>", lore, 0,true));
        moneyCompact.add(newItemBoxPVP(Material.EMERALD_BLOCK ,"<#24ff24>Moneda Compacta de Esmeralda<#a8ffa8>", lore, 0,true));
        moneyCompact.add(newItemBoxPVP(Material.CRYING_OBSIDIAN ,"<#ff38f8>Moneda Compacta de Especial<#ffa1fc>", lore, 0,true));

        //////////////
        //////////////

        lore.clear();
        lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
        lore.add(applyGradient("<#19fbff>Moneda para tradear items<#2a7c7d>"));
        lore.add(applyGradient("<#19fbff>con los aldeano<#2a7c7d>"));
        lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
        lore.add(ChatColor.translateAlternateColorCodes('&',"&8" + "Mas información con " + ColorLink + "/help"));

        moneyPrincipal.add(newItemBoxPVP(Material.AMETHYST_SHARD ,"<##d5a2fa>Fragmento Moneda Principal<#9f5ad1>", lore, 0,true));
        moneyPrincipal.add(newItemBoxPVP(Material.AMETHYST_CLUSTER ,"<##d5a2fa>Moneda Principal<#9f5ad1>", lore, 0,true));
        moneyPrincipal.add(newItemBoxPVP(Material.AMETHYST_BLOCK ,"<##d5a2fa>Moneda Principal Compacta<#9f5ad1>", lore, 0,true));

        //////////////
        //////////////

        lore.clear();
        lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
        lore.add(applyGradient("<#19fbff>Aumenta la regeneración<#2a7c7d>"));
        lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
        lore.add(ChatColor.translateAlternateColorCodes('&',"&8" + "Mas información con " + ColorLink + "/help"));
        helmets.add(newItemBoxPVP(Material.LEATHER_HELMET ,"<#5E7C16>Casco Inicial<#80C71F>", lore, 0,"5E7C16"));

        lore.clear();
        lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
        lore.add(applyGradient("<#19fbff>Aumenta la vida <#2a7c7d>"));
        lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
        lore.add(ChatColor.translateAlternateColorCodes('&',"&8" + "Mas información con " + ColorLink + "/help"));
        elytras.add(newItemBoxPVP(Material.ELYTRA ,"<#5E7C16>Elytra Inicial<#80C71F>", lore, 0, true));

        lore.clear();
        lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
        lore.add(applyGradient("<#19fbff>Aumenta la protection<#2a7c7d>"));
        lore.add(applyGradient("<#19fbff>a daño normal<#2a7c7d>"));
        lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
        lore.add(ChatColor.translateAlternateColorCodes('&',"&8" + "Mas información con " + ColorLink + "/help"));
        leggings.add(newItemBoxPVP(Material.LEATHER_LEGGINGS ,"<#5E7C16>Pantalones Inicial<#80C71F>", lore, 0,"5E7C16"));

        lore.clear();
        lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
        lore.add(applyGradient("<#19fbff>Aumenta la protection<#2a7c7d>"));
        lore.add(applyGradient("<#19fbff>a daño explosión<#2a7c7d>"));
        lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
        lore.add(ChatColor.translateAlternateColorCodes('&',"&8" + "Mas información con " + ColorLink + "/help"));
        boots.add(newItemBoxPVP(Material.LEATHER_BOOTS ,"<#5E7C16>Botas Inicial<#80C71F>", lore, 0,"5E7C16"));

        lore.clear();
        lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
        lore.add(applyGradient("<#19fbff>Es mas probable que<#2a7c7d>"));
        lore.add(applyGradient("<#19fbff>que te dos items<#2a7c7d>"));
        lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
        lore.add(ChatColor.translateAlternateColorCodes('&',"&8" + "Mas información con " + ColorLink + "/help"));
        pickaxes.add(newItemBoxPVP(Material.NETHERITE_PICKAXE ,"<#5E7C16>Pico Inicial<#80C71F>", lore, 0,true));

        lore.clear();
        lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
        lore.add(applyGradient("<#19fbff>Mayor daño<#2a7c7d>"));
        lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
        lore.add(ChatColor.translateAlternateColorCodes('&',"&8" + "Mas información con " + ColorLink + "/help"));
        swords.add(newItemBoxPVP(Material.NETHERITE_SWORD ,"<#5E7C16>Espada Inicial<#80C71F>", lore, 0,true));

        //////////////
        //////////////

        lore.clear();
        lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
        lore.add(applyGradient("<#19fbff>Este item da una mejora<#2a7c7d>"));
        lore.add(applyGradient("<#19fbff>en el combate<#2a7c7d>"));
        lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
        lore.add(ChatColor.translateAlternateColorCodes('&',"&8" + "Mas información con " + ColorLink + "/help"));

        especialItems.add(newItemBoxPVP(Material.FERMENTED_SPIDER_EYE ,"<#FF2121>Desgarre de daño<#FF8585>", lore, "DañoBonus",true));
        especialItems.add(newItemBoxPVP(Material.CHARCOAL ,"<##4A4A4A>Bendición de wither<#282828>", lore, "DañoPorWither",true));

        //////////////
        //////////////

        lore.clear();
        lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
        lore.add(applyGradient("<#19fbff>Con esta llave puedes<#2a7c7d>"));
        lore.add(applyGradient("<#19fbff>abrir una caja<#2a7c7d>"));
        lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
        lore.add(ChatColor.translateAlternateColorCodes('&',"&8" + "Mas información con " + ColorLink + "/help"));

        keys.add(newItemBoxPVP(Material.TRIPWIRE_HOOK ,"<#D6D6D6>Llave Basica<#919191>", lore,0,true));
        keys.add(newItemBoxPVP(Material.TRIPWIRE_HOOK ,"<#66FFF3>Llave Especial<#307D96>", lore, 1,true));
        keys.add(newItemBoxPVP(Material.TRIPWIRE_HOOK ,"<#CB66FF>Llave Epica<#5F3096>", lore, 2,true));
        keys.add(newItemBoxPVP(Material.TRIPWIRE_HOOK ,"<#F58100>Llave VIP<#F1B674>", lore, 3,true));
        /////////
        int i = 0;
        ItemStack itemStack;
        ItemMeta itemMeta;
        for (MinaBoxPvp mina : xBxTcore.getAutoFillsBox().minas){
            i++;
            if (i >= 17) return;

            lore.clear();
            lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
            lore.add(applyGradient("<#19fbff>Usalo para tradear con<#2a7c7d>"));
            lore.add(applyGradient("<#19fbff>los aldeano de las minas<#2a7c7d>"));
            lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
            lore.add(ChatColor.translateAlternateColorCodes('&',"&8" + "Mas información con " + ColorLink + "/help"));

            coinNormal.add(newItemBoxPVP(Utils.colorToMaterial(mina.getMaterial(), Material.BLACK_DYE),"<#" + ColorUtils.blockToHex(mina.getMaterial()) + ">Moneda Tier " + Utils.arabicToRoman(i) + "<#" + ColorUtils.modifyColorHexWithHLS(ColorUtils.blockToHex(mina.getMaterial()), 0.1f, 0.3f, -0.3f) + ">", lore, i, true));
            coinCompact.add(newItemBoxPVP(mina.getMaterial(),"<#" + ColorUtils.blockToHex(mina.getMaterial()) + ">Moneda Compacta Tier " + Utils.arabicToRoman(i) + "<#" + ColorUtils.modifyColorHexWithHLS(ColorUtils.blockToHex(mina.getMaterial()), 0.1f, 0.3f, -0.3f) + ">", lore, i, true));

            lore.clear();
            lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
            lore.add(applyGradient("<#19fbff>Aumenta la regeneración<#2a7c7d>"));
            lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
            lore.add(ChatColor.translateAlternateColorCodes('&',"&8" + "Mas información con " + ColorLink + "/help"));
            helmets.add(newItemBoxPVP(Material.LEATHER_HELMET ,"<#" + ColorUtils.blockToHex(mina.getMaterial()) + ">Casco Tier " + Utils.arabicToRoman(i) + "<#" + ColorUtils.modifyColorHexWithHLS(ColorUtils.blockToHex(mina.getMaterial()), 0.1f, 0.3f, -0.3f) + ">", lore, i, ColorUtils.blockToHex(mina.getMaterial())));

            lore.clear();
            lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
            lore.add(applyGradient("<#19fbff>Aumenta la vida maxima<#2a7c7d>"));
            lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
            lore.add(ChatColor.translateAlternateColorCodes('&',"&8" + "Mas información con " + ColorLink + "/help"));
            elytras.add(newItemBoxPVP(Material.ELYTRA ,"<#" + ColorUtils.blockToHex(mina.getMaterial()) + ">Elytra Tier " + Utils.arabicToRoman(i) + "<#" + ColorUtils.modifyColorHexWithHLS(ColorUtils.blockToHex(mina.getMaterial()), 0.1f, 0.3f, -0.3f) + ">", lore, i,true));

            lore.clear();
            lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
            lore.add(applyGradient("<#19fbff>Aumenta la protection<#2a7c7d>"));
            lore.add(applyGradient("<#19fbff>a daño normal<#2a7c7d>"));
            lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
            lore.add(ChatColor.translateAlternateColorCodes('&',"&8" + "Mas información con " + ColorLink + "/help"));
            leggings.add(newItemBoxPVP(Material.LEATHER_LEGGINGS ,"<#" + ColorUtils.blockToHex(mina.getMaterial()) + ">Pantalones Tier " + Utils.arabicToRoman(i) + "<#" + ColorUtils.modifyColorHexWithHLS(ColorUtils.blockToHex(mina.getMaterial()), 0.1f, 0.3f, -0.3f) + ">", lore, i, ColorUtils.blockToHex(mina.getMaterial())));

            lore.clear();
            lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
            lore.add(applyGradient("<#19fbff>Aumenta la protection<#2a7c7d>"));
            lore.add(applyGradient("<#19fbff>a daño explosión<#2a7c7d>"));
            lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
            lore.add(ChatColor.translateAlternateColorCodes('&',"&8" + "Mas información con " + ColorLink + "/help"));
            boots.add(newItemBoxPVP(Material.LEATHER_BOOTS ,"<#" + ColorUtils.blockToHex(mina.getMaterial()) + ">Botas Tier " + Utils.arabicToRoman(i) + "<#" + ColorUtils.modifyColorHexWithHLS(ColorUtils.blockToHex(mina.getMaterial()), 0.1f, 0.3f, -0.3f) + ">", lore, i, ColorUtils.blockToHex(mina.getMaterial())));

            lore.clear();
            lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
            lore.add(applyGradient("<#19fbff>Es mas probable que<#2a7c7d>"));
            lore.add(applyGradient("<#19fbff>que te dos items<#2a7c7d>"));
            lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
            lore.add(ChatColor.translateAlternateColorCodes('&',"&8" + "Mas información con " + ColorLink + "/help"));
            pickaxes.add(newItemBoxPVP(Material.NETHERITE_PICKAXE ,"<#" + ColorUtils.blockToHex(mina.getMaterial()) + ">Pico Tier " + Utils.arabicToRoman(i) + "<#" + ColorUtils.modifyColorHexWithHLS(ColorUtils.blockToHex(mina.getMaterial()), 0.1f, 0.3f, -0.3f) + ">", lore, i, true));

            lore.clear();
            lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
            lore.add(applyGradient("<#19fbff>Mayor daño<#2a7c7d>"));
            lore.add(ChatColor.translateAlternateColorCodes('&',Coloritem + " "));
            lore.add(ChatColor.translateAlternateColorCodes('&',"&8" + "Mas información con " + ColorLink + "/help"));
            itemStack = newItemBoxPVP(Material.NETHERITE_SWORD ,"<#" + ColorUtils.blockToHex(mina.getMaterial()) + ">Espada Tier " + Utils.arabicToRoman(i) + "<#" + ColorUtils.modifyColorHexWithHLS(ColorUtils.blockToHex(mina.getMaterial()), 0.1f, 0.3f, -0.3f) + ">", lore, i, true);
            itemMeta = itemStack.getItemMeta();
            itemMeta.addEnchant(Enchantment.DAMAGE_ALL , i * 2 , true);
            itemStack.setItemMeta(itemMeta);
            swords.add(itemStack);

            lore.clear();
            shurlkerBoxsPersonal.add(newItemBoxPVP(Utils.colorToMaterial(mina.getMaterial(), Material.BLACK_SHULKER_BOX) ,"<#" + ColorUtils.blockToHex(mina.getMaterial()) + ">Shurlker Box Personal<#" + ColorUtils.modifyColorHexWithHLS(ColorUtils.blockToHex(mina.getMaterial()), 0.1f, 0.3f, -0.3f) + ">", lore, i, true));

            shurlkerBoxs.add(newItemBoxPVP(Utils.colorToMaterial(mina.getMaterial(), Material.BLACK_SHULKER_BOX) ,"<#" + ColorUtils.blockToHex(mina.getMaterial()) + ">Kit Tier " + Utils.arabicToRoman(i) + "<#" + ColorUtils.modifyColorHexWithHLS(ColorUtils.blockToHex(mina.getMaterial()), 0.1f, 0.3f, -0.3f) + ">", lore, i, true));
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
            if (item.getType() == Material.NETHERITE_PICKAXE){
                meta.addEnchant(Enchantment.DIG_SPEED, 6, true);
            }
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

    public ItemStack wiki(){
        ItemStack item = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) item.getItemMeta();
        assert meta != null;
        meta.setAuthor("Ceres");
        meta.setTitle("xbxt wiki");
        meta.addPage(ChatColor.translateAlternateColorCodes('&', "Commandos\n\nMessages\n\nItems De BoxPvp\n\nDuelos"));
        meta.addPage(ChatColor.translateAlternateColorCodes('&',"&lCommandos\n" + Colorplayer + " /savekit o /sk &r\nEste Comando guarda tu inventario en un kit para" +
                "usarse primero tiene que poner el nombre de del kit SIN ESPACIOS por ejemplo '/sk mejor_kit' puede usar códigos de color de minecraft y también " +
                "puedes ponele un icono."));

        meta.addPage(ChatColor.translateAlternateColorCodes('&',"&lCommandos\n" + Colorplayer + "/kitfavorite o /kf&r\nGuardas un kit " +
                "como favorito para cuando mueras reaparezca con el kit seleccionaste para usarlo tienes que poner " +
                "El nombre del CON LOS CÓDIGOS DE COLOR y si escribes el comando solo carga el kit que tenias guardado."));

        meta.addPage(ChatColor.translateAlternateColorCodes('&',"&lCommandos\n" + Colorplayer + "/delkit o /dk\n&r" + "Elimina el kit " +
                "seleccionado para usarse tienes que poner el nombre de tu kit.\n" +
                Colorplayer + "/lobby o /spawn\n&rte lleva al mundo principal.\n" +
                Colorplayer + "/rank\n&rVes el top kills y rachas de los jugadores en linea"));

        item.setItemMeta(meta);
        return item;
    }
}
