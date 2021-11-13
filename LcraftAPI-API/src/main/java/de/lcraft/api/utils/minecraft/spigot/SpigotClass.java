package de.lcraft.api.utils.minecraft.spigot;

import de.lcraft.api.utils.minecraft.spigot.languages.filesystem.LanguagesManager;
import de.lcraft.api.utils.minecraft.spigot.manager.ModuleManager;
import de.lcraft.api.utils.minecraft.spigot.module.utils.configs.Config;
import de.lcraft.api.utils.minecraft.spigot.module.utils.server.ServerTPS;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class SpigotClass extends JavaPlugin {

    private static SpigotClass apiPluginMain;
    private Config cfg;
    private LanguagesManager languagesManager;
    private ModuleManager moduleManager;
    private ServerTPS tps;

    @Override
    public void onEnable() {
        apiPluginMain = this;
        cfg = new Config("config.yml");
        tps = new ServerTPS(apiPluginMain);
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

    public static SpigotClass getApiPluginMain() {
        return apiPluginMain;
    }
    public Config getCfg() {
        return cfg;
    }
    public ServerTPS getTps() {
        return tps;
    }

}
