package de.lcraft.api.utils.minecraft.bungeecord.languages.filesystem.impl;

import de.lcraft.api.utils.minecraft.bungeecord.languages.filesystem.Language;

import java.io.IOException;

public class German extends Language {

    public German() throws IOException {}

    @Override
    public String getName() {
        return "Deutsch";
    }

    @Override
    public String getEnglishName() {
        return "German";
    }

    @Override
    public String getShort() {
        return "de";
    }

}
