package de.lcraft.api.java_utils.new_configuration.providers;

import de.lcraft.api.java_utils.new_configuration.providerSystem.ProviderManager;

public enum ProviderType {

	JSON(null), YAML(null), DB(null), MySQL(null);

	private ProviderManager providerManager;

	ProviderType(ProviderManager providerManager) {
		this.providerManager = providerManager;
	}

	public ProviderManager getProviderManager() {
		return providerManager;
	}

}

