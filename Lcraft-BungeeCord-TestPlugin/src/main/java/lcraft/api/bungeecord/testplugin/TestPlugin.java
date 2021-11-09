package lcraft.api.bungeecord.testplugin;

import de.lcraft.api.plugin.modules.minecraft.bungeecord.manager.Module;
import de.lcraft.api.plugin.modules.minecraft.bungeecord.manager.logger.ModuleLoggerType;
import de.lcraft.apis.permissions.main.bungeecord.ModuleMain;
import lcraft.api.bungeecord.testplugin.commands.TestCommand;

public class TestPlugin extends Module {

    @Override
    public void onLoad() {
        ModuleMain.getPermsManager().getCommandManager().addCommand("test-bungee", new TestCommand(this));
    }

    @Override
    public void onEnable() {
        getLogger().sendModule(ModuleLoggerType.INFO, "Loaded successfully Test");
    }

    @Override
    public void onDisable() {

    }

}
