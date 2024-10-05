package xyz.xbcore.Inventory;

import xyz.xbcore.BoxPvp.ItemsBoxPvp.ItemManage;
import xyz.xbcommun.Command.BaseCommand;
import xyz.xbcore.Commands.CommandSection;
import xyz.xbcore.File.FileManagerSection;
import xyz.xbcore.Inventory.Models.InvetoryPlayer;
import xyz.xbcore.Inventory.Enum.InvetorySection;
import xyz.xbcore.Messages.Messages.Messages;
import xyz.xbcore.Service.PingRequest;
import xyz.xbcore.Utils.ColorUtils;
import xyz.xbcore.Utils.Utils;
import xyz.xbcore.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static xyz.xbcore.Inventory.InventorySection.*;
import static xyz.xbcore.Messages.MessageManager.*;
import static xyz.xbcore.xBxTcore.*;

public class InventoryMenu extends InventoryManager {


    public static ArrayList<String> eslore1 = new ArrayList<>();
    public static ArrayList<String> eslore2 = new ArrayList<>();
    public static ArrayList<String> eslore3 = new ArrayList<>();

    public static ArrayList<String> enlore1 = new ArrayList<>();
    public static ArrayList<String> enlore2 = new ArrayList<>();
    public static ArrayList<String> enlore3 = new ArrayList<>();

