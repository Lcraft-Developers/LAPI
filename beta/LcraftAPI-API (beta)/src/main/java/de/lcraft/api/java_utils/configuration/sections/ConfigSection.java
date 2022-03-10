package de.lcraft.api.java_utils.configuration.sections;

import de.lcraft.api.java_utils.configuration.value.ConfigValue;
import org.bukkit.configuration.MemorySection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ConfigSection {

	private ArrayList<ConfigValue> allKeys;
	private ConfigSectionType configSectionType;
	private ConfigValue value;
	private String root;

	public ConfigSection(String root, Object obj) {
		this(root);
		this.value = new ConfigValue(root, obj,this);

		refreshType();
	}
	public ConfigSection(String root) {
		this.root = root;
		this.allKeys = new ArrayList<>();

		refreshType();
	}
	public void refreshType() {
		if(Objects.nonNull(getValue()) && Objects.nonNull(getValue().toString()) && !(getValue().getSavedValue() instanceof MemorySection)) {
			setConfigSectionType(ConfigSectionType.OnlyValue);
		}
		if(Objects.nonNull(getAllKeysWithoutValue()) && !getAllKeysWithoutValue().isEmpty()) {
			if(getConfigSectionType() == ConfigSectionType.OnlyValue) {
				setConfigSectionType(ConfigSectionType.ListAndValue);
			} else {
				setConfigSectionType(ConfigSectionType.LIST);
			}
		}
	}

	public void addKey(String root, ConfigValue value) {
		if(existsKey(root)) {
			removeKey(root);
		}
		allKeys.add(value);

		refreshType();
	}
	public void removeKey(String root) {
		if(existsKey(root)) {
			allKeys.remove(root);
		}
		refreshType();
	}
	public boolean existsKey(String root) {
		refreshType();
		return allKeys.contains(root);
	}

	public void setValue(ConfigValue value) {
		this.value = value;
		refreshType();
	}
	public ConfigValue getValue() {
		return value;
	}

	public void setConfigSectionType(ConfigSectionType newType) {
		configSectionType = newType;
	}
	public ConfigSectionType getConfigSectionType() {
		return configSectionType;
	}

	public ArrayList<ConfigValue> getAllKeysWithoutValue() {
		return allKeys;
	}
	public ArrayList<ConfigValue> getAllKeysWithValue() {
		ArrayList<ConfigValue> newHashMap = getAllKeysWithoutValue();
		newHashMap.add(getValue());
		return newHashMap;
	}

	public int size() {
		int size = allKeys.size();
		if(configSectionType == ConfigSectionType.OnlyValue || configSectionType == ConfigSectionType.ListAndValue) {
			size++;
		}
		return size;
	}
	public String getRoot() {
		return root;
	}

}
