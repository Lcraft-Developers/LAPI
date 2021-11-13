package de.lcraft.api.utils.minecraft.bungeecord;

import de.lcraft.api.utils.minecraft.bungeecord.languages.filesystem.LanguagesManager;
import de.lcraft.api.utils.minecraft.bungeecord.manager.ModuleManager;
import de.lcraft.api.utils.minecraft.bungeecord.module.utils.configs.Config;
import net.md_5.bungee.api.plugin.Plugin;
import java.io.IOException;

public class BungeeClass extends Plugin {

    private static BungeeClass apiPluginMain;
    private Config cfg;
    private LanguagesManager languagesManager;
    private ModuleManager moduleManager;

    @Override
    public void onEnable() {
        apiPluginMain = this;
        try {
            cfg = new Config("config.yml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        moduleManager = new ModuleManager(apiPluginMain);
        try {
            languagesManager = new LanguagesManager();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            moduleManager.loadAllModules();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            moduleManager.onLoadAllModules();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            moduleManager.onEnableAllModules();
        } catch (IOException e) {
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

    public static BungeeClass getApiPluginMain() {
        return apiPluginMain;
    }
    public Config getCfg() {
        return cfg;
    }

}
