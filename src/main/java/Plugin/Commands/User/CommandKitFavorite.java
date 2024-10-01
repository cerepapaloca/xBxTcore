package Plugin.Commands.User;

import Plugin.Commands.BaseCommand;
import Plugin.File.FileManagerSection;
import Plugin.Messages.Messages.Messages;
import Plugin.Messages.MessageManager;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static Plugin.Utils.Utils.AntiSpam;

public class CommandKitFavorite extends BaseCommand {

    private final xBxTcore plugin;

    public CommandKitFavorite(xBxTcore plugin){
        super(new String[]{"kf", "kitfavorite"},
                "/kitfavorite | /kitfavorite <Nombre Del kit>",
                "xbxtcore.command.user",
                false,
                "Este comando se usa para cargar o guardar un kit como favorito, esto hace cuando te mueras cargue tu kit favorito automáticamente. " +
                        "si ejecutas el comando sin argumentos carga tu kit favorito pero si en el argumento pones el nombre del kit lo guardara");
        this.plugin = plugin;
    }

    public void LoadKit(Player player) {
        FileManagerSection.getPlayerFileManager().loadkitfavorite(player);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof Player player){
            if (player.getWorld().getName().equals(xBxTcore.worldBoxPvp)) {
                player.sendMessage(MessageManager.MasterMessageLocated(player, Messages.Generic_InArea));
                return;
            }

            if(args.length == 1){
                FileManagerSection.getPlayerFileManager().SaveNameKitFavorite(player.getUniqueId(), args[0]);
            } else if (args.length == 0) {
                LoadKit(player);
                AntiSpam(player, Messages.Kick_SpamCommand, plugin);
            }
        }else{
            Utils.sendMessage(sender, Messages.Generic_OnlyPlayers);
        }
    }
}
