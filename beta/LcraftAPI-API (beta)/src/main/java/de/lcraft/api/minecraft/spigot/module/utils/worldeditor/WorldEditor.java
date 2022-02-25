package de.lcraft.api.minecraft.spigot.module.utils.worldeditor;

import org.bukkit.Bukkit;
import org.bukkit.World;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;

public class WorldEditor {

	public final void createFilesForWorld(File folder, String worldName) {
		File propertiesFile = new File(Bukkit.getWorldContainer(), "server.properties");
		try (FileInputStream stream = new FileInputStream(propertiesFile)) {
			Properties properties = new Properties();
			properties.load(stream);
			if(Objects.nonNull(Bukkit.getWorld(worldName))) {
				deleteWorld(Bukkit.getWorld(worldName));
			}
			folder.mkdirs();
			folder.mkdir();

			(new File(folder.getAbsoluteFile(), "data")).mkdirs();
			(new File(folder.getAbsoluteFile(), "datapacks")).mkdirs();
			(new File(folder.getAbsoluteFile(), "playerdata")).mkdirs();
			(new File(folder.getAbsoluteFile(), "poi")).mkdirs();
			(new File(folder.getAbsoluteFile(), "region")).mkdirs();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public final void deleteWorld(World world) {
		if(Objects.nonNull(world)) {
			File folder = world.getWorldFolder();
			if (Files.exists(Paths.get(folder.getAbsolutePath()))) {
				try {
					Files.walk(Paths.get(folder.getAbsolutePath()), new java.nio.file.FileVisitOption[0])
							.sorted(Comparator.reverseOrder())
							.map(Path::toFile)
							.forEach(File::delete);
					Bukkit.getLogger().log(Level.INFO, "§2" + folder + " §awird gel§scht und resetet");
				} catch (IOException e) {
					Bukkit.getLogger().log(Level.SEVERE, "§4" + folder + " §ckonnte nicht gel§scht werden\n§4Grund: §c" + e.getCause().toString());
				}
			}
		}
	}
	public final void resetWorldToDefault() {
		ArrayList<File> allWorlds = getAllFolders();
		deleteAllWorlds();

		for(File currentFile : allWorlds) {
			createFilesForWorld(currentFile, currentFile.getName());
		}
	}
	public final void deleteAllWorlds() {
		for(World w : Bukkit.getWorlds()) {
			deleteWorld(w);
		}
	}

	public ArrayList<File> getAllFolders() {
		ArrayList<File> folders = new ArrayList<>();
		for(World w : Bukkit.getWorlds()) {
			folders.add(w.getWorldFolder());
		}
		return folders;
	}

}