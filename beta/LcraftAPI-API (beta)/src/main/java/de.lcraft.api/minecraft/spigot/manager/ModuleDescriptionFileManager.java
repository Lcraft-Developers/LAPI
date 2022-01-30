package de.lcraft.api.minecraft.spigot.manager;

import de.lcraft.api.java_utils.CodeHelper;
import de.lcraft.api.java_utils.Internet;
import de.lcraft.api.java_utils.connection.SpigotMc;
import de.lcraft.api.minecraft.spigot.manager.Module;
import de.lcraft.api.minecraft.spigot.manager.ModuleManager;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.zip.ZipFile;

public class ModuleDescriptionFileManager {

    private File file;
    private String[] authors;
    private String[] requiredStringModules;
    private String name,
                   version,
                   spigot_main,
                   bungeecord_main,
                   description,
                   spigotmc_id,
                   update_url;
    private Module[] requiredModules;

    public ModuleDescriptionFileManager(File file) {
        this.file = file;
    }
    public final void load() {
        Map<String, Object> data = getAllDatasFromModuleFile();
        if(hasEnoughInformation()) {
            name = data.get("name").toString();
            spigot_main = data.get("spigot-main").toString();
            if(data.containsKey("bungee-main")) {
                bungeecord_main = data.get("bungee-main").toString();
            } else {
                bungeecord_main = null;
            }
            if(data.containsKey("spigotmc_id")) {
                spigotmc_id = data.get("spigotmc_id").toString();
            }
            if(data.containsKey("upadate_url")) {
                update_url = data.get("update_url").toString();
            } else if(data.containsKey("spigotmc_id") && data.get("spigotmc_id") instanceof Integer) {
                update_url = new SpigotMc().getUpdateLink(Integer.valueOf(String.valueOf(data.get("spigotmc_id"))));
            }
            version = data.get("version").toString();
            description = data.get("description").toString();

            // Load Authors
            ArrayList<String> authors = new ArrayList<>();
            if(Objects.nonNull(data.get("authors"))) {
                // Like [LPD, Lcraft]
                String c = data.get("authors").toString().replace("[", "").replace("]", "");
                for(String a : c.split(", ")) {
                    authors.add(a);
                }
            }
            if(Objects.nonNull(data.get("author"))) {
                authors.add(data.get("author").toString());
            }
            this.authors = new CodeHelper().makeArrayListToStringArray(authors);

            // Load required String Modules
            ArrayList<String> requiredModulesStringList = new ArrayList<>();
            if(Objects.nonNull(data.get("required-modules"))) {
                String c = data.get("required-modules").toString().replace("[", "").replace("]", "");
                for(String a : c.split(", ")) {
                    requiredModulesStringList.add(a);
                }
            }
            requiredStringModules = new CodeHelper().makeArrayListToStringArray(requiredModulesStringList);
        }
    }
    public final Module[] reloadRequiredModules(ModuleManager manager) {
        Module[] requiredM = new Module[requiredStringModules.length];
        int i = 0;
        for(Module c : manager.getModules()) {
            if(new CodeHelper().containsFromStringArray(requiredStringModules, c.getModuleDescriptionFile().getName())) {
                requiredM[i] = c;
                i++;
            }
            continue;
        }
        this.requiredModules = requiredM;

        return requiredModules;
    }

    public final Map<String, Object> getAllDatasFromModuleFile() {
        ZipFile jarFile = null;
        try {
            jarFile = new ZipFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Yaml yaml = new Yaml();
        try {
            if(Objects.nonNull(jarFile.getEntry("module.yml")) && Objects.nonNull(jarFile)) {
                InputStream inputStream = jarFile.getInputStream(jarFile.getEntry("module.yml"));
                Map<String, Object> data = yaml.load(inputStream);
                return data;
            } else if(Objects.nonNull(jarFile.getEntry("module.yml"))) {
                new Exception("No module.yml found").printStackTrace();
                return null;
            } else if(Objects.nonNull(jarFile)) {
                new Exception("No jarFile found or file is not a jarFile").printStackTrace();
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public final boolean hasEnoughInformation() {
        Map<String, Object> data = getAllDatasFromModuleFile();
        if(Objects.nonNull(data.get("name"))) {
            String name = data.get("name").toString();
            if(Objects.nonNull(data.get("spigot-main"))) {
                if(Objects.nonNull(data.get("version"))) {
                    if(Objects.nonNull(data.get("authors")) || Objects.nonNull(data.get("author"))) {
                        if(Objects.nonNull(data.get("description"))) {
                            if(Objects.nonNull(data.get("required-modules"))) {
                                return true;
                            } else {
                                System.out.println("The spigot module " + name + " could not be loaded because the required-modules does not exist in module.yml");
                            }
                        } else {
                            System.out.println("The spigot module " + name + " could not be loaded because the description does not exist in module.yml");
                        }
                    } else {
                        System.out.println("The spigot module " + name + " could not be loaded because the author does not exist in module.yml");
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
    public final String getName() {
        return name;
    }
    public final File getFile() {
        return file;
    }
    public final String[] getAuthors() {
        return authors;
    }
    public final String getVersion() {
        return version;
    }
    public final String getDescription() {
        return description;
    }
    public final String getBungeeCord_Main() {
        return bungeecord_main;
    }
    public final String getSpigot_main() {
        return spigot_main;
    }
    public final Module[] getRequiredModules() {
        return requiredModules;
    }
    public final String[] getRequiredStringModules() {
        return requiredStringModules;
    }
    public final String getSpigotmc_id() {
        return spigotmc_id;
    }
    public final String getUpdate_url() {
        return update_url;
    }

}
