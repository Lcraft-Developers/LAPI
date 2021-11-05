package de.lcraft.apis.languages.system.bungeecord.filesystem.impl;

import de.lcraft.api.plugin.modules.minecraft.bungeecord.Module;
import de.lcraft.apis.languages.system.bungeecord.filesystem.Language;

import java.io.IOException;

public class German extends Language {

    public German(Module m) throws IOException {
        super(m);
    }

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
        return "da";
    }

}
