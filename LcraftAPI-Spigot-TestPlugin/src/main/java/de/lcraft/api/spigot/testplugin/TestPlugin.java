package de.lcraft.api.spigot.testplugin;

import de.lcraft.api.spigot.testplugin.listeners.JoinListener;
import de.lcraft.api.utils.minecraft.spigot.module.Module;
import de.lcraft.api.spigot.testplugin.commands.TestCommand;
import java.io.IOException;

public class TestPlugin extends Module {

    @Override
    public void onLoad() throws IOException {
        getModuleCommandManager().addCommand(new TestCommand(this));

        getListenerManager().addListener(new JoinListener());
        getListenerManager().registerAllListeners();
    }

    @Override
    public void onEnable() { }

    @Override
    public void onDisable() { }

}
