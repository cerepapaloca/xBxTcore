package Plugin.Messages.Messages;

import Plugin.Messages.Enum.Messages;
import net.md_5.bungee.api.ChatColor;

import java.util.HashMap;
import java.util.Map;

import static Plugin.Messages.Enum.Messages.*;
import static Plugin.Messages.MessageManager.*;

public record MessagesEN() {

    public static final Map<Messages, String> enMessages = new HashMap<>();

    public MessagesEN() {
        //Generico
        enMessages.put(Generic_NotOp,prefix + ColorError + "You don't have permission to perform this command!");
        enMessages.put(Generic_NotAllowed,prefix + ColorError + "it's not allowed");
        enMessages.put(Generic_InArea,prefix + ColorError + "It is not allowed to use here");
        enMessages.put(Generic_HoverExecute,"Execute this command");
        //Vote y prefix
        enMessages.put(NotVote,ColorError + "To have access to this command you have to vote for this server with the command: ");
        enMessages.put(TooLengthName,prefix + ColorError + "Very long name. Maximum characters 10");
        enMessages.put(WordNotAllowed,prefix + ColorError + "That word is not allowed");
        enMessages.put(PrefixSuccess,prefix + ColorSuccess + "The prefix has been put in your name");
        enMessages.put(Voted,Colorinfo + "Thank you for voting, you can now use the command: ");
        enMessages.put(PrefixClear,prefix + Colorinfo + "The prefix has been cleared");
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
        enMessages.put(Reward_CrateNotPermission, prefix + ColorError + "You are not VIP you can get it here " + ColorLink + "Under review");
        enMessages.put(Reward_ClaimReward, prefix + ColorSuccess + "I have claimed a reward");
        //tienda
        enMessages.put(Reward_StoreLink, prefix + Colorinfo + "You can buy here!!");
        enMessages.put(Reward_BuysTitel,ColorSuccess + "Thanks for the purchase!!");
        enMessages.put(Reward_BuysTitelLower,ColorSuccess + "I hope you enjoy it: " + Colorplayer + "%compra%");
        enMessages.put(Reward_BuyGeneric,prefix + "\n" + " \n" + Colorplayer + "%player% " + Colorinfo + "has Buying " + Colorplayer + "%compra%\n \n"
                + Colorinfo + "You can also buy!!\n" + "In the store: " + ColorLink + "aún en revision\n&r" + " \n");
        //login
        enMessages.put(Login_Registration_RegisterRequest, prefix + Colorinfo + "Please, register to the server with the command: /register <password> <ConfirmPassword>");
        enMessages.put(Login_Registration_CommandUsage, prefix + Colorinfo + "Usage: /register <password> <ConfirmPassword>");
        enMessages.put(Login_Registration_Success, prefix + ColorSuccess + "Successfully registered!");
        enMessages.put(Login_Registration_disabled, prefix + Colorinfo + "In-game registration is disabled!");
        enMessages.put(Login_Registration_NameTaken, prefix + ColorError + "You already have registered this username!");
        enMessages.put(Login_Password_MatchError, prefix + ColorError + "Passwords didn''t match, check them again!");
        enMessages.put(Login_Password_NameInPassword, prefix + ColorWarning + "You can''t use your name as password, please choose another one...");
        enMessages.put(Login_Password_UnsafePassword, prefix + ColorWarning + "The chosen password isn''t safe, please choose another one...");
        enMessages.put(Login_Password_OrbiddenCharactfers, prefix + ColorError + "Your password contains illegal characters.");
        enMessages.put(Login_Password_WrongLength, prefix + ColorError + "Your password is too short or too long! Please try with another one!");
        enMessages.put(Login_Login_CommandUsage, prefix + Colorinfo + "Usage: /login <password>");
        enMessages.put(Login_Login_Success, prefix + ColorSuccess + "Successfully logged in!");
        enMessages.put(Login_Login_LoginRequest, prefix + Colorinfo + "Please, login with the command: /login <password>");
        enMessages.put(Login_Login_TimeoutError, prefix + ColorError + "Login timeout exceeded, you have been kicked from the server, please try again!");
        enMessages.put(Login_Error_UnregisteredUser, prefix + ColorError + "This user isn''t registered!");
        enMessages.put(Login_Error_DeniedCommand, prefix + ColorError + "In order to use this command you must be authenticated!");
        enMessages.put(Login_Error_DeniedChat, prefix + ColorError + "In order to chat you must be authenticated!");
        enMessages.put(Login_Error_NotLoggedIn, prefix + ColorError + "You''re not logged in!");
        enMessages.put(Login_Error_TempbanMaxLogins, prefix + ColorError + "You have been temporarily banned for failing to log in too many times.");
        enMessages.put(Login_Error_MaxRegistration, prefix + ColorError + "You have exceeded the maximum number of registrations");
        enMessages.put(Login_Error_NoRermission, prefix + ColorError + "You don''t have the permission to perform this action!");
        enMessages.put(Login_Error_UnexpectedError, prefix + ColorError + "An unexpected error occurred, please contact an administrator!");
        enMessages.put(Login_Error_ErrorKickForVip, prefix + ColorError + "A VIP player has joined the server when it was full!");
        enMessages.put(Login_Error_LoggedIn, prefix + ColorError + "You''re already logged in!");
        enMessages.put(Login_Error_KickUnresolvedHostname, prefix + ColorError + "An error occurred: unresolved player hostname!");
        //Invetarios
        enMessages.put(Inventory_InvGlobal,"&a&lGlobal Kits");
        enMessages.put(Inventory_InvCustom,"&e&lCustom Kits");
        enMessages.put(Inventory_TpLobby,"&b&lReturn to the lobby");
        enMessages.put(Inventory_TpCreatorKits,"&b&lCreate your kit");
        enMessages.put(Inventory_InvPreview,"&bPreview");
        enMessages.put(Inventory_InvItemFrame,"&b&ltake what you want");
        enMessages.put(Inventory_InvExit,"&c&lExit");
        enMessages.put(Inventory_InvLoad,"&a&lLoad Kit");
        enMessages.put(Inventory_lore,"Name: ");
        enMessages.put(Inventory_Previous,"Previous page");
        enMessages.put(Inventory_Next,"Next page");
        enMessages.put(Inventory_InvClear,"&b&l Clear Inventory");
        enMessages.put(Inventory_CreteKit,"&b&lCreate your kit");
        enMessages.put(Inventory_KitList,"&bKits List&r - &6&lClick Right&r&6 To see the preview");
        enMessages.put(Inventory_KitMenu,"&bMenu");
        enMessages.put(Inventory_KitListBedrock, "&bKits List");
        enMessages.put(Inventory_PreviewOn, "&bPreview:&2&l Enabled");
        enMessages.put(Inventory_PreviewOff, "&bPreview:&c&l Disabled");
        enMessages.put(Inventory_DuelSendQuest, "&2&lSend Request");
        enMessages.put(Inventory_DuelInvPlayers, Coloritem + "&lList of invited players");
        enMessages.put(Inventory_DuelSelectKit, Coloritem + "&lThe selected kit");
        enMessages.put(Inventory_DuelTimeLimit, Coloritem + "&lTime limit");
        enMessages.put(Inventory_DuelSelectWorld, Coloritem + "&lSelected world");
        enMessages.put(Inventory_DuelLoreInvPlayers, Colorinfo + "Selected players");
        enMessages.put(Inventory_DuelLoreInvPlayersEmpty, Colorinfo + "Invite your friends!");
        enMessages.put(Inventory_DuelLoreSelectKit, Colorinfo + "The selected kit is: &e");
        enMessages.put(Inventory_DuelLoreSelectKitEmpty, Colorinfo + "The selected kit is the favorite");
        enMessages.put(Inventory_DuelLoreTimeLimit, Colorinfo + "Active time limit");
        enMessages.put(Inventory_DuelLoreTimeLimitDisabled, Colorinfo + "No time limit");
        enMessages.put(Inventory_DuelLoreSelectWorld, Colorplayer + "Selected: ");
        enMessages.put(Inventory_DuelTimeLimitOn,Coloritem + "Time limit activated");
        enMessages.put(Inventory_DuelTimeLimitOff,Coloritem + "Time limit disabled");
        enMessages.put(Inventory_DuelTimeLore, Colorinfo + "Maximum time: " + Colorplayer);
        enMessages.put(Inventory_SelectKitFavorite,Coloritem  + "&lSelect favorite kit");
        enMessages.put(Inventory_H1mas,"&21M+");
        enMessages.put(Inventory_S1mas,"&21S+");
        enMessages.put(Inventory_S1menos,"&c1S-");
        enMessages.put(Inventory_H1menos,"&c1M-");
        //otros
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
    }
}
