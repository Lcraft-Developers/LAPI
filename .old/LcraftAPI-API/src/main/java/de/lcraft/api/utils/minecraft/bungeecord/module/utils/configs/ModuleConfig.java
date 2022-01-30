package de.lcraft.api.utils.minecraft.bungeecord.module.utils.configs;

import de.lcraft.api.utils.minecraft.bungeecord.module.Module;

import java.io.IOException;

public class ModuleConfig extends Config {

    public ModuleConfig(String startpath, String path, String filename) throws IOException {
        super(startpath, path, filename);
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
