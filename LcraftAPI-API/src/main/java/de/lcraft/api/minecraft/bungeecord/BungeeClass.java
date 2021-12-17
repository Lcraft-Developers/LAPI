package de.lcraft.api.minecraft.bungeecord;

import de.lcraft.api.minecraft.bungeecord.manager.ModuleManager;
import de.lcraft.api.java_utils.Config;
import de.lcraft.api.minecraft.bungeecord.manager.utils.language.LanguagesManager;
import net.md_5.bungee.api.plugin.Plugin;
import java.io.IOException;

public class BungeeClass extends Plugin {

    private static BungeeClass apiPluginMain;
    private Config cfg;
    private ModuleManager moduleManager;

    @Override
    public void onEnable() {
        try {
            apiPluginMain = this;
            cfg = new Config("", "config.yml");

            moduleManager = new ModuleManager(apiPluginMain);
            moduleManager.loadAllModules();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        try {
            moduleManager.onDisableAllModules();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BungeeClass getAPIPluginMain() {
        return apiPluginMain;
    }
    public Config getMainCfg() {
        return cfg;
    }

}
