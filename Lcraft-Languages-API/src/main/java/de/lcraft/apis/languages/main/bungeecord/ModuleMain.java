package de.lcraft.apis.languages.main.bungeecord;

import de.lcraft.api.plugin.modules.minecraft.bungeecord.Module;
import de.lcraft.apis.languages.system.bungeecord.filesystem.LanguagesManager;
import java.io.IOException;

public class ModuleMain extends Module {

    private static LanguagesManager languagesManager;

    @Override
    public void onLoad() throws IOException {
        languagesManager = new LanguagesManager(this);
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    public static LanguagesManager getLanguagesManager() {
        return languagesManager;
    }

}
