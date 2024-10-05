package xyz.xbcore.Inventory.Models;

import xyz.xbcore.Inventory.Enum.InvetorySection;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.UUID;
@Getter
@Setter
public class InvetoryPlayer {
    private InvetorySection section;
    private Player player;
    private UUID UUIDKit;
    private int page;
    private Boolean previewMode = false;
    private Boolean kitSelectMode = false;

    public InvetoryPlayer(Player player) {
        this.player = player;
    }

}
