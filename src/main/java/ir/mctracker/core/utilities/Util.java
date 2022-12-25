package ir.mctracker.core.utilities;

import ir.mctracker.mctrackervote.config.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.UnknownHostException;

public class Util {

    public static String colorize(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static void sendToConsole(String message) {
        Bukkit.getConsoleSender().sendMessage(Messages.PREFIX + Util.colorize(message));
    }

    public static String fetchJson(String url) {
        HttpsURLConnection con = null;

        try {
            BufferedReader br;
            try {
                URL u = new URL(url);
                con = (HttpsURLConnection) u.openConnection();
                con.connect();
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } catch (UnknownHostException | FileNotFoundException exception) {
                return null;
            }

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }

            br.close();
            return sb.toString();

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }

        return null;
    }
}
