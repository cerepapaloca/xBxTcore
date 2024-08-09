package Plugin.Inventory;

import Plugin.Managers.InventoryManager;
import Plugin.Model.InvetoryPlayer;
import Plugin.Model.InvetorySection;
import Plugin.Model.Messages;
import Plugin.Utils.Tools;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

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
            inv = Bukkit.createInventory(null, 27, xBxTcore.getMessageManager().MasterMessage(player, Messages.KitMenu));
            SlotENDER_CHEST = 10;
            SlotCHEST = 7 + 9;
            SlotMINECART = 4 + 9;
        }else{
            inv = Bukkit.createInventory(null, 9, xBxTcore.getMessageManager().MasterMessage(player, Messages.KitMenu));
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
        meta.setDisplayName(xBxTcore.getMessageManager().MasterMessage(invetoryplayer.getPlayer(), Messages.InvGlobal));
        ENDER_CHEST.setItemMeta(meta);
        inv.setItem(SlotENDER_CHEST,ENDER_CHEST);
        /////////////////////////////////////////
        getPlayerFileManager().loadNameKit(player.getUniqueId());
        ItemStack MINECART = new ItemStack(Material.MINECART);
        meta = MINECART.getItemMeta();
        assert meta != null;
        if (invetoryplayer.getPlayer().getWorld().equals(Bukkit.getWorld("lobby"))) {
            meta.setDisplayName(xBxTcore.getMessageManager().MasterMessage(invetoryplayer.getPlayer(), Messages.TpCreatorKits));
        } else {
            meta.setDisplayName(xBxTcore.getMessageManager().MasterMessage(invetoryplayer.getPlayer(), Messages.TpLobby));
        }
        MINECART.setItemMeta(meta);

        if(!getPlayerFileManager().nameskits.isEmpty()) {
            ItemStack CHEST = new ItemStack(Material.CHEST);
            meta = CHEST.getItemMeta();
            assert meta != null;
            meta.setDisplayName(xBxTcore.getMessageManager().MasterMessage(invetoryplayer.getPlayer(), Messages.InvCustom));
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
            inv = Bukkit.createInventory(null, 54, xBxTcore.getMessageManager().MasterMessage(invetoryplayer.getPlayer(), Messages.KitListBedrock));
            MaxKits = 45;
        }else{
            inv = Bukkit.createInventory(null, 36, xBxTcore.getMessageManager().MasterMessage(invetoryplayer.getPlayer(), Messages.KitList));
            MaxKits = 27;
        }

        Player player = invetoryplayer.getPlayer();
        ////////////////////////////////////////////////////
        ItemStack ARROW = new ItemStack(Material.ARROW);
        itemMeta = ARROW.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(xBxTcore.getMessageManager().MasterMessage(player, Messages.Next));
        ARROW.setItemMeta(itemMeta);
        ///////////////////////////////////////////////////
        ItemStack ARROW_BACK = new ItemStack(Material.ARROW);
        ItemMeta backMeta = ARROW_BACK.getItemMeta();
        assert backMeta != null;
        backMeta.setDisplayName(xBxTcore.getMessageManager().MasterMessage(player, Messages.Previous));
        ARROW_BACK.setItemMeta(backMeta);
        ///////////////////////////////////////////////////
        ItemStack BARRIER = new ItemStack(Material.BARRIER);
        ItemMeta BARRIERMeta = BARRIER.getItemMeta();
        assert BARRIERMeta != null;
        BARRIERMeta.setDisplayName(xBxTcore.getMessageManager().MasterMessage(player, Messages.InvExit));
        BARRIER.setItemMeta(BARRIERMeta);
        ///////////////////////////////////////////////////
        ItemStack STRUCTURE_VOID;
        if(invetoryplayer.getPlayer().getName().contains(bedrockPrefix)){
            STRUCTURE_VOID = new ItemStack(Material.FEATHER);
        }else{
            STRUCTURE_VOID = new ItemStack(Material.STRUCTURE_VOID);
        }

        itemMeta = STRUCTURE_VOID.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(xBxTcore.getMessageManager().MasterMessage(player, Messages.InvClear));
        STRUCTURE_VOID.setItemMeta(itemMeta);
        ///////////////////////////////////////////////////
        xBxTcore.getPlayerFileManager().loadNameKit(invetoryplayer.getuuidkit());
        int posicion = 0;
        int start = page * MaxKits;
        int end = Math.min(start + MaxKits, getPlayerFileManager().nameskits.size());

        if(invetoryplayer.getPlayer().getName().contains(bedrockPrefix)){
            if (end < getPlayerFileManager().nameskits.size()) {
                inv.setItem(53, ARROW);
            }

            inv.setItem(48, STRUCTURE_VOID);
            ItemMeta Meta;
            if (invetoryplayer.getPreviewMode()){
                Meta = ButtoPreviewOn.getItemMeta();
                assert Meta != null;
                Meta.setDisplayName(xBxTcore.getMessageManager().MasterMessage(invetoryplayer.getPlayer(),Messages.PreviewOn));
                ButtoPreviewOn.setItemMeta(Meta);
                inv.setItem(50, ButtoPreviewOn);
            }else{
                Meta = ButtoPreviewOff.getItemMeta();
                assert Meta != null;
                Meta.setDisplayName(xBxTcore.getMessageManager().MasterMessage(invetoryplayer.getPlayer(),Messages.PreviewOff));
                ButtoPreviewOff.setItemMeta(Meta);
                inv.setItem(50, ButtoPreviewOff);
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

            if (end < getPlayerFileManager().nameskits.size()) {
                inv.setItem(35, ARROW);
            }

            inv.setItem(31, STRUCTURE_VOID);

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
            lore.add(ChatColor.translateAlternateColorCodes(' '," 8" + xBxTcore.getMessageManager().MasterMessage(player, Messages.lore) + nameskit));
            kitmeta.setLore(lore);
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
        Inventory inv = Bukkit.createInventory(null, 54, xBxTcore.getMessageManager().MasterMessage(player, Messages.InvPreview));
        player.openInventory(inv);
        getPlayerFileManager().loadKit(invetoryplayer.getuuidkit(), namekit, inv, invetoryplayer.getPlayer());
        getInvetoryManager().addplayer(invetoryplayer);
    }

    public void OpenItemframe(Player player, ItemStack item) {
        Inventory inv;
        if(player.getName().contains(".")){
            inv = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', xBxTcore.getMessageManager().MasterMessage(player, Messages.InvItemFrame)));
        }else{
            inv = Bukkit.createInventory(null, 18, ChatColor.translateAlternateColorCodes('&', xBxTcore.getMessageManager().MasterMessage(player, Messages.InvItemFrame)));
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
        Inventory inv = Bukkit.createInventory(null, 27, xBxTcore.getMessageManager().MasterMessage(player, Messages.KitMenu));
        ///////////////////////////////////////////////////
        if (xBxTcore.getPlayerDataUnique(player.getUniqueId()) != null){
            for (Player p : xBxTcore.getPlayerDataUnique(player.getUniqueId()).getGuestPlayers(false)){
                lore.add(p.getName());
            }
        }
        Tools.NewitemInvetory(Messages.InvExit, Material.BARRIER, 10, inv, player, lore);
        ///////////////////////////////////////////////////
        lore.clear();

        if (xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getKitData().getName() != null){
            lore.add(xBxTcore.getPlayerDataUnique(invetoryPlayer.getPlayer().getUniqueId()).getKitData().getName());
        }
        Tools.NewitemInvetory(Messages.InvExit, Material.EMERALD_BLOCK, 26, inv, player);
        ///////////////////////////////////////////////////
        Tools.NewitemInvetory(Messages.SpamCommand, Material.WHITE_SHULKER_BOX, 11, inv, player, lore);
        ///////////////////////////////////////////////////
        player.openInventory(inv);
        getInvetoryManager().addplayer(invetoryPlayer);

    }
}
