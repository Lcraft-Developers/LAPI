package de.lcraft.api.minecraft.spigot.module.manager;

import de.lcraft.api.minecraft.spigot.module.manager.loaders.ModuleFileLoader;
import de.lcraft.api.minecraft.spigot.SpigotClass;

import java.util.ArrayList;

public class ModuleManager {

    private final ArrayList<Module> modules = new ArrayList<>();
    private final SpigotClass pluginMain;
    private final ModuleFileLoader moduleFileLoader;

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
