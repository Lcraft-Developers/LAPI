package de.lcraft.api.java_utils.configuration.writer;

import de.lcraft.api.java_utils.configuration.Config;

import java.io.IOException;

public interface ConfigFileWriter {

	void clearCFGFile(Config cfg);
	void addIntoCFGFile(Config cfg) throws IOException;
	void loadFromCFGFile(Config cfg) throws IOException;

}
