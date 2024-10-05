package xyz.xbcore.Commands.User;

import xyz.xbcommun.Command.BaseTabCommand;
import xyz.xbcore.File.FileManagerSection;
import xyz.xbcore.Messages.Messages.Messages;
import xyz.xbcore.Messages.MessageManager;
import xyz.xbcore.Utils.Utils;
import xyz.xbcore.xBxTcore;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static xyz.xbcore.Utils.Utils.AntiSpam;

public class CommandKitFavorite extends BaseTabCommand {

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

    @Override
    public List<String> onTab(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            FileManagerSection.getPlayerFileManager().loadNameKit(player.getUniqueId());
            List<String> namekitsall = new ArrayList<>(FileManagerSection.getPlayerFileManager().nameskitsboth);
            if (args.length == 1) {
                String currentArg = args[0].toLowerCase();
                return namekitsall.stream()
                        .filter(name -> name.toLowerCase().contains(currentArg))
                        .collect(Collectors.toList());
            }
        }
        return null;
    }
}
