package Plugin.Apis;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Enumeration;

import static Plugin.Messages.MessageManager.*;

public class DDNS_NameCheap {

    private static final String DYNAMIC_DNS_URL = "https://dynamicdns.park-your-domain.com/update";
    private static final String domain = "xbxtpvp.xyz";
    private static final String password = "ead10319d4b349c29a8438e46490d8a2";
    private static String IpNow = null;

    public static String getPublicIP() throws Exception {
        String url = "https://api.ipify.org";
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content.toString();
    }

    public static String getPrivateIP() throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();
            Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress inetAddress = addresses.nextElement();
                // Filtramos las direcciones locales no deseadas y nos quedamos con las IPv4
                if (!inetAddress.isLoopbackAddress() && inetAddress.isSiteLocalAddress()) {
                    return inetAddress.getHostAddress();
                }
            }
        }
        return null;
    }

    public static void updateIP() throws Exception {
        if (IpNow == null) {
            IpNow = getPublicIP();
        }
        if (!IpNow.equals(getPublicIP())) {
            String url = DYNAMIC_DNS_URL + "?host=@&domain=" + domain + "&password=" + password + "&ip=" + getPublicIP();
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                IpNow = getPublicIP();
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefixConsole + ColorSuccess + "IP actualizada correctamente."));
            } else {
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefixConsole + ColorError + "Error al actualizar la IP. Código de respuesta: " + responseCode));
            }
        }else{
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefixConsole + ColorSuccess + "La Ip es la misma"));
        }
    }
}
