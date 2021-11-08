package de.lcraft.api.plugin.main.bungeecord;

import de.lcraft.api.plugin.modules.minecraft.bungeecord.manager.ModuleManager;
import de.lcraft.api.plugin.modules.minecraft.bungeecord.module.configs.Config;
import net.md_5.bungee.api.plugin.Plugin;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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

        moduleManager = new ModuleManager(apiPluginMain);
        try {
            moduleManager.loadAllModules();
            moduleManager.onLoadAllModules();
            getProxy().getScheduler().schedule(this, new Runnable() {
                @Override
                public void run() {
                    moduleManager.onEnableAllModules();
                }
            }, 50, TimeUnit.MILLISECONDS);
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
