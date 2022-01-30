package de.lcraft.api.utils.java.utils;

import de.lcraft.api.utils.java.exeptions.InternetNotFoundException;
import de.lcraft.api.utils.java.exeptions.VersionNotFound;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.function.Consumer;

public class Internet {

    public static boolean websiteExist(String url) throws IOException {
        URL u = new URL(url);
        HttpURLConnection huc = (HttpURLConnection) u.openConnection();
        int responseCode = huc.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean hasInternet() {
        boolean internet = false;

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

    public static File download(String url, String filename, String folder) throws IOException {
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

        private static boolean isOutdated = true,
                isUpdated = false;

        public static boolean isOutdated(int recourcesID, String currentVersion) {
            getLatestVersion(recourcesID, version -> {
                if (currentVersion.equals(version)) {
                    isOutdated = false;
                } else {
                    isOutdated = true;
                }
            });
            return isOutdated;
        }

        public static boolean isUpdated(int recourcesID, String currentVersion) {
            getLatestVersion(recourcesID, version -> {
                if (currentVersion.equals(version)) {
                    isUpdated = true;
                } else {
                    isUpdated = false;
                }
            });
            return isUpdated;
        }

        public static void getLatestVersion(int recourcesID, Consumer<String> consumer) {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + recourcesID).openStream();
                 Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            } catch (IOException exception) {
                new VersionNotFound(recourcesID).printStackTrace();
            }
        }

    }

}
