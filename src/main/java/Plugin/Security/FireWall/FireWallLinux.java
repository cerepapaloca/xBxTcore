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

    // Método para ejecutar comandos en el sistema
    private static void executeCommand(String command) throws IOException {
        Process process = Runtime.getRuntime().exec(command);
        try {
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', ColorSuccess + "Comando ejecutado exitosamente: " + command));
            } else {
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', ColorError + "Error al ejecutar el comando. Código de salida: " + exitCode));
            }
        } catch (InterruptedException e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', ColorError + "El comando fue interrumpido: " + e.getMessage()));
        }
    }

    private static File shFile;

    public static void createBlockIPScript(List<String> ips) throws IOException {

        shFile = new File(xBxTcore.getInstance().getDataFolder(), "firewall_zone.sh");

        // Crear el archivo si no existe
        if (!shFile.exists()) {
            shFile.createNewFile();
        }
        // Ruta del archivo .sh que vamos a generar
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(shFile))) {
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
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ColorSuccess + "Script generado exitosamente en: " + xBxTcore.getInstance().getDataFolder()));
    }

    public static void executeFirewallScript() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", "sudo Desktop/xBxTpvp.xyz/plugins/xBxTcore/firewall_zone.sh");

        try {
            // Ejecutar el comando
            Process process = processBuilder.start();

            // Leer la salida del comando
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',Colorinfo + line)); // Imprimir la salida en la consola
            }

            // Esperar a que el proceso termine y obtener el código de salida
            int exitCode = process.waitFor();
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorSuccess +
                    "Comando ejecutado con éxito, código de salida: " + exitCode));

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
