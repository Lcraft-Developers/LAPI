package de.lcraft.api.java_utils.configuration;

import de.lcraft.api.java_utils.configuration.sections.ConfigSection;
import de.lcraft.api.java_utils.configuration.sections.ConfigSectionType;
import de.lcraft.api.java_utils.configuration.writer.ConfigFileWriter;
import de.lcraft.api.java_utils.configuration.writer.EasyConfigFileWriter;
import de.lcraft.api.java_utils.configuration.writer.YAMLConfigFileWriter;

public class ConfigTest {

	public static void main(String[] args) {
		testConfig(new YAMLConfigFileWriter());
	}

	public static void testConfig(ConfigFileWriter writer) {
		Config cfg = new Config("testConfig.yml", writer);
		cfg.load();

		cfg.set("arena_count", 20);
		cfg.set("game.name","BedWars");
		cfg.set("game.arena.1.name", "Arena 1");
		cfg.set("game.arena.1.loc.1x", 50);
		cfg.set("game.arena.1.loc.1y", 443);
		cfg.set("game.arena.1.loc.1z", 54);
		cfg.set("game.arena.1.loc.2x", 99);
		cfg.set("game.arena.1.loc.2y", 66);
		cfg.set("game.arena.1.loc.2z", 12);

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
