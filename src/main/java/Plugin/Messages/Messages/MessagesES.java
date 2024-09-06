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
        esMessages.put(Generic_NotOp,prefix + ColorError + "No tienes permisos para usar este comando!");
        esMessages.put(Generic_NotAllowed,prefix + ColorError + "No esta permitido");
        esMessages.put(Generic_InArea,prefix + ColorError + "No se puede usar aquí");
        esMessages.put(Generic_HoverExecute,"Ejecutar este comando");
        //Vote
        esMessages.put(NotVote,ColorError + "Para tener acceso a este comando tienes que votar a este servidor con el comando: ");
        esMessages.put(TooLengthName,prefix + ColorError + "Nombre muy largo. Maximo de caracteres 10");
        esMessages.put(WordNotAllowed,prefix + ColorError + "Esa palabra no está permitida");
        esMessages.put(PrefixSuccess,prefix + ColorSuccess + "Se a puesto el prefijo en tu nombre");
        esMessages.put(Voted,Colorinfo + "Gracias Por votar, ya puedes usar el comando: ");
        esMessages.put(PrefixClear,prefix + Colorinfo + "El prefijo fue limpiado");
        //Eventos
        esMessages.put(Event_leave,Colorinfo + "Se a ido!");
        esMessages.put(Event_join,Colorinfo + "Se a unido!");
        //Muertes
        esMessages.put(Died_Died1,prefixDied + Colorplayer + "%player% " + Colorinfo + "fue asesinado por " + Colorplayer + "%killer% " + Colorinfo + "con: %item%");
        esMessages.put(Died_Died2,prefixDied + Colorplayer + "%player% " + Colorinfo + "fue explotado por " + Colorplayer + "%killer% " + Colorinfo + "con: %item%");
        esMessages.put(Died_Died3,prefixDied + Colorplayer + "%player% " + Colorinfo + "fue sofocado por " + Colorplayer + "Bloque ");
        esMessages.put(Died_Died4,prefixDied + Colorplayer + "%player% " + Colorinfo + "se a suicidado");
        esMessages.put(Died_Died5,prefixDied + Colorplayer + "%player% " + Colorinfo + "ha muerto de la caída ");
        esMessages.put(Died_DiedDuel,prefixDied + Colorinfo + "El Ganador Es: " + Colorplayer + "%killer%" + Colorinfo + " Y El Perdedor Es " + Colorplayer + "%player% ");
        //kit
        esMessages.put(Kit_KitNotExist,prefix + ColorError + "El kit no existe");
        esMessages.put(Kit_SaveError,prefix + ColorError + "Debes especificar el nombre del kit y/o el objeto");
        esMessages.put(Kit_SaveErrorPunto,prefix + ColorError + "No puede tener puntos");
        esMessages.put(Kit_DelError,prefix + ColorError + "Debes especificar el nombre del kit");
        esMessages.put(Kit_Removed,prefix + ColorSuccess + "El kit &r%namekit%"+ ColorSuccess +" fue eliminado exitosamente");
        esMessages.put(Kit_RemovedWaring,prefix + ColorWarning + "Se intento eliminar un kit que no existe");
        esMessages.put(Kit_Load,prefix + ColorSuccess + "El kit &r%namekit%"+ ColorSuccess +" fue cargado exitosamente");
        esMessages.put(Kit_Save,prefix + ColorSuccess + "El kit &r%namekit%"+ ColorSuccess +" fue salvado exitosamente");
        esMessages.put(Kit_Favorite,prefix + ColorSuccess + "El kit favorito fue guardado exitosamente");
        esMessages.put(Kit_LoadError,prefix + ColorError + "hubo un problema al cargar el kit &r%nameKit%" + ColorError + " intenta salir y entrar si se repite la situación contacta con el dueño");
        esMessages.put(Kit_LoadWaring,prefix + ColorWarning + "El Item '%item%' no se pudo cargar y sera omitido en la carga");
        esMessages.put(Kit_FavoriteWaring,prefix + ColorWarning + "El kit que se guardo aparentemente no existe pero se guardo exitosamente, Ten en cuenta que es sensible a mayúsculas y a los códigos de color de minecraft");
        //Combate Log
        esMessages.put(CombateLog_OnCombat,prefix + ColorError + "No esta permitido usar eso en combate, espera "+ "&b" + "%time%" + ColorError + " segundos para usarlo");
        esMessages.put(CombateLog_InSafeZone,"&4Sal De Zona Segura En %time% Segundos");
        //Las Solisituds del Duel
        esMessages.put(RequestDuel_Inv,prefix + ColorSuccess + "Se guardo exitosamente la lista de invitados");
        esMessages.put(RequestDuel_MissingPlayers,prefix + Colorinfo + "Jugadores faltantes ");
        esMessages.put(RequestDuel_SelfAccepted,prefix + ColorError + "No puede aceptar o denegar tu propia solicitud");
        esMessages.put(RequestDuel_OnDuel,prefix + ColorError + "Ya estas en un duelo");
        esMessages.put(RequestDuel_SendSelf,prefix + ColorError + "No puede enviarte a tí mismo");
        esMessages.put(RequestDuel_RequestExpired,prefix + ColorError + "La solicitud ya expiro");
        esMessages.put(RequestDuel_WorldType,prefix + ColorError + "Seleccioná un mundo 'bedrock','flat_Bedrcok' o 'flat_world'");
        esMessages.put(RequestDuel_DuelError,prefix + ColorError + "Tienes que especificar el nombre del usuario y especificar el tipo de mundo (opcional)");
        esMessages.put(RequestDuel_PlayerOffTarget,prefix + ColorError + "El jugador no esta en línea");
        esMessages.put(RequestDuel_PlayerOffSender,prefix + ColorError + "El jugador que te envió la solicitud no esta en línea");
        esMessages.put(RequestDuel_NotRequests,prefix + ColorError + "No tienes solicitud pendientes");
        esMessages.put(RequestDuel_DenyYour,prefix + Colorinfo + "Te denegaron la solicitud");
        esMessages.put(RequestDuel_AcceptedYourRequest,prefix + ColorSuccess + "Tu petición ha sido aceptada");
        esMessages.put(RequestDuel_AcceptedRequest,prefix +  ColorSuccess + "Usted ha aceptado una solicitud");
        esMessages.put(RequestDuel_FullSites,prefix + ColorWarning + "Los mundos de duelos están llenos, espera a que termine");
        esMessages.put(RequestDuel_SendRequest1,Colorinfo + "te an enviado una solicitud. Usa ");
        esMessages.put(RequestDuel_SendRequest2,Colorinfo + " para aceptarla o rechazarla con ");
        esMessages.put(RequestDuel_SendRequest,prefix + ColorSuccess + "Has enviado una solicitud a" + Colorplayer + " %player%");
        esMessages.put(RequestDuel_DenyYou,prefix + ColorSuccess + "You have denied the request");
        esMessages.put(RequestDuel_HoverYes,"Sí");
        esMessages.put(RequestDuel_HoverDeny,"No");
        esMessages.put(RequestDuel_ArenaDuel,Colorinfo + "Mundo: " + Colorplayer);
        esMessages.put(RequestDuel_SenderPlayer,Colorinfo + "Enviado por: " + Colorplayer);
        esMessages.put(RequestDuel_KitSelect, Colorinfo + "Kit:&r ");
        esMessages.put(RequestDuel_TimeLimit,Colorinfo + "Tiempo Limite: " + Colorplayer);
        esMessages.put(RequestDuel_KitFavorite, Colorplayer + "Tu kit favorito");
        esMessages.put(DuelWorld_EndTimeDuel,prefix + Colorinfo + "Se acabo el tiempo");
        esMessages.put(DuelWorld_ListPlayersEmpty,prefix + ColorError + "No has invitado a nadie");
        //En el mundo del duelo
        esMessages.put(DuelWorld_EndCombat,prefix + ColorWarning + "Fin del combate, jugador desconectado");
        esMessages.put(DuelWorld_IncorrectLoc,prefix + ColorError + "No esta permitido estar en el techo");
        esMessages.put(DuelWorld_DuelStarted1,prefixDuel + Colorinfo + "Duelo iniciado:" + Colorplayer + " %player1% " + Colorinfo + "VS " + Colorplayer + "%player2%" + Colorinfo + " En:" + Colorplayer + " %world%");
        esMessages.put(DuelWorld_DuelStarted2,prefixDuel + Colorinfo + "Duelo iniciado:" + Colorplayer + " %player% " + Colorinfo + "En: " + Colorplayer + "%world% " + Colorinfo + "Con estos jugadores: ");
        esMessages.put(DuelWorld_Go,"&2&lVAMOS!!");
        esMessages.put(DuelWorld_HoverDuel, "Ver el último duelo");
        //Para modo espetador
        esMessages.put(SpectatorMode_SpectatorError,prefix + ColorError + "No has especificado el nombre del mundo");
        esMessages.put(SpectatorMode_NotCombatWorld,prefix + ColorError + "No hay combate en ese mundo");
        esMessages.put(SpectatorMode_NotFoundWorld,prefix + ColorError + "El mundo no existe");
        esMessages.put(SpectatorMode_SpectatorSuccess,Colorinfo + "Para estar en el modo de juego de supervivencia, usa: ");
        //Recompensa
        esMessages.put(Reward_Daily, Coloritem + "Recompensa Diaria");
        esMessages.put(Reward_Weekly, Coloritem + "Recompensa Semanal");
        esMessages.put(Reward_Monthly, Coloritem + "Recompensa Mensual");
        esMessages.put(Reward_RewardNotYet,prefix + ColorError + "No puede tomar la recompensa aún");
        esMessages.put(Reward_GiveDaily, prefix + ColorSuccess + "Haz reclamado la recompensa diaria");
        esMessages.put(Reward_GiveWeekly, prefix + ColorSuccess + "Haz reclamado la recompensa semanal");
        esMessages.put(Reward_GiveMonthly, prefix + ColorSuccess + "Haz reclamado la recompensa mensual");
        esMessages.put(Reward_CrateNotPermission, prefix + ColorError + "No eres vip lo puede conseguir aquí " + ColorLink + "En revision");
        esMessages.put(Reward_ClaimReward, prefix + ColorSuccess + "Haz reclamado una recompensa");
        //tienda
        esMessages.put(Reward_StoreLink, prefix + Colorinfo + "Puedes comprar Aquí!!");
        esMessages.put(Reward_BuysTitel,ColorSuccess + "Gracias por comprar!!!");
        esMessages.put(Reward_BuysTitelLower,ColorSuccess + "Espero que lo disfrutes: " + Colorplayer + "%compra%");
        esMessages.put(Reward_BuyGeneric,prefix + "\n \n" + Coloritem + Colorplayer + "%player% " + Colorinfo + "ha comprado " + Colorplayer + "%compra%\n \n"
                + Colorinfo + "También puedes comprarlo!!\n" + "En Tienda: " + ColorLink + "aún en revision\n&r" + " \n");
        //login
        esMessages.put(Login_Registration_RegisterRequest, prefix + Colorinfo + "Por favor, regístrese en el servidor con el comando: /register <password> <ConfirmPassword>");
        esMessages.put(Login_Registration_CommandUsage, prefix + Colorinfo + "Usage: /register <contraseña> <ConfirmPassword>");
        esMessages.put(Login_Registration_Success, prefix + ColorSuccess + "¡Registrado exitosamente!");
        esMessages.put(Login_Registration_disabled, prefix + Colorinfo + "¡El registro en el juego está deshabilitado!");
        esMessages.put(Login_Registration_NameTaken, prefix + ColorError + "Ya has registrado este nombre de usuario!");
        esMessages.put(Login_Password_MatchError, prefix + ColorError + "Las contraseñas no coinciden, ¡verifícalas de nuevo!");
        esMessages.put(Login_Password_NameInPassword, prefix + ColorWarning + "No puedes usar tu nombre como contraseña, elige otra...");
        esMessages.put(Login_Password_UnsafePassword, prefix + ColorWarning + "La contraseña elegida no es segura, elige otra...");
        esMessages.put(Login_Password_OrbiddenCharactfers, prefix + ColorError + "Tu contraseña contiene caracteres no permitidos.");
        esMessages.put(Login_Password_WrongLength, prefix + ColorError + "¡Su contraseña es demasiado corta o demasiado larga! ¡Por favor, intente con otra!");
        esMessages.put(Login_Login_CommandUsage, prefix + Colorinfo + "Uso: /login <contraseña>");
        esMessages.put(Login_Login_Success, prefix + ColorSuccess + "¡Inició sesión correctamente!");
        esMessages.put(Login_Login_LoginRequest, prefix + Colorinfo + "Por favor, inicie sesión con el comando: /login <contraseña>");
        esMessages.put(Login_Login_TimeoutError, prefix + ColorError + "Se ha excedido el tiempo de espera para iniciar sesión, ha sido expulsado del servidor, ¡inténtelo de nuevo!");
        esMessages.put(Login_Error_UnregisteredUser, prefix + ColorError + "¡Este usuario no está registrado!");
        esMessages.put(Login_Error_DeniedCommand, prefix + ColorError + "¡Para poder usar este comando, debe estar autenticado!");
        esMessages.put(Login_Error_DeniedChat, prefix + ColorError + "¡Para poder chatear, debe estar autenticado!");
        esMessages.put(Login_Error_NotLoggedIn, prefix + ColorError + "¡No ha iniciado sesión!");
        esMessages.put(Login_Error_TempbanMaxLogins, prefix + ColorError + "Se le ha prohibido temporalmente iniciar sesión debido a que no ha podido iniciar sesión demasiadas veces.");
        esMessages.put(Login_Error_MaxRegistration, prefix + ColorError + "Ha superado el número máximo de registros");
        esMessages.put(Login_Error_NoRermission, prefix + ColorError + "¡No tiene permiso para realizar esta acción!");
        esMessages.put(Login_Error_UnexpectedError, prefix + ColorError + "¡Se produjo un error inesperado, póngase en contacto con un administrador!");
        esMessages.put(Login_Error_ErrorKickForVip, prefix + ColorError + "¡Un jugador VIP se ha unido al servidor cuando estaba lleno!");
        esMessages.put(Login_Error_LoggedIn, prefix + ColorError + "¡Ya has iniciado sesión!");
        esMessages.put(Login_Error_KickUnresolvedHostname, prefix + ColorError + "¡Se produjo un error: nombre de host del jugador sin resolver!");
        //Invetarios
        esMessages.put(Inventory_InvGlobal,"&a&lKits Globales");
        esMessages.put(Inventory_InvCustom,"&e&lKits Personalizado");
        esMessages.put(Inventory_TpLobby,"&b&lRegresar al lobby");
        esMessages.put(Inventory_TpCreatorKits,"&b&lCrea tu kit");
        esMessages.put(Inventory_InvPreview,"&bVista previa");
        esMessages.put(Inventory_InvItemFrame,"&b&lToma lo que quieras");
        esMessages.put(Inventory_InvExit,"&c&lSalir");
        esMessages.put(Inventory_InvLoad,"&a&lCargar Kit");
        esMessages.put(Inventory_lore,"Nombre: ");
        esMessages.put(Inventory_Previous,"Pagina anterior");
        esMessages.put(Inventory_Next,"Siguiente página");
        esMessages.put(Inventory_InvClear,"&b&lLimpiar inventario");
        esMessages.put(Inventory_CreteKit,"&b&lCrea tu kit");
        esMessages.put(Inventory_KitList,"&bLista de kits&r - &6&lHaga clic derecho&r&6 Para ver la vista previa");
        esMessages.put(Inventory_KitMenu,"&bMenu");
        esMessages.put(Inventory_KitListBedrock, "&bLista de kits");
        esMessages.put(Inventory_PreviewOn, "&bPrevisualización:&2&l Activada");
        esMessages.put(Inventory_PreviewOff, "&bPrevisualización:&c&l Desactivada");
        esMessages.put(Inventory_DuelSendQuest, "&2&lEnviar Petición");
        esMessages.put(Inventory_DuelInvPlayers, "&b&lLista de jugadores invitado");
        esMessages.put(Inventory_DuelSelectKit, "&b&lEl kit seleccionado");
        esMessages.put(Inventory_DuelTimeLimit, "&b&lLimite de tiempo");
        esMessages.put(Inventory_DuelSelectWorld, "&b&lMundo seleccionado");
        esMessages.put(Inventory_DuelLoreInvPlayers, Colorinfo + "Jugadores seleccionado ");
        esMessages.put(Inventory_DuelLoreInvPlayersEmpty, Colorinfo + "Invita a tus amigos!");
        esMessages.put(Inventory_DuelLoreSelectKit, Colorinfo + "El kit seleccionado es: &e");
        esMessages.put(Inventory_DuelLoreSelectKitEmpty, Colorinfo + "El kit seleccionado es el favorito");
        esMessages.put(Inventory_DuelLoreTimeLimit, Colorinfo + "Limite de tiempo activo");
        esMessages.put(Inventory_DuelLoreTimeLimitDisabled, Colorinfo + "Sin limite de tiempo");
        esMessages.put(Inventory_DuelLoreSelectWorld, Colorplayer + "Seleccionado: ");
        esMessages.put(Inventory_DuelTimeLimitOn,Coloritem + "Limite de tiempo activado");
        esMessages.put(Inventory_DuelTimeLimitOff,Coloritem + "Limite de tiempo desactivado");
        esMessages.put(Inventory_DuelTimeLore, Colorinfo + "Tiempo máximo: " + Colorplayer);
        esMessages.put(Inventory_SelectKitFavorite,Coloritem  + "&lSeleccionar kit favorito");
        esMessages.put(Inventory_H1mas,"&21M+");
        esMessages.put(Inventory_S1mas,"&21S+");
        esMessages.put(Inventory_S1menos,"&c1S-");
        esMessages.put(Inventory_H1menos,"&c1M-");
        //otros
        esMessages.put(Others_TimeBossBar, Colorinfo + "Tiempo restante: " + Colorplayer);
        esMessages.put(Others_WarningGetGuestPlayers, ColorWarning + "Unos de jugadores que invitaste no esta o no existe");
        esMessages.put(Others_IvnPlayers1, Coloritem + "Usa /inv");
        esMessages.put(Others_IvnPlayers2, Colorinfo + "Escribe los nombres de los jugadores");
        esMessages.put(Others_Alone,prefix + Colorinfo + "Si estas solo en el servidor, únete a nuestro discord para jugar con alguien.\nDiscord:" + ColorLink + "https://discord.gg/QYBwEFvnsG");
        esMessages.put(Others_message1Minute,prefix + Colorinfo + "Limpieza en 1 minuto");
        esMessages.put(Others_message5Seconds,prefix + Colorinfo + "Limpieza en 5 segundos");
        esMessages.put(Others_messageStarCleaner,prefix + Colorinfo + "Limpieza iniciada");
        esMessages.put(Others_CleanerExecuted, prefix + Colorinfo + "Limpieza ejecutada por el dueño");
        esMessages.put(Others_Donate,prefix + Colorinfo + "Agradecería tu aporte a este proyecto " + ColorLink + "https://www.paypal.com/paypalme/xBxTpvp\n&4&l!!&r&4 Todas las donaciones son &l'DONACIONES'&r&4 no se espera recibir nada a cambio y es por voluntad propia");
        esMessages.put(Others_Vote,prefix + Colorinfo + "Agradecería tu voto en alguna pagina:");
        esMessages.put(Others_NewPlayer,"&eBienvenido a " + ChatColor.of("#61CAFD") + "&lx" + ChatColor.of("#7CAFEC") + "&lB" + ChatColor.of("#9893DC")
                + "&lx" + ChatColor.of("#B378CB") + "&lT" + ChatColor.of("#FDC661") + "&l." + ChatColor.of("#FEAA41") + "&lx" +
                ChatColor.of("#FE8F22") + "&ly" + ChatColor.of("#FF7302") + "&lz" + Colorinfo + "\núnete a nuestro discord discord:" + ColorLink + "https://discord.gg/QYBwEFvnsG");
        esMessages.put(Others_OpNotAuthized,Colorinfo + "&lXD&r" + Colorinfo + " mas grande de todos. En serio?, ve hackear a otros servidores, por que en el mio NO\ny por cierto no te baneo por que quiero saber como lo conseguiste. osea has lo tuyo para conseguir op o creativo");
        //Kick
        esMessages.put(Kick_SpamCommand,prefixKick  + Colorinfo + "Echado por spam de comando");
        esMessages.put(Kick_AlreadyConnected,prefixKick  + Colorinfo + "Este Usuario ya esta conectado");
    }
}
