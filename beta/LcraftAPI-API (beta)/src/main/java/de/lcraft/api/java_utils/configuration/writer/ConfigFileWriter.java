package de.lcraft.api.java_utils.configuration.writer;

import de.lcraft.api.java_utils.configuration.Config;

public abstract class ConfigFileWriter {

	private boolean isLogging = true;

	public abstract void clearCFGFile(Config cfg);
	public abstract void addIntoCFGFile(Config cfg);
	public abstract void loadFromCFGFile(Config cfg);

	protected void sendDebuggerText(String str) {
		if(isLogging()) System.out.println(str);
	}
	public boolean isLogging() {
		return isLogging;
	}

}
