package de.lcraft.api.plugin.main.spigot;

import de.lcraft.api.plugin.modules.minecraft.spigot.ModuleManager;
import de.lcraft.api.plugin.modules.minecraft.spigot.utils.Config;
import de.lcraft.api.plugin.modules.minecraft.spigot.utils.ServerTPS;
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
