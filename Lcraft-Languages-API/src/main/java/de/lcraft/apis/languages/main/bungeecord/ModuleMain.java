package de.lcraft.apis.languages.main.bungeecord;

import de.lcraft.api.plugin.modules.minecraft.bungeecord.manager.Module;
import de.lcraft.apis.languages.system.bungeecord.filesystem.LanguagesManager;
import de.lcraft.apis.languages.system.bungeecord.ModuleCommandManager;

import java.io.IOException;

public class ModuleMain extends Module {

    private static LanguagesManager languagesManager;

    @Override
    public void onLoad() throws IOException {
        languagesManager = new de.lcraft.apis.languages.system.bungeecord.filesystem.LanguagesManager(this, new ModuleCommandManager(new de.lcraft.api.plugin.modules.minecraft.bungeecord.module.commands.ModuleCommandManager(this)));
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
