package lcraft.api.testplugin;

import de.lcraft.api.plugin.logger.LoggerPlace;
import de.lcraft.api.plugin.logger.LoggerType;
import de.lcraft.api.plugin.modules.Module;

public class Testplugin extends Module {

    public Testplugin() {
        super("Test Plugin", "lcraft.plugins.test");
    }

    @Override
    public void onLoad() { }

    @Override
    public void onEnable() { }

    @Override
    public void onDisable() { }

}
