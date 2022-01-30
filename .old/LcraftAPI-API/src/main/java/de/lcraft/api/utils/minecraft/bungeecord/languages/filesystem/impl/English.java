package de.lcraft.api.utils.minecraft.bungeecord.languages.filesystem.impl;

import de.lcraft.api.utils.minecraft.bungeecord.languages.filesystem.Language;

import java.io.IOException;

public class English extends Language {

    public English() throws IOException {}

    @Override
    public String getName() {
        return "English";
    }

    @Override
    public String getEnglishName() {
        return "English";
    }

    @Override
    public String getShort() {
        return "en";
    }

}
