package Plugin;

import Plugin.Apis.DDNS_NameCheap;
import Plugin.BoxPvp.BoxPvpSection;
import Plugin.CombatLog.CombatSection;
import Plugin.CombatLog.Listener.CombatlogListener;
import Plugin.Commands.CommandSection;
import Plugin.Duel.DuelSection;
import Plugin.Environments.*;
import Plugin.File.FileManagerSection;
import Plugin.File.PlayerData.PlayerfileManager;
import Plugin.Inventory.InventorySection;
import Plugin.Messages.Enum.Messages;
import Plugin.Messages.MessageSection;
import Plugin.Messages.MessageManager;
import Plugin.Duel.Model.PlayerDataUnique;
import Plugin.Placeholder.HealthPlaceholder;
import Plugin.PlayerManager.Listener.BlockerListener;
import Plugin.PlayerManager.PlayerManagerSection;
import Plugin.Security.SecuritySection;
import Plugin.Utils.Utils;
import Plugin.Utils.UtilsMain;
import Plugin.Vote.VoteSection;
import ac.grim.grimac.api.GrimAbstractAPI;
import com.onarandombox.MultiverseCore.MultiverseCore;
import fr.xephi.authme.AuthMe;
import fr.xephi.authme.api.v3.AuthMeApi;
import me.neznamy.tab.api.TabAPI;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import net.md_5.bungee.api.ChatColor;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static Plugin.Apis.DDNS_NameCheap.updateIP;
import static Plugin.File.FileManagerSection.getPlayerFileManager;
import static Plugin.Messages.MessageManager.*;

public final class xBxTcore extends JavaPlugin {

    private static PlayerfileManager playerfileManager;
    private static MultiverseCore multiverseCore;
    private static TabAPI tabAPI;
    private static LuckPerms luckPerms;
    public static xBxTcore plugin;
    public static GrimAbstractAPI grimAPI;
    public static final String worldBoxPvp = "boxpvp";

    public static String bedrockPrefix = ".";

    private long serverStartTime;

    public static ArrayList<World> worlds;
    public static ArrayList<String> playersOffline;
    private static final List<Section> sections = new ArrayList<>();

