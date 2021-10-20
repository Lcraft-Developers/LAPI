package de.lcraft.api.plugin.modules.minecraft.spigot;

public class ModuleLoader {

    Module module;
    public ModuleLoader(Module module) {
        this.module = module;
    }

    public void loadModule() {
        System.out.println("The Spigot Module " + module.getName() + " will be loaded.");
        module.onLoad();
        enableModule();
    }
    public void enableModule() {
        System.out.println("The Spigot Module " + module.getName() + " is loaded.");
        module.onEnable();
        ModuleManager.modules.add(module);
    }
    public void disableModule() {
        System.out.println("The Spigot Module " + module.getName() + " was disabled.");
        module.onDisable();
        ModuleManager.modules.remove(module);
    }

}
