package Plugin.Managers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static Plugin.Utils.Tools.additem;

public class ItemManage {

    public void AddItemMine(Player player, Material material){
        switch(material){
            case SLIME_BLOCK -> additem(player, new ItemStack(Material.SLIME_BALL));
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
        }
    }
}
