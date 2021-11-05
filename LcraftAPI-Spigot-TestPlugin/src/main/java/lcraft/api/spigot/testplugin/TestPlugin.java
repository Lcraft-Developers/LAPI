package lcraft.api.spigot.testplugin;

import de.lcraft.api.plugin.modules.minecraft.spigot.Module;
import de.lcraft.api.plugin.modules.minecraft.spigot.logger.LoggerPlace;
import de.lcraft.api.plugin.modules.minecraft.spigot.logger.LoggerType;
import lcraft.api.spigot.testplugin.commands.TestCommand;

public class TestPlugin extends Module {

    @Override
    public void onLoad() {
        getModuleCommandManager().addCommand("test-spigot", new TestCommand(this));
    }

    @Override
    public void onEnable() {
        getLogger().sendModule(LoggerType.INFO, LoggerPlace.SERVER, "Loaded successfully Test");
    }

    @Override
    public void onDisable() { }

}
