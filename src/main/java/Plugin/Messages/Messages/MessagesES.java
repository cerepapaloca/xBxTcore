package Plugin.Messages.Messages;

import Plugin.Messages.Enum.Messages;
import net.md_5.bungee.api.ChatColor;

import java.util.HashMap;
import java.util.Map;

import static Plugin.Messages.Enum.Messages.*;
import static Plugin.Messages.MessageManager.*;

public record MessagesES() {

    public static final Map<Messages, String> esMessages = new HashMap<>();

    public MessagesES() {
        //Generico
        esMessages.put(NotOp,prefix + ColorError + "No tienes permisos para usar este comando!");
        esMessages.put(NotAllowed,prefix + ColorError + "No esta permitido");
        esMessages.put(InArea,prefix + ColorError + "No se puede usar aquí");
        esMessages.put(HoverExecute,"Ejecutar este comando");
        //Vote
        esMessages.put(NotVote,ColorError + "Para tener acceso a este comando tienes que votar a este servidor con el comando: ");
        esMessages.put(TooLengthName,prefix + ColorError + "Nombre muy largo. Maximo de caracteres 10");
        esMessages.put(WordNotAllowed,prefix + ColorError + "Esa palabra no está permitida");
        esMessages.put(PrefixSuccess,prefix + ColorSuccess + "Se a puesto el prefijo en tu nombre");
        esMessages.put(Voted,Colorinfo + "Gracias Por votar, ya puedes usar el comando: ");
        esMessages.put(PrefixClear,prefix + Colorinfo + "El prefijo fue limpiado");
        //Eventos
        esMessages.put(leave,Colorinfo + "Se a ido!");
        esMessages.put(join,Colorinfo + "Se a unido!");
        //Muertes
        esMessages.put(Died1,prefixDied + Colorplayer + "%player% " + Colorinfo + "fue asesinado por " + Colorplayer + "%killer% " + Colorinfo + "con: %item%");
        esMessages.put(Died2,prefixDied + Colorplayer + "%player% " + Colorinfo + "fue explotado por " + Colorplayer + "%killer% " + Colorinfo + "con: %item%");
        esMessages.put(Died3,prefixDied + Colorplayer + "%player% " + Colorinfo + "fue sofocado por " + Colorplayer + "Bloque ");
        esMessages.put(Died4,prefixDied + Colorplayer + "%player% " + Colorinfo + "se a suicidado");
        esMessages.put(Died5,prefixDied + Colorplayer + "%player% " + Colorinfo + "ha muerto de la caída ");
        esMessages.put(DiedDuel,prefixDied + Colorinfo + "El Ganador Es: " + Colorplayer + "%killer%" + Colorinfo + " Y El Perdedor Es " + Colorplayer + "%player% ");
        //kit
        esMessages.put(KitNotExist,prefix + ColorError + "El kit no existe");
        esMessages.put(SaveError,prefix + ColorError + "Debes especificar el nombre del kit y/o el objeto");
        esMessages.put(SaveErrorPunto,prefix + ColorError + "No puede tener puntos");
        esMessages.put(DelError,prefix + ColorError + "Debes especificar el nombre del kit");
        esMessages.put(Removed,prefix + ColorSuccess + "El kit &r%namekit%"+ ColorSuccess +" fue eliminado exitosamente");
        esMessages.put(RemovedWaring,prefix + ColorWarning + "Se intento eliminar un kit que no existe");
        esMessages.put(Load,prefix + ColorSuccess + "El kit &r%namekit%"+ ColorSuccess +" fue cargado exitosamente");
        esMessages.put(Save,prefix + ColorSuccess + "El kit &r%namekit%"+ ColorSuccess +" fue salvado exitosamente");
        esMessages.put(Favorite,prefix + ColorSuccess + "El kit favorito fue guardado exitosamente");
        esMessages.put(LoadError,prefix + ColorError + "hubo un problema al cargar el kit &r%nameKit%" + ColorError + " intenta salir y entrar si se repite la situación contacta con el dueño");
        esMessages.put(LoadWaring,prefix + ColorWarning + "El Item '%item%' no se pudo cargar y sera omitido en la carga");
        esMessages.put(FavoriteWaring,prefix + ColorWarning + "El kit que se guardo aparentemente no existe pero se guardo exitosamente, Ten en cuenta que es sensible a mayúsculas y a los códigos de color de minecraft");
        //Combate Log
        esMessages.put(OnCombat,prefix + ColorError + "No esta permitido usar eso en combate, espera "+ "&b" + "%time%" + ColorError + " segundos para usarlo");
        esMessages.put(InSafeZone,"&4Sal De Zona Segura En %time% Segundos");
        //Las Solisituds del Duel
        esMessages.put(Inv,prefix + ColorSuccess + "Se guardo exitosamente la lista de invitados");
        esMessages.put(MissingPlayers,prefix + Colorinfo + "Jugadores faltantes ");
        esMessages.put(SelfAccepted,prefix + ColorError + "No puede aceptar o denegar tu propia solicitud");
        esMessages.put(OnDuel,prefix + ColorError + "Ya estas en un duelo");
        esMessages.put(SendSelf,prefix + ColorError + "No puede enviarte a tí mismo");
        esMessages.put(RequestExpired,prefix + ColorError + "La solicitud ya expiro");
        esMessages.put(WorldType,prefix + ColorError + "Seleccioná un mundo 'bedrock','flat_Bedrcok' o 'flat_world'");
        esMessages.put(DuelError,prefix + ColorError + "Tienes que especificar el nombre del usuario y especificar el tipo de mundo (opcional)");
        esMessages.put(PlayerOffTarget,prefix + ColorError + "El jugador no esta en línea");
        esMessages.put(PlayerOffSender,prefix + ColorError + "El jugador que te envió la solicitud no esta en línea");
        esMessages.put(NotRequests,prefix + ColorError + "No tienes solicitud pendientes");
        esMessages.put(DenyYour,prefix + Colorinfo + "Te denegaron la solicitud");
        esMessages.put(AcceptedYourRequest,prefix + ColorSuccess + "Tu petición ha sido aceptada");
        esMessages.put(AcceptedRequest,prefix +  ColorSuccess + "Usted ha aceptado una solicitud");
        esMessages.put(FullSites,prefix + ColorWarning + "Los mundos de duelos están llenos, espera a que termine");
        esMessages.put(SendRequest1,Colorinfo + "te an enviado una solicitud. Usa ");
        esMessages.put(SendRequest2,Colorinfo + " para aceptarla o rechazarla con ");
        esMessages.put(SendRequest,prefix + ColorSuccess + "Has enviado una solicitud a" + Colorplayer + " %player%");
        esMessages.put(DenyYou ,prefix + ColorSuccess + "You have denied the request");
        esMessages.put(HoverYes,"Sí");
        esMessages.put(HoverDeny,"No");
        esMessages.put(ArenaDuel,Colorinfo + "Mundo: " + Colorplayer);
        esMessages.put(SenderPlayer,Colorinfo + "Enviado por: " + Colorplayer);
        esMessages.put(KitSelect, Colorinfo + "Kit:&r ");
        esMessages.put(TimeLimit,Colorinfo + "Tiempo Limite: " + Colorplayer);
        esMessages.put(KitFavorite, Colorplayer + "Tu kit favorito");
        esMessages.put(EndTimeDuel,prefix + Colorinfo + "Se acabo el tiempo");
        esMessages.put(ListPlayersEmpty,prefix + ColorError + "No has invitado a nadie");
        //En el mundo del duelo
        esMessages.put(EndCombat,prefix + ColorWarning + "Fin del combate, jugador desconectado");
        esMessages.put(IncorrectLoc,prefix + ColorError + "No esta permitido estar en el techo");
        esMessages.put(DuelStarted1,prefixDuel + Colorinfo + "Duelo iniciado:" + Colorplayer + " %player1% " + Colorinfo + "VS " + Colorplayer + "%player2%" + Colorinfo + " En:" + Colorplayer + " %world%");
        esMessages.put(DuelStarted2,prefixDuel + Colorinfo + "Duelo iniciado:" + Colorplayer + " %player% " + Colorinfo + "En: " + Colorplayer + "%world% " + Colorinfo + "Con estos jugadores: ");
        esMessages.put(Go,"&2&lVAMOS!!");
        esMessages.put(HoverDuel, "Ver el último duelo");
        //Para modo espetador
        esMessages.put(SpectatorError,prefix + ColorError + "No has especificado el nombre del mundo");
        esMessages.put(NotCombatWorld,prefix + ColorError + "No hay combate en ese mundo");
        esMessages.put(NotFoundWorld,prefix + ColorError + "El mundo no existe");
        esMessages.put(SpectatorSuccess,Colorinfo + "Para estar en el modo de juego de supervivencia, usa: ");
        //Recompensa
        esMessages.put(Daily, Coloritem + "Recompensa Diaria");
        esMessages.put(Weekly, Coloritem + "Recompensa Semanal");
        esMessages.put(Monthly, Coloritem + "Recompensa Mensual");
        esMessages.put(RewardNotYet,prefix + ColorError + "No puede tomar la recompensa aún");
        esMessages.put(GiveDaily, prefix + ColorSuccess + "Haz reclamado la recompensa diaria");
        esMessages.put(GiveWeekly, prefix + ColorSuccess + "Haz reclamado la recompensa semanal");
        esMessages.put(GiveMonthly, prefix + ColorSuccess + "Haz reclamado la recompensa mensual");
        esMessages.put(CrateNotPermission, prefix + ColorError + "No eres vip lo puede conseguir aquí " + ColorLink + "En revision");
        esMessages.put(ClaimReward, prefix + ColorSuccess + "Haz reclamado una recompensa");
        //tienda
        esMessages.put(StoreLink, prefix + Colorinfo + "Puedes comprar Aquí!!");
        esMessages.put(BuysTitel,ColorSuccess + "Gracias por comprar!!!");
        esMessages.put(BuysTitelLower,ColorSuccess + "Espero que lo disfrutes: " + Colorplayer + "%compra%");
        esMessages.put(BuyGeneric,prefix + "\n \n" + Coloritem + Colorplayer + "%player% " + Colorinfo + "ha comprado " + Colorplayer + "%compra%\n \n"
                + Colorinfo + "También puedes comprarlo!!\n" + "En Tienda: " + ColorLink + "aún en revision\n&r" + " \n");
        //login
        esMessages.put(Registration_RegisterRequest, prefix + Colorinfo + "Por favor, regístrese en el servidor con el comando: /register <password> <ConfirmPassword>");
        esMessages.put(Registration_CommandUsage, prefix + Colorinfo + "Usage: /register <contraseña> <ConfirmPassword>");
        esMessages.put(Registration_Success, prefix + ColorSuccess + "¡Registrado exitosamente!");
        esMessages.put(Registration_disabled, prefix + Colorinfo + "¡El registro en el juego está deshabilitado!");
        esMessages.put(Registration_NameTaken, prefix + ColorError + "Ya has registrado este nombre de usuario!");
        esMessages.put(Password_MatchError, prefix + ColorError + "Las contraseñas no coinciden, ¡verifícalas de nuevo!");
        esMessages.put(Password_NameInPassword, prefix + ColorWarning + "No puedes usar tu nombre como contraseña, elige otra...");
        esMessages.put(Password_UnsafePassword, prefix + ColorWarning + "La contraseña elegida no es segura, elige otra...");
        esMessages.put(Password_OrbiddenCharactfers, prefix + ColorError + "Tu contraseña contiene caracteres no permitidos.");
        esMessages.put(Password_WrongLength, prefix + ColorError + "¡Su contraseña es demasiado corta o demasiado larga! ¡Por favor, intente con otra!");
        esMessages.put(Login_CommandUsage, prefix + Colorinfo + "Uso: /login <contraseña>");
        esMessages.put(Login_Success, prefix + ColorSuccess + "¡Inició sesión correctamente!");
        esMessages.put(Login_LoginRequest, prefix + Colorinfo + "Por favor, inicie sesión con el comando: /login <contraseña>");
        esMessages.put(Login_TimeoutError, prefix + ColorError + "Se ha excedido el tiempo de espera para iniciar sesión, ha sido expulsado del servidor, ¡inténtelo de nuevo!");
        esMessages.put(Error_UnregisteredUser, prefix + ColorError + "¡Este usuario no está registrado!");
        esMessages.put(Error_DeniedCommand, prefix + ColorError + "¡Para poder usar este comando, debe estar autenticado!");
        esMessages.put(Error_DeniedChat, prefix + ColorError + "¡Para poder chatear, debe estar autenticado!");
        esMessages.put(Error_NotLoggedIn, prefix + ColorError + "¡No ha iniciado sesión!");
        esMessages.put(Error_TempbanMaxLogins, prefix + ColorError + "Se le ha prohibido temporalmente iniciar sesión debido a que no ha podido iniciar sesión demasiadas veces.");
        esMessages.put(Error_MaxRegistration, prefix + ColorError + "Ha superado el número máximo de registros");
        esMessages.put(Error_NoRermission, prefix + ColorError + "¡No tiene permiso para realizar esta acción!");
        esMessages.put(Error_UnexpectedError, prefix + ColorError + "¡Se produjo un error inesperado, póngase en contacto con un administrador!");
        esMessages.put(Error_ErrorKickForVip, prefix + ColorError + "¡Un jugador VIP se ha unido al servidor cuando estaba lleno!");
        esMessages.put(Error_LoggedIn, prefix + ColorError + "¡Ya has iniciado sesión!");
        esMessages.put(Error_KickUnresolvedHostname, prefix + ColorError + "¡Se produjo un error: nombre de host del jugador sin resolver!");
        //Invetarios
        esMessages.put(InvGlobal,"&a&lKits Globales");
        esMessages.put(InvCustom,"&e&lKits Personalizado");
        esMessages.put(TpLobby,"&b&lRegresar al lobby");
        esMessages.put(TpCreatorKits,"&b&lCrea tu kit");
        esMessages.put(InvPreview,"&bVista previa");
        esMessages.put(InvItemFrame,"&b&lToma lo que quieras");
        esMessages.put(InvExit,"&c&lSalir");
        esMessages.put(InvLoad,"&a&lCargar Kit");
        esMessages.put(lore,"Nombre: ");
        esMessages.put(Previous,"Pagina anterior");
        esMessages.put(Next,"Siguiente página");
        esMessages.put(InvClear,"&b&lLimpiar inventario");
        esMessages.put(CreteKit,"&b&lCrea tu kit");
        esMessages.put(KitList,"&bLista de kits&r - &6&lHaga clic derecho&r&6 Para ver la vista previa");
        esMessages.put(KitMenu,"&bMenu");
        esMessages.put(KitListBedrock, "&bLista de kits");
        esMessages.put(PreviewOn, "&bPrevisualización:&2&l Activada");
        esMessages.put(PreviewOff, "&bPrevisualización:&c&l Desactivada");
        esMessages.put(DuelSendQuest, "&2&lEnviar Petición");
        esMessages.put(DuelInvPlayers, "&b&lLista de jugadores invitado");
        esMessages.put(DuelSelectKit, "&b&lEl kit seleccionado");
        esMessages.put(DuelTimeLimit, "&b&lLimite de tiempo");
        esMessages.put(DuelSelectWorld, "&b&lMundo seleccionado");
        esMessages.put(DuelLoreInvPlayers, Colorinfo + "Jugadores seleccionado ");
        esMessages.put(DuelLoreInvPlayersEmpty, Colorinfo + "Invita a tus amigos!");
        esMessages.put(DuelLoreSelectKit, Colorinfo + "El kit seleccionado es: &e");
        esMessages.put(DuelLoreSelectKitEmpty, Colorinfo + "El kit seleccionado es el favorito");
        esMessages.put(DuelLoreTimeLimit, Colorinfo + "Limite de tiempo activo");
        esMessages.put(DuelLoreTimeLimitDisabled, Colorinfo + "Sin limite de tiempo");
        esMessages.put(DuelLoreSelectWorld, Colorplayer + "Seleccionado: ");
        esMessages.put(DuelTimeLimitOn,Coloritem + "Limite de tiempo activado");
        esMessages.put(DuelTimeLimitOff,Coloritem + "Limite de tiempo desactivado");
        esMessages.put(DuelTimeLore, Colorinfo + "Tiempo máximo: " + Colorplayer);
        esMessages.put(SelectKitFavorite,Coloritem  + "&lSeleccionar kit favorito");
        esMessages.put(H1mas,"&21M+");
        esMessages.put(S1mas,"&21S+");
        esMessages.put(S1menos,"&c1S-");
        esMessages.put(H1menos,"&c1M-");
        //otros
        esMessages.put(TimeBossBar, Colorinfo + "Tiempo restante: " + Colorplayer);
        esMessages.put(WarningGetGuestPlayers, ColorWarning + "Unos de jugadores que invitaste no esta o no existe");
        esMessages.put(IvnPlayers1, Coloritem + "Usa /inv");
        esMessages.put(IvnPlayers2, Colorinfo + "Escribe los nombres de los jugadores");
        esMessages.put(Alone,prefix + Colorinfo + "Si estas solo en el servidor, únete a nuestro discord para jugar con alguien.\nDiscord:" + ColorLink + "https://discord.gg/QYBwEFvnsG");
        esMessages.put(message1Minute,prefix + Colorinfo + "Limpieza en 1 minuto");
        esMessages.put(message5Seconds,prefix + Colorinfo + "Limpieza en 5 segundos");
        esMessages.put(messageStarCleaner,prefix + Colorinfo + "Limpieza iniciada");
        esMessages.put(CleanerExecuted, prefix + Colorinfo + "Limpieza ejecutada por el dueño");
        esMessages.put(Donate,prefix + Colorinfo + "Agradecería tu aporte a este proyecto " + ColorLink + "https://www.paypal.com/paypalme/xBxTpvp\n&4&l!!&r&4 Todas las donaciones son &l'DONACIONES'&r&4 no se espera recibir nada a cambio y es por voluntad propia");
        esMessages.put(Vote,prefix + Colorinfo + "Agradecería tu voto en alguna pagina:");
        esMessages.put(NewPlayer,"&eBienvenido a " + ChatColor.of("#61CAFD") + "&lx" + ChatColor.of("#7CAFEC") + "&lB" + ChatColor.of("#9893DC")
                + "&lx" + ChatColor.of("#B378CB") + "&lT" + ChatColor.of("#FDC661") + "&l." + ChatColor.of("#FEAA41") + "&lx" +
                ChatColor.of("#FE8F22") + "&ly" + ChatColor.of("#FF7302") + "&lz" + Colorinfo + "\núnete a nuestro discord discord:" + ColorLink + "https://discord.gg/QYBwEFvnsG");
        esMessages.put(OpNotAuthized,Colorinfo + "&lXD&r" + Colorinfo + " mas grande de todos. En serio?, ve hackear a otros servidores, por que en el mio NO\ny por cierto no te baneo por que quiero saber como lo conseguiste. osea has lo tuyo para conseguir op o creativo");
        //Kick
        esMessages.put(SpamCommand,prefixKick  + Colorinfo + "Echado por spam de comando");
        esMessages.put(AlreadyConnected,prefixKick  + Colorinfo + "Este Usuario ya esta conectado");
    }
}
