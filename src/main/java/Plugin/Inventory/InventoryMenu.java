package Plugin.Inventory;

import Plugin.Managers.InventoryManager;
import Plugin.Model.Player.InvetoryPlayer;
import Plugin.Enum.InvetorySection;
import Plugin.Enum.Messages;
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

import static Plugin.Managers.MessageManager.Colorinfo;
import static Plugin.Managers.MessageManager.Colorplayer;
import static Plugin.xBxTcore.*;

public class InventoryMenu extends InventoryManager {

    public InventoryMenu(xBxTcore plugin) {
        super(plugin);
    }

    public void OpenMenuInvetory(InvetoryPlayer invetoryplayer) {
        invetoryplayer.setPreviewMode(false);
        Player player = invetoryplayer.getPlayer();
        invetoryplayer.setSection(InvetorySection.MENU);
        ItemMeta meta;
        Inventory inv;
        int SlotENDER_CHEST;
        int SlotCHEST;
        int SlotMINECART;
        if (invetoryplayer.getPlayer().getName().contains(bedrockPrefix)) {
            inv = Bukkit.createInventory(null, 27, xBxTcore.getMessageManager().MasterMessageLocated(player, Messages.KitMenu));
            SlotENDER_CHEST = 10;
            SlotCHEST = 7 + 9;
            SlotMINECART = 4 + 9;
        }else{
            inv = Bukkit.createInventory(null, 9, xBxTcore.getMessageManager().MasterMessageLocated(player, Messages.KitMenu));
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
        meta.setDisplayName(xBxTcore.getMessageManager().MasterMessageLocated(invetoryplayer.getPlayer(), Messages.InvGlobal));
        ENDER_CHEST.setItemMeta(meta);
        inv.setItem(SlotENDER_CHEST,ENDER_CHEST);
        /////////////////////////////////////////
        getPlayerFileManager().loadNameKit(player.getUniqueId());
        ItemStack MINECART = new ItemStack(Material.MINECART);
        meta = MINECART.getItemMeta();
        assert meta != null;
        if (invetoryplayer.getPlayer().getWorld().equals(Bukkit.getWorld("lobby"))) {
            meta.setDisplayName(xBxTcore.getMessageManager().MasterMessageLocated(invetoryplayer.getPlayer(), Messages.TpCreatorKits));
        } else {
            meta.setDisplayName(xBxTcore.getMessageManager().MasterMessageLocated(invetoryplayer.getPlayer(), Messages.TpLobby));
        }
        MINECART.setItemMeta(meta);

        if(!getPlayerFileManager().nameskits.isEmpty()) {
            ItemStack CHEST = new ItemStack(Material.CHEST);
            meta = CHEST.getItemMeta();
            assert meta != null;
            meta.setDisplayName(xBxTcore.getMessageManager().MasterMessageLocated(invetoryplayer.getPlayer(), Messages.InvCustom));
            CHEST.setItemMeta(meta);
            inv.setItem(SlotCHEST, CHEST);
            inv.setItem(SlotMINECART,MINECART);
        }else{
            inv.setItem(SlotCHEST,MINECART);
        }
        /////////////////////////////////////////
        player.openInventory(inv);
        getInvetoryManager().addplayer(invetoryplayer);
    }

    public void OpenInvetoryKitsList(InvetoryPlayer invetoryplayer, int page) {
        ItemMeta itemMeta;
        invetoryplayer.setSection(InvetorySection.MENUKITS);
        Inventory inv;
        int MaxKits;
        if (invetoryplayer.getPlayer().getName().contains(bedrockPrefix)) {
            inv = Bukkit.createInventory(null, 54, xBxTcore.getMessageManager().MasterMessageLocated(invetoryplayer.getPlayer(), Messages.KitListBedrock));
            MaxKits = 45;
        }else{
            inv = Bukkit.createInventory(null, 36, xBxTcore.getMessageManager().MasterMessageLocated(invetoryplayer.getPlayer(), Messages.KitList));
            MaxKits = 27;
        }

        Player player = invetoryplayer.getPlayer();
        ////////////////////////////////////////////////////
        ItemStack ARROW = new ItemStack(Material.ARROW);
        itemMeta = ARROW.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(xBxTcore.getMessageManager().MasterMessageLocated(player, Messages.Next));
        ARROW.setItemMeta(itemMeta);
        ///////////////////////////////////////////////////
        ItemStack ARROW_BACK = new ItemStack(Material.ARROW);
        ItemMeta backMeta = ARROW_BACK.getItemMeta();
        assert backMeta != null;
        backMeta.setDisplayName(xBxTcore.getMessageManager().MasterMessageLocated(player, Messages.Previous));
        ARROW_BACK.setItemMeta(backMeta);
        ///////////////////////////////////////////////////
        ItemStack BARRIER = new ItemStack(Material.BARRIER);
        ItemMeta BARRIERMeta = BARRIER.getItemMeta();
        assert BARRIERMeta != null;
        BARRIERMeta.setDisplayName(xBxTcore.getMessageManager().MasterMessageLocated(player, Messages.InvExit));
        BARRIER.setItemMeta(BARRIERMeta);
        ///////////////////////////////////////////////////
        ItemStack CHEST_MINECART;
        Messages messages;
        if (invetoryplayer.getuuidkit().equals(invetoryplayer.getPlayer().getUniqueId())){
            CHEST_MINECART = new ItemStack(Material.ENDER_CHEST);
            messages = Messages.InvGlobal;
        }else{
            CHEST_MINECART = new ItemStack(Material.CHEST);
            messages = Messages.InvCustom;
        }
        ItemMeta CHEST_MINECARTmeta = CHEST_MINECART.getItemMeta();
        assert CHEST_MINECARTmeta != null;
        CHEST_MINECARTmeta.setDisplayName(xBxTcore.getMessageManager().MasterMessageLocated(player, messages));
        CHEST_MINECART.setItemMeta(CHEST_MINECARTmeta);
        ///////////////////////////////////////////////////


        ItemStack STRUCTURE_VOID;
        if(invetoryplayer.getPlayer().getName().contains(bedrockPrefix)){
            STRUCTURE_VOID = new ItemStack(Material.FEATHER);
        }else{
            STRUCTURE_VOID = new ItemStack(Material.STRUCTURE_VOID);
        }
        ///////////////////////////////////////////////////
        xBxTcore.getPlayerFileManager().loadNameKit(invetoryplayer.getuuidkit());
        int posicion = 0;
        int start = page * MaxKits;
        int end = Math.min(start + MaxKits, getPlayerFileManager().nameskits.size());

        if(invetoryplayer.getPlayer().getName().contains(bedrockPrefix)){
            if (end < getPlayerFileManager().nameskits.size()) {
                inv.setItem(53, ARROW);
            }

            int previewslot = 0;
            itemMeta = STRUCTURE_VOID.getItemMeta();
            if (itemMeta != null){
                if (!invetoryplayer.getKitSelectMode()){
                    itemMeta.setDisplayName(xBxTcore.getMessageManager().MasterMessageLocated(player, Messages.SelectKitFavorite));
                    STRUCTURE_VOID.setItemMeta(itemMeta);
                    inv.setItem(48, STRUCTURE_VOID);
                    previewslot = 50;
                }else{
                    itemMeta.setDisplayName(xBxTcore.getMessageManager().MasterMessageLocated(player, Messages.InvClear));
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
                Meta.setDisplayName(xBxTcore.getMessageManager().MasterMessageLocated(invetoryplayer.getPlayer(),Messages.PreviewOn));
                ButtoPreviewOn.setItemMeta(Meta);
                inv.setItem(previewslot, ButtoPreviewOn);
            }else{
                Meta = ButtoPreviewOff.getItemMeta();
                assert Meta != null;
                Meta.setDisplayName(xBxTcore.getMessageManager().MasterMessageLocated(invetoryplayer.getPlayer(),Messages.PreviewOff));
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
                inv.setItem(31, STRUCTURE_VOID);
            }else{
                inv.setItem(30, CHEST_MINECART);
                inv.setItem(32, STRUCTURE_VOID);
            }

            if (end < getPlayerFileManager().nameskits.size()) {
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
            String nameskit = getPlayerFileManager().nameskits.get(i);
            Material material = getPlayerFileManager().materials.get(i);
            ItemStack kit = new ItemStack(material);
            ItemMeta kitmeta = kit.getItemMeta();
            assert kitmeta != null;
            kitmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',nameskit));
            lore.add(ChatColor.translateAlternateColorCodes(' '," 8" + xBxTcore.getMessageManager().MasterMessageLocated(player, Messages.lore) + nameskit));
            kitmeta.setLore(lore);
            kitmeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            kitmeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "kitName"), PersistentDataType.STRING, nameskit);
            kit.setItemMeta(kitmeta);
            inv.setItem(posicion, kit);
            posicion++;
        }
        xBxTcore.getPlayerFileManager().loadNameKit(invetoryplayer.getuuidkit());
        //////////////////////////////////////////////////
        player.openInventory(inv);
        getInvetoryManager().addplayer(invetoryplayer);
    }

    public void OpenPreviewKit (Player player, String namekit, InvetoryPlayer invetoryplayer){
        invetoryplayer.setSection(InvetorySection.PREVIEWKITS);
        Inventory inv = Bukkit.createInventory(null, 54, xBxTcore.getMessageManager().MasterMessageLocated(player, Messages.InvPreview));
        player.openInventory(inv);
        getPlayerFileManager().loadKit(invetoryplayer.getuuidkit(), namekit, inv, invetoryplayer.getPlayer());
        getInvetoryManager().addplayer(invetoryplayer);
    }

    public void OpenItemframe(Player player, ItemStack item) {
        Inventory inv;
        if(player.getName().contains(bedrockPrefix)){
            inv = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', xBxTcore.getMessageManager().MasterMessageLocated(player, Messages.InvItemFrame)));
        }else{
            inv = Bukkit.createInventory(null, 18, ChatColor.translateAlternateColorCodes('&', xBxTcore.getMessageManager().MasterMessageLocated(player, Messages.InvItemFrame)));
        }
        for (int i=0;i<inv.getSize();i++){
            item.setAmount(item.getMaxStackSize());
            inv.setItem(i,item);
        }
        player.openInventory(inv);
    }

    public void OpenDuel(InvetoryPlayer invetoryPlayer){
        invetoryPlayer.setSection(InvetorySection.MENUDUEL);
        Player player = invetoryPlayer.getPlayer();
        ArrayList<String> lore = new ArrayList<>();
        Inventory inv = Bukkit.createInventory(null, 27, xBxTcore.getMessageManager().MasterMessageLocated(player, Messages.KitMenu));
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
            lore.add(xBxTcore.getMessageManager().MasterMessageLocated(player, Messages.DuelLoreInvPlayers));
            for (Player p : xBxTcore.getPlayerDataUnique(player.getUniqueId()).getGuestPlayers(false)){
                if(!p.getName().equals(player.getName())){
                    lore.add(ChatColor.translateAlternateColorCodes('&', "&f&l-&r " + Colorplayer + p.getName()));
                }
            }
            if (lore.size() == 1){
                lore.clear();
                lore.add(xBxTcore.getMessageManager().MasterMessageLocated(player, Messages.DuelLoreInvPlayersEmpty));
            }
        }
        Utils.NewitemInvetory(Messages.DuelInvPlayers, Material.WRITABLE_BOOK, 10, inv, player, lore);
        ///////////////////////////////////////////////////
        lore.clear();

        if (xBxTcore.getPlayerDataUnique(player.getUniqueId()).getKitData().getName() != null){
            lore.add(ChatColor.translateAlternateColorCodes('&',xBxTcore.getMessageManager().MasterMessageLocated(player, Messages.DuelLoreSelectKit) + xBxTcore.getPlayerDataUnique(player.getUniqueId()).getKitData().getName()));
        }else{
            lore.add(xBxTcore.getMessageManager().MasterMessageLocated(player, Messages.DuelLoreSelectKitEmpty));
        }
        Utils.NewitemInvetory(Messages.DuelSelectKit, Material.CHEST_MINECART, 12, inv, player, lore);
        ///////////////////////////////////////////////////
        Utils.NewitemInvetory(Messages.DuelSendQuest, Material.BLAZE_POWDER, 22, inv, player);
        ///////////////////////////////////////////////////
        lore.clear();
        if(xBxTcore.getPlayerDataUnique(player.getUniqueId()).getTimelimit()){
            lore.add(xBxTcore.getMessageManager().MasterMessageLocated(player, Messages.DuelLoreTimeLimit));
            lore.addAll(secondsToMinutesLore(player));
        }else{
            lore.add(xBxTcore.getMessageManager().MasterMessageLocated(player, Messages.DuelLoreTimeLimitDisabled));
        }

        Utils.NewitemInvetory(Messages.DuelTimeLimit, Material.CLOCK, 14, inv, player, lore);
        ///////////////////////////////////////////////////
        player.openInventory(inv);
        getInvetoryManager().addplayer(invetoryPlayer);
        SelectMapDuel(invetoryPlayer, false);

    }

