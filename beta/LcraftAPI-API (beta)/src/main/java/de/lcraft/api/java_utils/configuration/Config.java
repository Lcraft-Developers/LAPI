package de.lcraft.api.java_utils.configuration;

import de.lcraft.api.java_utils.CodeHelper;
import de.lcraft.api.java_utils.configuration.sections.ConfigSection;
import de.lcraft.api.java_utils.configuration.sections.ConfigSectionType;
import de.lcraft.api.java_utils.configuration.utils.ConfigValidator;
import de.lcraft.api.java_utils.configuration.value.ConfigValue;
import de.lcraft.api.java_utils.configuration.writer.ConfigFileWriter;
import de.lcraft.api.java_utils.configuration.writer.EasyConfigFileWriter;
import de.lcraft.api.java_utils.configuration.writer.json.JSONConfigWriter;

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
		this(startPath, path, filename, new JSONConfigWriter());
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

		load();
	}
	protected void sendDebuggerText(String str) {
		System.out.println(str);
	}

	public boolean set(String wantedRoot, Object obj) {
		if(!wantedRoot.contains(".")) {
			sendDebuggerText("--------------------------------");
			ConfigSection configSection = createAndGetSection(new ConfigSection(""));

			sendDebuggerText("---");
			if(wantedRoot.isBlank() || wantedRoot.isEmpty()) {
				configSection.setValue(new ConfigValue(wantedRoot, obj, configSection));
				sendDebuggerText("setValue");
			} else {
				configSection.addKey(wantedRoot,new ConfigValue(wantedRoot, obj, configSection));
				sendDebuggerText("addValue");
			}
			setSection(configSection);

			sendDebuggerText("--------------------------------");
		} else {
			String rootBefore = "";
			String currentRoot = "";
			for(String currentPartByRoot : wantedRoot.split("\\.")) {
				if(rootBefore.isEmpty() || rootBefore.isBlank()) {
					currentRoot = currentPartByRoot;
				} else {
					currentRoot = rootBefore + "." + currentPartByRoot;
				}
				sendDebuggerText("--------------------------------");
				sendDebuggerText("Section: " + rootBefore);
				sendDebuggerText("CurrentRoot: " + currentRoot);
				if (wantedRoot.equals(currentRoot) && existsSection(rootBefore)) {
					ConfigSection section = getSection(rootBefore);
					if(exists(currentRoot)) {
						section.removeKey(currentRoot);
					}
					section.addKey(currentRoot, new ConfigValue(currentRoot, obj.toString(), section));
					setSection(section);

					sendDebuggerText("rootBefore: existsSection");
					sendDebuggerText("---");
					sendDebuggerText("getSection");
					sendDebuggerText("removeKeyRoot and addKeyRoot");
					sendDebuggerText("setSection");
					sendDebuggerText("--------------------------------");

					return true;
				} else if (wantedRoot.equals(currentRoot) && existsSection(currentRoot)) {
					ConfigSection section = getSection(currentRoot);
					section.setValue(new ConfigValue(currentRoot, obj.toString(), section));
					setSection(section);

					sendDebuggerText("currentRoot: existsSection");
					sendDebuggerText("---");
					sendDebuggerText("getSection");
					sendDebuggerText("createRoot");
					sendDebuggerText("setRoot");
					sendDebuggerText("setSection");

					return true;
				} else if(wantedRoot.equals(currentRoot)) {
					ConfigSection section = createAndGetSection(new ConfigSection(rootBefore));
					section.addKey(currentRoot, new ConfigValue(currentRoot, obj.toString(), section));
					setSection(section);

					sendDebuggerText("---");
					sendDebuggerText("createSection or getSection");
					sendDebuggerText("addKeyRoot");
					sendDebuggerText("setSection");
					sendDebuggerText("--------------------------------");

					return true;
				}

				rootBefore = currentRoot;
			}
		}
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
				for(ConfigValue value : c.getAllKeysWithValue()) {
					if(value.getRoot().equals(root)) {
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
		return Objects.nonNull(get(root));
	}

	public void setSection(ConfigSection section) {
		if(existsSection(section.getRoot())) {
			getAllConfigurationSections().remove(section);
		}
		getAllConfigurationSections().add(section);
	}
	public ConfigSection createAndGetSection(ConfigSection section) {
		if(!existsSection(section.getRoot())) {
			setSection(section);
			sendDebuggerText("(null) Section: create");
		} else {
			sendDebuggerText("(null) Section: exsists");
		}
		return section;
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
	public void resetAllConfigurationSections() {
		this.allConfigurationSections = new ArrayList<>();
	}

	public boolean existsAsString(String root) {
		ConfigValue value = get(root);
		if(Objects.nonNull(value)) {
			return new ConfigValidator().isString(value.getSavedValue().toString());
		}
		return false;
	}
	public boolean existsAsInteger(String root) {
		ConfigValue value = get(root);
		if(Objects.nonNull(value)) {
			return new ConfigValidator().isInteger(value.getSavedValue().toString());
		}
		return false;
	}
	public boolean existsAsDouble(String root) {
		ConfigValue value = get(root);
		if(Objects.nonNull(value)) {
			return new ConfigValidator().isDouble(value.getSavedValue().toString());
		}
		return false;
	}
	public boolean existsAsLong(String root) {
		ConfigValue value = get(root);
		if(Objects.nonNull(value)) {
			return new ConfigValidator().isLong(value.getSavedValue().toString());
		}
		return false;
	}
	public boolean existsAsFloat(String root) {
		ConfigValue value = get(root);
		if(Objects.nonNull(value)) {
			return new ConfigValidator().isFloat(value.getSavedValue().toString());
		}
		return false;
	}
	public boolean existsAsByte(String root) {
		ConfigValue value = get(root);
		if(Objects.nonNull(value)) {
			return new ConfigValidator().isByte(value.getSavedValue().toString());
		}
		return false;
	}
	public boolean existsAsBoolean(String root) {
		ConfigValue value = get(root);
		if(Objects.nonNull(value)) {
			return new ConfigValidator().isBoolean(value.getSavedValue().toString());
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
	public final Byte getByteDefault(String path, Byte start) {
		if(existsAsByte(path)) {
			return getByte(path);
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
		ConfigValue value = get(root);
		if(Objects.nonNull(value)) {
			return value.getSavedValue().toString();
		}
		return null;
	}
	public Integer getInteger(String root) {
		ConfigValue value = get(root);
		if(Objects.nonNull(value) && existsAsInteger(root)) {
			return new ConfigValidator().getAsInteger(value.getSavedValue().toString());
		}
		return null;
	}
	public Double getDouble(String root) {
		ConfigValue value = get(root);
		if(Objects.nonNull(value) && existsAsDouble(root)) {
			return new ConfigValidator().getAsDouble(value.getSavedValue().toString());
		}
		return null;
	}
	public Long getLong(String root) {
		ConfigValue value = get(root);
		if(Objects.nonNull(value) && existsAsLong(root)) {
			return new ConfigValidator().getAsLong(value.getSavedValue().toString());
		}
		return null;
	}
	public Float getFloat(String root) {
		ConfigValue value = get(root);
		if(Objects.nonNull(value) && existsAsFloat(root)) {
			return new ConfigValidator().getAsFloat(value.getSavedValue().toString());
		}
		return null;
	}
	public Byte getByte(String root) {
		ConfigValue value = get(root);
		if(Objects.nonNull(value) && existsAsByte(root)) {
			return new ConfigValidator().getAsByte(value.getSavedValue().toString());
		}
		return null;
	}
	public Boolean getBoolean(String root) {
		ConfigValue value = get(root);
		if(Objects.nonNull(value) && existsAsBoolean(root)) {
			return new ConfigValidator().getAsBoolean(value.getSavedValue().toString());
		}
		return null;
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
	public File getFile() {
		return file;
	}
	public File getFolder() {
		return folder;
	}
	public ConfigFileWriter getConfigWriter() {
		return configWriter;
	}
	public ArrayList<ConfigSection> getAllConfigurationSections() {
		return allConfigurationSections;
	}

}
