package xyz.xbcore.Placeholder;

import xyz.xbcore.BoxPvp.ItemsBoxPvp.BonusUpdate;
import xyz.xbcore.xBxTcore;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Placeholder extends PlaceholderExpansion {

    private final xBxTcore plugin;

    public Placeholder(xBxTcore plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return plugin.getName();
    }

    @Override
    public @NotNull String getAuthor() {
        return plugin.getDescription().getAuthors().get(0);
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String identifier) {
        if (player == null) {
            return "";
        }

        switch (identifier) {
            case "max_tier" -> {
                String s;
                if (BonusUpdate.playerTier.getOrDefault(player.getUniqueId(), 0) == 0){
                    s = "";
                }else{
                    s = "&7 |&a Tier " + BonusUpdate.playerTier.getOrDefault(player.getUniqueId(), 0);
                }
                return s;
            }
            case "full_health" -> {
                return String.valueOf(((int) Math.round(player.getHealth() + player.getAbsorptionAmount())));
            }
        }
        return null;
    }
}