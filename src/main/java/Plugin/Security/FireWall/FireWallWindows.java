package Plugin.Security.FireWall;
import Plugin.Utils.Utils;
import Plugin.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static Plugin.File.BLackList.BlackListIpManager.blackListedIps;
import static Plugin.Messages.MessageManager.ColorSuccess;
import static Plugin.Messages.MessageManager.prefixConsole;
import static Plugin.Utils.Utils.executeCommandCMD;

public class FireWallWindows {

    public static void updateFirewallRule() {

        StringBuilder ipList = new StringBuilder();
        for (byte[] bytes : blackListedIps) {
            try {
                String ip = InetAddress.getByAddress(bytes).toString();
                String ipName = ip.toString().replace("/","");
                if (ipList.isEmpty()) {
                    ipList.append(ipName);
                }else{
                    ipList.append(",").append(ipName);
                }
            }catch (UnknownHostException e){
                e.printStackTrace();
            }
        }

        if (Utils.isRunningAsAdmin()){
            executeCommandCMD("netsh advfirewall firewall delete rule name=\"Bloquear_IPs\"");
            executeCommandCMD("netsh advfirewall firewall add rule name=Bloquear_IPs dir=in action=block remoteip=" + ipList + " protocol=any");
        }else {
            try {
                createBatFile(ipList);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }

    private static File batFile;

    // Método para crear el archivo .bat dinámicamente
    public static void createBatFile(StringBuilder ipString) throws IOException {
        // Ruta donde se creará el archivo .bat
        batFile = new File(xBxTcore.getInstance().getDataFolder(), "firewall_rule.bat");

        // Crear el archivo si no existe
        if (!batFile.exists()) {
            batFile.createNewFile();
        }

        // Crear el contenido del archivo .bat con los parámetros dinámicos
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(batFile))) {
            writer.write("@echo off\n");

            writer.write(":: Solicitar permisos elevados para el script completo\n" +
                    "setlocal\n" +
                    "set \"params=%*\"\n" +
                    "if \"%1\"==\"ELEVATED\" goto :run\n" +
                    "powershell -Command \"Start-Process '%~f0' -ArgumentList 'ELEVATED %params%' -Verb RunAs\"\n" +
                    "exit /b\n" +
                    "\n" +
                    ":run\n" +
                    ":: Cambiar al directorio del script actual\n" +
                    "cd /d \"%~dp0\"\n");
            // Eliminar la regla existente llamada "Bloquear_IPs"
            writer.write("netsh advfirewall firewall delete rule name=\"Bloquear_IPs\"\n");

            // Crear una nueva regla "Bloquear_IPs" con la lista de IPs

            // Agregar la nueva regla al firewall
            writer.write("netsh advfirewall firewall add rule name=\"Bloquear_IPs\" dir=in action=block remoteip=" + ipString + " protocol=any\n");
            writer.write("pause\n");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    // Método para ejecutar el archivo .bat
    public static void runBatFile() {
        if (batFile == null)batFile = new File(xBxTcore.getInstance().getDataFolder(), "firewall_rule.bat");
        new Thread(() -> {
            try {
                executeCommandCMD(batFile.getAbsolutePath());
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefixConsole + ColorSuccess + "Se actualizo correctamente"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

}
