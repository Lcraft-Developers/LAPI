package lcraft.api.testplugin;

import de.lcraft.api.plugin.modules.minecraft.spigot.Module;
import lcraft.api.testplugin.commands.TestCommand;

public class TestPlugin extends Module {

    public TestPlugin() {
        super("Test Plugin", "lcraft.plugins.test");
    }

    @Override
    public void onLoad() {
        getModuleCommandManager().addCommand("test", new TestCommand(this));
        //getModuleCommandManager().addInvisibleCommand("invtest", new InvisibleTestCommand(this));
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() { }

}
