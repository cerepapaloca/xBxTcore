package Plugin.WebSide;

import Plugin.xBxTcore;
import org.bukkit.Bukkit;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PingRequest {

    private static long cooldown = System.currentTimeMillis();

    public static void pingRequest() {
        new Thread(() -> {
            try {
                // Ejecuta el comando "ping" en el sistema
                String ip = "google.com"; // Puedes cambiar por cualquier IP o dominio
                Process process;
                String timeLoc;
                String notConexion;
                switch(xBxTcore.getSystemOperative){
                    case WINDOWS -> {
                        timeLoc = "tiempo=";
                        notConexion = "no pudo encontrar el host";
                        process = Runtime.getRuntime().exec("ping -n 1 " + ip);
                    }
                    case LINUX -> {
                        timeLoc = "time=";
                        notConexion = "unknown";
                        process = Runtime.getRuntime().exec("ping -c 1 " + ip);
                    }
                    default -> {
                        return;
                    }
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    //Bukkit.getLogger().warning(line);
                    // Filtra la línea que contiene el tiempo en ms
                    if (line.contains(timeLoc)) {
                        String time = line.split(timeLoc)[1].split("ms")[0];
                        if (Integer.parseInt(time) > 150){
                            Bukkit.getLogger().warning("***************************************");
                            Bukkit.getLogger().warning(line);
                            Bukkit.getLogger().warning("¡¡LATENCIA MUY ALTA SERVER NO RESPONDE!!");
                            Bukkit.getLogger().warning("***************************************");
                        }
                    }else if (line.contains(notConexion)) {
                        if (cooldown < System.currentTimeMillis()){
                            Bukkit.getLogger().warning("******************************");
                            Bukkit.getLogger().warning("¡¡NO HAY CONNEXION A INTERNET");
                            Bukkit.getLogger().warning("******************************");
                            cooldown = System.currentTimeMillis() + 1000*30;
                        }
                    }
                }
                reader.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
