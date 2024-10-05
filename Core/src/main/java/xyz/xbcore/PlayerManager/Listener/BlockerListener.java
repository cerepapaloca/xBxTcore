package xyz.xbcore.PlayerManager.Listener;

import xyz.xbcore.BoxPvp.BoxPvpSection;
import xyz.xbcore.CombatLog.CombatSection;
import xyz.xbcommun.Messages.Messages.Messages;
import xyz.xbcore.xBxTcore;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityPlaceEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static xyz.xbcommun.Messages.MessageManager.MasterMessageLocated;
import static xyz.xbcore.Security.BlockByPass.*;
import static xyz.xbcore.xBxTcore.worldBoxPvp;

public class BlockerListener implements Listener {
    public final static HashSet<Location> blockLocations = new HashSet<>();
    public static final int ejey = 30;
    private static final Set<Material> materials = EnumSet.of(
            Material.CHEST, Material.TRAPPED_CHEST, Material.ENDER_CHEST,
            Material.FURNACE, Material.BLAST_FURNACE, Material.SMOKER,
            Material.CRAFTING_TABLE, Material.BREWING_STAND,
            Material.ENCHANTING_TABLE, Material.BARREL, Material.HOPPER,
            Material.DISPENSER, Material.DROPPER,
            Material.COMMAND_BLOCK, Material.JUKEBOX, Material.BEEHIVE,
            Material.BEE_NEST, Material.CARTOGRAPHY_TABLE,
            Material.LOOM, Material.STONECUTTER,
            Material.BELL, Material.NOTE_BLOCK, Material.LECTERN,
            Material.SHULKER_BOX, Material.WHITE_SHULKER_BOX, Material.ORANGE_SHULKER_BOX,
            Material.MAGENTA_SHULKER_BOX, Material.LIGHT_BLUE_SHULKER_BOX,
            Material.YELLOW_SHULKER_BOX, Material.LIME_SHULKER_BOX,
            Material.PINK_SHULKER_BOX, Material.GRAY_SHULKER_BOX,
            Material.LIGHT_GRAY_SHULKER_BOX, Material.CYAN_SHULKER_BOX,
            Material.PURPLE_SHULKER_BOX, Material.BLUE_SHULKER_BOX,
            Material.BROWN_SHULKER_BOX, Material.GREEN_SHULKER_BOX,
            Material.RED_SHULKER_BOX, Material.BLACK_SHULKER_BOX,
            Material.OAK_SIGN);
    private final Set<Material> materialsBoxPvp = EnumSet.of(Material.BLUE_GLAZED_TERRACOTTA,
            Material.BLACK_GLAZED_TERRACOTTA, Material.BROWN_GLAZED_TERRACOTTA, Material.GREEN_GLAZED_TERRACOTTA,
            Material.CYAN_GLAZED_TERRACOTTA, Material.LIGHT_BLUE_GLAZED_TERRACOTTA, Material.GRAY_GLAZED_TERRACOTTA,Material.YELLOW_GLAZED_TERRACOTTA,
            Material.LIME_GLAZED_TERRACOTTA, Material.ORANGE_GLAZED_TERRACOTTA, Material.PINK_GLAZED_TERRACOTTA, Material.RED_GLAZED_TERRACOTTA,
            Material.MAGENTA_GLAZED_TERRACOTTA, Material.WHITE_GLAZED_TERRACOTTA, Material.LIGHT_GRAY_GLAZED_TERRACOTTA, Material.PURPLE_GLAZED_TERRACOTTA,
            Material.SLIME_BLOCK,Material.DEEPSLATE_COPPER_ORE,Material.DEEPSLATE_GOLD_ORE,Material.DEEPSLATE_IRON_ORE,Material.DEEPSLATE_EMERALD_ORE,
            Material.IRON_BLOCK,Material.GOLD_BLOCK,Material.COPPER_BLOCK,Material.EMERALD_BLOCK,Material.PEARLESCENT_FROGLIGHT
            );

    private final xBxTcore plugin;

