package de.lcraft.api.minecraft.spigot.manager.configs;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Config {

    private final YamlConfiguration cfg;
    private final File file;
    private final File folder;

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
    public Config(String filename) {
        this("", filename);
    }

    public Object getDefault(String path, Object start) {
        if(exists(path)) {
            return get(path);
        } else {
            set(path, start);
            return start;
        }
    }
    public void set(String root, Object obj) {
        c().set(root, obj);
        save();
    }
    public boolean exists(String root) {
        return c().contains(root);
    }

    public final Object get(String root) {
        Object obj = c().get(root);
        return obj;
    }
    public final String getString(String root) {
        return get(root).toString();
    }
    public final Integer getInteger(String root) {
        if(get(root) instanceof Integer) {
            return Integer.valueOf(getString(root));
        } else {
            return null;
        }
    }
    public final Double getDouble(String root) {
        if(get(root) instanceof Double) {
            return Double.valueOf(getString(root));
        } else {
            return null;
        }
    }
    public final Float getFloat(String root) {
        if(get(root) instanceof Float) {
            return Float.valueOf(getString(root));
        } else {
            return null;
        }
    }
    public final Boolean getBoolean(String root) {
        if(get(root) instanceof Boolean) {
            return Boolean.valueOf(getString(root));
        } else {
            return null;
        }
    }
    public final ConfigurationSection getSection(String root) {
        return c().getConfigurationSection(root);
    }
    public final Set<String> getSectionKeys(String root) {
        return getSection(root).getKeys(false);
    }

    public final void saveStringArrayList(String root, ArrayList<String> list) {
        set(root, list);
    }
    public final ArrayList<String> getStringArrayList(String root) {
        if(Objects.nonNull(c().getStringList(root))) {
            ArrayList<String> all = new ArrayList<>();
            for(String c : c().getStringList(root)) {
                all.add(c);
            }
            return all;
        }
        saveStringArrayList(root, new ArrayList<>());
        return getStringArrayList(root);
    }

    public final void save() {
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private final Configuration c() {
        return cfg;
    }

}