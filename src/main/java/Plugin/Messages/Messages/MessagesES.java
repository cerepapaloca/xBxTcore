package Plugin.Messages.Messages;

import net.md_5.bungee.api.ChatColor;

import java.util.HashMap;
import java.util.Map;

import static Plugin.Messages.Messages.Messages.*;
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
        esMessages.put(Vote_Voted,prefix + ColorSuccess + "Gracias Por votar");
        esMessages.put(Vote_NotBoxPvp, prefix + Colorinfo + "Tienes que entrar al box pvp para recibir las recompensas");
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
        esMessages.put(Reward_CrateNotPermission, prefix + ColorError + "No eres vip lo puede conseguir aquí " + LinkTienda);
        esMessages.put(Reward_ClaimReward, prefix + ColorSuccess + "Haz reclamado una recompensa");
        //tienda
        esMessages.put(Reward_StoreLink, prefix + Colorinfo + "Puedes comprar Aquí!!");
        esMessages.put(Reward_BuysTitel,ColorSuccess + "Gracias por comprar!!!");
        esMessages.put(Reward_BuysTitelLower,ColorSuccess + "Espero que lo disfrutes: " + Colorplayer + "%compra%");
        esMessages.put(Reward_BuyGeneric,prefix + "\n \n" + Coloritem + Colorplayer + "%player% " + Colorinfo + "ha comprado " + Colorplayer + "%compra%\n \n"
                + Colorinfo + "También puedes comprarlo!!\n" + "En Tienda: " + LinkTienda + "\n&r" + " \n");
        //login
        esMessages.put(Login_Registration_RegisterRequest, prefix + prefixLogin + Colorinfo + "Por favor, regístrese en el servidor con el comando: /register <password> <ConfirmPassword>");
        esMessages.put(Login_Registration_CommandUsage, prefix + prefixLogin + Colorinfo + "Usage: /register <contraseña> <ConfirmPassword>");
        esMessages.put(Login_Registration_Success, prefix + prefixLogin + ColorSuccess + "¡Registrado exitosamente!");
        esMessages.put(Login_Registration_disabled, prefix + prefixLogin + Colorinfo + "¡El registro en el juego está deshabilitado!");
        esMessages.put(Login_Registration_NameTaken, prefix + prefixLogin + ColorError + "Ya has registrado este nombre de usuario!");
        esMessages.put(Login_Password_MatchError, prefix + prefixLogin + ColorError + "Las contraseñas no coinciden, ¡verifícalas de nuevo!");
        esMessages.put(Login_Password_NameInPassword, prefix + prefixLogin + ColorWarning + "No puedes usar tu nombre como contraseña, elige otra...");
        esMessages.put(Login_Password_UnsafePassword, prefix + prefixLogin + ColorWarning + "La contraseña elegida no es segura, elige otra...");
        esMessages.put(Login_Password_OrbiddenCharactfers, prefix + prefixLogin + ColorError + "Tu contraseña contiene caracteres no permitidos.");
        esMessages.put(Login_Password_WrongLength, prefix + prefixLogin + ColorError + "¡Su contraseña es demasiado corta o demasiado larga! ¡Por favor, intente con otra!");
        esMessages.put(Login_Login_CommandUsage, prefix + prefixLogin + Colorinfo + "Uso: /login <contraseña>");
        esMessages.put(Login_Login_Success, prefix + prefixLogin + ColorSuccess + "¡Inició sesión correctamente!");
        esMessages.put(Login_Login_LoginRequest, prefix + prefixLogin + Colorinfo + "Por favor, inicie sesión con el comando: /login <contraseña>");
        esMessages.put(Login_Login_TimeoutError, prefix + prefixLogin + ColorError + "Se ha excedido el tiempo de espera para iniciar sesión, ha sido expulsado del servidor, ¡inténtelo de nuevo!");
        esMessages.put(Login_Error_UnregisteredUser, prefix + prefixLogin + ColorError + "¡Este usuario no está registrado!");
        esMessages.put(Login_Error_DeniedCommand, prefix + prefixLogin + ColorError + "¡Para poder usar este comando, debe estar autenticado!");
        esMessages.put(Login_Error_DeniedChat, prefix + prefixLogin + ColorError + "¡Para poder chatear, debe estar autenticado!");
        esMessages.put(Login_Error_NotLoggedIn, prefix + prefixLogin + ColorError + "¡No ha iniciado sesión!");
        esMessages.put(Login_Error_TempbanMaxLogins, prefix + prefixLogin + ColorError + "Se le ha prohibido temporalmente iniciar sesión debido a que no ha podido iniciar sesión demasiadas veces.");
        esMessages.put(Login_Error_MaxRegistration, prefix + prefixLogin + ColorError + "Ha superado el número máximo de registros");
        esMessages.put(Login_Error_NoRermission, prefix + prefixLogin + ColorError + "¡No tiene permiso para realizar esta acción!");
        esMessages.put(Login_Error_UnexpectedError, prefix + prefixLogin + ColorError + "¡Se produjo un error inesperado, póngase en contacto con un administrador!");
        esMessages.put(Login_Error_ErrorKickForVip, prefix + prefixLogin + ColorError + "¡Un jugador VIP se ha unido al servidor cuando estaba lleno!");
        esMessages.put(Login_Error_LoggedIn, prefix + prefixLogin + ColorError + "¡Ya has iniciado sesión!");
        esMessages.put(Login_Error_KickUnresolvedHostname, prefix + prefixLogin + ColorError + "¡Se produjo un error: nombre de host del jugador sin resolver!");
        //teams
        esMessages.put(Teams_InvalidArg, prefix + prefixTeam + ColorError + "Argumentos no válidos, ayuda");
        esMessages.put(Teams_InTeam, prefix + prefixTeam + ColorError + "Debes estar en un equipo para hacer eso");
        esMessages.put(Teams_InternalError, prefix + prefixTeam + ColorError + "Algo salió mal al ejecutar ese comando, por favor repórtalo a los propietarios de tu servidor");
        esMessages.put(Teams_NotInTeam, prefix + prefixTeam + ColorError + "Debes abandonar tu equipo antes de hacer eso");
        esMessages.put(Teams_NeedOwner, prefix + prefixTeam + ColorError + "Debes ser el propietario del equipo para hacer eso");
        esMessages.put(Teams_NeedAdmin, prefix + prefixTeam + ColorError + "Debes ser administrador o propietario del equipo para hacer eso");
        esMessages.put(Teams_NeedPlayer, prefix + prefixTeam + ColorError + "Debes ser jugador para hacer eso");
        esMessages.put(Teams_NoPlayer, prefix + prefixTeam + ColorError + "No se encontró el jugador especificado");
        esMessages.put(Teams_NotTeam, prefix + prefixTeam + ColorError + "Ese equipo no existe, intenta " + ColorLink + "/team create <name>");
        esMessages.put(Teams_NeedSameTeam, prefix + prefixTeam + ColorError + "No estás en el mismo equipo que esa persona");
        esMessages.put(Teams_NoPerm, prefix + prefixTeam + ColorError + "No tienes permiso para hacer eso");
        esMessages.put(Teams_BannedChar, prefix + prefixTeam + ColorError + "El personaje que intentaste usar está prohibido");
        esMessages.put(Teams_NoTeam, prefix + prefixTeam + ColorError + "Ese equipo no existe");
        esMessages.put(Teams_Loading, prefix + prefixTeam + Colorinfo + "Cargando");
        esMessages.put(Teams_Create_Exists, prefix + prefixTeam + ColorError + "Ese equipo ya existe");
        esMessages.put(Teams_Create_Success, prefix + prefixTeam + ColorSuccess + "Tu equipo ha sido creado");
        esMessages.put(Teams_Create_Banned, prefix + prefixTeam + ColorError + "Ese nombre de equipo está prohibido");
        esMessages.put(Teams_Create_maxLength, prefix + prefixTeam + ColorError + "El nombre del equipo es demasiado largo");
        esMessages.put(Teams_Leave_Success, prefix + prefixTeam + ColorSuccess + "Has abandonado el equipo");
        esMessages.put(Teams_Leave_LastOwner, prefix + prefixTeam + Colorinfo + "Eres el único propietario dentro del equipo. Promueve a otra persona o usa '/team disband' para desmantelar el equipo");
        esMessages.put(Teams_Announce_disband, prefix + prefixTeam + Colorinfo + "El equipo [0] ha sido desmantelado");
        esMessages.put(Teams_Description_Success, prefix + prefixTeam + ColorSuccess + "¡Has iniciado sesión correctamente!");
        esMessages.put(Teams_Description_View, prefix + prefixTeam + Colorinfo + "Descripción del equipo: [0]");
        esMessages.put(Teams_Description_NoDesc, prefix + prefixTeam + ColorError + "No se ha establecido ninguna descripción del equipo");
        esMessages.put(Teams_Description_NoPerm, prefix + prefixTeam + ColorError + "No tiene permiso para editar la descripción");
        esMessages.put(Teams_Name_Success, prefix + prefixTeam + ColorSuccess + "Ha cambiado el nombre del equipo");
        esMessages.put(Teams_Name_View, prefix + prefixTeam + Colorinfo + "Nombre del equipo: [0]");
        esMessages.put(Teams_Name_Exists, prefix + prefixTeam + ColorSuccess + "Ese equipo ya existe");
        esMessages.put(Teams_Name_NoPerm, prefix + prefixTeam + ColorError + "No tienes permiso para cambiar el nombre de tu equipo");
        esMessages.put(Teams_Name_Invite, prefix + prefixTeam + ColorSuccess + "Has invitado a [0]");
        esMessages.put(Teams_Invite_Success, prefix + prefixTeam + ColorSuccess + "Ese jugador ha sido invitado");
        esMessages.put(Teams_Invite_Invite, prefix + prefixTeam + Colorinfo + "Has sido invitado a unirte al equipo [0] haz " + ColorLink + "/team join <NameTeam> " + Colorinfo + " para unirte al equipo");
        esMessages.put(Teams_Invite_Hover, prefix + prefixTeam + Colorinfo + "Haz clic para unirte");
        esMessages.put(Teams_Invite_InTeam, prefix + prefixTeam + ColorError + "Ese jugador ya está en un equipo");
        esMessages.put(Teams_Invite_Banned, prefix + prefixTeam + ColorError + "Ese jugador está baneado de tu equipo");
        esMessages.put(Teams_Invite_Full, prefix + prefixTeam + ColorError + "Tu equipo tiene el tamaño máximo, expulsa a alguien antes de invitar a más personas");
        esMessages.put(Teams_Invite_Expired, prefix + prefixTeam + ColorError + "La invitación de [0] ha expirado");
        esMessages.put(Teams_join_Success, prefix + prefixTeam + ColorSuccess + "Te has unido a ese equipo");
        esMessages.put(Teams_join_Notify, prefix + prefixTeam + ColorSuccess + "Bienvenido [0] al equipo!");
        esMessages.put(Teams_join_NotInvited, prefix + prefixTeam + Colorinfo + "No has sido invitado a ese equipo");
        esMessages.put(Teams_join_Banned, prefix + prefixTeam + ColorError + "Estás prohibido en ese equipo");
        esMessages.put(Teams_join_Full, prefix + prefixTeam + ColorError + "Ese equipo está lleno");
        esMessages.put(Teams_Open_SuccessOpen, prefix + prefixTeam + ColorSuccess + "Tu equipo ahora está abierto para todos");
        esMessages.put(Teams_Open_SuccessClose, prefix + prefixTeam + ColorSuccess + "Tu equipo ahora es solo para invitados");
        esMessages.put(Teams_Title_Change, prefix + prefixTeam + ColorSuccess + "Su título ha sido cambiado a [0]");
        esMessages.put(Teams_Title_Remove, prefix + prefixTeam + ColorSuccess + "Su título ha sido eliminado");
        esMessages.put(Teams_Title_Success, prefix + prefixTeam + ColorSuccess + "Ese título ha sido cambiado");
        esMessages.put(Teams_Title_TooLong, prefix + prefixTeam + ColorError + "Su título es demasiado largo");
        esMessages.put(Teams_Title_NoFormat, prefix + prefixTeam + ColorError + "No tiene permiso para dar formato a los títulos");
        esMessages.put(Teams_Title_NoColor, prefix + prefixTeam + ColorError + "No tiene permiso para dar color a los títulos");
        esMessages.put(Teams_Title_Reset, prefix + prefixTeam + ColorSuccess + "Tu título ha sido restablecido");
        esMessages.put(Teams_Title_NoPerm, prefix + prefixTeam + ColorError + "No tienes permiso para cambiar el título de tu equipo");
        esMessages.put(Teams_Kick_Success, prefix + prefixTeam + ColorSuccess + "Ese jugador ha sido expulsado");
        esMessages.put(Teams_Kick_Notify, prefix + prefixTeam + Colorinfo + "&6Te han expulsado del equipo [0]");
        esMessages.put(Teams_Kick_NoPerm, prefix + prefixTeam + ColorError + "&6No tienes permiso para expulsar a esa persona");
        esMessages.put(Teams_Ban_Success, prefix + prefixTeam + Colorinfo + "Ese jugador ha sido expulsado");
        esMessages.put(Teams_Ban_Notify, prefix + prefixTeam + ColorError + "Te han baneado del equipo [0]");
        esMessages.put(Teams_Ban_NoPerm, prefix + prefixTeam + ColorError + "No tienes permiso para banear a esa persona");
        esMessages.put(Teams_Ban_Already, prefix + prefixTeam + ColorError + "Ese jugador ya está baneado");
        esMessages.put(Teams_Unban_Success, prefix + prefixTeam + ColorSuccess + "Las has desbaneado");
        esMessages.put(Teams_Unban_Notify, prefix + prefixTeam + Colorinfo + "Te han desbaneado del equipo [0]");
        esMessages.put(Teams_Unban_NoPerm, prefix + prefixTeam + ColorError + "No tienes permiso para desbanear a esa persona");
        esMessages.put(Teams_Unban_Not, prefix + prefixTeam + ColorError + "Ese jugador no está baneado");
        esMessages.put(Teams_Promote_Success, prefix + prefixTeam + ColorSuccess + "Ese jugador ha sido promovido");
        esMessages.put(Teams_Promote_NoPerm, prefix + prefixTeam + ColorError + "No tienes permisos para promover a esa persona");
        esMessages.put(Teams_Promote_Max,prefix + prefixTeam + ColorError + "Esa persona ya ha sido promovida al máximo nivel!");
        esMessages.put(Teams_Promote_Notify, prefix + prefixTeam + ColorSuccess + "Has sido promovido");
        esMessages.put(Teams_Promote_MaxAdmins, prefix + prefixTeam + ColorError + "Tu equipo ya tiene la cantidad máxima de administradores, degrada a alguien o sube de nivel a tu equipo");
        esMessages.put(Teams_Promote_MaxOwners, prefix + prefixTeam + ColorError + "Tu equipo ya tiene la cantidad máxima de propietarios, degrada a alguien o sube de nivel a tu equipo");
        esMessages.put(Teams_Demote_Success, prefix + prefixTeam + ColorSuccess + "Lo has degradado");
        esMessages.put(Teams_Demote_NoPerm, prefix + prefixTeam + ColorError + "No tienes permisos para degradar a esa persona");
        esMessages.put(Teams_Demote_Min, prefix + prefixTeam + ColorError + "Esa persona ya tiene el rango más bajo");
        esMessages.put(Teams_Demote_Notify, prefix + prefixTeam + ColorSuccess + "Te han degradado");
        esMessages.put(Teams_Demote_LastOwner, prefix + prefixTeam + Colorinfo + "No puedes degradar al propietario final, primero promueve a otra persona");
        esMessages.put(Teams_Demote_MaxAdmins, prefix + prefixTeam + ColorError + "Tu equipo ya tiene la cantidad máxima de administradores, elimina un administrador o sube de nivel a tu equipo");
        esMessages.put(Teams_Chat_Enabled, prefix + prefixTeam + Colorinfo + "Tus mensajes ahora van al chat del equipo");
        esMessages.put(Teams_Chat_Disabled, prefix + prefixTeam + Colorinfo + "Tus mensajes ahora van al chat global");
        esMessages.put(Teams_Color_Success, prefix + prefixTeam + ColorSuccess + "Se ha cambiado el color de tu equipo");
        esMessages.put(Teams_Color_Fail, prefix + prefixTeam + ColorError + "Ese no es un color de chat reconocido");
        esMessages.put(Teams_Color_Banned, prefix + prefixTeam + ColorError + "Ese código de color está prohibido");
        esMessages.put(Teams_Ally_Already, prefix + prefixTeam + ColorError + "Ya son aliados");
        esMessages.put(Teams_Ally_Success, prefix + prefixTeam + ColorSuccess + "Tus equipos ahora son aliados");
        esMessages.put(Teams_Ally_Ally, prefix + prefixTeam + Colorinfo + "Tu equipo ahora está aliado con [0]");
        esMessages.put(Teams_Ally_Requested, prefix + prefixTeam + ColorError + "Se ha enviado una solicitud de aliado a ese equipo");
        esMessages.put(Teams_Ally_Request, prefix + prefixTeam + ColorSuccess + "[0] ha enviado una solicitud de aliado, usa " + ColorLink + " /team ally <team> " + Colorinfo + " &6para aceptar");
        esMessages.put(Teams_Ally_Self, prefix + prefixTeam + ColorError + "No puedes aliarte con tu propio equipo");
        esMessages.put(Teams_Ally_From, prefix + prefixTeam + ColorError + "Tienes solicitudes de aliado de: [0]");
        esMessages.put(Teams_Ally_NoRequests, prefix + prefixTeam + ColorError + "No tienes ninguna solicitud de aliado");
        esMessages.put(Teams_Ally_OnJoin, prefix + prefixTeam + ColorSuccess + "Tienes nuevas solicitudes de aliado, haz clic en " + ColorLink + "/aliado del equipo " + Colorinfo + "para verlas");
        esMessages.put(Teams_Ally_Limit, prefix + prefixTeam + ColorError + "Se alcanzó el límite de aliados");
        esMessages.put(Teams_Ally_AlreadyRequest, prefix + prefixTeam + ColorError + "Ya has solicitado ser aliado de ese equipo");
        esMessages.put(Teams_AllyChat_Disabled, prefix + prefixTeam + Colorinfo + "Tus mensajes ya no van al chat de aliados");
        esMessages.put(Teams_AllyChat_Enabled, prefix + prefixTeam + Colorinfo + "Tus mensajes ahora van al chat de aliados");
        esMessages.put(Teams_Neutral_Self, prefix + prefixTeam + ColorSuccess + "Ese es tu propio equipo");
        esMessages.put(Teams_Neutral_RequestRemove, prefix + prefixTeam + ColorSuccess + "Esa solicitud de aliado ha sido eliminada");
        esMessages.put(Teams_Neutral_Reject, prefix + prefixTeam + ColorSuccess + "Tu solicitud de aliado con [0] ha sido rechazada");
        esMessages.put(Teams_Neutral_NotAlly, prefix + prefixTeam + Colorinfo + "No eres aliado de ese equipo");
        esMessages.put(Teams_Neutral_Success, prefix + prefixTeam + ColorSuccess + "Ya no estás aliado con ese equipo");
        esMessages.put(Teams_Neutral_Remove, prefix + prefixTeam + ColorSuccess + "Ya no estás aliado con [0]");
        esMessages.put(Teams_SetOwner_Use, prefix + prefixTeam + Colorinfo + "No puedes promover a ese jugador, usa " + ColorLink + "/team setowner <player> " + Colorinfo + "para promover a ese jugador a propietario");
        esMessages.put(Teams_SetOwner_success, prefix + prefixTeam + ColorSuccess + "Ese jugador ahora es propietario");
        esMessages.put(Teams_SetOwner_notify, prefix + prefixTeam + Colorinfo + "Ahora eres propietario de tu equipo");
        esMessages.put(Teams_SetOwner_max, prefix + prefixTeam + Colorinfo + "Ese jugador ya es propietario");
        esMessages.put(Teams_Pvp_Enabled, prefix + prefixTeam + Colorinfo + "El Pvp ha sido habilitado para tu equipo");
        esMessages.put(Teams_Pvp_Disabled, prefix + prefixTeam + Colorinfo + "El Pvp ha sido deshabilitado para tu equipo");
        esMessages.put(Teams_Tag_Banned, prefix + prefixTeam + ColorError + "Esa etiqueta está prohibida");
        esMessages.put(Teams_Tag_Success, prefix + prefixTeam + ColorSuccess + "Tu etiqueta ha sido cambiada exitosamente");
        esMessages.put(Teams_Tag_MaxLength, prefix + prefixTeam + ColorError + "Tu etiqueta es demasiado larga");
        esMessages.put(Teams_Tag_NoPerm, prefix + prefixTeam + ColorError + "No tienes permiso para cambiar la etiqueta del equipo");
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
        esMessages.put(Inventory_TimeLimit_M1mas,"&21M+");
        esMessages.put(Inventory_TimeLimit_S1mas,"&21S+");
        esMessages.put(Inventory_TimeLimit_S1menos,"&c1S-");
        esMessages.put(Inventory_TimeLimit_M1menos,"&c1M-");
        //otros
        esMessages.put(Others_AddVIPTitle, Coloritem + "Ahora Eres VIP");
        esMessages.put(Others_Chat_Cooldown, prefix + ColorError + "No puedes enviar mensajes durante &b%time%");
        esMessages.put(Others_Chat_BanWord, prefix + ColorError + "Tu mensaje contiene una palabra prohibida");
        esMessages.put(Others_Chat_Active, prefix + Colorinfo + "La moderación en el chat fue activada");
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
        esMessages.put(Kick_NotAuthenticated,prefixKick  + Colorinfo + "Se detecto una discrepancia en su authentication, por favor vuele a iniciar sesión");
        esMessages.put(Kick_Cheat,prefixKick + Colorinfo + "No se permite hacks en el box pvp, pero en el FFA si");

        esMessages.put(Ban_BotsChat,prefixBanAuto + "uso de bot o de uso indebido de multicuentas. Por seguridad tu ip fue agregada a la lista negras de los bot");
        esMessages.put(Ban_AutoMessages, prefixBanAuto + "uso de mensajes automatizado");
        esMessages.put(Ban_HacksBoxPvp, prefixBanAuto + "uso de hacks en el box pvp");
        esMessages.put(Ban_KickBoxPvp, prefixBanAuto + "por favor no uses hacks en el box pvp");

    }
}
