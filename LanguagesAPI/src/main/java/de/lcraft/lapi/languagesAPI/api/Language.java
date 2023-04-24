package de.lcraft.lapi.languagesAPI.api;

import de.lcraft.lapi.configurationSystem.yaml.Config;

public interface Language {

	String getShortForm();
    String getName();
	String getNameInternational();
	Config getConfiguration();

}
