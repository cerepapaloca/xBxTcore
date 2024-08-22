package Plugin.Environments;

import Plugin.Model.MinaBoxPvp;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import java.util.Objects;

public class HologramasBoxPvp extends Hologramas {

    public HologramasBoxPvp(xBxTcore plugin) {
        super(plugin);
    }

    public void createTimesMina(MinaBoxPvp minaBoxPvp) {
        Location location = minaBoxPvp.getLocationTimis();
        ArmorStand armorStand = (ArmorStand) Objects.requireNonNull(location.getWorld()).spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&b<--&c&l" + minaBoxPvp.getName() + "&r&b-->"));
        armorStand.setCustomNameVisible(true);
        armorStand.setInvisible(true);
        armorStand.setGravity(false);
        armorStand.setMarker(true);
        minaBoxPvp.setArmorStand(createListArmorStand(textholograms, location, 1).get(0));
    }

    public void updateHologramTimesMina(){
        for (MinaBoxPvp mina : xBxTcore.getAutoFillsBox().minas){
            ArmorStand armorStand = mina.getArmorStand();
            armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&',"&e " + mina.getName() + Utils.SecondToMinutes(mina.getTimeLeft())));
        }
    }

}
