package Plugin.Environments;

import Plugin.Utils.Tools;
import Plugin.xBxTcore;
import org.bukkit.Chunk;

import java.util.ArrayList;

public class ZoneSafeBoxPvp {

    public static final ArrayList<Chunk> chunksSafe = new ArrayList<>();
    private final xBxTcore plugin;

    public ZoneSafeBoxPvp(xBxTcore plugin) {
        this.plugin = plugin;
        chunksSafe.add(Tools.getChunkByCoordinates("boxpvp", -1, -2));
    }
}
