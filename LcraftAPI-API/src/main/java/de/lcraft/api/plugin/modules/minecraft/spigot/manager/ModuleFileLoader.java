package de.lcraft.api.plugin.modules.minecraft.spigot.manager;

import de.lcraft.api.plugin.modules.java.utils.FileUtils;
import de.lcraft.api.plugin.modules.minecraft.spigot.manager.classloaders.ModuleClassLoader;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
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

    public void loadModules(JavaPlugin plugin) throws Exception {
        File dir = new File("lmodules/");
        if(!dir.exists()) dir.mkdir();
        List<File> files = FileUtils.getAllFilesFromADirectory("lmodules/");
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
    public void queuedModule(List<File> files, JavaPlugin plugin) throws Exception {
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
                    if((c.equalsIgnoreCase("LcraftAPI")  || c.equalsIgnoreCase("Lcraft-API")) && m.getRequiredStringModules().length == 1) {
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
    public Module getModule(File file, JavaPlugin plugin, ModuleDescriptionFile descriptionFile) throws Exception {
        // Loading of Main

        ModuleClassLoader moduleClassLoader = new ModuleClassLoader(descriptionFile);
        Class<?> clazz = moduleClassLoader.loadClass(descriptionFile.getSpigot_main());
        Class<? extends Module> pluginClass = clazz.asSubclass(Module.class);
        Module module = pluginClass.newInstance();
        //Module module = (Module) clazz.getDeclaredConstructor().newInstance();

        module.setPlugin(plugin);
        module.setFile(file);
        module.load(moduleManager);

        return module;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

}
