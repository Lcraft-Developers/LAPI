package de.lcraft.api.minecraft.spigot.utils;

import de.lcraft.api.minecraft.spigot.manager.Module;
import de.lcraft.api.minecraft.spigot.manager.utils.Config;

import java.io.IOException;

public class ModuleConfig extends Config {

    public ModuleConfig(String startPath, String path, String filename) throws IOException {
        super(startPath, path, filename);
    }
    public ModuleConfig(String path, String filename) throws IOException {
        super("lmodules//", path, filename);
    }
    public ModuleConfig(Module m, String path, String filename) throws IOException {
        super("lmodules//" + m.getModuleDescriptionFile().getName(), path, filename);
    }
    public ModuleConfig(Module m, String filename) throws IOException {
        super("lmodules//" + m.getModuleDescriptionFile().getName(), filename);
    }

}
