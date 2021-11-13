package de.lcraft.api.utils.minecraft.spigot.manager;

import de.lcraft.api.utils.minecraft.spigot.SpigotClass;
import de.lcraft.api.utils.minecraft.spigot.manager.classloaders.ModuleClassLoader;
import de.lcraft.api.utils.minecraft.spigot.module.Module;

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
    public void onEnableAllModules() throws IOException {
        for(Module c : getModules()) {
            ModuleEventManager eventManager = new ModuleEventManager(c);
            eventManager.enableModule();
        }
    }
    public void onLoadAllModules() throws IOException {
        for(Module c : getModules()) {
            ModuleEventManager eventManager = new ModuleEventManager(c);
            eventManager.loadModule();
        }
    }
    public void onDisableAllModules() throws IOException {
        for(Module c : getModules()) {
            ModuleEventManager eventManager = new ModuleEventManager(c);
            eventManager.disableModule();
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
