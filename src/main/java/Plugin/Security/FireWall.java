package Plugin.Security;
import java.net.InetAddress;

import static Plugin.File.BLackList.BlackListIpManager.loadIpBlacklist;
import static Plugin.Utils.Utils.executeCommandCMD;

public class FireWall {

    public static void updateFirewallRule() {

        StringBuilder ipList = new StringBuilder();
        for (InetAddress ip : loadIpBlacklist()) {
            String ipName = ip.toString().replace("/","");
            if (ipList.isEmpty()) {
                ipList.append(ipName);
            }else{
                ipList.append(",").append(ipName);
            }
        }

        executeCommandCMD("netsh advfirewall firewall delete rule name=\"Bloquear_IPs\"");
        executeCommandCMD("netsh advfirewall firewall add rule name=Bloquear_IPs dir=in action=block remoteip=" + ipList + " protocol=any");
    }

}
