package de.lcraft.api.java_utils.language;

import java.util.ArrayList;

public abstract class LanguageContainer {

	public static final ArrayList<LanguageContainer> allTranslations = new ArrayList<>();

	public LanguageContainer() {
		allTranslations.add(this);
	}

	protected abstract ArrayList<String> allUsedTranslatedText();
	public ArrayList<String> getAllTranslations() {
		return allUsedTranslatedText();
	}

}