    public void OpenTimeSelect(InvetoryPlayer invetoryPlayer){
        invetoryPlayer.setSection(InvetorySection.TIMESELECT);
        Player player = invetoryPlayer.getPlayer();
        ArrayList<String> lore = new ArrayList<>();
        Inventory inv = Bukkit.createInventory(null, 27, xBxTcore.getMessageManager().MasterMessageLocated(player, Messages.KitMenu));
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
        Utils.NewitemInvetory(Messages.H1menos, Material.RED_STAINED_GLASS_PANE, 11, inv, player);
        Utils.NewitemInvetory(Messages.S1menos, Material.RED_STAINED_GLASS_PANE, 12, inv, player);
        ///////////////////////////////////////////////////
        Utils.NewitemInvetory(Messages.S1mas, Material.GREEN_STAINED_GLASS_PANE, 14, inv, player);
        Utils.NewitemInvetory(Messages.H1mas, Material.GREEN_STAINED_GLASS_PANE, 15, inv, player);
        ///////////////////////////////////////////////////
        Utils.NewitemInvetory(Messages.InvExit, Material.BARRIER, 22, inv, player);


        if (xBxTcore.getPlayerDataUnique(player.getUniqueId()).getTimelimit()){
            Utils.NewitemInvetory(Messages.DuelTimeLimitOn, Material.ENDER_EYE, 13, inv, player, secondsToMinutesLore(player));
        }else{
            Utils.NewitemInvetory(Messages.DuelTimeLimitOff, Material.ENDER_PEARL, 13, inv, player, secondsToMinutesLore(player));
        }
        player.openInventory(inv);
        getInvetoryManager().addplayer(invetoryPlayer);
    }

