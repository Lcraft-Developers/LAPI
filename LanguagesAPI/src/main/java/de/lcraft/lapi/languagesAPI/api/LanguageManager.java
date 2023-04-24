package de.lcraft.lapi.languagesAPI.api;

import de.lcraft.lapi.configurationSystem.yaml.Config;

import java.util.List;

public interface LanguageManager {

	TranslatedText translate(Language language, String key);

	void addLanguage(String name, String internationalName, String shortForm);
	void removeLanguage(String shortForm);

	Language getLanguageByName(String name);
	Language getLanguageByInternationName(String internationalName);
	Language getLanguageByShortForm(String shortForm);
	Config getLanguageConfiguration(Language language);

	Language setDefaultLanguage(Language language);

	List<Language> getAllLanguages();
	List<LanguageContainer> getAllLanguageContainers();
	Language getDefaultLanguage();

}
