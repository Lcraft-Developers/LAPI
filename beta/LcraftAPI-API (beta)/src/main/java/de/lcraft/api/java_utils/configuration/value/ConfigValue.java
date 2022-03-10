package de.lcraft.api.java_utils.configuration.value;

import de.lcraft.api.java_utils.configuration.sections.ConfigSection;
import java.util.Objects;

public class ConfigValue {

	private ConfigSection configSection;
	private Object savedValue;
	private String root;

	public ConfigValue(String root, Object value, ConfigSection section) {
		this.configSection = section;
		this.savedValue = value;
		this.root = root;
	}

	public void setSavedValue(Object savedValue) {
		this.savedValue = savedValue;
	}
	public void setRoot(String root) {
		this.root = root;
	}

	public ConfigSection getConfigSection() {
		return configSection;
	}
	public Object getSavedValue() {
		return savedValue;
	}
	public String getRoot() {
		return root;
	}

}
