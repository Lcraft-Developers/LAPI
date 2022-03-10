package de.lcraft.api.java_utils.language;

import de.lcraft.api.java_utils.configuration.Config;

import java.util.Objects;

public abstract class Language {

	private final Config translations;
	private final Config help;
	private final Config cfg;
	private final LanguagesManager languagesManager;

	public Language(LanguagesManager languagesManager) {
		translations = new Config("lmodules", "Lcraft Languages//" + getShort(), "translations.yml");
		help = new Config("lmodules", "Lcraft Languages//" + getShort(), "help.yml");
		cfg = new Config("lmodules", "Lcraft Languages//" + getShort(), "config.yml");
		this.languagesManager = languagesManager;
		getTranslationsFile().load();
		getHelpFile().load();
		getConfig().load();
	}

	public abstract String getName();
	public abstract String getEnglishName();
	public abstract String getShortLanguage();
	public abstract String getShortLanguageType();

	public final String translate(String def) {
		String root = "translate." + getLanguagesManager().getIDFromString(def);
		getTranslationsFile().set(root + ".default", def);
		if(getTranslationsFile().exists(root + ".translation")) {
			def = getTranslationsFile().getString(root + ".translation");
		} else {
			getTranslationsFile().set(root + ".translation", def);
		}
		save();
		return def;
	}

	public final void setMessage(String root, String[] helpMessage) {
		String mainRoot = getShort() + "." + root;
		for(int i = 0; i < helpMessage.length; i++) {
			getHelpFile().set(mainRoot + "." + i, helpMessage[i]);
		}
		save();
	}
	public final String[] getMessage(String root, String[] defaultMessage) {
		String mainRoot = getShort() + "." + root;
		if(Objects.nonNull(getHelpFile().getSection(mainRoot))) {
			defaultMessage = new String[getHelpFile().getSection(mainRoot).size()];
			for(int i = 0; i < getHelpFile().getSection(mainRoot).size(); i++) {
				defaultMessage[i] = getHelpFile().getString(mainRoot + "." + i);
			}
		} else {
			setMessage(root, defaultMessage);
			return getMessage(root, defaultMessage);
		}
		save();
		return defaultMessage;
	}

	public final Config getTranslationsFile() {
		return translations;
	}
	public Config getConfig() {
		return cfg;
	}
	public void save() {
		getHelpFile().save();
		getTranslationsFile().save();
		getConfig().save();
	}
	public final Config getHelpFile() {
		return help;
	}
	public final String getShort() {
		return getShortLanguage() + "-" + getShortLanguageType();
	}
	public LanguagesManager getLanguagesManager() {
		return languagesManager;
	}

}