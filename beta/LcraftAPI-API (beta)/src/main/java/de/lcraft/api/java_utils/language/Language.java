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
		getJoinMessage(); getQuitMessage(); getHelp();
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
	public final void setJoinMessage(String[] joinMessage) {
		for(int i = 0; i < joinMessage.length; i++) {
			getConfig().set(getShort() + ".joinMessage." + i, joinMessage[i]);
		}
		save();
	}
	public final String[] getJoinMessage() {
		String[] joinMessage = new String[1];
		joinMessage[0] = "No Join Message seted in " + getEnglishName();
		if(Objects.nonNull(getConfig().getSection(getShort() + ".joinMessage"))) {
			joinMessage = new String[getConfig().getSection(getShort() + ".joinMessage").size()];
			for(int i = 0; i < getConfig().getSection(getShort() + ".joinMessage").size(); i++) {
				joinMessage[i] = getConfig().getString(getShort() + ".joinMessage." + i);
			}
		} else {
			setJoinMessage(joinMessage);
			return getJoinMessage();
		}
		return joinMessage;
	}
	public final void setQuitMessage(String[] quitMessage) {
		for(int i = 0; i < quitMessage.length; i++) {
			getConfig().set(getShort() + ".quitMessage." + i, quitMessage[i]);
		}
		save();
	}
	public final String[] getQuitMessage() {
		String[] quitMessage = new String[1];
		quitMessage[0] = "No Quit Message setted in " + getEnglishName();
		if(Objects.nonNull(getConfig().getSection(getShort() + ".quitMessage"))) {
			quitMessage = new String[getConfig().getSection(getShort() + ".quitMessage").size()];
			for(int i = 0; i < getConfig().getSection(getShort() + ".quitMessage").size(); i++) {
				quitMessage[i] = getConfig().getString(getShort() + ".quitMessage." + i);
			}
		} else {
			setQuitMessage(quitMessage);
			return getQuitMessage();
		}
		save();
		return quitMessage;
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