package lcraft.api.testplugin;

import de.lcraft.api.plugin.logger.LoggerPlace;
import de.lcraft.api.plugin.logger.LoggerType;
import de.lcraft.api.plugin.modules.Module;

public class Testplugin extends Module {

    public Testplugin() {
        super("Test Plugin", "lcraft.plugins.test");
    }

    @Override
    public void onLoad() { getLogger().sendModule(LoggerType.INFO, LoggerPlace.SERVER, "This Plugin loaded");}

    @Override
    public void onEnable() { getLogger().sendModule(LoggerType.INFO, LoggerPlace.SERVER, "This Plugin started");}

    @Override
    public void onDisable() { getLogger().sendModule(LoggerType.INFO, LoggerPlace.SERVER, "This Plugin stoppted");}

}
