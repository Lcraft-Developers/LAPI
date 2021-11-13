package de.lcraft.api.plugin.main.spigot;

import de.lcraft.api.plugin.modules.minecraft.spigot.manager.ModuleManager;
import de.lcraft.api.plugin.modules.minecraft.spigot.module.configs.Config;
import de.lcraft.api.plugin.modules.minecraft.spigot.module.server.ServerTPS;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class APIPluginMain extends JavaPlugin {

    private static APIPluginMain apiPluginMain;
    private Config cfg;
    private ModuleManager moduleManager;
    private ServerTPS serverTPS;

    @Override
    public void onEnable() {
        apiPluginMain = this;
        cfg = new Config("config.yml");
        serverTPS = new ServerTPS(apiPluginMain);

        moduleManager = new ModuleManager(apiPluginMain);
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

    public static APIPluginMain getApiPluginMain() {
        return apiPluginMain;
    }
    public ModuleManager getModuleManager() {
        return moduleManager;
    }

}
