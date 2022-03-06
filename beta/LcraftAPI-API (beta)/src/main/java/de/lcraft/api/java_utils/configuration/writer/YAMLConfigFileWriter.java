package de.lcraft.api.java_utils.configuration.writer;

import de.lcraft.api.java_utils.FileWriterHelper;
import de.lcraft.api.java_utils.configuration.Config;
import de.lcraft.api.java_utils.configuration.sections.ConfigSection;
import de.lcraft.api.java_utils.configuration.sections.ConfigSectionType;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.IOException;

public class YAMLConfigFileWriter implements ConfigFileWriter {

	@Override
	public void clearCFGFile(Config cfg) {
		try {
			FileWriterHelper helper = new FileWriterHelper(cfg.getFile());
			helper.removeAllLines();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addIntoCFGFile(Config cfg) {
		try {
			YamlConfiguration yaml = new YamlConfiguration();
			yaml.load(cfg.getFile());
			for(ConfigSection c : cfg.getAllConfigurationSections()) {
				c.refreshType();
				if(c.getConfigSectionType() == ConfigSectionType.OnlyValue || c.getConfigSectionType() == ConfigSectionType.ListAndValue) {
					yaml.set(c.getRoot(), c.getValue().getSavedValue());
					if(cfg.isLogging()) {
						System.out.println("-----------------------");
						System.out.println("ConfigurationSection value");
						System.out.println(c.getRoot() + ": " + c.getValue().getSavedValue().toString());
						System.out.println("-----------------------");
					}
					yaml.save(cfg.getFile());
				}
				if(c.getConfigSectionType() == ConfigSectionType.LIST || c.getConfigSectionType() == ConfigSectionType.ListAndValue) {
					for(String root : c.getAllKeys().keySet()) {
						yaml.set(root, c.getAllKeys().get(root).getSavedValue());
						if(cfg.isLogging()) {
							System.out.println("-----------------------");
							System.out.println("ConfigurationSection Key");
							System.out.println(root + ": " + c.getAllKeys().get(root).getSavedValue().toString());
							System.out.println("-----------------------");
						}
						yaml.save(cfg.getFile());
					}
				}
			}
			yaml.save(cfg.getFile());
		} catch (IOException exception) {
			exception.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void loadFromCFGFile(Config cfg) {
		try {
			YamlConfiguration yaml = new YamlConfiguration();
			yaml.load(cfg.getFile());
			for(String root : yaml.getKeys(true)) {
				cfg.set(root, yaml.getString(root).replace("'",""));
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

}
