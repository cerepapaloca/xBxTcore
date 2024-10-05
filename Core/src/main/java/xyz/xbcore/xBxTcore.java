package xyz.xbcore;

import xyz.xbcommun.xBxTcommon;
import xyz.xbcore.Service.DDNS_NameCheap;
import xyz.xbcore.BoxPvp.BoxPvpSection;
import xyz.xbcore.CombatLog.CombatSection;
import xyz.xbcore.Commands.CommandSection;
import xyz.xbcore.Duel.DuelSection;
import xyz.xbcore.Environment.*;
import xyz.xbcore.File.FileManagerSection;
import xyz.xbcore.Inventory.InventorySection;
import xyz.xbcommun.Messages.Messages.Messages;
import xyz.xbcore.Messages.MessageSection;
import xyz.xbcore.Duel.Model.PlayerDataRequestDuel;
import xyz.xbcore.Placeholder.Placeholder;
import xyz.xbcore.PlayerManager.Listener.BlockerListener;
import xyz.xbcore.PlayerManager.PlayerManagerSection;
import xyz.xbcore.Security.SecuritySection;
import xyz.xbcommun.Utils.ColorUtils;
import xyz.xbcommun.Utils.UtilsGlobal;
import xyz.xbcommun.Utils.UtilsGlobalSection;
import xyz.xbcore.Vote.VoteSection;
import xyz.xbcore.Service.PingRequest;
import ac.grim.grimac.api.GrimAbstractAPI;
import com.onarandombox.MultiverseCore.MultiverseCore;
import lombok.Getter;
import me.neznamy.tab.api.TabAPI;
import net.luckperms.api.LuckPerms;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import net.md_5.bungee.api.ChatColor;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static xyz.xbcommun.RegisterManager.register;
import static xyz.xbcommun.RegisterManager.startRegister;
import static xyz.xbcommun.xBxTcommon.Disable;
import static xyz.xbcommun.xBxTcommon.Enable;
import static xyz.xbcore.Service.DDNS_NameCheap.updateIP;
import static xyz.xbcommun.Messages.MessageManager.*;

public final class xBxTcore extends JavaPlugin {

    @Getter private static MultiverseCore multiverseCore;
    @Getter private static TabAPI tabAPI;
    @Getter private static LuckPerms luckPerms;
    public static xBxTcore plugin;
    public static GrimAbstractAPI grimAPI;
    public static final String worldBoxPvp = "boxpvp";

    public static String bedrockPrefix = ".";

    public long serverStartTime;

    public static ArrayList<World> worlds;


