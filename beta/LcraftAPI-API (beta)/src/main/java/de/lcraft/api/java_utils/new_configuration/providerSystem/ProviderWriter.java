package de.lcraft.api.java_utils.new_configuration.providerSystem;

import java.io.File;

public abstract class ProviderWriter {

	private File file;

	public ProviderWriter(File file) {
		this.file = file;
	}

	public abstract void createFileWhenNotExists();
	public abstract void clearFile();
	public abstract void deleteFile();

	public abstract void setAllIntoFile();

	public File getFile() {
		return file;
	}

}
