package de.lcraft.api.spigot.testplugin;

import de.lcraft.api.utils.minecraft.spigot.module.Module;
import de.lcraft.api.spigot.testplugin.commands.TestCommand;

import java.io.IOException;

public class TestPlugin extends Module {

    @Override
    public void onLoad() throws IOException {
        getModuleCommandManager().addCommand(new TestCommand(this));
    }

    @Override
    public void onEnable() { }

    @Override
    public void onDisable() { }

}
