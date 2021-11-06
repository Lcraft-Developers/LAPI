package de.lcraft.apis.permissions.utils.bungeecord;

import de.lcraft.api.plugin.modules.minecraft.bungeecord.Module;
import java.util.ArrayList;

public abstract class ModuleCommand extends de.lcraft.apis.languages.utils.bungeecord.ModuleCommand {

    private Module m;

    public ModuleCommand(String command, Module m) {
        super(command, m);
        this.m = m;
    }

    public abstract ArrayList<String> allPermissions(ArrayList<String> beforeAllPermissions);

}
