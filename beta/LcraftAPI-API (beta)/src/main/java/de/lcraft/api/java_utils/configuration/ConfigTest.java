package de.lcraft.api.java_utils.configuration;

import de.lcraft.api.java_utils.configuration.sections.ConfigSection;
import de.lcraft.api.java_utils.configuration.sections.ConfigSectionType;
import de.lcraft.api.java_utils.configuration.writer.EasyConfigFileWriter;

public class ConfigTest {

	public static void main(String[] args) {
		Config cfg1 = new Config("testEasy.yml", new EasyConfigFileWriter());
		cfg1.load();
		cfg1.set("test.lpd", 2);
		cfg1.set("test", 1);
		cfg1.set("test.lpd.nice", "Hallo");
		cfg1.save();

		Config cfg2 = new Config("testEasy.yml", new EasyConfigFileWriter());
		cfg2.load();
		for(ConfigSection c : cfg2.getAllConfigurationSections()) {
			if(c.getConfigSectionType() == ConfigSectionType.OnlyValue || c.getConfigSectionType() == ConfigSectionType.OnlyValue) {
				System.out.println(c.getRoot() + ": " + c.getValue().convertFromString(c.getValue().getSavedValue().toString()));
			}
			if(c.getConfigSectionType() == ConfigSectionType.LIST || c.getConfigSectionType() == ConfigSectionType.ListAndValue) {
				if(!c.getAllKeys().isEmpty()) {
					for(ConfigValue key : c.getAllKeys().values()) {
						System.out.println(c.getRoot() + ": " + key.convertFromString(key.getSavedValue().toString()));
					}
				}
			}
		}

		/*System.out.println("\nNext Test:\n");


		Config cfg3 = new Config("testYaml.yml", new YAMLConfigFileWriter());
		cfg3.load();
		cfg3.set("test.lpd", 2);
		cfg3.set("test", 1);
		cfg3.set("test.lpd.nice", "Hallo");
		cfg3.save();

		Config cfg4 = new Config("testYaml.yml", new YAMLConfigFileWriter());
		cfg4.load();
		for(ConfigSection c : cfg4.getAllConfigurationSections()) {
			if(c.getConfigSectionType() == ConfigSectionType.OnlyValue || c.getConfigSectionType() == ConfigSectionType.OnlyValue) {
				System.out.println(c.getRoot() + ": " + c.getValue().convertFromString(c.getValue().getSavedValue().toString()));
			}
			if(c.getConfigSectionType() == ConfigSectionType.LIST || c.getConfigSectionType() == ConfigSectionType.ListAndValue) {
				if(!c.getAllKeys().isEmpty()) {
					for(ConfigValue key : c.getAllKeys().values()) {
						System.out.println(c.getRoot() + ": " + key.convertFromString(key.getSavedValue().toString()));
					}
				}
			}
		}*/
	}

}
