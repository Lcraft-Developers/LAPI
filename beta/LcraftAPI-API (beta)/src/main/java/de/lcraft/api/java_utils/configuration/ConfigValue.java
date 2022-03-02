package de.lcraft.api.java_utils.configuration;

import de.lcraft.api.java_utils.configuration.sections.ConfigSection;

import java.util.Objects;

public class ConfigValue {

	private ConfigSection configSection;
	private Object savedValue;

	public ConfigValue(Object value, ConfigSection section) {
		this.configSection = section;
		this.savedValue = value;
	}

	public boolean isString(String c) {
		String value = convertFromString(c);
		if(!isInteger(value) &&
		   !isByte(value) &&
		   !isDouble(value) &&
		   !isFloat(value) &&
		   !isLong(value) &&
		   !isBoolean(value)) {
			return true;
		}
		return false;
	}
	public boolean isInteger(String c) {
		try {
			if(Objects.nonNull(c) && Objects.nonNull(Integer.valueOf(c))) {
				return true;
			}
		} catch (Exception e) {}
		return false;
	}
	public boolean isDouble(String c) {
		try {
		    if(Objects.nonNull(c) && Objects.nonNull(Double.valueOf(c))) {
			    return true;
		    }
		} catch (Exception e) {}
		return false;
	}
	public boolean isFloat(String c) {
		try {
		    if(Objects.nonNull(c) && Objects.nonNull(Float.valueOf(c))) {
			    return true;
		    }
		} catch (Exception e) {}
		return false;
	}
	public boolean isLong(String c) {
		try {
		    if(Objects.nonNull(c) && Objects.nonNull(Long.valueOf(c))) {
			    return true;
		    }
		} catch (Exception e) {}
		return false;
	}
	public boolean isByte(String c) {
		try {
		    if(Objects.nonNull(c) && Objects.nonNull(Byte.valueOf(c))) {
				return true;
			}
		} catch (Exception e) {}
		return false;
	}
	public boolean isBoolean(String c) {
		try {
		    if(Objects.nonNull(c) && Objects.nonNull(Boolean.valueOf(c))) {
				    return true;
			}
		} catch (Exception e) {}
		return false;
	}

	public Integer getAsInteger(String c) {
		if(isInteger(c)) {
			return Integer.valueOf(c);
		}
		return null;
	}
	public Double getAsDouble(String c) {
		if(isDouble(c)) {
			return Double.valueOf(c);
		}
		return null;
	}
	public Float getAsFloat(String c) {
		if(isFloat(c)) {
			return Float.valueOf(c);
		}
		return null;
	}
	public Long getAsLong(String c) {
		if(isLong(c)) {
			return Long.valueOf(c);
		}
		return null;
	}
	public Byte getAsByte(String c) {
		if(isByte(c)) {
			return Byte.valueOf(c);
		}
		return null;
	}
	public Boolean getAsBoolean(String c) {
		if(isBoolean(c)) {
			return Boolean.valueOf(c);
		}
		return null;
	}

	public String convertFromString(String c) {
		return c.replace("'","");
	}
	public String convertToString() {
		return "'" + savedValue.toString() + "'";
	}

	public ConfigSection getConfigSection() {
		return configSection;
	}
	public Object getSavedValue() {
		return savedValue;
	}

}
