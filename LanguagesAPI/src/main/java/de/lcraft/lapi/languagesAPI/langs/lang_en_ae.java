package de.lcraft.lapi.languagesAPI.langs;

import de.lcraft.lapi.languagesAPI.api.Language;

public class lang_en_ae implements Language {

	private String name;
	private String nameEnglish;
	private String langShort;
	private String shortType;

	public lang_en_ae() {
		init("American English", "American English", "en", "ae");
	}
	public lang_en_ae(String name, String nameEnglish, String langShort, String shortType) {
		init(name, nameEnglish, langShort, shortType);
	}
	private void init(String name, String nameEnglish, String langShort, String shortType) {
		if(name != null) setName(name);
		else setName("");

		if(nameEnglish != null) setNameEnglish(nameEnglish);
		else setNameEnglish("");

		if(langShort != null) setShort(langShort);
		else setShort("");

		if(shortType != null) setShortType(shortType);
		else setShortType("");
	}

	@Override
	public String getName() {
		return name;
	}
	@Override
	public String getNameEnglish() {
		return nameEnglish;
	}
	@Override
	public String getShort() {
		return langShort;
	}
	@Override
	public String getShortType() {
		return shortType;
	}

	private void setName(String name) {
		this.name = name;
	}
	private void setNameEnglish(String nameEnglish) {
		this.nameEnglish = nameEnglish;
	}
	private void setShort(String lang_short) {
		this.langShort = lang_short;
	}
	private void setShortType(String shortType) {
		this.shortType = shortType;
	}

}
