package de.lcraft.api.java_utils.new_configuration;

import de.lcraft.api.java_utils.CodeHelper;
import de.lcraft.api.java_utils.new_configuration.providerSystem.ProviderManager;
import de.lcraft.api.java_utils.new_configuration.providers.ProviderType;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Config {

	private final File file;
	private final File folder;
	private ProviderType providerType;

	public Config(String startPath, String path, String filename, ProviderType providerType) {
		if(startPath.endsWith("//")) {
			startPath = CodeHelper.replaceEnd(startPath,"//","");
		}
		if(path.startsWith("//")) {
			path = path.replaceFirst("//","");
		}
		if(!startPath.isEmpty() && !startPath.isBlank()) {
			folder = new File(startPath + "//" + path);

			if(Objects.nonNull(folder) && Objects.nonNull(startPath) && Objects.nonNull(path)) {
				file = new File(folder, filename);
			} else {
				file = new File(filename);
			}
		} else if(!path.isEmpty() && !path.isBlank()) {
			folder = new File(path);

			if(Objects.nonNull(folder) && Objects.nonNull(startPath) && Objects.nonNull(path)) {
				file = new File(folder, filename);
			} else {
				file = new File(filename);
			}
		} else {
			folder = new File("");

			file = new File(filename);
		}
		if(!folder.exists()) {
			folder.mkdir();
			folder.mkdirs();
		}
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.providerType = providerType;
		//load();
	}
	public Config(String startPath, String path, String filename) {
		this(startPath, path, filename, ProviderType.YAML);
	}
	public Config(String path, String filename, ProviderType providerType) {
		this("", path, filename, providerType);
	}
	public Config(String path, String filename) {
		this("", path, filename);
	}
	public Config(String filename, ProviderType providerType) {
		this("", filename, providerType);
	}
	public Config(String filename) {
		this("", filename);
	}

	public void load() {
		getProviderManager().load();
	}
	public void save() {
		getProviderManager().save();
	}

	public ProviderManager getProviderManager() {
		return providerType.getProviderManager();
	}
	public ProviderType getProviderType() {
		return providerType;
	}

}
