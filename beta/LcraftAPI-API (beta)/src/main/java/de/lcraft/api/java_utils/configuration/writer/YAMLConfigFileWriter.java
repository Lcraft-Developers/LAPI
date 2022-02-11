package de.lcraft.api.java_utils.configuration.writer;

import de.lcraft.api.java_utils.FileWriterHelper;
import de.lcraft.api.java_utils.configuration.Config;
import de.lcraft.api.java_utils.configuration.sections.ConfigSection;
import de.lcraft.api.java_utils.configuration.sections.ConfigSectionType;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class YAMLConfigFileWriter implements ConfigFileWriter {

	@Override
	public void clearCFGFile(Config cfg) {
		try {
			cfg.getFile().delete();
			cfg.getFile().createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void addIntoCFGFile(Config cfg) throws IOException {
		/*HashMap<String, ConfigSection> allConfigSections = new HashMap<>();
		for(ConfigSection c : cfg.getAllConfigurationSections()) {
			if(c.getConfigSectionType() == ConfigSectionType.OnlyValue || c.getConfigSectionType() == ConfigSectionType.ListAndValue) {
				allConfigSections.put(c.getRoot(), c);
			}
			if(c.getConfigSectionType() == ConfigSectionType.LIST || c.getConfigSectionType() == ConfigSectionType.ListAndValue) {
				for(String root : c.getAllKeys().keySet()) {
					allConfigSections.put(root, cfg.getSection(root));
				}
			}
		}

		FileWriterHelper writerHelper = new FileWriterHelper(cfg.getFile());*/
	}
	@Override
	public void loadFromCFGFile(Config cfg) {

	}

}
