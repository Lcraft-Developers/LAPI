package de.lcraft.api.java_utils.new_configuration.providerSystem.utils;

import de.lcraft.api.java_utils.new_configuration.providerSystem.value.*;
import de.lcraft.api.java_utils.new_configuration.providerSystem.value.number.*;
import de.lcraft.api.java_utils.new_configuration.providerSystem.value.lists.*;

public abstract class ProviderConverter {

	public abstract String convertStringToString(ProviderString providerString);
	public abstract ProviderString convertStringFromString();
	public abstract String convertBooleanToString(ProviderBoolean providerBoolean);
	public abstract ProviderBoolean convertBooleanFromString();

	public abstract Number convertNumberToString(ProviderNumber providerNumber);
	public abstract ProviderNumber convertNumberFromString();
	public abstract String convertIntegerToString(ProviderInteger providerInteger);
	public abstract ProviderInteger convertIntegerFromString();
	public abstract String convertDoubleToString(ProviderDouble providerDouble);
	public abstract ProviderDouble convertDoubleFromString();
	public abstract String convertLongToString(ProviderLong providerLong);
	public abstract ProviderLong convertLongFromString();
	public abstract String convertByteToString(ProviderByte providerByte);
	public abstract ProviderByte convertByteFromString();

	public abstract String convertListToString(ProviderList providerList);
	public abstract ProviderList convertListFromString();

	public abstract String convertMapToString(ProviderMap providerByte);
	public abstract ProviderMap convertMapFromString();

	public abstract String convertSetToString(ProviderSet providerSet);
	public abstract ProviderSet convertSetFromString();

}