    public InventoryMenu(xBxTcore plugin) {
        super(plugin);
        eslore1.add("");
        eslore1.add(ChatColor.translateAlternateColorCodes('&',Coloritem + "&lRecompensas"));
        eslore1.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorinfo + "Llave Basica x5"));
        eslore1.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorinfo + "Llave Especial x2"));
        eslore1.add(ChatColor.translateAlternateColorCodes('&',Coloritem + "&lRecompensas para " + Colorplayer + "VIP"));
        eslore1.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorplayer + "+ " + Colorinfo + "Llave Basical x10"));
        eslore1.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorplayer + "+ " + Colorinfo + "Llave Especial x10"));
        eslore1.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorplayer + "+ " + Colorinfo + "Llave VIP x3"));

        eslore2.add("");
        eslore2.add(ChatColor.translateAlternateColorCodes('&',Coloritem + "&lRecompensas"));
        eslore2.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorinfo + "Llave Basica x25"));
        eslore2.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorinfo + "Llave Especial x10"));
        eslore2.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorinfo + "Llave Epica x5"));
        eslore2.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorinfo + "Llave VIP x2"));
        eslore2.add(ChatColor.translateAlternateColorCodes('&',Coloritem + "&lRecompensas para " + Colorplayer + "VIP"));
        eslore2.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorplayer + "+ " + Colorinfo + "Llave Basica x30"));
        eslore2.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorplayer + "+ " + Colorinfo + "Llave Especial x25"));
        eslore2.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorplayer + "+ " + Colorinfo + "Llave Epica x20"));
        eslore2.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorplayer + "+ " + Colorinfo + "Llave VIP x10"));

        eslore3.add("");
        eslore3.add(ChatColor.translateAlternateColorCodes('&',Coloritem + "&lRecompensas"));
        eslore3.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorinfo + "Llave Basica x150"));
        eslore3.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorinfo + "Llave Especial x100"));
        eslore3.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorinfo + "Llave Epica x80"));
        eslore3.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorinfo + "Llave VIP x50"));
        eslore3.add(ChatColor.translateAlternateColorCodes('&',Coloritem + "&lRecompensas para " + Colorplayer + "VIP"));
        eslore3.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorplayer + "+ " + Colorinfo + "Llave Basica x140"));
        eslore3.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorplayer + "+ " + Colorinfo + "Llave Especial x120"));
        eslore3.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorplayer + "+ " + Colorinfo + "Llave Epica x100"));
        eslore3.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorplayer + "+ " + Colorinfo + "Llave VIP x70"));

        ///////////////////

        enlore1.add("");
        enlore1.add(ChatColor.translateAlternateColorCodes('&',Coloritem + "&lRewards"));
        enlore1.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorinfo + "Basic Key x5"));
        enlore1.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorinfo + "special Key x2"));
        enlore1.add(ChatColor.translateAlternateColorCodes('&',Coloritem + "&lReward for " + Colorplayer + "VIP"));
        enlore1.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorplayer + "+ " + Colorinfo + "Basic Key x10"));
        enlore1.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorplayer + "+ " + Colorinfo + "special Key x10"));
        enlore1.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorplayer + "+ " + Colorinfo + "VIP Key x3"));

        enlore2.add("");
        enlore2.add(ChatColor.translateAlternateColorCodes('&',Coloritem + "&lRewards"));
        enlore2.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorinfo + "Basic Key x25"));
        enlore2.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorinfo + "special Key x10"));
        enlore2.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorinfo + "Epic Key x5"));
        enlore2.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorinfo + "VIP Key x2"));
        enlore2.add(ChatColor.translateAlternateColorCodes('&',Coloritem + "&lReward for " + Colorplayer + "VIP"));
        enlore2.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorplayer + "+ " + Colorinfo + "Basic Keyx30"));
        enlore2.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorplayer + "+ " + Colorinfo + "special Key x25"));
        enlore2.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorplayer + "+ " + Colorinfo + "Epic Key x20"));
        enlore2.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorplayer + "+ " + Colorinfo + "VIP Key x10"));

        enlore3.add("");
        enlore3.add(ChatColor.translateAlternateColorCodes('&',Coloritem + "&lRewards"));
        enlore3.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorinfo + "Basic Key x150"));
        enlore3.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorinfo + "Special Key x100"));
        enlore3.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorinfo + "Epic Key x80"));
        enlore3.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorinfo + "VIP Key x50"));
        enlore3.add(ChatColor.translateAlternateColorCodes('&',Coloritem + "&lReward for " + Colorplayer + "VIP"));
        enlore3.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorplayer + "+ " + Colorinfo + "Basic Key x140"));
        enlore3.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorplayer + "+ " + Colorinfo + "Special Key x120"));
        enlore3.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorplayer + "+ " + Colorinfo + "Epic Key x100"));
        enlore3.add(ChatColor.translateAlternateColorCodes('&',"&f- " + Colorplayer + "+ " + Colorinfo + "VIP Key x70"));
    }

    public void OpenMenuKit(InvetoryPlayer invetoryplayer) {
        invetoryplayer.setPreviewMode(false);
        Player player = invetoryplayer.getPlayer();
        invetoryplayer.setSection(InvetorySection.MENU_PREKIT);
        ItemMeta meta;
        Inventory inv;
        int SlotENDER_CHEST;
        int SlotCHEST;
        int SlotMINECART;
        if (invetoryplayer.getPlayer().getName().contains(bedrockPrefix)) {
            inv = Bukkit.createInventory(null, 27, MasterMessageLocated(player, Messages.Inventory_KitMenu_Title));
            Utils.newItemInventory(Messages.Inventory_Generic_Exit, Material.BARRIER, 26, inv, player);
            SlotENDER_CHEST = 10;
            SlotCHEST = 7 + 9;
            SlotMINECART = 4 + 9;
        }else{
            inv = Bukkit.createInventory(null, 18, MasterMessageLocated(player, Messages.Inventory_KitMenu_Title));
            SlotENDER_CHEST = 1;
            SlotCHEST = 7;
            SlotMINECART = 4;
            ItemStack PANEL_GLASS = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            ItemMeta PanelMeta = PANEL_GLASS.getItemMeta();
            assert PanelMeta != null;
            PanelMeta.setDisplayName(" ");
            PANEL_GLASS.setItemMeta(PanelMeta);
            for(int i = 0; i < inv.getSize(); i++){
                inv.setItem(i,PANEL_GLASS);
            }
            Utils.newItemInventory(Messages.Inventory_Generic_Exit, Material.BARRIER, 13, inv, player);
        }
        /////////////////////////////////////////
        ItemStack ENDER_CHEST = new ItemStack(Material.ENDER_CHEST);
        meta = ENDER_CHEST.getItemMeta();
        assert meta != null;
        meta.setDisplayName(MasterMessageLocated(invetoryplayer.getPlayer(), Messages.Inventory_MenuKit_InvGlobal));
        ENDER_CHEST.setItemMeta(meta);
        inv.setItem(SlotENDER_CHEST,ENDER_CHEST);
        /////////////////////////////////////////
        FileManagerSection.getPlayerFileManager().loadNameKit(player.getUniqueId());
        ItemStack MINECART = new ItemStack(Material.MINECART);
        meta = MINECART.getItemMeta();
        assert meta != null;
        if (invetoryplayer.getPlayer().getWorld().equals(Bukkit.getWorld("lobby"))) {
            meta.setDisplayName(MasterMessageLocated(invetoryplayer.getPlayer(), Messages.Inventory_MenuKit_TpCreatorKits));
        } else {
            meta.setDisplayName(MasterMessageLocated(invetoryplayer.getPlayer(), Messages.Inventory_MenuKit_TpLobby));
        }
        MINECART.setItemMeta(meta);

        if(!FileManagerSection.getPlayerFileManager().nameskits.isEmpty()) {
            ItemStack CHEST = new ItemStack(Material.CHEST);
            meta = CHEST.getItemMeta();
            assert meta != null;
            meta.setDisplayName(MasterMessageLocated(invetoryplayer.getPlayer(), Messages.Inventory_MenuKit_InvCustom));
            CHEST.setItemMeta(meta);
            inv.setItem(SlotCHEST, CHEST);
            inv.setItem(SlotMINECART,MINECART);
        }else{
            inv.setItem(SlotCHEST,MINECART);
        }
        /////////////////////////////////////////
        player.openInventory(inv);
        getInventoryManager().addplayer(invetoryplayer);
    }

    public void OpenInvetoryKitsList(InvetoryPlayer invetoryplayer, int page) {
        ItemMeta itemMeta;
        invetoryplayer.setSection(InvetorySection.MENU_KITS);
        Inventory inv;
        int MaxKits;
        if (invetoryplayer.getPlayer().getName().contains(bedrockPrefix)) {
            inv = Bukkit.createInventory(null, 54, MasterMessageLocated(invetoryplayer.getPlayer(), Messages.Inventory_KitListBedrock_Title));
            MaxKits = 45;
        }else{
            inv = Bukkit.createInventory(null, 36, MasterMessageLocated(invetoryplayer.getPlayer(), Messages.Inventory_KitList_Title));
            MaxKits = 27;
        }

        Player player = invetoryplayer.getPlayer();
        ////////////////////////////////////////////////////
        ItemStack ARROW = new ItemStack(Material.ARROW);
        itemMeta = ARROW.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(MasterMessageLocated(player, Messages.Inventory_Generic_Next));
        ARROW.setItemMeta(itemMeta);
        ///////////////////////////////////////////////////
        ItemStack ARROW_BACK = new ItemStack(Material.ARROW);
        ItemMeta backMeta = ARROW_BACK.getItemMeta();
        assert backMeta != null;
        backMeta.setDisplayName(MasterMessageLocated(player, Messages.Inventory_Generic_Previous));
        ARROW_BACK.setItemMeta(backMeta);
        ///////////////////////////////////////////////////
        ItemStack BARRIER = new ItemStack(Material.BARRIER);
        ItemMeta BARRIERMeta = BARRIER.getItemMeta();
        assert BARRIERMeta != null;
        BARRIERMeta.setDisplayName(MasterMessageLocated(player, Messages.Inventory_Generic_Exit));
        BARRIER.setItemMeta(BARRIERMeta);
        ///////////////////////////////////////////////////
        ItemStack CHEST_MINECART;
        Messages messages;
        if (invetoryplayer.getUUIDKit().equals(invetoryplayer.getPlayer().getUniqueId())){
            CHEST_MINECART = new ItemStack(Material.ENDER_CHEST);
            messages = Messages.Inventory_MenuKit_InvGlobal;
        }else{
            CHEST_MINECART = new ItemStack(Material.CHEST);
            messages = Messages.Inventory_MenuKit_InvCustom;
        }
        ItemMeta CHEST_MINECARTmeta = CHEST_MINECART.getItemMeta();
        assert CHEST_MINECARTmeta != null;
        CHEST_MINECARTmeta.setDisplayName(MasterMessageLocated(player, messages));
        CHEST_MINECART.setItemMeta(CHEST_MINECARTmeta);
        ///////////////////////////////////////////////////


        ItemStack STRUCTURE_VOID;
        if(invetoryplayer.getPlayer().getName().contains(bedrockPrefix)){
            STRUCTURE_VOID = new ItemStack(Material.FEATHER);
        }else{
            STRUCTURE_VOID = new ItemStack(Material.STRUCTURE_VOID);
        }
        ///////////////////////////////////////////////////
        FileManagerSection.getPlayerFileManager().loadNameKit(invetoryplayer.getUUIDKit());
        int posicion = 0;
        int start = page * MaxKits;
        int end = Math.min(start + MaxKits, FileManagerSection.getPlayerFileManager().nameskits.size());

        if(invetoryplayer.getPlayer().getName().contains(bedrockPrefix)){
            if (end < FileManagerSection.getPlayerFileManager().nameskits.size()) {
                inv.setItem(53, ARROW);
            }

            int previewslot = 0;
            itemMeta = STRUCTURE_VOID.getItemMeta();
            if (itemMeta != null){
                if (!invetoryplayer.getKitSelectMode()){
                    itemMeta.setDisplayName(MasterMessageLocated(player, Messages.Inventory_MenuKit_SeleMode_SelectKitFavorite));
                    STRUCTURE_VOID.setItemMeta(itemMeta);
                    inv.setItem(48, STRUCTURE_VOID);
                    previewslot = 50;
                }else{
                    itemMeta.setDisplayName(MasterMessageLocated(player, Messages.Inventory_KitList_InvClear));
                    STRUCTURE_VOID.setItemMeta(itemMeta);
                    inv.setItem(47, CHEST_MINECART);
                    inv.setItem(49, STRUCTURE_VOID);
                    previewslot = 51;
                }
            }

            ItemMeta Meta;
            if (invetoryplayer.getPreviewMode()){
                Meta = ButtoPreviewOn.getItemMeta();
                assert Meta != null;
                Meta.setDisplayName(MasterMessageLocated(invetoryplayer.getPlayer(),Messages.Inventory_PreviewOn));
                ButtoPreviewOn.setItemMeta(Meta);
                inv.setItem(previewslot, ButtoPreviewOn);
            }else{
                Meta = ButtoPreviewOff.getItemMeta();
                assert Meta != null;
                Meta.setDisplayName(MasterMessageLocated(invetoryplayer.getPlayer(),Messages.Inventory_PreviewOff));
                ButtoPreviewOff.setItemMeta(Meta);
                inv.setItem(previewslot, ButtoPreviewOff);
            }

            if (page > 0) {
                inv.setItem(45, ARROW_BACK);
            }else{
                inv.setItem(45, BARRIER);
            }
        }else{
            ItemStack PANEL_GLASS = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
            ItemMeta PanelMeta = PANEL_GLASS.getItemMeta();
            assert PanelMeta != null;
            PanelMeta.setDisplayName(" ");
            PANEL_GLASS.setItemMeta(PanelMeta);
            for(int i = 28; i < 36; i++){
                inv.setItem(i,PANEL_GLASS);
            }
            PANEL_GLASS = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            PanelMeta = PANEL_GLASS.getItemMeta();
            assert PanelMeta != null;
            PanelMeta.setDisplayName(" ");
            PANEL_GLASS.setItemMeta(PanelMeta);
            for(int i = 0; i < 27; i++){
                inv.setItem(i,PANEL_GLASS);
            }

            if (!invetoryplayer.getKitSelectMode()){
                itemMeta.setDisplayName(MasterMessageLocated(player, Messages.Inventory_KitList_InvClear));
                STRUCTURE_VOID.setItemMeta(itemMeta);
                inv.setItem(31, STRUCTURE_VOID);
            }else{
                itemMeta.setDisplayName(MasterMessageLocated(player, Messages.Inventory_MenuKit_SeleMode_SelectKitFavorite));
                STRUCTURE_VOID.setItemMeta(itemMeta);
                inv.setItem(30, CHEST_MINECART);
                inv.setItem(32, STRUCTURE_VOID);
            }

            if (end < FileManagerSection.getPlayerFileManager().nameskits.size()) {
                inv.setItem(35, ARROW);
            }

            if (page > 0) {
                inv.setItem(27, ARROW_BACK);
            }else{
                inv.setItem(27, BARRIER);
            }
        }

        for (int i = start; i < end; i++) {
            List<String> lore = new ArrayList<>();
            String nameskit = FileManagerSection.getPlayerFileManager().nameskits.get(i);
            Material material = FileManagerSection.getPlayerFileManager().materials.get(i);
            ItemStack kit = new ItemStack(material);
            ItemMeta kitmeta = kit.getItemMeta();
            assert kitmeta != null;
            kitmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',nameskit));
            lore.add(ChatColor.translateAlternateColorCodes(' '," 8" + MasterMessageLocated(player, Messages.Inventory_PreviewKit_Load_lore) + nameskit));
            kitmeta.setLore(lore);
            kitmeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            kitmeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "kitName"), PersistentDataType.STRING, nameskit);
            kit.setItemMeta(kitmeta);
            inv.setItem(posicion, kit);
            posicion++;
        }
        FileManagerSection.getPlayerFileManager().loadNameKit(invetoryplayer.getUUIDKit());
        //////////////////////////////////////////////////
        player.openInventory(inv);
        getInventoryManager().addplayer(invetoryplayer);
    }

    public void OpenPreviewKit (Player player, String namekit, InvetoryPlayer invetoryplayer){
        invetoryplayer.setSection(InvetorySection.PREVIEW_KITS);
        Inventory inv = Bukkit.createInventory(null, 54, MasterMessageLocated(player, Messages.Inventory_PreviewKit_Title));
        player.openInventory(inv);
        FileManagerSection.getPlayerFileManager().loadKit(invetoryplayer.getUUIDKit(), namekit, inv, invetoryplayer.getPlayer());
        getInventoryManager().addplayer(invetoryplayer);
    }

    public void OpenItemFrame(Player player, ItemStack item) {
        Inventory inv;
        if(player.getName().contains(bedrockPrefix)){
            inv = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', MasterMessageLocated(player, Messages.Inventory_InvItemFrame_Title)));
        }else{
            inv = Bukkit.createInventory(null, 18, ChatColor.translateAlternateColorCodes('&', MasterMessageLocated(player, Messages.Inventory_InvItemFrame_Title)));
        }
        for (int i=0;i<inv.getSize();i++){
            item.setAmount(item.getMaxStackSize());
            inv.setItem(i,item);
        }
        player.openInventory(inv);
    }

    public void OpenMenuDuel(InvetoryPlayer invetoryPlayer){
        invetoryPlayer.setSection(InvetorySection.MENU_DUEL);
        Player player = invetoryPlayer.getPlayer();
        ArrayList<String> lore = new ArrayList<>();
        Inventory inv = Bukkit.createInventory(null, 27, MasterMessageLocated(player, Messages.Inventory_MenuDuel_Title));
        ///////////////////////////////////////////////////
        ItemStack PANEL_GLASS = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta PanelMeta = PANEL_GLASS.getItemMeta();
        assert PanelMeta != null;
        PanelMeta.setDisplayName(" ");
        PANEL_GLASS.setItemMeta(PanelMeta);
        for(int i = 0; i < 27; i++){
            inv.setItem(i,PANEL_GLASS);
        }
        ///////////////////////////////////////////////////
        if (getPlayerDataUnique(player.getUniqueId()) != null){
            lore.add(MasterMessageLocated(player, Messages.Inventory_MenuDuel_InvPlayers_Lore));
            for (Player p : getPlayerDataUnique(player.getUniqueId()).getGuestPlayers(false)){
                if(!p.getName().equals(player.getName())){
                    lore.add(ChatColor.translateAlternateColorCodes('&', "&f&l-&r " + Colorplayer + p.getName()));
                }
            }
            if (lore.size() == 1){
                lore.clear();
                lore.add(MasterMessageLocated(player, Messages.Inventory_MenuDuel_InvPlayersEmpty_Lore));
            }
        }
        Utils.newItemInventory(Messages.Inventory_MenuDuel_InvPlayers_title, Material.WRITABLE_BOOK, 10, inv, player, lore);
        ///////////////////////////////////////////////////
        lore.clear();

        if (getPlayerDataUnique(player.getUniqueId()).getKitData().getName() != null){
            lore.add(ChatColor.translateAlternateColorCodes('&',MasterMessageLocated(player, Messages.Inventory_MenuDuel_SelectKit_Lore) + getPlayerDataUnique(player.getUniqueId()).getKitData().getName()));
        }else{
            lore.add(MasterMessageLocated(player, Messages.Inventory_MenuDuel_SelectKitEmpty_Lore));
        }
        Utils.newItemInventory(Messages.Inventory_MenuDuel_SelectKit, Material.CHEST_MINECART, 12, inv, player, lore);
        ///////////////////////////////////////////////////
        Utils.newItemInventory(Messages.Inventory_MenuDuel_SendQuest, Material.BLAZE_POWDER, 22, inv, player);
        ///////////////////////////////////////////////////
        lore.clear();
        if(getPlayerDataUnique(player.getUniqueId()).getTimeLimit()){
            lore.add(MasterMessageLocated(player, Messages.Inventory_MenuDuel_TimeLimit_Lore));
            lore.addAll(secondsToMinutesLore(player));
        }else{
            lore.add(MasterMessageLocated(player, Messages.Inventory_MenuDuel_TimeLimitDisabled_Lore));
        }

        Utils.newItemInventory(Messages.Inventory_MenuDuel_TimeLimit_Title, Material.CLOCK, 14, inv, player, lore);
        ///////////////////////////////////////////////////
        Utils.newItemInventory(Messages.Inventory_Generic_Exit, Material.BARRIER, 26, inv, player);
        player.openInventory(inv);
        getInventoryManager().addplayer(invetoryPlayer);
        SelectMapDuel(invetoryPlayer, false);

    }

    public void OpenTimeSelect(InvetoryPlayer invetoryPlayer){
        invetoryPlayer.setSection(InvetorySection.TIME_SELECT);
        Player player = invetoryPlayer.getPlayer();
        Inventory inv = Bukkit.createInventory(null, 27, MasterMessageLocated(player, Messages.Inventory_KitMenu_Title));
        ///////////////////////////////////////////////////
        ItemStack PANEL_GLASS = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta PanelMeta = PANEL_GLASS.getItemMeta();
        assert PanelMeta != null;
        PanelMeta.setDisplayName(" ");
        PANEL_GLASS.setItemMeta(PanelMeta);
        for(int i = 0; i < 27; i++){
            inv.setItem(i,PANEL_GLASS);
        }
        ///////////////////////////////////////////////////
        Utils.newItemInventory(Messages.Inventory_MenuDuel_TimeLimit_M1menos, Material.RED_STAINED_GLASS_PANE, 11, inv, player);
        Utils.newItemInventory(Messages.Inventory_MenuDuel_TimeLimit_S1menos, Material.RED_STAINED_GLASS_PANE, 12, inv, player);
        ///////////////////////////////////////////////////
        Utils.newItemInventory(Messages.Inventory_MenuDuel_TimeLimit_S1mas, Material.GREEN_STAINED_GLASS_PANE, 14, inv, player);
        Utils.newItemInventory(Messages.Inventory_MenuDuel_TimeLimit_M1mas, Material.GREEN_STAINED_GLASS_PANE, 15, inv, player);
        ///////////////////////////////////////////////////
        Utils.newItemInventory(Messages.Inventory_Generic_Exit, Material.BARRIER, 22, inv, player);


        if (getPlayerDataUnique(player.getUniqueId()).getTimeLimit()){
            Utils.newItemInventory(Messages.Inventory_MenuDuel_TimeLimit_On, Material.ENDER_EYE, 13, inv, player, secondsToMinutesLore(player));
        }else{
            Utils.newItemInventory(Messages.Inventory_MenuDuel_TimeLimit_Off, Material.ENDER_PEARL, 13, inv, player, secondsToMinutesLore(player));
        }
        player.openInventory(inv);
        getInventoryManager().addplayer(invetoryPlayer);
    }

    public void OpenRewardTimes(InvetoryPlayer invetoryPlayer){
        Player player = invetoryPlayer.getPlayer();
        invetoryPlayer.setSection(InvetorySection.REWARD_TIMES);
        Inventory inv;
        if (invetoryPlayer.getPlayer().getName().contains(bedrockPrefix)) {
            inv = Bukkit.createInventory(null, 54, " ");
        }else{
            inv = Bukkit.createInventory(null, 45, " ");
            ///////////////////////////////////////////////////
            ItemStack PANEL_GLASS = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            ItemMeta PanelMeta = PANEL_GLASS.getItemMeta();
            assert PanelMeta != null;
            PanelMeta.setDisplayName(" ");
            PANEL_GLASS.setItemMeta(PanelMeta);
            for(int i = 0; i < inv.getSize(); i++){
                inv.setItem(i,PANEL_GLASS);
            }
            ///////////////////////////////////////////////////
            PANEL_GLASS = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
            PanelMeta = PANEL_GLASS.getItemMeta();
            assert PanelMeta != null;
            PanelMeta.setDisplayName(" ");
            PANEL_GLASS.setItemMeta(PanelMeta);
            for(int i = 0; i < 9; i++){
                inv.setItem(i,PANEL_GLASS);
            }
            for(int i = 36; i < inv.getSize(); i++){
                inv.setItem(i,PANEL_GLASS);
            }
        }
        ///////////////////////////////////////////////////
        ArrayList<String> lore = new ArrayList<>();
        new BukkitRunnable() {
            public void run() {
                if (player.getOpenInventory().getTopInventory().isEmpty()){
                    cancel();
                    return;
                }

                FileManagerSection.getPlayerFileManager().loadTimesRewords(invetoryPlayer.getPlayer().getUniqueId());
                lore.clear();
                lore.add(ChatColor.translateAlternateColorCodes('&',Colorinfo + "Puedes reclamarlo en: " + Utils.TimeToString(FileManagerSection.getPlayerFileManager().daily - System.currentTimeMillis(), 1)));
                if (player.getLocale().contains("es")){//Hay que arreglar esto algún día (nunca lo arreglo)
                    lore.addAll(eslore1);
                }else {
                    lore.addAll(enlore1);
                }

                if (FileManagerSection.getPlayerFileManager().daily <= System.currentTimeMillis()){
                    if (player.getLocale().contains("es")){
                        Utils.newItemInventory(Messages.Reward_Daily, Material.CHEST_MINECART, 20, inv, player, eslore1);
                    }else {
                        Utils.newItemInventory(Messages.Reward_Daily, Material.CHEST_MINECART, 20, inv, player, enlore1);
                    }

                }else{
                    Utils.newItemInventory(Messages.Reward_Daily, Material.MINECART, 20, inv, player, lore);
                }
                lore.clear();
                lore.add(ChatColor.translateAlternateColorCodes('&',Colorinfo + "Puedes reclamarlo en: "+ Utils.TimeToString(FileManagerSection.getPlayerFileManager().weekly - System.currentTimeMillis(), 1)));
                if (player.getLocale().contains("es")){
                    lore.addAll(eslore2);
                }else {
                    lore.addAll(enlore2);
                }

                if (FileManagerSection.getPlayerFileManager().weekly <= System.currentTimeMillis()){
                    if (player.getLocale().contains("es")){
                        Utils.newItemInventory(Messages.Reward_Weekly, Material.CHEST_MINECART, 22, inv, player, eslore2);
                    }else {
                        Utils.newItemInventory(Messages.Reward_Weekly, Material.CHEST_MINECART, 22, inv, player, enlore2);
                    }

                }else{
                    Utils.newItemInventory(Messages.Reward_Weekly, Material.MINECART, 22, inv, player, lore);
                }
                lore.clear();
                lore.add(ChatColor.translateAlternateColorCodes('&',Colorinfo + "Puedes reclamarlo en: "+ Utils.TimeToString(FileManagerSection.getPlayerFileManager().monthly - System.currentTimeMillis(), 1)));

                if (player.getLocale().contains("es")){
                    lore.addAll(eslore3);
                }else {
                    lore.addAll(enlore3);
                }

                if (FileManagerSection.getPlayerFileManager().monthly <= System.currentTimeMillis()){
                    if (player.getLocale().contains("es")){
                        Utils.newItemInventory(Messages.Reward_Monthly, Material.CHEST_MINECART, 24, inv, player, eslore3);
                    }else {
                        Utils.newItemInventory(Messages.Reward_Monthly, Material.CHEST_MINECART, 24, inv, player, enlore3);
                    }
                }else{
                    Utils.newItemInventory(Messages.Reward_Monthly, Material.MINECART, 24, inv, player, lore);
                }
                lore.clear();
            }
        }.runTaskTimer(plugin, 0, 20);
        player.openInventory(inv);
        getInventoryManager().addplayer(invetoryPlayer);
    }

    public void OpenMenuHelp(InvetoryPlayer invetoryPlayer){
        Player player = invetoryPlayer.getPlayer();
        invetoryPlayer.setSection(InvetorySection.HELP);
        Inventory inv = Bukkit.createInventory(null, 27, MasterMessageLocated(player, Messages.Inventory_MenuHelp_Title));
        if (!player.getName().contains(bedrockPrefix)){
            for (int i = 0; inv.getSize() > i ; i++){
                Utils.newItemInventory(" ", Material.GRAY_STAINED_GLASS_PANE, i, inv);
            }
        }
        String lore = MasterMessageLocated(player, Messages.Inventory_MenuHelp_Command_Lore);
        Utils.newItemInventory(ColorUtils.applyGradient(MasterMessageLocated(player, Messages.Inventory_MenuHelp_Command),'l'), Material.COMMAND_BLOCK,
                10, inv, Utils.StringToLoreString(lore, true, '3'));
        lore = MasterMessageLocated(player, Messages.Inventory_MenuHelp_Item_Lore);
        Utils.newItemInventory(ColorUtils.applyGradient(MasterMessageLocated(player, Messages.Inventory_MenuHelp_Item),'l'), Material.DIAMOND_SWORD,
                12, inv, Utils.StringToLoreString(lore, true, '3'));
        lore = MasterMessageLocated(player, Messages.Inventory_MenuHelp_Rules_Lore);
        Utils.newItemInventory(ColorUtils.applyGradient(MasterMessageLocated(player, Messages.Inventory_MenuHelp_Rules),'l'), Material.WRITABLE_BOOK,
                14, inv, Utils.StringToLoreString(lore, true, '3'));
        lore = MasterMessageLocated(player, Messages.Inventory_MenuHelp_Info_Lore);
        Utils.newItemInventory(ColorUtils.applyGradient(MasterMessageLocated(player, Messages.Inventory_MenuHelp_Info),'l'), Material.SPYGLASS,
                16, inv, Utils.StringToLoreString(lore, true, '3'));
        Utils.newItemInventory(Messages.Inventory_Generic_Exit, Material.BARRIER, 26, inv, player);
        player.openInventory(inv);
        getInventoryManager().addplayer(invetoryPlayer);
    }

    public void OpenHelpRules(InvetoryPlayer invetoryPlayer){
        Player player = invetoryPlayer.getPlayer();
        invetoryPlayer.setSection(InvetorySection.HELP_RULE);
        Inventory inv = Bukkit.createInventory(null, 27, ColorUtils.applyGradient(MasterMessageLocated(player, Messages.Inventory_MenuHelp_Rules)));
        if (!player.getName().contains(bedrockPrefix)){
            for (int i = 0; inv.getSize() > i ; i++){
                Utils.newItemInventory(" ", Material.GRAY_STAINED_GLASS_PANE, i, inv);
            }
        }
        for (int i = 0; 7 > i; i++){
            Utils.newItemInventory(ColorUtils.applyGradient("<#FC5C5C>" + MasterMessageLocated(player, Messages.Rule_Title).replace("%#%", String.valueOf(i)) + "<#FFDD91>"),
                    Material.WRITABLE_BOOK, i, inv, Utils.StringToLoreString(MasterMessageLocated(player ,Messages.valueOf("Rule_" + i)), true, '7'));
        }
        Utils.newItemInventory(Messages.Inventory_Generic_Exit, Material.BARRIER, 26, inv, player);
        player.openInventory(inv);
        getInventoryManager().addplayer(invetoryPlayer);
    }

    public void OpenHelpInfo(InvetoryPlayer invetoryPlayer){
        Player player = invetoryPlayer.getPlayer();
        invetoryPlayer.setSection(InvetorySection.HELP_INFO);
        Inventory inv = Bukkit.createInventory(null, 27, ColorUtils.applyGradient(MasterMessageLocated(player, Messages.Inventory_MenuHelp_Info)));
        if (!player.getName().contains(bedrockPrefix)){
            for (int i = 0; inv.getSize() > i ; i++){
                Utils.newItemInventory(" ", Material.GRAY_STAINED_GLASS_PANE, i, inv);
            }
        }

        new BukkitRunnable(){
            public void run(){
                OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
                if (player.getOpenInventory().getTopInventory().isEmpty()){
                    cancel();
                    return;
                }
                String lore = """
                &f-&6 Ping:&3 %ping% &8(En relación google.com)
                &f-&6 CPU:&3 i7-4700MQ&3 %pro%% &8(Proceso)
                &f-&6 Tiempo:&3 %tiempo% &8(Proceso)
                &f-&6 RAM:&3 %ram%% &8(DDR3L 8GB)
                """.replace("%ping%", PingRequest.getPing() + "ms")
                        .replace("%ram%",String.valueOf(Math.round((float) Runtime.getRuntime().totalMemory() / Runtime.getRuntime().freeMemory() * 10)))
                        .replace("%pro%", String.valueOf(osBean.getSystemLoadAverage()))
                        .replace("%tiempo%", Objects.requireNonNull(Utils.TimeToString(System.currentTimeMillis() - plugin.serverStartTime, 1)));
                Utils.newItemInventory("Hardware/Sistema", Material.COMMAND_BLOCK_MINECART, 12, inv, Utils.StringToLoreString(lore, 1000, true, '8'));
            }
        }.runTaskTimer(plugin, 0, 40);
        String lore = "";
        Utils.newItemInventory(prefix.replace("[","").replace("]",""), Material.REDSTONE_BLOCK, 14, inv,
                Utils.StringToLoreString(MasterMessageLocated(player, Messages.Inventory_MenuHelp_Info_xBxTCore_lore) , true, '7'));
        Utils.newItemInventory(Messages.Inventory_Generic_Exit, Material.BARRIER, 26, inv, player);
        player.openInventory(inv);
        getInventoryManager().addplayer(invetoryPlayer);
    }

    public void OpenMenuCommands(InvetoryPlayer invetoryPlayer){
        Player player = invetoryPlayer.getPlayer();
        invetoryPlayer.setSection(InvetorySection.HELP_COMMANDS);
        Inventory inv = Bukkit.createInventory(null, 27, ColorUtils.applyGradient(MasterMessageLocated(player, Messages.Inventory_MenuHelp_Command)));
        if (!player.getName().contains(bedrockPrefix)){
            for (int i = 0; inv.getSize() > i ; i++){
                Utils.newItemInventory(" ", Material.GRAY_STAINED_GLASS_PANE, i, inv);
            }
        }
        int i = 0;
        for (BaseCommand baseCommand : CommandSection.getCommandHandler().getCommands()){
            if(baseCommand.getOnlyOP())continue;
            ArrayList<String> lore = new ArrayList<>();
            lore.add("&f- " + Coloritem + "Uso: &r" + formatUsesCommand(baseCommand.getUsage()));
            lore.add("&f- " + Coloritem + "Rango Mínimo: " + Colorinfo + permissionToRange(baseCommand.getPermissions()));
            lore.addAll(Utils.StringToLoreString("&f- " + Coloritem + "Descripción: &7" + baseCommand.getDescription(), false, '7'));
            Utils.newItemInventory(ColorUtils.applyGradient("<#FCD05C>" + Arrays.toString(baseCommand.getName()).replace("[","").replace("]","") + "<#FFE7A7>"),
                    Material.COMMAND_BLOCK, i, inv, lore);
            i++;
        }
        Utils.newItemInventory(Messages.Inventory_Generic_Exit, Material.BARRIER, 26, inv, player);
        player.openInventory(inv);
        getInventoryManager().addplayer(invetoryPlayer);
    }

    public void OpenHelpItem(InvetoryPlayer invetoryPlayer){
        Player player = invetoryPlayer.getPlayer();
        invetoryPlayer.setSection(InvetorySection.HELP_ITEM);
        Inventory inv = Bukkit.createInventory(null, 54, ColorUtils.applyGradient(MasterMessageLocated(player, Messages.Inventory_MenuHelp_Item)));
        if (!player.getName().contains(bedrockPrefix)){
            for (int i = 0; inv.getSize() > i ; i++){
                Utils.newItemInventory(" ", Material.GRAY_STAINED_GLASS_PANE, i, inv);
            }
            for (int i = 9; 18 > i ; i++){
                Utils.newItemInventory(" ", Material.BLUE_STAINED_GLASS_PANE, i, inv);
            }
        }
        int i = 0;
        for (ItemStack itemStack : ItemManage.especialItems){
            ItemMeta itemMeta = itemStack.getItemMeta();
            if (itemMeta == null)continue;
            Utils.newItemInventory(itemMeta.getDisplayName(), itemStack.getType(), i, inv, Utils.StringToLoreString(MasterMessageLocated(player ,loreOfItemEspecial(itemStack.getType())), true));
            i++;
        }
        Utils.newItemInventory(ColorUtils.applyGradient(MasterMessageLocated(player, Messages.Inventory_MenuHelp_Item_ArmorItem_Title), 'l'), Material.NETHERITE_SWORD, 22, inv, Utils.StringToLoreString(MasterMessageLocated(player , Messages.Inventory_MenuHelp_Item_ArmorItem_Lore),70, false));
        Utils.newItemInventory(ColorUtils.applyGradient(MasterMessageLocated(player, Messages.Inventory_MenuHelp_Item_CoinP_Title), 'l'), Material.AMETHYST_BLOCK, 38, inv, Utils.StringToLoreString(MasterMessageLocated(player , Messages.Inventory_MenuHelp_Item_CoinP_Lore), true));
        Utils.newItemInventory(ColorUtils.applyGradient(MasterMessageLocated(player, Messages.Inventory_MenuHelp_Item_CoinS_Title), 'l'), Material.COPPER_BLOCK, 40, inv, Utils.StringToLoreString(MasterMessageLocated(player , Messages.Inventory_MenuHelp_Item_CoinS_Lore), true));
        Utils.newItemInventory(ColorUtils.applyGradient(MasterMessageLocated(player, Messages.Inventory_MenuHelp_Item_CoinT_Title), 'l'), Material.RED_GLAZED_TERRACOTTA, 42, inv, Utils.StringToLoreString(MasterMessageLocated(player , Messages.Inventory_MenuHelp_Item_CoinT_Lore), true));

        Utils.newItemInventory(Messages.Inventory_Generic_Exit, Material.BARRIER, 53, inv, player);
        player.openInventory(inv);
        getInventoryManager().addplayer(invetoryPlayer);
    }

    public void OpenMenu(InvetoryPlayer invetoryPlayer){
        Player player = invetoryPlayer.getPlayer();
        invetoryPlayer.setSection(InvetorySection.MENU);
        String title1 = ColorUtils.applyGradient(MasterMessageLocated(player, Messages.Inventory_Menu_Title).split(":")[0], 'l');
        String title2 = ColorUtils.applyGradient(MasterMessageLocated(player, Messages.Inventory_Menu_Title).split(":")[1], 'l');
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&',title1 + " " + title2));
        if (!player.getName().contains(bedrockPrefix)) {
            for (int i = 0; inv.getSize() > i; i++) {
                Utils.newItemInventory(" ", Material.GRAY_STAINED_GLASS_PANE, i, inv);
            }
        }

        Utils.newItemInventory(Messages.Inventory_KitMenu_Title, Material.SHULKER_BOX, 11, inv, player,
                Utils.StringToLoreString(MasterMessageLocated(player , Messages.Inventory_Menu_MenuKit_Lore), true));
        Utils.newItemInventory(Messages.Inventory_MenuDuel_Title, Material.NETHERITE_SWORD, 13, inv, player,
                Utils.StringToLoreString(MasterMessageLocated(player , Messages.Inventory_Menu_MenuDuel_Lore), true));
        Utils.newItemInventory(Messages.Inventory_MenuHelp_Title, Material.WRITABLE_BOOK, 15, inv, player,
                Utils.StringToLoreString(MasterMessageLocated(player , Messages.Inventory_Menu_MenuHelp_Lore), true));

        player.openInventory(inv);
        getInventoryManager().addplayer(invetoryPlayer);
    }

}