    @Override
    public void onEnable() {
        Enable(this);
        long timeStaringTotal = System.currentTimeMillis();
        plugin = this;
        OtherRegister();
        APIs();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', Colorinfo + "Registro iniciado..."));
        register(new FileManagerSection(this));
        register(new CommandSection(this));
        register(new PlayerManagerSection(this));
        register(new MessageSection());
        register(new InventorySection(this));
        register(new EnvironmentsSection(this));
        register(new BoxPvpSection(this));
        register(new UtilsGlobalSection(this));
        register(new DuelSection(this));
        register(new CombatSection(this));
        register(new VoteSection(this));
        register(new SecuritySection(this));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', ColorSuccess + "Registro completado"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',Colorinfo + "iniciando..."));
        startRegister();
        Bukkit.getScheduler().runTaskAsynchronously(plugin, DDNS_NameCheap::updateIP);
        serverStartTime = System.currentTimeMillis();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', ColorSuccess + "xBxTcore Cargado " + Colorplayer + (System.currentTimeMillis() - timeStaringTotal) + "ms"));

        switch (xBxTcommon.getSystemOperative){
            case WINDOWS -> MessageONWindows();
            case LINUX -> MessageONLinux();
        }
    }

    public void onDisable() {
        for (Location loc : BlockerListener.blockLocations){
            loc.getBlock().setType(Material.AIR);
        }

        for (Player p : Objects.requireNonNull(Bukkit.getWorld(worldBoxPvp)).getPlayers()) {
            FileManagerSection.getPlayerFileManager().SaveInventoryBoxPvp(p.getUniqueId(), UtilsGlobal.getItensInvetory(p));
        }
        HandlerList.unregisterAll(this);
        Disable();
        MessageOFF();
    }

    private void MessageONWindows(){
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
    }

    private void MessageONLinux(){
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ColorUtils.applyGradient("<#61CAFD> ___    ___ ________     ___    ___ _________   <#B378CB>",'l') + ColorUtils.applyGradient("<#FEAA41> ________  ________  ________  _______      <#FF7302>",'l')));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ColorUtils.applyGradient("<#61CAFD>|\\  \\  /  /|\\   __  \\   |\\  \\  /  /|\\___   ___\\ <#B378CB>",'l') + ColorUtils.applyGradient("<#FEAA41>|\\   ____\\|\\   __  \\|\\   __  \\|\\  ___ \\     <#FF7302>",'l')));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ColorUtils.applyGradient("<#61CAFD>\\ \\  \\/  / | \\  \\|\\ /_  \\ \\  \\/  / ||___ \\  \\_| <#B378CB>",'l') + ColorUtils.applyGradient("<#FEAA41>\\ \\  \\___|\\ \\  \\|\\  \\ \\  \\|\\  \\ \\   __/|    <#FF7302>",'l')));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ColorUtils.applyGradient("<#61CAFD> \\ \\    / / \\ \\   __  \\  \\ \\    / /     \\ \\  \\  <#B378CB>",'l') + ColorUtils.applyGradient("<#FEAA41> \\ \\  \\    \\ \\  \\\\\\  \\ \\   _  _\\ \\  \\_|/__  <#FF7302>",'l')));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ColorUtils.applyGradient("<#61CAFD>  /     \\/   \\ \\  \\|\\  \\  /     \\/       \\ \\  \\ <#B378CB>",'l') + ColorUtils.applyGradient("<#FEAA41>  \\ \\  \\____\\ \\  \\\\\\  \\ \\  \\\\  \\\\ \\  \\_|\\ \\ <#FF7302>",'l')));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ColorUtils.applyGradient("<#61CAFD> /  /\\   \\    \\ \\_______\\/  /\\   \\        \\ \\__\\<#B378CB>",'l') + ColorUtils.applyGradient("<#FEAA41>   \\ \\_______\\ \\_______\\ \\__\\\\ _\\\\ \\_______\\<#FF7302>",'l')));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ColorUtils.applyGradient("<#61CAFD>/__/ /\\ __\\    \\|_______/__/ /\\ __\\        \\|__|<#B378CB>",'l') + ColorUtils.applyGradient("<#FEAA41>    \\|_______|\\|_______|\\|__|\\|__|\\|_______|<#FF7302>",'l')));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ColorUtils.applyGradient("<#61CAFD>|__|/ \\|__|             |__|/ \\|__|             <#B378CB>",'l')));
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7   ╔═══════════════════════════════════════════════════════════════════════════╗"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7   ║                     &fxBxT Core Las Bases Del Servidor&7                      ║"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7   ╚═══════════════════════════════════════════════════════════════════════════╝"));
        Bukkit.getConsoleSender().sendMessage("");
    }


    private void MessageOFF(){
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',  Colorinfo + "XBXTPVP.XYZ " + Colorplayer + "OFF"));
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
            new Placeholder(this).register();
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
        WorldProtec();
        Timeinfo();
        startAutoCleaner();
        TimeAutoSafeInventory();
        StarRequestPing();
        String ip = "?";

        try {
            ip = DDNS_NameCheap.getPrivateIP();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (ip != null) {
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

    public static xBxTcore getInstance(){
        return plugin;
    }

    public static ArrayList<World> getWorldProtec(){
        return worlds;
    }

    ///////////////////////////////////////////////////
    ///////////////////////////////////////////////////

    private void startAutoCleaner() {
        long intervalTicks = 2 * 60 * 60 * 20L;
        new BukkitRunnable() {
            @Override
            public void run() {
                BroadcastMessage(Messages.Others_message1Minute);
                left1minutes();
            }
        }.runTaskTimer(plugin, 20*4, intervalTicks);
    }

    private void left1minutes() {
        new BukkitRunnable() {
            @Override
            public void run() {
                BroadcastMessage(Messages.Others_message5Seconds);
                left5seconds();
            }
        }.runTaskLater(this, 55*20);
    }

    private void left5seconds() {
        new BukkitRunnable() {
            @Override
            public void run() {
                ExecuteClener();
            }
        }.runTaskLater(this, 5*20);
    }

    private void ExecuteClener(){
        BroadcastMessage(Messages.Others_messageStarCleaner);
        EnvironmentsSection.getCleaner().clearArea("lobby");
    }

    ///////////////////////////////////////////////////
    ///////////////////////////////////////////////////

    public void messageOnlyPlayer(){
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + Colorinfo + "Solo jugadores Tonto"));
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

    private static final HashMap<UUID, PlayerDataRequestDuel> playerDataUnique = new HashMap<>();

    public static PlayerDataRequestDuel getPlayerDataUnique(UUID uuid){
        if (!playerDataUnique.containsKey(uuid)){
            PlayerDataRequestDuel playerDataUnique1 = new PlayerDataRequestDuel(uuid);
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
                    FileManagerSection.getPlayerFileManager().SaveInventoryBoxPvp(players.get(i).getUniqueId(), UtilsGlobal.getItensInvetory(players.get(i)));
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
        }.runTaskTimer(this, 20*10, 20*10);
    }

    public void StarRequestPing(){
        new BukkitRunnable() {
            public void run() {
                PingRequest.pingRequest();
            }
        }.runTaskTimer(this, 20, 20);
    }
}
