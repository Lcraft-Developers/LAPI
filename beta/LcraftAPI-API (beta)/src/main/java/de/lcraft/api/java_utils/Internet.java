package de.lcraft.api.java_utils;

import de.lcraft.api.java_utils.exeptions.InternetNotFoundException;
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Internet {

    public static final boolean websiteExist(String url) {
        try {
            URL u = new URL(url);
            HttpURLConnection huc = (HttpURLConnection) u.openConnection();
            int responseCode = huc.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_NOT_FOUND) {
                return true;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static final boolean hasInternet() {
        return hasInternet(250);
    }
    public static final boolean hasInternet(int millisecoundtimeout) {
        boolean internet = false;

        try {
            if(InetAddress.getLocalHost().isReachable(millisecoundtimeout)) internet = true;

            if (websiteExist("wikipedia.de")) internet = true;
            if (websiteExist("wikipedia.com")) internet = true;
            if (websiteExist("google.de")) internet = true;
            if (websiteExist("google.com")) internet = true;
            if (websiteExist("youtube.de")) internet = true;
            if (websiteExist("youtube.com")) internet = true;
            if (websiteExist("lcraft.de")) internet = true;
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return internet;
    }

    public static final File download(String url, String filename, String folder) {
        if (hasInternet()) {
            try {
                BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
                FileOutputStream fileOutputStream = new FileOutputStream(filename);
                byte dataBuffer[] = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
                Files.copy(in, Paths.get(folder + "//" + filename), StandardCopyOption.REPLACE_EXISTING);
                return new File(folder + "//" + filename);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            new InternetNotFoundException().printStackTrace();
        }
        return null;
    }

}
