package de.lcraft.lapi.languagesAPI.api;

import java.util.List;

public interface LanguageManager {

	List<Language> getAllLanguages();
	List<LanguageContainer> getAllLanguageContainer();
	Language getDefaultLanguage();

}
