package lcraft.api.spigot.testplugin;

import de.lcraft.api.plugin.modules.minecraft.spigot.manager.Module;
import de.lcraft.api.plugin.modules.minecraft.spigot.manager.logger.ModuleLoggerPlace;
import de.lcraft.api.plugin.modules.minecraft.spigot.manager.logger.ModuleLoggerType;
import de.lcraft.apis.permissions.main.spigot.ModuleMain;
import lcraft.api.spigot.testplugin.commands.TestCommand;

public class TestPlugin extends Module {

    @Override
    public void onLoad() {
        ModuleMain.getPermsManager().getCommandManager().addCommand("test-spigot", new TestCommand(this));
    }

    @Override
    public void onEnable() {
        getLogger().sendModule(ModuleLoggerType.INFO, ModuleLoggerPlace.SERVER, "Loaded successfully Test");
    }

    @Override
    public void onDisable() { }

}
