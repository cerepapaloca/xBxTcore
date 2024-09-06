package Plugin.PlayerManager.Listener;

import Plugin.BoxPvp.BoxPvpSection;
import Plugin.CombatLog.CombatSection;
import Plugin.Messages.Enum.Messages;
import Plugin.xBxTcore;
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
import java.util.List;

import static Plugin.Messages.MessageManager.MasterMessageLocated;
import static Plugin.Security.BlockByPass.checkOpCreative;
import static Plugin.xBxTcore.worldBoxPvp;

public class BlockerListener implements Listener {
    public final static ArrayList<Location> blockLocations = new ArrayList<>();
    public static final int ejey = 30;
    private final List<String> restrictedCommands = new ArrayList<>();
    private final HashMap<String, String> restrictedCommandsWithPermissions = new HashMap<>();
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
        restrictedCommands.add("kill");
        restrictedCommands.add("sk");
        restrictedCommands.add("kf");
        restrictedCommands.add("dk");
        restrictedCommands.add("kit");
        restrictedCommands.add("lobby");
        restrictedCommands.add("delkit");
        restrictedCommands.add("tell");
        restrictedCommands.add("savekit");
        restrictedCommands.add("kitfavorite");
        restrictedCommands.add("w");
        restrictedCommands.add("r");
        restrictedCommands.add("msg");
        restrictedCommands.add("help");
        restrictedCommands.add("duel");
        restrictedCommands.add("discord");
        restrictedCommands.add("spectator");
        restrictedCommands.add("spawn");
        restrictedCommands.add("rank");
        restrictedCommands.add("vote");
        restrictedCommands.add("donate");
        restrictedCommands.add("prefix");
        restrictedCommands.add("plugins");
        restrictedCommands.add("inv");
        restrictedCommands.add("boxpvp");
        restrictedCommands.add("shop");
        restrictedCommands.add("login");
        restrictedCommands.add("log");
        restrictedCommands.add("register");
        restrictedCommandsWithPermissions.put("skin", "xbxtcore.vip");
        //////////////////////////////////////
    }

    @EventHandler
    public void BlockBreak(@NotNull BlockBreakEvent event) {
        checkOpCreative(event.getPlayer());
        if (!event.getPlayer().isOp() && ejey <= event.getBlock().getLocation().getBlockY() && xBxTcore.getWorldProtec().contains(event.getPlayer().getWorld())) {
            if (materialsBoxPvp.contains(event.getBlock().getType())) {
                event.setDropItems(false);
                double i = Math.random();
                double tier = 0;
                ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
                if (item.getItemMeta() != null) {
                    tier = (double) item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER);
                    tier = (tier*3)/100;
                }
                BoxPvpSection.getItemManage().AddItemMine(event.getPlayer(), event.getBlock().getType());
                if (i < tier) {
                    BoxPvpSection.getItemManage().AddItemMine(event.getPlayer(), event.getBlock().getType());
                }

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
    public void PlayerCommandPreprocess(@NotNull PlayerCommandPreprocessEvent event) {
        checkOpCreative(event.getPlayer());
        if(!event.getPlayer().isOp()){
            String command = event.getMessage().split(" ")[0].substring(1).toLowerCase();

            if(restrictedCommandsWithPermissions.containsKey(command)){
                if (event.getPlayer().hasPermission(restrictedCommandsWithPermissions.get(command)))return;
            }

            if(!restrictedCommands.contains(command)){
                event.setCancelled(true);
                event.getPlayer().sendMessage(MasterMessageLocated(event.getPlayer(), Messages.Generic_NotAllowed));
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

    public void temporalBlock(Location location) {
        new BukkitRunnable() {
            public void run() {
                location.getBlock().setType(Material.AIR);
                blockLocations.remove(location);
            }
        }.runTaskLater(plugin, 20 * 60);
    }
}