    @Override
    public void onEnable() {
        long timeStaringtotal = System.currentTimeMillis();
        plugin = this;
        OtherRegister();
        APIs();
        register(new FileManagerSection(this));
        register(new PlayerManagerSection(this));
        register(new MessageSection(this));
        register(new InventorySection(this));
        register(new CommandSection(this));
        register(new EnvironmentsSection(this));
        register(new BoxPvpSection(this));
        register(new UtilsMain(this));
        register(new DuelSection(this));
        register(new CombatSection(this));
        register(new VoteSection(this));
        register(new SecuritySection(this));



        serverStartTime = System.currentTimeMillis();
        for (Section section : sections) {
            try {
                section.enable();
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorError + "Error al Cargar " + section.getName() + " servidor requiere reinicio"));
                e.printStackTrace();
            }
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', ColorSuccess + "xBxTcore Cargado " + Colorplayer + (System.currentTimeMillis() - timeStaringtotal) + "ms"));
        MessageON();
    }

    public void onDisable() {
        for (Location loc : BlockerListener.blockLocations){
            loc.getBlock().setType(Material.AIR);
        }

        for (Player p : Objects.requireNonNull(Bukkit.getWorld(worldBoxPvp)).getPlayers()) {
            getPlayerFileManager().SaveInventoryBoxPvp(p.getUniqueId(), Utils.getItensInvetory(p));
        }
        MessageOFF();
        HandlerList.unregisterAll(this);
        sections.forEach(Section::disable);
        sections.clear();
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
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7   ╔═══════════════════════════════════════════════════════════════════════════╗"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7   ║                     &fxBxT Core Las Bases Del Servidor&7                      ║"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7   ╚═══════════════════════════════════════════════════════════════════════════╝"));
        Bukkit.getConsoleSender().sendMessage("");
        if (Utils.isRunningAsAdmin()){
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ColorSuccess +  "FireWall Activo"));
        }else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ColorWarning +  "FireWall Desactivado"));
        }
    }

    private void MessageOFF(){
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',  Colorinfo + "XBXTPVP.XYZ " + Colorplayer + "OFF"));
    }

    private void register(Section section) {
        if (getSectionByName(section.getName()) != null)
            throw new IllegalArgumentException("Section has already been registered " + section.getName());
        sections.add(section);
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', Colorinfo + section.getName() + ColorSuccess + " Ok"));
    }

    public void register(Listener... listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }

    public Section getSectionByName(String name) {
        return sections.stream().filter(s -> s.getName().equals(name)).findFirst().orElse(null);
    }

    public void APIs(){
        multiverseCore = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
        tabAPI = TabAPI.getInstance();
        Bukkit.getServer().getPluginManager().getPlugin("Votifier");
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            luckPerms = provider.getProvider();
        }
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new HealthPlaceholder(this).register();
        } else {
            getLogger().warning("PlaceholderAPI no está instalado. El plugin no funcionará correctamente.");
        }

        RegisteredServiceProvider<GrimAbstractAPI> provider1 = Bukkit.getServicesManager().getRegistration(GrimAbstractAPI.class);
        if (provider1 != null) {
            grimAPI = provider1.getProvider();
        }
    }

    private void OtherRegister(){
        worlds = new ArrayList<>();
        playersOffline = new ArrayList<>();
        WorldProtec();
        Timeinfo();
        startAutoCleaner();
        TimeAutoSafeInventory();
        String ip = "?";
        try {
            ip = DDNS_NameCheap.getPrivateIP();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (ip != null && !ip.equals("192.168.1.4")) {
            AutoUpdateDNS();
        }

    }

    public void WorldProtec() {
        worlds.add(Bukkit.getWorld("lobby"));
        worlds.add(Bukkit.getWorld("creatorkits"));
        worlds.add(Bukkit.getWorld("boxpvp"));
    }
    ///////////////////////////////////////////////////
    ///////////////////////////////////////////////////

    public static GrimAbstractAPI getGrimAPI() {
        return grimAPI;
    }

    public static xBxTcore getInstance(){
        return plugin;
    }

    public static MultiverseCore getMultiverseCore() {
        return multiverseCore;
    }

    public static ArrayList<World> getWorldProtec(){
        return worlds;
    }

    public static TabAPI getTabAPI(){
        return tabAPI;
    }

    public static LuckPerms getLuckPerms(){
        return luckPerms;
    }

    BukkitTask task1minute;
    BukkitTask task5seconds;

    ///////////////////////////////////////////////////
    ///////////////////////////////////////////////////

    private void startAutoCleaner() {
        long intervalTicks =2 * 60 * 60 * 20L;
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            BroadcastMessage(Messages.Others_message1Minute);
            left1minutes();
        }, 0L, intervalTicks);
    }

    private void left1minutes() {
       task1minute = Bukkit.getScheduler().runTaskTimer(this, () -> {
           BroadcastMessage(Messages.Others_message5Seconds);
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
        BroadcastMessage(Messages.Others_messageStarCleaner);
        EnvironmentsSection.getCleaner().clearArea("lobby");
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

    private void Timeinfo() {
        Bukkit.getScheduler().runTaskTimer(this, this::info, 40 * 20,10 * 60 * 20);
    }

    private List<Player> players = new ArrayList<>();

    private void TimeAutoSafeInventory (){
        Bukkit.getScheduler().runTaskTimer(this, () -> {

            players = Objects.requireNonNull(Bukkit.getWorld(worldBoxPvp)).getPlayers();
            if (players.isEmpty()){
                return;
            }
            new BukkitRunnable() {
                int i = 0;
                public void run() {
                    if (players.size() <= i){
                        cancel();
                        return;
                    }
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorSuccess +
                            "Se guardo exitosamente el inventario del Box PvP del jugador " + Colorplayer + players.get(i).getName()));
                    getPlayerFileManager().SaveInventoryBoxPvp(players.get(i).getUniqueId(), Utils.getItensInvetory(players.get(i)));
                    i++;
                }
            }.runTaskTimer(this, 0, 2);
        }, 2 * 20,60 * 20 * 5);
    }

    private void AutoUpdateDNS (){
        new BukkitRunnable() {
            public void run() {
                try {
                    updateIP();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }.runTaskTimer(this, 20*60*10, 20*60*60);
    }
}
