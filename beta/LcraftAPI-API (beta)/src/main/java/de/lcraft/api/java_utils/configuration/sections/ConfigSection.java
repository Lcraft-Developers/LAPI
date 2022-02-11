package de.lcraft.api.java_utils.configuration.sections;

import de.lcraft.api.java_utils.configuration.ConfigValue;

import java.util.HashMap;
import java.util.Objects;

public class ConfigSection {

	private HashMap<String, ConfigValue> allKeys;
	private String root;
	private ConfigSectionType configSectionType;
	private ConfigValue value;

	public ConfigSection(String root, Object obj, ConfigSectionType type) {
		this(root, type, true);
		this.value = new ConfigValue(obj,this);
	}
	public ConfigSection(String root, ConfigSectionType type, boolean existsValue) {
		if(existsValue && type == ConfigSectionType.LIST) {
			type = ConfigSectionType.ListAndValue;
		}
		this.root = root;
		this.configSectionType = type;
		this.allKeys = new HashMap<>();
	}

	public void addKey(String root, ConfigValue value) {
		if(existsKey(root)) {
			removeKey(root);
		}
		getAllKeys().put(root,value);
	}
	public void removeKey(String root) {
		if(existsKey(root)) {
			getAllKeys().remove(root);
		}
	}
	public boolean existsKey(String root) {
		return getAllKeys().containsKey(root);
	}

	public ConfigValue getValue() {
		return value;
	}
	public HashMap<String, ConfigValue> getAllKeys() {
		return allKeys;
	}
	public ConfigSectionType getConfigSectionType() {
		return configSectionType;
	}
	public void setConfigSectionType(ConfigSectionType newType) {
		configSectionType = newType;
	}
	public String getRoot() {
		return root;
	}
	public int size() {
		int size = allKeys.size();
		if(configSectionType == ConfigSectionType.OnlyValue || configSectionType == ConfigSectionType.ListAndValue) {
			size++;
		}
		return size;
	}

}
