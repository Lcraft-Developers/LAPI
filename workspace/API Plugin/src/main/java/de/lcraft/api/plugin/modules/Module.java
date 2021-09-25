package de.lcraft.api.plugin.modules;

import de.lcraft.api.plugin.logger.Logger;

public abstract class Module {

    private String name,
                          id;
    private Logger logger;

    public Module(String name, String id) {
        this.name = name;
        this.id = id;
        logger = new Logger(getName());
    }

    public abstract void onLoad();
    public abstract void onEnable();
    public abstract void onDisable();

    public String getName() {
        return name;
    }
    public Logger getLogger() {
        return logger;
    }
    public String getId() {
        return id;
    }

}
