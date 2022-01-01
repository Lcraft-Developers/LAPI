package de.lcraft.api.java_utils;

import de.lcraft.api.java_utils.exeptions.InternetNotFoundException;
import de.lcraft.api.java_utils.exeptions.VersionNotFound;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.function.Consumer;

public class Internet {

    public boolean websiteExist(String url) throws IOException {
        URL u = new URL(url);
        HttpURLConnection huc = (HttpURLConnection) u.openConnection();
        int responseCode = huc.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
            return false;
        } else {
            return true;
        }
    }

    public boolean hasInternet() throws IOException {
        return hasInternet(250);
    }
    public boolean hasInternet(int millisecoundtimeout) throws IOException {
        boolean internet = false;

        if(InetAddress.getLocalHost().isReachable(millisecoundtimeout)) internet = true;

        try {
            if (websiteExist("wikipedia.de")) internet = true;
            if (websiteExist("wikipedia.com")) internet = true;
            if (websiteExist("google.de")) internet = true;
            if (websiteExist("google.com")) internet = true;
            if (websiteExist("youtube.de")) internet = true;
            if (websiteExist("youtube.com")) internet = true;
            if (websiteExist("lcraft.de")) internet = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return internet;
    }

    public File download(String url, String filename, String folder) throws IOException {
        if (hasInternet()) {
            BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            Files.copy(in, Paths.get(folder + "//" + filename), StandardCopyOption.REPLACE_EXISTING);
            return new File(folder + "//" + filename);
        } else {
            new InternetNotFoundException().printStackTrace();
        }
        return null;
    }

    public static class SpigotMc {

        private boolean isOutdated = true,
                        isUpdated = false;

        public boolean isUpdated(int resourcesID, String currentVersion) {
            return isUpdated(getUpdateLink(resourcesID), currentVersion);
        }

        public boolean isUpdated(String updateLink, String currentVersion) {
            getLatestVersion(updateLink, updateLink, version -> {
                if (currentVersion.equals(version)) {
                    isUpdated = true;
                } else {
                    isUpdated = false;
                }
            });
            return isUpdated;
        }

        public void getLatestVersion(String updateLink, String nameOrRecourcesID, Consumer<String> consumer) {
            try (InputStream inputStream = new URL(updateLink).openStream();
                 Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            } catch (IOException exception) {
                new VersionNotFound(nameOrRecourcesID).printStackTrace();
            }
        }

        public String getUpdateLink(int resourcesID) {
            return "https://api.spigotmc.org/legacy/update.php?resource=" + resourcesID;
        }

    }

}
