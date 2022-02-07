package de.lcraft.api.minecraft.spigot.manager.configs;

import de.lcraft.api.minecraft.spigot.manager.Module;

public class ModuleBukkitConfig extends BukkitConfig {

    public ModuleBukkitConfig(String startPath, String path, String filename) {
        super(startPath, path, filename);
    }
    public ModuleBukkitConfig(String path, String filename) {
        super("lmodules//", path, filename);
    }
    public ModuleBukkitConfig(Module m, String path, String filename) {
        super("lmodules//" + m.getModuleDescriptionFile().getName(), path, filename);
    }
    public ModuleBukkitConfig(Module m, String filename) {
        super("lmodules//" + m.getModuleDescriptionFile().getName(), filename);
    }

}
