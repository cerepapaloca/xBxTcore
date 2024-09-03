package Plugin.Placeholder;

import Plugin.File.FileManagerSection;
import Plugin.xBxTcore;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class prefixClanPlaceholder extends PlaceholderExpansion {

    private final xBxTcore plugin;

    public prefixClanPlaceholder(xBxTcore plugin) {
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

        if (identifier.equals("prefix_clan")) {
            return FileManagerSection.getClanFileManager().loadPrefixClan(FileManagerSection.getPlayerFileManager().loadClanId(player.getUniqueId()));
        }

        return null;
    }
}