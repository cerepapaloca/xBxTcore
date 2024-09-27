package Plugin.Placeholder;

import Plugin.BoxPvp.ItemsBoxPvp.BonusUpdate;
import Plugin.xBxTcore;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
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
                int maxTier = 0;
                for (ItemStack item : player.getInventory().getContents()) {
                    if (item.getType().equals(Material.NETHERITE_SWORD)) {
                        if (item.getItemMeta() != null) {
                            int tier = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "tier"), PersistentDataType.INTEGER);
                            maxTier = Math.max(tier, BonusUpdate.BoxPvpSection);
                            Bukkit.getConsoleSender().sendMessage("Tier: " + maxTier);
                            break;
                        }
                    }
                }
                return String.valueOf(maxTier);
            }
            case "full_health" -> {
                return String.valueOf(((int) Math.round(player.getHealth() + player.getAbsorptionAmount())));
            }
        }
        return null;
    }
}