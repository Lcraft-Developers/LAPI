package de.lcraft.api.java_utils.configuration.writer;

import de.lcraft.api.java_utils.configuration.Config;

public interface ConfigFileWriter {

	boolean isLogging = true;

	void clearCFGFile(Config cfg);
	void addIntoCFGFile(Config cfg);
	void loadFromCFGFile(Config cfg);

}
