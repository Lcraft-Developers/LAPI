package de.lcraft.api.java_utils.new_configuration.providerSystem.utils;

import de.lcraft.api.java_utils.new_configuration.providerSystem.value.ProviderObject;

public abstract class ProviderValidator {

	public abstract boolean isString(ProviderObject providerObject);
	public abstract boolean isBoolean(ProviderObject providerObject);

	public abstract boolean isNumber(ProviderObject providerObject);
	public abstract boolean isInteger(ProviderObject providerObject);
	public abstract boolean isDouble(ProviderObject providerObject);
	public abstract boolean isLong(ProviderObject providerObject);
	public abstract boolean isByte(ProviderObject providerObject);

	public abstract boolean isList(ProviderObject providerObject);
	public abstract boolean isArrayList(ProviderObject providerObject);
	public abstract boolean isLinkedList(ProviderObject providerObject);

	public abstract boolean isMap(ProviderObject providerObject);
	public abstract boolean isHashMap(ProviderObject providerObject);
	public abstract boolean isHashtable(ProviderObject providerObject);
	public abstract boolean isLinkedHashMap(ProviderObject providerObject);
	public abstract boolean isTreeMap(ProviderObject providerObject);

	public abstract boolean isSet(ProviderObject providerObject);
	public abstract boolean isHashSet(ProviderObject providerObject);
	public abstract boolean isLinkedHashSet(ProviderObject providerObject);
	public abstract boolean isTreeSet(ProviderObject providerObject);

}