    public void OpenRewardTimes(InvetoryPlayer invetoryPlayer){
        Player player = invetoryPlayer.getPlayer();
        invetoryPlayer.setSection(InvetorySection.REWARDTIMES);
        Inventory inv;
        if (invetoryPlayer.getPlayer().getName().contains(bedrockPrefix)) {
            inv = Bukkit.createInventory(null, 54, xBxTcore.getMessageManager().MasterMessageLocated(player, Messages.KitListBedrock));
        }else{
            inv = Bukkit.createInventory(null, 45, xBxTcore.getMessageManager().MasterMessageLocated(player, Messages.KitList));
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
                xBxTcore.getPlayerFileManager().loadTimesRewords(invetoryPlayer.getPlayer().getUniqueId());
                lore.add(Utils.SecondToMinutes(getPlayerFileManager().daily - System.currentTimeMillis()));
                if (getPlayerFileManager().daily <= System.currentTimeMillis()){
                    Utils.NewitemInvetory(Messages.Daily, Material.CHEST_MINECART, 20, inv, player);
                }else{
                    Utils.NewitemInvetory(Messages.Daily, Material.MINECART, 20, inv, player, lore);
                }
                lore.clear();
                lore.add(ChatColor.translateAlternateColorCodes('&',Colorinfo + Utils.SecondToMinutes(getPlayerFileManager().weekly - System.currentTimeMillis())));
                if (getPlayerFileManager().weekly <= System.currentTimeMillis()){
                    Utils.NewitemInvetory(Messages.Weekly, Material.CHEST_MINECART, 22, inv, player);
                }else{
                    Utils.NewitemInvetory(Messages.Weekly, Material.MINECART, 22, inv, player, lore);
                }
                lore.clear();
                lore.add(ChatColor.translateAlternateColorCodes('&',Colorinfo + Utils.SecondToMinutes(getPlayerFileManager().monthly - System.currentTimeMillis())));
                if (getPlayerFileManager().monthly <= System.currentTimeMillis()){
                    Utils.NewitemInvetory(Messages.Monthly, Material.CHEST_MINECART, 24, inv, player);
                }else{
                    Utils.NewitemInvetory(Messages.Monthly, Material.MINECART, 24, inv, player, lore);
                }
                lore.clear();
            }
        }.runTaskTimer(plugin, 0, 20);
        player.openInventory(inv);
        getInvetoryManager().addplayer(invetoryPlayer);
    }
}
