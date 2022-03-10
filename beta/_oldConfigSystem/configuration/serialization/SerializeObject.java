package de.lcraft.api.java_utils.configuration.serialization;

import java.util.Map;

public interface SerializeObject {

	Map<String, Object> serialize();
	//<V extends SerializeObject> Map<String, V> serialize();
	<T extends SerializeObject> Object deserialize();

}
