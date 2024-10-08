package xyz.xbcommun.Messages.Messages;

import net.md_5.bungee.api.ChatColor;

import java.util.HashMap;
import java.util.Map;

import static xyz.xbcommun.Messages.Messages.Messages.*;
import static xyz.xbcommun.Messages.MessageManager.*;

public record MessagesEN() {

    public static final Map<Messages, String> enMessages = new HashMap<>();

    public MessagesEN() {
        //Generico
        enMessages.put(Generic_NotOp,prefix + ColorError + "You don't have permission to perform this command!");
        enMessages.put(Generic_NotAllowed,prefix + ColorError + "it's not allowed");
        enMessages.put(Generic_InArea,prefix + ColorError + "It is not allowed to use here");
        enMessages.put(Generic_HoverExecute,"Execute this command");
        enMessages.put(Generic_OnlyPlayers, prefixConsole + ColorError +  "only be executed by players");
        //Vote y prefix
        enMessages.put(Vote_Voted,prefix + ColorSuccess + "Thank you for voting");
        enMessages.put(Vote_NotBoxPvp, prefix + Colorinfo + "You have to enter the pvp box to receive the rewards");
        //Eventos
        enMessages.put(Event_leave,Colorinfo + " left the game!");
        enMessages.put(Event_join,Colorinfo + " joined the game!");
        //Muertes
        enMessages.put(Died_Died1,prefixDied + Colorplayer + "%player% " + Colorinfo + "was kill by " + Colorplayer + "%killer% " + Colorinfo + "with: %item%");
        enMessages.put(Died_Died2,prefixDied + Colorplayer + "%player% " + Colorinfo + "was explode by " + Colorplayer + "%killer% " + Colorinfo + "with: %item%");
        enMessages.put(Died_Died3,prefixDied + Colorplayer + "%player% " + Colorinfo + "was suffocation by " + Colorplayer + "BLock ");
        enMessages.put(Died_Died4,prefixDied + Colorplayer + "%player% " + Colorinfo + "committed suicide");
        enMessages.put(Died_Died5,prefixDied + Colorplayer + "%player% " + Colorinfo + "died from the fall");
        enMessages.put(Died_DiedDuel,prefixDied + Colorinfo + "Winner Is " + Colorplayer + "%killer%" + Colorinfo + " And Looser Is " + Colorplayer + "%player% ");
        //kit
        enMessages.put(Kit_KitNotExist,prefix + ColorError + "The Kit Does Not Exist");
        enMessages.put(Kit_SaveError,prefix + ColorError + "You must specify the name of the kits and/or item");
        enMessages.put(Kit_SaveErrorPunto,prefix + ColorError + "It can't have dot");
        enMessages.put(Kit_DelError,prefix + ColorError + "You must specify the name of the kit");
        enMessages.put(Kit_Removed,prefix + ColorSuccess + "the kit &r%namekit%"+ ColorSuccess +" was removed successfully");
        enMessages.put(Kit_RemovedWaring,prefix + ColorWarning + "An attempt was made to delete a kit that does not exist");
        enMessages.put(Kit_Load,prefix + ColorSuccess + "the kit &r%namekit%"+ ColorSuccess +" was loaded successfully");
        enMessages.put(Kit_Save,prefix + ColorSuccess + "the kit &r%namekit%"+ ColorSuccess +" was saved successfully");
        enMessages.put(Kit_Favorite,prefix + ColorSuccess + "favorite kit was saved successfully");
        enMessages.put(Kit_LoadError,prefix + ColorError + "There was a problem loading the kit &r%nameKit%" + ColorError + " try to log out and back in if the situation repeats contact the owner");
        enMessages.put(Kit_LoadWaring,prefix + ColorWarning + "Item '%item%' could not be loaded and will be skipped when loading");
        enMessages.put(Kit_FavoriteWaring,prefix + ColorWarning + "The kit being saved apparently does not exist but is saved successfully. Please note that it is case sensitive as well as minecraft color codes.");
        //Combate Log
        enMessages.put(CombateLog_OnCombat,prefix + ColorError + "It is not allowed to use in combat wait "+ "&b" + "%time%" + ColorError + " seconds to use it");
        enMessages.put(CombateLog_InSafeZone,"&4Leave Safe Zone In %time% Seconds");
        //Las Solisitudes del Duel
        enMessages.put(RequestDuel_Inv,prefix + ColorSuccess + "Guest list saved successfully");
        enMessages.put(RequestDuel_MissingPlayers,prefix + Colorinfo + "Missing players ");
        enMessages.put(RequestDuel_SelfAccepted,prefix + ColorError + "Cannot accept or deny your own request");
        enMessages.put(RequestDuel_OnDuel,prefix + ColorError + "You are already in a duel");
        enMessages.put(RequestDuel_SendSelf,prefix + ColorError + "Can't send yourself");
        enMessages.put(RequestDuel_RequestExpired,prefix + ColorError + "The request has expired");
        enMessages.put(RequestDuel_WorldType,prefix + ColorError + "Select world type 'bedrock','flat_Bedrcok' o 'flat_world'");
        enMessages.put(RequestDuel_DuelError,prefix + ColorError + "You have to specify the username and specify the type world (optional)");
        enMessages.put(RequestDuel_PlayerOffTarget,prefix + ColorError + "The player is not online");
        enMessages.put(RequestDuel_PlayerOffSender,prefix + ColorError + "The player who sent you the request is no longer online");
        enMessages.put(RequestDuel_NotRequests,prefix + ColorError + "You have no pending requests");
        enMessages.put(RequestDuel_DenyYour,prefix + Colorinfo + "Has denied your request");
        enMessages.put(RequestDuel_AcceptedYourRequest,prefix + ColorSuccess + "Your request has been accepted");
        enMessages.put(RequestDuel_AcceptedRequest,prefix +  ColorSuccess + "You have accepted a request");
        enMessages.put(RequestDuel_FullSites,prefix + ColorWarning + "The duel worlds are full, wait for it to end.");
        enMessages.put(RequestDuel_SendRequest1,Colorinfo + "A request has been sent to you. Use ");
        enMessages.put(RequestDuel_SendRequest2,Colorinfo + " To accept it or deny it with ");
        enMessages.put(RequestDuel_SendRequest,prefix + ColorSuccess + "You have sent a request to" + Colorplayer + " %player%");
        enMessages.put(RequestDuel_DenyYou,prefix + ColorSuccess + "You have denied the request");
        enMessages.put(RequestDuel_HoverYes,"Yes");
        enMessages.put(RequestDuel_HoverDeny,"Deny");
        enMessages.put(RequestDuel_ArenaDuel,Colorinfo + "World: " + Colorplayer);
        enMessages.put(RequestDuel_SenderPlayer,Colorinfo + "Send by: " + Colorplayer);
        enMessages.put(RequestDuel_KitSelect, Colorinfo + "Kit:&r ");
        enMessages.put(RequestDuel_TimeLimit,Colorinfo + "Time limit: " + Colorplayer);
        enMessages.put(RequestDuel_KitFavorite, Colorplayer + "Your favorite kit");
        enMessages.put(DuelWorld_EndTimeDuel,prefix + Colorinfo + "Time is up");
        enMessages.put(DuelWorld_ListPlayersEmpty,prefix + ColorError + "You haven't invited anyone");
        //En el mundo del duelo
        enMessages.put(DuelWorld_EndCombat,prefix + ColorWarning + "End Combat, player disconnected");
        enMessages.put(DuelWorld_IncorrectLoc,prefix + ColorError + "You are not allowed to be on the roof");
        enMessages.put(DuelWorld_DuelStarted1,prefixDuel + Colorinfo + "Duel started: " + Colorplayer + "%player1% " + Colorinfo + "VS " + Colorplayer + "%player2% " + Colorinfo + "In: " + Colorplayer + "%world%");
        enMessages.put(DuelWorld_DuelStarted2,prefixDuel + Colorinfo + "Duel started: " + Colorplayer + "%player% " + Colorinfo + "In: " + Colorplayer + "%world% " + Colorinfo + "With these players: ");
        enMessages.put(DuelWorld_Go,"&2&lGO!!");
        enMessages.put(DuelWorld_HoverDuel, "See the last duel");
        //Para modo espetador
        enMessages.put(SpectatorMode_SpectatorError,prefix + ColorError + "You have not specified the name of the world.");
        enMessages.put(SpectatorMode_NotCombatWorld,prefix + ColorError + "There is no combat in that world");
        enMessages.put(SpectatorMode_NotFoundWorld,prefix + ColorError + "The world does not exist");
        enMessages.put(SpectatorMode_SpectatorSuccess,Colorinfo + "To be in survival game mode use: ");
        //Recompensa
        enMessages.put(Reward_Daily, Coloritem + "Daily reward");
        enMessages.put(Reward_Weekly, Coloritem + "Weekly Reward");
        enMessages.put(Reward_Monthly, Coloritem + "Monthly Reward");
        enMessages.put(Reward_RewardNotYet,prefix + ColorError + "You can't take the reward yet");
        enMessages.put(Reward_GiveDaily, prefix + ColorSuccess + "You have claimed the daily reward");
        enMessages.put(Reward_GiveWeekly, prefix + ColorSuccess + "You have claimed the weekly reward");
        enMessages.put(Reward_GiveMonthly, prefix + ColorSuccess +  "You have claimed the monthly reward");
        enMessages.put(Reward_CrateNotPermission, prefix + ColorError + "You are not VIP you can get it here " + LinkTienda);
        enMessages.put(Reward_ClaimReward, prefix + ColorSuccess + "I have claimed a reward");
        //tienda
        enMessages.put(Reward_StoreLink, prefix + Colorinfo + "You can buy here!!");
        enMessages.put(Reward_BuysTitel,ColorSuccess + "Thanks for the purchase!!");
        enMessages.put(Reward_BuysTitelLower,ColorSuccess + "I hope you enjoy it: " + Colorplayer + "%compra%");
        enMessages.put(Reward_BuyGeneric,prefix + "\n" + " \n" + Colorplayer + "%player% " + Colorinfo + "has Buying " + Colorplayer + "%compra%\n \n"
                + Colorinfo + "You can also buy!!\n" + "In the store: " + LinkTienda + "\n&r" + " \n");
        //login
        enMessages.put(Login_Registration_RegisterRequest, prefix + prefixLogin + Colorinfo + "Please, register to the server with the command: /register <password> <ConfirmPassword>");
        enMessages.put(Login_Registration_CommandUsage, prefix + prefixLogin +Colorinfo + "Usage: /register <password> <ConfirmPassword>");
        enMessages.put(Login_Registration_Success, prefix + prefixLogin +ColorSuccess + "Successfully registered!");
        enMessages.put(Login_Registration_disabled, prefix + prefixLogin +Colorinfo + "In-game registration is disabled!");
        enMessages.put(Login_Registration_NameTaken, prefix + prefixLogin +ColorError + "You already have registered this username!");
        enMessages.put(Login_Password_MatchError, prefix + prefixLogin +ColorError + "Passwords didn''t match, check them again!");
        enMessages.put(Login_Password_NameInPassword, prefix + prefixLogin +ColorWarning + "You can''t use your name as password, please choose another one...");
        enMessages.put(Login_Password_UnsafePassword, prefix + prefixLogin +ColorWarning + "The chosen password isn''t safe, please choose another one...");
        enMessages.put(Login_Password_OrbiddenCharactfers, prefixLogin +prefix + ColorError + "Your password contains illegal characters.");
        enMessages.put(Login_Password_WrongLength, prefix + prefixLogin +ColorError + "Your password is too short or too long! Please try with another one!");
        enMessages.put(Login_Login_CommandUsage, prefix + prefixLogin +Colorinfo + "Usage: /login <password>");
        enMessages.put(Login_Login_Success, prefix + prefixLogin +ColorSuccess + "Successfully logged in!");
        enMessages.put(Login_Login_LoginRequest, prefix + prefixLogin +Colorinfo + "Please, login with the command: /login <password>");
        enMessages.put(Login_Login_TimeoutError, prefix + prefixLogin +ColorError + "Login timeout exceeded, you have been kicked from the server, please try again!");
        enMessages.put(Login_Error_UnregisteredUser, prefix + prefixLogin +ColorError + "This user isn''t registered!");
        enMessages.put(Login_Error_DeniedCommand, prefix + prefixLogin +ColorError + "In order to use this command you must be authenticated!");
        enMessages.put(Login_Error_DeniedChat, prefix + prefixLogin +ColorError + "In order to chat you must be authenticated!");
        enMessages.put(Login_Error_NotLoggedIn, prefix + prefixLogin +ColorError + "You''re not logged in!");
        enMessages.put(Login_Error_TempbanMaxLogins, prefix + prefixLogin +ColorError + "You have been temporarily banned for failing to log in too many times.");
        enMessages.put(Login_Error_MaxRegistration, prefix + prefixLogin +ColorError + "You have exceeded the maximum number of registrations");
        enMessages.put(Login_Error_NoRermission, prefix + prefixLogin +ColorError + "You don''t have the permission to perform this action!");
        enMessages.put(Login_Error_UnexpectedError, prefix + prefixLogin +ColorError + "An unexpected error occurred, please contact an administrator!");
        enMessages.put(Login_Error_ErrorKickForVip, prefix + prefixLogin +ColorError + "A VIP player has joined the server when it was full!");
        enMessages.put(Login_Error_LoggedIn, prefix + prefixLogin +ColorError + "You''re already logged in!");
        enMessages.put(Login_Error_KickUnresolvedHostname, prefix + prefixLogin +ColorError + "An error occurred: unresolved player hostname!");
        //Teams
        enMessages.put(Teams_InvalidArg, prefix + prefixTeam + ColorError + "Invalid Arguments, help");
        enMessages.put(Teams_InTeam, prefix + prefixTeam + ColorError + "You must be in a team to do that");
        enMessages.put(Teams_InternalError, prefix + prefixTeam + ColorError + "Something went wrong while executing that command, please report it to your server owners");
        enMessages.put(Teams_NotInTeam, prefix + prefixTeam + ColorError + "You must leave your team before doing that");
        enMessages.put(Teams_NeedOwner, prefix + prefixTeam + ColorError + "You must be the owner of the team to do that");
        enMessages.put(Teams_NeedAdmin, prefix + prefixTeam + ColorError + "You must be admin or owner of the team to do that");
        enMessages.put(Teams_NeedPlayer, prefix + prefixTeam + ColorError + "You must be a player to do that");
        enMessages.put(Teams_NoPlayer, prefix + prefixTeam + ColorError + "Specified player not found");
        enMessages.put(Teams_NotTeam, prefix + prefixTeam + ColorError + "That team does not exist try " + ColorLink + "/team create <name>");
        enMessages.put(Teams_NeedSameTeam, prefix + prefixTeam + ColorError + "You are not in the same team as that person");
        enMessages.put(Teams_NoPerm, prefix + prefixTeam + ColorError + "You do not have permission to do that");
        enMessages.put(Teams_BannedChar, prefix + prefixTeam + ColorError + "Character you tried to use is banned");
        enMessages.put(Teams_NoTeam, prefix + prefixTeam + ColorError + "That team does not exist");
        enMessages.put(Teams_Loading, prefix + prefixTeam + Colorinfo + "Loading");
        enMessages.put(Teams_Create_Exists, prefix + prefixTeam + ColorError + "That team already exists");
        enMessages.put(Teams_Create_Success, prefix + prefixTeam + ColorSuccess + "Your team has been created");
        enMessages.put(Teams_Create_Banned, prefix + prefixTeam + ColorError + "That team name is banned");
        enMessages.put(Teams_Create_maxLength, prefix + prefixTeam + ColorError + "That team name is too long");
        enMessages.put(Teams_Leave_Success, prefix + prefixTeam + ColorSuccess + "You have left the team");
        enMessages.put(Teams_Leave_LastOwner, prefix + prefixTeam + Colorinfo + "You are the only owner rank within the team, Either promote someone else or use '/team disband' to disband command. the team");
        enMessages.put(Teams_Announce_disband, prefix + prefixTeam + Colorinfo + "Team [0] has been disbanded");
        enMessages.put(Teams_Description_Success, prefix + prefixTeam + ColorSuccess + "You have successfully logged in!");
        enMessages.put(Teams_Description_View, prefix + prefixTeam + Colorinfo + "Team Description: [0]");
        enMessages.put(Teams_Description_NoDesc, prefix + prefixTeam + ColorError + "No team description set");
        enMessages.put(Teams_Description_NoPerm, prefix + prefixTeam + ColorError + "You do not have permission to edit the description");
        enMessages.put(Teams_Name_Success, prefix + prefixTeam + ColorSuccess + "You have changed the team name");
        enMessages.put(Teams_Name_View, prefix + prefixTeam + Colorinfo + "Team name: [0]");
        enMessages.put(Teams_Name_Exists, prefix + prefixTeam + ColorSuccess + "That team already exists");
        enMessages.put(Teams_Name_NoPerm, prefix + prefixTeam + ColorError + "You do not have permission to change your team name");
        enMessages.put(Teams_Name_Invite, prefix + prefixTeam + ColorSuccess + "You have invited [0]");
        enMessages.put(Teams_Invite_Success, prefix + prefixTeam + ColorSuccess + "That player has been invited");
        enMessages.put(Teams_Invite_Invite, prefix + prefixTeam + Colorinfo + "You have been invited to join team [0] do " + ColorLink + "/team join <NameTeam> " + Colorinfo + " to join the team");
        enMessages.put(Teams_Invite_Hover, prefix + prefixTeam + Colorinfo + "Click to join");
        enMessages.put(Teams_Invite_InTeam, prefix + prefixTeam + ColorError + "That player is already in a team");
        enMessages.put(Teams_Invite_Banned, prefix + prefixTeam + ColorError + "That player is banned from your team");
        enMessages.put(Teams_Invite_Full, prefix + prefixTeam + ColorError + "Your team is maximum size, kick someone out before inviting more people");
        enMessages.put(Teams_Invite_Expired, prefix + prefixTeam + ColorError + "The invite from [0] has expired");
        enMessages.put(Teams_join_Success, prefix + prefixTeam + ColorSuccess + "You have joined that team");
        enMessages.put(Teams_join_Notify, prefix + prefixTeam + ColorSuccess + "Welcome [0] to the team!");
        enMessages.put(Teams_join_NotInvited, prefix + prefixTeam + Colorinfo + "You have not been invited to that team");
        enMessages.put(Teams_join_Banned, prefix + prefixTeam + ColorError + "You are banned from that team");
        enMessages.put(Teams_join_Full, prefix + prefixTeam + ColorError + "That team is full");
        enMessages.put(Teams_Open_SuccessOpen, prefix + prefixTeam + ColorSuccess + "Your team now open to everyone");
        enMessages.put(Teams_Open_SuccessClose, prefix + prefixTeam + ColorSuccess + "Your team is now invite only'");
        enMessages.put(Teams_Title_Change, prefix + prefixTeam + ColorSuccess + "Your title has been changed to [0]");
        enMessages.put(Teams_Title_Remove, prefix + prefixTeam + ColorSuccess + "Your title has been removed");
        enMessages.put(Teams_Title_Success, prefix + prefixTeam + ColorSuccess + "That title has been changed");
        enMessages.put(Teams_Title_TooLong, prefix + prefixTeam + ColorError + "Your title is too long");
        enMessages.put(Teams_Title_NoFormat, prefix + prefixTeam + ColorError + "You do not have permission to format titles");
        enMessages.put(Teams_Title_NoColor, prefix + prefixTeam + ColorError + "You do not have permission to color titles");
        enMessages.put(Teams_Title_Reset, prefix + prefixTeam + ColorSuccess + "Your title has been reset");
        enMessages.put(Teams_Title_NoPerm, prefix + prefixTeam + ColorError + "You do not have permission to change your team title");
        enMessages.put(Teams_Kick_Success, prefix + prefixTeam + ColorSuccess + "That player has been kicked");
        enMessages.put(Teams_Kick_Notify, prefix + prefixTeam + Colorinfo + "&6You have been kicked from team [0]");
        enMessages.put(Teams_Kick_NoPerm, prefix + prefixTeam + ColorError + "&6You do not have permission to kick that person");
        enMessages.put(Teams_Ban_Success, prefix + prefixTeam + Colorinfo + "That player has been banned");
        enMessages.put(Teams_Ban_Notify, prefix + prefixTeam + ColorError + "You have been banned from team [0]");
        enMessages.put(Teams_Ban_NoPerm, prefix + prefixTeam + ColorError + "You do not have permission to ban that person");
        enMessages.put(Teams_Ban_Already, prefix + prefixTeam + ColorError + "That player is already banned");
        enMessages.put(Teams_Unban_Success, prefix + prefixTeam + ColorSuccess + "you have unbanned them");
        enMessages.put(Teams_Unban_Notify, prefix + prefixTeam + Colorinfo + "You have been unbanned from team [0]");
        enMessages.put(Teams_Unban_NoPerm, prefix + prefixTeam + ColorError + "You do not have permission to unban that person");
        enMessages.put(Teams_Unban_Not, prefix + prefixTeam + ColorError + "That player is not banned");
        enMessages.put(Teams_Promote_Success, prefix + prefixTeam + ColorSuccess + "That player has been promoted");
        enMessages.put(Teams_Promote_NoPerm, prefix + prefixTeam + ColorError + "You do not have permissions to promote that person");
        enMessages.put(Teams_Promote_Max,prefix + prefixTeam + ColorError + "That person is already promoted to the max!");
        enMessages.put(Teams_Promote_Notify, prefix + prefixTeam + ColorSuccess + "You have been promoted");
        enMessages.put(Teams_Promote_MaxAdmins, prefix + prefixTeam + ColorError + "Your team already has the maximum number of admins, demote someone or level your team up");
        enMessages.put(Teams_Promote_MaxOwners, prefix + prefixTeam + ColorError + "Your team already has the maximum number of owners, demote someone or level your team up");
        enMessages.put(Teams_Demote_Success, prefix + prefixTeam + ColorSuccess + "you have degraded it");
        enMessages.put(Teams_Demote_NoPerm, prefix + prefixTeam + ColorError + "You do not have permissions to demote that person");
        enMessages.put(Teams_Demote_Min, prefix + prefixTeam + ColorError + "That person is already the lowest rank");
        enMessages.put(Teams_Demote_Notify, prefix + prefixTeam + ColorSuccess + "You have been demoted");
        enMessages.put(Teams_Demote_LastOwner, prefix + prefixTeam + Colorinfo + "You cannot demote the final owner, promote someone else first");
        enMessages.put(Teams_Demote_MaxAdmins, prefix + prefixTeam + ColorError + "Your team already has the maximum number of admins, remove and admin or level your team up");
        enMessages.put(Teams_Chat_Enabled, prefix + prefixTeam + Colorinfo + "Your messages now go to the team chat");
        enMessages.put(Teams_Chat_Disabled, prefix + prefixTeam + Colorinfo + "Your messages now go to the global chat");
        enMessages.put(Teams_Color_Success, prefix + prefixTeam + ColorSuccess + "Your team color has been changed");
        enMessages.put(Teams_Color_Fail, prefix + prefixTeam + ColorError + "That is not a recognised chat color");
        enMessages.put(Teams_Color_Banned, prefix + prefixTeam + ColorError + "That color code is banned");
        enMessages.put(Teams_Ally_Already, prefix + prefixTeam + ColorError + "You are already allies");
        enMessages.put(Teams_Ally_Success, prefix + prefixTeam + ColorSuccess + "Your teams are now allies");
        enMessages.put(Teams_Ally_Ally, prefix + prefixTeam + Colorinfo + "Your team is now allied with [0]");
        enMessages.put(Teams_Ally_Requested, prefix + prefixTeam + ColorError + "An ally request has been sent to that team");
        enMessages.put(Teams_Ally_Request, prefix + prefixTeam + ColorSuccess + "[0] has sent an ally request, use " + ColorLink + " /team ally <team> " + Colorinfo + " &6to accept");
        enMessages.put(Teams_Ally_Self, prefix + prefixTeam + ColorError + "You cannot ally your own team");
        enMessages.put(Teams_Ally_From, prefix + prefixTeam + ColorError + "You have ally requests from: [0]");
        enMessages.put(Teams_Ally_NoRequests, prefix + prefixTeam + ColorError + "You do not have any ally requests");
        enMessages.put(Teams_Ally_OnJoin, prefix + prefixTeam + ColorSuccess + "You have new ally requests do " + ColorLink + "/team ally " + Colorinfo + "to view them");
        enMessages.put(Teams_Ally_Limit, prefix + prefixTeam + ColorError + "The limit on allies has been reached");
        enMessages.put(Teams_Ally_AlreadyRequest, prefix + prefixTeam + ColorError + "You have already requested to be allies with that team");
        enMessages.put(Teams_AllyChat_Disabled, prefix + prefixTeam + Colorinfo + "Your messages are no longer going to the ally chat");
        enMessages.put(Teams_AllyChat_Enabled, prefix + prefixTeam + Colorinfo + "Your messages are now going to the ally chat");
        enMessages.put(Teams_Neutral_Self, prefix + prefixTeam + ColorSuccess + "That is your own team");
        enMessages.put(Teams_Neutral_RequestRemove, prefix + prefixTeam + ColorSuccess + "That ally request has been removed");
        enMessages.put(Teams_Neutral_Reject, prefix + prefixTeam + ColorSuccess + "Your ally request with [0] has been rejected");
        enMessages.put(Teams_Neutral_NotAlly, prefix + prefixTeam + Colorinfo + "You are not allied with that team");
        enMessages.put(Teams_Neutral_Success, prefix + prefixTeam + ColorSuccess + "You are no longer allied with that team");
        enMessages.put(Teams_Neutral_Remove, prefix + prefixTeam + ColorSuccess + "You are no longer allied with [0]");
        enMessages.put(Teams_SetOwner_Use, prefix + prefixTeam + Colorinfo + "You cannot promote that player, use " + ColorLink + "/team setowner <player> " + Colorinfo + "to promote that player to owner");
        enMessages.put(Teams_SetOwner_success, prefix + prefixTeam + ColorSuccess + "That player is now owner");
        enMessages.put(Teams_SetOwner_notify, prefix + prefixTeam + Colorinfo + "You are now owner of your team");
        enMessages.put(Teams_SetOwner_max, prefix + prefixTeam + Colorinfo + "That player is already owner");
        enMessages.put(Teams_Pvp_Enabled, prefix + prefixTeam + Colorinfo + "Pvp has been enabled for your team");
        enMessages.put(Teams_Pvp_Disabled, prefix + prefixTeam + Colorinfo + "Pvp has been disabled for your team");
        enMessages.put(Teams_Tag_Banned, prefix + prefixTeam + ColorError + "That tag is banned");
        enMessages.put(Teams_Tag_Success, prefix + prefixTeam + ColorSuccess + "Your tag has successfully changed");
        enMessages.put(Teams_Tag_MaxLength, prefix + prefixTeam + ColorError + "Your tag is too long");
        enMessages.put(Teams_Tag_NoPerm, prefix + prefixTeam + ColorError + "You do not have permission to change the team tag");
        //Invetarios
        enMessages.put(Inventory_Menu_MenuDuel_Lore,"This menu is used to customize duels between one or more people to have a " +
                "private combat\n \n " + ColorWarning + "⚠ can not be opened in the pvp Box ⚠");
        enMessages.put(Inventory_Menu_MenuKit_Lore,"In this menu you can see and load the kits you want. You can see the global kits or your own " +
                "\n \n" + ColorWarning + "⚠ can not be opened in the pvp Box ⚠");
        enMessages.put(Inventory_Menu_MenuHelp_Lore,"Here you can see extra information about the server ");
        enMessages.put(Inventory_Menu_Title,ColorWarning + "<#61CAFD>xBxT<#B378CB>:<#FDC661>Menu<#FF7302>");
        enMessages.put(Inventory_MenuKit_InvGlobal,"&a&lGlobal Kits");
        enMessages.put(Inventory_MenuKit_InvCustom,"&e&lCustom Kits");
        enMessages.put(Inventory_MenuKit_TpLobby,"&b&lReturn to the lobby");
        enMessages.put(Inventory_MenuKit_TpCreatorKits,"&b&lCreate your kit");
        enMessages.put(Inventory_PreviewKit_Title,"&b&lPreview");
        enMessages.put(Inventory_InvItemFrame_Title,"&b&ltake what you want");
        enMessages.put(Inventory_Generic_Exit,"&c&lExit");
        enMessages.put(Inventory_PreviewKit_Load_Title,"&a&lLoad Kit");
        enMessages.put(Inventory_PreviewKit_Load_lore,"Name: ");
        enMessages.put(Inventory_Generic_Previous,"Previous page");
        enMessages.put(Inventory_Generic_Next,"Next page");
        enMessages.put(Inventory_KitList_InvClear,"&b&l Clear Inventory");
        enMessages.put(Inventory_KitList_Title,"&bKits List&r - &6&lClick Right&r&6 To see the preview");
        enMessages.put(Inventory_KitMenu_Title,"&b&lMenu Kit");
        enMessages.put(Inventory_KitListBedrock_Title, "&bKits List");
        enMessages.put(Inventory_PreviewOn, "&bPreview:&2&l Enabled");
        enMessages.put(Inventory_PreviewOff, "&bPreview:&c&l Disabled");
        enMessages.put(Inventory_MenuDuel_Title, "&b&lMenu Duels");
        enMessages.put(Inventory_MenuDuel_SendQuest, "&2&lSend Request");
        enMessages.put(Inventory_MenuDuel_InvPlayers_title, Coloritem + "&lList of invited players");
        enMessages.put(Inventory_MenuDuel_SelectKit, Coloritem + "&lThe selected kit");
        enMessages.put(Inventory_MenuDuel_TimeLimit_Title, Coloritem + "&lTime limit");
        enMessages.put(Inventory_MenuDuel_SelectWorld_Title, Coloritem + "&lSelected world");
        enMessages.put(Inventory_MenuDuel_InvPlayers_Lore, Colorinfo + "Selected players");
        enMessages.put(Inventory_MenuDuel_InvPlayersEmpty_Lore, Colorinfo + "Invite your friends!");
        enMessages.put(Inventory_MenuDuel_SelectKit_Lore, Colorinfo + "The selected kit is: &e");
        enMessages.put(Inventory_MenuDuel_SelectKitEmpty_Lore, Colorinfo + "The selected kit is the favorite");
        enMessages.put(Inventory_MenuDuel_TimeLimit_Lore, Colorinfo + "Active time limit");
        enMessages.put(Inventory_MenuDuel_TimeLimitDisabled_Lore, Colorinfo + "No time limit");
        enMessages.put(Inventory_MenuDuel_SelectWorld_Lore, Colorplayer + "Selected: ");
        enMessages.put(Inventory_MenuDuel_TimeLimit_On,Coloritem + "Time limit activated");
        enMessages.put(Inventory_MenuDuel_TimeLimit_Off,Coloritem + "Time limit disabled");
        enMessages.put(Inventory_MenuDuel_TimeLimit_ItemLore, Colorinfo + "Maximum time: " + Colorplayer);
        enMessages.put(Inventory_MenuKit_SeleMode_SelectKitFavorite,Coloritem  + "&lSelect favorite kit");
        enMessages.put(Inventory_MenuDuel_TimeLimit_M1mas,"&21M+");
        enMessages.put(Inventory_MenuDuel_TimeLimit_S1mas,"&21S+");
        enMessages.put(Inventory_MenuDuel_TimeLimit_S1menos,"&c1S-");
        enMessages.put(Inventory_MenuDuel_TimeLimit_M1menos,"&c1M-");
        enMessages.put(Inventory_MenuHelp_Title,"&b&lMenu Help");
        enMessages.put(Inventory_MenuHelp_Command,"<#FCD05C>Commands<#FFE7A7>");
        enMessages.put(Inventory_MenuHelp_Command_Lore,"You can see what each server command does\n \n" + ColorWarning + "⚠ limited translation into english ⚠");
        enMessages.put(Inventory_MenuHelp_Item,"<#19fbff>Items Box PvP<#2a7c7d>");
        enMessages.put(Inventory_MenuHelp_Item_Lore,"You can see what each server command does");
        enMessages.put(Inventory_MenuHelp_Item_EspecialItem_FERMENTED_SPIDER_EYE_Lore, "When you carry it in your secondary hand you do 75% more damage but " +
                "for each hit you give your hunger goes down");
        enMessages.put(Inventory_MenuHelp_Item_EspecialItem_CHARCOAL_Lore, "When you carry it in your secondary hand and if you are less than 16 health (8 hearts) you deal with 3 " +
                "for 10 seconds");
        enMessages.put(Inventory_MenuHelp_Item_EspecialItem_SCUTE_Lore, "When you carry it in your secondary hand you do 125% more damage only when it has absorption if not" +
                " you do -25% damage");
        enMessages.put(Inventory_MenuHelp_Item_EspecialItem_FLINT_Lore, "When you carry this in your offhand there is a 30% chance that you will be blinded for 10 seconds");
        enMessages.put(Inventory_MenuHelp_Item_EspecialItem_NETHERITE_SCRAP_Lore, "When you carry this in your offhand you gain slow 3 for 30 seconds and damage resistance " +
                " 3 for 30 seconds");
        enMessages.put(Inventory_MenuHelp_Item_ArmorItem_Title, "<#19fbff>Armor/Tools<#2a7c7d>");
        enMessages.put(Inventory_MenuHelp_Item_ArmorItem_Lore,
                "&l" + Coloritem + "Helmet: &7Increases your generation speed up to tier 10, from there it does not increase further.\n \n" +
                "&l" + Coloritem + "Elytra: &7Increases your maximum life for each tier increases by one heart.\n \n" +
                "&l" + Coloritem + "Pants: &7Increases your overall resistance to damage and resistance to penetration by this and increases by one level for each tier.\n \n" +
                "&l" + Coloritem + "Boots: &7Increases your resistance to pushback and explosions for each tier increases by one enchantment level.\n \n" +
                "&l" + Coloritem + "Sword: &7Increases the enchantment level of the blade for each tier increases 2 levels of enchantment.\n \n" +
                "&l" + Coloritem + "Pickaxe: &7for each tier increases the chance of getting a duplicate item when mining increases 3% for each tier.");
        enMessages.put(Inventory_MenuHelp_Item_CoinP_Title, "<#19fbff>Principal Coin<#2a7c7d>");
        enMessages.put(Inventory_MenuHelp_Item_CoinP_Lore, "The main coin is used to buy from the villagers on the surface and improve your equipment. To have " +
                "this coin you have to exchange it with the converter villagers who exchange the gold ore, iron ore... for the main coin with its respective value. The " +
                "main coins have different values, fragment, normal and compact. You can compact or fragment the coin as many times as you want with " +
                "the same villagers");
        enMessages.put(Inventory_MenuHelp_Item_CoinS_Title, "<#19fbff>Ore Coin<#2a7c7d>");
        enMessages.put(Inventory_MenuHelp_Item_CoinS_Lore, "This coin is obtained by mining materials such as gold, iron, copper ... these are found " +
                "around the center of the Box PvP. According to their value, these mines take longer to regenerate. The one that takes the longest is the special mine, which has the " +
                "highest value of all and is located right in the center of the Box PvP. With this coin you can trade with the villagers on the surface to have the " +
                "main currency");
        enMessages.put(Inventory_MenuHelp_Item_CoinT_Title, "<#19fbff>Tier Coin<#2a7c7d>");
        enMessages.put(Inventory_MenuHelp_Item_CoinT_Lore, "The tier currency is only used to upgrade your armor, pickaxe, and sword. It is obtained in each tower where " +
                "each mine of each tier is located. With this you trade with a villager who is in the same mine to compact it to improve your equipment");
        enMessages.put(Inventory_MenuHelp_Rules,"<#FC5C5C>Rules<#FFDD91>");
        enMessages.put(Inventory_MenuHelp_Rules_Lore,"You can know the rules of this server");
        enMessages.put(Inventory_MenuHelp_Info,"<#19fbff>info<#2a7c7d>");
        enMessages.put(Inventory_MenuHelp_Info_Lore,"You can find out general information about the server\n \n" + ColorWarning + "⚠ limited translation into english ⚠");
        enMessages.put(Inventory_MenuHelp_Info_xBxTCore_lore, "The xBxT Core plugin is a private plugin in charge of the main operation of the server" +
                " it takes care of almost everything on the server such as translations, inventories, bans and many other things. The reason for its existence " +
                "is to have absolute control of the server and not be limited to third-party plugins even though there are plugins that are necessary but " +
                "these have an api that handles xBxT Core and thanks to 8b8tCore for their project organization system.");
        //otros
        enMessages.put(Others_AddVIPTitle, Coloritem + "You Are Now VIP");
        enMessages.put(Others_Chat_Cooldown, prefix + ColorError + "You cannot send messages for &b%time%");
        enMessages.put(Others_Chat_BanWord, prefix + ColorError + "Your message contains a forbidden word");
        enMessages.put(Others_Chat_Active, prefix + Colorinfo + "Chat moderation has been activated");
        enMessages.put(Others_TimeBossBar, Colorinfo + "Time remaining: " + Colorplayer);
        enMessages.put(Others_WarningGetGuestPlayers, ColorWarning + "One of the players you invited is not connected or does not exist");
        enMessages.put(Others_IvnPlayers1, Colorinfo + "Use /inv");
        enMessages.put(Others_IvnPlayers2, Colorinfo + "Write the names of the players.");
        enMessages.put(Others_Alone,prefix + Colorinfo + "If you are alone on the server, join our discord to find someone to play with.\nDiscord:" + ColorLink + "https://discord.gg/QYBwEFvnsG");
        enMessages.put(Others_message1Minute,prefix + Colorinfo + "1 minute Cleaner");
        enMessages.put(Others_message5Seconds,prefix + Colorinfo + "5 seconds Cleaner");
        enMessages.put(Others_messageStarCleaner,prefix + Colorinfo + "Star Cleaner");
        enMessages.put(Others_CleanerExecuted, prefix + Colorinfo + "Cleaner executed by owner");
        enMessages.put(Others_Donate,prefix + Colorinfo + "I would appreciate your contribution to this project" + ColorLink + "https://www.paypal.com/paypalme/xBxTpvp\n&4&l!!&r&4 All donations are &l'DONATIONS'&r&4 nothing is expected in return and it is of one's own free will");
        enMessages.put(Others_Vote,prefix + Colorinfo + "I would appreciate your vote on some page:");
        enMessages.put(Others_NewPlayer,"&e Welcome To " + ChatColor.of("#61CAFD") + "&lx" + ChatColor.of("#7CAFEC") + "&lB" + ChatColor.of("#9893DC")
                + "&lx" + ChatColor.of("#B378CB") + "&lT" + ChatColor.of("#FDC661") + "&l." + ChatColor.of("#FEAA41") + "&lx" +
                ChatColor.of("#FE8F22") + "&ly" + ChatColor.of("#FF7302") + "&lz" + Colorinfo + "\nJoin our discord:" + ColorLink + "https://discord.gg/QYBwEFvnsG");
        enMessages.put(Others_OpNotAuthized,Colorinfo + "&lXD&r" + Colorinfo + " biggest of all. Seriously? Go hack other servers, because mine is NO\nAnd by the way, I won't ban you because I want to know how you got it. I mean, do your thing to get op or creative.");
        //Kick
        enMessages.put(Kick_SpamCommand,prefixKick + Colorinfo + "Kicked out for command spam");
        enMessages.put(Kick_AlreadyConnected,prefixKick  + Colorinfo + "This user is already logged in");
        enMessages.put(Kick_NotAuthenticated,prefixKick  + Colorinfo + "A discrepancy was detected in your authentication, please log in again.");
        enMessages.put(Kick_Cheat,prefixKick + Colorinfo + "Hacks are not allowed in the PVP box, but in FFA they are.");

        enMessages.put(Ban_BotsChat,prefixBanAuto + "use of bot or misuse of multiple accounts. For security reasons your IP has been added to the bot blacklist");
        enMessages.put(Ban_AutoMessages, prefixBanAuto + "use of automated messages");
        enMessages.put(Ban_HacksBoxPvp, prefixBanAuto + "use of hacks in the pvp box");
        enMessages.put(Ban_KickBoxPvp, prefixBanAuto + "please do not use hacks in the pvp box");
        enMessages.put(Ban_Dupe, prefixBanAuto + "You have two items with the same UUID, that is, you have duplicated");
        //rule
        //rules
        enMessages.put(Rule_Title, "Rule %#%");
        enMessages.put(Rule_0, "Bullying and harassment between my users is not allowed. If you are a victim of these acts, I will open a ticket on the discord. The victim decides the severity of the ban. " +
                "If it turns out that the victim gives inconsistent evidence, they will be permanently banned from the chat.");
        enMessages.put(Rule_1, "The use of bots and improper use of multiple accounts is not allowed. It is only allowed if the baritone is being used and " +
                "You can have multiple accounts, but there must be a justification for it.");
        enMessages.put(Rule_2, "The use of dupes or other exploits is not allowed. You will be automatically banned. If you have knowledge of dupes or possible dupes, I will open a ticket on the discord server.");
        enMessages.put(Rule_3, "The use of automated messages on the server or external links is not allowed. If you break them, you will be automatically banned.");
        enMessages.put(Rule_4, "The use of hacks in the pvp box is not allowed. This only applies to the pvp box. For the FFA, it is allowed.");
        enMessages.put(Rule_5, "It is not allowed to enter other users' accounts. If you get in through a bug, exploit, or bypass, I will open a ticket on discord " +
                "If not, all your behavior will be analyzed to solve it and you will be permanently banned.");
        enMessages.put(Rule_6, "It is not allowed to have OP or Creative on the server. If you get it, I will open a ticket on discord. If not, all your behavior will be analyzed to solve it and you will not be banned.");
    }
}
