package Plugin.Utils;

import Plugin.Messages.Enum.Messages;
import Plugin.xBxTcore;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.types.InheritanceNode;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static Plugin.Messages.MessageManager.*;
import static Plugin.Messages.MessageManager.ColorLink;
import static org.bukkit.Bukkit.getServer;

public class Utils {

    private final xBxTcore plugin;

    public Utils(xBxTcore plugin){
        this.plugin = plugin;
        TimeAlone();
    }

    private void TimeAlone() {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            if(getServer().getOnlinePlayers().size() == 1){
                for (Player p :getServer().getOnlinePlayers()){
                    p.sendMessage(MasterMessageLocated(p, Messages.Others_Alone));
                }
            }
        }, 2 * 20,40 * 20);
    }

    private static final HashMap<UUID, Integer> playersExecute = new HashMap<>();

    public static void AntiSpam(@NotNull Player p, @NotNull Messages messages, xBxTcore plugin) {
        UUID uuid = p.getUniqueId();
        playersExecute.put(uuid, playersExecute.getOrDefault(uuid, 0) + 1);
        if (playersExecute.get(uuid) > 3) {
            KickMessage(p, messages);
            playersExecute.remove(uuid);
        }
        new BukkitRunnable() {
            public void run() {
                playersExecute.remove(uuid);
            }
        }.runTaskLater(plugin, 200);
    }

    public static void RewardVote(String name, @NotNull Boolean force) {
        Player player = Bukkit.getPlayer(name);
        if (force){
            if(player == null) {
                xBxTcore.playersOffline.add(name);
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefixConsole + ColorWarning + "El jugador&r " + name
                        + ColorWarning + " no existe o no esta conectado"));
            }else{
                xBxTcore.getLuckPerms().getUserManager().modifyUser(player.getUniqueId(), user -> {
                    user.data().add(InheritanceNode.builder("vote").build());
                });
                Bukkit.getConsoleSender().sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo + "Ha votado el jugador: " + Colorplayer + name));
            }
        }else{
            if(player == null && xBxTcore.playersOffline.contains(name)) {
                xBxTcore.playersOffline.add(name);
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefixConsole + ColorError + "El jugador&r " + Colorplayer + name + " no se pudo dar la recompensa de algún modo"));
                return;
            }
            if(xBxTcore.playersOffline.contains(name)){
                xBxTcore.playersOffline.remove(name);
                assert player != null;
                xBxTcore.getLuckPerms().getUserManager().modifyUser(player.getUniqueId(), user -> {
                    user.data().add(InheritanceNode.builder("vote").build());
                });
                Bukkit.getConsoleSender().sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo + "Se ha echo un voto por adelantado de parte del jugador: " + Colorplayer + name));
            }
        }
    }

    public static void ClickExecuteCommand(String command,@NotNull Messages messages,@NotNull Player p) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix));
        TextComponent execute = new TextComponent();
        TextComponent executefinal = new TextComponent(MasterMessageLocated(p, messages));
        execute.setText(ChatColor.translateAlternateColorCodes('&', ColorLink + "/" + command));
        execute.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/" + command));
        execute.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(MasterMessageLocated(p,Messages.Generic_HoverExecute)).create()));
        executefinal.addExtra(execute);
        p.spigot().sendMessage(executefinal);
    }

    public static void NewitemInvetory(Messages m, Material ma, int slot, @NotNull Inventory i, Player p){
        ItemStack itemStack = new ItemStack(ma);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(MasterMessageLocated(p, m));
        itemStack.setItemMeta(itemMeta);
        i.setItem(slot, itemStack);
    }

    public static void NewitemInvetory(@NotNull Messages m, Material ma, int slot, Inventory i, @NotNull Player p, @NotNull ArrayList<String> lore){
        ItemStack itemStack = new ItemStack(ma);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        ArrayList<String> colorlore = new ArrayList<>();
        for (String s : lore){
            colorlore.add(ChatColor.translateAlternateColorCodes('&',  s));
        }
        itemMeta.setLore(colorlore);
        itemMeta.setDisplayName(MasterMessageLocated(p, m));
        itemStack.setItemMeta(itemMeta);
        i.setItem(slot, itemStack);
    }

    public static @NotNull ArrayList<ItemStack> getItensInvetory(Player player){
        ArrayList<ItemStack> items = new ArrayList<>();
        for(int i = 0; i<player.getInventory().getContents().length;){
            ItemStack itemstack = player.getInventory().getItem(i);
            items.add(itemstack);
            i++;
        }
        return items;
    }

    @Contract(pure = true)
    public static @NotNull String SecondToMinutes(int time){
        int minutes = time/60;
        int seconds = time%60;

        String timeString = "";

        if (minutes < 10){
            timeString = "0" + minutes;
        }

        if (seconds < 10){
            return timeString + ":0" + seconds;
        }else{
            return minutes + ":" + seconds;
        }
    }

    @Contract(pure = true)
    public static @NotNull String SecondToMinutes(long time){
        long days = TimeUnit.MILLISECONDS.toDays(time);
        time -= TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(time);
        time -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(time);
        time -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(time);
        return days + "d " + hours + "h " + minutes + "m " + seconds + "s ";
    }

    public static void additem(@NotNull Player player, ItemStack item){
        player.playSound(player, Sound.ENTITY_ITEM_PICKUP, 1,1);
        if (player.getInventory().addItem(item).isEmpty()) {
            return;
        }
        Location location = player.getLocation();
        World world = player.getWorld();
        world.dropItemNaturally(location, item);
    }

    public static void additem(@NotNull Player player, ItemStack item, int cantidad){
        player.playSound(player, Sound.ENTITY_ITEM_PICKUP, 1,1);
        for (int i = 0; i < cantidad; i++){
            if (player.getInventory().addItem(item).isEmpty()) {
                return;
            }
            Location location = player.getLocation();
            World world = player.getWorld();
            world.dropItemNaturally(location, item);
        }
    }

    public static @NotNull String processFormattingTags(String input) {
        // Reemplazar etiquetas de negrita y cursiva
        String result = input;

        // Negrita
        result = result.replaceAll("<b>", ChatColor.BOLD.toString());
        result = result.replaceAll("</b>", ChatColor.RESET.toString());

        // Cursiva
        result = result.replaceAll("<i>", ChatColor.ITALIC.toString());
        result = result.replaceAll("</i>", ChatColor.RESET.toString());

        return result;
    }

    public static @Nullable Chunk getChunkByCoordinates(String worldName, int chunkX, int chunkZ) {
        World world = Bukkit.getWorld(worldName);
        if (world != null) {
            return world.getChunkAt(chunkX, chunkZ);
        }
        return null;
    }

    @Contract("_, _ -> new")
    public static @NotNull Location getMidpoint(@NotNull Location loc1, @NotNull Location loc2) {
        if (loc1.getWorld() != loc2.getWorld()) {
            throw new IllegalArgumentException("Las dos ubicaciones deben estar en el mismo mundo.");
        }

        double midX = (loc1.getX() + loc2.getX()) / 2.0;
        double midY = (loc1.getY() + loc2.getY()) / 2.0;
        double midZ = (loc1.getZ() + loc2.getZ()) / 2.0;

        return new Location(loc1.getWorld(), midX, midY, midZ);
    }

    @Contract(pure = true)
    public static @NotNull String arabicToRoman(int number) {
        if (number < 1 || number > 3999) {
            throw new IllegalArgumentException("El número debe estar entre 1 y 3999.");
        }

        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        String roman = thousands[number / 1000] +
                hundreds[(number % 1000) / 100] +
                tens[(number % 100) / 10] +
                units[number % 10];

        return roman;
    }

    public static Material colorToMaterial(@NotNull Material material, @NotNull Material target) {
        String nameColor = material.name();
        String nameTarget = target.name();

        if (nameColor.contains("BLACK")) {
            nameColor = "BLACK_";
        } else if (nameColor.contains("RED")) {
            nameColor = "RED_";
        } else if (nameColor.contains("GREEN")) {
            nameColor = "GREEN_";
        } else if (nameColor.contains("BROWN")) {
            nameColor = "BROWN_";
        } else if (nameColor.contains("LIGHT_BLUE")) {
            nameColor = "BLUE_";
        } else if (nameColor.contains("PURPLE")) {
            nameColor = "PURPLE_";
        } else if (nameColor.contains("CYAN")) {
            nameColor = "CYAN_";
        } else if (nameColor.contains("LIGHT_GRAY")) {
            nameColor = "LIGHT_GRAY_";
        } else if (nameColor.contains("GRAY")) {
            nameColor = "GRAY_";
        } else if (nameColor.contains("PINK")) {
            nameColor = "PINK_";
        } else if (nameColor.contains("LIME")) {
            nameColor = "LIME_";
        } else if (nameColor.contains("YELLOW")) {
            nameColor = "YELLOW_";
        } else if (nameColor.contains("BLUE")) {
            nameColor = "LIGHT_BLUE_";
        } else if (nameColor.contains("MAGENTA")) {
            nameColor = "MAGENTA_";
        } else if (nameColor.contains("ORANGE")) {
            nameColor = "ORANGE_";
        } else if (nameColor.contains("WHITE")) {
            nameColor = "WHITE_";
        }

        if (nameTarget.contains("DYE")) {
            nameTarget = "DYE";
            return Material.getMaterial(nameColor + nameTarget);
        } else if (nameTarget.contains("WOOL")){
            nameTarget = "WOOL";
            return Material.getMaterial(nameColor + nameTarget);
        } else if (nameTarget.contains("CARPET")){
            nameTarget = "CARPET";
            return Material.getMaterial(nameColor + nameTarget);
        } else if (nameTarget.contains("TERRACOTTA")){
            nameTarget = "TERRACOTTA";
            return Material.getMaterial(nameColor + nameTarget);
        } else if (nameTarget.contains("STAINED_GLASS")){
            nameTarget = "STAINED_GLASS";
            return Material.getMaterial(nameColor + nameTarget);
        } else if (nameTarget.contains("SHULKER_BOX")){
            nameTarget = "SHULKER_BOX";
            return Material.getMaterial(nameColor + nameTarget);
        }

        return Material.STRUCTURE_VOID;
    }

    public static void executeCommand(String command) {
        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);

        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                Bukkit.getConsoleSender().sendMessage( ChatColor.translateAlternateColorCodes('&',prefixConsole + Colorinfo + "informe: " + line));
            }
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getPlayerPrefix(Player player) {
        LuckPerms luckPerms = LuckPermsProvider.get();
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());

        if (user != null) {
            return user.getCachedData().getMetaData().getPrefix();
        }

        return null;
    }

    public static boolean isRunningAsAdmin() {
        try {
            // Ejecutar el comando "net session" que solo los administradores pueden usar
            Process process = Runtime.getRuntime().exec("net session");

            // Leer la salida del comando
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                // Procesar la salida
            }

            // Si el comando devuelve 0, el usuario tiene permisos de administrador
            return process.waitFor() == 0;
        } catch (Exception e) {
            // Si el comando falla (acceso denegado o error), no se está ejecutando como administrador
            return false;
        }
    }

}