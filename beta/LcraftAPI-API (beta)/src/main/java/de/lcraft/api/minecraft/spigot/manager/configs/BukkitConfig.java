package de.lcraft.api.minecraft.spigot.manager.configs;

import lombok.Getter;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

public class BukkitConfig {

    @Getter
    private final YamlConfiguration cfg;
    @Getter
    private final File file;
    @Getter
    private final File folder;

    public BukkitConfig(String startPath, String path, String filename) {
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
        cfg = new YamlConfiguration();
        try {
            cfg.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
    public BukkitConfig(String path, String filename) {
        this("plugins//Lcraft-API//", path, filename);
    }
    public BukkitConfig(String filename) {
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
        getCfg().set(root, obj);
        save();
    }
    public boolean exists(String root) {
        return getCfg().contains(root);
    }

    public final Object get(String root) {
        Object obj = getCfg().get(root);
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
        return getCfg().getConfigurationSection(root);
    }
    public final Set<String> getSectionKeys(String root) {
        return getSection(root).getKeys(false);
    }

    public final void saveStringArrayList(String root, ArrayList<String> list) {
        set(root, list);
    }
    public final ArrayList<String> getStringArrayList(String root) {
        if(Objects.nonNull(getCfg().getStringList(root))) {
            ArrayList<String> all = new ArrayList<>();
            for(String c : getCfg().getStringList(root)) {
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

}