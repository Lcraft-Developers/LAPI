package de.lcraft.api.plugin.modules.minecraft.bungeecord;

public class ModuleLoader {

    Module module;
    public ModuleLoader(Module module) {
        this.module = module;
    }

    public void loadModule() {
        System.out.println("The BungeeCord Module " + module.getName() + " will be loaded.");
        module.onLoad();
        enableModule();
    }
    public void enableModule() {
        System.out.println("The BungeeCord Module " + module.getName() + " is loaded.");
        module.onEnable();
        ModuleManager.modules.add(module);
    }
    public void disableModule() {
        System.out.println("The BungeeCord Module " + module.getName() + " was disabled.");
        module.onDisable();
        ModuleManager.modules.remove(module);
    }

}
