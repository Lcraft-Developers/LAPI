package de.lcraft.api.plugin.main.bungeecord;

import de.lcraft.api.plugin.modules.minecraft.bungeecord.ModuleManager;
import de.lcraft.api.plugin.utils.Config;
import net.md_5.bungee.api.plugin.Plugin;

public class APIPluginMain extends Plugin {

    private static APIPluginMain apiPluginMain;
    private Config cfg;
    private ModuleManager moduleManager;

    @Override
    public void onEnable() {
        apiPluginMain = this;
        cfg = new Config("config.yml");
        moduleManager = new ModuleManager();
        moduleManager.loadModules(this);
        moduleManager.onLoadAllModules();

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
