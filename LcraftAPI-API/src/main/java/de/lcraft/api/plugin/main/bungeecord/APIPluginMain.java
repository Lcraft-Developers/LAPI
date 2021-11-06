package de.lcraft.api.plugin.main.bungeecord;

import de.lcraft.api.plugin.modules.minecraft.bungeecord.ModuleManager;
import de.lcraft.api.plugin.modules.minecraft.bungeecord.utils.Config;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.IOException;

public class APIPluginMain extends Plugin {

    private static APIPluginMain apiPluginMain;
    private Config cfg;
    private ModuleManager moduleManager;

    @Override
    public void onEnable() {
        apiPluginMain = this;
        try {
            cfg = new Config("config.yml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        moduleManager = new ModuleManager();
        moduleManager.loadModules(this);
        try {
            moduleManager.onLoadAllModules();
        } catch (IOException e) {
            e.printStackTrace();
        }

        moduleManager.onEnableAllModules();
    }

    @Override
    public void onDisable() {
        moduleManager.onDisableAllModules();
    }

    public static APIPluginMain getApiPluginMain() {
        return apiPluginMain;
    }

}
