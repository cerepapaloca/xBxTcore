package Plugin.Security;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import static Plugin.Messages.MessageManager.Colorinfo;
import static Plugin.Messages.MessageManager.prefixConsole;
import static Plugin.Utils.Utils.executeCommand;

public class FireWall {

    public static void updateFirewallRule() {

        /*StringBuilder ipList = new StringBuilder();
        for (InetAddress ip : loadIpBlacklist()) {
            String ipName = ip.toString().replace("/","");
            if (ipList.isEmpty()) {
                ipList.append(ipName);
            }else{
                ipList.append(",").append(ipName);
            }
        }*/
        String ipList = "123.123.123.123";
        // Crea o actualiza la regla de firewall
        // Primero elimina la regla existente si existe
        executeCommand("netsh advfirewall firewall delete rule name=\"Bloquear_IPs\"");

        // Luego añade la nueva regla con todas las IPs
        ProcessBuilder processBuilder = new ProcessBuilder(
                "netsh", "advfirewall", "firewall", "add", "rule",
                "name=Bloquear_IPs",
                "dir=in", "action=block", "remoteip=" + ipList, "protocol=any"
        );

        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + Colorinfo + "Informe: " + line));
            }
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
