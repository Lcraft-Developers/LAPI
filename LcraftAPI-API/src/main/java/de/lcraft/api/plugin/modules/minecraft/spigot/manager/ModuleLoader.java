package de.lcraft.api.plugin.modules.minecraft.spigot.manager;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ModuleLoader {

    private ModuleManager moduleManager;

    public ModuleLoader(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }

    public void loadModuleToService(ArrayList<Module> modules) throws Exception {
        // Add Modules to ArrayList
        for(Module c : modules) {
            getModuleManager().getModules().add(c);
        }

        // Reload Configuration
        for(Module c : modules) {
            c.getModuleDescriptionFile().load();
            c.getModuleDescriptionFile().reloadRequiredModules(moduleManager);
        }
    }
    public List<File> getFilesFromModule(List<Module> modules) {
        List<File> files = new ArrayList<>();
        for(Module c : modules) {
            files.add(c.getFile());
        }
        return files;
    }
    public List<URL> getURLs(List<File> files) throws MalformedURLException {
        List<URL> urls = new ArrayList<>();
        for(File c : files) {
            urls.add(c.toURL());
        }
        return urls;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

}
