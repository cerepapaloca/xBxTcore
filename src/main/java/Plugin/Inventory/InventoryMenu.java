package Plugin.Inventory;

import Plugin.File.FileManagerSection;
import Plugin.Inventory.Models.InvetoryPlayer;
import Plugin.Inventory.Enum.InvetorySection;
import Plugin.Messages.Messages.Messages;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
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

import java.util.ArrayList;
import java.util.List;

import static Plugin.Inventory.InventorySection.*;
import static Plugin.Messages.MessageManager.*;
import static Plugin.xBxTcore.*;

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

    public void OpenMenuInvetory(InvetoryPlayer invetoryplayer) {
        invetoryplayer.setPreviewMode(false);
        Player player = invetoryplayer.getPlayer();
        invetoryplayer.setSection(InvetorySection.MENU_PREKIT);
        ItemMeta meta;
        Inventory inv;
        int SlotENDER_CHEST;
        int SlotCHEST;
        int SlotMINECART;
        if (invetoryplayer.getPlayer().getName().contains(bedrockPrefix)) {
            inv = Bukkit.createInventory(null, 27, MasterMessageLocated(player, Messages.Inventory_KitMenu));
            SlotENDER_CHEST = 10;
            SlotCHEST = 7 + 9;
            SlotMINECART = 4 + 9;
        }else{
            inv = Bukkit.createInventory(null, 9, MasterMessageLocated(player, Messages.Inventory_KitMenu));
            SlotENDER_CHEST = 1;
            SlotCHEST = 7;
            SlotMINECART = 4;
            ItemStack PANEL_GLASS = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
            ItemMeta PanelMeta = PANEL_GLASS.getItemMeta();
            assert PanelMeta != null;
            PanelMeta.setDisplayName(" ");
            PANEL_GLASS.setItemMeta(PanelMeta);
            for(int i = 0; i < 9; i++){
                inv.setItem(i,PANEL_GLASS);
            }
        }
        /////////////////////////////////////////
        ItemStack ENDER_CHEST = new ItemStack(Material.ENDER_CHEST);
        meta = ENDER_CHEST.getItemMeta();
        assert meta != null;
        meta.setDisplayName(MasterMessageLocated(invetoryplayer.getPlayer(), Messages.Inventory_InvGlobal));
        ENDER_CHEST.setItemMeta(meta);
        inv.setItem(SlotENDER_CHEST,ENDER_CHEST);
        /////////////////////////////////////////
        FileManagerSection.getPlayerFileManager().loadNameKit(player.getUniqueId());
        ItemStack MINECART = new ItemStack(Material.MINECART);
        meta = MINECART.getItemMeta();
        assert meta != null;
        if (invetoryplayer.getPlayer().getWorld().equals(Bukkit.getWorld("lobby"))) {
            meta.setDisplayName(MasterMessageLocated(invetoryplayer.getPlayer(), Messages.Inventory_TpCreatorKits));
        } else {
            meta.setDisplayName(MasterMessageLocated(invetoryplayer.getPlayer(), Messages.Inventory_TpLobby));
        }
        MINECART.setItemMeta(meta);

        if(!FileManagerSection.getPlayerFileManager().nameskits.isEmpty()) {
            ItemStack CHEST = new ItemStack(Material.CHEST);
            meta = CHEST.getItemMeta();
            assert meta != null;
            meta.setDisplayName(MasterMessageLocated(invetoryplayer.getPlayer(), Messages.Inventory_InvCustom));
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
            inv = Bukkit.createInventory(null, 54, MasterMessageLocated(invetoryplayer.getPlayer(), Messages.Inventory_KitListBedrock));
            MaxKits = 45;
        }else{
            inv = Bukkit.createInventory(null, 36, MasterMessageLocated(invetoryplayer.getPlayer(), Messages.Inventory_KitList));
            MaxKits = 27;
        }

        Player player = invetoryplayer.getPlayer();
        ////////////////////////////////////////////////////
        ItemStack ARROW = new ItemStack(Material.ARROW);
        itemMeta = ARROW.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(MasterMessageLocated(player, Messages.Inventory_Next));
        ARROW.setItemMeta(itemMeta);
        ///////////////////////////////////////////////////
        ItemStack ARROW_BACK = new ItemStack(Material.ARROW);
        ItemMeta backMeta = ARROW_BACK.getItemMeta();
        assert backMeta != null;
        backMeta.setDisplayName(MasterMessageLocated(player, Messages.Inventory_Previous));
        ARROW_BACK.setItemMeta(backMeta);
        ///////////////////////////////////////////////////
        ItemStack BARRIER = new ItemStack(Material.BARRIER);
        ItemMeta BARRIERMeta = BARRIER.getItemMeta();
        assert BARRIERMeta != null;
        BARRIERMeta.setDisplayName(MasterMessageLocated(player, Messages.Inventory_InvExit));
        BARRIER.setItemMeta(BARRIERMeta);
        ///////////////////////////////////////////////////
        ItemStack CHEST_MINECART;
        Messages messages;
        if (invetoryplayer.getUUIDKit().equals(invetoryplayer.getPlayer().getUniqueId())){
            CHEST_MINECART = new ItemStack(Material.ENDER_CHEST);
            messages = Messages.Inventory_InvGlobal;
        }else{
            CHEST_MINECART = new ItemStack(Material.CHEST);
            messages = Messages.Inventory_InvCustom;
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
                    itemMeta.setDisplayName(MasterMessageLocated(player, Messages.Inventory_SelectKitFavorite));
                    STRUCTURE_VOID.setItemMeta(itemMeta);
                    inv.setItem(48, STRUCTURE_VOID);
                    previewslot = 50;
                }else{
                    itemMeta.setDisplayName(MasterMessageLocated(player, Messages.Inventory_InvClear));
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
                itemMeta.setDisplayName(MasterMessageLocated(player, Messages.Inventory_InvClear));
                STRUCTURE_VOID.setItemMeta(itemMeta);
                inv.setItem(31, STRUCTURE_VOID);
            }else{
                itemMeta.setDisplayName(MasterMessageLocated(player, Messages.Inventory_SelectKitFavorite));
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
            lore.add(ChatColor.translateAlternateColorCodes(' '," 8" + MasterMessageLocated(player, Messages.Inventory_lore) + nameskit));
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
        Inventory inv = Bukkit.createInventory(null, 54, MasterMessageLocated(player, Messages.Inventory_InvPreview));
        player.openInventory(inv);
        FileManagerSection.getPlayerFileManager().loadKit(invetoryplayer.getUUIDKit(), namekit, inv, invetoryplayer.getPlayer());
        getInventoryManager().addplayer(invetoryplayer);
    }

    public void OpenItemframe(Player player, ItemStack item) {
        Inventory inv;
        if(player.getName().contains(bedrockPrefix)){
            inv = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', MasterMessageLocated(player, Messages.Inventory_InvItemFrame)));
        }else{
            inv = Bukkit.createInventory(null, 18, ChatColor.translateAlternateColorCodes('&', MasterMessageLocated(player, Messages.Inventory_InvItemFrame)));
        }
        for (int i=0;i<inv.getSize();i++){
            item.setAmount(item.getMaxStackSize());
            inv.setItem(i,item);
        }
        player.openInventory(inv);
    }

    public void OpenDuel(InvetoryPlayer invetoryPlayer){
        invetoryPlayer.setSection(InvetorySection.MENU_DUEL);
        Player player = invetoryPlayer.getPlayer();
        ArrayList<String> lore = new ArrayList<>();
        Inventory inv = Bukkit.createInventory(null, 27, MasterMessageLocated(player, Messages.Inventory_KitMenu));
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
        if (xBxTcore.getPlayerDataUnique(player.getUniqueId()) != null){
            lore.add(MasterMessageLocated(player, Messages.Inventory_DuelLoreInvPlayers));
            for (Player p : xBxTcore.getPlayerDataUnique(player.getUniqueId()).getGuestPlayers(false)){
                if(!p.getName().equals(player.getName())){
                    lore.add(ChatColor.translateAlternateColorCodes('&', "&f&l-&r " + Colorplayer + p.getName()));
                }
            }
            if (lore.size() == 1){
                lore.clear();
                lore.add(MasterMessageLocated(player, Messages.Inventory_DuelLoreInvPlayersEmpty));
            }
        }
        Utils.NewitemInvetory(Messages.Inventory_DuelInvPlayers, Material.WRITABLE_BOOK, 10, inv, player, lore);
        ///////////////////////////////////////////////////
        lore.clear();

        if (xBxTcore.getPlayerDataUnique(player.getUniqueId()).getKitData().getName() != null){
            lore.add(ChatColor.translateAlternateColorCodes('&',MasterMessageLocated(player, Messages.Inventory_DuelLoreSelectKit) + xBxTcore.getPlayerDataUnique(player.getUniqueId()).getKitData().getName()));
        }else{
            lore.add(MasterMessageLocated(player, Messages.Inventory_DuelLoreSelectKitEmpty));
        }
        Utils.NewitemInvetory(Messages.Inventory_DuelSelectKit, Material.CHEST_MINECART, 12, inv, player, lore);
        ///////////////////////////////////////////////////
        Utils.NewitemInvetory(Messages.Inventory_DuelSendQuest, Material.BLAZE_POWDER, 22, inv, player);
        ///////////////////////////////////////////////////
        lore.clear();
        if(xBxTcore.getPlayerDataUnique(player.getUniqueId()).getTimeLimit()){
            lore.add(MasterMessageLocated(player, Messages.Inventory_DuelLoreTimeLimit));
            lore.addAll(secondsToMinutesLore(player));
        }else{
            lore.add(MasterMessageLocated(player, Messages.Inventory_DuelLoreTimeLimitDisabled));
        }

        Utils.NewitemInvetory(Messages.Inventory_DuelTimeLimit, Material.CLOCK, 14, inv, player, lore);
        ///////////////////////////////////////////////////
        player.openInventory(inv);
        getInventoryManager().addplayer(invetoryPlayer);
        SelectMapDuel(invetoryPlayer, false);

    }

    public void OpenTimeSelect(InvetoryPlayer invetoryPlayer){
        invetoryPlayer.setSection(InvetorySection.TIME_SELECT);
        Player player = invetoryPlayer.getPlayer();
        ArrayList<String> lore = new ArrayList<>();
        Inventory inv = Bukkit.createInventory(null, 27, MasterMessageLocated(player, Messages.Inventory_KitMenu));
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
        Utils.NewitemInvetory(Messages.Inventory_TimeLimit_M1menos, Material.RED_STAINED_GLASS_PANE, 11, inv, player);
        Utils.NewitemInvetory(Messages.Inventory_TimeLimit_S1menos, Material.RED_STAINED_GLASS_PANE, 12, inv, player);
        ///////////////////////////////////////////////////
        Utils.NewitemInvetory(Messages.Inventory_TimeLimit_S1mas, Material.GREEN_STAINED_GLASS_PANE, 14, inv, player);
        Utils.NewitemInvetory(Messages.Inventory_TimeLimit_M1mas, Material.GREEN_STAINED_GLASS_PANE, 15, inv, player);
        ///////////////////////////////////////////////////
        Utils.NewitemInvetory(Messages.Inventory_InvExit, Material.BARRIER, 22, inv, player);


        if (xBxTcore.getPlayerDataUnique(player.getUniqueId()).getTimeLimit()){
            Utils.NewitemInvetory(Messages.Inventory_DuelTimeLimitOn, Material.ENDER_EYE, 13, inv, player, secondsToMinutesLore(player));
        }else{
            Utils.NewitemInvetory(Messages.Inventory_DuelTimeLimitOff, Material.ENDER_PEARL, 13, inv, player, secondsToMinutesLore(player));
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
                        Utils.NewitemInvetory(Messages.Reward_Daily, Material.CHEST_MINECART, 20, inv, player, eslore1);
                    }else {
                        Utils.NewitemInvetory(Messages.Reward_Daily, Material.CHEST_MINECART, 20, inv, player, enlore1);
                    }

                }else{
                    Utils.NewitemInvetory(Messages.Reward_Daily, Material.MINECART, 20, inv, player, lore);
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
                        Utils.NewitemInvetory(Messages.Reward_Weekly, Material.CHEST_MINECART, 22, inv, player, eslore2);
                    }else {
                        Utils.NewitemInvetory(Messages.Reward_Weekly, Material.CHEST_MINECART, 22, inv, player, enlore2);
                    }

                }else{
                    Utils.NewitemInvetory(Messages.Reward_Weekly, Material.MINECART, 22, inv, player, lore);
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
                        Utils.NewitemInvetory(Messages.Reward_Monthly, Material.CHEST_MINECART, 24, inv, player, eslore3);
                    }else {
                        Utils.NewitemInvetory(Messages.Reward_Monthly, Material.CHEST_MINECART, 24, inv, player, enlore3);
                    }
                }else{
                    Utils.NewitemInvetory(Messages.Reward_Monthly, Material.MINECART, 24, inv, player, lore);
                }
                lore.clear();
            }
        }.runTaskTimer(plugin, 0, 20);
        player.openInventory(inv);
        getInventoryManager().addplayer(invetoryPlayer);
    }

    public void OpenHelp(InvetoryPlayer invetoryPlayer){
        Player player = invetoryPlayer.getPlayer();
        invetoryPlayer.setSection(InvetorySection.HELP);
        Inventory inv = Bukkit.createInventory(null, 27, "ayuda");
        String lore = "Puedes ber que hace cada comando del servidor";
        Utils.NewitemInvetory(Messages.Inventory_TimeLimit_M1mas, Material.COMMAND_BLOCK, 10, inv, player, Utils.StringToLoreString(lore, true));
        lore = "Puedes saber información Cada Item Del Box Pvp";
        Utils.NewitemInvetory(Messages.Inventory_TimeLimit_M1mas, Material.DIAMOND_SWORD, 12, inv, player, Utils.StringToLoreString(lore, true));
        lore = "Puedes saber las reglase de este servidor";
        Utils.NewitemInvetory(Messages.Inventory_TimeLimit_M1mas, Material.WRITABLE_BOOK, 14, inv, player, Utils.StringToLoreString(lore, true));
        lore = "Puedes saber información genera del server";
        Utils.NewitemInvetory(Messages.Inventory_TimeLimit_M1mas, Material.SPYGLASS, 16, inv, player, Utils.StringToLoreString(lore, true));
        player.openInventory(inv);
        InventorySection.getInventoryManager().addplayer(invetoryPlayer);
    }

    public void OpenHelpRules(InvetoryPlayer invetoryPlayer){
        Player player = invetoryPlayer.getPlayer();
        invetoryPlayer.setSection(InvetorySection.HELP);
        Inventory inv = Bukkit.createInventory(null, 27, "ayuda");
        ArrayList<String> lore = new ArrayList<>();

        for (int i = 0; 7 > i; i++){
            lore.addAll(Utils.StringToLoreString("", true));
            Utils.NewitemInvetory(MasterMessageLocated(player, Messages.Rule_Title).replace("%#%", String.valueOf(i)), Material.BARRIER, i, inv,
                    player,Utils.StringToLoreString(MasterMessageLocated(player ,Messages.valueOf("Rule_" + i)), true));
            lore.clear();
        }
        Utils.NewitemInvetory(Messages.Inventory_InvExit, Material.BARRIER, 26, inv, player);
        player.openInventory(inv);
        getInventoryManager().addplayer(invetoryPlayer);
    }

    public void OpenHelpInfo(InvetoryPlayer invetoryPlayer){
        Player player = invetoryPlayer.getPlayer();
        invetoryPlayer.setSection(InvetorySection.HELP_INFO);
        Inventory inv = Bukkit.createInventory(null, 27, "ayuda");
        String lore = "Ping: %ping% (En relación google.com)\n" +
                "Temperatura: %temp% C° \n" +
                "CPU: algo\n" +
                "RAM: algo\n" +
                "ROM: algo\n";
        Utils.NewitemInvetory("Hardware/Sistema", Material.COMMAND_BLOCK_MINECART, 10, inv, player, Utils.StringToLoreString(lore , true));
        lore = Colorinfo + "El plugin xBxT Core es un plugin privado encargado del funcionamiento principal del servidor este se encargar de casi todo del servidor como las traducciones, inventarios " +
                "baneos y entre muchas cosas más. el motivo de su existencia es para tener un control absoluto del servidor y no estar limitado a los plugins de terceros " +
                "aún que hay plugins que son necesarios pero estos tiene un api que maneja XBXT Core";
        Utils.NewitemInvetory("xBxT Core", Material.REDSTONE_BLOCK, 14, inv, player, Utils.StringToLoreString(lore , true));
        Utils.NewitemInvetory(Messages.Inventory_InvExit, Material.BARRIER, 26, inv, player);
        player.openInventory(inv);
        getInventoryManager().addplayer(invetoryPlayer);
    }

}
