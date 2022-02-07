package de.lcraft.api.minecraft.spigot.manager.util.language;

import de.lcraft.api.minecraft.spigot.manager.configs.ModuleBukkitConfig;

import java.util.Objects;

public abstract class Language {

	private ModuleBukkitConfig translations;
	private ModuleBukkitConfig help;
	private ModuleBukkitConfig cfg;
	private LanguagesManager languagesManager;

	public Language(LanguagesManager languagesManager) {
		translations = new ModuleBukkitConfig("Lcraft Languages/" + getShort(), "translations.yml");
		help = new ModuleBukkitConfig("Lcraft Languages/" + getShort(), "help.yml");
		cfg = new ModuleBukkitConfig("Lcraft Languages/" + getShort(), "config.yml");
		this.languagesManager = languagesManager;
	}

	public abstract String getName();
	public abstract String getEnglishName();
	public abstract String getShortLanguage();
	public abstract String getShortLanguageType();

	public final String translate(String def) {
		String root = "translate." + languagesManager.getIDFromString(def);
		translations.set(root + ".default", def);
		if(translations.exists(root + ".translation")) {
			def = translations.getString(root + ".translation");
		} else {
			translations.set(root + ".translation", def);
		}
		return def;
	}
	public final String getJoinMessage() {
		String root = "msgs.join";
		if(cfg.exists(root)) {
			return cfg.getString(root);
		} else {
			translations.set(root, "§6%PLAYER% §6joined the game");
			translations.save();
		}
		return getJoinMessage();
	}
	public final String getQuitMessage() {
		String root = "msgs.quit";
		if(cfg.exists(root)) {
			return cfg.getString(root);
		} else {
			translations.set(root, "§6%PLAYER% §6leaved the game");
			translations.save();
		}
		return getQuitMessage();
	}

	public final String[] getHelp() {
		String[] help = new String[1];
		help[0] = "No Help Message seted in Language " + getEnglishName();
		if(Objects.nonNull(getHelpFile().getSection(getShort() + ".help"))) {
			help = new String[getHelpFile().getSection(getShort() + ".help").getKeys(false).size()];
			for(int i = 0; i < getHelpFile().getSection(getShort() + ".help").getKeys(false).size(); i++) {
				help[i] = getHelpFile().getString(getShort() + ".help." + i);
			}
		}
		return help;
	}
	public final ModuleBukkitConfig getTranslationsFile() {
		return translations;
	}
	public final ModuleBukkitConfig getHelpFile() {
		return help;
	}
	public final String getShort() {
		return getShortLanguage() + "-" + getShortLanguageType();
	}

}