package xyz.xbcore.Environment.Hologrmas;

import xyz.xbcore.BoxPvp.AutoFillsBox;
import xyz.xbcore.BoxPvp.Model.MinaBoxPvp;
import xyz.xbcommun.Utils.ColorUtils;
import xyz.xbcommun.Utils.UtilsGlobal;
import xyz.xbcore.xBxTcore;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import java.util.Objects;

import static xyz.xbcommun.Messages.MessageManager.Colorinfo;
import static xyz.xbcommun.Messages.MessageManager.Colorplayer;
import static xyz.xbcommun.Utils.ColorUtils.applyGradient;

public class HologramasBoxPvp extends Hologramas {


    public HologramasBoxPvp(xBxTcore plugin) {
        super(plugin);
    }

    public void createTimesMina(MinaBoxPvp minaBoxPvp) {
        Location location = minaBoxPvp.getLocationTimis();
        ArmorStand armorStand = (ArmorStand) Objects.requireNonNull(location.getWorld()).spawnEntity(location, EntityType.ARMOR_STAND);
        if (minaBoxPvp.getHexColor().isEmpty()){
            armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&',  Colorinfo + "<---&r" + (applyGradient("<#" +
                    ColorUtils.blockToHex(minaBoxPvp.getMaterial()) + ">" + minaBoxPvp.getName() + "<#" + ColorUtils.modifyColorHexWithHLS(ColorUtils.blockToHex(
                    minaBoxPvp.getMaterial()), 0.1f, 0.3f, -0.3f) +
                    "> ") + Colorinfo + "--->")));
        }else {
            armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&',  Colorinfo + "<---&r" + (applyGradient("<#" +
                    minaBoxPvp.getHexColor().get(0) + ">" + minaBoxPvp.getName() + "<#" + minaBoxPvp.getHexColor().get(1) +
                    "> ") + Colorinfo + "--->")));
        }
        armorStand.setCustomNameVisible(true);
        armorStand.setInvisible(true);
        armorStand.setGravity(false);
        armorStand.setMarker(true);
        minaBoxPvp.setArmorStand(createListArmorStand(textholograms, location, 1).get(0));
    }

    public void updateHologramTimesMina(){
        for (MinaBoxPvp mina : AutoFillsBox.minas){
            ArmorStand armorStand = mina.getArmorStand();
            armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&',Colorinfo + "Tiempo Restante " + Colorplayer + UtilsGlobal.TimeToString(mina.getTimeLeft(), 0)));
        }
    }
}
