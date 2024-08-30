package Plugin.Messages.Listener;

import Plugin.CombatLog.CombatSection;
import Plugin.Duel.DuelSection;
import Plugin.Duel.Enum.EndCombatCauses;
import Plugin.Environments.EnvironmentsSection;
import Plugin.Messages.Enum.Messages;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static Plugin.Messages.MessageManager.BroadcastMessageDied;

public class MessageDiedListener implements Listener {

    private Player playeranchor;
    private Player playeranvil;
    private Material MaterialFalling;
    private Player playerMasterKiller;

    @EventHandler
    public void PlayerPlaceAnchor(PlayerInteractEvent event) {
        if (event.getMaterial() == Material.RESPAWN_ANCHOR) {
            playeranchor = event.getPlayer();
        }
    }

    @EventHandler
    public void PlayerPlaceAnvil(BlockPlaceEvent event) {
        if (event.getBlock().getType() == Material.ANVIL || (event.getBlock().getType() == Material.POINTED_DRIPSTONE)) {
            MaterialFalling = event.getBlock().getType();
            playeranvil = event.getPlayer();
        }
    }

    private String custonNameWeapon(@NotNull Material material, Player player){
        String weaponname;
        ItemStack weapon = player.getInventory().getItemInMainHand();
        ItemStack weaponOff = player.getInventory().getItemInOffHand();
        if(weapon.getType().equals(material)){
            if (weapon.getItemMeta() == null)return "?";
            weaponname = weapon.getItemMeta().getDisplayName();
            if(weaponname.isEmpty()){
                return Objects.requireNonNull(weapon.getType().name().replace("_", " ")).toLowerCase();
            } else {
                return ChatColor.translateAlternateColorCodes('&', weaponname);
            }

        }else if(weaponOff.getType().equals(material)){

            if (weaponOff.getItemMeta() == null)return "?";
            weaponname = weaponOff.getItemMeta().getDisplayName();
            if(weaponname.isEmpty()){
                return Objects.requireNonNull(weaponOff.getType().name().replace("_", " ")).toLowerCase();
            }else {
                return ChatColor.translateAlternateColorCodes('&', weaponname);
            }
        }else{
            return ChatColor.translateAlternateColorCodes('&', material.name().replace("_", " ").toLowerCase());
        }
    }

    public void suicide(Player player) {
        BroadcastMessageDied(Messages.Died4, player, null, "");
    }

    @EventHandler
    public void PlayerDeathMessage(PlayerDeathEvent event) {
        String weaponname;
        if (!event.getEntity().getWorld().equals(Bukkit.getWorld("boxpvp"))){
            event.getEntity().teleport(xBxTcore.getMultiverseCore().getMVWorldManager().getMVWorld("boxpvp").getSpawnLocation());
        }else{
            event.getEntity().teleport(xBxTcore.getMultiverseCore().getMVWorldManager().getMVWorld("lobby").getSpawnLocation());
        }
        if (event.getEntity().getKiller() != null) {
            playerMasterKiller = event.getEntity().getKiller();
        }

        if (event.getEntity().getLastDamageCause() != null) {
            Player p = event.getEntity();
            if(p == playerMasterKiller){
                suicide(p);
                event.setDeathMessage(null);
                return;
            }
            /*ItemStack weapon;
            if (null != event.getEntity().getKiller()){
                weapon = playerMasterKiller.getInventory().getItemInMainHand();
                if (weapon.getItemMeta() != null){
                    weaponname = Objects.requireNonNull(weapon.getItemMeta()).getDisplayName();
                    if(weaponname.isEmpty()){
                        weaponname = Objects.requireNonNull(weapon.getType().name().replace("_", " ")).toLowerCase();
                    }
                }else{
                    weaponname = "?";
                }
                weaponname = ChatColor.translateAlternateColorCodes('&', weaponname);

            }else {
                weaponname = "";
            }*/
            weaponname = custonNameWeapon(playerMasterKiller.getInventory().getItemInMainHand().getType(), playerMasterKiller);
            switch (Objects.requireNonNull(event.getEntity().getLastDamageCause()).getCause()) {
                case ENTITY_EXPLOSION -> {
                    weaponname = custonNameWeapon(Material.END_CRYSTAL, playerMasterKiller);
                    BroadcastMessageDied(Messages.Died2, p, playerMasterKiller, weaponname);
                }
                case BLOCK_EXPLOSION -> {
                    if(p == playeranchor){
                        suicide(p);
                        event.setDeathMessage(null);
                        return;
                    }
                    playerMasterKiller = playeranchor;
                    if (Objects.requireNonNull(event.getDeathMessage()).contains("[Intentional Game Design]")) {
                        weaponname = custonNameWeapon(Material.RESPAWN_ANCHOR, playerMasterKiller);
                    }else{
                        weaponname = custonNameWeapon(Material.TNT, playerMasterKiller);
                    }
                    BroadcastMessageDied(Messages.Died2, p, playerMasterKiller, weaponname);
                }
                case SUICIDE -> suicide(playerMasterKiller);

                case ENTITY_ATTACK, PROJECTILE, ENTITY_SWEEP_ATTACK -> BroadcastMessageDied(Messages.Died1, p, playerMasterKiller, weaponname);

                case FALL -> BroadcastMessageDied(Messages.Died5, p, null, "");

                case FALLING_BLOCK -> {
                    playerMasterKiller = playeranvil;
                    if(p == playeranvil){
                        suicide(p);
                        event.setDeathMessage(null);
                        return;
                    }
                    weaponname = custonNameWeapon(MaterialFalling, playerMasterKiller);
                    BroadcastMessageDied(Messages.Died1, p, playerMasterKiller, weaponname);
                }
                case SUFFOCATION -> BroadcastMessageDied(Messages.Died3, p, null,"");
            }
            if (playerMasterKiller != null) {
                int i = playerMasterKiller.getLevel();
                playerMasterKiller.setLevel(i + 1);
                CombatSection.getCombatlogManager().endCombat(event.getEntity());
                CombatSection.getCombatlogManager().endCombat(playerMasterKiller);
                EnvironmentsSection.getHologramas().PlayerKillerAdd(playerMasterKiller);
                playerMasterKiller = null;
            }
            if (!xBxTcore.getWorldProtec().contains(event.getEntity().getWorld())){
                DuelSection.getDuelManager().EndDuel(event.getEntity().getWorld(), playerMasterKiller, EndCombatCauses.DIED);
            }
            event.setDeathMessage(null);
        }
        event.setDeathMessage(null);
    }
}
