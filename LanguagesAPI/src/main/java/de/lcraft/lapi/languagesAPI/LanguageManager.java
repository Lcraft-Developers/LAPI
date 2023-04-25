package de.lcraft.lapi.languagesAPI;

import de.lcraft.lapi.configurationSystem.yaml.Config;
import de.lcraft.lapi.languagesAPI.api.Language;
import de.lcraft.lapi.languagesAPI.api.LanguageContainer;
import de.lcraft.lapi.languagesAPI.api.TranslatedText;

import java.util.ArrayList;
import java.util.List;

public class LanguageManager implements de.lcraft.lapi.languagesAPI.api.LanguageManager {

	private Config configuration;
	private List<Language> languages;
	private List<LanguageContainer> languageContainers;

	public LanguageManager(Config configuration) {
		init(configuration);
	}
	public LanguageManager() {
		init(null);
	}
	private void init(Config configuration) {
		setLanguages(new ArrayList<>());
		setLanguageContainers(new ArrayList<>());

		if(configuration != null) setConfiguration(configuration);
		else setConfiguration(new Config());
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
	public Language getDefaultLanguage() {
		return null;
	}



	private void setConfiguration(Config configuration) {
		this.configuration = configuration;
	}
	private void setLanguages(List<Language> languages) {
		this.languages = languages;
	}
	private void setLanguageContainers(List<LanguageContainer> languageContainers) {
		this.languageContainers = languageContainers;
	}

	public Config getConfiguration() {
		return configuration;
	}
	@Override
	public List<Language> getAllLanguages() {
		return languages;
	}
	@Override
	public List<LanguageContainer> getAllLanguageContainers() {
		return languageContainers;
	}

}
