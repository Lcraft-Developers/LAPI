package de.lcraft.api.java_utils.new_configuration.providerSystem.value;

public abstract class ProviderObject {

	protected Object object;

	public ProviderObject(Object object) {
		this.object = object;
	}

	public Object getObject() {
		return object;
	}

}
