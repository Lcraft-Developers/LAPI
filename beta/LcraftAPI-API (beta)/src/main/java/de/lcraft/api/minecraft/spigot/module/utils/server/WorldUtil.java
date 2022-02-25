package de.lcraft.api.minecraft.spigot.module.utils.server;

import org.bukkit.Bukkit;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Properties;
import java.util.logging.Level;

public class WorldUtil {

	public final void resetWorld() {
		deleteFolder("world");
		deleteFolder("world_nether");
		deleteFolder("world_the_end");

		createFiles();
	}
	public final void deleteFolder(String folder) {
		if (Files.exists(Paths.get(folder))) {
			try {
				Files.walk(Paths.get(folder), new java.nio.file.FileVisitOption[0])
						.sorted(Comparator.reverseOrder())
						.map(Path::toFile)
						.forEach(File::delete);

				createFiles();
				Bukkit.getLogger().log(Level.INFO, "§2" + folder.toUpperCase() + " §awird gel§scht und resetet");
			} catch (IOException e) {
				Bukkit.getLogger().log(Level.SEVERE, "§4" + folder.toUpperCase() + " §ckonnte nicht gel§scht werden\n§4Grund: §c" + e
						.getCause().toString());
			}
		}
	}
	public final void createFiles() {
		File propertiesFile = new File(Bukkit.getWorldContainer(), "server.properties");
		try (FileInputStream stream = new FileInputStream(propertiesFile)) {
			Properties properties = new Properties();
			properties.load(stream);

			File world = new File(Bukkit.getWorldContainer(), properties.getProperty("level-name"));
			Files.delete(world.getAbsoluteFile().toPath());

			File nether = new File(Bukkit.getWorldContainer(), "world_nether");
			Files.delete(nether.getAbsoluteFile().toPath());
			world.mkdirs();

			(new File(world, "data")).mkdirs();
			(new File(world, "datapacks")).mkdirs();
			(new File(world, "playerdata")).mkdirs();
			(new File(world, "poi")).mkdirs();
			(new File(world, "region")).mkdirs();
			(new File(nether, "data")).mkdirs();
			(new File(nether, "datapacks")).mkdirs();
			(new File(nether, "playerdata")).mkdirs();
			(new File(nether, "poi")).mkdirs();
			(new File(nether, "region")).mkdirs();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}