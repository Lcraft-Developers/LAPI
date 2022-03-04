package de.lcraft.api.java_utils.configuration.writer;

import de.lcraft.api.java_utils.FileWriterHelper;
import de.lcraft.api.java_utils.configuration.Config;
import de.lcraft.api.java_utils.configuration.ConfigValue;
import de.lcraft.api.java_utils.configuration.sections.ConfigSection;
import de.lcraft.api.java_utils.configuration.sections.ConfigSectionType;

import java.io.IOException;

public class EasyConfigFileWriter implements ConfigFileWriter {

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
			FileWriterHelper writerHelper = new FileWriterHelper(cfg.getFile());
			for(ConfigSection c : cfg.getAllConfigurationSections()) {
				c.refreshType();
				if(c.getConfigSectionType() == ConfigSectionType.OnlyValue || c.getConfigSectionType() == ConfigSectionType.ListAndValue) {
					writerHelper.addLine(c.getRoot() + ": " + c.getValue().convertToString());
				}
				if(c.getConfigSectionType() == ConfigSectionType.LIST || c.getConfigSectionType() == ConfigSectionType.ListAndValue) {
					for(String root : c.getAllKeys().keySet()) {
						writerHelper.addLine(root + ": " + c.getAllKeys().get(root).convertToString());
					}
				}
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	@Override
	public void loadFromCFGFile(Config cfg) {
		try {
			FileWriterHelper writerHelper = new FileWriterHelper(cfg.getFile());
			for(String line : writerHelper.getAllLines()) {
				String root = line.split(": ")[0];
				String value = line.split(": ")[1];
				cfg.set(root, value);
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

}
