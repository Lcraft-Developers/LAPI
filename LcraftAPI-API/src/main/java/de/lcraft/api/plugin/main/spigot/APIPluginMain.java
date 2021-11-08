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
            moduleManager.onLoadAllModules();
            Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                @Override
                public void run() {
                    moduleManager.onEnableAllModules();
                }
            }, 5);
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

    public static APIPluginMain getApiPluginMain() {
        return apiPluginMain;
    }
    public ModuleManager getModuleManager() {
        return moduleManager;
    }

}
