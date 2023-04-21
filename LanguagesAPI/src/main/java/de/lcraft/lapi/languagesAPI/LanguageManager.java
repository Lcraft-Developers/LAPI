package de.lcraft.lapi.languagesAPI;

import de.lcraft.lapi.languagesAPI.api.Language;
import de.lcraft.lapi.languagesAPI.api.LanguageContainer;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.reflections.scanners.Scanners.SubTypes;

public class LanguageManager implements de.lcraft.lapi.languagesAPI.api.LanguageManager {

	private List<Language> allLanguages;
	private List<LanguageContainer> allLanguageContainer;
	private Language defaultLanguage;

	private void init(Language defaultLanguage) {
		setAllLanguages(new ArrayList<>());
		setAllLanguageContainer(new ArrayList<>());

		initLanguages();
		initLanguageContainer();

		if(defaultLanguage != null) setDefaultLanguage(defaultLanguage);
		else setDefaultLanguage(null); // TODO: Add default Language
	}
	private void initLanguages() {
		Reflections reflections = new Reflections("de.lcraft.lapi.languagesAPI");
		Set<Class<? extends Language>> subTypes = reflections.getSubTypesOf(Language.class);
		for(Class<? extends Language> classes : subTypes) {
			getAllLanguages().add(Language.class.cast(classes));
		}
	}
	private void initLanguageContainer() {
		Reflections reflections = new Reflections("de.lcraft.lapi.languagesAPI");
		Set<Class<? extends LanguageContainer>> subTypes = reflections.getSubTypesOf(LanguageContainer.class);
		for(Class<? extends LanguageContainer> classes : subTypes) {
			getAllLanguageContainer().add(LanguageContainer.class.cast(classes));
		}
	}

	// TODO: Add methods to get specific Languages

	@Override
	public List<Language> getAllLanguages() {
		return allLanguages;
	}
	@Override
	public List<LanguageContainer> getAllLanguageContainer() {
		return allLanguageContainer;
	}
	@Override
	public Language getDefaultLanguage() {
		return defaultLanguage;
	}

	private void setAllLanguages(List<Language> allLanguages) {
		this.allLanguages = allLanguages;
	}
	private void setAllLanguageContainer(List<LanguageContainer> allLanguageContainer) {
		this.allLanguageContainer = allLanguageContainer;
	}
	private void setDefaultLanguage(Language defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}

}
