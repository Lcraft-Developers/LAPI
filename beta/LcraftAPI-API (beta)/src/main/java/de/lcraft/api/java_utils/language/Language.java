package de.lcraft.api.java_utils.language;

import de.lcraft.api.java_utils.configuration.Config;
import java.util.Objects;

public abstract class Language {

	private Config translations;
	private Config help;
	private Config cfg;
	private LanguagesManager languagesManager;

	public Language(LanguagesManager languagesManager) {
		translations = new Config("Lcraft Languages/" + getShort(), "translations.yml");
		help = new Config("Lcraft Languages/" + getShort(), "help.yml");
		cfg = new Config("Lcraft Languages/" + getShort(), "config.yml");
		this.languagesManager = languagesManager;
		getHelp();
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
	public final void setHelpMessage(String[] helpMessage) {
		for(int i = 0; i < helpMessage.length; i++) {
			getHelpFile().set(getShort() + ".helpMessage." + i, helpMessage[i]);
		}
		save();
	}
	public final String[] getHelp() {
		String[] help = new String[1];
		help[0] = "No Help Message seted in Language " + getEnglishName();
		if(Objects.nonNull(getHelpFile().getSection(getShort() + ".helpMessage"))) {
			help = new String[getHelpFile().getSection(getShort() + ".helpMessage").size()];
			for(int i = 0; i < getHelpFile().getSection(getShort() + ".helpMessage").size(); i++) {
				help[i] = getHelpFile().getString(getShort() + ".helpMessage." + i);
			}
		} else {
			setHelpMessage(help);
			return getHelp();
		}
		save();
		return help;
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