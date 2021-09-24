package lcraft.api.testplugin;

import de.lcraft.api.plugin.modules.Module;

public class Testplugin extends Module {

    public Testplugin() {
        super("Test Plugin", "lcraft.plugins.test");
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {
        System.out.println("Plugin started");
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin stopped");
    }

}
