package de.lcraft.api.java_utils.configuration.writer.json;

import de.lcraft.api.java_utils.configuration.Config;
import de.lcraft.api.java_utils.configuration.writer.ConfigFileWriter;

import java.io.IOException;

public class JSONConfigWriter extends ConfigFileWriter {

	@Override
	public void clearCFGFile(Config cfg) {
		/*try {
			FileWriterHelper helper = new FileWriterHelper(cfg.getFile());
			helper.removeAllLines();
			helper.close();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	@Override
	public void addIntoCFGFile(Config cfg) {
		/*try {
			FileWriterHelper writerHelper = new FileWriterHelper(cfg.getFile());
			for(ConfigSection c : cfg.getAllConfigurationSections()) {
				c.refreshType();
				if(c.getConfigSectionType() == ConfigSectionType.OnlyValue || c.getConfigSectionType() == ConfigSectionType.ListAndValue) {
					writerHelper.addLine(c.getRoot() + ": " + c.getValue().getSavedValue().toString());

					sendDebuggerText("- Saving -");
					sendDebuggerText("ConfigurationSection value");
					sendDebuggerText(c.getRoot() + ": " + c.getValue().getSavedValue().toString());
					sendDebuggerText("-----------------------");
				}
				if(c.getConfigSectionType() == ConfigSectionType.LIST || c.getConfigSectionType() == ConfigSectionType.ListAndValue) {
					for(ConfigValue value : c.getAllKeysWithoutValue()) {
						writerHelper.addLine(value.getRoot() + ": " + value.getSavedValue().toString());

						sendDebuggerText("- Saving -");
						sendDebuggerText("ConfigurationSection Key");
						sendDebuggerText(value.getRoot() + ": " + value.getSavedValue().toString());
						sendDebuggerText("-----------------------");
					}
				}
			}
			writerHelper.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}*/
		sendDebuggerText("");
		sendDebuggerText("");
		sendDebuggerText("");
	}
	@Override
	public void loadFromCFGFile(Config cfg) {
		cfg.resetAllConfigurationSections();
		/*try {
			FileWriterHelper writerHelper = new FileWriterHelper(cfg.getFile());
			for(String line : writerHelper.getAllLines()) {
				if(line.split(": ").length > 1) {
					String root = line.split(": ")[0];
					String value = line.split(": ")[1];
					cfg.set(root, value);

					sendDebuggerText("- Loading -");
					sendDebuggerText("ConfigurationSection value");
					sendDebuggerText(root + ": " + value);
					sendDebuggerText("-----------------------");
				}
			}
			writerHelper.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}*/
		sendDebuggerText("");
		sendDebuggerText("");
		sendDebuggerText("");
	}

}
