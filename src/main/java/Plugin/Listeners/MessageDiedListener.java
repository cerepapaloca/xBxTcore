package Plugin.Listeners;

import Plugin.Enum.EndCombatCauses;
import Plugin.Enum.Messages;
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

import java.util.Objects;

public class MessageDiedListener implements Listener {

    public String weaponname;
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

    private String custonNameWeapon(Material material, Player player){
        ItemStack weapon = player.getInventory().getItemInMainHand();
        ItemStack weaponOff = player.getInventory().getItemInOffHand();
        if(weapon.getType().equals(material)){
            weaponname = Objects.requireNonNull(weapon.getItemMeta()).getDisplayName();
            if(weaponname.isEmpty()){
                weaponname = Objects.requireNonNull(weapon.getType().name().replace("_", " ")).toLowerCase();
            }
        }else if(weaponOff.getType().equals(material)){
            weaponname = Objects.requireNonNull(weaponOff.getItemMeta()).getDisplayName();
            if(weaponname.isEmpty()){
                weaponname = Objects.requireNonNull(weaponOff.getType().name().replace("_", " ")).toLowerCase();
            }
        }else{
            weaponname = material.name().replace("_", " ").toLowerCase();
        }
        /*if(weaponname.contains("")){
            weaponname = "&4&l&oNo SPAM!!!!";
        }*/
        return ChatColor.translateAlternateColorCodes('&', weaponname);
    }

    public void suicide(Player player) {
        xBxTcore.getMessageManager().BroadcastMessageDied(Messages.Died4, player, null, "");
    }

    @EventHandler
    public void PlayerDeathMessage(PlayerDeathEvent event) {
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
            ItemStack weapon;
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
                if(weaponname.equalsIgnoreCase("http")){
                    weaponname = "&4&l&oNo SPAM!!!!";
                }
                weaponname = ChatColor.translateAlternateColorCodes('&', weaponname);

            }else {
                weaponname = "";
            }
            switch (Objects.requireNonNull(event.getEntity().getLastDamageCause()).getCause()) {
                case ENTITY_EXPLOSION -> {
                    weaponname = custonNameWeapon(Material.END_CRYSTAL, playerMasterKiller);
                    xBxTcore.getMessageManager().BroadcastMessageDied(Messages.Died2, p, playerMasterKiller, weaponname);
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
                    xBxTcore.getMessageManager().BroadcastMessageDied(Messages.Died2, p, playerMasterKiller, weaponname);
                }
                case SUICIDE -> suicide(playerMasterKiller);

                case ENTITY_ATTACK, PROJECTILE, ENTITY_SWEEP_ATTACK -> xBxTcore.getMessageManager().BroadcastMessageDied(Messages.Died1, p, playerMasterKiller, weaponname);

                case FALL -> xBxTcore.getMessageManager().BroadcastMessageDied(Messages.Died5, p, null, "");

                case FALLING_BLOCK -> {
                    playerMasterKiller = playeranvil;
                    if(p == playeranvil){
                        suicide(p);
                        event.setDeathMessage(null);
                        return;
                    }
                    weaponname = custonNameWeapon(MaterialFalling, playerMasterKiller);
                    xBxTcore.getMessageManager().BroadcastMessageDied(Messages.Died1, p, playerMasterKiller, weaponname);
                }
                case SUFFOCATION -> xBxTcore.getMessageManager().BroadcastMessageDied(Messages.Died3, p, null,"");
            }
            if (playerMasterKiller != null) {
                int i = playerMasterKiller.getLevel();
                playerMasterKiller.setLevel(i + 1);
                xBxTcore.getcombatlogListener().endCombat(event.getEntity());
                xBxTcore.getcombatlogListener().endCombat(playerMasterKiller);
                xBxTcore.getHologramas().PlayerKillerAdd(playerMasterKiller);
                playerMasterKiller = null;
            }
            if (!xBxTcore.getWorldProtec().contains(event.getEntity().getWorld())){
                xBxTcore.getDuelManager().EndDuel(event.getEntity().getWorld(), playerMasterKiller, EndCombatCauses.DIED);
            }
            event.setDeathMessage(null);
        }
        event.setDeathMessage(null);
    }
}
