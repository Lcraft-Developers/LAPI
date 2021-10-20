package de.lcraft.api.plugin.modules.minecraft.spigot.utils;

public class ModuleConfig extends Config {

    public ModuleConfig(String startpath, String path, String filename) {
        super(startpath, path, filename);
    }

    public ModuleConfig(Module m, String path, String filename) {
        super("lmodules//" + m.getName(), path, filename);
    }

    public ModuleConfig(Module m, String filename) {
        super("lmodules//" + m.getName(), filename);
    }

}
