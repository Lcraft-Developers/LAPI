package lcraft.api.spigot.testplugin;

import de.lcraft.api.plugin.modules.minecraft.spigot.Module;
import lcraft.api.spigot.testplugin.commands.TestCommand;

public class TestPlugin extends Module {

    @Override
    public void onLoad() {
        getModuleCommandManager().addCommand("test-spigot", new TestCommand(this));
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() { }

}
