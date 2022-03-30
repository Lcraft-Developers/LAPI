package de.lcraft.api.java_utils.new_configuration.providerSystem.utils;

import de.lcraft.api.java_utils.new_configuration.providerSystem.value.*;
import de.lcraft.api.java_utils.new_configuration.providerSystem.value.number.*;
import de.lcraft.api.java_utils.new_configuration.providerSystem.value.lists.*;
import de.lcraft.api.java_utils.new_configuration.providerSystem.value.lists.list.*;
import de.lcraft.api.java_utils.new_configuration.providerSystem.value.lists.map.*;
import de.lcraft.api.java_utils.new_configuration.providerSystem.value.lists.set.*;

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
	public abstract String convertArrayListToString(ProviderArrayList providerArrayList);
	public abstract ProviderArrayList convertArrayListFromString();
	public abstract String convertLinkedListToString(ProviderLinkedList providerLinkedArrayList);
	public abstract ProviderLinkedList convertLinkedListFromString();

	public abstract String convertMapToString(ProviderMap providerByte);
	public abstract ProviderMap convertMapFromString();
	public abstract String convertHashMapToString(ProviderHashMap providerHashMap);
	public abstract ProviderMap convertHashMapFromString();
	public abstract String convertByteToHashtable(ProviderHashtable providerHashtable);
	public abstract ProviderHashtable convertHashtableFromString();
	public abstract String convertLinkedHashMapToString(ProviderLinkedHashMap providerLinkedHashMap);
	public abstract ProviderLinkedHashMap convertLinkedHashMapFromString();
	public abstract String convertTreeMapToString(ProviderTreeMap providerTreeMap);
	public abstract ProviderTreeMap convertTreeMapFromString();

	public abstract String convertSetToString(ProviderSet providerSet);
	public abstract ProviderSet convertSetFromString();
	public abstract String convertHashSetToString(ProviderHashSet providerHashSet);
	public abstract ProviderHashSet convertHashSetFromString();
	public abstract String convertLinkedHashSetToString(ProviderLinkedHashSet providerLinkedHashSet);
	public abstract ProviderLinkedHashSet convertLinkedHashSetFromString();
	public abstract String convertTreeSetToString(ProviderTreeSet providerTreeSet);
	public abstract ProviderTreeSet convertTreeSetFromString();

}
