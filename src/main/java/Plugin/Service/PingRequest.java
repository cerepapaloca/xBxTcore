package Plugin.Service;

import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static Plugin.Messages.MessageManager.ColorError;
import static Plugin.Messages.MessageManager.ColorSuccess;

public final class PingRequest {

    private static long cooldown = System.currentTimeMillis();
    public static boolean conected = true;

    public static void pingRequest() {
        Bukkit.getScheduler().runTaskAsynchronously(xBxTcore.getInstance(), () -> {
            try {
                String ip = "google.com"; // Puedes cambiar por cualquier IP o dominio
                Process process;
                String timeLoc;
                String notConexion;
                String regex;
                notConexion = "no pudo encontrar el host";
                switch(xBxTcore.getSystemOperative){
                    case WINDOWS -> {
                        timeLoc = "tiempo=";
                        regex = "ms";
                        process = Runtime.getRuntime().exec("ping -n 1 " + ip);
                    }
                    case LINUX -> {
                        timeLoc = "time=";
                        regex = " ms";
                        process = Runtime.getRuntime().exec("ping -c 1 " + ip);
                    }
                    default -> {
                        return;
                    }
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                int i = 0;
                while ((line = reader.readLine()) != null) {
                    i ++;
                    if (line.contains(timeLoc)) {
                        if (!conected) {
                            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', ColorSuccess + "*******************************"));
                            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', ColorSuccess + "¡¡YA HAY CONNEXION A INTERNET!!"));
                            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', ColorSuccess + "*******************************"));
                            conected = true;
                            cooldown = System.currentTimeMillis();
                        }
                        String time = line.split(timeLoc)[1].split(regex)[0];
                        if (Float.parseFloat(time) > 150F) {
                            Bukkit.getLogger().warning("****************************************");
                            Bukkit.getLogger().warning(line);
                            Bukkit.getLogger().warning("¡¡LATENCIA MUY ALTA SERVER NO RESPONDE!!");
                            Bukkit.getLogger().warning("****************************************");
                        }
                    } else if (line.contains(notConexion)) {
                        conected = false;
                        if (cooldown < System.currentTimeMillis()) {
                            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', ColorError + "*******************************"));
                            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', ColorError + "¡¡NO HAY CONNEXION A INTERNET!!"));
                            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', ColorError + "*******************************"));
                            cooldown = System.currentTimeMillis() + 1000 * 60 * 5;
                        }
                    }
                }
                if (cooldown < System.currentTimeMillis() && i == 0) {
                    conected = false;
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', ColorError + "*******************************"));
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', ColorError + "¡¡NO HAY CONNEXION A INTERNET!!"));
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', ColorError + "*******************************"));
                    cooldown = System.currentTimeMillis() + 1000 * 60 * 5;
                }
                reader.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
