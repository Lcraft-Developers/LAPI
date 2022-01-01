package de.lcraft.api.minecraft.spigot.manager.configs;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class Config {

    private YamlConfiguration cfg;
    private File file;
    private File folder;

    public Config(String startPath, String path, String filename) {
        folder = new File(startPath + "//" + path);
        file = new File(folder, filename);
        if(!folder.exists()) {
            folder.mkdir();
            folder.mkdirs();
        }
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        cfg = YamlConfiguration.loadConfiguration(file);
    }
    public Config(String path, String filename) {
        this("plugins//Lcraft-API//", path, filename);
    }
    public Config(String filename) throws IOException {
        this("", filename);
    }

    public Object getOptionWhenNotExistsCreate(String path, Object start) {
        if(exists(path)) {
            return get(path);
        } else {
            set(path, start);
            return start;
        }
    }
    public void set(String root, Object obj) {
        cfg().set(root, obj);
        save();
    }
    public Object get(String root) {
        Object obj = cfg().get(root);
        return obj;
    }
    public String getString(String root) {
        return get(root).toString();
    }
    public Integer getInteger(String root) {
        if(get(root) instanceof Integer) {
            return Integer.valueOf(getString(root));
        } else {
            return null;
        }
    }
    public Double getDouble(String root) {
        if(get(root) instanceof Double) {
            return Double.valueOf(getString(root));
        } else {
            return null;
        }
    }
    public Float getFloat(String root) {
        if(get(root) instanceof Float) {
            return Float.valueOf(getString(root));
        } else {
            return null;
        }
    }
    public Boolean getBoolean(String root) {
        if(get(root) instanceof Boolean) {
            return Boolean.valueOf(getString(root));
        } else {
            return null;
        }
    }
    public boolean exists(String root) {
        return cfg().contains(root);
    }
    public ConfigurationSection getSection(String root) {
        return cfg().getConfigurationSection(root);
    }
    public Set<String> getSectionKeys(String root) {
        return getSection(root).getKeys(false);
    }

    public void save() {
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Configuration cfg() {
        return cfg;
    }

}