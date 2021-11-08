package de.lcraft.apis.permissions.utils.spigot;

import de.lcraft.api.plugin.modules.minecraft.spigot.manager.Module;
import java.util.ArrayList;

public abstract class ModuleCommand extends de.lcraft.apis.languages.utils.spigot.ModuleCommand {

    private Module m;

    public ModuleCommand(String command, String desc, String usage, Module m) {
        super(command, desc, usage, m);
        this.m = m;
    }

    public abstract ArrayList<String> allPermissions(ArrayList<String> beforeAllPermissions);

}
