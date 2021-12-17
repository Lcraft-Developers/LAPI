package de.lcraft.api.minecraft.bungeecord.manager;

import de.lcraft.api.minecraft.bungeecord.manager.loaders.ModuleClassLoader;
import de.lcraft.api.minecraft.bungeecord.manager.loaders.ModuleFileLoader;
import de.lcraft.api.minecraft.bungeecord.manager.loaders.ModuleLoader;
import de.lcraft.api.minecraft.bungeecord.BungeeClass;

import java.io.IOException;
import java.util.ArrayList;

public class ModuleManager {

    private volatile ArrayList<Module> modules = new ArrayList<>();
    private BungeeClass pluginMain;
    private ModuleFileLoader moduleFileLoader;
    private ModuleLoader moduleLoader;

    public ModuleManager(BungeeClass pluginMain) {
        this.pluginMain = pluginMain;

        moduleFileLoader = new ModuleFileLoader(this);
        moduleLoader = new ModuleLoader(this);

        ModuleClassLoader.classLoaders.add(pluginMain.getClass().getClassLoader());
    }

    public void loadAllModules() throws Exception {
        moduleFileLoader.loadModules(pluginMain);
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
    public BungeeClass getPluginMain() {
        return pluginMain;
    }
    public ModuleFileLoader getModuleFileLoader() {
        return moduleFileLoader;
    }
    public ModuleLoader getModuleLoader() {
        return moduleLoader;
    }

}
