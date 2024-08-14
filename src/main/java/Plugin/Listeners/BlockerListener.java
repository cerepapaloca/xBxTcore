package Plugin.Listeners;

import Plugin.Model.Messages;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPlaceEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
import java.util.List;

public class BlockerListener implements Listener {
    public final static ArrayList<Location> blockLocations = new ArrayList<>();
    private final int ejey = 30;
    private final List<String> restrictedCommands = new ArrayList<>();
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
            Material.CYAN_GLAZED_TERRACOTTA, Material.LIGHT_BLUE_GLAZED_TERRACOTTA, Material.GRAY_GLAZED_TERRACOTTA,
            Material.LIME_GLAZED_TERRACOTTA, Material.ORANGE_GLAZED_TERRACOTTA, Material.PINK_GLAZED_TERRACOTTA, Material.RED_GLAZED_TERRACOTTA,
            Material.MAGENTA_GLAZED_TERRACOTTA, Material.WHITE_GLAZED_TERRACOTTA, Material.LIGHT_GRAY_GLAZED_TERRACOTTA, Material.PURPLE_GLAZED_TERRACOTTA
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
        //////////////////////////////////////
    }

    @EventHandler
    public void BlockBreak(BlockBreakEvent event) {
        if (!event.getPlayer().isOp() && ejey <= event.getBlock().getLocation().getBlockY() && xBxTcore.getWorldProtec().contains(event.getPlayer().getWorld())) {
            if (materialsBoxPvp.contains(event.getBlock().getType())) {
                int i = 0;
                for (ItemStack stack : event.getPlayer().getInventory().getContents()) {
                    if (stack == null || stack.getType() == Material.AIR) {
                        i++;
                    }
                }
                if(i >= 1){
                    event.setDropItems(false);
                    additem(event.getPlayer(), event.getBlock());
                }
                return;
            }
            event.setCancelled(true);
            event.getPlayer().sendMessage(xBxTcore.getMessageManager().MasterMessage(event.getPlayer(), Messages.NotAllowed));
        }
    }

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent event) {
        if (event.getPlayer().getWorld().equals(Bukkit.getWorld("boxpvp"))) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void BlockPlace(BlockPlaceEvent event) {
        if (!event.getPlayer().isOp() && ejey <= event.getBlock().getLocation().getBlockY() && xBxTcore.getWorldProtec().contains(event.getPlayer().getWorld())) {
            if (event.getPlayer().getWorld().equals(Bukkit.getWorld("boxpvp")) && (event.getBlock().getType().equals(Material.OBSIDIAN)) || event.getBlock().getType().equals(Material.COBWEB)){
                temporalBlock(event.getBlock().getLocation());
                blockLocations.add(event.getBlock().getLocation());
                return;
            }
            event.getPlayer().sendMessage(xBxTcore.getMessageManager().MasterMessage(event.getPlayer(), Messages.NotAllowed));
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void PlaceBlockfluit(PlayerBucketEmptyEvent event) {
        if (!event.getPlayer().isOp() && ejey <= event.getBlock().getLocation().getBlockY() && xBxTcore.getWorldProtec().contains(event.getPlayer().getWorld())) {
            event.getPlayer().sendMessage(xBxTcore.getMessageManager().MasterMessage(event.getPlayer(), Messages.NotAllowed));
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void PlayerInteract(PlayerInteractEvent event) {
        if (!event.getPlayer().isOp() && ejey <= event.getPlayer().getLocation().getBlockY()) {
            Block block = event.getClickedBlock();
            if (null != event.getClickedBlock()){
                assert block != null;
                if (materials.contains(block.getType()) && xBxTcore.getWorldProtec().contains(event.getPlayer().getWorld())) {
                    event.getPlayer().sendMessage(xBxTcore.getMessageManager().MasterMessage(event.getPlayer(), Messages.NotAllowed));
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void PlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        if(!event.getPlayer().isOp()){
            String command = event.getMessage().split(" ")[0].substring(1).toLowerCase();
            if(!restrictedCommands.contains(command)){
                event.setCancelled(true);
                event.getPlayer().sendMessage(xBxTcore.getMessageManager().MasterMessage(event.getPlayer(), Messages.NotAllowed));
            }
        }
    }

    @EventHandler
    public void EntityPlace(EntityPlaceEvent event) {
        if (!Objects.requireNonNull(event.getPlayer()).isOp() && ejey + 1 <= event.getEntity().getLocation().getBlockY())  {
            if (xBxTcore.getWorldProtec().contains(event.getEntity().getWorld())){
                event.getPlayer().sendMessage(xBxTcore.getMessageManager().MasterMessage(event.getPlayer(), Messages.NotAllowed));
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void ExplosionPrime(ExplosionPrimeEvent event) {
        if(ejey <= event.getEntity().getLocation().getBlockY()){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void EntityDamage(EntityDamageEvent event) {
        if (event.getCause() != EntityDamageEvent.DamageCause.KILL){
            if (ejey + 4 <= event.getEntity().getLocation().getBlockY() && xBxTcore.getWorldProtec().contains(event.getEntity().getWorld())) {
                event.setCancelled(true);
            }
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL && event.getDamage() > 30)  {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void Exp(PlayerExpChangeEvent event) {
        event.setAmount(0);
    }

    public void temporalBlock(Location location) {
        new BukkitRunnable() {
            public void run() {
                location.getBlock().setType(Material.AIR);
                blockLocations.remove(location);
            }
        }.runTaskLater(plugin, 20 * 3);
    }

    public void additem(Player player, Block block){
        ItemStack item = new ItemStack(block.getType());
        player.playSound(player,Sound.ENTITY_ITEM_PICKUP, 1,1);
        player.getInventory().addItem(item);
    }

}
