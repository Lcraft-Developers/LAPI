package de.lcraft.api.minecraft.spigot.manager;

import de.lcraft.api.minecraft.spigot.SpigotClass;
import de.lcraft.api.minecraft.spigot.manager.loaders.ModuleClassLoader;
import de.lcraft.api.minecraft.spigot.manager.loaders.ModuleFileLoader;
import de.lcraft.api.minecraft.spigot.manager.loaders.ModuleLoader;

import java.io.IOException;
import java.util.ArrayList;

public class ModuleManager {

    private volatile ArrayList<Module> modules = new ArrayList<>();
    private SpigotClass pluginMain;
    private ModuleFileLoader moduleFileLoader;
    private ModuleLoader moduleLoader;

    public ModuleManager(SpigotClass pluginMain) {
        this.pluginMain = pluginMain;

        moduleFileLoader = new ModuleFileLoader(this);
        moduleLoader = new ModuleLoader(this);

        ModuleClassLoader.classLoaders.add(pluginMain.getClass().getClassLoader());
    }

    public void loadAllModules() throws Exception {
        moduleFileLoader.loadModules(pluginMain);
    }
    public void onDisableAllModules() throws IOException {
        if(!getModules().isEmpty()) {
            for(Module c : getModules()) {
                ModuleEventManager eventManager = new ModuleEventManager(c);
                eventManager.disableModule();
            }
        }
    }

    public ArrayList<Module> getModules() {
        return modules;
    }
    public SpigotClass getPluginMain() {
        return pluginMain;
    }
    public ModuleFileLoader getModuleFileLoader() {
        return moduleFileLoader;
    }
    public ModuleLoader getModuleLoader() {
        return moduleLoader;
    }

}
