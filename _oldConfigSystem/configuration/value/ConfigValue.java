package de.lcraft.api.java_utils.configuration.value;

import de.lcraft.api.java_utils.configuration.sections.ConfigSection;
import java.util.Objects;

public class ConfigValue {

	private ConfigSection configSection;
	private Object savedValue;

	public ConfigValue(Object value, ConfigSection section) {
		this.configSection = section;
		this.savedValue = value;
	}

	public void setSavedValue(Object savedValue) {
		this.savedValue = savedValue;
	}
	public void setConfigSection(ConfigSection configSection) {
		this.configSection = configSection;
	}

	public ConfigSection getConfigSection() {
		return configSection;
	}
	public Object getSavedValue() {
		return savedValue;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ConfigValue value = (ConfigValue) o;

		if (!Objects.equals(configSection, value.configSection))
			return false;
		return Objects.equals(savedValue, value.savedValue);
	}

}
