package de.lcraft.apis.permissions.main.spigot;

import de.lcraft.api.plugin.utils.minecraft.spigot.manager.Module;
import de.lcraft.apis.permissions.system.spigot.ModuleCommandManager;
import de.lcraft.apis.permissions.system.spigot.PermsManager;

import java.io.IOException;

public class ModuleMain extends Module {

    private static PermsManager permsManager;

    @Override
    public void onLoad() throws IOException {
        permsManager = new PermsManager(this, new ModuleCommandManager(new de.lcraft.apis.languages.system.spigot.ModuleCommandManager(new de.lcraft.api.plugin.utils.minecraft.spigot.module.commands.ModuleCommandManager(this))));
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    public static PermsManager getPermsManager() {
        return permsManager;
    }

}