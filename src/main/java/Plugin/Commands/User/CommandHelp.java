package Plugin.Commands.User;

import Plugin.xBxTcore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static Plugin.Managers.MessageManager.prefix;

public class CommandHelp implements CommandExecutor {

    private final xBxTcore plugin;

    public CommandHelp(xBxTcore plugin){
        this.plugin = plugin;
    }
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command cmd,@NotNull String label, String[] args) {
        if(sender instanceof Player p){
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',prefix + "&b<--&c&lCommands&b--> &cEN"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c/kit&e Open List Kits"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c/kitfavorite&e Load your kit favorite when respawn or save your kit"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c/savekit&e save all your inventory in a kit"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c/delkit&e Delete kit selected"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c/lobby &e Teleport to the lobby"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c/duel &e You can create a duel with a player and select world type"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',prefix + "&b<--&c&lComandos&b--> &cES"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c/kit&e Abre la lista de Kits"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c/kitfavorite&e Carga el kit favorito o guarda tu kit favorito"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c/savekit&e Guarda todo tu invetario en un kit"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c/delkit&e Elimina el kit selecionado"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c/lobby&e Te teletrasporta al lobby"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c/duel&e Creas un duelo con otro jugador y seleciona el tipo de mundo"));
        }else{
            plugin.messageOnlyPlayer();
        }

        return false;
    }
}
