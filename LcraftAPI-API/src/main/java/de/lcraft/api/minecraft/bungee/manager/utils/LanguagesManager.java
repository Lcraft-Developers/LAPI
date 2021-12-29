package de.lcraft.api.minecraft.bungee.manager.utils;

import de.lcraft.api.minecraft.bungee.manager.ModuleConfig;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class LanguagesManager {

	private ArrayList<Language> addedLanguages;
	private ModuleConfig fileConfig,
			             userConfig;

	public LanguagesManager() throws IOException {
		fileConfig = new ModuleConfig("Lcraft Languages", "config.yml");
		userConfig = new ModuleConfig("Lcraft Languages", "users.yml");
		addedLanguages = new ArrayList<>();
	}

	public void setIDLanguage(int id, Language lang) throws IOException {
		if(lang == null) lang = getMainLanguage();
		userConfig.cfg().set("users." + id + ".lang", lang.getShortLanguage().toLowerCase());
		userConfig.save();
	}
	public boolean hasIDAnLanguage(int id) {
		return userConfig.cfg().contains("users." + id + ".lang");
	}
	public Language getIDLanguage(int id) throws IOException {
		if(hasIDAnLanguage(id)) {
			return getAllLanguageByFullShort(userConfig.cfg().getString("users." + id + ".lang"));
		} else {
			return getMainLanguage();
		}
	}

	public int getIDFromString(String normal) {
		int id = 0;
		int bid = 0;
		for(String c : normal.split("")) {
			bid++;
			id = id + (bid * 3);
			if(c.equals("a")) {
				bid = bid + 1;
				id = id + bid;
			} else if(c.equals("b")) {
				bid = bid + 2;
				id = id + bid;
			} else if(c.equals("c")) {
				bid = bid + 3;
				id = id + bid;
			} else if(c.equals("d")) {
				bid = bid + 4;
				id = id + bid;
			} else if(c.equals("e")) {
				bid = bid + 5;
				id = id + bid;
			} else if(c.equals("f")) {
				bid = bid + 6;
				id = id + bid;
			} else if(c.equals("g")) {
				bid = bid + 7;
				id = id + bid;
			} else if(c.equals("h")) {
				bid = bid + 8;
				id = id + bid;
			} else if(c.equals("i")) {
				bid = bid + 9;
				id = id + bid;
			} else if(c.equals("j")) {
				bid = bid + 10;
				id = id + bid;
			} else if(c.equals("k")) {
				bid = bid + 11;
				id = id + bid;
			} else if(c.equals("l")) {
				bid = bid + 12;
				id = id + bid;
			} else if(c.equals("m")) {
				bid = bid + 13;
				id = id + bid;
			} else if(c.equals("n")) {
				bid = bid + 14;
				id = id + bid;
			} else if(c.equals("o")) {
				bid = bid + 15;
				id = id + bid;
			} else if(c.equals("p")) {
				bid = bid + 16;
				id = id + bid;
			} else if(c.equals("q")) {
				bid = bid + 17;
				id = id + bid;
			} else if(c.equals("r")) {
				bid = bid + 18;
				id = id + bid;
			} else if(c.equals("s")) {
				bid = bid + 19;
				id = id + bid;
			} else if(c.equals("t")) {
				bid = bid + 20;
				id = id + bid;
			} else if(c.equals("u")) {
				bid = bid + 21;
				id = id + bid;
			} else if(c.equals("v")) {
				bid = bid + 22;
				id = id + bid;
			} else if(c.equals("w")) {
				bid = bid + 23;
				id = id + bid;
			} else if(c.equals("x")) {
				bid = bid + 24;
				id = id + bid;
			} else if(c.equals("y")) {
				bid = bid + 25;
				id = id + bid;
			} else if(c.equals("z")) {
				bid = bid + 26;
				id = id + bid;
			} else if(c.equals("A")) {
				bid = bid + 27;
				id = id + bid;
			} else if(c.equals("B")) {
				bid = bid + 28;
				id = id + bid;
			} else if(c.equals("C")) {
				bid = bid + 29;
				id = id + bid;
			} else if(c.equals("D")) {
				bid = bid + 30;
				id = id + bid;
			} else if(c.equals("E")) {
				bid = bid + 31;
				id = id + bid;
			} else if(c.equals("F")) {
				bid = bid + 32;
				id = id + bid;
			} else if(c.equals("G")) {
				bid = bid + 33;
				id = id + bid;
			} else if(c.equals("H")) {
				bid = bid + 34;
				id = id + bid;
			} else if(c.equals("I")) {
				bid = bid + 35;
				id = id + bid;
			} else if(c.equals("J")) {
				bid = bid + 36;
				id = id + bid;
			} else if(c.equals("K")) {
				bid = bid + 37;
				id = id + bid;
			} else if(c.equals("L")) {
				bid = bid + 38;
				id = id + bid;
			} else if(c.equals("M")) {
				bid = bid + 39;
				id = id + bid;
			} else if(c.equals("N")) {
				bid = bid + 40;
				id = id + bid;
			} else if(c.equals("O")) {
				bid = bid + 41;
				id = id + bid;
			} else if(c.equals("P")) {
				bid = bid + 42;
				id = id + bid;
			} else if(c.equals("Q")) {
				bid = bid + 43;
				id = id + bid;
			} else if(c.equals("R")) {
				bid = bid + 44;
				id = id + bid;
			} else if(c.equals("S")) {
				bid = bid + 45;
				id = id + bid;
			} else if(c.equals("T")) {
				bid = bid + 46;
				id = id + bid;
			} else if(c.equals("U")) {
				bid = bid + 47;
				id = id + bid;
			} else if(c.equals("V")) {
				bid = bid + 48;
				id = id + bid;
			} else if(c.equals("W")) {
				bid = bid + 49;
				id = id + bid;
			} else if(c.equals("X")) {
				bid = bid + 50;
				id = id + bid;
			} else if(c.equals("Y")) {
				bid = bid + 51;
				id = id + bid;
			} else if(c.equals("Z")) {
				bid = bid + 52;
				id = id + bid;
			} else if(c.equals("0")) {
				bid = bid + 53;
				id = id + bid;
			} else if(c.equals("1")) {
				bid = bid + 54;
				id = id + bid;
			} else if(c.equals("2")) {
				bid = bid + 55;
				id = id + bid;
			} else if(c.equals("3")) {
				bid = bid + 56;
				id = id + bid;
			} else if(c.equals("4")) {
				bid = bid + 57;
				id = id + bid;
			} else if(c.equals("5")) {
				bid = bid + 58;
				id = id + bid;
			} else if(c.equals("6")) {
				bid = bid + 59;
				id = id + bid;
			} else if(c.equals("7")) {
				bid = bid + 60;
				id = id + bid;
			} else if(c.equals("8")) {
				bid = bid + 61;
				id = id + bid;
			} else if(c.equals("9")) {
				bid = bid + 62;
				id = id + bid;
			} else if(c.equals("&")) {
				bid = bid + 63;
				id = id + bid;
			} else if(c.equals("%")) {
				bid = bid + 64;
				id = id + bid;
			} else if(c.equals("$")) {
				bid = bid + 65;
				id = id + bid;
			} else if(c.equals("§")) {
				bid = bid + 66;
				id = id + bid;
			} else if(c.equals("(")) {
				bid = bid + 67;
				id = id + bid;
			} else if(c.equals(")")) {
				bid = bid + 68;
				id = id + bid;
			} else if(c.equals("[")) {
				bid = bid + 69;
				id = id + bid;
			} else if(c.equals("]")) {
				bid = bid + 70;
				id = id + bid;
			} else if(c.equals("=")) {
				bid = bid + 71;
				id = id + bid;
			} else if(c.equals("?")) {
				bid = bid + 72;
				id = id + bid;
			} else if(c.equals("/")) {
				bid = bid + 73;
				id = id + bid;
			} else if(c.equals(".")) {
				bid = bid + 74;
				id = id + bid;
			} else if(c.equals(",")) {
				bid = bid + 75;
				id = id + bid;
			} else if(c.equals(";")) {
				bid = bid + 76;
				id = id + bid;
			} else if(c.equals(":")) {
				bid = bid + 77;
				id = id + bid;
			} else if(c.equals("!")) {
				bid = bid + 78;
				id = id + bid;
			} else if(c.equals("<")) {
				bid = bid + 79;
				id = id + bid;
			} else if(c.equals(">")) {
				bid = bid + 80;
				id = id + bid;
			} else if(c.equals("+")) {
				bid = bid + 81;
				id = id + bid;
			} else if(c.equals("-")) {
				bid = bid + 82;
				id = id + bid;
			} else if(c.equals("*")) {
				bid = bid + 83;
				id = id + bid;
			} else if(c.equals("³")) {
				bid = bid + 84;
				id = id + bid;
			} else if(c.equals("{")) {
				bid = bid + 85;
				id = id + bid;
			} else if(c.equals("}")) {
				bid = bid + 86;
				id = id + bid;
			} else if(c.equals("`")) {
				bid = bid + 87;
				id = id + bid;
			} else if(c.equals("´")) {
				bid = bid + 88;
				id = id + bid;
			} else if(c.equals("~")) {
				bid = bid + 89;
				id = id + bid;
			} else if(c.equals("ä")) {
				bid = bid + 90;
				id = id + bid;
			} else if(c.equals("ö")) {
				bid = bid + 91;
				id = id + bid;
			} else if(c.equals("ü")) {
				bid = bid + 92;
				id = id + bid;
			} else if(c.equals("Ä")) {
				bid = bid + 93;
				id = id + bid;
			} else if(c.equals("Ö")) {
				bid = bid + 94;
				id = id + bid;
			}
		}
		return bid + id;
	}
	public int getIDFromUUID(UUID uuid) {
		return getIDFromString(uuid.toString());
	}
	public int getIDFromPlayer(ProxiedPlayer p) {
		return getIDFromUUID(p.getUniqueId());
	}
	public Language getMainLanguage() throws IOException {
		if(hasIDAnLanguage(0)) {
			return getIDLanguage(0);
		} else {
			setIDLanguage(0, getAllLanguageByShortLanguage("en", "be"));
			return getMainLanguage();
		}
	}

	public Language addExtraLanguage(String name, String englishName, String shortLanguage, String shortLanguageType) throws IOException {
		if(!existsLanguage(shortLanguageType, shortLanguageType) && !existsAddedLanguage(shortLanguage, shortLanguageType)) {
			Language c = new Language(this) {
				@Override
				public String getName() {
					return name;
				}

				@Override
				public String getEnglishName() {
					return englishName;
				}

				@Override
				public String getShortLanguage() {
					return shortLanguage;
				}

				@Override
				public String getShortLanguageType() {
					return shortLanguageType;
				}
			};
			addedLanguages.add(c);
			return c;
		}
		return null;
	}
	public boolean existsLanguage(String shortLanguage, String shortLanguageType) throws IOException {
		for(Language c : getAllLanguages()) {
			if((c.getShortLanguage().toLowerCase().equalsIgnoreCase(shortLanguage) && c.getShortLanguageType().toLowerCase().equalsIgnoreCase(shortLanguageType))) {
				return true;
			}
		}
		return false;
	}
	public boolean existsAddedLanguage(String shortLanguage, String shortLanguageType) {
		for(Language c : getAddedLanguages()) {
			if((c.getShortLanguage().toLowerCase().equalsIgnoreCase(shortLanguage) && c.getShortLanguageType().toLowerCase().equalsIgnoreCase(shortLanguageType))) {
				return true;
			}
		}
		return false;
	}
	public ArrayList<Language> getAllLanguages() throws IOException {
		ArrayList<Language> allLanguages = new ArrayList<>();

		Language en_us = new Language(this) {
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
		Language en_be = new Language(this) {
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
		Language de_hd = new Language(this) {
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
		Language de_ch = new Language(this) {
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
		Language de_öster = new Language(this) {
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
		}; allLanguages.add(de_öster);

		return allLanguages;
	}
	public ArrayList<Language> getAllLanguagesAndAdded() throws IOException {
		ArrayList<Language> languages = getAllLanguages();
		for(Language c : getAddedLanguages()) {
			languages.add(c);
		}
		return languages;
	}

	public Language getLanguageByName(String name) throws IOException {
		for(Language c : getAllLanguages()) {
			if(c.getName().equalsIgnoreCase(name)) return c;
		}
		return null;
	}
	public Language getLanguageByFullShort(String short_) throws IOException {
		for(Language c : getAllLanguages()) {
			if(c.getShortLanguage().toLowerCase().equalsIgnoreCase(short_)) return c;
		}
		return null;
	}
	public Language getLanguageByShortLanguage(String shortLanguage, String shortLanguageType) throws IOException {
		for(Language c : getAllLanguages()) {
			if(c.getShortLanguage().toLowerCase().equalsIgnoreCase(shortLanguage) && c.getShortLanguageType().toLowerCase().equalsIgnoreCase(shortLanguageType)) return c;
		}
		return null;
	}
	public Language getLanguageByEnglishName(String englishName) throws IOException {
		for(Language c : getAllLanguages()) {
			if(c.getEnglishName().equalsIgnoreCase(englishName)) return c;
		}
		return null;
	}

	public Language getAddedLanguageByName(String name) {
		for(Language c : getAddedLanguages()) {
			if(c.getName().equalsIgnoreCase(name)) return c;
		}
		return null;
	}
	public Language getAddedLanguageByFullShort(String short_) {
		for(Language c : getAddedLanguages()) {
			if(c.getShortLanguage().toLowerCase().equalsIgnoreCase(short_)) return c;
		}
		return null;
	}
	public Language getAddedLanguageByShortLanguage(String shortLanguage, String shortLanguageType) {
		for(Language c : getAddedLanguages()) {
			if(c.getShortLanguage().toLowerCase().equalsIgnoreCase(shortLanguage) && c.getShortLanguageType().toLowerCase().equalsIgnoreCase(shortLanguageType)) return c;
		}
		return null;
	}
	public Language getAddedLanguageByEnglishName(String englishName) {
		for(Language c : getAddedLanguages()) {
			if(c.getEnglishName().equalsIgnoreCase(englishName)) return c;
		}
		return null;
	}

	public Language getAllLanguageByName(String name) throws IOException {
		for(Language c : getAllLanguagesAndAdded()) {
			if(c.getName().equalsIgnoreCase(name)) return c;
		}
		return null;
	}
	public Language getAllLanguageByFullShort(String short_) throws IOException {
		for(Language c : getAllLanguagesAndAdded()) {
			if(c.getShortLanguage().toLowerCase().equalsIgnoreCase(short_)) return c;
		}
		return null;
	}
	public Language getAllLanguageByShortLanguage(String shortLanguage, String shortLanguageType) throws IOException {
		for(Language c : getAllLanguagesAndAdded()) {
			if(c.getShortLanguage().toLowerCase().equalsIgnoreCase(shortLanguage) && c.getShortLanguageType().toLowerCase().equalsIgnoreCase(shortLanguageType)) return c;
		}
		return null;
	}
	public Language getAllLanguageByEnglishName(String englishName) throws IOException {
		for(Language c :getAllLanguagesAndAdded()) {
			if(c.getEnglishName().equalsIgnoreCase(englishName)) return c;
		}
		return null;
	}

	public ArrayList<Language> getAddedLanguages() {
		return addedLanguages;
	}
	public ModuleConfig getFileConfig() {
		return fileConfig;
	}
	public ModuleConfig getUserConfig() {
		return userConfig;
	}

	public abstract class Language {

		private ModuleConfig translations;
		private ModuleConfig help;
		private ModuleConfig cfg;
		private LanguagesManager languagesManager;

		public Language(LanguagesManager languagesManager) throws IOException {
			translations = new ModuleConfig("Lcraft Languages/" + getShort(), "translations.yml");
			help = new ModuleConfig("Lcraft Languages/" + getShort(), "help.yml");
			cfg = new ModuleConfig("Lcraft Languages/" + getShort(), "config.yml");
			this.languagesManager = languagesManager;
		}

		public abstract String getName();
		public abstract String getEnglishName();
		public abstract String getShortLanguage();
		public abstract String getShortLanguageType();

		public String translate(String def) {
			String root = "translate." + languagesManager.getIDFromString(def);
			translations.cfg().set(root + ".default", def);
			if(translations.cfg().contains(root + ".translation")) {
				def = translations.cfg().getString(root + ".translation");
			} else {
				translations.cfg().set(root + ".translation", def);
			}
			translations.save();
			return def;
		}
		public String[] getHelp() {
			String[] help = new String[1];
			help[0] = "No Help Message seted in Language " + getEnglishName();
			if(getHelpFile().cfg().getSection(getShort() + ".help") != null) {
				help = new String[getHelpFile().cfg().getSection(getShort() + ".help").getKeys().size()];
				for(int i = 0; i < getHelpFile().cfg().getSection(getShort() + ".help").getKeys().size(); i++) {
					help[i] = getHelpFile().cfg().getString(getShort() + ".help." + i);
				}
			}
			return help;
		}
		public ModuleConfig getTranslationsFile() {
			return translations;
		}
		public ModuleConfig getHelpFile() {
			return help;
		}
		public String getShort() {
			return getShortLanguage() + "-" + getShortLanguageType();
		}

	}

}
