package lcraft.api.testplugin;

import de.lcraft.api.plugin.logger.Logger;
import de.lcraft.api.plugin.logger.LoggerPlace;
import de.lcraft.api.plugin.logger.LoggerType;
import de.lcraft.api.plugin.modules.Module;
import lcraft.api.testplugin.commands.TestCommand;

public class Testplugin extends Module {

    public Testplugin() {
        super("Test Plugin", "lcraft.plugins.test");
    }

    @Override
    public void onLoad() {
        getModuleManager().getMainApi().registerModuleCommand("test", new TestCommand(this));
        getLogger().sendModule(LoggerType.INFO, LoggerPlace.SERVER, "This Plugin loaded");
    }

    @Override
    public void onEnable() { getLogger().sendModule(LoggerType.INFO, LoggerPlace.SERVER, "This Plugin started");}

    @Override
    public void onDisable() { getLogger().sendModule(LoggerType.INFO, LoggerPlace.SERVER, "This Plugin stoppted");}

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public String getId() {
        return id;
    }

}
