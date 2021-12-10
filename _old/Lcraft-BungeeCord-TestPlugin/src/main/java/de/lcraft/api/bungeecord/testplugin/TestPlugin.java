package de.lcraft.api.bungeecord.testplugin;

import de.lcraft.api.bungeecord.testplugin.commands.TestCommand;
import de.lcraft.api.bungeecord.testplugin.listeners.JoinListener;
import de.lcraft.api.utils.minecraft.bungeecord.module.Module;
import java.io.IOException;

public class TestPlugin extends Module {

    @Override
    public void onLoad() {}

    @Override
    public void onEnable() throws IOException {
        getModuleCommandManager().addCommand(new TestCommand(this), true);

        getListenerManager().addListener(new JoinListener());
        getListenerManager().registerAllListeners();
    }

    @Override
    public void onDisable() { }

}
