package de.lcraft.api.utils.minecraft.spigot.module.utils.configs;

import de.lcraft.api.utils.minecraft.spigot.module.Module;

public class ModuleConfig extends Config {

    public ModuleConfig(String startpath, String path, String filename) {
        super(startpath, path, filename);
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
