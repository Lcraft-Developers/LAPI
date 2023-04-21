package de.lcraft.lapi.languagesAPI;

import de.lcraft.lapi.languagesAPI.api.Language;

public class TranslatedText implements de.lcraft.lapi.languagesAPI.api.TranslatedText {

	private Language language;
	private String key;
	private String translation;

	public TranslatedText(Language language, String key, String translation) {
		init(language, key, translation);
	}
	private void init(Language language, String key, String translation) {
		if(language != null) setLanguage(language);
		else setLanguage(null); // TODO: Default Language

		if(key != null) setKey(key);
		else setKey("");

		if(translation != null) setTranslation(translation);
		else setTranslation("");
	}

	@Override
	public Language getLanguage() {
		return language;
	}
	@Override
	public String getKey() {
		return key;
	}
	@Override
	public String getTranslation() {
		return translation;
	}

	private void setLanguage(Language language) {
		this.language = language;
	}
	private void setKey(String key) {
		this.key = key;
	}
	private void setTranslation(String translation) {
		this.translation = translation;
	}

}
