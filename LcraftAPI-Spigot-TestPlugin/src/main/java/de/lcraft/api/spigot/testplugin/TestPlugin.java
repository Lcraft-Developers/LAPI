package de.lcraft.api.spigot.testplugin;

import de.lcraft.api.plugin.modules.minecraft.spigot.manager.Module;
import de.lcraft.apis.permissions.main.spigot.ModuleMain;
import de.lcraft.api.spigot.testplugin.commands.TestCommand;

public class TestPlugin extends Module {

    @Override
    public void onLoad() {
        ModuleMain.getPermsManager().getCommandManager().addCommand("test-spigot", new TestCommand(this));
    }

    @Override
    public void onEnable() { }

    @Override
    public void onDisable() { }

}
