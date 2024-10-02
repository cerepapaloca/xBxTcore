package Plugin.Messages.Messages;

public enum Messages {
    //Generico
    Generic_NotOp,
    Generic_NotAllowed,
    Generic_InArea,
    Generic_HoverExecute,
    Generic_OnlyPlayers,
    //Vote y prefix
    Vote_NotVote,
    Vote_TooLengthName,
    Vote_WordNotAllowed,
    Vote_PrefixSuccess,
    Vote_Voted,
    Vote_PrefixClear,
    Vote_NotBoxPvp,
    //Eventos
    Event_leave,
    Event_join,
    //Muertes
    Died_Died1,
    Died_Died2,
    Died_Died3,
    Died_Died4,
    Died_Died5,
    Died_DiedDuel,//No creo que lo vuelva incluir
    //kit
    Kit_KitNotExist,
    Kit_SaveError,
    Kit_SaveErrorPunto,
    Kit_DelError,
    Kit_Removed,
    Kit_RemovedWaring,
    Kit_Load,
    Kit_Save,
    Kit_Favorite,
    Kit_LoadError,
    Kit_LoadWaring,
    Kit_FavoriteWaring,
    //Combate Log
    CombateLog_OnCombat,
    CombateLog_InSafeZone,
    //Las Solisitudes del Duel
    RequestDuel_Inv,
    RequestDuel_MissingPlayers,
    RequestDuel_SelfAccepted,
    RequestDuel_OnDuel,
    RequestDuel_SendSelf,
    RequestDuel_RequestExpired,
    RequestDuel_WorldType,
    RequestDuel_DuelError,
    RequestDuel_PlayerOffTarget,
    RequestDuel_PlayerOffSender,
    RequestDuel_NotRequests,
    RequestDuel_DenyYour,
    RequestDuel_AcceptedYourRequest,
    RequestDuel_AcceptedRequest,
    RequestDuel_FullSites,
    RequestDuel_SendRequest1,
    RequestDuel_SendRequest2,
    RequestDuel_SendRequest,
    RequestDuel_HoverYes,
    RequestDuel_HoverDeny,
    RequestDuel_DenyYou,
    RequestDuel_ArenaDuel,
    RequestDuel_SenderPlayer,
    RequestDuel_KitSelect,
    RequestDuel_TimeLimit,
    RequestDuel_KitFavorite,
    //En el mundo del duelo
    DuelWorld_EndCombat,
    DuelWorld_IncorrectLoc,
    DuelWorld_DuelStarted1,
    DuelWorld_DuelStarted2,
    DuelWorld_Go,
    DuelWorld_HoverDuel,
    DuelWorld_EndTimeDuel,
    DuelWorld_ListPlayersEmpty,
    //Para modo espetador
    SpectatorMode_SpectatorError,
    SpectatorMode_NotCombatWorld,
    SpectatorMode_NotFoundWorld,
    SpectatorMode_SpectatorSuccess,
    //Recompensas
    Reward_Daily,
    Reward_Weekly,
    Reward_Monthly,
    Reward_RewardNotYet,
    Reward_GiveDaily,
    Reward_GiveWeekly,
    Reward_GiveMonthly,
    Reward_ClaimReward,
    Reward_CrateNotPermission,
    //tienda
    Reward_StoreLink,
    Reward_BuyGeneric,
    Reward_BuysTitel,
    Reward_BuysTitelLower,
    //login
    Login_Registration_RegisterRequest,
    Login_Registration_CommandUsage,
    Login_Registration_Success,
    Login_Registration_disabled,
    Login_Registration_NameTaken,
    Login_Password_MatchError,
    Login_Password_NameInPassword,
    Login_Password_UnsafePassword,
    Login_Password_OrbiddenCharactfers,
    Login_Password_WrongLength,
    Login_Login_CommandUsage,
    Login_Login_Success,
    Login_Login_LoginRequest,
    Login_Login_TimeoutError,
    Login_Error_UnregisteredUser,
    Login_Error_DeniedCommand,
    Login_Error_DeniedChat,
    Login_Error_NotLoggedIn,
    Login_Error_TempbanMaxLogins,
    Login_Error_MaxRegistration,
    Login_Error_NoRermission,
    Login_Error_UnexpectedError,
    Login_Error_ErrorKickForVip,
    Login_Error_LoggedIn,
    Login_Error_KickUnresolvedHostname,
    //teams
    Teams_InvalidArg,
    Teams_InTeam,
    Teams_InternalError,
    Teams_NotInTeam,
    Teams_NeedOwner,
    Teams_NeedAdmin,
    Teams_NeedPlayer,
    Teams_NoPlayer,
    Teams_NotTeam,
    Teams_NeedSameTeam,
    Teams_NoPerm,
    Teams_BannedChar,
    Teams_NoTeam,
    Teams_Loading,
    Teams_Create_Exists,
    Teams_Create_Success,
    Teams_Create_Banned,
    Teams_Create_maxLength,
    Teams_Leave_Success,
    Teams_Leave_LastOwner,
    Teams_Announce_disband,
    Teams_Description_Success,
    Teams_Description_View,
    Teams_Description_NoDesc,
    Teams_Description_NoPerm,
    Teams_Name_Success,
    Teams_Name_View,
    Teams_Name_Exists,
    Teams_Name_NoPerm,
    Teams_Name_Invite,
    Teams_Invite_Success,
    Teams_Invite_Invite,
    Teams_Invite_Hover,
    Teams_Invite_InTeam,
    Teams_Invite_Banned,
    Teams_Invite_Full,
    Teams_Invite_Expired,
    Teams_join_Success,
    Teams_join_Notify,
    Teams_join_NotInvited,
    Teams_join_Banned,
    Teams_join_Full,
    Teams_join_Open,
    Teams_Open_SuccessOpen,
    Teams_Open_SuccessClose,
    Teams_Title_Change,
    Teams_Title_Remove,
    Teams_Title_Success,
    Teams_Title_TooLong,
    Teams_Title_NoFormat,
    Teams_Title_NoColor,
    Teams_Title_Reset,
    Teams_Title_NoPerm,
    Teams_Kick_Success,
    Teams_Kick_Notify,
    Teams_Kick_Title,
    Teams_Kick_NoPerm,
    Teams_Ban_Success,
    Teams_Ban_Notify,
    Teams_Ban_NoPerm,
    Teams_Ban_Already,
    Teams_Unban_Success,
    Teams_Unban_Notify,
    Teams_Unban_NoPerm,
    Teams_Unban_Not,
    Teams_Promote_Success,
    Teams_Promote_NoPerm,
    Teams_Promote_Max,
    Teams_Promote_Notify,
    Teams_Promote_MaxAdmins,
    Teams_Promote_MaxOwners,
    Teams_Demote_Success,
    Teams_Demote_NoPerm,
    Teams_Demote_Min,
    Teams_Demote_Notify,
    Teams_Demote_LastOwner,
    Teams_Demote_MaxAdmins,
    Teams_Chat_Enabled,
    Teams_Chat_Disabled,
    Teams_Color_Success,
    Teams_Color_Fail,
    Teams_Color_Banned,
    Teams_Ally_Already,
    Teams_Ally_Success,
    Teams_Ally_Ally,
    Teams_Ally_Requested,
    Teams_Ally_Request,
    Teams_Ally_Self,
    Teams_Ally_From,
    Teams_Ally_NoRequests,
    Teams_Ally_OnJoin,
    Teams_Ally_Limit,
    Teams_Ally_AlreadyRequest,
    Teams_AllyChat_Disabled,
    Teams_AllyChat_Enabled,
    Teams_Neutral_Self,
    Teams_Neutral_RequestRemove,
    Teams_Neutral_Reject,
    Teams_Neutral_NotAlly,
    Teams_Neutral_Success,
    Teams_Neutral_Remove,
    Teams_SetOwner_Use,
    Teams_SetOwner_success,
    Teams_SetOwner_notify,
    Teams_SetOwner_max,
    Teams_Pvp_Enabled,
    Teams_Pvp_Disabled,
    Teams_Tag_Banned,
    Teams_Tag_Success,
    Teams_Tag_MaxLength,
    Teams_Tag_NoPerm,
    //Invetarios
    Inventory_InvGlobal,
    Inventory_InvCustom,
    Inventory_TpLobby,
    Inventory_TpCreatorKits,
    Inventory_InvPreview,
    Inventory_InvItemFrame,
    Inventory_InvExit,
    Inventory_InvLoad,
    Inventory_lore,
    Inventory_Previous,
    Inventory_Next,
    Inventory_InvClear,
    Inventory_CreteKit,
    Inventory_KitList,
    Inventory_KitMenu,
    Inventory_KitListBedrock,
    Inventory_PreviewOn,
    Inventory_PreviewOff,
    Inventory_DuelSendQuest,
    Inventory_DuelInvPlayers,
    Inventory_DuelSelectKit,
    Inventory_DuelTimeLimit,
    Inventory_DuelSelectWorld,
    Inventory_DuelLoreInvPlayers,
    Inventory_DuelLoreInvPlayersEmpty,
    Inventory_DuelLoreSelectKit,
    Inventory_DuelLoreSelectKitEmpty,
    Inventory_DuelLoreTimeLimit,
    Inventory_DuelLoreTimeLimitDisabled,
    Inventory_DuelLoreSelectWorld,
    Inventory_DuelTimeLimitOn,
    Inventory_DuelTimeLimitOff,
    Inventory_DuelTimeLore,
    Inventory_SelectKitFavorite,
    Inventory_TimeLimit_M1mas,
    Inventory_TimeLimit_S1mas,
    Inventory_TimeLimit_S1menos,
    Inventory_TimeLimit_M1menos,
    Inventory_Help_Command,
    Inventory_Help_Command_Lore,
    Inventory_Help_Item,
    Inventory_Help_Item_Lore,
    Inventory_Help_Item_EspecialItem_FERMENTED_SPIDER_EYE_Lore,
    Inventory_Help_Item_EspecialItem_CHARCOAL_Lore,
    Inventory_Help_Item_EspecialItem_SCUTE_Lore,
    Inventory_Help_Item_EspecialItem_FLINT_Lore,
    Inventory_Help_Item_EspecialItem_NETHERITE_SCRAP_Lore,
    Inventory_Help_Item_ArmorItem_Title,
    Inventory_Help_Item_ArmorItem_Lore,
    Inventory_Help_Item_CoinP_Title,
    Inventory_Help_Item_CoinP_Lore,
    Inventory_Help_Item_CoinS_Title,
    Inventory_Help_Item_CoinS_Lore,
    Inventory_Help_Item_CoinT_Title,
    Inventory_Help_Item_CoinT_Lore,
    Inventory_Help_Rules,
    Inventory_Help_Rules_Lore,
    Inventory_Help_Info,
    Inventory_Help_Info_Lore,
    Inventory_Help_Info_xBxTCore_lore,
    //otros
    Others_AddVIPTitle,
    Others_Chat_Cooldown,
    Others_Chat_BanWord,
    Others_Chat_Active,
    Others_WarningGetGuestPlayers,
    Others_IvnPlayers1,
    Others_IvnPlayers2,
    Others_TimeBossBar,
    Others_Alone,
    Others_message1Minute,
    Others_message5Seconds,
    Others_messageStarCleaner,
    Others_CleanerExecuted,
    Others_Donate,
    Others_Vote,
    Others_NewPlayer,
    Others_OpNotAuthized,
    //kick
    Kick_SpamCommand,
    Kick_AlreadyConnected,
    Kick_NotAuthenticated,
    Kick_Cheat,
    //ban
    Ban_BotsChat,
    Ban_AutoMessages,
    Ban_HacksBoxPvp,
    Ban_KickBoxPvp,
    Ban_Dupe,
    //rule
    Rule_Title,
    Rule_0,
    Rule_1,
    Rule_2,
    Rule_3,
    Rule_4,
    Rule_5,
    Rule_6,



}