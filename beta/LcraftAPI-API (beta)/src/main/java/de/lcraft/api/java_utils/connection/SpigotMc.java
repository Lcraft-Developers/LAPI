package de.lcraft.api.java_utils.connection;

import de.lcraft.api.java_utils.exeptions.SpigotMCPluginNotFound;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class SpigotMc {

	private final boolean isOutdated = true;
	private boolean isUpdated = false;

	public final boolean isUpdated(int resourcesID, String currentVersion) {
		return isUpdated(getUpdateLink(resourcesID), currentVersion);
	}
	public boolean isUpdated(String updateLink, String currentVersion) {
		getLatestVersion(updateLink, updateLink, version -> {
			isUpdated = currentVersion.equals(version);
		});
		return isUpdated;
	}
	public final void getLatestVersion(String updateLink, String nameOrRecourcesID, Consumer<String> consumer) {
		try (InputStream inputStream = new URL(updateLink).openStream();
			 Scanner scanner = new Scanner(inputStream)) {
			if (scanner.hasNext()) {
				consumer.accept(scanner.next());
			}
		} catch (IOException exception) {
			new SpigotMCPluginNotFound(nameOrRecourcesID).printStackTrace();
		}
	}
	public final String getUpdateLink(int resourcesID) {
		return "https://api.spigotmc.org/legacy/update.php?resource=" + resourcesID;
	}

}
