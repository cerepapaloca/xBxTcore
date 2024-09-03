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
        enMessages.put(NotOp,prefix + ColorError + "You don't have permission to perform this command!");
        enMessages.put(NotAllowed,prefix + ColorError + "it's not allowed");
        enMessages.put(InArea,prefix + ColorError + "It is not allowed to use here");
        enMessages.put(HoverExecute,"Execute this command");
        //Vote y prefix
        enMessages.put(NotVote,ColorError + "To have access to this command you have to vote for this server with the command: ");
        enMessages.put(TooLengthName,prefix + ColorError + "Very long name. Maximum characters 10");
        enMessages.put(WordNotAllowed,prefix + ColorError + "That word is not allowed");
        enMessages.put(PrefixSuccess,prefix + ColorSuccess + "The prefix has been put in your name");
        enMessages.put(Voted,Colorinfo + "Thank you for voting, you can now use the command: ");
        enMessages.put(PrefixClear,prefix + Colorinfo + "The prefix has been cleared");
        //Eventos
        enMessages.put(leave,Colorinfo + " left the game!");
        enMessages.put(join,Colorinfo + " joined the game!");
        //Muertes
        enMessages.put(Died1,prefixDied + Colorplayer + "%player% " + Colorinfo + "was kill by " + Colorplayer + "%killer% " + Colorinfo + "with: %item%");
        enMessages.put(Died2,prefixDied + Colorplayer + "%player% " + Colorinfo + "was explode by " + Colorplayer + "%killer% " + Colorinfo + "with: %item%");
        enMessages.put(Died3,prefixDied + Colorplayer + "%player% " + Colorinfo + "was suffocation by " + Colorplayer + "BLock ");
        enMessages.put(Died4,prefixDied + Colorplayer + "%player% " + Colorinfo + "committed suicide");
        enMessages.put(Died5,prefixDied + Colorplayer + "%player% " + Colorinfo + "died from the fall");
        enMessages.put(DiedDuel,prefixDied + Colorinfo + "Winner Is " + Colorplayer + "%killer%" + Colorinfo + " And Looser Is " + Colorplayer + "%player% ");
        //kit
        enMessages.put(KitNotExist,prefix + ColorError + "The Kit Does Not Exist");
        enMessages.put(SaveError,prefix + ColorError + "You must specify the name of the kits and/or item");
        enMessages.put(SaveErrorPunto,prefix + ColorError + "It can't have dot");
        enMessages.put(DelError,prefix + ColorError + "You must specify the name of the kit");
        enMessages.put(Removed,prefix + ColorSuccess + "the kit &r%namekit%"+ ColorSuccess +" was removed successfully");
        enMessages.put(RemovedWaring,prefix + ColorWarning + "An attempt was made to delete a kit that does not exist");
        enMessages.put(Load,prefix + ColorSuccess + "the kit &r%namekit%"+ ColorSuccess +" was loaded successfully");
        enMessages.put(Save,prefix + ColorSuccess + "the kit &r%namekit%"+ ColorSuccess +" was saved successfully");
        enMessages.put(Favorite,prefix + ColorSuccess + "favorite kit was saved successfully");
        enMessages.put(LoadError,prefix + ColorError + "There was a problem loading the kit &r%nameKit%" + ColorError + " try to log out and back in if the situation repeats contact the owner");
        enMessages.put(LoadWaring,prefix + ColorWarning + "Item '%item%' could not be loaded and will be skipped when loading");
        enMessages.put(FavoriteWaring,prefix + ColorWarning + "The kit being saved apparently does not exist but is saved successfully. Please note that it is case sensitive as well as minecraft color codes.");
        //Combate Log
        enMessages.put(OnCombat,prefix + ColorError + "It is not allowed to use in combat wait "+ "&b" + "%time%" + ColorError + " seconds to use it");
        enMessages.put(InSafeZone,"&4Leave Safe Zone In %time% Seconds");
        //Las Solisitudes del Duel
        enMessages.put(Inv,prefix + ColorSuccess + "Guest list saved successfully");
        enMessages.put(MissingPlayers,prefix + Colorinfo + "Missing players ");
        enMessages.put(SelfAccepted,prefix + ColorError + "Cannot accept or deny your own request");
        enMessages.put(OnDuel,prefix + ColorError + "You are already in a duel");
        enMessages.put(SendSelf,prefix + ColorError + "Can't send yourself");
        enMessages.put(RequestExpired,prefix + ColorError + "The request has expired");
        enMessages.put(WorldType,prefix + ColorError + "Select world type 'bedrock','flat_Bedrcok' o 'flat_world'");
        enMessages.put(DuelError,prefix + ColorError + "You have to specify the username and specify the type world (optional)");
        enMessages.put(PlayerOffTarget,prefix + ColorError + "The player is not online");
        enMessages.put(PlayerOffSender,prefix + ColorError + "The player who sent you the request is no longer online");
        enMessages.put(NotRequests,prefix + ColorError + "You have no pending requests");
        enMessages.put(DenyYour,prefix + Colorinfo + "Has denied your request");
        enMessages.put(AcceptedYourRequest,prefix + ColorSuccess + "Your request has been accepted");
        enMessages.put(AcceptedRequest,prefix +  ColorSuccess + "You have accepted a request");
        enMessages.put(FullSites,prefix + ColorWarning + "The duel worlds are full, wait for it to end.");
        enMessages.put(SendRequest1,Colorinfo + "A request has been sent to you. Use ");
        enMessages.put(SendRequest2,Colorinfo + " To accept it or deny it with ");
        enMessages.put(SendRequest,prefix + ColorSuccess + "You have sent a request to" + Colorplayer + " %player%");
        enMessages.put(DenyYou ,prefix + ColorSuccess + "You have denied the request");
        enMessages.put(HoverYes,"Yes");
        enMessages.put(HoverDeny,"Deny");
        enMessages.put(ArenaDuel,Colorinfo + "World: " + Colorplayer);
        enMessages.put(SenderPlayer,Colorinfo + "Send by: " + Colorplayer);
        enMessages.put(KitSelect, Colorinfo + "Kit:&r ");
        enMessages.put(TimeLimit,Colorinfo + "Time limit: " + Colorplayer);
        enMessages.put(KitFavorite, Colorplayer + "Your favorite kit");
        enMessages.put(EndTimeDuel,prefix + Colorinfo + "Time is up");
        enMessages.put(ListPlayersEmpty,prefix + ColorError + "You haven't invited anyone");
        //En el mundo del duelo
        enMessages.put(EndCombat,prefix + ColorWarning + "End Combat, player disconnected");
        enMessages.put(IncorrectLoc,prefix + ColorError + "You are not allowed to be on the roof");
        enMessages.put(DuelStarted1,prefixDuel + Colorinfo + "Duel started: " + Colorplayer + "%player1% " + Colorinfo + "VS " + Colorplayer + "%player2% " + Colorinfo + "In: " + Colorplayer + "%world%");
        enMessages.put(DuelStarted2,prefixDuel + Colorinfo + "Duel started: " + Colorplayer + "%player% " + Colorinfo + "In: " + Colorplayer + "%world% " + Colorinfo + "With these players: ");
        enMessages.put(Go,"&2&lGO!!");
        enMessages.put(HoverDuel, "See the last duel");
        //Para modo espetador
        enMessages.put(SpectatorError,prefix + ColorError + "You have not specified the name of the world.");
        enMessages.put(NotCombatWorld,prefix + ColorError + "There is no combat in that world");
        enMessages.put(NotFoundWorld,prefix + ColorError + "The world does not exist");
        enMessages.put(SpectatorSuccess,Colorinfo + "To be in survival game mode use: ");
        //Recompensa
        enMessages.put(Daily, Coloritem + "Daily reward");
        enMessages.put(Weekly, Coloritem + "Weekly Reward");
        enMessages.put(Monthly, Coloritem + "Monthly Reward");
        enMessages.put(RewardNotYet,prefix + ColorError + "You can't take the reward yet");
        enMessages.put(GiveDaily, prefix + ColorSuccess + "You have claimed the daily reward");
        enMessages.put(GiveWeekly, prefix + ColorSuccess + "You have claimed the weekly reward");
        enMessages.put(GiveMonthly, prefix + ColorSuccess +  "You have claimed the monthly reward");
        enMessages.put(CrateNotPermission, prefix + ColorError + "You are not VIP you can get it here " + ColorLink + "Under review");
        enMessages.put(ClaimReward, prefix + ColorSuccess + "I have claimed a reward");
        //tienda
        enMessages.put(StoreLink, prefix + Colorinfo + "You can buy here!!");
        enMessages.put(BuysTitel,ColorSuccess + "Thanks for the purchase!!");
        enMessages.put(BuysTitelLower,ColorSuccess + "I hope you enjoy it: " + Colorplayer + "%compra%");
        enMessages.put(BuyGeneric,prefix + "\n" + " \n" + Colorplayer + "%player% " + Colorinfo + "has Buying " + Colorplayer + "%compra%\n \n"
                + Colorinfo + "You can also buy!!\n" + "In the store: " + ColorLink + "aún en revision\n&r" + " \n");
        //login
        enMessages.put(Registration_RegisterRequest, prefix + Colorinfo + "Please, register to the server with the command: /register <password> <ConfirmPassword>");
        enMessages.put(Registration_CommandUsage, prefix + Colorinfo + "Usage: /register <password> <ConfirmPassword>");
        enMessages.put(Registration_Success, prefix + ColorSuccess + "Successfully registered!");
        enMessages.put(Registration_disabled, prefix + Colorinfo + "In-game registration is disabled!");
        enMessages.put(Registration_NameTaken, prefix + ColorError + "You already have registered this username!");
        enMessages.put(Password_MatchError, prefix + ColorError + "Passwords didn''t match, check them again!");
        enMessages.put(Password_NameInPassword, prefix + ColorWarning + "You can''t use your name as password, please choose another one...");
        enMessages.put(Password_UnsafePassword, prefix + ColorWarning + "The chosen password isn''t safe, please choose another one...");
        enMessages.put(Password_OrbiddenCharactfers, prefix + ColorError + "Your password contains illegal characters.");
        enMessages.put(Password_WrongLength, prefix + ColorError + "Your password is too short or too long! Please try with another one!");
        enMessages.put(Login_CommandUsage, prefix + Colorinfo + "Usage: /login <password>");
        enMessages.put(Login_Success, prefix + ColorSuccess + "Successfully logged in!");
        enMessages.put(Login_LoginRequest, prefix + Colorinfo + "Please, login with the command: /login <password>");
        enMessages.put(Login_TimeoutError, prefix + ColorError + "Login timeout exceeded, you have been kicked from the server, please try again!");
        enMessages.put(Error_UnregisteredUser, prefix + ColorError + "This user isn''t registered!");
        enMessages.put(Error_DeniedCommand, prefix + ColorError + "In order to use this command you must be authenticated!");
        enMessages.put(Error_DeniedChat, prefix + ColorError + "In order to chat you must be authenticated!");
        enMessages.put(Error_NotLoggedIn, prefix + ColorError + "You''re not logged in!");
        enMessages.put(Error_TempbanMaxLogins, prefix + ColorError + "You have been temporarily banned for failing to log in too many times.");
        enMessages.put(Error_MaxRegistration, prefix + ColorError + "You have exceeded the maximum number of registrations");
        enMessages.put(Error_NoRermission, prefix + ColorError + "You don''t have the permission to perform this action!");
        enMessages.put(Error_UnexpectedError, prefix + ColorError + "An unexpected error occurred, please contact an administrator!");
        enMessages.put(Error_ErrorKickForVip, prefix + ColorError + "A VIP player has joined the server when it was full!");
        enMessages.put(Error_LoggedIn, prefix + ColorError + "You''re already logged in!");
        enMessages.put(Error_KickUnresolvedHostname, prefix + ColorError + "An error occurred: unresolved player hostname!");
        //Invetarios
        enMessages.put(InvGlobal,"&a&lGlobal Kits");
        enMessages.put(InvCustom,"&e&lCustom Kits");
        enMessages.put(TpLobby,"&b&lReturn to the lobby");
        enMessages.put(TpCreatorKits,"&b&lCreate your kit");
        enMessages.put(InvPreview,"&bPreview");
        enMessages.put(InvItemFrame,"&b&ltake what you want");
        enMessages.put(InvExit,"&c&lExit");
        enMessages.put(InvLoad,"&a&lLoad Kit");
        enMessages.put(lore,"Name: ");
        enMessages.put(Previous,"Previous page");
        enMessages.put(Next,"Next page");
        enMessages.put(InvClear,"&b&l Clear Inventory");
        enMessages.put(CreteKit,"&b&lCreate your kit");
        enMessages.put(KitList,"&bKits List&r - &6&lClick Right&r&6 To see the preview");
        enMessages.put(KitMenu,"&bMenu");
        enMessages.put(KitListBedrock, "&bKits List");
        enMessages.put(PreviewOn, "&bPreview:&2&l Enabled");
        enMessages.put(PreviewOff, "&bPreview:&c&l Disabled");
        enMessages.put(DuelSendQuest, "&2&lSend Request");
        enMessages.put(DuelInvPlayers, Coloritem + "&lList of invited players");
        enMessages.put(DuelSelectKit, Coloritem + "&lThe selected kit");
        enMessages.put(DuelTimeLimit, Coloritem + "&lTime limit");
        enMessages.put(DuelSelectWorld, Coloritem + "&lSelected world");
        enMessages.put(DuelLoreInvPlayers, Colorinfo + "Selected players");
        enMessages.put(DuelLoreInvPlayersEmpty, Colorinfo + "Invite your friends!");
        enMessages.put(DuelLoreSelectKit, Colorinfo + "The selected kit is: &e");
        enMessages.put(DuelLoreSelectKitEmpty, Colorinfo + "The selected kit is the favorite");
        enMessages.put(DuelLoreTimeLimit, Colorinfo + "Active time limit");
        enMessages.put(DuelLoreTimeLimitDisabled, Colorinfo + "No time limit");
        enMessages.put(DuelLoreSelectWorld, Colorplayer + "Selected: ");
        enMessages.put(DuelTimeLimitOn,Coloritem + "Time limit activated");
        enMessages.put(DuelTimeLimitOff,Coloritem + "Time limit disabled");
        enMessages.put(DuelTimeLore, Colorinfo + "Maximum time: " + Colorplayer);
        enMessages.put(SelectKitFavorite,Coloritem  + "&lSelect favorite kit");
        enMessages.put(H1mas,"&21M+");
        enMessages.put(S1mas,"&21S+");
        enMessages.put(S1menos,"&c1S-");
        enMessages.put(H1menos,"&c1M-");
        //otros
        enMessages.put(TimeBossBar, Colorinfo + "Time remaining: " + Colorplayer);
        enMessages.put(WarningGetGuestPlayers, ColorWarning + "One of the players you invited is not connected or does not exist");
        enMessages.put(IvnPlayers1, Colorinfo + "Use /inv");
        enMessages.put(IvnPlayers2, Colorinfo + "Write the names of the players.");
        enMessages.put(Alone,prefix + Colorinfo + "If you are alone on the server, join our discord to find someone to play with.\nDiscord:" + ColorLink + "https://discord.gg/QYBwEFvnsG");
        enMessages.put(message1Minute,prefix + Colorinfo + "1 minute Cleaner");
        enMessages.put(message5Seconds,prefix + Colorinfo + "5 seconds Cleaner");
        enMessages.put(messageStarCleaner,prefix + Colorinfo + "Star Cleaner");
        enMessages.put(CleanerExecuted, prefix + Colorinfo + "Cleaner executed by owner");
        enMessages.put(Donate,prefix + Colorinfo + "I would appreciate your contribution to this project" + ColorLink + "https://www.paypal.com/paypalme/xBxTpvp\n&4&l!!&r&4 All donations are &l'DONATIONS'&r&4 nothing is expected in return and it is of one's own free will");
        enMessages.put(Vote,prefix + Colorinfo + "I would appreciate your vote on some page:");
        enMessages.put(NewPlayer,"&e Welcome To " + ChatColor.of("#61CAFD") + "&lx" + ChatColor.of("#7CAFEC") + "&lB" + ChatColor.of("#9893DC")
                + "&lx" + ChatColor.of("#B378CB") + "&lT" + ChatColor.of("#FDC661") + "&l." + ChatColor.of("#FEAA41") + "&lx" +
                ChatColor.of("#FE8F22") + "&ly" + ChatColor.of("#FF7302") + "&lz" + Colorinfo + "\nJoin our discord:" + ColorLink + "https://discord.gg/QYBwEFvnsG");
        enMessages.put(OpNotAuthized,Colorinfo + "&lXD&r" + Colorinfo + " biggest of all. Seriously? Go hack other servers, because mine is NO\nAnd by the way, I won't ban you because I want to know how you got it. I mean, do your thing to get op or creative.");
        //Kick
        enMessages.put(SpamCommand,prefixKick + Colorinfo + "Kicked out for command spam");
        enMessages.put(AlreadyConnected,prefixKick  + Colorinfo + "This user is already logged in");
    }
}
