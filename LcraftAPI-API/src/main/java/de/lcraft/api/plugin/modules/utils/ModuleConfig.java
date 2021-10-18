package de.lcraft.api.plugin.modules.utils;

import de.lcraft.api.plugin.utils.Config;

public class ModuleConfig extends Config {

    public ModuleConfig(String startpath, String path, String filename) {
        super(startpath, path, filename);
    }

    public ModuleConfig(Module m, String path, String filename) {
        super("modules//" + m.getName(), path, filename);
    }

    public ModuleConfig(Module m, String filename) {
        super("modules//" + m.getName(), filename);
    }

}
