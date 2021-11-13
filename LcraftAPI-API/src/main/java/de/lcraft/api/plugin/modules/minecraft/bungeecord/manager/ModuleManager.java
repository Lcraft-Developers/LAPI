package de.lcraft.api.plugin.modules.minecraft.bungeecord.manager;

import de.lcraft.api.plugin.main.bungeecord.APIPluginMain;
import de.lcraft.api.plugin.modules.minecraft.bungeecord.manager.classloaders.ModuleClassLoader;
import java.io.IOException;
import java.util.ArrayList;

public class ModuleManager {

    private volatile ArrayList<Module> modules = new ArrayList<>();
    private APIPluginMain pluginMain;
    private ModuleFileLoader moduleFileLoader;
    private ModuleLoader moduleLoader;

    public ModuleManager(APIPluginMain pluginMain) {
        this.pluginMain = pluginMain;

        moduleFileLoader = new ModuleFileLoader(this);
        moduleLoader = new ModuleLoader(this);

        // BungeeCord Classloader adding to mine
        //ModuleClassLoader.classLoaders.add(pluginMain.getClass().getClassLoader());
        // Spigot Classloader adding to mine
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
            eventManager.enableModule();
        }
    }
    public void onDisableAllModules() throws IOException {
        for(Module c : getModules()) {
            ModuleEventManager eventManager = new ModuleEventManager(c);
            eventManager.enableModule();
        }
    }

    public ArrayList<Module> getModules() {
        return modules;
    }
    public APIPluginMain getPluginMain() {
        return pluginMain;
    }
    public ModuleFileLoader getModuleFileLoader() {
        return moduleFileLoader;
    }
    public ModuleLoader getModuleLoader() {
        return moduleLoader;
    }

}
