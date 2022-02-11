package de.lcraft.api.minecraft.spigot.manager.configs;

import de.lcraft.api.java_utils.configuration.Config;
import de.lcraft.api.java_utils.configuration.sections.ConfigSection;
import de.lcraft.api.minecraft.spigot.manager.Module;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import java.util.Objects;

public class ModuleConfig {

	private final Config cfg;

	public ModuleConfig(String startPath, String path, String filename) {
		this.cfg = new Config(startPath, path, filename);
		this.cfg.load();
	}
	public ModuleConfig(Module m, String path, String filename) {
		this("lmodules//" + m.getModuleDescriptionFile().getName(), path, filename);
	}
	public ModuleConfig(Module m, String filename) {
		this("lmodules//" + m.getModuleDescriptionFile().getName(), filename);
	}
	public ModuleConfig(String path, String filename) {
		this("lmodules//", path, filename);
	}
	public ModuleConfig(String filename) {
		this("lmodules//", "", filename);
	}

	public final Object getDefault(String path, Object start) {
		if(exists(path)) {
			return get(path);
		} else {
			set(path, start);
			return start;
		}
	}
	public final String getStringDefault(String path, String start) {
		if(exists(path)) {
			return getString(path);
		} else {
			set(path, start);
			return start;
		}
	}
	public final void set(String root, Object obj) {
		getCfg().set(root,obj);
		getCfg().load();
	}
	public final boolean exists(String root) {
		return getCfg().exists(root);
	}
	public final boolean isEmpty() {
		return getCfg().isEmpty();
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

	public final Object get(String root) {
		Object obj = getCfg().get(root);
		return obj;
	}
	public final String getString(String root) {
		return getCfg().getString(root);
	}
	public final Integer getInteger(String root) {
		return getCfg().getInteger(root);
	}
	public final Double getDouble(String root) {
		return getCfg().getDouble(root);
	}
	public final Float getFloat(String root) {
		return getCfg().getFloat(root);
	}
	public final Boolean getBoolean(String root) {
		return getCfg().getBoolean(root);
	}
	public final ConfigSection getSection(String root) {
		return getCfg().getSection(root);
	}

	public final void save() {
		getCfg().save();
	}
	public final Config getCfg() {
		return cfg;
	}

}
