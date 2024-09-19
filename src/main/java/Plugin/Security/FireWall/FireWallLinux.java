package Plugin.Security.FireWall;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.*;

import static Plugin.File.BLackList.BlackListIpManager.loadIpBlacklist;
import static Plugin.Messages.MessageManager.*;

public class FireWallLinux {

    public static void updateBlockedIPs() {
        List<String> ipList = new ArrayList<>();

        for(InetAddress ip : loadIpBlacklist ()){
            ipList.add(ip.getHostAddress());
        }

        Set<String> blockedIPs = getBlockedIPs();

        // Bloquear nuevas IPs (que están en la lista pero no en el firewall)
        for (String ip : ipList) {
            if (!blockedIPs.contains(ip)) {
                try {
                    blockIPWithFirewalld(ip);
                } catch (IOException e) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefixConsole + ColorError +
                            "Error al bloquear la IP " + ip + ": " + e.getMessage()));
                }
            }
        }

        // Eliminar IPs que ya no están en la lista pero siguen bloqueadas
        for (String blockedIp : blockedIPs) {
            if (!ipList.contains(blockedIp)) {
                try {
                    unblockIPWithFirewalld(blockedIp);
                } catch (IOException e) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefixConsole + ColorError +
                            "Error al desbloquear la IP " + blockedIp + ": " + e.getMessage()));
                    System.err.println();
                }
            }
        }

        // Recargar las reglas del firewall para que los cambios tengan efecto
        try {
            reloadFirewalld();
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefixConsole + ColorError +
                    "Error al recargar firewalld: " + e.getMessage()));
        }

        try {
            saveFirewalldChanges();
        }catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefixConsole + ColorError +
                    "Error al guardar el firewalld: " + e.getMessage()));
        }

        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefixConsole + ColorSuccess +
                "Se ejecuto exitosamente"));
    }

    // Método para obtener las IPs actualmente bloqueadas en el firewall
    private static Set<String> getBlockedIPs() {
        Set<String> blockedIPs = new HashSet<>();
        String command = "sudo firewall-cmd --list-all";
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

    // Método para bloquear una IP usando firewalld
    private static void blockIPWithFirewalld(String ip) throws IOException {
        // Comando para agregar la IP a la zona pública y bloquearla
        String command = "sudo firewall-cmd --zone=public --add-rich-rule='rule family=ipv4 source address=" + ip + " reject'";
        executeCommand(command);
        System.out.println("IP bloqueada: " + ip);
    }

    // Método para desbloquear una IP usando firewalld
    private static void unblockIPWithFirewalld(String ip) throws IOException {
        // Comando para eliminar la IP de las reglas de bloqueo
        String command = "sudo firewall-cmd --zone=public --remove-rich-rule='rule family=ipv4 source address=" + ip + " reject'";
        executeCommand(command);
        System.out.println("IP desbloqueada: " + ip);
    }

    // Método para recargar firewalld después de aplicar los cambios
    private static void reloadFirewalld() throws IOException {
        // Recargar las reglas de firewalld para que los cambios surtan efecto
        String command = "sudo firewall-cmd --reload";
        executeCommand(command);
    }

    private static void saveFirewalldChanges() throws IOException {
        String command = "sudo firewall-cmd --runtime-to-permanent";
        executeCommand(command);
        System.out.println("Cambios guardados permanentemente.");
    }

    // Método auxiliar para ejecutar un comando en la terminal
    private static void executeCommand(String command) throws IOException {
        Process process = Runtime.getRuntime().exec(command);
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Error al esperar a que el comando se ejecute.", e);
        }

        if (process.exitValue() != 0) {
            throw new IOException("El comando falló con el código de salida " + process.exitValue());
        }
    }
}
