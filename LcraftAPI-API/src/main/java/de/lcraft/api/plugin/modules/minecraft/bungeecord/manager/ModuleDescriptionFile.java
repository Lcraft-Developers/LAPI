package de.lcraft.api.plugin.modules.minecraft.bungeecord.manager;

import de.lcraft.api.plugin.modules.java.utils.ArrayHelper;
import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.zip.ZipFile;

public class ModuleDescriptionFile {

    private File file;
    private String[] authors;
    private String[] requiredStringModules;
    private String name,
                   version,
                   spigot_main,
                   bungeecord_main,
                   description;
    private Module[] requiredModules;

    public ModuleDescriptionFile(File file) {
        this.file = file;
    }

    public void load() throws Exception {
        Map<String, Object> data = getAllDatasFromModuleFile();
        if(hasEnoughInformation()) {
            name = data.get("name").toString();
            bungeecord_main = data.get("bungee-main").toString();
            if(data.containsKey("spigot-main")) {
                spigot_main = data.get("spigot-main").toString();
            } else {
                spigot_main = null;
            }
            version = data.get("version").toString();
            description = data.get("description").toString();

            // Load Authors
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
            this.authors = ArrayHelper.makeArrayListToStringArray(authors);

            // Load required String Modules
            ArrayList<String> requiredModulesStringList = new ArrayList<>();
            if(data.get("required-modules") != null) {
                String c = data.get("required-modules").toString().replace("[", "").replace("]", "");
                for(String a : c.split(", ")) {
                    requiredModulesStringList.add(a);
                }
            }
            requiredStringModules = ArrayHelper.makeArrayListToStringArray(requiredModulesStringList);
        }
    }
    public Module[] reloadRequiredModules(ModuleManager manager) {
        Module[] requiredM = new Module[requiredStringModules.length];
        int i = 0;
        for(Module c : manager.getModules()) {
            if(ArrayHelper.containsFromStringArray(requiredStringModules, c.getModuleDescriptionFile().getName())) {
                requiredM[i] = c;
                i++;
            }
        }
        this.requiredModules = requiredM;

        return requiredModules;
    }

    public Map<String, Object> getAllDatasFromModuleFile() throws Exception {
        ZipFile jarFile = new ZipFile(file);
        Yaml yaml = new Yaml();
        InputStream inputStream = jarFile.getInputStream(jarFile.getEntry("module.yml"));
        Map<String, Object> data = yaml.load(inputStream);
        return data;
    }
    public boolean hasEnoughInformation() throws Exception {
        Map<String, Object> data = getAllDatasFromModuleFile();
        if(data.get("name") != null) {
            String name = data.get("name").toString();
            if(data.get("bungee-main") != null) {
                if(data.get("version") != null) {
                    if(data.get("authors") != null || data.get("author") != null) {
                        if(data.get("description") != null) {
                            if(data.get("required-modules") != null) {
                                return true;
                            } else {
                                System.out.println("The BungeeCord module " + name + " could not be loaded because the required-modules does not exist in module.yml");
                            }
                        } else {
                            System.out.println("The BungeeCord module " + name + " could not be loaded because the description does not exist in module.yml");
                        }
                    } else {
                        System.out.println("The BungeeCord module " + name + " could not be loaded because the author does not exist in module.yml");
                    }
                } else {
                    System.out.println("The BungeeCord module " + name + " could not be loaded because the version does not exist in module.yml");
                }
            } else {
                System.out.println("The BungeeCord module " + name + " could not be loaded because the main does not exist in module.yml");
            }
        } else {
            System.out.println("A BungeeCord module could not be loaded because the name in module.yml does not exist!");
        }
        return false;
    }
    public String getName() {
        return name;
    }
    public File getFile() {
        return file;
    }
    public String[] getAuthors() {
        return authors;
    }
    public String getVersion() {
        return version;
    }
    public String getDescription() {
        return description;
    }
    public String getBungeeCord_Main() {
        return bungeecord_main;
    }
    public String getSpigot_main() {
        return spigot_main;
    }
    public Module[] getRequiredModules() {
        return requiredModules;
    }
    public String[] getRequiredStringModules() {
        return requiredStringModules;
    }

}
