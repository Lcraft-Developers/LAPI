package de.lcraft.api.java_utils.serialize;

import java.util.HashMap;
import java.util.Map;

public class TestSerializeObject implements SerializeObject {

	private int sheeesh = 10;

	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> map = new HashMap<>();
		map.put("sheeesh", sheeesh);
		return map;
	}

	@Override
	public <T extends SerializeObject> TestSerializeObject deserialize() {
		TestSerializeObject testClass = new TestSerializeObject();
		testClass.setSheeesh(2);
		return testClass;
	}

	public int getSheeesh() {
		return sheeesh;
	}
	private void setSheeesh(int sheeesh) {
		this.sheeesh = sheeesh;
	}

}
