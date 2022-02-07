package de.lcraft.api.minecraft.spigot.manager.utils.language;

import java.util.ArrayList;

public class DefaultLanguages {

	public static final ArrayList<Language> getAllDefaultLanguages(LanguagesManager languagesManager) {
		ArrayList<Language> allLanguages = new ArrayList<>();

		Language en_us = new Language(languagesManager) {
			@Override
			public String getName() {
				return "American English";
			}

			@Override
			public String getEnglishName() {
				return "American English";
			}

			@Override
			public String getShortLanguage() {
				return "en";
			}

			@Override
			public String getShortLanguageType() {
				return "us";
			}
		}; allLanguages.add(en_us);
		Language en_be = new Language(languagesManager) {
			@Override
			public String getName() {
				return "British English";
			}

			@Override
			public String getEnglishName() {
				return "British English";
			}

			@Override
			public String getShortLanguage() {
				return "en";
			}

			@Override
			public String getShortLanguageType() {
				return "be";
			}
		}; allLanguages.add(en_be);
		Language de_hd = new Language(languagesManager) {
			@Override
			public String getName() {
				return "Deutsch";
			}

			@Override
			public String getEnglishName() {
				return "German";
			}

			@Override
			public String getShortLanguage() {
				return "de";
			}

			@Override
			public String getShortLanguageType() {
				return "hd";
			}
		}; allLanguages.add(de_hd);
		/*Language de_ch = new Language(languagesManager) {
			@Override
			public String getName() {
				return "Schwizerdütsch";
			}

			@Override
			public String getEnglishName() {
				return "Swiss German";
			}

			@Override
			public String getShortLanguage() {
				return "de";
			}

			@Override
			public String getShortLanguageType() {
				return "schw";
			}
		}; allLanguages.add(de_ch);
		Language de_oester = new Language(languagesManager) {
			@Override
			public String getName() {
				return "Österreichisches Deutsch";
			}

			@Override
			public String getEnglishName() {
				return "Austrian German";
			}

			@Override
			public String getShortLanguage() {
				return "de";
			}

			@Override
			public String getShortLanguageType() {
				return "öster";
			}
		}; allLanguages.add(de_oester);*/

		return allLanguages;
	}

}
