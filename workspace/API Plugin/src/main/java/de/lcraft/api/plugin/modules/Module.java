package de.lcraft.api.plugin.modules;

import de.lcraft.api.plugin.logger.Logger;

public abstract class Module {

    public String name,
                  id;
    public Logger logger;
    public ModuleManager moduleManager;

    public Module(String name, String id) {
        this.name = name;
        this.id = id;
        logger = new Logger(getName());
    }

    public abstract void onLoad();
    public abstract void onEnable();
    public abstract void onDisable();

    public abstract String getName();
    public abstract Logger getLogger();
    public abstract String getId();

    public void setModuleManager(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }
    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public boolean existsModule(String name) {
        for(Module m : ModuleManager.modules) {
            if(m.getName().equals(name) || m.getName().equalsIgnoreCase(name) || m.getId().equals(name) || m.getId().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

}
