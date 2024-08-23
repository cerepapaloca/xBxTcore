package Plugin;

import Plugin.Commands.OnlyOp.*;
import Plugin.Commands.Tab.*;
import Plugin.Commands.User.*;
import Plugin.Environments.*;
import Plugin.Inventory.InventoryMenu;
import Plugin.Listeners.Invetory.ArmorBonusListener;
import Plugin.Listeners.Invetory.InventoryListener;
import Plugin.Listeners.Invetory.ItemframeListener;
import Plugin.Listeners.Invetory.ShulkerBoxInventoryListener;
import Plugin.Listeners.*;
import Plugin.Managers.*;
import Plugin.Enum.Messages;
import Plugin.Model.Player.PlayerDataGLobal;
import Plugin.Model.Player.PlayerDataUnique;
import Plugin.Utils.ColorUtils;
import Plugin.Utils.Utils;
import com.onarandombox.MultiverseCore.MultiverseCore;
import com.vexsoftware.votifier.NuVotifierBukkit;
import me.neznamy.tab.api.TabAPI;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static Plugin.Managers.MessageManager.*;

public class xBxTcore extends JavaPlugin {

    private static PlayerfileManager playerfileManager;
    private static Cleaner cleaner;
    private static MultiverseCore multiverseCore;
    private static TabAPI tabAPI;
    private static NuVotifierBukkit nuVotifier;
    private static LuckPerms luckPerms;
    private static CombatlogListener combatlogListener;
    private static DuelManager duelManager;
    private static CommandDuel commandDuel;
    private static MessageManager messageManager;
    private static AutoFillsBox autoFillsBox;
    private static InventoryMenu inventoryMenu;
    private static HologramasBoxPvp hologramasBoxPvp;
    private static ZoneSafeBoxPvp zoneSafeBoxPvp;
    private static Utils tools;
    private static ItemManage itemManage;
    public static Hologramas hologramas;
    public static PlayerDataGLobal playerDataGLobal;
    public static InventoryManager invetoryManager;


    public static String bedrockPrefix = ".";


    private long serverStartTime;
    private long timeStaring;

    public static ArrayList<World> worlds;
    public static ArrayList<String> playersOffline;

