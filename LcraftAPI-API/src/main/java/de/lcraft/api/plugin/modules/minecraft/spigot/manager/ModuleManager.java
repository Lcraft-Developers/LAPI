package de.lcraft.api.plugin.modules.minecraft.spigot.manager;

import de.lcraft.api.plugin.main.spigot.APIPluginMain;
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
    }

    public void loadAllModules() throws Exception {
        moduleFileLoader.loadModules(pluginMain);
    }

    public void onEnableAllModules() {
        for(Module c : getModules()) {
            try {
                c.onEnable();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onLoadAllModules() throws IOException {
        for(Module c : getModules()) {
            c.onLoad();
        }
    }

    public void onDisableAllModules() throws IOException {
        for(Module c : getModules()) {
            c.onDisable();
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
