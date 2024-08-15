package Plugin.Environments;

import Plugin.Model.MinaBoxPvp;
import Plugin.Utils.Tools;
import Plugin.xBxTcore;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.Objects;

public class HologramasBoxPvp extends Hologramas {

    private ArrayList<ArmorStand> armorStandsTimesMina = new ArrayList<>();

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
        armorStandsTimesMina = createListArmorStand(textholograms, location, 1);
    }

    public void updateHologramTimesMina(){
        int i = 0;
        for (MinaBoxPvp mina : xBxTcore.getAutoFillsBox().minas){
            ArmorStand armorStand = armorStandsTimesMina.get(i);
            armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&',"&e " + mina.getName() + Tools.SecondToMinutes(mina.getTimeLeft())));
            i++;
        }
    }


}