    @Override
    public void onEnable() {
        timeStaring = System.currentTimeMillis();
        playerDataGLobal = new PlayerDataGLobal();
        cleaner = new Cleaner(this);
        worlds = new ArrayList<>();
        playersOffline = new ArrayList<>();
        UtilsRegister();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo + "Utils Cargado " + Colorplayer + (System.currentTimeMillis() - timeStaring) + "ms"));
        timeStaring = System.currentTimeMillis();
        APIs();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo + "Apis Cargado " + Colorplayer + (System.currentTimeMillis() - timeStaring) + "ms"));
        timeStaring = System.currentTimeMillis();
        ManagersRegister();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo + "Managers Cargado " + Colorplayer + (System.currentTimeMillis() - timeStaring) + "ms"));
        timeStaring = System.currentTimeMillis();
        CommandRegister();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo + "Comandos Cargado " + Colorplayer + (System.currentTimeMillis() - timeStaring)+ "ms"));
        timeStaring = System.currentTimeMillis();
        ListenersRegister();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo + "Listener Cargado " + Colorplayer + (System.currentTimeMillis() - timeStaring) + "ms"));
        timeStaring = System.currentTimeMillis();
        startAutoCleaner();
        Timeinfo();
        WorldProtec();
        MessageON();
        serverStartTime = System.currentTimeMillis();
    }

    public void onDisable() {
        for (Location loc : BlockerListener.blockLocations){
            loc.getBlock().setType(Material.AIR);
        }

        for (Player p : Objects.requireNonNull(Bukkit.getWorld("boxpvp")).getPlayers()) {
            xBxTcore.getPlayerFileManager().SaveInventoryBoxPvp(p.getUniqueId(), Utils.getItensInvetory(p));
        }
        MessageOFF();
    }

    private void MessageON(){
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8 ___    ___&8 ________   &f  ___    ___&f _________    &e    ________  ___      ___ ________"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8|\\  \\  /  /&8|\\   __  \\  &f |\\  \\  /  /|&f\\___   ___\\     &e|\\   __  \\|\\  \\    /  /|\\   __  \\ "));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8\\ \\  \\/  / &8| \\  \\|\\ /_  &f\\ \\  \\/  / |&f\\___ \\  \\_|   &e  \\ \\  \\|\\  \\ \\  \\  /  / | \\  \\|\\  \\"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8 \\ \\    / / &8\\ \\   __  \\  &f\\ \\    / /     &f\\ \\  \\     &e  \\ \\   ____\\ \\  \\/  / / \\ \\   ____\\"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8  /     \\/   &8\\ \\  \\|\\  \\  &f/     \\/      &f \\ \\  \\    &e   \\ \\  \\___|\\ \\    / /   \\ \\  \\___|"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8 /  /\\   \\    &8\\ \\_______\\&f/  /\\   \\      &f  \\ \\__\\   &e    \\ \\__\\    \\ \\__/ /     \\ \\__\\  "));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8/__/ /\\ __\\    &8\\|_______&f/__/ /\\ __\\       &f \\|__|       &e \\|__|     \\|__|/       \\|__|   "));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8|__|/ \\|__|       &8      &f|__|/ \\|__| "));

    }

    private void MessageOFF(){
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',  Colorinfo + "XBXTPVP.XYZ " + Colorplayer + "OFF"));
    }

    public void CommandRegister(){
        Objects.requireNonNull(this.getCommand("kit")).setExecutor(new CommandKit(this));
        Objects.requireNonNull(this.getCommand("savekit")).setExecutor(new CommandSaveKit(this));
        Objects.requireNonNull(this.getCommand("delkit")).setExecutor(new CommandDelKit(this));
        Objects.requireNonNull(this.getCommand("kitfavorite")).setExecutor(new CommandKitFavorite(this));
        Objects.requireNonNull(this.getCommand("Cleaner")).setExecutor(new CommandCleaner());
        Objects.requireNonNull(this.getCommand("info")).setExecutor(new Commandinfo(this));
        Objects.requireNonNull(this.getCommand("lobby")).setExecutor(new CommandLobby(this));
        Objects.requireNonNull(this.getCommand("help")).setExecutor(new CommandHelp(this));
        Objects.requireNonNull(this.getCommand("kill")).setExecutor(new CommandKill(this));
        Objects.requireNonNull(this.getCommand("duel")).setExecutor(commandDuel = new CommandDuel(this));
        Objects.requireNonNull(this.getCommand("discord")).setExecutor(new CommandDiscord(this));
        Objects.requireNonNull(this.getCommand("spectator")).setExecutor(new CommandSpectator(this));
        Objects.requireNonNull(this.getCommand("ping")).setExecutor(new CommandPing());
        Objects.requireNonNull(this.getCommand("debugkit")).setExecutor(new CommandDebugKit());
        Objects.requireNonNull(this.getCommand("rank")).setExecutor(new CommandRank(this));
        Objects.requireNonNull(this.getCommand("vote")).setExecutor(new CommandVote(this));
        Objects.requireNonNull(this.getCommand("donate")).setExecutor(new CommandDonate(this));
        Objects.requireNonNull(this.getCommand("prefix")).setExecutor(new CommandPrefix(this));
        Objects.requireNonNull(this.getCommand("inv")).setExecutor(new CommandInv(this));
        Objects.requireNonNull(this.getCommand("boxpvp")).setExecutor(new CommandBoxPvp(this));
        Objects.requireNonNull(this.getCommand("ReloadDataPlayares")).setExecutor(new CommandReloadDataPlayares(this));
        Objects.requireNonNull(this.getCommand("itemboxpvp")).setExecutor(new CommandItemBoxpvp(this));
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        Objects.requireNonNull(this.getCommand("sk")).setExecutor(new CommandSaveKit(this));
        Objects.requireNonNull(this.getCommand("dk")).setExecutor(new CommandDelKit(this));
        Objects.requireNonNull(this.getCommand("kf")).setExecutor(new CommandKitFavorite(this));
        Objects.requireNonNull(this.getCommand("spawn")).setExecutor(new CommandLobby(this));
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        Objects.requireNonNull(getCommand("savekit")).setTabCompleter(new TabCompleterSaveKit());
        Objects.requireNonNull(getCommand("delKit")).setTabCompleter(new TabCompleterDelKit());
        Objects.requireNonNull(getCommand("kitfavorite")).setTabCompleter(new TabCompleterKitFavorite());
        Objects.requireNonNull(getCommand("duel")).setTabCompleter(new TabCompleterDuel());
        Objects.requireNonNull(getCommand("spectator")).setTabCompleter(new TabCompleterSpectator());
        Objects.requireNonNull(getCommand("debugkit")).setTabCompleter(new TabCompleterDebugKit());
        Objects.requireNonNull(getCommand("itemboxpvp")).setTabCompleter(new TabItemBoxpvp());
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        Objects.requireNonNull(getCommand("sk")).setTabCompleter(new TabCompleterSaveKit());
        Objects.requireNonNull(getCommand("dk")).setTabCompleter(new TabCompleterDelKit());
        Objects.requireNonNull(getCommand("kf")).setTabCompleter(new TabCompleterKitFavorite());
    }

    public void ListenersRegister(){
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        getServer().getPluginManager().registerEvents(new ItemframeListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockerListener(this ),this);
        getServer().getPluginManager().registerEvents(new VoteListener(),this);
        getServer().getPluginManager().registerEvents(new RewardsListener(),this);
        getServer().getPluginManager().registerEvents(new MessageDiedListener(), this);
        getServer().getPluginManager().registerEvents(new ShulkerBoxInventoryListener(), this);
        getServer().getPluginManager().registerEvents(new ArmorBonusListener(this), this);
        getServer().getPluginManager().registerEvents(combatlogListener = new CombatlogListener(this),this);
    }

    public void APIs(){
        multiverseCore = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
        tabAPI = TabAPI.getInstance();
        nuVotifier = (NuVotifierBukkit) Bukkit.getServer().getPluginManager().getPlugin("Votifier");
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            luckPerms = provider.getProvider();
        }
    }

    public void ManagersRegister(){
        playerfileManager = new PlayerfileManager(this);
        duelManager = new DuelManager(this);
        messageManager = new MessageManager();
        inventoryMenu = new InventoryMenu();
        //invetoryManager = new InventoryManager(this);
        //hologramas = new Hologramas(this);
        hologramasBoxPvp = new HologramasBoxPvp(this);
        autoFillsBox = new AutoFillsBox(this);
        itemManage = new ItemManage(this);
        zoneSafeBoxPvp = new ZoneSafeBoxPvp();
    }

    public void UtilsRegister(){
        tools = new Utils(this);
        new ColorUtils();
    }

    public void WorldProtec(){
        worlds.add(Bukkit.getWorld("lobby"));
        worlds.add(Bukkit.getWorld("creatorkits"));
        worlds.add(Bukkit.getWorld("boxpvp"));
    }

    ///////////////////////////////////////////////////
    ///////////////////////////////////////////////////

    public static Cleaner getCleaner() {
        return cleaner;
    }

    public static InventoryManager getInvetoryManager() {
        return invetoryManager;
    }

    public static PlayerfileManager getPlayerFileManager() {
        return playerfileManager;
    }

    public static MultiverseCore getMultiverseCore() {
        return multiverseCore;
    }

    public static Hologramas getHologramas() {
        return hologramas;
    }

    public static CombatlogListener getcombatlogListener(){
        return combatlogListener;
    }

    public static DuelManager getDuelManager() {
        return duelManager;
    }

    public static CommandDuel getCommandDuel(){
        return commandDuel;
    }

    public static ArrayList<World> getWorldProtec(){
        return worlds;
    }

    public static MessageManager getMessageManager() {
        return messageManager;
    }

    public static PlayerDataGLobal getPlayerDataGlobal(){
        return playerDataGLobal;
    }

    public static TabAPI getTabAPI(){
        return tabAPI;
    }

    public static Utils getTools(){
        return tools;
    }

    public String getVercion(){
        return getDescription().getVersion();
    }

    public static InventoryMenu getInventoryMenu(){
        return inventoryMenu;
    }

    public static NuVotifierBukkit getNuVotifierBukkit(){
        return nuVotifier;
    }

    public static AutoFillsBox getAutoFillsBox(){
        return autoFillsBox;
    }

    public static LuckPerms getLuckPerms(){
        return luckPerms;
    }

    public static HologramasBoxPvp getHologramasBoxPvp(){
        return hologramasBoxPvp;
    }

    public static ZoneSafeBoxPvp getZoneSafeBoxPvp(){
        return zoneSafeBoxPvp;
    }

    public static ItemManage getItemManage(){
        return itemManage;
    }

    BukkitTask task1minute;
    BukkitTask task5seconds;

    ///////////////////////////////////////////////////
    ///////////////////////////////////////////////////

    private void startAutoCleaner() {
        long intervalTicks =2 * 60 * 60 * 20L;
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            messageManager.BroadcastMessage(Messages.message1Minute);
            left1minutes();
        }, 0L, intervalTicks);
    }

    private void left1minutes() {
       task1minute = Bukkit.getScheduler().runTaskTimer(this, () -> {
           messageManager.BroadcastMessage(Messages.message5Seconds);
           left5seconds();
       }, 55 * 20, 30);
    }

    private void left5seconds() {
        task5seconds = Bukkit.getScheduler().runTaskTimer(this, this::ExecuteClener, 5 * 20, 0);
        task1minute.cancel();
    }

    ///////////////////////////////////////////////////
    ///////////////////////////////////////////////////

    private void ExecuteClener(){
        messageManager.BroadcastMessage(Messages.messageStarCleaner);
        cleaner.clearArea("lobby");
        task5seconds.cancel();
    }

    public void messageOnlyPlayer(){
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.prefix + Colorinfo + "Solo jugadores Tonto"));
    }

    public void info(){
        long uptimeMillis = System.currentTimeMillis() - serverStartTime;
        long days = TimeUnit.MILLISECONDS.toDays(uptimeMillis);
        uptimeMillis -= TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(uptimeMillis);
        uptimeMillis -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(uptimeMillis);
        uptimeMillis -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(uptimeMillis);

        float memory = ((float) Runtime.getRuntime().totalMemory() / Runtime.getRuntime().freeMemory() );
        float mb = (float) (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory())/1000000;
        memory *= 10;
        if (memory < 10){
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo + "[#---------]" + memory + "% used: " + mb + "MB" + " /////////////////////"));
        } else if (memory < 20){
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo + "[###-------] " + memory + "% used:" + mb + "MB" + " /////////////////////"));
        }  else if (memory < 30){
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo + "[####------] " + memory + "% used:" + mb + "MB" + " /////////////////////"));
        } else if (memory < 40){
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo + "[#####-----] " + memory + "% used:" + mb + "MB" + " /////////////////////"));
        } else if (memory < 50){
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo + "[######----] " + memory + "% used:" + mb + "MB" + " /////////////////////"));
        } else if (memory < 60){
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo + "[#######---] " + memory + "% used:" + mb + "MB" + " /////////////////////"));
        } else if (memory < 80){
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo + "[########--] " + memory + "% used:" + mb + "MB" + " /////////////////////"));
        } else if (memory < 90){
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo + "[#########-] " + memory + "% used:" + mb + "MB" + " /////////////////////"));
        } else if (memory < 95){
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo + "[##########] " + memory + "% used:" + mb + "MB" + " /////////////////////"));
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo +
                getServer().getServerTickManager().getTickRate() + " TPS // " + Bukkit.getOnlinePlayers().size() + "/" +
                Bukkit.getServer().getMaxPlayers() + " Players // " + "Tiempo de iniciado " + seconds + "S " + minutes + "M " + hours +"H " + days + "D " ));
        Bukkit.setMotd("xBxTCore el Mejor Plugin de todos los tiempos");

    }

    private static final HashMap<UUID, PlayerDataUnique> playerDataUnique = new HashMap<>();

    public static PlayerDataUnique getPlayerDataUnique(UUID uuid){
        if (!playerDataUnique.containsKey(uuid)){
            PlayerDataUnique playerDataUnique1 = new PlayerDataUnique(uuid);
            playerDataUnique.put(uuid, playerDataUnique1);
        }
        return playerDataUnique.get(uuid);
    }

    public static void deletePlayerDataUnique(UUID uuid){
        playerDataUnique.remove(uuid);
    }

    private void Timeinfo() {
        Bukkit.getScheduler().runTaskTimer(this, this::info, 40 * 20,10 * 60 * 20);
    }
}
