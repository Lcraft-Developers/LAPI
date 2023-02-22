package de.lcraft.lapi.javaUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

public class InternetUtils {

    public static String[] urls = {
            "wikipedia.de", "wikipedia.com",
            "google.de", "google.com",
            "youtube.de", "youtube.com"
    };

    public static int isConnectionSucceedToWebsite(String urlStr) {
        int sucessIndex = 0;

        try {
            URL url = new URL(urlStr);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();

            int responseCodeHttp = httpURLConnection.getResponseCode();
            int responseCodeHttps = httpsURLConnection.getResponseCode();

            if(responseCodeHttp != HttpURLConnection.HTTP_NOT_FOUND) sucessIndex++;
            if(responseCodeHttps != HttpsURLConnection.HTTP_NOT_FOUND) sucessIndex++;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sucessIndex;
    }
    public static int getHighestConnectionSucceedFromWebsite(String[] urlsStr) {
        int highestSucceedIndex = 0;

        for(String urlStr : urlsStr) {
            int result = isConnectionSucceedToWebsite(urlStr);
            if(result > highestSucceedIndex) highestSucceedIndex = result;
        }

        return highestSucceedIndex;
    }

    public static boolean hasInternet() {
        return hasInternet(6000);
    }
    public static boolean hasInternet(int maxMsTimeout) {
        try {
            if(InetAddress.getLocalHost().isReachable(maxMsTimeout)) return true;
            else if(getHighestConnectionSucceedFromWebsite(urls) > 0) return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

}
