package de.lcraft.api.plugin.modules;

public abstract class Module {

    private String name,
                   id;

    public Module(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public abstract void onLoad();
    public abstract void onEnable();
    public abstract void onDisable();

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

}
