package de.lcraft.api.java_utils.configuration.sections;

import de.lcraft.api.java_utils.configuration.Config;
import de.lcraft.api.java_utils.configuration.ConfigValue;
import org.bukkit.configuration.MemorySection;

import java.util.HashMap;
import java.util.Objects;

public class ConfigSection {

	private HashMap<String, ConfigValue> allKeys;
	private String root;
	private ConfigSectionType configSectionType;
	private ConfigValue value;

	public ConfigSection(String root, Object obj) {
		this(root);
		this.value = new ConfigValue(obj,this);

		refreshType();
	}
	public ConfigSection(String root) {
		this.root = root;
		this.allKeys = new HashMap<>();

		refreshType();
	}

	public void addKey(String root, ConfigValue value) {
		if(existsKey(root)) {
			removeKey(root);
		}
		getAllKeys().put(root,value);

		refreshType();
	}
	public void removeKey(String root) {
		if(existsKey(root)) {
			getAllKeys().remove(root);
		}
		refreshType();
	}
	public boolean existsKey(String root) {
		refreshType();
		return getAllKeys().containsKey(root);
	}
	public void setValue(ConfigValue value) {
		this.value = value;
		refreshType();
	}
	public void refreshType() {
		if(Objects.nonNull(getValue()) && Objects.nonNull(getValue().convertToString()) && !(getValue().getSavedValue() instanceof MemorySection)) {
			setConfigSectionType(ConfigSectionType.OnlyValue);
		}
		if(Objects.nonNull(getAllKeys()) && !getAllKeys().isEmpty()) {
			if(getConfigSectionType() == ConfigSectionType.OnlyValue) {
				setConfigSectionType(ConfigSectionType.ListAndValue);
			} else {
				setConfigSectionType(ConfigSectionType.LIST);
			}
		}
	}

	public ConfigValue getValue() {
		return value;
	}
	public HashMap<String, ConfigValue> getAllKeys() {
		return allKeys;
	}
	public HashMap<String, ConfigValue> getAllKeysWithoutStartRoot() {
		HashMap<String, ConfigValue> newKeys = new HashMap<>();

		for(String current : getAllKeys().keySet()) {
			ConfigValue value = getAllKeys().get(current);
			if(current.startsWith(getRoot() + ".")) {
				newKeys.put(current.replaceFirst(getRoot() + ".",""), value);
			} else {
				newKeys.put(current.replaceFirst(getRoot(),""), value);
			}
		}

		return newKeys;
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
