package de.lcraft.api.plugin.modules.minecraft.bungeecord.utils;

import java.io.IOException;

public class ModuleConfig extends Config {

    public ModuleConfig(String startpath, String path, String filename) throws IOException {
        super(startpath, path, filename);
    }

    public ModuleConfig(Module m, String path, String filename) throws IOException {
        super("lmodules//" + m.getName(), path, filename);
    }

    public ModuleConfig(Module m, String filename) throws IOException {
        super("lmodules//" + m.getName(), filename);
    }

}
