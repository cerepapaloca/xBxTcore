package xyz.xbcore.Service;

import xyz.xbcore.xBxTcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Enumeration;

import static xyz.xbcommun.Messages.MessageManager.*;

public final class DDNS_NameCheap {

    private static final String DYNAMIC_DNS_URL = "https://dynamicdns.park-your-domain.com/update";
    private static final String domain = "xbxtpvp.xyz";
    private static final String password = "ead10319d4b349c29a8438e46490d8a2";
    private static String IpNow = null;

    public static @NotNull String getPublicIP() throws Exception {
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

    public static @Nullable String getPrivateIP() throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();
            Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress inetAddress = addresses.nextElement();
                if (!inetAddress.isLoopbackAddress() && inetAddress.isSiteLocalAddress()) {
                    return inetAddress.getHostAddress();
                }
            }
        }
        return null;
    }

    public static void updateIP() {
        Bukkit.getScheduler().runTaskAsynchronously(xBxTcore.getInstance(), () -> {
            if (!PingRequest.conected)return;
            try {
                if (IpNow == null) {
                    IpNow = getPublicIP();
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',Colorinfo + "La ip publica es: " + IpNow));
                }
                if (!IpNow.equals(getPublicIP())) {
                    String url = DYNAMIC_DNS_URL + "?host=@&domain=" + domain + "&password=" + password + "&ip=" + getPublicIP();
                    HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                    connection.setRequestMethod("GET");
                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefixConsole + ColorSuccess + "IP actualizada correctamente. de " + IpNow + " a " + getPublicIP()));
                        IpNow = getPublicIP();
                    } else {
                        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefixConsole + ColorError + "Error al actualizar la IP. Código de respuesta: " + responseCode));
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }
}
