package Plugin.Model.Player;

import Plugin.Enum.InvetorySection;
import org.bukkit.entity.Player;

import java.util.UUID;

public class InvetoryPlayer {
    private InvetorySection section;
    private Player player;
    private UUID uuidkit;
    private int page;
    private Boolean previewMode = false;
    private Boolean kitSelectMode = false;

    public InvetoryPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public InvetorySection getSection() {
        return section;
    }

    public void setSection(InvetorySection section) {
        this.section = section;
    }

    public UUID getuuidkit() {
        return uuidkit;
    }

    public void setuuidkit(UUID uuidkit) {
        this.uuidkit = uuidkit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Boolean getPreviewMode() {
        return previewMode;
    }

    public void setPreviewMode(Boolean previewMode) {
        this.previewMode = previewMode;
    }

    public Boolean getKitSelectMode() {
        return kitSelectMode;
    }

    public void setKitSelectMode(Boolean kitSelectMode) {
        this.kitSelectMode = kitSelectMode;
    }

}
