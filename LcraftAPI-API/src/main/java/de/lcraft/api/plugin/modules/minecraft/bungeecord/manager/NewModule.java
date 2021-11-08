package de.lcraft.api.plugin.modules.minecraft.bungeecord.manager;

import java.io.File;

public class NewModule {

    private File file;
    private String name;
    private String[] requiredModules;

    public NewModule(File file, String name, String[] requiredModules) {
        this.file = file;
        this.name = name;
        this.requiredModules = requiredModules;
    }

    public File getFile() {
        return file;
    }
    public String getName() {
        return name;
    }
    public String[] getRequiredModules() {
        return requiredModules;
    }

}
