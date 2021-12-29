package de.lcraft.api.minecraft.bungee.manager.loaders;

import de.lcraft.api.java_utils.CodeHelper;
import de.lcraft.api.minecraft.bungee.manager.Module;
import de.lcraft.api.minecraft.bungee.manager.ModuleDescriptionFile;
import de.lcraft.api.minecraft.bungee.manager.ModuleManager;
import net.md_5.bungee.api.plugin.Plugin;
import java.io.File;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModuleFileLoader {

    private ModuleManager moduleManager;
    private ArrayList<Module> modules;

    public ModuleFileLoader(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
        modules = new ArrayList<>();
    }

    public void loadModules(Plugin plugin) throws Exception {
        File dir = new File("lmodules/");
        if(!dir.exists()) dir.mkdir();
        List<File> files = new CodeHelper().getAllFilesFromADirectory("lmodules/");
        List<File> goodFiles = new ArrayList<>();
        if(files != null) {
            for(File file : files) {
                if(file.getName().endsWith(".jar")) {
                    ModuleDescriptionFile descriptionFile = new ModuleDescriptionFile(file);
                    descriptionFile.load();
                    if(descriptionFile.hasEnoughInformation()) {
                        goodFiles.add(file);
                    }
                }
            }
        }
        queuedModule(goodFiles, plugin);
        getModuleManager().getModuleLoader().loadModuleToService(modules);
    }
    public void queuedModule(List<File> files, Plugin plugin) throws Exception {
        HashMap<ModuleDescriptionFile, Boolean> newModules = new HashMap<>();

        // Add Module to ArrayList
        for(File file : files) {
            ModuleDescriptionFile moduleDescriptionFile = new ModuleDescriptionFile(file);
            moduleDescriptionFile.load();
            newModules.put(moduleDescriptionFile, false);
        }

        // Look if Module it has no requiements
        for(ModuleDescriptionFile m : newModules.keySet()) {
            boolean hasNoRequriement = false;
            if(m.getRequiredStringModules().length != 0) {
                for(String c : m.getRequiredStringModules()) {
                    if((c.equalsIgnoreCase("LcraftAPI")  || c.equalsIgnoreCase("Lcraft-API") || c.equalsIgnoreCase("Lcraft Permissions API") || c.equalsIgnoreCase("Lcraft Languages API")) && m.getRequiredStringModules().length == 1) {
                        hasNoRequriement = true;
                    }
                }
            } else {
                hasNoRequriement = true;
            }
            if(hasNoRequriement) {
                newModules.put(m, true);
                modules.add(getModule(m.getFile(), plugin, m));
            }
        }

        // Load Requiered
        boolean allHasBeenLoaded = false;
        while(!allHasBeenLoaded) {
            for(ModuleDescriptionFile m : newModules.keySet()) {
                boolean isLoaded = newModules.get(m);
                if(!isLoaded) {
                    boolean allRequiredModulesHasBeenLoaded = true;
                    for(String allModules : m.getRequiredStringModules()) {
                        for(ModuleDescriptionFile c : newModules.keySet()) {
                            boolean isLoadedc = newModules.get(c);
                            if(allModules.equalsIgnoreCase(c.getName())) {
                                if(!isLoadedc) allRequiredModulesHasBeenLoaded = false;
                            }
                        }
                    }

                    if(allRequiredModulesHasBeenLoaded) {
                        newModules.put(m, true);
                        modules.add(getModule(m.getFile(), plugin, m));
                    }
                }
            }


            boolean hasBeenLoaded = true;
            for(ModuleDescriptionFile m : newModules.keySet()) {
                if(!newModules.get(m)) hasBeenLoaded = false;
            }
            if(hasBeenLoaded) {
                allHasBeenLoaded = true;
            }
        }
    }
    public Module getModule(File file, Plugin plugin, ModuleDescriptionFile descriptionFile) throws Exception {
        URLClassLoader moduleClassLoader = new ModuleClassLoader(descriptionFile);
        Class<?> main = moduleClassLoader.loadClass(descriptionFile.getBungeeCord_Main());
        Module module = (Module) main.newInstance();

        module.setPlugin(plugin);
        module.setFile(file);
        module.load(moduleManager);

        return module;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

}