    public BlockerListener(xBxTcore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void BlockBreak(@NotNull BlockBreakEvent event) {
        checkOpCreative(event.getPlayer());
        if (!event.getPlayer().isOp() && ejey <= event.getBlock().getLocation().getBlockY() && xBxTcore.getWorldProtec().contains(event.getPlayer().getWorld())) {
            if (materialsBoxPvp.contains(event.getBlock().getType())) {
                event.setDropItems(false);
                double i = Math.random();
                double tier;
                ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
                if (item.getItemMeta() != null) {
                    tier = (double) item.getItemMeta().getPersistentDataContainer().getOrDefault(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER, -1);
                    if(tier != -1){
                        tier = (tier*3)/100;
                        if (i < tier) {
                            BoxPvpSection.getItemManage().AddItemMine(event.getPlayer(), event.getBlock().getType());
                        }
                    }
                }
                BoxPvpSection.getItemManage().AddItemMine(event.getPlayer(), event.getBlock().getType());
                return;
            }
            if (blockLocations.contains(event.getBlock().getLocation())) {
                blockLocations.remove(event.getBlock().getLocation());
                return;
            }
            event.setCancelled(true);
            event.getPlayer().sendMessage(MasterMessageLocated(event.getPlayer(), Messages.Generic_NotAllowed));
        }
    }

    @EventHandler
    public void onItemDamage(@NotNull PlayerItemDamageEvent event) {
        if (event.getPlayer().getWorld().equals(Bukkit.getWorld(worldBoxPvp))) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void BlockPlace(@NotNull BlockPlaceEvent event) {
        checkOpCreative(event.getPlayer());
        if (!event.getPlayer().isOp() && ejey <= event.getBlock().getLocation().getBlockY() && xBxTcore.getWorldProtec().contains(event.getPlayer().getWorld())) {
            if (event.getPlayer().getWorld().equals(Bukkit.getWorld(worldBoxPvp)) && (event.getBlock().getType().equals(Material.OBSIDIAN)) || event.getBlock().getType().equals(Material.COBWEB)){
                if(120 <= event.getBlock().getLocation().getBlockY()) {
                    event.setCancelled(true);
                    return;
                }
                temporalBlock(event.getBlock().getLocation());
                blockLocations.add(event.getBlock().getLocation());
                return;
            }
            event.getPlayer().sendMessage(MasterMessageLocated(event.getPlayer(), Messages.Generic_NotAllowed));
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void PlaceBlockfluit(@NotNull PlayerBucketEmptyEvent event) {
        if (!event.getPlayer().isOp() && ejey <= event.getBlock().getLocation().getBlockY() && xBxTcore.getWorldProtec().contains(event.getPlayer().getWorld())) {
            event.getPlayer().sendMessage(MasterMessageLocated(event.getPlayer(), Messages.Generic_NotAllowed));
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void PlayerInteract(@NotNull PlayerInteractEvent event) {
        if (!event.getPlayer().isOp() && ejey <= event.getPlayer().getLocation().getBlockY()) {
            Block block = event.getClickedBlock();
            if (null != block){
                if (materials.contains(block.getType()) && xBxTcore.getWorldProtec().contains(event.getPlayer().getWorld())) {
                    if (!event.getPlayer().getWorld().equals(Bukkit.getWorld(worldBoxPvp)) && !block.getType().equals(Material.YELLOW_SHULKER_BOX)) {
                        event.getPlayer().sendMessage(MasterMessageLocated(event.getPlayer(), Messages.Generic_NotAllowed));
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void PlayerInteractEnderChest(@NotNull PlayerInteractEvent event) {
        if (event.getClickedBlock() != null){
            if (event.getClickedBlock().getType().equals(Material.ENDER_CHEST) && !event.getPlayer().getWorld().equals(Bukkit.getWorld(worldBoxPvp))){
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void EntityPlace(@NotNull EntityPlaceEvent event) {
        if (!Objects.requireNonNull(event.getPlayer()).isOp() && ejey + 1 <= event.getEntity().getLocation().getBlockY())  {
            if (xBxTcore.getWorldProtec().contains(event.getEntity().getWorld())){
                event.getPlayer().sendMessage(MasterMessageLocated(event.getPlayer(), Messages.Generic_NotAllowed));
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void ExplosionPrime(@NotNull EntityExplodeEvent event) {
        if(ejey <= event.getEntity().getLocation().getBlockY()){
            event.blockList().clear();
        }
    }

    @EventHandler
    public void EntityDamage(@NotNull EntityDamageEvent event) {
        if (event.getCause() != EntityDamageEvent.DamageCause.KILL){
            if (event.getEntity() instanceof Player player){
                if (CombatSection.getCombatlogManager().isInCombat(player)){
                    return;
                }
            }
            if (ejey + 4 <= event.getEntity().getLocation().getBlockY() && (Bukkit.getWorld("lobby") == event.getEntity().getWorld()) || Bukkit.getWorld("creatorkits") == event.getEntity().getWorld()) {
                event.setCancelled(true);
                return;
            }else if (Bukkit.getWorld(worldBoxPvp) == event.getEntity().getWorld()){
                event.setCancelled(BoxPvpSection.getZoneSafeBoxPvp().isSafeZone(event.getEntity().getLocation()));
            }
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL && event.getEntity().getWorld().equals(Bukkit.getWorld(worldBoxPvp)))  {
                event.setCancelled(true);
                return;
            }
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL && event.getDamage() > 30)  {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void Exp(@NotNull PlayerExpChangeEvent event) {
        event.setAmount(0);
    }

    @EventHandler
    public void BlockDrop(@NotNull PlayerDropItemEvent event) {
        if (materials.contains(event.getItemDrop().getItemStack().getType()))event.setCancelled(true);
    }

    public void temporalBlock(Location location) {
        new BukkitRunnable() {
            public void run() {
                location.getBlock().setType(Material.AIR);
                blockLocations.remove(location);
            }
        }.runTaskLater(plugin, 20 * 60);
    }
}
