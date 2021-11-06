package lcraft.api.spigot.testplugin;

import de.lcraft.api.plugin.modules.minecraft.spigot.Module;
import de.lcraft.api.plugin.modules.minecraft.spigot.logger.LoggerPlace;
import de.lcraft.api.plugin.modules.minecraft.spigot.logger.LoggerType;
import de.lcraft.apis.permissions.main.spigot.ModuleMain;
import lcraft.api.spigot.testplugin.commands.TestCommand;

public class TestPlugin extends Module {

    @Override
    public void onLoad() {
        ModuleMain.getPermsManager().getCommandManager().addCommand("test-spigot", new TestCommand(this));
    }

    @Override
    public void onEnable() {
        getLogger().sendModule(LoggerType.INFO, LoggerPlace.SERVER, "Loaded successfully Test");
    }

    @Override
    public void onDisable() { }

}
