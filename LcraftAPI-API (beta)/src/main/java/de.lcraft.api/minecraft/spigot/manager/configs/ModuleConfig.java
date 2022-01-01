package de.lcraft.api.minecraft.spigot.manager.configs;

import de.lcraft.api.minecraft.spigot.manager.Module;

import java.io.IOException;

public class ModuleConfig extends Config {

    public ModuleConfig(String startPath, String path, String filename) {
        super(startPath, path, filename);
    }
    public ModuleConfig(String path, String filename) {
        super("lmodules//", path, filename);
    }
    public ModuleConfig(Module m, String path, String filename) {
        super("lmodules//" + m.getModuleDescriptionFile().getName(), path, filename);
    }
    public ModuleConfig(Module m, String filename) {
        super("lmodules//" + m.getModuleDescriptionFile().getName(), filename);
    }

}
