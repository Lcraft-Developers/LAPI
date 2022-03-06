package de.lcraft.api.java_utils.configuration.writer;

import de.lcraft.api.java_utils.FileWriterHelper;
import de.lcraft.api.java_utils.configuration.Config;
import de.lcraft.api.java_utils.configuration.sections.ConfigSection;
import de.lcraft.api.java_utils.configuration.sections.ConfigSectionType;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.IOException;

public class YAMLConfigFileWriter implements ConfigFileWriter {

	protected void sendDebuggerText(String str) {
		if(isLogging) System.out.println(str);
	}

	@Override
	public void clearCFGFile(Config cfg) {
		try {
			FileWriterHelper helper = new FileWriterHelper(cfg.getFile());
			helper.removeAllLines();
			helper.close();
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
					yaml.save(cfg.getFile());

					sendDebuggerText("- Saving -");
					sendDebuggerText("ConfigurationSection value");
					sendDebuggerText(c.getRoot() + ": " + c.getValue().getSavedValue().toString());
					sendDebuggerText("-----------------------");
				}
				if(c.getConfigSectionType() == ConfigSectionType.LIST || c.getConfigSectionType() == ConfigSectionType.ListAndValue) {
					for(String root : c.getAllKeys().keySet()) {
						yaml.set(root, c.getAllKeys().get(root).getSavedValue());
						yaml.save(cfg.getFile());

						sendDebuggerText("- Saving -");
						sendDebuggerText("ConfigurationSection Key");
						sendDebuggerText(root + ": " + c.getAllKeys().get(root).getSavedValue().toString());
						sendDebuggerText("-----------------------");
					}
				}
			}
			yaml.save(cfg.getFile());
		} catch (IOException exception) {
			exception.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
		sendDebuggerText("");
		sendDebuggerText("");
		sendDebuggerText("");
	}

	@Override
	public void loadFromCFGFile(Config cfg) {
		cfg.resetAllConfigurationSections();
		try {
			YamlConfiguration yaml = new YamlConfiguration();
			yaml.load(cfg.getFile());
			for(String root : yaml.getKeys(true)) {
				String value = yaml.getString(root).replace("'","");
				cfg.set(root, value);

				sendDebuggerText("- Loading -");
				sendDebuggerText("ConfigurationSection value");
				sendDebuggerText(root + ": " + value);
				sendDebuggerText("-----------------------");
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
		sendDebuggerText("");
		sendDebuggerText("");
		sendDebuggerText("");
	}

}
