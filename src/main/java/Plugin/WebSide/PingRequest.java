package Plugin.WebSide;

import org.bukkit.Bukkit;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PingRequest {

    public static void pingRequest() {
        try {
            // Ejecuta el comando "ping" en el sistema
            String ip = "google.com"; // Puedes cambiar por cualquier IP o dominio
            Process process = Runtime.getRuntime().exec("ping -c 1 " + ip);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                // Filtra la línea que contiene el tiempo en ms
                if (line.contains("time=")) {
                    Bukkit.getConsoleSender().sendMessage(line);
                    // Extrae el valor de latencia
                    String time = line.split("time=")[1].split(" ")[0];
                    Bukkit.getConsoleSender().sendMessage("Latencia: " + time + " ms");
                }
            }
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
