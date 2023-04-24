package de.lcraft.lapi.languagesAPI;

import de.lcraft.lapi.configurationSystem.yaml.Config;
import de.lcraft.lapi.languagesAPI.api.Language;
import de.lcraft.lapi.languagesAPI.api.LanguageContainer;
import de.lcraft.lapi.languagesAPI.api.TranslatedText;
import java.util.List;

public class LanguageManager implements de.lcraft.lapi.languagesAPI.api.LanguageManager {

	public LanguageManager() {
		init();
	}
	private void init() {

	}

	@Override
	public TranslatedText translate(Language language, String key) {
		return null;
	}

	@Override
	public void addLanguage(String name, String internationalName, String shortForm) {

	}

	@Override
	public void removeLanguage(String shortForm) {

	}

	@Override
	public Language getLanguageByName(String name) {
		return null;
	}

	@Override
	public Language getLanguageByInternationName(String internationalName) {
		return null;
	}

	@Override
	public Language getLanguageByShortForm(String shortForm) {
		return null;
	}

	@Override
	public Config getLanguageConfiguration(Language language) {
		return null;
	}

	@Override
	public Language setDefaultLanguage(Language language) {
		return null;
	}

	@Override
	public List<Language> getAllLanguages() {
		return null;
	}

	@Override
	public List<LanguageContainer> getAllLanguageContainers() {
		return null;
	}

	@Override
	public Language getDefaultLanguage() {
		return null;
	}

}
