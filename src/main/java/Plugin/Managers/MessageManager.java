package Plugin.Managers;

import Plugin.Enum.Messages;
import Plugin.xBxTcore;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MessageManager {

    private final Map<String, String> esMessages = new HashMap<>();
    private final Map<String, String> enMessages = new HashMap<>();

    public static String prefixKick = "&6[&8&lxB&f&lxT &e&lPvP&6]&r \n";
    public static String prefixConsole = "&6[&8&lxB&f&lxT &eConsoler&6]&r ";
    public static String prefix = "&6[" + ChatColor.of("#61CAFD") + "&lx" + ChatColor.of("#7CAFEC") + "&lB" + ChatColor.of("#9893DC")
            + "&lx" + ChatColor.of("#B378CB") + "&lT" + ChatColor.of("#FDC661") + " &lC" + ChatColor.of("#FEAA41") + "&lo" +
            ChatColor.of("#FE8F22") + "&lr" + ChatColor.of("#FF7302") + "&le" + "&6]&r ";
    public static String prefixDuel = "&8[&4⚔&8] ";
    public static String prefixDied = "&8[&4☠&8] ";

    public static String ColorError = "&c";
    public static String ColorSuccess = "&a";
    public static String ColorWarning = "&e";
    public static String Colorinfo = "&3";
    public static String Colorplayer = "&6";
    public static String ColorLink = "&a&n";
    public static String Coloritem = "&b";

    private static final String esErrorMensaje = prefix + ColorError + "&LHubo un error con el mensaje: '%message%' Contacta rapidamente con el dueño del servidor";
    private static final String enErrorMensaje = prefix + ColorError + "&LThere was an error with the message: '%message%' Please contact the server owner quickly";

    public MessageManager() {

        //EN//

        //Generico
       enMessages.put("NotOp",prefix + ColorError + "You don't have permission to perform this command!");
       enMessages.put("NotAllowed",prefix + ColorError + "it's not allowed");
       enMessages.put("InArea",prefix + ColorError + "It is not allowed to use here");
       enMessages.put("HoverExecute","Execute this command");
       //Vote y prefix
       enMessages.put("NotVote",ColorError + "To have access to this command you have to vote for this server with the command: ");
       enMessages.put("TooLengthName",prefix + ColorError + "Very long name. Maximum characters 10");
       enMessages.put("WordNotAllowed",prefix + ColorError + "That word is not allowed");
       enMessages.put("PrefixSuccess",prefix + ColorSuccess + "The prefix has been put in your name");
       enMessages.put("Voted",Colorinfo + "Thank you for voting, you can now use the command: ");
       enMessages.put("PrefixClear",prefix + Colorinfo + "The prefix has been cleared");
       //Eventos
       enMessages.put("leave",Colorinfo + " left the game!");
       enMessages.put("join",Colorinfo + " joined the game!");
       //Muertes
       enMessages.put("Died1",prefixDied + Colorplayer + "%player% " + Colorinfo + "was kill by " + Colorplayer + "%killer% " + Colorinfo + "with: %item%");
       enMessages.put("Died2",prefixDied + Colorplayer + "%player% " + Colorinfo + "was explode by " + Colorplayer + "%killer% " + Colorinfo + "with: %item%");
       enMessages.put("Died3",prefixDied + Colorplayer + "%player% " + Colorinfo + "was suffocation by " + Colorplayer + "BLock ");
       enMessages.put("Died4",prefixDied + Colorplayer + "%player% " + Colorinfo + "committed suicide");
       enMessages.put("Died5",prefixDied + Colorplayer + "%player% " + Colorinfo + "died from the fall");
       enMessages.put("DiedDuel",prefixDied + Colorinfo + "Winner Is " + Colorplayer + "%killer%" + Colorinfo + " And Looser Is " + Colorplayer + "%player% ");
        //kit
       enMessages.put("KitNotExist",prefix + ColorError + "The Kit Does Not Exist");
       enMessages.put("SaveError",prefix + ColorError + "You must specify the name of the kits and/or item");
       enMessages.put("SaveErrorPunto",prefix + ColorError + "It can't have dot");
       enMessages.put("DelError",prefix + ColorError + "You must specify the name of the kit");
       enMessages.put("Removed",prefix + ColorSuccess + "the kit &r%namekit%"+ ColorSuccess +" was removed successfully");
       enMessages.put("RemovedWaring",prefix + ColorWarning + "An attempt was made to delete a kit that does not exist");
       enMessages.put("Load",prefix + ColorSuccess + "the kit &r%namekit%"+ ColorSuccess +" was loaded successfully");
       enMessages.put("Save",prefix + ColorSuccess + "the kit &r%namekit%"+ ColorSuccess +" was saved successfully");
       enMessages.put("Favorite",prefix + ColorSuccess + "favorite kit was saved successfully");
       enMessages.put("LoadError",prefix + ColorError + "There was a problem loading the kit &r%nameKit%" + ColorError + " try to log out and back in if the situation repeats contact the owner");
       enMessages.put("LoadWaring",prefix + ColorWarning + "Item '%item%' could not be loaded and will be skipped when loading");
       enMessages.put("FavoriteWaring",prefix + ColorWarning + "The kit being saved apparently does not exist but is saved successfully. Please note that it is case sensitive as well as minecraft color codes.");
       //Combate Log
       enMessages.put("OnCombat",prefix + ColorError + "It is not allowed to use in combat wait "+ "&b" + "%time%" + ColorError + " seconds to use it");
       enMessages.put("InSafeZone","&4Leave Safe Zone In %time% Seconds");
       //Las Solisitudes del Duel
       enMessages.put("Inv",prefix + ColorSuccess + "Guest list saved successfully");
       enMessages.put("MissingPlayers",prefix + Colorinfo + "Missing players ");
       enMessages.put("SelfAccepted",prefix + ColorError + "Cannot accept or deny your own request");
       enMessages.put("OnDuel",prefix + ColorError + "You are already in a duel");
       enMessages.put("SendSelf",prefix + ColorError + "Can't send yourself");
       enMessages.put("RequestExpired",prefix + ColorError + "The request has expired");
       enMessages.put("WorldType",prefix + ColorError + "Select world type 'bedrock','flat_Bedrcok' o 'flat_world'");
       enMessages.put("DuelError",prefix + ColorError + "You have to specify the username and specify the type world (optional)");
       enMessages.put("PlayerOffTarget",prefix + ColorError + "The player is not online");
       enMessages.put("PlayerOffSender",prefix + ColorError + "The player who sent you the request is no longer online");
       enMessages.put("NotRequests",prefix + ColorError + "You have no pending requests");
       enMessages.put("DenyYour",prefix + Colorinfo + "Has denied your request");
       enMessages.put("AcceptedYourRequest",prefix + ColorSuccess + "Your request has been accepted");
       enMessages.put("AcceptedRequest",prefix +  ColorSuccess + "You have accepted a request");
       enMessages.put("FullSites",prefix + ColorWarning + "The duel worlds are full, wait for it to end.");
       enMessages.put("SendRequest1",Colorinfo + "A request has been sent to you. Use ");
       enMessages.put("SendRequest2",Colorinfo + " To accept it or deny it with ");
       enMessages.put("SendRequest",prefix + ColorSuccess + "You have sent a request to" + Colorplayer + " %player%");
       enMessages.put("DenyYou" ,prefix + ColorSuccess + "You have denied the request");
       enMessages.put("HoverYes","Yes");
       enMessages.put("HoverDeny","Deny");
       enMessages.put("ArenaDuel",Colorinfo + "World: " + Colorplayer);
       enMessages.put("SenderPlayer",Colorinfo + "Send by: " + Colorplayer);
       enMessages.put("KitSelect", Colorinfo + "Kit:&r ");
       enMessages.put("TimeLimit",Colorinfo + "Time limit: " + Colorplayer);
       enMessages.put("KitFavorite", Colorplayer + "Your favorite kit");
       enMessages.put("EndTimeDuel",prefix + Colorinfo + "Time is up");
       enMessages.put("ListPlayersEmpty",prefix + ColorError + "You haven't invited anyone");
        //En el mundo del duelo
       enMessages.put("EndCombat",prefix + ColorWarning + "End Combat, player disconnected");
       enMessages.put("IncorrectLoc",prefix + ColorError + "You are not allowed to be on the roof");
       enMessages.put("DuelStarted1",prefixDuel + Colorinfo + "Duel started: " + Colorplayer + "%player1% " + Colorinfo + "VS " + Colorplayer + "%player2% " + Colorinfo + "In: " + Colorplayer + "%world%");
       enMessages.put("DuelStarted2",prefixDuel + Colorinfo + "Duel started: " + Colorplayer + "%player% " + Colorinfo + "In: " + Colorplayer + "%world% " + Colorinfo + "With these players: ");
       enMessages.put("Go","&2&lGO!!");
       enMessages.put("HoverDuel", "See the last duel");
       //Para modo espetador
       enMessages.put("SpectatorError",prefix + ColorError + "You have not specified the name of the world.");
       enMessages.put("NotCombatWorld",prefix + ColorError + "There is no combat in that world");
       enMessages.put("NotFoundWorld",prefix + ColorError + "The world does not exist");
       enMessages.put("SpectatorSuccess",Colorinfo + "To be in survival game mode use: ");
       //Invetarios
       enMessages.put("InvGlobal","&a&lGlobal Kits");
       enMessages.put("InvCustom","&e&lCustom Kits");
       enMessages.put("TpLobby","&b&lReturn to the lobby");
       enMessages.put("TpCreatorKits","&b&lCreate your kit");
       enMessages.put("InvPreview","&bPreview");
       enMessages.put("InvItemFrame","&b&ltake what you want");
       enMessages.put("InvExit","&c&lExit");
       enMessages.put("InvLoad","&a&lLoad Kit");
       enMessages.put("lore","Name: ");
       enMessages.put("Previous","Previous page");
       enMessages.put("Next","Next page");
       enMessages.put("InvClear","&b&l Clear Inventory");
       enMessages.put("CreteKit","&b&lCreate your kit");
       enMessages.put("KitList","&bKits List&r - &6&lClick Right&r&6 To see the preview");
       enMessages.put("KitMenu","&bMenu");
       enMessages.put("KitListBedrock", "&bKits List");
       enMessages.put("PreviewOn", "&bPreview:&2&l Enabled");
       enMessages.put("PreviewOff", "&bPreview:&c&l Disabled");
       enMessages.put("DuelSendQuest", "&2&lEnviar Petición");
       enMessages.put("DuelInvPlayers", Coloritem + "&lList of invited players");
       enMessages.put("DuelSelectKit", Coloritem + "&lThe selected kit");
       enMessages.put("DuelTimeLimit", Coloritem + "&lTime limit");
       enMessages.put("DuelSelectWorld", Coloritem + "&lSelected world");
       enMessages.put("DuelLoreInvPlayers", Colorinfo + "Selected players");
       enMessages.put("DuelLoreInvPlayersEmpty", Colorinfo + "Invite your friends!");
       enMessages.put("DuelLoreSelectKit", Colorinfo + "The selected kit is: &e");
       enMessages.put("DuelLoreSelectKitEmpty", Colorinfo + "The selected kit is the favorite");
       enMessages.put("DuelLoreTimeLimit", Colorinfo + "Active time limit");
       enMessages.put("DuelLoreTimeLimitDisabled", Colorinfo + "No time limit");
       enMessages.put("DuelLoreSelectWorld", Colorplayer + "Selected: ");
       enMessages.put("DuelTimeLimitOn",Coloritem + "Time limit activated");
       enMessages.put("DuelTimeLimitOff",Coloritem + "Time limit disabled");
       enMessages.put("DuelTimeLore", Colorinfo + "Maximum time: " + Colorplayer);
       enMessages.put("SelectKitFavorite",Coloritem  + "&lSelect favorite kit");
       enMessages.put("H1mas","&21M+");
       enMessages.put("S1mas","&21S+");
       enMessages.put("S1menos","&c1S-");
       enMessages.put("H1menos","&c1M-");
       //otros
       enMessages.put("TimeBossBar", Colorinfo + "Time remaining: " + Colorplayer);
       enMessages.put("WarningGetGuestPlayers", ColorWarning + "One of the players you invited is not connected or does not exist");
       enMessages.put("IvnPlayers1", Colorinfo + "Use /inv");
       enMessages.put("IvnPlayers2", Colorinfo + "Write the names of the players.");
       enMessages.put("Alone",prefix + Colorinfo + "If you are alone on the server, join our discord to find someone to play with.\nDiscord:" + ColorLink + "https://discord.gg/QYBwEFvnsG");
       enMessages.put("message1Minute",prefix + Colorinfo + "1 minute Cleaner");
       enMessages.put("message5Seconds",prefix + Colorinfo + "5 seconds Cleaner");
       enMessages.put("messageStarCleaner",prefix + Colorinfo + "Star Cleaner");
       enMessages.put("CleanerExecuted", prefix + Colorinfo + "Cleaner executed by owner");
       enMessages.put("Donate",prefix + Colorinfo + "I would appreciate your contribution to this project" + ColorLink + "https://www.paypal.com/paypalme/xBxTpvp\n&4&l!!&r&4 All donations are &l'DONATIONS'&r&4 nothing is expected in return and it is of one's own free will");
       enMessages.put("Vote",prefix + Colorinfo + "I would appreciate your vote on some page:");
       enMessages.put("NewPlayer","&e Welcome To " + ChatColor.of("#61CAFD") + "&lx" + ChatColor.of("#7CAFEC") + "&lB" + ChatColor.of("#9893DC")
                + "&lx" + ChatColor.of("#B378CB") + "&lT" + ChatColor.of("#FDC661") + "&l." + ChatColor.of("#FEAA41") + "&lx" +
                ChatColor.of("#FE8F22") + "&ly" + ChatColor.of("#FF7302") + "&lz" + Colorinfo + "\nJoin our discord:" + ColorLink + "https://discord.gg/QYBwEFvnsG");
       //Kick
       enMessages.put("SpamCommand",prefixKick + Colorinfo + "Kicked out for command spam");


        //ES//

        //Generico
        esMessages.put("NotOp",prefix + ColorError + "No tienes permisos para usar este comando!");
        esMessages.put("NotAllowed",prefix + ColorError + "No esta permitido");
        esMessages.put("InArea",prefix + ColorError + "No se puede usar aquí");
        esMessages.put("HoverExecute","Ejecutar este comando");
        //Vote
        esMessages.put("NotVote",ColorError + "Para tener acceso a este comando tienes que votar a este servidor con el comando: ");
        esMessages.put("TooLengthName",prefix + ColorError + "Nombre muy largo. Maximo de caracteres 10");
        esMessages.put("WordNotAllowed",prefix + ColorError + "Esa palabra no está permitida");
        esMessages.put("PrefixSuccess",prefix + ColorSuccess + "Se a puesto el prefijo en tu nombre");
        esMessages.put("Voted",Colorinfo + "Gracias Por votar, ya puedes usar el comando: ");
        esMessages.put("PrefixClear",prefix + Colorinfo + "El prefijo fue limpiado");
        //Eventos
        esMessages.put("leave",Colorinfo + "Se a ido!");
        esMessages.put("join",Colorinfo + "Se a unido!");
        //Muertes
        esMessages.put("Died1",prefixDied + Colorplayer + "%player% " + Colorinfo + "fue asesinado por " + Colorplayer + "%killer% " + Colorinfo + "con: %item%");
        esMessages.put("Died2",prefixDied + Colorplayer + "%player% " + Colorinfo + "fue explotado por " + Colorplayer + "%killer% " + Colorinfo + "con: %item%");
        esMessages.put("Died3",prefixDied + Colorplayer + "%player% " + Colorinfo + "fue sofocado por " + Colorplayer + "Bloque ");
        esMessages.put("Died4",prefixDied + Colorplayer + "%player% " + Colorinfo + "se a suicidado");
        esMessages.put("Died5",prefixDied + Colorplayer + "%player% " + Colorinfo + "ha muerto de la caída ");
        esMessages.put("DiedDuel",prefixDied + Colorinfo + "El Ganador Es: " + Colorplayer + "%killer%" + Colorinfo + " Y El Perdedor Es " + Colorplayer + "%player% ");
        //kit
        esMessages.put("KitNotExist",prefix + ColorError + "El kit no existe");
        esMessages.put("SaveError",prefix + ColorError + "Debes especificar el nombre del kit y/o el objeto");
        esMessages.put("SaveErrorPunto",prefix + ColorError + "No puede tener puntos");
        esMessages.put("DelError",prefix + ColorError + "Debes especificar el nombre del kit");
        esMessages.put("Removed",prefix + ColorSuccess + "El kit &r%namekit%"+ ColorSuccess +" fue eliminado exitosamente");
        esMessages.put("RemovedWaring",prefix + ColorWarning + "Se intento eliminar un kit que no existe");
        esMessages.put("Load",prefix + ColorSuccess + "El kit &r%namekit%"+ ColorSuccess +" fue cargado exitosamente");
        esMessages.put("Save",prefix + ColorSuccess + "El kit &r%namekit%"+ ColorSuccess +" fue salvado exitosamente");
        esMessages.put("Favorite",prefix + ColorSuccess + "El kit favorito fue guardado exitosamente");
        esMessages.put("LoadError",prefix + ColorError + "hubo un problema al cargar el kit &r%nameKit%" + ColorError + " intenta salir y entrar si se repite la situación contacta con el dueño");
        esMessages.put("LoadWaring",prefix + ColorWarning + "El Item '%item%' no se pudo cargar y sera omitido en la carga");
        esMessages.put("FavoriteWaring",prefix + ColorWarning + "El kit que se guardo aparentemente no existe pero se guardo exitosamente, Ten en cuenta que es sensible a mayúsculas y a los códigos de color de minecraft");
        //Combate Log
        esMessages.put("OnCombat",prefix + ColorError + "No esta permitido usar eso en combate, espera "+ "&b" + "%time%" + ColorError + " segundos para usarlo");
        esMessages.put("InSafeZone","&4Sal De Zona Segura En %time% Segundos");
        //Las Solisitudes del Duel
        esMessages.put("Inv",prefix + ColorSuccess + "Se guardo exitosamente la lista de invitados");
        esMessages.put("MissingPlayers",prefix + Colorinfo + "Jugadores faltantes ");
        esMessages.put("SelfAccepted",prefix + ColorError + "No puede aceptar o denegar tu propia solicitud");
        esMessages.put("OnDuel",prefix + ColorError + "Ya estas en un duelo");
        esMessages.put("SendSelf",prefix + ColorError + "No puede enviarte a tí mismo");
        esMessages.put("RequestExpired",prefix + ColorError + "La solicitud ya expiro");
        esMessages.put("WorldType",prefix + ColorError + "Seleccioná un mundo 'bedrock','flat_Bedrcok' o 'flat_world'");
        esMessages.put("DuelError",prefix + ColorError + "Tienes que especificar el nombre del usuario y especificar el tipo de mundo (opcional)");
        esMessages.put("PlayerOffTarget",prefix + ColorError + "El jugador no esta en línea");
        esMessages.put("PlayerOffSender",prefix + ColorError + "El jugador que te envió la solicitud no esta en línea");
        esMessages.put("NotRequests",prefix + ColorError + "No tienes solicitud pendientes");
        esMessages.put("DenyYour",prefix + Colorinfo + "Te denegaron la solicitud");
        esMessages.put("AcceptedYourRequest",prefix + ColorSuccess + "Tu petición ha sido aceptada");
        esMessages.put("AcceptedRequest",prefix +  ColorSuccess + "Usted ha aceptado una solicitud");
        esMessages.put("FullSites",prefix + ColorWarning + "Los mundos de duelos están llenos, espera a que termine");
        esMessages.put("SendRequest1",Colorinfo + "te an enviado una solicitud. Usa ");
        esMessages.put("SendRequest2",Colorinfo + " para aceptarla o rechazarla con ");
        esMessages.put("SendRequest",prefix + ColorSuccess + "Has enviado una solicitud a" + Colorplayer + " %player%");
        esMessages.put("DenyYou" ,prefix + ColorSuccess + "You have denied the request");
        esMessages.put("HoverYes","Sí");
        esMessages.put("HoverDeny","No");
        esMessages.put("ArenaDuel",Colorinfo + "Mundo: " + Colorplayer);
        esMessages.put("SenderPlayer",Colorinfo + "Enviado por: " + Colorplayer);
        esMessages.put("KitSelect", Colorinfo + "Kit:&r ");
        esMessages.put("TimeLimit",Colorinfo + "Tiempo Limite: " + Colorplayer);
        esMessages.put("KitFavorite", Colorplayer + "Tu kit favorito");
        esMessages.put("EndTimeDuel",prefix + Colorinfo + "Se acabo el tiempo");
        esMessages.put("ListPlayersEmpty",prefix + ColorError + "No has invitado a nadie");
        //En el mundo del duelo
        esMessages.put("EndCombat",prefix + ColorWarning + "Fin del combate, jugador desconectado");
        esMessages.put("IncorrectLoc",prefix + ColorError + "No esta permitido estar en el techo");
        esMessages.put("DuelStarted1",prefixDuel + Colorinfo + "Duelo iniciado:" + Colorplayer + " %player1% " + Colorinfo + "VS " + Colorplayer + "%player2%" + Colorinfo + " En:" + Colorplayer + " %world%");
        esMessages.put("DuelStarted2",prefixDuel + Colorinfo + "Duelo iniciado:" + Colorplayer + " %player% " + Colorinfo + "En: " + Colorplayer + "%world% " + Colorinfo + "Con estos jugadores: ");
        esMessages.put("Go","&2&lVAMOS!!");
        esMessages.put("HoverDuel", "Ver el último duelo");
        //Para modo espetador
        esMessages.put("SpectatorError",prefix + ColorError + "No has especificado el nombre del mundo");
        esMessages.put("NotCombatWorld",prefix + ColorError + "No hay combate en ese mundo");
        esMessages.put("NotFoundWorld",prefix + ColorError + "El mundo no existe");
        esMessages.put("SpectatorSuccess",Colorinfo + "Para estar en el modo de juego de supervivencia, usa: ");
        //Invetarios
        esMessages.put("InvGlobal","&a&lKits Globales");
        esMessages.put("InvCustom","&e&lKits Personalizado");
        esMessages.put("TpLobby","&b&lRegresar al lobby");
        esMessages.put("TpCreatorKits","&b&lCrea tu kit");
        esMessages.put("InvPreview","&bVista previa");
        esMessages.put("InvItemFrame","&b&lToma lo que quieras");
        esMessages.put("InvExit","&c&lSalir");
        esMessages.put("InvLoad","&a&lCargar Kit");
        esMessages.put("lore","Nombre: ");
        esMessages.put("Previous","Pagina anterior");
        esMessages.put("Next","Siguiente página");
        esMessages.put("InvClear","&b&lLimpiar inventario");
        esMessages.put("CreteKit","&b&lCrea tu kit");
        esMessages.put("KitList","&bLista de kits&r - &6&lHaga clic derecho&r&6 Para ver la vista previa");
        esMessages.put("KitMenu","&bMenu");
        esMessages.put("KitListBedrock", "&bLista de kits");
        esMessages.put("PreviewOn", "&bPrevisualización:&2&l Activada");
        esMessages.put("PreviewOff", "&bPrevisualización:&c&l Desactivada");
        esMessages.put("DuelSendQuest", "&2&lEnviar Petición");
        esMessages.put("DuelInvPlayers", "&b&lLista de jugadores invitado");
        esMessages.put("DuelSelectKit", "&b&lEl kit seleccionado");
        esMessages.put("DuelTimeLimit", "&b&lLimite de tiempo");
        esMessages.put("DuelSelectWorld", "&b&lMundo seleccionado");
        esMessages.put("DuelLoreInvPlayers", Colorinfo + "Jugadores seleccionado ");
        esMessages.put("DuelLoreInvPlayersEmpty", Colorinfo + "Invita a tus amigos!");
        esMessages.put("DuelLoreSelectKit", Colorinfo + "El kit seleccionado es: &e");
        esMessages.put("DuelLoreSelectKitEmpty", Colorinfo + "El kit seleccionado es el favorito");
        esMessages.put("DuelLoreTimeLimit", Colorinfo + "Limite de tiempo activo");
        esMessages.put("DuelLoreTimeLimitDisabled", Colorinfo + "Sin limite de tiempo");
        esMessages.put("DuelLoreSelectWorld", Colorplayer + "Seleccionado: ");
        esMessages.put("DuelTimeLimitOn",Coloritem + "Limite de tiempo activado");
        esMessages.put("DuelTimeLimitOff",Coloritem + "Limite de tiempo desactivado");
        esMessages.put("DuelTimeLore", Colorinfo + "Tiempo máximo: " + Colorplayer);
        enMessages.put("SelectKitFavorite",Coloritem  + "&lSeleccionar kit favorito");
        esMessages.put("H1mas","&21M+");
        esMessages.put("S1mas","&21S+");
        esMessages.put("S1menos","&c1S-");
        esMessages.put("H1menos","&c1M-");
        //otros
        esMessages.put("TimeBossBar", Colorinfo + "Tiempo restante: " + Colorplayer);
        esMessages.put("WarningGetGuestPlayers", ColorWarning + "Unos de jugadores que invitaste no esta o no existe");
        esMessages.put("IvnPlayers1", Coloritem + "Usa /inv");
        esMessages.put("IvnPlayers2", Colorinfo + "Escribe los nombres de los jugadores");
        esMessages.put("Alone",prefix + Colorinfo + "Si estas solo en el servidor, únete a nuestro discord para jugar con alguien.\nDiscord:" + ColorLink + "https://discord.gg/QYBwEFvnsG");
        esMessages.put("message1Minute",prefix + Colorinfo + "Limpieza en 1 minuto");
        esMessages.put("message5Seconds",prefix + Colorinfo + "Limpieza en 5 segundos");
        esMessages.put("messageStarCleaner",prefix + Colorinfo + "Limpieza iniciada");
        esMessages.put("CleanerExecuted", prefix + Colorinfo + "Limpieza ejecutada por el dueño");
        esMessages.put("Donate",prefix + Colorinfo + "Agradecería tu aporte a este proyecto " + ColorLink + "https://www.paypal.com/paypalme/xBxTpvp\n&4&l!!&r&4 Todas las donaciones son &l'DONACIONES'&r&4 no se espera recibir nada a cambio y es por voluntad propia");
        esMessages.put("Vote",prefix + Colorinfo + "Agradecería tu voto en alguna pagina:");
        esMessages.put("NewPlayer","&eBienvenido a " + ChatColor.of("#61CAFD") + "&lx" + ChatColor.of("#7CAFEC") + "&lB" + ChatColor.of("#9893DC")
                + "&lx" + ChatColor.of("#B378CB") + "&lT" + ChatColor.of("#FDC661") + "&l." + ChatColor.of("#FEAA41") + "&lx" +
                ChatColor.of("#FE8F22") + "&ly" + ChatColor.of("#FF7302") + "&lz" + Colorinfo + "\núnete a nuestro discord discord:" + ColorLink + "https://discord.gg/QYBwEFvnsG");
        //Kick
        esMessages.put("SpamCommand",prefixKick  + Colorinfo + "Echado por spam de comando");
    }

    public String MasterMessage(Player player, Messages message){
        String locate = player.getLocale().toLowerCase();
        if (locate.contains("es")){
            if (esMessages.get(message.name()) != null){
                return ChatColor.translateAlternateColorCodes('&', esMessages.get(message.name()));
            }else{
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', esErrorMensaje).replace("%message%", message.name()));
                return ChatColor.translateAlternateColorCodes('&', esErrorMensaje).replace("%message%", message.name());
            }
        }else{
            if (enMessages.get(message.name()) != null){
                return ChatColor.translateAlternateColorCodes('&', enMessages.get(message.name()));
            }else{
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', enErrorMensaje).replace("%message%", message.name()));
                return ChatColor.translateAlternateColorCodes('&', enErrorMensaje).replace("%message%", message.name());
            }
        }
    }
    public void BroadcastMessage(Messages message){
        for(Player p : Bukkit.getOnlinePlayers()){
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', MasterMessage(p,message)));
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', esMessages.get(message.name())));
    }

    public void BroadcastMessageDied(Messages message, Player player, Player killer, String item){
        String prefixWorld;

        switch (player.getWorld().getName()){
            case "lobby" -> prefixWorld = "&8[&9FFA&8]&r ";
            case "boxpvp" -> prefixWorld = "&8[&4BOXPVP&8]&r ";
            default -> prefixWorld = "&8[&eDUEL&8]&r ";
        }

        for(Player p : Bukkit.getOnlinePlayers()){
            if(killer != null){
                p.sendMessage(ChatColor.translateAlternateColorCodes( '&',MasterMessage(p,message).replace("%player%", prefixWorld + xBxTcore.getPlayerFileManager().loadPrefix(player.getUniqueId()) + Colorplayer +  player.getName()).replace("%killer%",xBxTcore.getPlayerFileManager().loadPrefix(killer.getUniqueId()) + Colorplayer + killer.getName()).replace("%item%", item)));
            }else{
                p.sendMessage(ChatColor.translateAlternateColorCodes( '&',MasterMessage(p,message).replace("%player%", prefixWorld + xBxTcore.getPlayerFileManager().loadPrefix(player.getUniqueId()) + Colorplayer +  player.getName()).replace("%item%", item)));
            }

        }
        if(killer == null){
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', esMessages.get(message.name())).replace("%player%",ChatColor.translateAlternateColorCodes('&',prefixWorld + xBxTcore.getPlayerFileManager().loadPrefix(player.getUniqueId()) + Colorplayer +  player.getName()).replace("%item%", item)));
        }else{
            Bukkit.getConsoleSender().sendMessage(xBxTcore.getPlayerFileManager().loadPrefix(player.getUniqueId()));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', esMessages.get(message.name())).replace("%player%", ChatColor.translateAlternateColorCodes('&',prefixWorld + xBxTcore.getPlayerFileManager().loadPrefix(player.getUniqueId()) + Colorplayer +  player.getName()).replace("%killer%",xBxTcore.getPlayerFileManager().loadPrefix(killer.getUniqueId()) + Colorplayer + killer.getName()).replace("%item%", item)));
        }
    }

    public void KickMessage(Player p ,Messages message){
        p.kickPlayer(MasterMessage(p,message));
    }

    public void BroadcastMessagejoin(Player player){
        for(Player p : Bukkit.getOnlinePlayers()){
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&a+&8] " + Colorplayer + player.getName() + " " + MasterMessage(p,Messages.join)));
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&a+&8] " + Colorplayer + player.getName() + " " + esMessages.get("join")));
    }

    public void BroadcastMessageleave(Player player){
        for(Player p : Bukkit.getOnlinePlayers()){
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&4-&8] " + Colorplayer + player.getName() + " " + MasterMessage(p,Messages.leave)));

        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&4-&8] " + Colorplayer + player.getName() + " " + esMessages.get("leave")));
    }


}
