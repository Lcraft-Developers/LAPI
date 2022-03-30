package de.lcraft.api.java_utils.new_configuration.providerSystem;

import de.lcraft.api.java_utils.new_configuration.providerSystem.utils.*;
import de.lcraft.api.java_utils.new_configuration.providerSystem.value.*;
import de.lcraft.api.java_utils.new_configuration.providerSystem.value.number.*;
import de.lcraft.api.java_utils.new_configuration.providerSystem.value.lists.*;
import de.lcraft.api.java_utils.new_configuration.providerSystem.value.lists.list.*;
import de.lcraft.api.java_utils.new_configuration.providerSystem.value.lists.map.*;
import de.lcraft.api.java_utils.new_configuration.providerSystem.value.lists.set.*;

public abstract class ProviderManager {

	private Class<? extends ProviderConverter> providerConvertor;
	private Class<? extends ProviderValidator> providerValidator;

	private Class<? extends ProviderObject> providerObject;
	private Class<? extends ProviderString> providerString;
	private Class<? extends ProviderBoolean> providerBoolean;

	private Class<? extends ProviderNumber> providerNumber;
	private Class<? extends ProviderInteger> providerInteger;
	private Class<? extends ProviderDouble> providerDouble;
	private Class<? extends ProviderLong> providerLong;
	private Class<? extends ProviderByte> providerByte;

	private Class<? extends ProviderList> providerList;
	private Class<? extends ProviderArrayList> providerArrayList;
	private Class<? extends ProviderLinkedList> providerLinkedList;

	private Class<? extends ProviderMap> providerMap;
	private Class<? extends ProviderHashMap> providerHashMap;
	private Class<? extends ProviderHashtable> providerHashtable;
	private Class<? extends ProviderLinkedHashMap> providerLinkedHashMap;
	private Class<? extends ProviderTreeMap> providerTreeMap;

	private Class<? extends ProviderSet> providerSet;
	private Class<? extends ProviderHashSet> providerHashSet;
	private Class<? extends ProviderLinkedHashSet> providerLinkedHashSet;
	private Class<? extends ProviderTreeSet> providerTreeSet;



	public ProviderManager(Class<? extends ProviderConverter> providerConvertor,
						   Class<? extends ProviderValidator> providerValidator,
						   Class<? extends ProviderObject> providerObject,
						   Class<? extends ProviderString> providerString,
						   Class<? extends ProviderBoolean> providerBoolean,
						   Class<? extends ProviderNumber> providerNumber,
						   Class<? extends ProviderInteger> providerInteger,
						   Class<? extends ProviderDouble> providerDouble,
						   Class<? extends ProviderLong> providerLong,
						   Class<? extends ProviderByte> providerByte,
						   Class<? extends ProviderList> providerList,
						   Class<? extends ProviderArrayList> providerArrayList,
						   Class<? extends ProviderLinkedList> providerLinkedList,
						   Class<? extends ProviderMap> providerMap,
						   Class<? extends ProviderHashMap> providerHashMap,
						   Class<? extends ProviderHashtable> providerHashtable,
						   Class<? extends ProviderLinkedHashMap> providerLinkedHashMap,
						   Class<? extends ProviderTreeMap> providerTreeMap,
						   Class<? extends ProviderSet> providerSet,
						   Class<? extends ProviderHashSet> providerHashSet,
						   Class<? extends ProviderLinkedHashSet> providerLinkedHashSet,
						   Class<? extends ProviderTreeSet> providerTreeSet) {
		this.providerConvertor = providerConvertor;
		this.providerValidator = providerValidator;
		this.providerObject = providerObject;
		this.providerString = providerString;
		this.providerBoolean = providerBoolean;
		this.providerNumber = providerNumber;
		this.providerInteger = providerInteger;
		this.providerDouble = providerDouble;
		this.providerLong = providerLong;
		this.providerByte = providerByte;
		this.providerList = providerList;
		this.providerArrayList = providerArrayList;
		this.providerLinkedList = providerLinkedList;
		this.providerMap = providerMap;
		this.providerHashMap = providerHashMap;
		this.providerHashtable = providerHashtable;
		this.providerLinkedHashMap = providerLinkedHashMap;
		this.providerTreeMap = providerTreeMap;
		this.providerSet = providerSet;
		this.providerHashSet = providerHashSet;
		this.providerLinkedHashSet = providerLinkedHashSet;
		this.providerTreeSet = providerTreeSet;
	}



	public Class<? extends ProviderConverter> getProviderConvertor() {
		return providerConvertor;
	}
	public Class<? extends ProviderValidator> getProviderValidator() {
		return providerValidator;
	}
	public Class<? extends ProviderObject> getProviderObject() {
		return providerObject;
	}
	public Class<? extends ProviderString> getProviderString() {
		return providerString;
	}
	public Class<? extends ProviderBoolean> getProviderBoolean() {
		return providerBoolean;
	}

	public Class<? extends ProviderNumber> getProviderNumber() {
		return providerNumber;
	}
	public Class<? extends ProviderInteger> getProviderInteger() {
		return providerInteger;
	}
	public Class<? extends ProviderDouble> getProviderDouble() {
		return providerDouble;
	}
	public Class<? extends ProviderLong> getProviderLong() {
		return providerLong;
	}
	public Class<? extends ProviderByte> getProviderByte() {
		return providerByte;
	}

	public Class<? extends ProviderList> getProviderList() {
		return providerList;
	}
	public Class<? extends ProviderArrayList> getProviderArrayList() {
		return providerArrayList;
	}
	public Class<? extends ProviderLinkedList> getProviderLinkedList() {
		return providerLinkedList;
	}

	public Class<? extends ProviderMap> getProviderMap() {
		return providerMap;
	}
	public Class<? extends ProviderHashMap> getProviderHashMap() {
		return providerHashMap;
	}
	public Class<? extends ProviderHashtable> getProviderHashtable() {
		return providerHashtable;
	}
	public Class<? extends ProviderLinkedHashMap> getProviderLinkedHashMap() {
		return providerLinkedHashMap;
	}
	public Class<? extends ProviderTreeMap> getProviderTreeMap() {
		return providerTreeMap;
	}

	public Class<? extends ProviderSet> getProviderSet() {
		return providerSet;
	}
	public Class<? extends ProviderHashSet> getProviderHashSet() {
		return providerHashSet;
	}
	public Class<? extends ProviderLinkedHashSet> getProviderLinkedHashSet() {
		return providerLinkedHashSet;
	}
	public Class<? extends ProviderTreeSet> getProviderTreeSet() {
		return providerTreeSet;
	}

}
