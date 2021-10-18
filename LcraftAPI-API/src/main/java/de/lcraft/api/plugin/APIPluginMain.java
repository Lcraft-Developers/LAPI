package de.lcraft.api.plugin;

import de.lcraft.api.plugin.modules.ModuleManager;
import de.lcraft.api.plugin.utils.Config;
import org.bukkit.plugin.java.JavaPlugin;

public class APIPluginMain extends JavaPlugin {

    private APIPluginMain apiPluginMain;
    private Config cfg;
    private ModuleManager moduleManager;

    @Override
    public void onEnable() {
        apiPluginMain = this;
        cfg = new Config("config.yml");
        moduleManager = new ModuleManager();
        moduleManager.loadModules();
        moduleManager.onLoadAllModules();

        moduleManager.onEnableAllModules();
    }

    @Override
    public void onDisable() {
        moduleManager.onDisableAllModules();
    }

    public APIPluginMain getApiPluginMain() {
        return apiPluginMain;
    }

}
