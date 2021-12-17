package de.lcraft.api.minecraft.spigot;

import de.lcraft.api.minecraft.spigot.manager.Config;
import de.lcraft.api.minecraft.spigot.manager.ModuleManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.IOException;

public class SpigotClass extends JavaPlugin {

    private static SpigotClass apiPluginMain;
    private Config cfg;
    private ModuleManager moduleManager;

    @Override
    public void onEnable() {
        try {
            apiPluginMain = this;
            cfg = new Config("", "config.yml");

            moduleManager = new ModuleManager(apiPluginMain);
            moduleManager.loadAllModules();
        } catch (IOException e) {
            e.printStackTrace();
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

    public static SpigotClass getAPIPluginMain() {
        return apiPluginMain;
    }
    public Config getMainCfg() {
        return cfg;
    }

}
