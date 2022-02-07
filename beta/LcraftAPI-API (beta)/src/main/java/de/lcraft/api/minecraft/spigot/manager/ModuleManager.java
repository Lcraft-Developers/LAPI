package de.lcraft.api.minecraft.spigot.manager;

import de.lcraft.api.minecraft.spigot.manager.loaders.ModuleFileLoader;
import de.lcraft.api.minecraft.spigot.SpigotClass;
import lombok.Getter;

import java.util.ArrayList;

public class ModuleManager {

    @Getter
    private volatile ArrayList<Module> modules = new ArrayList<>();
    @Getter
    private SpigotClass pluginMain;
    @Getter
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

}
