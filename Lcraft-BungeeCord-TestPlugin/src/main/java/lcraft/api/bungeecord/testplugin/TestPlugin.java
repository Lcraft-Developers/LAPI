package lcraft.api.bungeecord.testplugin;

import de.lcraft.api.plugin.modules.minecraft.bungeecord.Module;
import de.lcraft.api.plugin.modules.minecraft.bungeecord.logger.LoggerType;
import lcraft.api.bungeecord.testplugin.commands.TestCommand;

public class TestPlugin extends Module {

    @Override
    public void onLoad() {
        getModuleCommandManager().addCommand("test-bungee", new TestCommand(this));
    }

    @Override
    public void onEnable() {
        getLogger().sendModule(LoggerType.INFO, "Loaded successfully Test");
    }

    @Override
    public void onDisable() {

    }

}
