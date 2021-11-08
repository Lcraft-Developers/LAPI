package de.lcraft.apis.languages.system.bungeecord.filesystem.impl;

import de.lcraft.apis.languages.system.bungeecord.filesystem.Language;

import java.io.IOException;

public class English extends Language {

    public English(Module m) throws IOException {
        super(m);
    }

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
