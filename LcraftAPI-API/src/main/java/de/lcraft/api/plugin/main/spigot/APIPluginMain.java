package de.lcraft.api.plugin.main.spigot;

import de.lcraft.api.plugin.modules.minecraft.spigot.ModuleManager;
import de.lcraft.api.plugin.utils.Config;
import org.bukkit.plugin.java.JavaPlugin;

public class APIPluginMain extends JavaPlugin {

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
