package de.lcraft.api.plugin.modules;

public class ModuleLoader {

    Module module;
    public ModuleLoader(Module module) {
        this.module = module;
    }

    public void loadModule() {
        System.out.println("Das Modul " + module.getName() + " wird geladen.");
        module.onLoad();
        enableModule();
    }
    public void enableModule() {
        System.out.println("Das Modul " + module.getName() + " wurde geladen.");
        module.onEnable();
        ModuleManager.modules.add(module);
    }
    public void disableModule() {
        System.out.println("Das Modul " + module.getName() + " wurde deaktiviert.");
        module.onDisable();
        ModuleManager.modules.remove(module);
    }

}
