package de.lcraft.api.java_utils.configuration;

import de.lcraft.api.java_utils.configuration.sections.ConfigSection;
import de.lcraft.api.java_utils.configuration.sections.ConfigSectionType;

public class ConfigTest {

	public static void main(String[] args) {
		Config cfg = new Config("yamlConfig.yml");
		cfg.load();

		cfg.set("abc","ee");
		cfg.set("aabc.eee", "gg");
		cfg.set("aaa", "890");

		cfg.save();
		cfg.load();

		for(ConfigSection c : cfg.getAllConfigurationSections()) {
			if(c.getConfigSectionType() == ConfigSectionType.ListAndValue || c.getConfigSectionType() == ConfigSectionType.OnlyValue) {
				System.out.println(c.getRoot() + ": " + c.getValue().convertToString());
			}
			if(c.getConfigSectionType() == ConfigSectionType.ListAndValue ||c.getConfigSectionType() == ConfigSectionType.LIST) {
				for(String key : c.getAllKeys().keySet()) {
					System.out.println(key + ": " + c.getAllKeys().get(key).convertToString());
				}
			}
		}
	}

}
