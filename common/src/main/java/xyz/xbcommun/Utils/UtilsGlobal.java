package xyz.xbcommun.Utils;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.plugin.Plugin;
import xyz.xbcommun.Messages.MessageManager;
import xyz.xbcommun.Messages.Messages.Messages;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static xyz.xbcommun.Messages.MessageManager.*;
import static xyz.xbcommun.Messages.MessageManager.ColorLink;
import static org.bukkit.Bukkit.getServer;

public final class UtilsGlobal {

    private static Plugin plugin;

    public UtilsGlobal(Plugin plugin){
        UtilsGlobal.plugin = plugin;
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

    public static void AntiSpam(@NotNull Player p, @NotNull Messages messages, Plugin plugin) {
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

    public static void sendMessage(@NotNull CommandSender sender, @NotNull Messages messages) {
        if (sender instanceof Player player) {
            sendMessage(sender, MasterMessageLocated(player, messages));
        }else{
            MessageManager.sendMessageConsole(messages);
        }
    }

    public static void sendMessage(@NotNull CommandSender sender, @NotNull String message) {
        sender.sendMessage(message);
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

    public static void newItemInventory(String titile, Material ma, int slot, Inventory i){
        newItemInventory(titile, ma, slot, i, null);
    }

    public static void newItemInventory(Messages m, Material ma, int slot, Inventory i, Player p){
        newItemInventory(MasterMessageLocated(p, m), ma, slot, i, null);
    }

    public static void newItemInventory(Messages m, Material ma, int slot, Inventory i, Player p, @Nullable ArrayList<String> lore){
        newItemInventory(MasterMessageLocated(p, m), ma, slot, i, lore);
    }

    public static void newItemInventory(String titile, Material ma, int slot, Inventory i, @Nullable ArrayList<String> lore){
        ItemStack itemStack = new ItemStack(ma);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        if (lore != null) {
            ArrayList<String> colorlore = new ArrayList<>();
            for (String s : lore){
                colorlore.add(ChatColor.translateAlternateColorCodes('&', s));
            }
            itemMeta.setLore(colorlore);
        }
        itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_DYE);

        itemMeta.setDisplayName(org.bukkit.ChatColor.translateAlternateColorCodes('&',titile));
        itemStack.setItemMeta(itemMeta);
        i.setItem(slot, itemStack);
    }


    public static @NotNull ArrayList<ItemStack> getItensInvetory(@NotNull Player player){
        ArrayList<ItemStack> items = new ArrayList<>();
        for(int i = 0; i<player.getInventory().getContents().length;){
            ItemStack itemstack = player.getInventory().getItem(i);
            items.add(itemstack);
            i++;
        }
        return items;
    }

    public static String TimeToString(int second, int format){
        return TimeToString(second*1000L, format);
    }
    @Contract(pure = true)
    public static String TimeToString(long time, int format) {
        long days = TimeUnit.MILLISECONDS.toDays(time);
        time -= TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(time);
        time -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(time);
        time -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(time);
        switch (format) {
            case 0 -> {
                //time /= 1000;
                //long minutes = time / 60;
                //long seconds = time % 60;

                String timeString = "";

                if (minutes < 10) {
                    timeString = "0" + minutes;
                }

                if (seconds < 10) {
                    return timeString + ":0" + seconds;
                } else {
                    return minutes + ":" + seconds;
                }
            }
            case 1 -> {
                return days + "d " + hours + "h " + minutes + "m " + seconds + "s ";
            }
            case 2 -> {
                String date = "";
                if (days != 0) {
                    date = date + days + " dias";
                } else if (hours != 0) {
                    date = date + hours + " horas";
                } else if (minutes != 0) {
                    date = date + minutes + " minutos";
                } else if (seconds != 0) {
                    date = date + seconds + " segundos";
                }
                return date;
            }
        }
        return null;
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

    @Contract(pure = true)
    public static long StringToMilliseconds(@NotNull String time) {
        char unit = time.charAt(time.length() - 1);
        long value = Long.parseLong(time.substring(0, time.length() - 1));

        return switch (unit) {
            case 's' -> // Segundos
                    value * 1000;
            case 'm' -> // Minutos
                    value * 1000 * 60;
            case 'h' -> // Horas
                    value * 1000 * 60 * 60;
            case 'd' -> // Días
                    value * 1000 * 60 * 60 * 24;
            default -> throw new IllegalArgumentException("Unidad de tiempo no válida: " + unit);
        };
    }

    public static @NotNull ArrayList<String> StringToLoreString(@NotNull String texto, boolean space) {
        return StringToLoreString(texto, 40, space, '7');
    }

    public static @NotNull ArrayList<String> StringToLoreString(@NotNull String texto, int longitud, boolean space) {
        return StringToLoreString(texto, longitud, space, '7');
    }

    public static @NotNull ArrayList<String> StringToLoreString(@NotNull String texto, boolean space, char color) {
        return StringToLoreString(texto, 40, space, color);
    }

    public static @NotNull ArrayList<String> StringToLoreString(@NotNull String texto, int longitud, boolean space, char color) {
        texto = ChatColor.translateAlternateColorCodes('&', texto);
        ArrayList<String> lineas = new ArrayList<>();
        if (space) lineas.add(" ");

        String[] partes = texto.split("\n"); // Dividimos el texto en líneas si contiene \n

        for (String parte : partes) {
            int inicio = 0;
            while (inicio < parte.length()) {
                int fin = Math.min(inicio + longitud, parte.length());

                // Si el substring no termina en espacio, buscar el último espacio dentro del rango
                if (fin < parte.length() && parte.charAt(fin) != ' ') {
                    int ultimoEspacio = parte.lastIndexOf(' ', fin);
                    if (ultimoEspacio > inicio) {
                        fin = ultimoEspacio; // Ajusta el fin al último espacio encontrado
                    }
                }

                lineas.add(ChatColor.translateAlternateColorCodes('&', "&" + color + parte.substring(inicio, fin).trim()));
                inicio = fin + 1; // Salta el espacio
            }
        }

        if (space && !lineas.isEmpty()) lineas.add(" ");
        return lineas;
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

    @Contract(pure = true)
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

    @Contract(pure = true)
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

        return thousands[number / 1000] +
                hundreds[(number % 1000) / 100] +
                tens[(number % 100) / 10] +
                units[number % 10];
    }

    @Contract(pure = true)
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

    public static void executeCommandCMD(String command) {
        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);

        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                Bukkit.getConsoleSender().sendMessage( ChatColor.translateAlternateColorCodes('&',prefixConsole + Colorinfo + line));
            }
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Contract(pure = true)
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

            // Si el comando devuelve 0, el usuario tiene permisos de administrador
            return process.waitFor() == 0;
        } catch (Exception e) {
            // Si el comando falla (acceso denegado o error), no se está ejecutando como administrador
            return false;
        }
    }

}