package de.lcraft.api.java_utils.configuration;

import de.lcraft.api.java_utils.CodeHelper;
import net.md_5.bungee.config.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Config {

	private final File file;
	private final File folder;
	private YamlConfiguration configuration;

	public Config(String startPath, String path, String filename) {
		if(startPath.endsWith("//")) {
			startPath = CodeHelper.replaceEnd(startPath,"//","");
		}
		if(path.startsWith("//")) {
			path = path.replaceFirst("//","");
		}
		if(!startPath.isEmpty() && !startPath.isBlank()) {
			folder = new File(startPath + "//" + path);

			if(Objects.nonNull(folder) && Objects.nonNull(startPath) && Objects.nonNull(path)) {
				file = new File(folder, filename);
			} else {
				file = new File(filename);
			}
		} else if(!path.isEmpty() && !path.isBlank()) {
			folder = new File(path);

			if(Objects.nonNull(folder) && Objects.nonNull(startPath) && Objects.nonNull(path)) {
				file = new File(folder, filename);
			} else {
				file = new File(filename);
			}
		} else {
			folder = new File("");

			file = new File(filename);
		}
		if(!folder.exists()) {
			folder.mkdir();
			folder.mkdirs();
		}
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		load();
	}
	public Config(String path, String filename) {
		this("", path, filename);
	}
	public Config(String filename) {
		this("", filename);
	}

	public void load() {
		this.configuration = new YamlConfiguration();
		try {
			getConfiguration().load(getFile());
		} catch (IOException | InvalidConfigurationException e) {
			throw new RuntimeException(e);
		}
	}
	public void save() {
		try {
			this.configuration.save(getFile());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	protected void sendDebuggerText(String str) {
		System.out.println(str);
	}

	public boolean set(String wantedRoot, Object obj) {
		getConfiguration().set(wantedRoot, obj);
		return true;
	}
	public Object get(String root) {
		return getConfiguration().get(root);
	}
	public boolean exists(String root) {
		return getConfiguration().contains(root);
	}

	/*public void setSection(ConfigSection section) {

	}
	public ConfigSection createAndGetSection(ConfigSection section) {

	}*/
	public ConfigurationSection getSection(String root) {
		return getConfiguration().getConfigurationSection(root);
	}
	public boolean existsSection(String root) {
		return getConfiguration().contains(root) && getConfiguration().isConfigurationSection(root);
	}
	/*public void resetAllConfigurationSections() {

	}*/

	public boolean existsAsString(String root) {
		return getConfiguration().contains(root) && getConfiguration().get(root) instanceof String;
	}
	public boolean existsAsInteger(String root) {
		return getConfiguration().contains(root) && getConfiguration().get(root) instanceof Integer;
	}
	public boolean existsAsDouble(String root) {
		return getConfiguration().contains(root) && getConfiguration().get(root) instanceof Double;
	}
	public boolean existsAsLong(String root) {
		return getConfiguration().contains(root) && getConfiguration().get(root) instanceof Long;
	}
	public boolean existsAsFloat(String root) {
		return getConfiguration().contains(root) && getConfiguration().get(root) instanceof Float;
	}
	public boolean existsAsBoolean(String root) {
		return getConfiguration().contains(root) && getConfiguration().get(root) instanceof Boolean;
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
		if(existsAsString(path)) {
			return getString(path);
		} else {
			set(path, start);
			return start;
		}
	}
	public final Integer getIntegerDefault(String path, Integer start) {
		if(existsAsInteger(path)) {
			return getInteger(path);
		} else {
			set(path, start);
			return start;
		}
	}
	public final Double getDoubleDefault(String path, Double start) {
		if(existsAsDouble(path)) {
			return getDouble(path);
		} else {
			set(path, start);
			return start;
		}
	}
	public final Long getLongDefault(String path, Long start) {
		if(existsAsLong(path)) {
			return getLong(path);
		} else {
			set(path, start);
			return start;
		}
	}
	public final Float getFloatDefault(String path, Float start) {
		if(existsAsFloat(path)) {
			return getFloat(path);
		} else {
			set(path, start);
			return start;
		}
	}
	public final Boolean getBooleanDefault(String path, Boolean start) {
		if(existsAsBoolean(path)) {
			return getBoolean(path);
		} else {
			set(path, start);
			return start;
		}
	}

	public String getString(String root) {
		return getConfiguration().getString(root);
	}
	public Integer getInteger(String root) {
		return getConfiguration().getInt(root);
	}
	public Double getDouble(String root) {
		return getConfiguration().getDouble(root);
	}
	public Long getLong(String root) {
		return getConfiguration().getLong(root);
	}
	public Float getFloat(String root) {
		return Float.valueOf(Objects.requireNonNull(getConfiguration().get(root)).toString());
	}
	public Boolean getBoolean(String root) {
		return getConfiguration().getBoolean(root);
	}

	public boolean isEmpty() {
		return false;
	}
	public File getFile() {
		return file;
	}
	public File getFolder() {
		return folder;
	}
	private YamlConfiguration getConfiguration() {
		return configuration;
	}

}
