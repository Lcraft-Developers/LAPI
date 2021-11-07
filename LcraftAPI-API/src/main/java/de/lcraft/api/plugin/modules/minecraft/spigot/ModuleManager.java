package de.lcraft.api.plugin.modules.minecraft.spigot;

import de.lcraft.api.plugin.modules.java.utils.FileUtils;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipFile;

public class ModuleManager {

    public static volatile ArrayList<Module> modules = new ArrayList<>();

    public void loadModules(JavaPlugin plugin) {
        File dir = new File("lmodules/");
        if(!dir.exists()) dir.mkdir();
        List<File> files = FileUtils.getAllFilesFromADirectory("lmodules/");
        if(files != null) {
            for(File file : files) {
                loadModule(file, plugin);
            }
        }
    }
    public void loadModule(File file, JavaPlugin plugin) {
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
                                        FileUtils.ClassPathManager.addFile(file);
                                        Class<? extends Module> pluginClass = clazz.asSubclass(Module.class);
                                        Module module = pluginClass.newInstance();

                                        module.setPlugin(plugin);
                                        module.setName(name);
                                        module.setVersion(version);
                                        module.setApi_version(api_version);
                                        module.setAuthors(authors);
                                        module.setDescription(description);

                                        ModuleLoader moduleLoader = new ModuleLoader(module);
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
    }

    public static ArrayList<Module> getModules() {
        return modules;
    }

    public void onEnableAllModules() {
        for(Module c : getModules()) {
            c.onEnable();
        }
    }

    public void onLoadAllModules() throws IOException {
        for(Module c : getModules()) {
            c.onLoad();
        }
    }

    public void onDisableAllModules() {
        for(Module c : getModules()) {
            c.onDisable();
        }
    }

    public class Authors {

        private List<Object> authors;

        public List<Object> getAuthors() {
            return authors;
        }
        public void setAuthors(List<Object> authors) {
            this.authors = authors;
        }
    }

}
