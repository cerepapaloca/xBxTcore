package Plugin.Commands.User;

import Plugin.Commands.BaseCommand;
import Plugin.Inventory.InventorySection;
import Plugin.Inventory.Models.InvetoryPlayer;
import Plugin.Messages.Messages.Messages;
import Plugin.Utils.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMenu extends BaseCommand {

    public CommandMenu(){
        super("menu",
                "/menu",
                "xbxtcore.command.user",
                false,
                "Puedes ver todos los inventarios del servidor relacionados a xBxTCore");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            generateCircle(player.getLocation(), Integer.parseInt(args[0]), Material.STONE);
        }
        /*if(sender instanceof Player p){
            InventorySection.getInventoryMenu().OpenMenu(new InvetoryPlayer(p));
        }else{
            Utils.sendMessage(sender, Messages.Generic_OnlyPlayers);
        }*/
    }

    public void generateCircle(Location center, int radius, Material material) {
        int cx = center.getBlockX();
        int cy = center.getBlockY();
        int cz = center.getBlockZ();

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if (x * x + z * z <= radius * radius) { // Fórmula del círculo
                    Location loc = new Location(center.getWorld(), cx + x, cy, cz + z);
                    loc.getBlock().setType(material);
                }
            }
        }
    }
}
