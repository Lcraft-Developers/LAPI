package de.lcraft.api.minecraft.spigot.module.manager.configs;

import de.lcraft.api.java_utils.configuration.Config;
import de.lcraft.api.java_utils.configuration.sections.ConfigSection;
import de.lcraft.api.minecraft.spigot.module.manager.Module;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import java.util.Objects;

public class ModuleConfig extends Config {

	public ModuleConfig(Module m, String path, String filename) {
		super("lmodules//" + m.getModuleDescriptionFile().getName(), path, filename);
	}
	public ModuleConfig(Module m, String filename) {
		super("lmodules//" + m.getModuleDescriptionFile().getName(), filename);
	}
	public ModuleConfig(String path, String filename) {
		super("lmodules", path, filename);
	}
	public ModuleConfig(String filename) {
		super("lmodules", "", filename);
	}

	public void setBlockLocation(String root, Location loc) {
		set(root + ".world", loc.getWorld().getName());
		set(root + ".x", loc.getX());
		set(root + ".y", loc.getY());
		set(root + ".z", loc.getZ());
	}
	public Location getBlockLocation(String root) {
		if(existsBlockLocation(root)) {
			World w = Bukkit.getWorld(getString(root + ".world"));
			double x = getDouble(root + ".x"),
					y = getDouble(root + ".y"),
					z = getDouble(root + ".z");
			return new Location(w, x, y, z);
		}
		return null;
	}
	public boolean existsBlockLocation(String root) {
		if(exists(root + ".world") && Objects.nonNull(Bukkit.getWorld(getString(root + ".world")))) {
			if(exists(root + ".x")) {
				if(exists(root + ".y")) {
					if(exists(root + ".z")) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public void setLocation(String root, Location loc) {
		set(root + ".world", loc.getWorld().getName());
		set(root + ".x", loc.getX());
		set(root + ".y", loc.getY());
		set(root + ".z", loc.getZ());
		set(root + ".yaw", loc.getYaw());
		set(root + ".pitch", loc.getPitch());
	}
	public Location getLocation(String root, Location loc) {
		if(existsLocation(root)) {
			World w = Bukkit.getWorld(getString(root + ".world"));
			double x = getDouble(root + ".x"),
					y = getDouble(root + ".y"),
					z = getDouble(root + ".z");
			float yaw = getFloat(root + ".yaw");
			float pitch = getFloat(root + ".pitch");
			return new Location(w, x, y, z, yaw, pitch);
		}
		return null;
	}
	public boolean existsLocation(String root) {
		if(exists(root + ".world") && Objects.nonNull(Bukkit.getWorld(getString(root + ".world")))) {
			if(exists(root + ".x")) {
				if(exists(root + ".y")) {
					if(exists(root + ".z")) {
						if(exists(root + ".yaw")) {
							if(exists(root + ".pitch")) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

}
