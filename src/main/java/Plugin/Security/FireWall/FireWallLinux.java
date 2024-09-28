package Plugin.Security.FireWall;

import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

import static Plugin.File.BLackList.BlackListIpManager.blackListedIps;
import static Plugin.Messages.MessageManager.*;

public class FireWallLinux {

    private static Set<String> getBlockedIPs() {
        Set<String> blockedIPs = new HashSet<>();
        String command = "sudo firewall-cmd --permanent --list-all";  // Para obtener las reglas permanentes
        try {
            Process process = Runtime.getRuntime().exec(command);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("source address")) {
                        // Extraer la IP del texto de la regla
                        String ip = line.split("source address=")[1].split(" ")[0];
                        blockedIPs.add(ip);
                    }
                }
            }
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            Bukkit.getConsoleSender().sendMessage("Error al obtener las IPs bloqueadas: " + e.getMessage());
        }

        return blockedIPs;
    }

    public static void updateFirewallZone() {

        List<String> ipList = new ArrayList<>();
        for (byte[] bytes : blackListedIps) {
            try {
                String ip = InetAddress.getByAddress(bytes).toString();
                String ipName = ip.toString().replace("/","");
                ipList.add(ipName);
            }catch (UnknownHostException e){
                throw new RuntimeException(e);
            }
        }

        try {
            createBlockIPScript(ipList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static File shFile;

    public static void createBlockIPScript(List<String> ips) throws IOException {

        shFile = new File(xBxTcore.getInstance().getDataFolder(), "firewall_zone.sh");

        if (!shFile.exists()) {
            shFile.createNewFile();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(shFile))) {
            writer.write("sudo -v\n");
            writer.write("#!/bin/bash\n");
            writer.write("# Script para actualizar las IPs bloqueadas en firewalld\n\n");

            // Eliminar las reglas de bloqueo actuales
            writer.write("# Eliminar las reglas de bloqueo actuales\n");
            writer.write("sudo firewall-cmd --zone=public --list-rich-rules | grep 'reject' | while read -r rule; do\n");
            writer.write("    sudo firewall-cmd --zone=public --remove-rich-rule=\"$rule\"\n");
            writer.write("done\n\n");

            // Añadir las nuevas reglas de IP de bloqueo
            for (String ip : ips) {
                String command = "sudo firewall-cmd --zone=public --add-rich-rule='rule family=\"ipv4\" source address=\"" + ip + "\" reject'\n";
                writer.write(command);
            }

            // Hacer los cambios permanentes
            writer.write("\n# Hacer los cambios permanentes\n");
            writer.write("sudo firewall-cmd --runtime-to-permanent\n");
            writer.write("sudo firewall-cmd --reload\n");
            writer.write("read -p \"Presiona Enter para cerrar...\"");
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ColorSuccess + "Script generado exitosamente en: " + xBxTcore.getInstance().getDataFolder()));
    }

    public static void executeFirewallScript() {
        // Construye el comando para abrir gnome-terminal y ejecutar el script con sudo
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("gnome-terminal", "--", "bash", "-c", xBxTcore.getInstance().getDataFolder() + "/firewall_zone.sh; exec bash");
        // Ejecuta el comando en un nuevo hilo
        new Thread(() -> {
            try {
                Process process = processBuilder.start();

                // Lee la salida del proceso
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', Colorinfo + "Salida De FireWall: " + line));
                }

                // Espera a que el proceso termine
                int exitCode = process.waitFor();
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorSuccess +
                        "Comando ejecutado con éxito, código de salida: " + exitCode + " recuerda que puedes usar 'firewall-cmd --zone=public --list-all' para ver las IPs"));

            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

}
