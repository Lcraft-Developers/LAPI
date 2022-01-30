package de.lcraft.api.minecraft.spigot.manager;

import de.lcraft.api.minecraft.spigot.SpigotClass;
import de.lcraft.api.minecraft.spigot.manager.loaders.ModuleClassLoader;
import de.lcraft.api.minecraft.spigot.manager.loaders.ModuleFileLoader;
import de.lcraft.api.minecraft.spigot.player.LPlayerManager;

import java.io.IOException;
import java.util.ArrayList;

public class ModuleManager {

    private volatile ArrayList<Module> modules = new ArrayList<>();
    private SpigotClass pluginMain;
    private ModuleFileLoader moduleFileLoader;

    public ModuleManager(SpigotClass pluginMain) {
        this.pluginMain = pluginMain;
        moduleFileLoader = new ModuleFileLoader(this);
    }

    public final void loadAllModules() {
        moduleFileLoader.loadModules(pluginMain);
    }
    public final void onDisableAllModules() {
        if(!getModules().isEmpty()) {
            for(Module c : getModules()) {
                c.disableModule();
            }
        }
    }

    public final ArrayList<Module> getModules() {
        return modules;
    }
    public final SpigotClass getPluginMain() {
        return pluginMain;
    }
    public final ModuleFileLoader getModuleFileLoader() {
        return moduleFileLoader;
    }

}
