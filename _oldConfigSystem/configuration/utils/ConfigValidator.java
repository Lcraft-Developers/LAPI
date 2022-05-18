package de.lcraft.api.java_utils.configuration.utils;

import java.util.Objects;

public class ConfigValidator {

	public boolean isString(String c) {
		return !isNumeric(c) &&
				!isBoolean(c);
	}
	public boolean isInteger(String c) {
		if(Objects.nonNull(c)) {
			try {
				int integer_ = Integer.parseInt(c);

				return true;
			} catch (NumberFormatException nfe) {}
		}
		return false;
	}
	public boolean isDouble(String c) {
		if(Objects.nonNull(c)) {
			try {
				double double_ = Double.parseDouble(c);
				return true;
			} catch (NumberFormatException nfe) {}
		}
		return false;
	}
	public boolean isFloat(String c) {
		if(Objects.nonNull(c)) {
			try {
				float float_ = Float.parseFloat(c);
				return true;
			} catch (NumberFormatException nfe) {}
		}
		return false;
	}
	public boolean isLong(String c) {
		if(Objects.nonNull(c)) {
			try {
				long long_ = Long.parseLong(c);
				return true;
			} catch (NumberFormatException nfe) {}
		}
		return false;
	}
	public boolean isByte(String c) {
		if(Objects.nonNull(c)) {
			try {
				byte byte_ = Byte.parseByte(c);
				return true;
			} catch (NumberFormatException nfe) {}
		}
		return false;
	}
	public boolean isNumeric(String c) {
		boolean is = isInteger(c);

		if(isDouble(c)) is = true;
		if(isFloat(c)) is = true;
		if(isLong(c)) is = true;
		if(isByte(c)) is = true;

		return is;
	}
	public boolean isBoolean(String c) {
		if(Objects.nonNull(c)) {
			try {
				boolean boolean_ = Boolean.valueOf(c);
				return true;
			} catch (NumberFormatException nfe) {}
		}
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

}
