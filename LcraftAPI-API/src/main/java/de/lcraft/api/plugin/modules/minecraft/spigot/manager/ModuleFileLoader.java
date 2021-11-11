package de.lcraft.api.plugin.modules.minecraft.spigot.manager;

import de.lcraft.api.plugin.modules.java.utils.FileUtils;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.A;
import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipFile;

public class ModuleFileLoader {

    private ModuleManager moduleManager;
    private HashMap<Integer, File> whatLoadFirst;
    private ArrayList<Module> modules;

    public ModuleFileLoader(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;

        whatLoadFirst = new HashMap<>();
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
                    if(hasEnoughInformation(file)) {
                        goodFiles.add(file);
                    }
                }
            }
        }
        queuedModule(goodFiles, plugin);
        getModuleManager().getModuleLoader().loadModuleToService(modules);
    }
    public Map<String, Object> getAllDatasFromFile(File file) throws Exception {
        ZipFile jarFile = new ZipFile(file);
        Yaml yaml = new Yaml();
        InputStream inputStream = jarFile.getInputStream(jarFile.getEntry("module.yml"));
        Map<String, Object> data = yaml.load(inputStream);
        return data;
    }
    public boolean hasEnoughInformation(File file) throws Exception {
        Map<String, Object> data = getAllDatasFromFile(file);
        if(data.get("name") != null) {
            String name = data.get("name").toString();
            if(data.get("spigot-main") != null) {
                if(data.get("version") != null) {
                    if(data.get("authors") != null || data.get("author") != null) {
                        if(data.get("description") != null) {
                            if(data.get("required-apis") != null) {
                                return true;
                            } else {
                                System.out.println("The spigot module " + name + " could not be loaded because the required-apis does not exist in module.yml");
                            }
                        } else {
                            System.out.println("The spigot module " + name + " could not be loaded because the description does not exist in module.yml");
                        }
                    } else {
                        System.out.println("The spigot module " + name + " could not be loaded because the athor does not exist in module.yml");
                    }
                } else {
                    System.out.println("The spigot module " + name + " could not be loaded because the version does not exist in module.yml");
                }
            } else {
                System.out.println("The spigot module " + name + " could not be loaded because the main does not exist in module.yml");
            }
        } else {
            System.out.println("A spigot module could not be loaded because the name in module.yml does not exist!");
        }
        return false;
    }
    public void queuedModule(List<File> files, JavaPlugin plugin) throws Exception {
        HashMap<NewModule, Boolean> newModules = new HashMap<>();
        for(File file : files) {
            Map<String, Object> datas = getAllDatasFromFile(file);
            String c = datas.get("required-apis").toString().replace("[", "").replace("]", "");
            String[] requiredAPIS = new String[c.split(", ").length];
            int e = 0;
            for(String a : c.split(", ")) {
                requiredAPIS[e] = a;
                e++;
            }
            newModules.put(new NewModule(file, datas.get("name").toString(), requiredAPIS), false);
        }

        for(NewModule m : newModules.keySet()) {
            boolean hasNoRequriement = false;
            if(m.getRequiredModules().length != 0) {
                for(String c : m.getRequiredModules()) {
                    if((c.equalsIgnoreCase("LcraftAPI")  || c.equalsIgnoreCase("Lcraft-API")) && m.getRequiredModules().length == 1) {
                        hasNoRequriement = true;
                    }
                }
            } else {
                hasNoRequriement = true;
            }
            if(hasNoRequriement) {
                getModuleManager().getModuleLoader().addToRuntime(m.getFile());

                newModules.put(m, true);
                modules.add(getModule(m.getFile(), plugin));
            }
        }

        boolean allHasBeenLoaded = false;
        while(!allHasBeenLoaded) {
            for(NewModule m : newModules.keySet()) {
                boolean isLoaded = newModules.get(m);
                if(!isLoaded) {
                    boolean allRequiredModulesHasBeenLoaded = true;
                    for(String allModules : m.getRequiredModules()) {
                        for(NewModule c : newModules.keySet()) {
                            boolean isLoadedc = newModules.get(c);
                            if(allModules.equalsIgnoreCase(c.getName())) {
                                if(!isLoadedc) allRequiredModulesHasBeenLoaded = false;
                            }
                        }
                    }

                    if(allRequiredModulesHasBeenLoaded) {
                        getModuleManager().getModuleLoader().addToRuntime(m.getFile());

                        newModules.put(m, true);
                        modules.add(getModule(m.getFile(), plugin));
                    }
                }
            }



            boolean hasBeenLoaded = true;
            for(NewModule m : newModules.keySet()) {
                if(!newModules.get(m)) hasBeenLoaded = false;
            }
            if(hasBeenLoaded) {
                allHasBeenLoaded = true;
            }
        }
    }
    public Module getModule(File file, JavaPlugin plugin) throws Exception {
        Map<String, Object> data = getAllDatasFromFile(file);
        String name = data.get("name").toString();
        String main = data.get("spigot-main").toString();
        String version = data.get("version").toString();

        ArrayList<String> authors = new ArrayList<>();

        if(data.get("authors") != null) {
            // Like [LPD, Lcraft]
            String c = data.get("authors").toString().replace("[", "").replace("]", "");
            for(String a : c.split(", ")) {
                authors.add(a);
            }
        }
        if(data.get("author") != null) {
            authors.add(data.get("author").toString());
        }

        String description = data.get("description").toString();


        ClassLoader classLoader = URLClassLoader.newInstance(new URL[]{file.toURI().toURL()}, getClass().getClassLoader());
        Class<?> clazz = classLoader.loadClass(main);
        Class<? extends Module> pluginClass = clazz.asSubclass(Module.class);
        Module module = pluginClass.newInstance();

        module.setPlugin(plugin);
        module.setName(name);
        module.setVersion(version);
        module.setAuthors(authors);
        module.setDescription(description);
        module.setMainFile(file);

        return module;
    }

    /*public void loadModule(File file, JavaPlugin plugin) {
        if(!file.isDirectory() && file.getName().endsWith(".jar")) {
            try {
                ZipFile jarFile = new ZipFile(file);
                Yaml yaml = new Yaml();
                InputStream inputStream = jarFile.getInputStream(jarFile.getEntry("module.yml"));
                Map<String, Object> data = yaml.load(inputStream);
                if(data.get("name") != null) {
                    String name = data.get("name").toString();
                    if(data.get("spigot-main") != null) {
                        String main = data.get("spigot-main").toString();
                        if(data.get("version") != null) {
                            String version = data.get("version").toString();
                            if(data.get("api-version") != null) {
                                String api_version = data.get("api-version").toString();
                                if(data.get("authors") != null || data.get("author") != null) {
                                    ArrayList<String> authors = new ArrayList<>();

                                    if(data.get("authors") != null) {
                                        // Like [LPD, Lcraft]
                                        String c = data.get("authors").toString().replace("[", "").replace("]", "");
                                        for(String a : c.split(", ")) {
                                            authors.add(a);
                                        }
                                    }
                                    if(data.get("author") != null) {
                                        authors.add(data.get("author").toString());
                                    }

                                    if(data.get("description") != null) {
                                        String description = data.get("description").toString();

                                        ClassLoader classLoader = URLClassLoader.newInstance(new URL[]{file.toURI().toURL()}, getClass().getClassLoader());
                                        Class<?> clazz = classLoader.loadClass(main);
                                        Class<? extends Module> pluginClass = clazz.asSubclass(Module.class);
                                        Module module = pluginClass.newInstance();

                                        module.setPlugin(plugin);
                                        module.setName(name);
                                        module.setVersion(version);
                                        module.setApi_version(api_version);
                                        module.setAuthors(authors);
                                        module.setDescription(description);
                                        module.setMainFile(file);

                                        ModuleEventManager moduleLoader = new ModuleEventManager(module);
                                        moduleLoader.loadModule();
                                    } else {
                                        System.out.println("The spigot module " + name + " could not be loaded because the description does not exist in module.yml");
                                    }
                                } else {
                                    System.out.println("The spigot module " + name + " could not be loaded because the athor does not exist in module.yml");
                                }
                            } else {
                                System.out.println("The spigot module " + name + " could not be loaded because the api-version does not exist in module.yml");
                            }
                        } else {
                            System.out.println("The spigot module " + name + " could not be loaded because the version does not exist in module.yml");
                        }
                    } else {
                        System.out.println("The spigot module " + name + " could not be loaded because the main does not exist in module.yml");
                    }
                } else {
                    System.out.println("A spigot module could not be loaded because the name in module.yml does not exist!");
                }
            } catch(IOException | ClassNotFoundException | IllegalAccessException | InstantiationException exception) {
                exception.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

}
