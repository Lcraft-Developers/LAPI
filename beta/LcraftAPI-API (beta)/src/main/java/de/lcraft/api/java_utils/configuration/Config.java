package de.lcraft.api.java_utils.configuration;

import de.lcraft.api.java_utils.CodeHelper;
import de.lcraft.api.java_utils.configuration.sections.ConfigSection;
import de.lcraft.api.java_utils.configuration.sections.ConfigSectionType;
import de.lcraft.api.java_utils.configuration.writer.ConfigFileWriter;
import de.lcraft.api.java_utils.configuration.writer.EasyConfigFileWriter;
import de.lcraft.api.java_utils.configuration.writer.YAMLConfigFileWriter;
import org.bukkit.configuration.InvalidConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Config {

	private File file;
	private File folder;
	private ArrayList<ConfigSection> allConfigurationSections;
	private ConfigFileWriter configWriter;

	public Config(String startPath, String path, String filename, ConfigFileWriter configWriter) {
		allConfigurationSections = new ArrayList<>();
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
		this.configWriter = configWriter;
		load();
	}
	public Config(String startPath, String path, String filename) {
		this(startPath, path, filename, new YAMLConfigFileWriter());
	}
	public Config(String path, String filename, ConfigFileWriter configWriter) {
		this("", path, filename, configWriter);
	}
	public Config(String path, String filename) {
		this("", path, filename);
	}
	public Config(String filename, ConfigFileWriter configWriter) {
		this("", filename, configWriter);
	}
	public Config(String filename) {
		this("", filename);
	}

	public void load() {
		getConfigWriter().loadFromCFGFile(this);
	}
	public void save() {
		getConfigWriter().clearCFGFile(this);
		getConfigWriter().addIntoCFGFile(this);
	}
	public boolean isEmpty() {
		if(Objects.nonNull(getAllConfigurationSections()) && getAllConfigurationSections().isEmpty()) {
			return true;
		} else if(Objects.isNull(getAllConfigurationSections())) {
			allConfigurationSections = new ArrayList<>();
			return true;
		}
		return false;
	}

	public boolean set(String wantedRoot, Object obj) {
		/*if(!wantedRoot.contains(".")) {
			// If root is a variable
			if(existsSection(wantedRoot)) {
				ConfigSection section = getSection(wantedRoot);
				section.setValue(new ConfigValue(obj,section));
				setSection(section);
				return true;
			} else if(exists(wantedRoot)) {
				setSection(new ConfigSection("", obj));
				return true;
				// If root is a section
			} else {
				setSection(new ConfigSection("", obj));
				return true;
			}
		} else {*/
			String rootBefore = "";
			String currentRoot = "";
			for(String currentPartByRoot : wantedRoot.split("\\.")) {
				if(rootBefore.isEmpty() || rootBefore.isBlank()) {
					currentRoot = currentPartByRoot;
				} else {
					currentRoot = rootBefore + "." + currentPartByRoot;
				}
				/*System.out.println("");
				System.out.println(rootBefore);
				System.out.println(currentRoot);*/
				// If currentRoot exsits
				// If rootBefore is Section
				// If currentRoot is wantedRoot
				if(exists(currentRoot) && existsSection(rootBefore) && currentRoot.equals(wantedRoot)) {
					if(currentRoot.equals(wantedRoot)) {
						ConfigSection section = getSection(rootBefore);
						section.removeKey(rootBefore);
						section.addKey(currentRoot, new ConfigValue(obj.toString(), section));
						setSection(section);
						//System.out.println(1);

						return true;
					}

					// If currentRoot is wantedRoot
					// Not contains .
				}  else if(wantedRoot.equals(currentRoot) && !currentRoot.contains(".")) {
					ConfigSection section;
					if(existsSection("")) {
						section = getSection("");
					} else {
						section = new ConfigSection("");
					}
					section.addKey(currentRoot, new ConfigValue(obj.toString(),section));
					setSection(section);
					//System.out.println(2);

					return true;
					// If rootBefore is Section
					// If currentRoot is wantedRoot
					// Contains .
				} else if(wantedRoot.equals(currentRoot) && existsSection(rootBefore) && currentRoot.contains(".")) {
					ConfigSection section = getSection(rootBefore);
					section.addKey(currentRoot, new ConfigValue(obj.toString(),section));
					setSection(section);
					//System.out.println(3);

					return true;
					// If rootBefore is not a Section
					// If currentRoot is wantedRoot
				} else if(wantedRoot.equals(currentRoot) && !existsSection(rootBefore)) {
					ConfigSection section = new ConfigSection(rootBefore);
					section.addKey(currentRoot, new ConfigValue(obj.toString(),section));
					setSection(section);
					//System.out.println(4);

					return true;
				}

				rootBefore = currentRoot;
			}
		//}
		return false;
	}
	public ConfigValue get(String root) {
		for(ConfigSection c : getAllConfigurationSections()) {
			if(c.getConfigSectionType() == ConfigSectionType.ListAndValue || c.getConfigSectionType() == ConfigSectionType.OnlyValue) {
				if(c.getRoot().equals(root)) {
					return c.getValue();
				}
			}
			if(c.getConfigSectionType() == ConfigSectionType.LIST || c.getConfigSectionType() == ConfigSectionType.ListAndValue) {
				for(String roots : c.getAllKeys().keySet()) {
					ConfigValue value = c.getAllKeys().get(roots);
					if(roots.equals(root)) {
						if(Objects.nonNull(value)) {
							return value;
						}
					}
				}
			}
		}
		return null;
	}
	public boolean exists(String root) {
		if(Objects.nonNull(get(root))) {
			return true;
		}
		return false;
	}

	public boolean existsAsString(String root) {
		ConfigValue value = get(root);
		if(Objects.nonNull(value)) {
			if(value.isString(value.convertFromString(value.getSavedValue().toString()))) {
				return true;
			}
		}
		return false;
	}
	public boolean existsAsInteger(String root) {
		ConfigValue value = get(root);
		if(Objects.nonNull(value)) {
			if(value.isInteger(value.convertFromString(value.getSavedValue().toString()))) {
				return true;
			}
		}
		return false;
	}
	public boolean existsAsLong(String root) {
		ConfigValue value = get(root);
		if(Objects.nonNull(value)) {
			if(value.isLong(value.convertFromString(value.getSavedValue().toString()))) {
				return true;
			}
		}
		return false;
	}
	public boolean existsAsFloat(String root) {
		ConfigValue value = get(root);
		if(Objects.nonNull(value)) {
			if(value.isFloat(value.convertFromString(value.getSavedValue().toString()))) {
				return true;
			}
		}
		return false;
	}
	public boolean existsAsDouble(String root) {
		ConfigValue value = get(root);
		if(Objects.nonNull(value)) {
			if(value.isDouble(value.convertFromString(value.getSavedValue().toString()))) {
				return true;
			}
		}
		return false;
	}
	public boolean existsAsByte(String root) {
		ConfigValue value = get(root);
		if(Objects.nonNull(value)) {
			if(value.isByte(value.convertFromString(value.getSavedValue().toString()))) {
				return true;
			}
		}
		return false;
	}
	public boolean existsAsBoolean(String root) {
		ConfigValue value = get(root);
		if(Objects.nonNull(value)) {
			if(value.isBoolean(value.convertFromString(value.getSavedValue().toString()))) {
				return true;
			}
		}
		return false;
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

	public String getString(String root) {
		ConfigValue value = get(root);
		if(Objects.nonNull(value)) {
			return value.convertFromString(value.getSavedValue().toString());
		}
		return null;
	}
	public Integer getInteger(String root) {
		ConfigValue value = get(root);
		if(Objects.nonNull(value) && existsAsInteger(root)) {
			return value.getAsInteger(value.convertFromString(value.getSavedValue().toString()));
		}
		return null;
	}
	public Long getLong(String root) {
		ConfigValue value = get(root);
		if(Objects.nonNull(value) && existsAsLong(root)) {
			return value.getAsLong(value.convertFromString(value.getSavedValue().toString()));
		}
		return null;
	}
	public Float getFloat(String root) {
		ConfigValue value = get(root);
		if(Objects.nonNull(value) && existsAsFloat(root)) {
			return value.getAsFloat(value.convertFromString(value.getSavedValue().toString()));
		}
		return null;
	}
	public Double getDouble(String root) {
		ConfigValue value = get(root);
		if(Objects.nonNull(value) && existsAsDouble(root)) {
			return value.getAsDouble(value.convertFromString(value.getSavedValue().toString()));
		}
		return null;
	}
	public Byte getByte(String root) {
		ConfigValue value = get(root);
		if(Objects.nonNull(value) && existsAsByte(root)) {
			return value.getAsByte(value.convertFromString(value.getSavedValue().toString()));
		}
		return null;
	}
	public Boolean getBoolean(String root) {
		ConfigValue value = get(root);
		if(Objects.nonNull(value) && existsAsBoolean(root)) {
			return value.getAsBoolean(value.convertFromString(value.getSavedValue().toString()));
		}
		return null;
	}

	public void setSection(ConfigSection section) {
		if(existsSection(section.getRoot())) {
			getAllConfigurationSections().remove(section);
		}
		getAllConfigurationSections().add(section);
	}
	public ConfigSection getSection(String root) {
		for(ConfigSection c : getAllConfigurationSections()) {
			if(c.getRoot().equals(root)) {
				return c;
			}
		}
		return null;
	}
	public boolean existsSection(String root) {
		for(ConfigSection c : getAllConfigurationSections()) {
			if(c.getRoot().equals(root)) {
				return true;
			}
		}
		return false;
	}

	public File getFile() {
		return file;
	}
	public ArrayList<ConfigSection> getAllConfigurationSections() {
		return allConfigurationSections;
	}
	public File getFolder() {
		return folder;
	}
	public ConfigFileWriter getConfigWriter() {
		return configWriter;
	}

}
