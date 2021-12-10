package de.lcraft.apis.permissions.main.bungeecord;

import de.lcraft.api.plugin.utils.minecraft.bungeecord.manager.Module;
import de.lcraft.apis.permissions.system.bungeecord.ModuleCommandManager;
import de.lcraft.apis.permissions.system.bungeecord.PermsManager;

import java.io.IOException;

public class ModuleMain extends Module {

    private static PermsManager permsManager;

    @Override
    public void onLoad() throws IOException {
        permsManager = new PermsManager(this, new ModuleCommandManager(new de.lcraft.apis.languages.system.bungeecord.ModuleCommandManager(new de.lcraft.api.plugin.utils.minecraft.bungeecord.module.commands.ModuleCommandManager(this))));
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