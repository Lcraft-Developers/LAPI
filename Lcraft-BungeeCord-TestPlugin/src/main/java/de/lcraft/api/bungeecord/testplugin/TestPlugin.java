package de.lcraft.api.bungeecord.testplugin;

import de.lcraft.api.bungeecord.testplugin.commands.TestCommand;
import de.lcraft.api.utils.minecraft.bungeecord.module.Module;
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
